package org.sitracel.mission.callout.mission;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.mission.model.MHRMission;

public class SitracelMissionCalloutMission implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Timestamp dateDebut = (Timestamp) mTab.getValue(MHRMission.COLUMNNAME_Date_Debut);
        Timestamp dateFin   = (Timestamp) mTab.getValue(MHRMission.COLUMNNAME_Date_Fin);

        // Si une des dates n'est pas encore renseignée → OK
        if (dateDebut == null || dateFin == null) {
            return "";
        }

        // Règle métier
        if (!dateDebut.before(dateFin)) {
        	mTab.setValue(mField.getColumnName(), null);
            return "La date de début doit être strictement inférieure à la date de fin.";
        }
		return null;
	}

}
