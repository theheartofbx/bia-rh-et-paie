package org.sitracel.notification.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.notification.process.notifier.SitracelProcessNotifier;

public class SitracelNotificationProcessFactory implements IProcessFactory {

    @Override
    public ProcessCall newProcessInstance(String className) {
        if (SitracelProcessNotifier.class.getName().equals(className)) {
            return new SitracelProcessNotifier();
        }
        return null;
    }
}
