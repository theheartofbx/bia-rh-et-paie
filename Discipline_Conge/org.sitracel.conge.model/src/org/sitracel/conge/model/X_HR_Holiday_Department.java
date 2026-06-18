/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.sitracel.discipline.model.I_HR_TypeSanction;

/** Generated Model for HR_Holiday_Department
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Holiday_Department")
public class X_HR_Holiday_Department extends PO implements I_HR_Holiday_Department, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250823L;

    /** Standard Constructor */
    public X_HR_Holiday_Department (Properties ctx, int HR_Holiday_Department_ID, String trxName)
    {
      super (ctx, HR_Holiday_Department_ID, trxName);
      /** if (HR_Holiday_Department_ID == 0)
        {
			setHR_Holiday_Department_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Holiday_Department (Properties ctx, int HR_Holiday_Department_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Holiday_Department_ID, trxName, virtualColumns);
      /** if (HR_Holiday_Department_ID == 0)
        {
			setHR_Holiday_Department_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Holiday_Department (Properties ctx, String HR_Holiday_Department_UU, String trxName)
    {
      super (ctx, HR_Holiday_Department_UU, trxName);
      /** if (HR_Holiday_Department_UU == null)
        {
			setHR_Holiday_Department_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Holiday_Department (Properties ctx, String HR_Holiday_Department_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Holiday_Department_UU, trxName, virtualColumns);
      /** if (HR_Holiday_Department_UU == null)
        {
			setHR_Holiday_Department_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Holiday_Department (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    @Override
	protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    @Override
	protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    @Override
	public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_Holiday_Department[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	@Override
	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner .
		@param C_BPartner_ID Identifies a Business Partner
	*/
	@Override
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
		}
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	@Override
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public org.compiere.model.I_C_BPartner getCollegue() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getCollegue_ID(), get_TrxName());
	}

	/** Set Collègue.
		@param Collegue_ID Collègue
	*/
	@Override
	public void setCollegue_ID (int Collegue_ID)
	{
		if (Collegue_ID < 1) {
			set_Value (COLUMNNAME_Collegue_ID, null);
		} else {
			set_Value (COLUMNNAME_Collegue_ID, Integer.valueOf(Collegue_ID));
		}
	}

	/** Get Collègue.
		@return Collègue	  */
	@Override
	public int getCollegue_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Collegue_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	@Override
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	@Override
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
	}

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	@Override
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	@Override
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
	}

	@Override
	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_ID)
			.getPO(getHR_Department_ID(), get_TrxName());
	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department
	*/
	@Override
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) {
			set_Value (COLUMNNAME_HR_Department_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
		}
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	@Override
	public int getHR_Department_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Congé du Département.
		@param HR_Holiday_Department_ID Congé du Département
	*/
	@Override
	public void setHR_Holiday_Department_ID (int HR_Holiday_Department_ID)
	{
		if (HR_Holiday_Department_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_Department_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_Department_ID, Integer.valueOf(HR_Holiday_Department_ID));
		}
	}

	/** Get Congé du Département.
		@return Congé du Département	  */
	@Override
	public int getHR_Holiday_Department_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Holiday_Department_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Holiday_Department_UU.
		@param HR_Holiday_Department_UU HR_Holiday_Department_UU
	*/
	@Override
	public void setHR_Holiday_Department_UU (String HR_Holiday_Department_UU)
	{
		set_Value (COLUMNNAME_HR_Holiday_Department_UU, HR_Holiday_Department_UU);
	}

	/** Get HR_Holiday_Department_UU.
		@return HR_Holiday_Department_UU	  */
	@Override
	public String getHR_Holiday_Department_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Holiday_Department_UU);
	}

	@Override
	public I_HR_Holiday getHR_Holiday() throws RuntimeException
	{
		return (I_HR_Holiday)MTable.get(getCtx(), I_HR_Holiday.Table_ID)
			.getPO(getHR_Holiday_ID(), get_TrxName());
	}

	/** Set Congé.
		@param HR_Holiday_ID Congé
	*/
	@Override
	public void setHR_Holiday_ID (int HR_Holiday_ID)
	{
		if (HR_Holiday_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, Integer.valueOf(HR_Holiday_ID));
		}
	}

	/** Get Congé.
		@return Congé	  */
	@Override
	public int getHR_Holiday_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Holiday_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_TypeSanction getHR_TypeSanction() throws RuntimeException
	{
		return (I_HR_TypeSanction)MTable.get(getCtx(), I_HR_TypeSanction.Table_ID)
			.getPO(getHR_TypeSanction_ID(), get_TrxName());
	}

	/** Set Punishment Type.
		@param HR_TypeSanction_ID Punishment Type
	*/
	@Override
	public void setHR_TypeSanction_ID (int HR_TypeSanction_ID)
	{
		if (HR_TypeSanction_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_TypeSanction_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_TypeSanction_ID, Integer.valueOf(HR_TypeSanction_ID));
		}
	}

	/** Get Punishment Type.
		@return Punishment Type	  */
	@Override
	public int getHR_TypeSanction_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeSanction_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Type_Conge getHR_Type_Conge() throws RuntimeException
	{
		return (I_HR_Type_Conge)MTable.get(getCtx(), I_HR_Type_Conge.Table_ID)
			.getPO(getHR_Type_Conge_ID(), get_TrxName());
	}

	/** Set Type de Congé.
		@param HR_Type_Conge_ID Type de Congé
	*/
	@Override
	public void setHR_Type_Conge_ID (int HR_Type_Conge_ID)
	{
		if (HR_Type_Conge_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Type_Conge_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Type_Conge_ID, Integer.valueOf(HR_Type_Conge_ID));
		}
	}

	/** Get Type de Congé.
		@return Type de Congé	  */
	@Override
	public int getHR_Type_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Conge_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour.
		@param Nombre_Employe_Departement_Hol Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour
	*/
	@Override
	public void setNombre_Employe_Departement_Hol (int Nombre_Employe_Departement_Hol)
	{
		set_Value (COLUMNNAME_Nombre_Employe_Departement_Hol, Integer.valueOf(Nombre_Employe_Departement_Hol));
	}

	/** Get Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour.
		@return Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour
	  */
	@Override
	public int getNombre_Employe_Departement_Hol()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Employe_Departement_Hol);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}
}