package org.sitracel.stage.modelvalidator.suivi;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.stage.model.I_HR_StageSuivi;

public class SitracelModelValidatorStageSuivi implements ModelValidator {

    private int m_AD_Client_ID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_StageSuivi.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return m_AD_Client_ID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            return beforeSave(po, type == TYPE_BEFORE_NEW);
        }
        if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE || type == TYPE_AFTER_DELETE) {
            updateIndicateurs(po, type == TYPE_AFTER_DELETE);
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }

    private String beforeSave(PO po, boolean isNew) {
        int stageId = (Integer) po.get_Value("HR_Stage_ID");

        // Garde-fou 1 : Stage validé → lecture seule
        String isValidee = DB.getSQLValueString(po.get_TrxName(),
            "SELECT IsValidee FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if ("Y".equals(isValidee)) {
            return "Le stage est validé, aucune modification n'est possible.";
        }

        // Garde-fou 2 : Dates du suivi dans la période du stage
        Timestamp suiviDebut = (Timestamp) po.get_Value("Date_Debut");
        Timestamp suiviFin = (Timestamp) po.get_Value("Date_Fin");
        if (suiviDebut != null || suiviFin != null) {
            Timestamp stageDebut = DB.getSQLValueTS(po.get_TrxName(),
                "SELECT Date_Debut FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
            Timestamp stageFin = DB.getSQLValueTS(po.get_TrxName(),
                "SELECT Date_Fin FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);

            if (stageDebut != null && suiviDebut != null && suiviDebut.before(stageDebut)) {
                return "La date de début de l'objectif ne peut pas être avant le début du stage.";
            }
            if (stageFin != null && suiviFin != null && suiviFin.after(stageFin)) {
                return "La date de fin de l'objectif ne peut pas dépasser la fin du stage.";
            }
        }
        if (suiviDebut != null && suiviFin != null && suiviFin.before(suiviDebut)) {
            return "La date de fin ne peut pas être antérieure à la date de début.";
        }

        // Garde-fou 3 : Score <= ScoreMax
        Object scoreObj = po.get_Value("Score");
        Object scoreMaxObj = po.get_Value("ScoreMax");
        if (scoreObj != null && scoreMaxObj != null) {
            BigDecimal score = (BigDecimal) scoreObj;
            BigDecimal scoreMax = (BigDecimal) scoreMaxObj;
            if (score.compareTo(BigDecimal.ZERO) < 0) {
                return "Le score ne peut pas être négatif.";
            }
            if (score.compareTo(scoreMax) > 0) {
                return "Le score (" + score + ") ne peut pas dépasser le score maximum (" + scoreMax + ").";
            }
        }

        return null;
    }

    private void updateIndicateurs(PO po, boolean isDelete) {
        int stageId;
        if (isDelete) {
            stageId = (Integer) po.get_ValueOld("HR_Stage_ID");
        } else {
            stageId = (Integer) po.get_Value("HR_Stage_ID");
        }
        if (stageId <= 0) return;

        DB.executeUpdateEx(
            "UPDATE HR_Stage SET " +
            "NombreObjectifs = (SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = " + stageId + " AND IsActive = 'Y'), " +
            "NombreDefinis = (SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = " + stageId + " AND IsActive = 'Y' AND IsOk = 'Y'), " +
            "NombreEvalues = (SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = " + stageId + " AND IsActive = 'Y' AND IsEvalue = 'Y'), " +
            "PourcentageAvancement = (SELECT CASE WHEN COUNT(*) = 0 THEN 0 ELSE " +
            "ROUND(COUNT(CASE WHEN IsEvalue = 'Y' THEN 1 END)::NUMERIC / COUNT(*)::NUMERIC * 100, 0) END " +
            "FROM HR_StageSuivi WHERE HR_Stage_ID = " + stageId + " AND IsActive = 'Y') " +
            "WHERE HR_Stage_ID = " + stageId,
            po.get_TrxName());
    }
}
