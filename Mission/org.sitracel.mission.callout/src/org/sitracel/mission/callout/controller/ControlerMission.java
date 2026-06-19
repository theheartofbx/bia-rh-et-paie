package org.sitracel.mission.callout;

import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionFrais;
import org.sitracel.model.MCBPartner;

public class ControlerMission {

    public static void recalculerEtatMission(int hrMissionId, String trxName) {
    	
        MHRMission mission = new MHRMission(Env.getCtx(), hrMissionId, trxName);
        try {
        	// 1. Rejet prioritaire
	        if (SqlControlerMission.existeRejetObligatoire(hrMissionId, trxName)) {
	            mission.setIsRejetee(true);
	            mission.setIsValidee(false);
	            mission.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
	            mission.setDate_Validation(null);
	            mission.saveEx();
	            return;
	        }

	        // 2. Validation complète
	        if (SqlControlerMission.isMissionValide(hrMissionId, trxName)) {
	            mission.setIsValidee(true);
	            mission.setIsRejetee(false);
	            mission.setDate_Validation(SqlControlerMission.getDateValidationFinale(hrMissionId, trxName));
	            mission.setDate_Rejet(null);
	            mission.saveEx();
	            return;
	        }

	        // 3. En attente
	        mission.setIsValidee(false);
	        mission.setIsRejetee(false);
	        mission.setDate_Validation(null);
	        mission.setDate_Rejet(null);
	        mission.saveEx();
        	
        }catch (Exception e) {
            throw new AdempiereException(
                    "Erreur lors du recalcul de l'état de la mission", e
                );
        }
    }
    
    public static void majValidationRejet(MHRMissionFrais frais) {

        boolean validationChangee =
            frais.is_ValueChanged(MHRMissionFrais.COLUMNNAME_IsValidee)
            && frais.isValidee();

        boolean rejetChange =
            frais.is_ValueChanged(MHRMissionFrais.COLUMNNAME_IsRejetee)
            && frais.isRejetee();

        if (!validationChangee && !rejetChange) {
            return; // rien à faire
        }

        // ❌ Sécurité métier : jamais les deux à Y
        if (frais.isValidee() && frais.isRejetee()) {
            throw new AdempiereException(
                "Un frais ne peut pas être validé et rejeté en même temps."
            );
        }

        BeanIdentifiant identifiant =
            MCBPartner.getIdentifiant(
                Env.getAD_User_ID(Env.getCtx()),
                frais.get_TrxName()
            );

        if (identifiant == null) {
            throw new AdempiereException(
                "Impossible de déterminer l'identité de l'utilisateur."
            );
        }

        // ✔ Injection des informations
        frais.set_ValueOfColumn(
            MHRMissionFrais.COLUMNNAME_Valide_Rejete_Par_Nom_ID,
            identifiant.getNumEmploye()
        );

        frais.set_ValueOfColumn(
            MHRMissionFrais.COLUMNNAME_Valide_Rejete_Par_Poste_ID,
            identifiant.getNumeroPoste()
        );

        frais.set_ValueOfColumn(
            MHRMissionFrais.COLUMNNAME_Valide_Rejete_Par_Matricule,
            identifiant.getMatriculeEmploye()
        );
    }

}
