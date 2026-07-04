package org.sitracel.contrat.modelvalidator.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.contrat.model.I_HR_Affectation;
import org.sitracel.contrat.model.I_HR_Contrat;
import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.contrat.model.MHRContrat;

/**
 * Accès SQL pour le ModelValidator du module Contrat.
 * Aucune logique de décision ici : on se contente de récupérer des
 * données. La décision (bloquer ou non) est dans ContratValidatorService.
 */
public class ContratValidatorRepository {

    private static final CLogger log = CLogger.getCLogger(ContratValidatorRepository.class);

    /**
     * Retourne les autres contrats actifs du même employé, en excluant
     * le contrat en cours de création/modification (son propre ID).
     */
    public static List<MHRContrat> getAutresContrats(int bpartnerId, int excludeContratId, String trxName) {
        List<MHRContrat> resultat = new ArrayList<>();
        if (bpartnerId <= 0) {
            return resultat;
        }

        String sql = "SELECT * FROM " + I_HR_Contrat.Table_Name
            + " WHERE " + I_HR_Contrat.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Contrat.COLUMNNAME_HR_Contrat_ID + " <> ?"
            + " AND IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, excludeContratId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRContrat(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.warning("getAutresContrats : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    /**
     * Retourne les autres affectations actives du même employé, en
     * excluant l'affectation en cours de création/modification.
     */
    public static List<MHRAffectation> getAutresAffectations(int bpartnerId, int excludeAffectationId, String trxName) {
        List<MHRAffectation> resultat = new ArrayList<>();
        if (bpartnerId <= 0) {
            return resultat;
        }

        String sql = "SELECT * FROM " + I_HR_Affectation.Table_Name
            + " WHERE " + I_HR_Affectation.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_Affectation.COLUMNNAME_HR_Affectation_ID + " <> ?"
            + " AND IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, excludeAffectationId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRAffectation(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.warning("getAutresAffectations : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }
}
