package org.sitracel.stage.modelvalidator.stage;

import java.sql.Timestamp;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.stage.model.I_HR_Stage;

public class SitracelModelValidatorStage implements ModelValidator {

    private int m_AD_Client_ID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_Stage.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return m_AD_Client_ID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            return beforeSave(po, type == TYPE_BEFORE_NEW);
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }

    private String beforeSave(PO po, boolean isNew) {
        // Garde-fou 1 : Verrouillage si stage déjà validé
        if (!isNew) {
            Object oldValidee = po.get_ValueOld("IsValidee");
            if (oldValidee != null && "Y".equals(oldValidee.toString())) {
                return "Ce stage est validé et ne peut plus être modifié.";
            }
        }

        // Garde-fou 2 : Contrat obligatoire et de type Stage (HR_ContratType_ID = 303)
        int contratId = (Integer) po.get_Value("HR_Contrat_ID");
        if (contratId > 0) {
            int typeContrat = DB.getSQLValue(po.get_TrxName(),
                "SELECT HR_ContratType_ID FROM HR_Contrat WHERE HR_Contrat_ID = ?", contratId);
            if (typeContrat != 303) {
                return "Le contrat sélectionné n'est pas de type Stage.";
            }
        }

        // Garde-fou 3 : Date_Fin >= Date_Debut
        Timestamp debut = (Timestamp) po.get_Value("Date_Debut");
        Timestamp fin = (Timestamp) po.get_Value("Date_Fin");
        if (debut != null && fin != null && fin.before(debut)) {
            return "La date de fin ne peut pas être antérieure à la date de début.";
        }

        // Garde-fou 4 : Empêcher modification manuelle des indicateurs
        if (!isNew) {
            String[] indicateurs = {"NombreObjectifs", "NombreDefinis", "NombreEvalues", "PourcentageAvancement"};
            for (String col : indicateurs) {
                Object oldVal = po.get_ValueOld(col);
                Object newVal = po.get_Value(col);
                if (oldVal != null && newVal != null && !oldVal.equals(newVal)) {
                    // Remettre l'ancienne valeur silencieusement
                    po.set_ValueNoCheck(col, oldVal);
                }
            }
        }

        return null;
    }
}
