package org.sitracel.mission.modelvalidator.missionvalidation;

import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.employe.HREmployeService;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.mission.callout.ControlerMission;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionValidation;
import org.sitracel.mission.model.MHRTypeValidation;
import org.sitracel.notification.NotificationControler;
import org.sitracel.organigramme.ActionOrganigramme;
import org.sitracel.organigramme.ModuleAutorisation;
import org.sitracel.organigramme.OrganigrammeService;

/**
 * ModelValidator sur MHRMissionValidation.
 *
 * Recalcule l'état de la mission parente après chaque vote de validation,
 * et notifie si la mission vient d'être validée ou rejetée.
 *
 * CORRECTION BUG (session antérieure) : les anciens états (isValidee,
 * isRejetee) sont capturés AVANT le recalcul, car get_ValueOld() ne
 * fonctionne pas sur un PO rechargé depuis la base.
 *
 * Depuis cette session : garde-fou d'habilitation réel avant tout
 * enregistrement d'un vote. Jusqu'ici, rien ne vérifiait que le votant
 * avait vraiment le droit de voter le type de validation qu'il
 * s'attribuait (le callout se contentait de le deviner automatiquement,
 * sans contrôle serveur). Même principe que Congé/Discipline
 * (OrganigrammeService), adapté au modèle "vote" de Mission via la
 * nouvelle table HR_MissionAutorisation. Le cas RH reste à part (pas une
 * position hiérarchique) — vérifié via HRContratService.isUserRH(),
 * même principe que la dérogation par rôle déjà utilisée pour Congé.
 */
public class SitracelModelValidatorMissionValidation implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRMissionValidation.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (po == null || !(po instanceof MHRMissionValidation)) return null;

        MHRMissionValidation mv = (MHRMissionValidation) po;

        // Garde-fou : avant tout enregistrement d'un vote
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            String erreur = validerHabilitationVote(mv);
            if (erreur != null) {
                return erreur;
            }
        }

        int missionId = mv.getHR_Mission_ID();

        if (type == TYPE_AFTER_NEW
         || type == TYPE_AFTER_CHANGE
         || type == TYPE_AFTER_DELETE) {

            if (missionId > 0) {

                // Charger AVANT recalcul pour capturer l'ancien état
                MHRMission missionAvant = new MHRMission(
                    Env.getCtx(), missionId, mv.get_TrxName());

                boolean etaitValidee = missionAvant != null && missionAvant.isValidee();
                boolean etaitRejetee = missionAvant != null && missionAvant.isRejetee();

                // Recalcul de l'état
                ControlerMission.recalculerEtatMission(missionId, mv.get_TrxName());

                // Recharger APRÈS pour connaître le nouvel état
                MHRMission missionApres = new MHRMission(Env.getCtx(), missionId, null);
                if (missionApres == null) return null;

                // Détecter la transition et notifier
                if (missionApres.isValidee() && !etaitValidee) {
                    NotificationControler.notify(
                        NotificationEvent.MISSION_VALIDATED, missionApres);
                } else if (missionApres.isRejetee() && !etaitRejetee) {
                    NotificationControler.notify(
                        NotificationEvent.MISSION_REJECTED, missionApres);
                }
            }
        }

        return null;
    }

    /**
     * Vérifie que le votant a vraiment le droit de voter le type de
     * validation qu'il s'attribue sur cette mission.
     *
     * Ne s'applique que si un vote positif est réellement posé
     * (IsValidee ou IsRejetee = Y) — pas sur une ligne créée sans
     * vote encore choisi.
     *
     * @return null si habilité, un message d'erreur bloquant sinon
     */
    @Override
    public String docValidate(PO po, int timing) { return null; }

    private static String validerHabilitationVote(MHRMissionValidation mv) {
        if (!mv.isValidee() && !mv.isRejetee()) {
            return null;
        }

        if (mv.getHR_TypeValidation_ID() <= 0 || mv.getHR_Mission_ID() <= 0) {
            return null;
        }

        MHRMission mission = new MHRMission(Env.getCtx(), mv.getHR_Mission_ID(), mv.get_TrxName());
        int emetteurId = mission.getEmis_Par_Nom_ID();
        if (emetteurId <= 0) {
            return null;
        }

        int adUserId = Env.getAD_User_ID(Env.getCtx());
        BeanIdentifiant votant = HREmployeService.getIdentifiant(adUserId, mv.get_TrxName());
        if (votant == null) {
            return "Impossible de déterminer votre identité employé.";
        }

        MHRTypeValidation typeValidation = new MHRTypeValidation(
            Env.getCtx(), mv.getHR_TypeValidation_ID(), mv.get_TrxName());
        String nomType = typeValidation.getName();

        boolean estTypeRH = nomType != null && nomType.toUpperCase().contains("RH");

        boolean habilite;
        if (estTypeRH) {
            habilite = org.sitracel.employe.HRContratService.isUserRH(adUserId, mv.get_TrxName());
        } else {
            List<Integer> acteurs = OrganigrammeService.getActeurs(
                emetteurId, mv.getHR_TypeValidation_ID(),
                ModuleAutorisation.MISSION, ActionOrganigramme.VALIDATION,
                mv.get_TrxName());
            habilite = acteurs.contains(votant.getNumEmploye());
        }

        if (!habilite) {
            return "Vous n'êtes pas habilité à voter le type de validation \""
                + nomType + "\" pour cette mission.";
        }

        return null;
    }
}
