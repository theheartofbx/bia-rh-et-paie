package org.sitracel.notification.gestionmodele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.bean.BeanParametreNotificationCible;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.mission.model.MHRMissionAffectation;
import org.sitracel.notification.NotificationSqlControler;
import org.sitracel.notification.model.MHRNotificationType;

public class NotificationGestionDestinataireMissionAffectation
implements NotificationGestionDestinataire {

	@Override
	public boolean supports(PO po) {
	return po instanceof MHRMissionAffectation;
	}
	
	@Override
	public List<BeanDestinataire> traiter(
		    NotificationEvent event,
		    PO po,
		    String trxName
		) {
		
		MHRMissionAffectation mi = (MHRMissionAffectation) po;
		
		Map<String, BeanDestinataire> map =
		        new HashMap<String, BeanDestinataire>();
		
		MHRNotificationType notifType =
		        new MHRNotificationType(
		        		Env.getCtx(),
		        		NotificationSqlControler.loadNotificationTypeFromDB(event.name()),
		        		trxName
	        		);
		
		if (notifType.getName() == null) {
		    return new ArrayList<BeanDestinataire>();
		}
		
		BeanParametreNotificationCible param =
		        new BeanParametreNotificationCible(
		                mi.getEmployee_ID(),     // employé concerné
		                mi.getAffecte_Par_Nom_ID(),   // émetteur
		                mi.getCreated()            // date référence
		        );
		
		NotificationDestinataireEnrichir.enrichir(
		        map,
		        notifType,
		        param,
		        po.getCtx(),
		        po.get_TrxName()
		);
		
		return new ArrayList<BeanDestinataire>(map.values());
		}
}

