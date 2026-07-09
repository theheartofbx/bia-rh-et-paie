package org.sitracel.discipline.callout.sanction;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.controller.GeneralController;
import org.sitracel.discipline.callout.sanction.service.SuspensionValidationService;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;

/**
 * Callout — se déclenche quand l'utilisateur choisit la durée de la
 * suspension. Calcule la date de fin et valide la période via
 * SuspensionValidationService (logique partagée avec
 * CalloutDateDebutApplication).
 */
public class CalloutDureeSuspension implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        Timestamp dateDebutApplication = (Timestamp) mTab.getValue(MHRPunishment.COLUMNNAME_Date_Debut_Application);
        if (dateDebutApplication == null) {
            return null;
        }

        Integer dureeSuspensionID = (Integer) mTab.getValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID);
        if (dureeSuspensionID == null) {
            return null;
        }

        MHRDureeSanction dureeSuspension = new MHRDureeSanction(Env.getCtx(), dureeSuspensionID, null);
        Timestamp dateFinApplication = GeneralController.ajouterNombreJour(
            dateDebutApplication, dureeSuspension.getNombre_De_Jour());

        Integer bpartnerID = (Integer) mTab.getValue(MHRPunishment.COLUMNNAME_C_BPartner_ID);

        SuspensionValidationService.validerPeriodeSuspension(
            mTab, bpartnerID, dateDebutApplication, dateFinApplication);

        return null;
    }
}
