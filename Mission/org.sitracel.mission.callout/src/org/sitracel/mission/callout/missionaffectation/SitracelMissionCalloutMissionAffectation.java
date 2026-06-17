package org.sitracel.mission.callout.missionaffectation;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MTable;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionAffectation;

public class SitracelMissionCalloutMissionAffectation implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub

        Timestamp dateDebut = (Timestamp) mTab.getValue(MHRMissionAffectation.COLUMNNAME_Date_Debut);
        Timestamp dateFin   = (Timestamp) mTab.getValue(MHRMissionAffectation.COLUMNNAME_Date_Fin);
        Integer missionId   = (Integer) mTab.getValue(MHRMissionAffectation.COLUMNNAME_HR_Mission_ID);

        // Pas assez d'informations → OK
        if (dateDebut == null || dateFin == null) {
            return "";
        }

        // 🔹 Règle 1 : cohérence interne
        if (!dateDebut.before(dateFin)) {
        	mTab.setValue(mField.getColumnName(), null);
            return "La date de début doit être strictement inférieure à la date de fin.";
        }

        // 🔹 Règle 2 : inclusion dans la mission
        if (missionId != null && missionId > 0) {

            Timestamp missionDebut =
                (Timestamp) MTable.get(ctx, MHRMission.Table_Name)
                    .getPO(missionId, null)
                    .get_Value("Date_Debut");

            Timestamp missionFin =
                (Timestamp) MTable.get(ctx, MHRMission.Table_Name)
                    .getPO(missionId, null)
                    .get_Value("Date_Fin");

            if (missionDebut != null && dateDebut.before(missionDebut)) {
            	mTab.setValue("Date_Debut", null);
                return "La date de début de l'affectation doit être comprise dans la période de la mission.";
            }

            if (missionFin != null && dateFin.after(missionFin)) {
            	mTab.setValue("Date_Fin", null);
                return "La date de fin de l'affectation doit être comprise dans la période de la mission.";
            }
        }
		return null;
	}

}
