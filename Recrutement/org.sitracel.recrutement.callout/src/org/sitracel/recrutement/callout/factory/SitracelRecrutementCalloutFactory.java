package org.sitracel.recrutement.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.recrutement.callout.candidatevaluation.CalloutScoreCompetence;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;

public class SitracelRecrutementCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
				
		
		if(tableName.equalsIgnoreCase(MHRCandidatEvaluation.Table_Name) && columnName.equalsIgnoreCase(MHRCandidatEvaluation.COLUMNNAME_Score)) {
			list.add(new CalloutScoreCompetence());
		}

		return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
