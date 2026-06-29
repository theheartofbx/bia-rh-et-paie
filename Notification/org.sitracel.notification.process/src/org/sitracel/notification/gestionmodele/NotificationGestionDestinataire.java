package org.sitracel.notification.gestionmodele;

import java.util.List;

import org.compiere.model.PO;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.enumeration.NotificationEvent;

public interface NotificationGestionDestinataire {
	
	boolean supports(PO po);

    List<BeanDestinataire> traiter(
            NotificationEvent event,
            PO po,String trxName
    );

}
