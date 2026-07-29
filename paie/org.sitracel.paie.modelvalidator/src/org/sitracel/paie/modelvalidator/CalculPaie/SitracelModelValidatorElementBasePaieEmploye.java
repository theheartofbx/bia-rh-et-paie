package org.sitracel.paie.modelvalidator.CalculPaie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.process.service.PayrollOrchestrator;
import org.sitracel.paie.process.service.calcul.PayrollCalculEngine;

public class SitracelModelValidatorElementBasePaieEmploye implements ModelValidator {

    private static final CLogger log = CLogger.getCLogger(
            SitracelModelValidatorElementBasePaieEmploye.class);

    // Représente un contrat avec ses dates
    private static class PeriodeContrat {
        Timestamp dateDebut;
        Timestamp dateFin; // null = CDI
        int contratId;
        String statut;

        PeriodeContrat(int contratId, Timestamp dateDebut, Timestamp dateFin, String statut) {
            this.contratId = contratId;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.statut = statut;
        }
    }

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRElementBasePaieEmploye.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() {
        return 0;
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        if (!MHRElementBasePaieEmploye.Table_Name.equalsIgnoreCase(po.get_TableName())) {
            return null;
        }

        MHRElementBasePaieEmploye element = (MHRElementBasePaieEmploye) po;

        // ---------------------------------------------------------------
        // AFTER : recalculer conge et paie si les elements changent
        // ---------------------------------------------------------------
        if (type == ModelValidator.TYPE_AFTER_CHANGE
                || type == ModelValidator.TYPE_AFTER_NEW
                || type == ModelValidator.TYPE_AFTER_DELETE) {
            recalculerCongeEtPaieSiNecessaire(element);
            return null;
        }

        if (type != ModelValidator.TYPE_BEFORE_NEW
                && type != ModelValidator.TYPE_BEFORE_CHANGE) {
            return null;
        }


        int bpartnerId = element.getC_BPartner_ID();
        Timestamp dateDebut = element.getDate_Debut();
        Timestamp dateFin = element.getDate_Fin();

        if (bpartnerId <= 0 || dateDebut == null) {
            return null;
        }

        // Récupérer tous les contrats de l'employé qui intersectent la période
        // triés par date_debut ASC pour vérifier la continuité
        String sql = "SELECT c.hr_contrat_id, c.date_debut, c.date_fin, cs.name AS statut "
                + "FROM adempiere.hr_contrat c "
                + "JOIN adempiere.hr_contratstatut cs "
                + "  ON cs.hr_contratstatut_id = c.hr_contratstatut_id "
                + "WHERE c.c_bpartner_id = ? "
                + "  AND c.isactive = 'Y' "
                + "  AND c.date_debut <= ? " // commence avant ou à la fin de notre période
                + "  AND (c.date_fin IS NULL OR c.date_fin >= ?) " // finit après ou à la date_debut
                + "ORDER BY c.date_debut ASC";

        // La borne droite de notre période (date_fin si renseignée, sinon date_debut)
        Timestamp borneGauche = dateDebut;
        Timestamp borneDroite = (dateFin != null) ? dateFin : new Timestamp(System.currentTimeMillis());

        List<PeriodeContrat> contrats = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, po.get_TrxName());
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, borneDroite);
            pstmt.setTimestamp(3, borneGauche);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                contrats.add(new PeriodeContrat(
                        rs.getInt("hr_contrat_id"),
                        rs.getTimestamp("date_debut"),
                        rs.getTimestamp("date_fin"),
                        rs.getString("statut")));
            }
        } catch (Exception e) {
            log.severe("Erreur recherche contrats : " + e.getMessage());
            return "Erreur lors de la vérification des contrats : " + e.getMessage();
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

        // Aucun contrat trouvé
        if (contrats.isEmpty()) {
            return buildMessageAucunContrat(dateDebut, dateFin);
        }

        // Vérifier les statuts — tous les contrats couvrants doivent être Actifs
        for (PeriodeContrat c : contrats) {
            if (!"Actif".equalsIgnoreCase(c.statut)) {
                return "Un contrat couvrant cette période a le statut \""
                        + c.statut
                        + "\". Seul un contrat avec le statut \"Actif\" est accepté. "
                        + "Veuillez vérifier le statut du contrat (ID: " + c.contratId + ").";
            }
        }

        // Vérifier que le premier contrat couvre bien la date de début
        PeriodeContrat premier = contrats.get(0);
        if (premier.dateDebut.after(dateDebut)) {
            return buildMessageAucunContrat(dateDebut, dateFin);
        }

        // Vérifier la continuité et la couverture complète
        // On parcourt les contrats et on vérifie qu'il n'y a pas de trou
        Timestamp couvertureJusqua = premier.dateFin; // null = infini (CDI)

        for (int i = 1; i < contrats.size(); i++) {
            PeriodeContrat suivant = contrats.get(i);

            // Si le contrat précédent est un CDI, il couvre tout → on peut arrêter
            if (couvertureJusqua == null) {
                break;
            }

            // Vérifier qu'il n'y a pas de trou entre le contrat précédent et le suivant
            // On tolère une continuité immédiate (date_debut_suivant <= date_fin_precedent + 1 jour)
            long jourMs = 24L * 60 * 60 * 1000;
            if (suivant.dateDebut.getTime() > couvertureJusqua.getTime() + jourMs) {
                // Il y a un trou
                return "Il existe un trou dans la couverture contractuelle de cet employé "
                        + "entre le " + couvertureJusqua + " et le " + suivant.dateDebut + ". "
                        + "La période demandée [" + dateDebut
                        + (dateFin != null ? " → " + dateFin : "")
                        + "] ne peut pas être enregistrée.";
            }

            // Étendre la couverture
            if (suivant.dateFin == null) {
                couvertureJusqua = null; // CDI — couverture infinie
            } else if (couvertureJusqua != null
                    && suivant.dateFin.after(couvertureJusqua)) {
                couvertureJusqua = suivant.dateFin;
            }
        }

        // Vérifier que la couverture atteint la borne droite
        if (dateFin != null && couvertureJusqua != null
                && couvertureJusqua.before(dateFin)) {
            return "La couverture contractuelle de cet employé s'arrête le "
                    + couvertureJusqua
                    + " mais la date de fin demandée est le " + dateFin + ". "
                    + "Veuillez créer ou prolonger un contrat pour couvrir cette période.";
        }

        // Tout est valide — assigner le contrat le plus pertinent (celui qui couvre date_debut)
        element.setHR_Contrat_ID(premier.contratId);
        log.info("Contrat " + premier.contratId + " assigné automatiquement.");

        return null;
    }

    private String buildMessageAucunContrat(Timestamp dateDebut, Timestamp dateFin) {
        if (dateFin != null) {
            return "Aucun contrat actif ne couvre la période ["
                    + dateDebut + " → " + dateFin + "]. "
                    + "Veuillez d'abord créer un contrat pour cet employé.";
        } else {
            return "Aucun contrat actif ne couvre la date de début ["
                    + dateDebut + "]. "
                    + "Veuillez d'abord créer un contrat pour cet employé.";
        }
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }

    // -------------------------------------------------------------------------
    // Recalcul automatique conge + paie courante
    // -------------------------------------------------------------------------

    /**
     * Quand les elements de paie d un employe changent :
     * 1. Si un conge valide futur existe -> recalculer l indemnite de conge
     * 2. Recalculer la paie de la periode salariale courante
     */
    private void recalculerCongeEtPaieSiNecessaire(MHRElementBasePaieEmploye element) {
        int bpartnerId = element.getC_BPartner_ID();
        if (bpartnerId <= 0) return;

        String trxName = element.get_TrxName();

        // 1. Conge valide futur : recalculer l indemnite
        try {
            String sqlConge = "SELECT hr_holiday_id FROM adempiere.hr_holiday"
                    + " WHERE c_bpartner_id = ?"
                    + " AND hr_congestatut_id = 1000003"
                    + " AND date_debut_effective > now()"
                    + " AND isactive = 'Y'";

            int holidayId = DB.getSQLValue(trxName, sqlConge, bpartnerId);
            if (holidayId > 0) {
                log.info("Element paie modifie pour bpartner=" + bpartnerId
                        + " -> recalcul indemnite conge (holiday=" + holidayId + ")");
                PayrollOrchestrator.calculerIndemniteConge(bpartnerId, holidayId);
            }
        } catch (Exception e) {
            log.warning("Erreur recalcul conge apres modification element paie : "
                    + e.getMessage());
        }

        // 2. Paie de la periode courante : recalculer
        try {
            int periodeId = DB.getSQLValue(trxName,
                    "SELECT hr_periode_salariale_id FROM adempiere.hr_periode_salariale"
                    + " WHERE date_debut_defaut <= now()"
                    + " AND date_fin_defaut >= now()"
                    + " AND isactive = 'Y'");
            if (periodeId > 0) {
                log.info("Element paie modifie pour bpartner=" + bpartnerId
                        + " -> recalcul paie periode courante (periode=" + periodeId + ")");
                PayrollCalculEngine.calculerPaie(bpartnerId, periodeId, trxName);
            }
        } catch (Exception e) {
            log.warning("Erreur recalcul paie apres modification element paie : "
                    + e.getMessage());
        }
    }

}
