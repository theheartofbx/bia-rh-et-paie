package org.sitracel.paie.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.paie.model.MHRMouvementPaie;
import org.sitracel.paie.model.MHRMouvementPaieType;

/**
 * Callout HR_Mouvement_Paie — Synchronisation IsIndemnite depuis le type.
 *
 * Déclenché sur : HR_MouvementPaieType_ID
 *
 * Quand l'utilisateur choisit un type de mouvement, IsIndemnite est
 * automatiquement copié depuis HR_MouvementPaieType.
 * L'utilisateur n'a jamais à le remplir manuellement.
 */
public class CalloutMajMouvementPaieType implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        if (value == null) return null;

        int typeId = 0;
        if (value instanceof Integer) typeId = (Integer) value;
        else {
            try { typeId = Integer.parseInt(value.toString()); }
            catch (NumberFormatException e) { return null; }
        }

        if (typeId <= 0) return null;

        MHRMouvementPaieType type = new MHRMouvementPaieType(Env.getCtx(), typeId, null);
        if (type == null || type.getHR_MouvementPaieType_ID() <= 0) return null;

        // Copier IsIndemnite du type vers le mouvement
        String isIndemnite = type.get_Value("IsIndemnite") != null
                ? type.get_Value("IsIndemnite").toString()
                : "N";
        mTab.setValue(MHRMouvementPaie.COLUMNNAME_IsIndemnite, isIndemnite);

        return null;
    }
}
