package org.sitracel.paie.modelvalidator.MouvementPaie;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.compiere.model.MTable;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.paie.model.MHRMouvementPaie;
import org.sitracel.paie.model.MHRMouvementPaieType;

/**
 * ModelValidator HR_Mouvement_Paie
 *
 * Reprend et complète la logique des callouts pour couvrir les cas
 * où le mouvement est créé par code (PayrollOrchestrator, etc.)
 * et non via l'interface utilisateur.
 *
 * BEFORE_SAVE :
 *   1. Sync IsIndemnite depuis le type (si type renseigné)
 *   2. Calculer IsRecurrent selon Nombre_Mensualite
 *   3. Calculer mensualités si mode non récurrent
 *   4. Initialiser Solde si non récurrent et Solde non encore posé
 */
public class SitracelModelValidatorMouvementPaie implements ModelValidator {

    private static final Logger log = Logger.getLogger(
            SitracelModelValidatorMouvementPaie.class.getName());

    private int adClientId = 0;

    @Override
    public void initialize(ModelValidationEngine engine, org.compiere.model.MClient client) {
        if (client != null) adClientId = client.getAD_Client_ID();
        engine.addModelChange(MHRMouvementPaie.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return adClientId; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        if (!(po instanceof MHRMouvementPaie)) return null;
        if (type != ModelValidator.TYPE_BEFORE_NEW && type != ModelValidator.TYPE_BEFORE_CHANGE)
            return null;

        MHRMouvementPaie mouvement = (MHRMouvementPaie) po;

        // ---------------------------------------------------------------
        // 1. Sync IsIndemnite depuis le type
        // ---------------------------------------------------------------
        int typeId = mouvement.getHR_MouvementPaieType_ID();
        if (typeId > 0) {
            MHRMouvementPaieType type = new MHRMouvementPaieType(Env.getCtx(), typeId, po.get_TrxName());
            if (type != null && type.getHR_MouvementPaieType_ID() > 0) {
                Object isIndem = type.get_Value("IsIndemnite");
                String valeur = (isIndem != null) ? isIndem.toString() : "N";
                mouvement.set_Value("IsIndemnite", valeur);
                log.fine("MouvementPaie [" + mouvement.getName()
                        + "] IsIndemnite=" + valeur + " (depuis type " + type.getName() + ")");
            }
        }

        // ---------------------------------------------------------------
        // 2. IsRecurrent selon Nombre_Mensualite
        // ---------------------------------------------------------------
        int nbMensualite = mouvement.getNombre_Mensualite();
        boolean recurrent = (nbMensualite <= 0);
        mouvement.set_Value("IsRecurrent", recurrent ? "Y" : "N");

        // ---------------------------------------------------------------
        // 3 & 4. Mensualités + Solde (mode non récurrent uniquement)
        // ---------------------------------------------------------------
        if (recurrent) {
            // Mode CDI : on ne touche pas aux montants
            log.fine("MouvementPaie [" + mouvement.getName() + "] mode récurrent");
            return null;
        }

        BigDecimal montantTotal = mouvement.getMontant_Total();
        if (montantTotal == null || montantTotal.compareTo(BigDecimal.ZERO) <= 0) return null;

        BigDecimal acompte = mouvement.getAcompte();
        BigDecimal base = montantTotal;
        if (acompte != null && acompte.compareTo(BigDecimal.ZERO) > 0) {
            base = base.subtract(acompte);
        }

        BigDecimal mensualite = base.divideToIntegralValue(BigDecimal.valueOf(nbMensualite));
        BigDecimal derniere;
        if (nbMensualite == 1) {
            derniere = mensualite;
        } else {
            derniere = base.subtract(mensualite.multiply(BigDecimal.valueOf(nbMensualite - 1)));
        }

        mouvement.setMontant_Mensualite(mensualite);
        mouvement.setMontant_Derniere_Mensualite(derniere);

        // Initialiser Solde seulement s'il n'est pas déjà posé (pour ne pas
        // écraser un solde partiellement remboursé lors d'un recalcul)
        BigDecimal soldeActuel = mouvement.getSolde();
        if (soldeActuel == null || soldeActuel.compareTo(BigDecimal.ZERO) == 0) {
            mouvement.setSolde(base);
        }

        log.fine("MouvementPaie [" + mouvement.getName()
                + "] mensualite=" + mensualite
                + " derniere=" + derniere
                + " solde=" + mouvement.getSolde());

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
