package org.sitracel.evaluation.process;

import java.math.BigDecimal;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.employe.HRContratService;

/**
 * Processus : Générer les lignes et résultats d'une évaluation
 *
 * Habilitation : RH uniquement
 * Copie les lignes de HR_EvalGrilleLigne → HR_EvalLigne
 * Copie les formules de HR_EvalGrilleFormule → HR_EvalResultat
 * Met IsGeneree = Y
 *
 * Note : ValeurCible n'existe que dans HR_EvalLigne (personnalisable
 * par l'évaluateur), pas dans HR_EvalGrilleLigne.
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

        // --- Copier les lignes de la grille → HR_EvalLigne ---
        String sqlLignes = "SELECT HR_EvalGrilleLigne_ID, SeqNo, Acronyme,"
            + " ScoreMin, ScoreMax, ValeurMin, ValeurMax,"
            + " SeuilValidation, SeuilEchec,"
            + " IsProgressif, IsBinaire, IsSubjectif, IsPourcentage, IsEliminatoire"
            + " FROM HR_EvalGrilleLigne"
            + " WHERE HR_EvalGrille_ID = ? AND IsActive = 'Y'"
            + " ORDER BY SeqNo";

        int clientId = getAD_Client_ID();
        int orgId = Env.getAD_Org_ID(getCtx());
        int userId = Env.getAD_User_ID(getCtx());

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

                int nextId = DB.getNextID(clientId, "HR_EvalResultat", get_TrxName());
                DB.executeUpdateEx(
                    "INSERT INTO HR_EvalResultat"
                    + " (HR_EvalResultat_ID, AD_Client_ID, AD_Org_ID,"
                    + "  Created, CreatedBy, Updated, UpdatedBy, IsActive,"
                    + "  HR_Eval_ID, HR_EvalGrilleFormule_ID, Formule,"
                    + "  IsPrincipale, IsCalcule)"
                    + " VALUES (?, ?, ?, now(), ?, now(), ?, 'Y',"
                    + "  ?, ?, ?,"
                    + "  ?, 'N')",
                    new Object[]{
                        nextId, clientId, orgId,
                        userId, userId,
                        evalId, grilleFormuleId, formule,
                        isPrincipale
                    }, get_TrxName());
                nbFormules++;
            }
        } finally {
            DB.close(rs, pstmt);
        }

        // --- Mettre à jour l'évaluation ---
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsGeneree = 'Y',"
            + " NombreObjectifs = ?, NombreEvalues = 0,"
            + " NombreReussis = 0, NombreEchec = 0,"
            + " NombreEliminatoires = 0, PourcentageAvancement = 0"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ nbLignes, evalId }, get_TrxName());

        return "Évaluation générée : " + nbLignes + " objectif(s), "
            + nbFormules + " formule(s).";
    }
}
