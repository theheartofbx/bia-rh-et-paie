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
        if ("HR_FormationCatalogue".equals(tableName)) return MHRFormationCatalogue.class;
        if ("HR_FormationModule".equals(tableName)) return MHRFormationModule.class;
        if ("HR_FormationProgramme".equals(tableName)) return MHRFormationProgramme.class;
        if ("HR_FormationSession".equals(tableName)) return MHRFormationSession.class;
        if ("HR_FormationPlanning".equals(tableName)) return MHRFormationPlanning.class;
        if ("HR_FormationPlanningLigne".equals(tableName)) return MHRFormationPlanningLigne.class;
        if ("HR_FormationParticipant".equals(tableName)) return MHRFormationParticipant.class;
        if ("HR_FormationDemande".equals(tableName)) return MHRFormationDemande.class;
        if ("HR_FormationType".equals(tableName)) return MHRFormationType.class;
        if ("HR_FormationDomaine".equals(tableName)) return MHRFormationDomaine.class;
        if ("HR_FormationSessionStatut".equals(tableName)) return MHRFormationSessionStatut.class;
        if ("HR_FormationStatutP".equals(tableName)) return MHRFormationStatutParti.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        Properties ctx = Env.getCtx();
        if ("HR_FormationCatalogue".equals(tableName)) return new MHRFormationCatalogue(ctx, Record_ID, trxName);
        if ("HR_FormationModule".equals(tableName)) return new MHRFormationModule(ctx, Record_ID, trxName);
        if ("HR_FormationProgramme".equals(tableName)) return new MHRFormationProgramme(ctx, Record_ID, trxName);
        if ("HR_FormationSession".equals(tableName)) return new MHRFormationSession(ctx, Record_ID, trxName);
        if ("HR_FormationPlanning".equals(tableName)) return new MHRFormationPlanning(ctx, Record_ID, trxName);
        if ("HR_FormationPlanningLigne".equals(tableName)) return new MHRFormationPlanningLigne(ctx, Record_ID, trxName);
        if ("HR_FormationParticipant".equals(tableName)) return new MHRFormationParticipant(ctx, Record_ID, trxName);
        if ("HR_FormationDemande".equals(tableName)) return new MHRFormationDemande(ctx, Record_ID, trxName);
        if ("HR_FormationType".equals(tableName)) return new MHRFormationType(ctx, Record_ID, trxName);
        if ("HR_FormationDomaine".equals(tableName)) return new MHRFormationDomaine(ctx, Record_ID, trxName);
        if ("HR_FormationSessionStatut".equals(tableName)) return new MHRFormationSessionStatut(ctx, Record_ID, trxName);
        if ("HR_FormationStatutP".equals(tableName)) return new MHRFormationStatutParti(ctx, Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        Properties ctx = Env.getCtx();
        if ("HR_FormationCatalogue".equals(tableName)) return new MHRFormationCatalogue(ctx, rs, trxName);
        if ("HR_FormationModule".equals(tableName)) return new MHRFormationModule(ctx, rs, trxName);
        if ("HR_FormationProgramme".equals(tableName)) return new MHRFormationProgramme(ctx, rs, trxName);
        if ("HR_FormationSession".equals(tableName)) return new MHRFormationSession(ctx, rs, trxName);
        if ("HR_FormationPlanning".equals(tableName)) return new MHRFormationPlanning(ctx, rs, trxName);
        if ("HR_FormationPlanningLigne".equals(tableName)) return new MHRFormationPlanningLigne(ctx, rs, trxName);
        if ("HR_FormationParticipant".equals(tableName)) return new MHRFormationParticipant(ctx, rs, trxName);
        if ("HR_FormationDemande".equals(tableName)) return new MHRFormationDemande(ctx, rs, trxName);
        if ("HR_FormationType".equals(tableName)) return new MHRFormationType(ctx, rs, trxName);
        if ("HR_FormationDomaine".equals(tableName)) return new MHRFormationDomaine(ctx, rs, trxName);
        if ("HR_FormationSessionStatut".equals(tableName)) return new MHRFormationSessionStatut(ctx, rs, trxName);
        if ("HR_FormationStatutP".equals(tableName)) return new MHRFormationStatutParti(ctx, rs, trxName);
        return null;
    }
}
