package org.sitracel.paie.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_BIA_Bareme;
import org.sitracel.paie.model.I_HR_Annee;
import org.sitracel.paie.model.I_HR_Attribute;
import org.sitracel.paie.model.I_HR_Bareme;
import org.sitracel.paie.model.I_HR_Bareme_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Indemnite_Conge;
import org.sitracel.paie.model.I_HR_Calcul_Paie;
import org.sitracel.paie.model.I_HR_CategorieProfessionnelle;
import org.sitracel.paie.model.I_HR_Concept;
import org.sitracel.paie.model.I_HR_Concept_Category;
import org.sitracel.paie.model.I_HR_DetailIndemniteBrutConge;
import org.sitracel.paie.model.I_HR_Echelon;
import org.sitracel.paie.model.I_HR_Element_Base_Paie;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.I_HR_Element_Conge;
import org.sitracel.paie.model.I_HR_GestionPaieEmploye;
import org.sitracel.paie.model.I_HR_Gestion_Presence;
import org.sitracel.paie.model.I_HR_Historique_Paie;
import org.sitracel.paie.model.I_HR_Mois;
import org.sitracel.paie.model.I_HR_Periode_Salariale;
import org.sitracel.paie.model.I_HR_Rang_Calcul;
import org.sitracel.paie.model.I_HR_RecapSalaire;
import org.sitracel.paie.model.I_HR_Retenue_Salariale;
import org.sitracel.paie.model.I_HR_Taux_Salarial;
import org.sitracel.paie.model.I_HR_TypeContrat;
import org.sitracel.paie.model.I_HR_Type_Calcul;
import org.sitracel.paie.model.I_HR_TypeDeCharge;
import org.sitracel.paie.model.I_HR_Type_Taux_Salarial;
import org.sitracel.paie.model.MBIABareme;
import org.sitracel.paie.model.MHRAnnee;
import org.sitracel.paie.model.MHRAttribute;
import org.sitracel.paie.model.MHRBareme;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculIndemniteConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRCategorieProfessionnelle;
import org.sitracel.paie.model.MHRConcept;
import org.sitracel.paie.model.MHRConceptCategory;
import org.sitracel.paie.model.MHRDetailIndemniteBrutConge;
import org.sitracel.paie.model.MHREchelon;
import org.sitracel.paie.model.MHRElementBasePaie;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRElementConge;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRGestionPresence;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRMois;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.model.MHRRangCalcul;
import org.sitracel.paie.model.MHRRecapSalaire;
import org.sitracel.paie.model.MHRRetenueSalariale;
import org.sitracel.paie.model.MHRTauxSalarial;
import org.sitracel.paie.model.MHRTypeContrat;
import org.sitracel.paie.model.MHRTypeDeCalcul;
import org.sitracel.paie.model.MHRTypeDeCharge;
import org.sitracel.paie.model.MHRTypeTauxSalarial;

public class SitracelPaieModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_BIA_Bareme.Table_Name))
            return MBIABareme.class;
        if (tableName.equalsIgnoreCase(I_HR_Annee.Table_Name))
            return MHRAnnee.class;
        if (tableName.equalsIgnoreCase(I_HR_Attribute.Table_Name))
            return MHRAttribute.class;
        if (tableName.equalsIgnoreCase(I_HR_Bareme.Table_Name))
            return MHRBareme.class;
        if (tableName.equalsIgnoreCase(I_HR_Bareme_Conge.Table_Name))
            return MHRBaremeConge.class;
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Conge.Table_Name))
            return MHRCalculConge.class;
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Indemnite_Conge.Table_Name))
            return MHRCalculIndemniteConge.class;
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Paie.Table_Name))
            return MHRCalculPaie.class;
        if (tableName.equalsIgnoreCase(I_HR_CategorieProfessionnelle.Table_Name))
            return MHRCategorieProfessionnelle.class;
        if (tableName.equalsIgnoreCase(I_HR_Concept.Table_Name))
            return MHRConcept.class;
        if (tableName.equalsIgnoreCase(I_HR_Concept_Category.Table_Name))
            return MHRConceptCategory.class;
        if (tableName.equalsIgnoreCase(I_HR_DetailIndemniteBrutConge.Table_Name))
            return MHRDetailIndemniteBrutConge.class;
        if (tableName.equalsIgnoreCase(I_HR_Echelon.Table_Name))
            return MHREchelon.class;
        if (tableName.equalsIgnoreCase(I_HR_Element_Base_Paie.Table_Name))
            return MHRElementBasePaie.class;
        if (tableName.equalsIgnoreCase(I_HR_ElementBasePaieEmploye.Table_Name))
            return MHRElementBasePaieEmploye.class;
        if (tableName.equalsIgnoreCase(I_HR_Element_Conge.Table_Name))
            return MHRElementConge.class;
        if (tableName.equalsIgnoreCase(I_HR_GestionPaieEmploye.Table_Name))
            return MHRGestionPaieEmploye.class;
        if (tableName.equalsIgnoreCase(I_HR_Gestion_Presence.Table_Name))
            return MHRGestionPresence.class;
        if (tableName.equalsIgnoreCase(I_HR_Historique_Paie.Table_Name))
            return MHRHistoriquePaie.class;
        if (tableName.equalsIgnoreCase(I_HR_Mois.Table_Name))
            return MHRMois.class;
        if (tableName.equalsIgnoreCase(I_HR_Periode_Salariale.Table_Name))
            return MHRPeriodeSalariale.class;
        if (tableName.equalsIgnoreCase(I_HR_Rang_Calcul.Table_Name))
            return MHRRangCalcul.class;
        if (tableName.equalsIgnoreCase(I_HR_RecapSalaire.Table_Name))
            return MHRRecapSalaire.class;
        if (tableName.equalsIgnoreCase(I_HR_Retenue_Salariale.Table_Name))
            return MHRRetenueSalariale.class;
        if (tableName.equalsIgnoreCase(I_HR_Taux_Salarial.Table_Name))
            return MHRTauxSalarial.class;
        if (tableName.equalsIgnoreCase(I_HR_TypeContrat.Table_Name))
            return MHRTypeContrat.class;
        if (tableName.equalsIgnoreCase(I_HR_Type_Calcul.Table_Name))
            return MHRTypeDeCalcul.class;
        if (tableName.equalsIgnoreCase(I_HR_TypeDeCharge.Table_Name))
            return MHRTypeDeCharge.class;
        if (tableName.equalsIgnoreCase(I_HR_Type_Taux_Salarial.Table_Name))
            return MHRTypeTauxSalarial.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_BIA_Bareme.Table_Name))
            return new MBIABareme(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Annee.Table_Name))
            return new MHRAnnee(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Attribute.Table_Name))
            return new MHRAttribute(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Bareme.Table_Name))
            return new MHRBareme(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Bareme_Conge.Table_Name))
            return new MHRBaremeConge(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Conge.Table_Name))
            return new MHRCalculConge(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Indemnite_Conge.Table_Name))
            return new MHRCalculIndemniteConge(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Paie.Table_Name))
            return new MHRCalculPaie(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_CategorieProfessionnelle.Table_Name))
            return new MHRCategorieProfessionnelle(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Concept.Table_Name))
            return new MHRConcept(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Concept_Category.Table_Name))
            return new MHRConceptCategory(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_DetailIndemniteBrutConge.Table_Name))
            return new MHRDetailIndemniteBrutConge(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Echelon.Table_Name))
            return new MHREchelon(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Element_Base_Paie.Table_Name))
            return new MHRElementBasePaie(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_ElementBasePaieEmploye.Table_Name))
            return new MHRElementBasePaieEmploye(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Element_Conge.Table_Name))
            return new MHRElementConge(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_GestionPaieEmploye.Table_Name))
            return new MHRGestionPaieEmploye(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Gestion_Presence.Table_Name))
            return new MHRGestionPresence(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Historique_Paie.Table_Name))
            return new MHRHistoriquePaie(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mois.Table_Name))
            return new MHRMois(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Periode_Salariale.Table_Name))
            return new MHRPeriodeSalariale(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Rang_Calcul.Table_Name))
            return new MHRRangCalcul(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_RecapSalaire.Table_Name))
            return new MHRRecapSalaire(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Retenue_Salariale.Table_Name))
            return new MHRRetenueSalariale(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Taux_Salarial.Table_Name))
            return new MHRTauxSalarial(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeContrat.Table_Name))
            return new MHRTypeContrat(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Calcul.Table_Name))
            return new MHRTypeDeCalcul(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeDeCharge.Table_Name))
            return new MHRTypeDeCharge(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Taux_Salarial.Table_Name))
            return new MHRTypeTauxSalarial(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_BIA_Bareme.Table_Name))
            return new MBIABareme(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Annee.Table_Name))
            return new MHRAnnee(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Attribute.Table_Name))
            return new MHRAttribute(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Bareme.Table_Name))
            return new MHRBareme(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Bareme_Conge.Table_Name))
            return new MHRBaremeConge(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Conge.Table_Name))
            return new MHRCalculConge(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Indemnite_Conge.Table_Name))
            return new MHRCalculIndemniteConge(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Calcul_Paie.Table_Name))
            return new MHRCalculPaie(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_CategorieProfessionnelle.Table_Name))
            return new MHRCategorieProfessionnelle(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Concept.Table_Name))
            return new MHRConcept(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Concept_Category.Table_Name))
            return new MHRConceptCategory(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_DetailIndemniteBrutConge.Table_Name))
            return new MHRDetailIndemniteBrutConge(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Echelon.Table_Name))
            return new MHREchelon(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Element_Base_Paie.Table_Name))
            return new MHRElementBasePaie(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_ElementBasePaieEmploye.Table_Name))
            return new MHRElementBasePaieEmploye(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Element_Conge.Table_Name))
            return new MHRElementConge(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_GestionPaieEmploye.Table_Name))
            return new MHRGestionPaieEmploye(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Gestion_Presence.Table_Name))
            return new MHRGestionPresence(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Historique_Paie.Table_Name))
            return new MHRHistoriquePaie(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mois.Table_Name))
            return new MHRMois(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Periode_Salariale.Table_Name))
            return new MHRPeriodeSalariale(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Rang_Calcul.Table_Name))
            return new MHRRangCalcul(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_RecapSalaire.Table_Name))
            return new MHRRecapSalaire(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Retenue_Salariale.Table_Name))
            return new MHRRetenueSalariale(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Taux_Salarial.Table_Name))
            return new MHRTauxSalarial(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeContrat.Table_Name))
            return new MHRTypeContrat(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Calcul.Table_Name))
            return new MHRTypeDeCalcul(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeDeCharge.Table_Name))
            return new MHRTypeDeCharge(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Taux_Salarial.Table_Name))
            return new MHRTypeTauxSalarial(Env.getCtx(), rs, trxName);
        return null;
    }
}
