package org.sitracel.notification;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.compiere.model.MCity;
import org.compiere.model.MCountry;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
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

public class NotificationControler {

    /* ==========================
     * CONSTANTES / HELPERS
     * ========================== */


    private static final Map<String, Integer> CACHE_NOTIFICATION_TYPE =
            new ConcurrentHashMap<>();
    
    private static final Map<String, Integer> CACHE_NOTIFICATION_CHANNEL =
            new ConcurrentHashMap<>();    

    private static final Map<String, Integer> CACHE_DESTINATAIRE_TYPE =
            new ConcurrentHashMap<>();
    
    private static final Map<String, Integer> CACHE_NOTIFICATION_STATUT =
            new ConcurrentHashMap<>();

    private static final SimpleDateFormat DATE_FMT =
            new SimpleDateFormat("dd/MM/yyyy");

    /* ==========================
     * NOTIFICATION
     * ========================== */

    public static void notify(NotificationEvent event, PO po) {

        if (event == null || po == null) {
            return;
        }

        Properties ctx = po.getCtx();
        String trxName = po.get_TrxName();

        int typeId = getNotificationTypeId(event);
        if (typeId <= 0) {
            return;
        }

        int canalId = getNotificationCanalId(NotificationCanal.EMAIL);
        if (canalId <= 0) {
            return;
        }

        // 1️⃣ Création de la notification
        MHRNotification notif =
            new MHRNotification(ctx, 0, trxName);

        notif.setHR_NotificationType_ID(typeId);
        notif.setAD_Table_ID(po.get_Table_ID());
        notif.setNumero_Enregistrement(po.get_ID());
        notif.saveEx();

        // 2️⃣ Résolution des destinataires
        List<BeanDestinataire> recipients =
            resolve(event, po);

        for (BeanDestinataire r : recipients) {

            MHRNotificationDestinataire nr =
                new MHRNotificationDestinataire(ctx, 0, trxName);

            nr.setHR_Notification_ID(
                notif.getHR_Notification_ID()
            );
            nr.setC_BPartner_ID(r.getCBPartnerId());
            nr.setEMail(r.getEmail());
            nr.setHR_DestinataireType_ID(
                getDestinataireTypeId(r.getType()) // TO / CC / BCC
            );
            nr.saveEx();
        }

        // 3️⃣ UNE SEULE entrée de queue
        MHRNotificationQueue q =
            new MHRNotificationQueue(ctx, 0, trxName);

        q.setHR_Notification_ID(
            notif.getHR_Notification_ID()
        );
        q.setHR_NotificationStatut_ID(getNotificationStatutId(NotificationStatut.CREATED));
        q.setNombre_Tentative(0);
        q.saveEx();
    }

    /* ==========================
     * BUILD CONTEXT
     * ========================== */

    public static Map<String, Object> build(MHRNotification notif) {

        Map<String, Object> ctx = new HashMap<>();

        if (notif == null) {
            return ctx;
        }

        PO po = MTable.get(
                Env.getCtx(),
                notif.getAD_Table_ID()
        ).getPO(
                notif.getNumero_Enregistrement(),
                notif.get_TrxName()
        );

        if (po == null) {
            return ctx;
        }

        NotificationSqlControler.buildCompany(ctx);
        buildActor(ctx, notif);
        buildApplication(ctx);

        if (po instanceof MHRMission) {
        	MHRMission m = (MHRMission) po;
            buildMission(ctx, m);
        }
        else if (po instanceof MHRMissionAffectation) {
        	MHRMissionAffectation ma = (MHRMissionAffectation) po;
            buildMissionAffectation(ctx, ma);
        }
        else if (po instanceof MHRDemandeExplication) {
        	MHRDemandeExplication d = (MHRDemandeExplication) po;
            buildDemandeExplication(ctx, d);
        }
        else if (po instanceof MHRPunishment) {
        	MHRPunishment s = (MHRPunishment) po;
            buildSanction(ctx, s);
        }
        else if (po instanceof MHRHoliday) {
        	MHRHoliday h = (MHRHoliday) po;
            buildHoliday(ctx, h);
        }

        return ctx;
    }

    /* ==========================
     * DESTINATAIRES
     * ========================== */

    public static List<BeanDestinataire> resolve(
            NotificationEvent event,
            PO po
    ) {

        List<BeanDestinataire> recipients = new ArrayList<>();

        if (event == null || po == null) {
            return recipients;
        }

        List<String> rhRoles = List.of(
                "Responsable Ressources Humaines",
                "Ressource Humaine",
                "Ressource Humaine - Responsable"
        );

        if (po instanceof MHRMission) {
        	
        	MHRMission m = (MHRMission) po;

            addRecipient(
                recipients,
                m.getEmis_Par_Nom_ID(),
                NotificationTypeDestinataireEmail.TO
            );

            safeList(
                GeneralController.getSuperieursHierarchiques(
                    m.getEmis_Par_Nom_ID()
                )
            ).forEach(id ->
                addRecipient(
                    recipients,
                    id,
                    NotificationTypeDestinataireEmail.CC
                )
            );

        } else if (po instanceof MHRMissionAffectation) {

        	MHRMissionAffectation ma = (MHRMissionAffectation) po;
        	
            addRecipient(
                recipients,
                ma.getEmployee_ID(),
                NotificationTypeDestinataireEmail.TO
            );

        } else if (po instanceof MHRDemandeExplication) {

        	MHRDemandeExplication d = (MHRDemandeExplication) po;
        	
            addRecipient(
                recipients,
                d.getC_BPartner_ID(),
                NotificationTypeDestinataireEmail.TO
            );

        } else if (po instanceof MHRPunishment) {

        	MHRPunishment s = (MHRPunishment) po;
        	
            addRecipient(
                recipients,
                s.getC_BPartner_ID(),
                NotificationTypeDestinataireEmail.TO
            );

        } else if (po instanceof MHRHoliday) {

        	MHRHoliday h = (MHRHoliday) po;
        	
            addRecipient(
                recipients,
                h.getC_BPartner_ID(),
                NotificationTypeDestinataireEmail.TO
            );
        }

        safeList(
            GeneralSqlController.getEmployeesByRoles(rhRoles)
        ).forEach(id ->
            addRecipient(
                recipients,
                id,
                NotificationTypeDestinataireEmail.BCC
            )
        );

        return recipients.stream().distinct().toList();
    }

    /* ==========================
     * BUILDERS METIER
     * ========================== */

    public static int getNotificationTypeId(
	        NotificationEvent event
	) {
	
	    if (event == null) {
	        return 0;
	    }
	
	    return CACHE_NOTIFICATION_TYPE.computeIfAbsent(
	        event.getNotificationTypeName(),
	        NotificationSqlControler::loadFromDB
	    );
	}
    
    public static int getNotificationStatutId(
            NotificationStatut statut
    ) {

        if (statut == null) {
            return 0;
        }

        return CACHE_NOTIFICATION_STATUT.computeIfAbsent(
            statut.getCode(),
            NotificationSqlControler::loadNotificationStatutIdFromDB
        );
    }

    
    public static int getDestinataireTypeId(
            NotificationTypeDestinataireEmail type
    ) {

        if (type == null) {
            return 0;
        }

        return CACHE_DESTINATAIRE_TYPE.computeIfAbsent(
            type.getValue(), // TO / CC / BCC
            NotificationSqlControler::loadDestinataireTypeFromDB
        );
    }

	public static int getNotificationCanalId(NotificationCanal channel) {
	
	    if (channel == null) {
	        return 0;
	    }
	
	    return CACHE_NOTIFICATION_CHANNEL.computeIfAbsent(
	        channel.getValue(),
	        NotificationSqlControler::loadChannelIdFromDB
	    );
	}

	public static String getEmailByBPartner(int cBPartnerId) {
	
	    if (cBPartnerId <= 0) {
	        return null;
	    }
	
	    MCBPartner bp = new MCBPartner(
	            Env.getCtx(),
	            cBPartnerId,
	            null
	    );
	
	    String email = bp.getEMail();
	
	    return (email != null && !email.isBlank())
	            ? email.trim()
	            : null;
	}

	private static void buildApplication(
            Map<String, Object> ctx
    ) {
        ctx.put("ApplicationName", "SITRACEL RH");
    }

    private static void buildActor(
            Map<String, Object> ctx,
            MHRNotification notif
    ) {

        BeanIdentifiant actor =
                safeIdentifiant(
                        notif.getCreatedBy(),
                        notif.get_TrxName()
                );

        ctx.put("ActorName", safe(actor.getNomEmploye()));
        ctx.put("ActorMatricule", safe(actor.getMatriculeEmploye()));
        ctx.put("ActorJobTitle", safe(actor.getNomPoste()));
        ctx.put("ActionDate", fmt(notif.getCreated()));
    }

    private static void buildEmployee(
            Map<String, Object> ctx,
            int bpartnerId,
            String trxName
    ) {

        BeanIdentifiant emp =
                safeIdentifiant(bpartnerId, trxName);

        ctx.put("EmployeeFullName", safe(emp.getNomEmploye()));
        ctx.put("EmployeeMatricule", safe(emp.getMatriculeEmploye()));
        ctx.put("EmployeeJob", safe(emp.getNomPoste()));
    }

    private static void buildMission(
            Map<String, Object> ctx,
            MHRMission m
    ) {

        ctx.put("MissionName", safe(m.getName()));
        ctx.put("MissionDescription", safe(m.getDescription()));
        ctx.put("MissionStartDate", fmt(m.getDate_Debut()));
        ctx.put("MissionEndDate", fmt(m.getDate_Fin()));
        ctx.put("MissionLocation", getMissionLocation(m));

        buildEmployee(ctx, m.getEmis_Par_Nom_ID(), m.get_TrxName());
    }

    private static void buildMissionAffectation(
            Map<String, Object> ctx,
            MHRMissionAffectation ma
    ) {

        ctx.put(
            "NewPeriod",
            buildPeriod(
                ma.getDate_Debut(),
                ma.getDate_Fin()
            )
        );

        buildEmployee(ctx, ma.getEmployee_ID(), ma.get_TrxName());
    }

    private static void buildDemandeExplication(
            Map<String, Object> ctx,
            MHRDemandeExplication d
    ) {

        ctx.put(
            "ExplanationReason",
            safe(d.getMotif_Demande_Explication())
        );
        ctx.put(
            "ExplanationDate",
            fmt(d.getDate_Emission())
        );
        ctx.put(
            "ExplanationReply",
            safe(d.getReponse_Demande_Explication())
        );
        ctx.put(
            "ExplanationReplyDate",
            fmt(d.getDate_Reponse())
        );

        buildEmployee(ctx, d.getC_BPartner_ID(), d.get_TrxName());
    }

    private static void buildSanction(
            Map<String, Object> ctx,
            MHRPunishment s
    ) {

        String type = "";

        if (s.getEmission_Sanction_ID() > 0) {
            MHRSanctionAutorisation a =
                new MHRSanctionAutorisation(
                    Env.getCtx(),
                    s.getEmission_Sanction_ID(),
                    s.get_TrxName()
                );

            if (a.getHR_TypeSanction_ID() > 0) {
                MHRTypeSanction t =
                    new MHRTypeSanction(
                        Env.getCtx(),
                        a.getHR_TypeSanction_ID(),
                        s.get_TrxName()
                    );
                type = safe(t.getNom_Sanction());
            }
        }

        ctx.put("SanctionType", type);
        ctx.put(
            "SanctionReason",
            safe(s.getMotif_Demande_Explication())
        );

        buildEmployee(ctx, s.getC_BPartner_ID(), s.get_TrxName());
    }

    private static void buildHoliday(
            Map<String, Object> ctx,
            MHRHoliday h
    ) {

        String type = "";

        if (h.getEmission_Conge_ID() > 0) {
            MHRTypeConge tc =
                new MHRTypeConge(
                    Env.getCtx(),
                    h.getEmission_Conge_ID(),
                    h.get_TrxName()
                );
            type = safe(tc.getNom_Conge());
        }

        ctx.put("HolidayType", type);
        ctx.put(
            "HolidayStartDate",
            fmt(h.getDate_Debut_Souhaitee())
        );
        ctx.put(
            "HolidayEndDate",
            fmt(h.getDate_Fin_Souhaitee())
        );

        buildEmployee(ctx, h.getC_BPartner_ID(), h.get_TrxName());
    }

    /* ==========================
     * UTILITAIRES
     * ========================== */

    private static String fmt(Timestamp ts) {
        return ts != null ? DATE_FMT.format(ts) : "";
    }

    private static String safe(String v) {
        return v != null ? v : "";
    }

    private static <T> List<T> safeList(List<T> l) {
        return l != null ? l : List.of();
    }

    private static BeanIdentifiant safeIdentifiant(
            int bpartnerId,
            String trxName
    ) {
        BeanIdentifiant id =
            MCBPartner.getIdentifiantByBPartner(
                bpartnerId,
                trxName
            );
        return id != null ? id : new BeanIdentifiant();
    }

    private static String buildPeriod(
            Timestamp start,
            Timestamp end
    ) {
        if (start == null && end == null) {
            return "";
        }
        return fmt(start) + " - " + fmt(end);
    }

    private static String getMissionLocation(
            MHRMission m
    ) {

        StringBuilder sb = new StringBuilder();

        if (m.getC_City_ID() > 0) {
            MCity city =
                new MCity(
                    m.getCtx(),
                    m.getC_City_ID(),
                    m.get_TrxName()
                );
            sb.append(safe(city.getName()));
        }

        if (m.getC_Country_ID() > 0) {
            MCountry c =
                new MCountry(
                    m.getCtx(),
                    m.getC_Country_ID(),
                    m.get_TrxName()
                );
            if (sb.length() > 0) sb.append(", ");
            sb.append(safe(c.getName()));
        }

        return sb.toString();
    }
    
    private static void addRecipient(
            List<BeanDestinataire> list,
            int cBPartnerId,
            NotificationTypeDestinataireEmail type
    ) {
        if (cBPartnerId <= 0) {
            return;
        }

        String email = getEmailByBPartner(cBPartnerId);

        if (email == null) {
            return; // pas d’email = pas de notification
        }

        list.add(
            new BeanDestinataire(
                cBPartnerId,
                email,
                type
            )
        );
    }


}
