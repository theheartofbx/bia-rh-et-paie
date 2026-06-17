package org.sitracel.notification.process.notifier;

import java.util.List;
import java.util.Map;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.SvrProcess;
import org.compiere.util.EMail;
import org.sitracel.notification.model.MHRNotification;
import org.sitracel.notification.model.MHRNotificationQueue;
import org.sitracel.notification.model.MHRNotificationTemplate;
import org.sitracel.notification.model.MHRNotificationType;

public class SitracelProcessNotifier extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		List<MHRNotificationQueue> queue =
	            MHRNotificationQueue.getNew(getCtx(), get_TrxName());

	        for (MHRNotificationQueue q : queue) {

	            try {
	                sendOne(q);
	                q.setStatus(MHRNotificationQueue.STATUS_Sent);
	            } catch (Exception e) {
	                q.setStatus(MHRNotificationQueue.STATUS_Error);
	                q.setErrorMsg(e.getMessage());
	            }

	            q.saveEx();
	        }
		return null;
	}
	
	private void sendOne(MHRNotificationQueue q) {

	    MHRNotification notif = q.getHR_Notification();
	    MHRNotificationType type = notif.getHR_NotificationType();

	    // 1️⃣ Déterminer la langue
	    String adLanguage = HRLanguageUtil.getLanguage(q.getC_BPartner_ID());

	    // 2️⃣ Charger le template
	    MHRNotificationTemplate template =
	        MHRNotificationTemplate.get(type.get_ID(), adLanguage);

	    if (template == null) {
	        throw new AdempiereException("Template introuvable");
	    }

	    // 3️⃣ Construire le contexte
	    Map<String, Object> context =
	        NotificationContextBuilder.build(notif);

	    // 4️⃣ Rendu dynamique
	    String subject =
	        NotificationTemplateEngine.render(template.getMessage_Objet(), context);

	    String body =
	        NotificationTemplateEngine.render(template.getMessage_Contenu(), context);

	    // 5️⃣ Envoi mail natif iDempiere
	    EMail email = HRMailUtil.createMail(q.getC_BPartner_ID(), subject, body);
	    email.send();
	}

}
