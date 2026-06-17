package org.sitracel.notification.template;

import org.compiere.model.PO;

public interface NotificationTraitementVariable {

    String resolve(PO po);
}
