package org.sitracel.discipline.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.discipline.model.I_HR_Delai_Reponse;
import org.sitracel.discipline.model.I_HR_Demande_Explication;
import org.sitracel.discipline.model.I_HR_Dossier_Disciplinaire;
import org.sitracel.discipline.model.I_HR_Duree_Sanction;
import org.sitracel.discipline.model.I_HR_Punishment;
import org.sitracel.discipline.model.I_HR_Sanction_Autorisation;
import org.sitracel.discipline.model.I_HR_TypeSanction;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;

public class SitracelDisciplineModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name))
            return MHRDelaiReponse.class;
        if (tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name))
            return MHRDemandeExplication.class;
        if (tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name))
            return MHRDossierDisciplinaire.class;
        if (tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name))
            return MHRDureeSanction.class;
        if (tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name))
            return MHRPunishment.class;
        if (tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name))
            return MHRSanctionAutorisation.class;
        if (tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name))
            return MHRTypeSanction.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name))
            return new MHRDelaiReponse(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name))
            return new MHRDemandeExplication(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name))
            return new MHRDossierDisciplinaire(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name))
            return new MHRDureeSanction(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name))
            return new MHRPunishment(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name))
            return new MHRSanctionAutorisation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name))
            return new MHRTypeSanction(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name))
            return new MHRDelaiReponse(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name))
            return new MHRDemandeExplication(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name))
            return new MHRDossierDisciplinaire(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name))
            return new MHRDureeSanction(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name))
            return new MHRPunishment(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name))
            return new MHRSanctionAutorisation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name))
            return new MHRTypeSanction(Env.getCtx(), rs, trxName);
        return null;
    }
}
