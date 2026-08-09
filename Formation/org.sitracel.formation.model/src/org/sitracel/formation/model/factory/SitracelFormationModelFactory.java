package org.sitracel.formation.model.factory;

import java.sql.ResultSet;
import java.util.Properties;
import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.formation.model.*;

public class SitracelFormationModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equals("HR_FormationCatalogue")) return MHRFormationCatalogue.class;
        if (tableName.equals("HR_FormationModule")) return MHRFormationModule.class;
        if (tableName.equals("HR_FormationProgramme")) return MHRFormationProgramme.class;
        if (tableName.equals("HR_FormationSession")) return MHRFormationSession.class;
        if (tableName.equals("HR_FormationPlanning")) return MHRFormationPlanning.class;
        if (tableName.equals("HR_FormationPlanningLigne")) return MHRFormationPlanningLigne.class;
        if (tableName.equals("HR_FormationParticipant")) return MHRFormationParticipant.class;
        if (tableName.equals("HR_FormationDemande")) return MHRFormationDemande.class;
        if (tableName.equals("HR_FormationType")) return MHRFormationType.class;
        if (tableName.equals("HR_FormationDomaine")) return MHRFormationDomaine.class;
        if (tableName.equals("HR_FormationSessionStatut")) return MHRFormationSessionStatut.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        Properties ctx = Env.getCtx();
        if (tableName.equals("HR_FormationCatalogue")) return new MHRFormationCatalogue(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationModule")) return new MHRFormationModule(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationProgramme")) return new MHRFormationProgramme(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationSession")) return new MHRFormationSession(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationPlanning")) return new MHRFormationPlanning(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationPlanningLigne")) return new MHRFormationPlanningLigne(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationParticipant")) return new MHRFormationParticipant(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationDemande")) return new MHRFormationDemande(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationType")) return new MHRFormationType(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationDomaine")) return new MHRFormationDomaine(ctx, Record_ID, trxName);
        if (tableName.equals("HR_FormationSessionStatut")) return new MHRFormationSessionStatut(ctx, Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        Properties ctx = Env.getCtx();
        if (tableName.equals("HR_FormationCatalogue")) return new MHRFormationCatalogue(ctx, rs, trxName);
        if (tableName.equals("HR_FormationModule")) return new MHRFormationModule(ctx, rs, trxName);
        if (tableName.equals("HR_FormationProgramme")) return new MHRFormationProgramme(ctx, rs, trxName);
        if (tableName.equals("HR_FormationSession")) return new MHRFormationSession(ctx, rs, trxName);
        if (tableName.equals("HR_FormationPlanning")) return new MHRFormationPlanning(ctx, rs, trxName);
        if (tableName.equals("HR_FormationPlanningLigne")) return new MHRFormationPlanningLigne(ctx, rs, trxName);
        if (tableName.equals("HR_FormationParticipant")) return new MHRFormationParticipant(ctx, rs, trxName);
        if (tableName.equals("HR_FormationDemande")) return new MHRFormationDemande(ctx, rs, trxName);
        if (tableName.equals("HR_FormationType")) return new MHRFormationType(ctx, rs, trxName);
        if (tableName.equals("HR_FormationDomaine")) return new MHRFormationDomaine(ctx, rs, trxName);
        if (tableName.equals("HR_FormationSessionStatut")) return new MHRFormationSessionStatut(ctx, rs, trxName);
        return null;
    }
}
