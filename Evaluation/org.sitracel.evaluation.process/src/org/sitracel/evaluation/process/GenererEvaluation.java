package org.sitracel.evaluation.process;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.employe.HRContratService;
import org.sitracel.evaluation.model.engine.EvalFormulaEngine;

/**
 * Processus : Générer les lignes et résultats d'une évaluation
 *
 * Habilitation : RH uniquement
 * 1. Copie les lignes de HR_EvalGrilleLigne → HR_EvalLigne
 * 2. Copie les formules de HR_EvalGrilleFormule → HR_EvalResultat
 * 3. Calcule ScoreMin et ScoreMax de chaque résultat
 * 4. Met IsGeneree = Y
 */
public class GenererEvaluation extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();

        // --- Habilitation : RH uniquement ---
        int adUserId = Env.getAD_User_ID(getCtx());
        if (!HRContratService.isUserRH(adUserId, get_TrxName())) {
            return "Seul le service RH peut générer une évaluation.";
        }

        // --- Vérifier que l'évaluation n'est pas déjà générée ---
        String isGeneree = DB.getSQLValueString(get_TrxName(),
            "SELECT IsGeneree FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isGeneree)) {
            return "L'évaluation est déjà générée.";
        }

        // --- Récupérer la grille ---
        int grilleId = DB.getSQLValueEx(get_TrxName(),
            "SELECT HR_EvalGrille_ID FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (grilleId <= 0) {
            return "Aucune grille sélectionnée.";
        }

        // --- Vérifier que la grille est validée ---
        String isGrilleValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_EvalGrille WHERE HR_EvalGrille_ID = ?", grilleId);
        if (!"Y".equals(isGrilleValidee)) {
            return "La grille n'est pas validée. Validez-la d'abord.";
        }

        int clientId = getAD_Client_ID();
        int orgId = Env.getAD_Org_ID(getCtx());
        int userId = Env.getAD_User_ID(getCtx());

        // --- Copier les lignes de la grille → HR_EvalLigne ---
        String sqlLignes = "SELECT HR_EvalGrilleLigne_ID, SeqNo, Acronyme,"
            + " ScoreMin, ScoreMax, ValeurMin, ValeurMax,"
            + " SeuilValidation, SeuilEchec,"
            + " IsProgressif, IsBinaire, IsSubjectif, IsPourcentage, IsEliminatoire"
            + " FROM HR_EvalGrilleLigne"
            + " WHERE HR_EvalGrille_ID = ? AND IsActive = 'Y'"
            + " ORDER BY SeqNo";

        // Collecter les ScoreMin et ScoreMax par acronyme pour le calcul des bornes
        Map<String, BigDecimal> acronymeScoreMin = new LinkedHashMap<String, BigDecimal>();
        Map<String, BigDecimal> acronymeScoreMax = new LinkedHashMap<String, BigDecimal>();

        java.sql.PreparedStatement pstmt = null;
        java.sql.ResultSet rs = null;
        int nbLignes = 0;
        try {
            pstmt = DB.prepareStatement(sqlLignes, get_TrxName());
            pstmt.setInt(1, grilleId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int grilleLigneId = rs.getInt(1);
                int seqNo = rs.getInt(2);
                String acronyme = rs.getString(3);
                BigDecimal scoreMin = rs.getBigDecimal(4);
                BigDecimal scoreMax = rs.getBigDecimal(5);
                BigDecimal valeurMin = rs.getBigDecimal(6);
                BigDecimal valeurMax = rs.getBigDecimal(7);
                BigDecimal seuilValidation = rs.getBigDecimal(8);
                BigDecimal seuilEchec = rs.getBigDecimal(9);
                String isProgressif = rs.getString(10);
                String isBinaire = rs.getString(11);
                String isSubjectif = rs.getString(12);
                String isPourcentage = rs.getString(13);
                String isEliminatoire = rs.getString(14);

                // Stocker les bornes par acronyme
                if (acronyme != null) {
                    acronymeScoreMin.put(acronyme.toUpperCase(),
                        scoreMin != null ? scoreMin : BigDecimal.ZERO);
                    acronymeScoreMax.put(acronyme.toUpperCase(),
                        scoreMax != null ? scoreMax : BigDecimal.TEN);
                }

                int nextId = DB.getNextID(clientId, "HR_EvalLigne", get_TrxName());
                DB.executeUpdateEx(
                    "INSERT INTO HR_EvalLigne"
                    + " (HR_EvalLigne_ID, AD_Client_ID, AD_Org_ID,"
                    + "  Created, CreatedBy, Updated, UpdatedBy, IsActive,"
                    + "  HR_Eval_ID, HR_EvalGrilleLigne_ID, SeqNo, Acronyme,"
                    + "  ScoreMin, ScoreMax, ValeurMin, ValeurMax,"
                    + "  SeuilValidation, SeuilEchec,"
                    + "  IsProgressif, IsBinaire, IsSubjectif, IsPourcentage,"
                    + "  IsEliminatoire, IsEvalue, IsOk)"
                    + " VALUES (?, ?, ?, now(), ?, now(), ?, 'Y',"
                    + "  ?, ?, ?, ?,"
                    + "  ?, ?, ?, ?,"
                    + "  ?, ?,"
                    + "  ?, ?, ?, ?,"
                    + "  ?, 'N', 'N')",
                    new Object[]{
                        nextId, clientId, orgId,
                        userId, userId,
                        evalId, grilleLigneId, seqNo, acronyme,
                        scoreMin, scoreMax, valeurMin, valeurMax,
                        seuilValidation, seuilEchec,
                        isProgressif, isBinaire, isSubjectif, isPourcentage,
                        isEliminatoire
                    }, get_TrxName());
                nbLignes++;
            }
        } finally {
            DB.close(rs, pstmt);
        }

        // --- Copier les formules de la grille → HR_EvalResultat ---
        String sqlFormules = "SELECT HR_EvalGrilleFormule_ID, Formule, IsPrincipale"
            + " FROM HR_EvalGrilleFormule"
            + " WHERE HR_EvalGrille_ID = ? AND IsActive = 'Y' AND IsValide = 'Y'";

        int nbFormules = 0;
        try {
            pstmt = DB.prepareStatement(sqlFormules, get_TrxName());
            pstmt.setInt(1, grilleId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int grilleFormuleId = rs.getInt(1);
                String formule = rs.getString(2);
                String isPrincipale = rs.getString(3);

                // Calculer ScoreMin et ScoreMax du résultat
                BigDecimal resultatMin = null;
                BigDecimal resultatMax = null;
                try {
                    resultatMin = EvalFormulaEngine.evaluer(formule, acronymeScoreMin);
                } catch (Exception e) {
                    // Si erreur, on laisse null
                }
                try {
                    resultatMax = EvalFormulaEngine.evaluer(formule, acronymeScoreMax);
                } catch (Exception e) {
                    // Si erreur, on laisse null
                }

                int nextId = DB.getNextID(clientId, "HR_EvalResultat", get_TrxName());
                DB.executeUpdateEx(
                    "INSERT INTO HR_EvalResultat"
                    + " (HR_EvalResultat_ID, AD_Client_ID, AD_Org_ID,"
                    + "  Created, CreatedBy, Updated, UpdatedBy, IsActive,"
                    + "  HR_Eval_ID, HR_EvalGrilleFormule_ID, Formule,"
                    + "  IsPrincipale, IsCalcule, ScoreMin, ScoreMax)"
                    + " VALUES (?, ?, ?, now(), ?, now(), ?, 'Y',"
                    + "  ?, ?, ?,"
                    + "  ?, 'N', ?, ?)",
                    new Object[]{
                        nextId, clientId, orgId,
                        userId, userId,
                        evalId, grilleFormuleId, formule,
                        isPrincipale, resultatMin, resultatMax
                    }, get_TrxName());
                nbFormules++;
            }
        } finally {
            DB.close(rs, pstmt);
        }

        // --- Mettre à jour ScoreTotalMax sur HR_Eval ---
        BigDecimal scoreTotalMax = DB.getSQLValueBD(get_TrxName(),
            "SELECT ScoreMax FROM HR_EvalResultat"
            + " WHERE HR_Eval_ID = ? AND IsPrincipale = 'Y' AND IsActive = 'Y'",
            evalId);

        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsGeneree = 'Y',"
            + " NombreObjectifs = ?, NombreEvalues = 0,"
            + " NombreReussis = 0, NombreEchec = 0,"
            + " NombreEliminatoires = 0, PourcentageAvancement = 0,"
            + " ScoreTotalMax = ?"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ nbLignes, scoreTotalMax, evalId }, get_TrxName());

        return "Évaluation générée : " + nbLignes + " objectif(s), "
            + nbFormules + " formule(s)."
            + (scoreTotalMax != null ? " Score max possible : " + scoreTotalMax : "");
    }
}
