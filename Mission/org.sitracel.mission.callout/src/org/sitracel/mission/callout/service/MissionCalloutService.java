package org.sitracel.mission.callout.service;

import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionFrais;
import org.sitracel.model.MCBPartner;

/**
 * Service — logique métier du callout mission.
 *
 * Gère le recalcul de l'état de validation des missions
 * et la mise à jour des frais de mission.
 *
 * Remplace ControlerMission.
 */
public final class MissionCalloutService {

    private MissionCalloutService() {}

    // =========================================================================
    // RECALCUL ÉTAT MISSION
    // =========================================================================

    /**
     * Recalcule et met à jour l'état de validation d'une mission
     * en fonction des votes de validation reçus.
     *
     * Règles métier :
     *   1. Si au moins un rejet obligatoire → mission rejetée
     *   2. Si toutes les validations obligatoires validées → mission validée
     *   3. Sinon → en attente
     *
     * Remplace ControlerMission.recalculerEtatMission().
     */
    public static void recalculerEtatMission(int hrMissionId, String trxName) {
        MHRMission mission = new MHRMission(Env.getCtx(), hrMissionId, trxName);
        try {
            // 1. Rejet prioritaire
            if (MissionCalloutRepository.existeRejetObligatoire(hrMissionId, trxName)) {
                mission.setIsRejetee(true);
                mission.setIsValidee(false);
                mission.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
                mission.setDate_Validation(null);
                mission.saveEx();
                return;
            }

            // 2. Validation complète
            if (MissionCalloutRepository.isMissionValide(hrMissionId, trxName)) {
                mission.setIsValidee(true);
                mission.setIsRejetee(false);
                mission.setDate_Validation(
                    MissionCalloutRepository.getDateValidationFinale(hrMissionId, trxName));
                mission.setDate_Rejet(null);
                mission.saveEx();
                return;
            }

            // 3. En attente
            mission.setIsValidee(false);
            mission.setIsRejetee(false);
            mission.setDate_Validation(null);
            mission.setDate_Rejet(null);
            mission.saveEx();

        } catch (Exception e) {
            throw new AdempiereException(
                "Erreur lors du recalcul de l'état de la mission", e);
        }
    }

    // =========================================================================
    // FRAIS DE MISSION
    // =========================================================================

    /**
     * Met à jour les informations de validation/rejet d'un frais de mission.
     *
     * Remplace ControlerMission.majValidationRejet().
     */
    public static void majValidationRejet(MHRMissionFrais frais) {
        boolean validationChangee =
            frais.is_ValueChanged(MHRMissionFrais.COLUMNNAME_IsValidee)
            && frais.isValidee();

        boolean rejetChange =
            frais.is_ValueChanged(MHRMissionFrais.COLUMNNAME_IsRejetee)
            && frais.isRejetee();

        if (!validationChangee && !rejetChange) return;

        // Sécurité métier : jamais les deux à Y
        if (frais.isValidee() && frais.isRejetee()) {
            throw new AdempiereException(
                "Un frais ne peut pas être validé et rejeté en même temps.");
        }

        BeanIdentifiant identifiant = MCBPartner.getIdentifiant(
            Env.getAD_User_ID(Env.getCtx()), frais.get_TrxName());

        if (identifiant == null) {
            throw new AdempiereException(
                "Impossible de déterminer l'identité de l'utilisateur.");
        }

        if (validationChangee) {
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Valide_Par_Nom_ID,
                identifiant.getNumEmploye());
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Valide_Par_Poste_ID,
                identifiant.getNumeroPoste());
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Valide_Par_Matricule,
                identifiant.getMatriculeEmploye());
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Date_Validation,
                new Timestamp(System.currentTimeMillis()));
        }

        if (rejetChange) {
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Rejete_Par_Nom_ID,
                identifiant.getNumEmploye());
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Rejete_Par_Poste_ID,
                identifiant.getNumeroPoste());
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Rejete_Par_Matricule,
                identifiant.getMatriculeEmploye());
            frais.set_ValueOfColumn(MHRMissionFrais.COLUMNNAME_Date_Rejet,
                new Timestamp(System.currentTimeMillis()));
        }
    }
}
