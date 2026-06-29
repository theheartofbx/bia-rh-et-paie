package org.sitracel.notification;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.compiere.model.PO;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.enumeration.NotificationStatut;
import org.sitracel.notification.model.MHRNotification;
import org.sitracel.notification.model.MHRNotificationDestinataire;
import org.sitracel.notification.model.MHRNotificationTemplate;
import org.sitracel.notification.model.MHRNotificationType;
import org.sitracel.notification.template.NotificationGestionTemplate;
import org.sitracel.notification.template.NotificationTemplateService;
import org.sitracel.notification.template.NotificationVariableBuilder;

public class NotificationPersistenceService {

	public static void createNotification(
	        MHRNotificationType notifType,
	        PO po,
	        List<BeanDestinataire> destinataires,
	        String trxName
	) {

	    if (notifType == null || po == null || destinataires == null || destinataires.isEmpty()) {
	        return;
	    }

	    Properties ctx = po.getCtx();

	    // 1️⃣ Charger template
	    MHRNotificationTemplate template =
	            NotificationTemplateService.getByNotificationType(
	                    notifType.getHR_NotificationType_ID(),
	                    trxName
	            );

	    if (template == null) {
	        return;
	    }

	    // 2️⃣ Construire variables
	    Map<String, Object> vars =
	            NotificationVariableBuilder.buildVariables(po);

	    // 3️⃣ Render objet et message
	    String objet =
	            NotificationGestionTemplate.render(
	                    template.getMessage_Objet(),
	                    vars
	            );

	    String message =
	            NotificationGestionTemplate.render(
	                    template.getMessage_Contenu(),
	                    vars
	            );

	    // 4️⃣ Créer notification mère
	    MHRNotification notification =
	            new MHRNotification(ctx, 0, trxName);

	    notification.setHR_NotificationType_ID(
	            notifType.getHR_NotificationType_ID()
	    );
	    notification.setAD_Table_ID(po.get_Table_ID());
	    notification.setNumero_Enregistrement(po.get_ID());

	    notification.saveEx();

	    // 5️⃣ Créer destinataires
	    for (BeanDestinataire d : destinataires) {

	        MHRNotificationDestinataire dest =
	                new MHRNotificationDestinataire(ctx, 0, trxName);

	        dest.setHR_Notification_ID(
	                notification.getHR_Notification_ID()
	        );

	        dest.setC_BPartner_ID(d.getCBPartnerId());

	        if (d.getCanal() != null) {
	            int canalId =
	                    NotificationSqlControler.loadChannelIdFromDB(
	                            d.getCanal().name()
	                    );
	            dest.setHR_NotificationCanal_ID(canalId);
	        }

	        dest.setAdresse(d.getCanalValue());

	        dest.setHR_DestinataireType_ID(
	                NotificationCacheService
	                        .getDestinataireTypeId(d.getType())
	        );

	        // ✅ ON FIGE LE CONTENU
	        dest.setObjet(objet);
	        dest.setMessage(message);

	        dest.setHR_NotificationStatut_ID(
	                NotificationCacheService
	                        .getStatutId(NotificationStatut.CREATED)
	        );

	        dest.setNombre_Tentative(0);

	        dest.saveEx();
	    }
	}

}
