package org.sitracel.conge.callout.conge;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.bean.BeanAbsence;
import org.sitracel.bean.BeanInfoCongeDepartement;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.conge.callout.conge.controller.CalloutControllerConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralController;

public class CalloutCongeAnnuel implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_YEAR, 7);
		Timestamp debutAnnee = new Timestamp(cal.getTime().getTime());
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.add(Calendar.YEAR, 1);
		Timestamp finAnnee = new Timestamp(cal.getTime().getTime());
		Timestamp dateDebut = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee);
		Timestamp dateFin = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee);
		
		Integer idConge = (Integer)mTab.getValue(MHRHoliday.COLUMNNAME_Emission_Conge_ID);
		BeanPeriode beanPeriode = null;
		BeanAbsence beanAbsence =null;
		
		if(idConge!=null) {
			MHRTypeConge typeConge = typeConge = new MHRTypeConge(Env.getCtx(), idConge, null);
			if(typeConge!=null) {
				if(dateDebut!=null && dateFin!=null) {
					if(dateDebut.before(dateFin)) {
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, dateDebut);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, dateFin);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, dateDebut);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, dateFin);
						if(typeConge.isCongeAnnuel()) {
							if(dateDebut.after(debutAnnee) && dateDebut.before(finAnnee) && dateFin.before(finAnnee) && dateFin.after(debutAnnee)) {
								beanPeriode = CalloutControllerConge.isDejaPris((Integer) mTab.getValue(MHRHoliday.COLUMNNAME_C_BPartner_ID), dateDebut, dateFin);
								if(beanPeriode==null) {
									beanPeriode = GeneralController.isPeriodeSuspensionIn((Integer)mTab.getValue(MHRHoliday.COLUMNNAME_C_BPartner_ID), dateDebut, dateFin);
									if(beanPeriode==null) {
										beanAbsence = GeneralController.isAbsenceIn((Integer)mTab.getValue(MHRHoliday.COLUMNNAME_C_BPartner_ID), dateDebut, dateFin);
										if(beanAbsence==null) {
											Integer nbJour = CalloutControllerConge.getNombreJourTravaille(dateDebut, dateFin);
											if(nbJour!=null) {
												BeanInfoCongeDepartement beanInfoCongeDepartement =CalloutControllerConge.getPeriodeCongeCritique((Integer) mTab.getValue(MHRHoliday.COLUMNNAME_C_BPartner_ID), 
														dateDebut, dateFin);
												Integer nbJourDejaUtilise = (Integer) mTab.getValue(MHRHoliday.COLUMNNAME_Jours_Conge_Deja_Utilise);
												Integer nbJourTotal = (Integer) mTab.getValue(MHRHoliday.COLUMNNAME_Jours_Conge_Total);
												if(nbJourDejaUtilise!=null && nbJourTotal!=null) {
													if((nbJourTotal-nbJourDejaUtilise-nbJour)<0) {
														mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
														mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
														mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
														mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
														mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
														mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
														mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
														mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "la période choisie("+nbJour+" jour(s)) dépasse le nombre de jours de congé qu'il vous reste ("+(nbJourTotal-nbJourDejaUtilise)+" jours) !");
														return null;
													}
												}
												mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_Correspondant, nbJour);
												mTab.setValue(MHRHoliday.COLUMNNAME_Nombre_Employe_Departement, beanInfoCongeDepartement.getNombreEmployeDepartement());
												mTab.setValue(MHRHoliday.COLUMNNAME_Nombre_Employe_Departement_Hol, beanInfoCongeDepartement.getNombreEmployeDepartementConge());
												mTab.setValue(MHRHoliday.COLUMNNAME_Jour_Conge_Max_Depart, beanInfoCongeDepartement.getJourCritique());
												mTab.setValue(MHRHoliday.COLUMNNAME_Disponibilite_Departement, 
														new BigDecimal(beanInfoCongeDepartement.getPourcentageEmployeConge()));
											}
											mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "N");
											mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "");
										}
										else {
											mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
											mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
											mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
											mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
											mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
											mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
											mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
											mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "une absence au "+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(beanAbsence.getDateAbsence())
											+"a été enregistrée durant la période que vous avez choisi ");
											return null;
										}
									}
									else {
										mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
										mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
										mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
										mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
										mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
										mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
										mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
										mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "une période de suspension allant du "+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(beanPeriode.getDateDebutConge())
										+" au "+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(beanPeriode.getDateFinConge())+" a été émise durant la période choisie");
										return null;
									}
								}
								else {
									mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
									mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
									mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
									mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
									mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
									mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
									mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
									mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "La période que vous avez choisi coincide avec une autre période de congé allant du"
									+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(beanPeriode.getDateDebutConge())+" au "+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(beanPeriode.getDateFinConge())+"!");
									return null;
								}
							}
							else {
								mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
								mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
								mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
								mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
								mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
								mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
								mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
								mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "Vous devez prendre des congés 1 semaine au moins à compter d'aujourd'hui et un an au plus !");
								return null;
							}
						}
					}
					else {
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
						mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
						mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "la date de début ne peut pas être après la date de fin !");
						return null;
					}
				}
			}
		}
		
		return null;
	}

}
