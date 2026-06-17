package org.sitracel.notification.gestionmodele;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.compiere.util.Env;
import org.sitracel.enumeration.NotificationCible;
import org.sitracel.model.MHRCibleType;
import org.sitracel.notification.model.MHRNotificationAcces;

public class NotificationRegroupementCible {
	private final Set<Integer> roleIds = new HashSet<>();
    private final Set<Integer> categorieIds = new HashSet<>();
    private boolean includeSuperieurs;
    private boolean includeEmploye;
    private boolean includeEmetteur;
    
    public NotificationRegroupementCible()
    {
    	
    }
    
    
    public Set<Integer> getRoleIds() {
        return roleIds;
    }

    public Set<Integer> getCategorieIds() {
        return categorieIds;
    }

    public boolean isIncludeSuperieurs() {
        return includeSuperieurs;
    }

    public boolean isIncludeEmploye() {
        return includeEmploye;
    }
    
    public boolean isIncludeEmetteur() {
        return includeEmetteur;
    }

    /* ==========================
     * COLLECTE
     * ========================== */

    public void addRole(int roleId) {
        if (roleId > 0) {
            roleIds.add(roleId);
        }
    }

    public void addCategorie(int categorieId) {
        if (categorieId > 0) {
            categorieIds.add(categorieId);
        }
    }

    public void enableSuperieurs() {
        includeSuperieurs = true;
    }

    public void enableEmploye() {
        includeEmploye = true;
    }

    public void enableEmetteur() {
        includeEmetteur = true;
    }
    
    public static NotificationRegroupementCible group(
            List<MHRNotificationAcces> regles
    ) {

    	NotificationRegroupementCible groups =
            new NotificationRegroupementCible();

        for (MHRNotificationAcces r : regles) {
        	
        	MHRCibleType cibleType = new MHRCibleType(Env.getCtx(), r.getHR_CibleType_ID(), null);
        	
        	if(cibleType.getName() == null) {
        		continue;
        	}

            NotificationCible cible =
                NotificationCible.fromName(cibleType.getName());

            if (cible == null) {
                continue;
            }

            switch (cible) {

                case ROLE:
                    groups.addRole(r.getCible_ID());
                    break;

                case CATEGORIE_RESP:
                    groups.addCategorie(
                        r.getCible_ID()
                    );
                    break;

                case SUPERIEUR:
                    groups.enableSuperieurs();
                    break;

                case EMPLOYE:
                    groups.enableEmploye();
                    break;

                case EMETTEUR:
                    groups.enableEmetteur();
                    break;
            }
        }

        return groups;
    }
}
