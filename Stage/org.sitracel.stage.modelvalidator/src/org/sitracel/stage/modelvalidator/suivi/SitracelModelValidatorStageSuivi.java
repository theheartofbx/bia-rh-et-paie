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
        if (type == TYPE_BEFORE_DELETE) {
            return beforeDelete(po);
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

        // Garde-fou 2 : Stage rejeté → lecture seule
        String isRejetee = DB.getSQLValueString(po.get_TrxName(),
            "SELECT IsRejetee FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if ("Y".equals(isRejetee)) {
            return "Le stage est rejeté, aucune modification n'est possible.";
        }

        // Garde-fou 3 : Dates du suivi dans la période du stage
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

        // Auto-flag IsOk : Date_Debut ET Date_Fin renseignées
        if (suiviDebut != null && suiviFin != null) {
            po.set_ValueNoCheck("IsOk", "Y");
        } else {
            po.set_ValueNoCheck("IsOk", "N");
        }

        // Auto-flag IsEvalue : Score renseigné et > 0
        Object scoreObj = po.get_Value("Score");
        boolean hasScore = scoreObj != null && ((BigDecimal) scoreObj).compareTo(BigDecimal.ZERO) > 0;

        if (hasScore) {
            // Vérifier que l'objectif est défini avant d'évaluer
            if (suiviDebut == null || suiviFin == null) {
                return "Impossible d'évaluer un objectif dont les dates ne sont pas renseignées.";
            }
            po.set_ValueNoCheck("IsEvalue", "Y");
            if (po.is_ValueChanged("Score") || po.get_Value("Date_Evaluation") == null) {
                po.set_ValueNoCheck("Date_Evaluation", new Timestamp(System.currentTimeMillis()));
            }
        } else {
            po.set_ValueNoCheck("IsEvalue", "N");
            po.set_ValueNoCheck("Date_Evaluation", null);
        }

        // Garde-fou : Score <= ScoreMax et >= 0
        if (scoreObj != null) {
            BigDecimal score = (BigDecimal) scoreObj;
            if (score.compareTo(BigDecimal.ZERO) < 0) {
                return "Le score ne peut pas être négatif.";
            }
            Object scoreMaxObj = po.get_Value("ScoreMax");
            if (scoreMaxObj != null) {
                BigDecimal scoreMax = (BigDecimal) scoreMaxObj;
                if (scoreMax.compareTo(BigDecimal.ZERO) > 0 && score.compareTo(scoreMax) > 0) {
                    return "Le score (" + score + ") ne peut pas dépasser le score maximum (" + scoreMax + ").";
                }
            }
        }

        return null;
    }

    private String beforeDelete(PO po) {
        int stageId = (Integer) po.get_Value("HR_Stage_ID");

        String isValidee = DB.getSQLValueString(po.get_TrxName(),
            "SELECT IsValidee FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if ("Y".equals(isValidee)) {
            return "Impossible de supprimer un objectif d'un stage validé.";
        }

        String isRejetee = DB.getSQLValueString(po.get_TrxName(),
            "SELECT IsRejetee FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if ("Y".equals(isRejetee)) {
            return "Impossible de supprimer un objectif d'un stage rejeté.";
        }

        return null;
    }

    private void updateIndicateurs(PO po, boolean isDelete) {
        int stageId;
        if (isDelete) {
            Object old = po.get_ValueOld("HR_Stage_ID");
            stageId = old != null ? (Integer) old : 0;
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
