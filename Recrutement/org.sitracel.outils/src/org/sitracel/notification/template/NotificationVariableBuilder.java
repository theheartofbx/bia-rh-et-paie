package org.sitracel.notification.template;

import java.util.HashMap;
import java.util.Map;

import org.compiere.model.PO;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.model.MCBPartner;

public class NotificationVariableBuilder {

    public static Map<String, Object> buildVariables(
            PO po
    ) {

        Map<String, Object> vars =
                new HashMap<String, Object>();

        if (po == null) {
            return vars;
        }

        vars.put("record_id", po.get_ID());
        vars.put("table_name", po.get_TableName());

        // Exemple si la table a un champ Name
        if (po.get_ColumnIndex("Name") >= 0) {
            vars.put("name", po.get_ValueAsString("Name"));
        }
        
        if (po.get_ColumnIndex("C_BPartner_ID") >= 0) {
        	
        	BeanIdentifiant bi = MCBPartner.getIdentifiantByBPartner(
        			po.get_ValueAsInt("C_BPartner_ID"), 
        			po.get_TrxName()
    			);
        	if(bi!=null) {
                vars.put("EmployeName", bi.getNomEmploye());
                vars.put("EmployePoste", bi.getNomPoste());
                vars.put("EmployeMatricule", bi.getMatriculeEmploye());
        	}
        }
        
        if (po.get_ColumnIndex("Employee_ID") >= 0) {
        	
        	BeanIdentifiant bi = MCBPartner.getIdentifiantByBPartner(
        			po.get_ValueAsInt("Employee_ID"), 
        			po.get_TrxName()
    			);
        	if(bi!=null) {
                vars.put("EmployeName", bi.getNomEmploye());
                vars.put("EmployePoste", bi.getNomPoste());
                vars.put("EmployeMatricule", bi.getMatriculeEmploye());
        	}
        }
        
        if (po.get_ColumnIndex("Emis_Par_Nom_ID") >= 0) {
        	
        	BeanIdentifiant bi = MCBPartner.getIdentifiantByBPartner(
        			po.get_ValueAsInt("Emis_Par_Nom_ID"), 
        			po.get_TrxName()
    			);
        	if(bi!=null) {
                vars.put("EmetteurName", bi.getNomEmploye());
                vars.put("EmetteurPoste", bi.getNomPoste());
                vars.put("EmetteurMatricule", bi.getMatriculeEmploye());
        	}
        }

        if (po.get_ColumnIndex("Approuve_Desapprouve_Nom_ID") >= 0) {
        	
        	BeanIdentifiant bi = MCBPartner.getIdentifiantByBPartner(
        			po.get_ValueAsInt("Approuve_Desapprouve_Nom_ID"), 
        			po.get_TrxName()
    			);
        	if(bi!=null) {
                vars.put("ApprobateurName", bi.getNomEmploye());
                vars.put("ApprobateurPoste", bi.getNomPoste());
                vars.put("ApprobateurMatricule", bi.getMatriculeEmploye());
        	}
        }

        if (po.get_ColumnIndex("Valide_Rejete_Par_Nom_ID") >= 0) {
        	
        	BeanIdentifiant bi = MCBPartner.getIdentifiantByBPartner(
        			po.get_ValueAsInt("Valide_Rejete_Par_Nom_ID"), 
        			po.get_TrxName()
    			);
        	if(bi!=null) {
                vars.put("ValidateurName", bi.getNomEmploye());
                vars.put("ValidateurPoste", bi.getNomPoste());
                vars.put("ValidateurMatricule", bi.getMatriculeEmploye());
        	}
        }

        if (po.get_ColumnIndex("Affecte_Par_Nom_ID") >= 0) {
        	
        	BeanIdentifiant bi = MCBPartner.getIdentifiantByBPartner(
        			po.get_ValueAsInt("Affecte_Par_Nom_ID"), 
        			po.get_TrxName()
    			);
        	if(bi!=null) {
                vars.put("AffectantName", bi.getNomEmploye());
                vars.put("AffectantPoste", bi.getNomPoste());
                vars.put("AffectantMatricule", bi.getMatriculeEmploye());
        	}
        }
        
        return vars;
    }
}
