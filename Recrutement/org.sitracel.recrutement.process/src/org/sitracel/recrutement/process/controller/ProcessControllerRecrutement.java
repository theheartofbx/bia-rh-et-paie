package org.sitracel.recrutement.process.controller;

import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.model.MCBPartner;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.model.MHROffreEmploi;
import org.sitracel.recrutement.model.MHROffreTestEvaluation;
import org.sitracel.recrutement.model.MHRSessionRecrutement;

/**
 * Contrôleur Valider/Rejeter du module Recrutement.
 * Session 18 : garde-fous anti-double-clic + vérifications métier.
 */
public class ProcessControllerRecrutement {

    // =========================================================================
    // OFFRE D'EMPLOI
    // =========================================================================

    public static String validerOffreEmploi(Integer offreEmploiID, Integer adUserID) {
        if (offreEmploiID == null || adUserID == null) return null;
        MHROffreEmploi offre = new MHROffreEmploi(Env.getCtx(), offreEmploiID, null);
        if (offre == null || offre.get_ID() == 0) return null;

        // Garde-fous
        if ("Y".equals(offre.get_Value("IsValidee")))
            throw new AdempiereException("Cette offre est déjà validée.");
        // Vérifier qu'un poste est défini
        int posteID = offre.getPoste_ID();
        if (posteID <= 0)
            throw new AdempiereException("Impossible de valider : aucun poste défini sur l'offre.");

        int nbComp = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_OffreCompetences WHERE HR_OffreEmploi_ID=? AND IsActive='Y'",
            offreEmploiID);
        if (nbComp <= 0)
            throw new AdempiereException("Impossible de valider : aucune compétence requise définie. "
                + "Ajoutez au moins une compétence dans l'onglet Compétence.");

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        if (bi == null) return null;
        offre.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        offre.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        offre.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        offre.setIsValidee(true);
        offre.setIsRejetee(false);
        offre.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        offre.setDate_Rejet(null);
        offre.save();
        return "Offre d'emploi validée.";
    }

    public static String rejeterOffreEmploi(Integer offreEmploiID, Integer adUserID) {
        if (offreEmploiID == null || adUserID == null) return null;
        MHROffreEmploi offre = new MHROffreEmploi(Env.getCtx(), offreEmploiID, null);
        if (offre == null || offre.get_ID() == 0) return null;

        if ("Y".equals(offre.get_Value("IsRejetee")))
            throw new AdempiereException("Cette offre est déjà rejetée.");

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        if (bi == null) return null;
        offre.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        offre.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        offre.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        offre.setIsValidee(false);
        offre.setIsRejetee(true);
        offre.setDate_Validation(null);
        offre.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        offre.save();
        return "Offre d'emploi rejetée.";
    }

    // =========================================================================
    // SESSION DE RECRUTEMENT
    // =========================================================================

    public static String validerSessionRecrutement(Integer sessionID, Integer adUserID) {
        if (sessionID == null || adUserID == null) return null;
        MHRSessionRecrutement session = new MHRSessionRecrutement(Env.getCtx(), sessionID, null);
        if (session == null || session.get_ID() == 0) return null;

        // Garde-fous
        if ("Y".equals(session.get_Value("IsValidee")))
            throw new AdempiereException("Cette session est déjà validée.");

        // Vérifier que l'offre est validée
        int offreID = session.getHR_OffreEmploi_ID();
        if (offreID <= 0)
            throw new AdempiereException("Aucune offre d'emploi liée à cette session.");
        int offreValidee = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_OffreEmploi WHERE HR_OffreEmploi_ID=? AND IsValidee='Y'",
            offreID);
        if (offreValidee <= 0)
            throw new AdempiereException("L'offre d'emploi liée n'est pas encore validée.");

        // Vérifier que le test est validé
        int testID = session.getHR_OffreTestEvaluation_ID();
        if (testID <= 0)
            throw new AdempiereException("Aucun test d'évaluation lié à cette session.");
        int testValide = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_OffreTestEvaluation WHERE HR_OffreTestEvaluation_ID=? AND IsValidee='Y'",
            testID);
        if (testValide <= 0)
            throw new AdempiereException("Le test d'évaluation lié n'est pas encore validé.");

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        if (bi == null) return null;
        session.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        session.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        session.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        session.setIsValidee(true);
        session.setIsRejetee(false);
        session.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        session.setDate_Rejet(null);
        session.save();
        return "Session de recrutement validée.";
    }

    public static String rejeterSessionRecrutement(Integer sessionID, Integer adUserID) {
        if (sessionID == null || adUserID == null) return null;
        MHRSessionRecrutement session = new MHRSessionRecrutement(Env.getCtx(), sessionID, null);
        if (session == null || session.get_ID() == 0) return null;

        if ("Y".equals(session.get_Value("IsRejetee")))
            throw new AdempiereException("Cette session est déjà rejetée.");

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        if (bi == null) return null;
        session.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        session.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        session.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        session.setIsValidee(false);
        session.setIsRejetee(true);
        session.setDate_Validation(null);
        session.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        session.save();
        return "Session de recrutement rejetée.";
    }

    // =========================================================================
    // TEST D'ÉVALUATION
    // =========================================================================

    public static String validerTestEvaluation(Integer testID, Integer adUserID) {
        if (testID == null || adUserID == null) return null;
        MHROffreTestEvaluation test = new MHROffreTestEvaluation(Env.getCtx(), testID, null);
        if (test == null || test.get_ID() == 0) return null;

        // Garde-fous
        if ("Y".equals(test.get_Value("IsValidee")))
            throw new AdempiereException("Ce test est déjà validé.");
        int nbCriteres = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_OffreCritereEvaluation WHERE HR_OffreTestEvaluation_ID=? AND IsActive='Y'",
            testID);
        if (nbCriteres <= 0)
            throw new AdempiereException("Impossible de valider : aucun critère d'évaluation défini. "
                + "Ajoutez au moins un critère dans l'onglet Critères d'Évaluation.");

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        if (bi == null) return null;
        test.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        test.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        test.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        test.setIsValidee(true);
        test.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        test.save();
        return "Test d'évaluation validé.";
    }

    // =========================================================================
    // CANDIDATURE
    // =========================================================================

    public static String validerCandidature(Integer candidatureID, Integer adUserID) {
        if (candidatureID == null || adUserID == null) return null;
        MHRCandidature candidature = new MHRCandidature(Env.getCtx(), candidatureID, null);
        if (candidature == null || candidature.get_ID() == 0) return null;

        // Garde-fous
        if ("Y".equals(candidature.get_Value("IsValidee")))
            throw new AdempiereException("Cette candidature est déjà validée.");
        int nbNonEvaluees = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_CandidatEvaluation"
            + " WHERE HR_Candidature_ID=? AND IsActive='Y' AND IsCompetenceEvalue='N'",
            candidatureID);
        if (nbNonEvaluees > 0)
            throw new AdempiereException("Impossible de valider : " + nbNonEvaluees
                + " compétence(s) non encore évaluée(s). "
                + "Complétez toutes les évaluations d'abord.");

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        if (bi == null) return null;
        candidature.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        candidature.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        candidature.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        candidature.setIsValidee(true);
        candidature.setIsRejetee(false);
        candidature.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        candidature.setDate_Rejet(null);
        candidature.save();
        return "Candidature validée.";
    }

    public static String rejeterCandidature(Integer candidatureID, Integer adUserID) {
        if (candidatureID == null || adUserID == null) return null;
        MHRCandidature candidature = new MHRCandidature(Env.getCtx(), candidatureID, null);
        if (candidature == null || candidature.get_ID() == 0) return null;

        if ("Y".equals(candidature.get_Value("IsRejetee")))
            throw new AdempiereException("Cette candidature est déjà rejetée.");

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        if (bi == null) return null;
        candidature.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        candidature.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        candidature.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        candidature.setIsValidee(false);
        candidature.setIsRejetee(true);
        candidature.setDate_Validation(null);
        candidature.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        candidature.save();
        return "Candidature rejetée.";
    }
}
