package org.sitracel.paie.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.bean.BeanConge;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.paie.model.MHRAttribute;

public class CalloutMajPeriodeReference implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		CLogger log = CLogger.getCLogger(PO.class);
		Integer holidayID = (Integer)mTab.getValue(MHRAttribute.COLUMNNAME_HR_Holiday_ID);
		log.warning("\nHolidayID : "+holidayID);
		if(holidayID!=null) {
			MHRHoliday holiday = new MHRHoliday(Env.getCtx(), holidayID, null);
			if(holiday!=null) {
				BeanConge beanConge = BeanFactory.getBeanConge();
				 beanConge = GeneralSqlController.getDateDernierConge(holiday.getC_BPartner_ID(),holiday.getDate_Debut_Souhaitee(),"Annuel",beanConge, null);
				if(beanConge!=null) {
					log.warning("\nDate Debut : "+beanConge.getDateDebutDernierConge());
					log.warning("\nDate FIn : "+beanConge.getDateFindernierConge());
					log.warning("\nDate EMBAUCHE : "+beanConge.getDateEmbauche());
					if(beanConge.getDateDebutDernierConge()!=null) {
						mTab.setValue(MHRAttribute.COLUMNNAME_Periode_Reference_Debut, beanConge.getDateDebutDernierConge());
					}
					else {
						mTab.setValue(MHRAttribute.COLUMNNAME_Periode_Reference_Debut, beanConge.getDateEmbauche());
					}
				}
			}
		}
		return null;
	}

}
