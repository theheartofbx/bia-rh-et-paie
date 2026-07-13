package org.sitracel.paie.modelvalidator.MouvementPaie;

import java.math.BigDecimal;
import java.util.logging.Logger;

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
 * BEFORE_NEW + BEFORE_CHANGE :
 *   1. Sync IsIndemnite depuis le type
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
        if (type != ModelValidator.TYPE_BEFORE_NEW
                && type != ModelValidator.TYPE_BEFORE_CHANGE) return null;

        MHRMouvementPaie m = (MHRMouvementPaie) po;

        // -----------------------------------------------------------
        // 1. Sync IsIndemnite depuis le type
        // -----------------------------------------------------------
        int typeId = m.getHR_MouvementPaieType_ID();
        if (typeId > 0) {
            MHRMouvementPaieType typeObj =
                    new MHRMouvementPaieType(Env.getCtx(), typeId, po.get_TrxName());
            if (typeObj != null && typeObj.getHR_MouvementPaieType_ID() > 0) {
                m.setIsIndemnite(typeObj.getIsIndemnite());
                log.fine("MouvementPaie [" + m.getName()
                        + "] IsIndemnite=" + typeObj.getIsIndemnite()
                        + " (type=" + typeObj.getName() + ")");
            }
        }

        // -----------------------------------------------------------
        // 2. IsRecurrent selon Nombre_Mensualite
        // -----------------------------------------------------------
        int nbMensualite = m.getNombre_Mensualite();
        boolean recurrent = (nbMensualite <= 0);
        m.setIsRecurrent(recurrent ? "Y" : "N");

        // -----------------------------------------------------------
        // 3 & 4. Mensualités + Solde (mode non récurrent uniquement)
        // -----------------------------------------------------------
        if (recurrent) {
            log.fine("MouvementPaie [" + m.getName() + "] mode récurrent — pas de mensualités");
            return null;
        }

        BigDecimal montantTotal = m.getMontant_Total();
        if (montantTotal == null || montantTotal.compareTo(BigDecimal.ZERO) <= 0)
            return null;

        BigDecimal acompte = m.getAcompte();
        BigDecimal base = montantTotal;
        if (acompte != null && acompte.compareTo(BigDecimal.ZERO) > 0)
            base = base.subtract(acompte);

        BigDecimal mensualite = base.divideToIntegralValue(BigDecimal.valueOf(nbMensualite));
        BigDecimal derniere = (nbMensualite == 1)
                ? mensualite
                : base.subtract(mensualite.multiply(BigDecimal.valueOf(nbMensualite - 1)));

        m.setMontant_Mensualite(mensualite);
        m.setMontant_Derniere_Mensualite(derniere);

        // Initialiser Solde seulement si pas encore posé
        BigDecimal solde = m.getSolde();
        if (solde == null || solde.compareTo(BigDecimal.ZERO) == 0)
            m.setSolde(base);

        log.fine("MouvementPaie [" + m.getName()
                + "] mensualite=" + mensualite
                + " derniere=" + derniere
                + " solde=" + m.getSolde());

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
