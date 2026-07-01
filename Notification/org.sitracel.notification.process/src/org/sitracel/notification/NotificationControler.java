package org.sitracel.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.enumeration.NotificationCanal;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.enumeration.NotificationStatut;
import org.sitracel.enumeration.NotificationTypeDestinataireEmail;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionAffectation;
import org.sitracel.model.MCBPartner;
import org.sitracel.notification.model.MHRNotification;
import org.sitracel.notification.model.MHRNotificationDestinataire;
import org.sitracel.notification.model.MHRNotificationQueue;
import org.sitracel.notification.gestionmodele.NotificationGestionCanal;
import org.sitracel.organigramme.ActionOrganigramme;
import org.sitracel.organigramme.ModuleAutorisation;
import org.sitracel.organigramme.OrganigrammeService;

/**
 * Point d'entrée unique pour l'envoi de notifications.
 *
 * Utilise OrganigrammeService pour résoudre les destinataires
 * en fonction des autorisations définies par module et type de document.
 *
 * Usage depuis n'importe quel module :
 *   NotificationControler.notify(NotificationEvent.HOLIDAY_CREATED, monConge);
 */
public class NotificationControler {

    // Cache des IDs techniques pour éviter les requêtes répétées
    private static final Map<String, Integer> CACHE_NOTIFICATION_TYPE =
        new ConcurrentHashMap<>();
    private static final Map<String, Integer> CACHE_NOTIFICATION_CHANNEL =
        new ConcurrentHashMap<>();
    private static final Map<String, Integer> CACHE_DESTINATAIRE_TYPE =
        new ConcurrentHashMap<>();
    private static final Map<String, Integer> CACHE_NOTIFICATION_STATUT =
        new ConcurrentHashMap<>();

    private NotificationControler() {}

    // =========================================================================
    // API PRINCIPALE
    // =========================================================================

    /**
     * Déclenche une notification pour un événement sur un document.
     * Résout automatiquement les destinataires via OrganigrammeService.
     *
     * @param event L'événement métier (ex: HOLIDAY_CREATED)
     * @param po    Le document concerné (ex: MHRHoliday)
     */
    public static void notify(NotificationEvent event, PO po) {

        if (event == null || po == null) return;

        Properties ctx  = po.getCtx();
        String trxName  = po.get_TrxName();

        // 1. Vérifier que le type de notification existe en base
        int typeId = getNotificationTypeId(event);
        if (typeId <= 0) return;

        // 2. Résoudre les destinataires
        List<BeanDestinataire> destinataires = resoudreDestinataires(event, po);
        if (destinataires.isEmpty()) return;

        // 3. Créer la notification mère
        MHRNotification notif = new MHRNotification(ctx, 0, trxName);
        notif.setHR_NotificationType_ID(typeId);
        notif.setAD_Table_ID(po.get_Table_ID());
        notif.setNumero_Enregistrement(po.get_ID());
        notif.saveEx();

        // 4. Créer les destinataires (dédupliqués)
        List<Integer> dejAjoutes = new ArrayList<>();
        for (BeanDestinataire dest : destinataires) {
            if (dest.getCBPartnerId() <= 0) continue;
            if (dejAjoutes.contains(dest.getCBPartnerId())) continue;
            dejAjoutes.add(dest.getCBPartnerId());

            String email = NotificationGestionCanal.getEmailByBPartner(
                dest.getCBPartnerId()
            );
            if (email == null || email.isBlank()) continue;

            MHRNotificationDestinataire nr =
                new MHRNotificationDestinataire(ctx, 0, trxName);
            nr.setHR_Notification_ID(notif.getHR_Notification_ID());
            nr.setC_BPartner_ID(dest.getCBPartnerId());
            nr.setAdresse(email);
            nr.setHR_DestinataireType_ID(
                getDestinataireTypeId(dest.getType())
            );
            nr.saveEx();
        }

        // 5. Une seule entrée de queue
        MHRNotificationQueue q = new MHRNotificationQueue(ctx, 0, trxName);
        q.setHR_Notification_ID(notif.getHR_Notification_ID());
        q.setHR_NotificationStatut_ID(
            getNotificationStatutId(NotificationStatut.CREATED)
        );
        q.setNombre_Tentative(0);
        q.saveEx();
    }

    // =========================================================================
    // RÉSOLUTION DES DESTINATAIRES
    // =========================================================================

    /**
     * Résout les destinataires selon le type de document et l'événement.
     *
     * Règle générale :
     *   TO  → l'employé concerné
     *   CC  → les acteurs habilités (approbateurs/validateurs selon l'événement)
     *   BCC → les responsables RH
     */
    private static List<BeanDestinataire> resoudreDestinataires(
            NotificationEvent event, PO po) {

        List<BeanDestinataire> destinataires = new ArrayList<>();
        String trxName = po.get_TrxName();

        if (po instanceof MHRHoliday) {
            resoudreConge(destinataires, event, (MHRHoliday) po, trxName);

        } else if (po instanceof MHRPunishment) {
            resoudreSanction(destinataires, event, (MHRPunishment) po, trxName);

        } else if (po instanceof MHRDemandeExplication) {
            resoudreDemandeExplication(
                destinataires, event, (MHRDemandeExplication) po, trxName);

        } else if (po instanceof MHRMission) {
            resoudreMission(destinataires, event, (MHRMission) po, trxName);

        } else if (po instanceof MHRMissionAffectation) {
            resoudreMissionAffectation(
                destinataires, (MHRMissionAffectation) po, trxName);
        }

        // BCC → responsables RH (tous les modules)
        ajouterRH(destinataires, trxName);

        return destinataires;
    }

    // ── CONGÉS ────────────────────────────────────────────────────────────────

    private static void resoudreConge(
            List<BeanDestinataire> dest,
            NotificationEvent event,
            MHRHoliday h,
            String trxName) {

        int employeId  = h.getC_BPartner_ID();
        int typeCongeId = getTypeCongeId(h);

        // TO → l'employé
        ajouterTO(dest, employeId);

        // CC → selon l'événement, on notifie les bonnes personnes
        switch (event) {
            case HOLIDAY_CREATED:
                // Notifier les approbateurs potentiels
                ajouterCC(dest, OrganigrammeService.getActeurs(
                    employeId, typeCongeId,
                    ModuleAutorisation.CONGE,
                    ActionOrganigramme.APPROBATION,
                    trxName
                ));
                break;

            case HOLIDAY_APPROVED:
                // Notifier les validateurs potentiels
                ajouterCC(dest, OrganigrammeService.getActeurs(
                    employeId, typeCongeId,
                    ModuleAutorisation.CONGE,
                    ActionOrganigramme.VALIDATION,
                    trxName
                ));
                break;

            case HOLIDAY_DISAPPROVED:
            case HOLIDAY_REJECTED:
                // Notifier l'émetteur (qui a créé le congé)
                ajouterCC(dest, h.getEmis_Par_Nom_ID());
                break;

            case HOLIDAY_VALIDATED:
                // Notifier les responsables de compensation si applicable
                ajouterCC(dest, OrganigrammeService.getActeurs(
                    employeId, typeCongeId,
                    ModuleAutorisation.CONGE,
                    ActionOrganigramme.COMPENSATION,
                    trxName
                ));
                break;

            default:
                // Pour tout autre événement, notifier tous les supérieurs
                ajouterCC(dest, OrganigrammeService.getSuperieurs(
                    employeId, trxName
                ));
                break;
        }
    }

    // ── SANCTIONS ─────────────────────────────────────────────────────────────

    private static void resoudreSanction(
            List<BeanDestinataire> dest,
            NotificationEvent event,
            MHRPunishment p,
            String trxName) {

        int employeId     = p.getC_BPartner_ID();
        int typeSanctionId = getTypeSanctionId(p);

        // TO → l'employé
        ajouterTO(dest, employeId);

        switch (event) {
            case SANCTION_CREATED:
                // Notifier les approbateurs
                ajouterCC(dest, OrganigrammeService.getActeurs(
                    employeId, typeSanctionId,
                    ModuleAutorisation.SANCTION,
                    ActionOrganigramme.APPROBATION,
                    trxName
                ));
                break;

            case SANCTION_APPROVED:
                // Notifier les validateurs
                ajouterCC(dest, OrganigrammeService.getActeurs(
                    employeId, typeSanctionId,
                    ModuleAutorisation.SANCTION,
                    ActionOrganigramme.VALIDATION,
                    trxName
                ));
                break;

            case SANCTION_DISAPPROVED:
            case SANCTION_REJECTED:
                // Notifier l'émetteur
                ajouterCC(dest, p.getEmis_Par_Nom_ID());
                break;

            case SANCTION_VALIDATED:
                // Notifier tous les supérieurs (information)
                ajouterCC(dest, OrganigrammeService.getSuperieurs(
                    employeId, trxName
                ));
                break;

            default:
                ajouterCC(dest, OrganigrammeService.getSuperieurs(
                    employeId, trxName
                ));
                break;
        }
    }

    // ── DEMANDE D'EXPLICATION ─────────────────────────────────────────────────

    private static void resoudreDemandeExplication(
            List<BeanDestinataire> dest,
            NotificationEvent event,
            MHRDemandeExplication d,
            String trxName) {

        if (event == NotificationEvent.DEMANDE_EXPLICATION_CREATED) {
            // TO → l'employé qui doit répondre
            ajouterTO(dest, d.getC_BPartner_ID());
            // CC → l'émetteur de la demande
            ajouterCC(dest, d.getEmis_Par_Nom_ID());

        } else if (event == NotificationEvent.DEMANDE_EXPLICATION_REPLIED) {
            // TO → l'émetteur (qui attend la réponse)
            ajouterTO(dest, d.getEmis_Par_Nom_ID());
            // CC → l'employé qui a répondu
            ajouterCC(dest, d.getC_BPartner_ID());
        }
    }

    // ── MISSION ───────────────────────────────────────────────────────────────

    private static void resoudreMission(
            List<BeanDestinataire> dest,
            NotificationEvent event,
            MHRMission m,
            String trxName) {

        // TO → l'émetteur de la mission
        ajouterTO(dest, m.getEmis_Par_Nom_ID());
        // CC → tous les supérieurs (information)
        ajouterCC(dest, OrganigrammeService.getSuperieurs(
            m.getEmis_Par_Nom_ID(), trxName
        ));
    }

    private static void resoudreMissionAffectation(
            List<BeanDestinataire> dest,
            MHRMissionAffectation ma,
            String trxName) {

        // TO → l'employé affecté
        ajouterTO(dest, ma.getEmployee_ID());
        // CC → supérieurs de l'employé affecté
        ajouterCC(dest, OrganigrammeService.getSuperieurs(
            ma.getEmployee_ID(), trxName
        ));
    }

    // ── RESPONSABLES RH (BCC) ─────────────────────────────────────────────────

    private static void ajouterRH(
            List<BeanDestinataire> dest, String trxName) {

        List<String> rolesRH = List.of(
            "Responsable Ressources Humaines",
            "Ressource Humaine",
            "Ressource Humaine - Responsable"
        );

        NotificationSqlControler
            .getEmployeesByRoles(rolesRH, trxName)
            .forEach(id -> ajouterBCC(dest, id));
    }

    // =========================================================================
    // UTILITAIRES DESTINATAIRES
    // =========================================================================

    private static void ajouterTO(List<BeanDestinataire> dest, int bpartnerId) {
        if (bpartnerId <= 0) return;
        BeanDestinataire d = new BeanDestinataire();
        d.setCBPartnerId(bpartnerId);
        d.setType(NotificationTypeDestinataireEmail.TO);
        dest.add(d);
    }

    private static void ajouterCC(List<BeanDestinataire> dest, int bpartnerId) {
        if (bpartnerId <= 0) return;
        BeanDestinataire d = new BeanDestinataire();
        d.setCBPartnerId(bpartnerId);
        d.setType(NotificationTypeDestinataireEmail.CC);
        dest.add(d);
    }

    private static void ajouterCC(
            List<BeanDestinataire> dest, List<Integer> ids) {
        if (ids == null) return;
        ids.forEach(id -> ajouterCC(dest, id));
    }

    private static void ajouterBCC(List<BeanDestinataire> dest, int bpartnerId) {
        if (bpartnerId <= 0) return;
        BeanDestinataire d = new BeanDestinataire();
        d.setCBPartnerId(bpartnerId);
        d.setType(NotificationTypeDestinataireEmail.BCC);
        dest.add(d);
    }

    // =========================================================================
    // UTILITAIRES TYPE IDS
    // =========================================================================

    private static int getTypeCongeId(MHRHoliday h) {
        if (h.getEmission_Conge_ID() <= 0) return 0;
        MHRAutorisationConge autorisation = new MHRAutorisationConge(
            Env.getCtx(), h.getEmission_Conge_ID(), null
        );
        return autorisation != null ? autorisation.getHR_Type_Conge_ID() : 0;
    }

    private static int getTypeSanctionId(MHRPunishment p) {
        if (p.getEmission_Sanction_ID() <= 0) return 0;
        MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(
            Env.getCtx(), p.getEmission_Sanction_ID(), null
        );
        return autorisation != null ? autorisation.getHR_TypeSanction_ID() : 0;
    }

    // =========================================================================
    // CACHE IDS TECHNIQUES
    // =========================================================================

    public static int getNotificationTypeId(NotificationEvent event) {
        if (event == null) return 0;
        return CACHE_NOTIFICATION_TYPE.computeIfAbsent(
            event.getCode(),
            NotificationSqlControler::loadNotificationTypeIdFromDB
        );
    }

    public static int getNotificationStatutId(NotificationStatut statut) {
        if (statut == null) return 0;
        return CACHE_NOTIFICATION_STATUT.computeIfAbsent(
            statut.getCode(),
            NotificationSqlControler::loadNotificationStatutIdFromDB
        );
    }

    public static int getDestinataireTypeId(NotificationTypeDestinataireEmail type) {
        if (type == null) return 0;
        return CACHE_DESTINATAIRE_TYPE.computeIfAbsent(
            type.getValue(),
            NotificationSqlControler::loadDestinataireTypeFromDB
        );
    }

    public static int getNotificationCanalId(NotificationCanal canal) {
        if (canal == null) return 0;
        return CACHE_NOTIFICATION_CHANNEL.computeIfAbsent(
            canal.getValue(),
            NotificationSqlControler::loadChannelIdFromDB
        );
    }
}
