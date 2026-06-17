package org.sitracel.conge.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.bean.BeanConge;
import org.sitracel.model.I_C_BPartner;

public class MHREmployeeChildren extends X_HR_Employee_Children{
	private static final long serialVersionUID = 7448052037738596413L;

	private static CLogger log = CLogger.get();

	public MHREmployeeChildren(Properties ctx, int HR_Employee_Children_ID, String trxName) {
		super(ctx, HR_Employee_Children_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHREmployeeChildren(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static BeanConge getEnfantMoins6(Integer bpartnerID, Timestamp dateActuelle, BeanConge beanConge, String trxName) {
		if(bpartnerID!=null && dateActuelle!=null && beanConge!=null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateActuelle);
			cal.add(Calendar.YEAR, -6);
			Timestamp moinsDeSixAns = new Timestamp(cal.getTime().getTime());
			StringBuilder sql = new StringBuilder("SELECT bp."+I_C_BPartner.COLUMNNAME_Sex
					//+", bp."+MCBPartner.COLUMNNAME_DateFrom
					+", child."+I_HR_Employee_Children.COLUMNNAME_Date_Naissance
					+" FROM "+I_C_BPartner.Table_Name+" bp"
					+" LEFT JOIN "+I_HR_Employee_Children.Table_Name+" child"
						+" ON child."+I_HR_Employee_Children.COLUMNNAME_C_BPartner_ID+"=bp."+I_C_BPartner.COLUMNNAME_C_BPartner_ID
						+" AND child."+I_HR_Employee_Children.COLUMNNAME_Date_Naissance+">?"
					+" WHERE bp."+I_C_BPartner.COLUMNNAME_C_BPartner_ID+"=?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setTimestamp(1, moinsDeSixAns);
				pstmt.setInt(2, bpartnerID);
				rs = pstmt.executeQuery();
				int in = 0;
				List<Integer> list = new ArrayList<>();
				while (rs.next()) {
					if(in==0) {
						beanConge.setGenre(rs.getString(I_C_BPartner.COLUMNNAME_Sex));
					}
					if(rs.getTimestamp(I_HR_Employee_Children.COLUMNNAME_Date_Naissance)!=null) {
						cal.setTime(rs.getTimestamp(I_HR_Employee_Children.COLUMNNAME_Date_Naissance));
						list.add(cal.get(Calendar.YEAR));
					}
					in++;
				}
				beanConge.setNombreEnfantPetit(list.size());
				beanConge.setAnneeNaissance(list);
			}
			catch (SQLException e)
			{
				log.warning(e.getMessage());
				e.printStackTrace();
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		return beanConge;
	}

}
