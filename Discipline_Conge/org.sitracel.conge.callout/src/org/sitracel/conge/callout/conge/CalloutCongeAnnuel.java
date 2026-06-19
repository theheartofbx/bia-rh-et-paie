package org.sitracel.conge.callout.conge;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.bean.BeanInfoCongeDepartement;
import org.sitracel.conge.callout.conge.service.CongeCalloutService;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.time.HRCalendrierService;

/**
 * Callout — validation des dates de congé annuel.
 *
 * Vérifie que la période choisie est :
 *   - dans la fenêtre autorisée (1 semaine min, 1 an max)
 *   - sans chevauchement avec un autre congé, suspension ou absence
 *   - dans les droits restants de l'employé
 *
 * Calcule également la disponibilité du département.
 */
public class CalloutCongeAnnuel implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        // Fenêtre autorisée : 1 semaine à partir d'aujourd'hui, jusqu'à fin d'année suivante
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Timestamp debutAutorise = new Timestamp(cal.getTime().getTime());

        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.add(Calendar.YEAR, 1);
        Timestamp finAutorisee = new Timestamp(cal.getTime().getTime());

        Timestamp dateDebut = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee);
        Timestamp dateFin   = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee);
        Integer idConge     = (Integer)   mTab.getValue(MHRHoliday.COLUMNNAME_Emission_Conge_ID);
        Integer bpartnerId  = (Integer)   mTab.getValue(MHRHoliday.COLUMNNAME_C_BPartner_ID);

        String  message = "";
        boolean dateOk  = true;

        if (idConge == null) return null;

        MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), idConge, null);
        if (typeConge == null) return null;

        if (dateDebut == null || dateFin == null) return null;

        if (!dateDebut.before(dateFin)) {
            message = "La date de début ne peut pas être après la date de fin !";
            dateOk = false;
        } else {
            // Initialiser les dates effectives
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, dateDebut);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, dateFin);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, dateDebut);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, dateFin);

            if (typeConge.isCongeAnnuel()) {

                // Vérifier la fenêtre temporelle
                if (!dateDebut.after(debutAutorise) || !dateDebut.before(finAutorisee)
                        || !dateFin.before(finAutorisee) || !dateFin.after(debutAutorise)) {
                    message = "Vous devez prendre vos congés au moins 1 semaine à compter"
                        + " d'aujourd'hui et au plus tard dans 1 an !";
                    dateOk = false;

                // Vérifier chevauchement congé
                } else if (GeneralSqlController.chevaucheAnyCongeNonRejete(
                        bpartnerId, dateDebut, dateFin, null)) {
                    message = "La période choisie coïncide avec une autre période de congé.";
                    dateOk = false;

                // Vérifier chevauchement suspension
                } else if (GeneralSqlController.chevaucheSuspensionNonRejete(
                        bpartnerId, dateDebut, dateFin, null)) {
                    message = "Une période de suspension est déjà émise durant cette période.";
                    dateOk = false;

                // Vérifier chevauchement absence
                } else if (GeneralSqlController.isPeriodeAbsence(
                        bpartnerId, dateDebut, dateFin, null)) {
                    message = "Une absence a déjà été enregistrée durant la période choisie.";
                    dateOk = false;

                } else {
                    // Calculer le nombre de jours
                    Integer nbJour = HRCalendrierService.getNombreJourTravaille(
                        dateDebut, dateFin);

                    if (nbJour != null) {
                        // Vérifier les droits restants
                        Integer nbJourDejaUtilise =
                            (Integer) mTab.getValue(MHRHoliday.COLUMNNAME_Jours_Conge_Deja_Utilise);
                        Integer nbJourTotal =
                            (Integer) mTab.getValue(MHRHoliday.COLUMNNAME_Jours_Conge_Total);

                        if (nbJourDejaUtilise != null && nbJourTotal != null) {
                            if ((nbJourTotal - nbJourDejaUtilise - nbJour) < 0) {
                                message = "La période choisie (" + nbJour + " jour(s)) dépasse"
                                    + " le nombre de jours restants ("
                                    + (nbJourTotal - nbJourDejaUtilise) + " jours) !";
                                dateOk = false;
                            }
                        }

                        // Disponibilité département
                        BeanInfoCongeDepartement beanDept =
                            CongeCalloutService.getPeriodeCongeCritique(
                                bpartnerId, dateDebut, dateFin);

                        mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_Correspondant, nbJour);
                        mTab.setValue(MHRHoliday.COLUMNNAME_Nombre_Employe_Departement,
                            beanDept.getNombreEmployeDepartement());
                        mTab.setValue(MHRHoliday.COLUMNNAME_Nombre_Employe_Departement_Hol,
                            beanDept.getNombreEmployeDepartementConge());
                        mTab.setValue(MHRHoliday.COLUMNNAME_Jour_Conge_Max_Depart,
                            beanDept.getJourCritique());
                        mTab.setValue(MHRHoliday.COLUMNNAME_Disponibilite_Departement,
                            new BigDecimal(beanDept.getPourcentageEmployeConge()));
                    }

                    if (dateOk) {
                        mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "N");
                        mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "");
                    }
                }
            }
        }

        if (!dateOk) {
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
            mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
            mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, message);
        }

        return null;
    }
}
