package org.sitracel.notification.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.notification.model.I_HR_DestinataireRole;
import org.sitracel.notification.model.I_HR_DestinataireType;
import org.sitracel.notification.model.I_HR_Notification;
import org.sitracel.notification.model.I_HR_NotificationAcces;
import org.sitracel.notification.model.I_HR_NotificationCanal;
import org.sitracel.notification.model.I_HR_NotificationDestinataire;
import org.sitracel.notification.model.I_HR_NotificationFile;
import org.sitracel.notification.model.I_HR_NotificationHistorique;
import org.sitracel.notification.model.I_HR_NotificationQueue;
import org.sitracel.notification.model.I_HR_NotificationStatut;
import org.sitracel.notification.model.I_HR_NotificationTemplate;
import org.sitracel.notification.model.I_HR_NotificationType;
import org.sitracel.notification.model.MHRDestinataireRole;
import org.sitracel.notification.model.MHRDestinataireType;
import org.sitracel.notification.model.MHRNotification;
import org.sitracel.notification.model.MHRNotificationAcces;
import org.sitracel.notification.model.MHRNotificationCanal;
import org.sitracel.notification.model.MHRNotificationDestinataire;
import org.sitracel.notification.model.MHRNotificationFile;
import org.sitracel.notification.model.MHRNotificationHistorique;
import org.sitracel.notification.model.MHRNotificationQueue;
import org.sitracel.notification.model.MHRNotificationStatut;
import org.sitracel.notification.model.MHRNotificationTemplate;
import org.sitracel.notification.model.MHRNotificationType;

public class SitracelNotificationModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_DestinataireRole.Table_Name))
            return MHRDestinataireRole.class;
        if (tableName.equalsIgnoreCase(I_HR_DestinataireType.Table_Name))
            return MHRDestinataireType.class;
        if (tableName.equalsIgnoreCase(I_HR_Notification.Table_Name))
            return MHRNotification.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationAcces.Table_Name))
            return MHRNotificationAcces.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationCanal.Table_Name))
            return MHRNotificationCanal.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationDestinataire.Table_Name))
            return MHRNotificationDestinataire.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationFile.Table_Name))
            return MHRNotificationFile.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationHistorique.Table_Name))
            return MHRNotificationHistorique.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationQueue.Table_Name))
            return MHRNotificationQueue.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationStatut.Table_Name))
            return MHRNotificationStatut.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationTemplate.Table_Name))
            return MHRNotificationTemplate.class;
        if (tableName.equalsIgnoreCase(I_HR_NotificationType.Table_Name))
            return MHRNotificationType.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_DestinataireRole.Table_Name))
            return new MHRDestinataireRole(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_DestinataireType.Table_Name))
            return new MHRDestinataireType(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Notification.Table_Name))
            return new MHRNotification(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationAcces.Table_Name))
            return new MHRNotificationAcces(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationCanal.Table_Name))
            return new MHRNotificationCanal(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationDestinataire.Table_Name))
            return new MHRNotificationDestinataire(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationFile.Table_Name))
            return new MHRNotificationFile(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationHistorique.Table_Name))
            return new MHRNotificationHistorique(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationQueue.Table_Name))
            return new MHRNotificationQueue(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationStatut.Table_Name))
            return new MHRNotificationStatut(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationTemplate.Table_Name))
            return new MHRNotificationTemplate(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationType.Table_Name))
            return new MHRNotificationType(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_DestinataireRole.Table_Name))
            return new MHRDestinataireRole(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_DestinataireType.Table_Name))
            return new MHRDestinataireType(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Notification.Table_Name))
            return new MHRNotification(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationAcces.Table_Name))
            return new MHRNotificationAcces(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationCanal.Table_Name))
            return new MHRNotificationCanal(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationDestinataire.Table_Name))
            return new MHRNotificationDestinataire(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationFile.Table_Name))
            return new MHRNotificationFile(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationHistorique.Table_Name))
            return new MHRNotificationHistorique(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationQueue.Table_Name))
            return new MHRNotificationQueue(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationStatut.Table_Name))
            return new MHRNotificationStatut(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationTemplate.Table_Name))
            return new MHRNotificationTemplate(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_NotificationType.Table_Name))
            return new MHRNotificationType(Env.getCtx(), rs, trxName);
        return null;
    }
}
