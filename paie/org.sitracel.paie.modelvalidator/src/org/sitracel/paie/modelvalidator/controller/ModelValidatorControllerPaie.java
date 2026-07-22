package org.sitracel.paie.modelvalidator.controller;

import org.compiere.model.ModelValidator;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.paie.model.I_HR_Historique_Paie;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;

public class ModelValidatorControllerPaie {

    public static void updateHistorique(MHRCalculPaie calculPaie, int type) {
        if (calculPaie != null) {
            if (type == ModelValidator.TYPE_AFTER_NEW || type == ModelValidator.TYPE_AFTER_CHANGE) {
                MHRHistoriquePaie historiquePaie = GeneralSqlController.getHistoriquePaie(
                        calculPaie.getC_BPartner_ID(),
                        calculPaie.getHR_Element_Base_Paie_ID(),
                        calculPaie.getHR_Periode_Salariale_ID(), null);
                if (historiquePaie == null) {
                    historiquePaie = new MHRHistoriquePaie(Env.getCtx(), 0, null);
                    historiquePaie.setC_BPartner_ID(calculPaie.getC_BPartner_ID());
                    historiquePaie.setHR_Periode_Salariale_ID(calculPaie.getHR_Periode_Salariale_ID());
                    historiquePaie.setHR_Element_Base_Paie_ID(calculPaie.getHR_Element_Base_Paie_ID());
                    historiquePaie.setHR_Historique_Paie_ID(
                            DB.getNextID(Env.getCtx(), I_HR_Historique_Paie.Table_Name, null));
                }
                MHRPeriodeSalariale periodeSalariale = new MHRPeriodeSalariale(
                        Env.getCtx(), calculPaie.getHR_Periode_Salariale_ID(), null);
                if (periodeSalariale != null) {
                    historiquePaie.setDate_Debut(periodeSalariale.getDate_Debut_Defaut());
                }
                historiquePaie.setMontant(calculPaie.getMontant());
                historiquePaie.save();
            }
        }
    }
}
