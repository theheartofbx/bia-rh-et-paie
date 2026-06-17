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
package org.sitracel.paie.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.sitracel.conge.model.I_HR_Holiday;

/** Generated Model for HR_Calcul_Conge
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Calcul_Conge")
public class X_HR_Calcul_Conge extends PO implements I_HR_Calcul_Conge, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250512L;

    /** Standard Constructor */
    public X_HR_Calcul_Conge (Properties ctx, int HR_Calcul_Conge_ID, String trxName)
    {
      super (ctx, HR_Calcul_Conge_ID, trxName);
      /** if (HR_Calcul_Conge_ID == 0)
        {
			setHR_Calcul_Conge_ID (0);
			setHR_Element_Conge_ID (0);
			setHR_Holiday_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Calcul_Conge (Properties ctx, int HR_Calcul_Conge_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Calcul_Conge_ID, trxName, virtualColumns);
      /** if (HR_Calcul_Conge_ID == 0)
        {
			setHR_Calcul_Conge_ID (0);
			setHR_Element_Conge_ID (0);
			setHR_Holiday_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Calcul_Conge (Properties ctx, String HR_Calcul_Conge_UU, String trxName)
    {
      super (ctx, HR_Calcul_Conge_UU, trxName);
      /** if (HR_Calcul_Conge_UU == null)
        {
			setHR_Calcul_Conge_ID (0);
			setHR_Element_Conge_ID (0);
			setHR_Holiday_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Calcul_Conge (Properties ctx, String HR_Calcul_Conge_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Calcul_Conge_UU, trxName, virtualColumns);
      /** if (HR_Calcul_Conge_UU == null)
        {
			setHR_Calcul_Conge_ID (0);
			setHR_Element_Conge_ID (0);
			setHR_Holiday_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Calcul_Conge (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Calcul_Conge[")
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
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		} else {
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set Description.
		@param Description Optional short description of the record
	*/
	@Override
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	@Override
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Calcul Congé.
		@param HR_Calcul_Conge_ID Calcul Congé
	*/
	@Override
	public void setHR_Calcul_Conge_ID (int HR_Calcul_Conge_ID)
	{
		if (HR_Calcul_Conge_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Calcul_Conge_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Calcul_Conge_ID, Integer.valueOf(HR_Calcul_Conge_ID));
		}
	}

	/** Get Calcul Congé.
		@return Calcul Congé	  */
	@Override
	public int getHR_Calcul_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Calcul_Conge_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Calcul_Conge_UU.
		@param HR_Calcul_Conge_UU HR_Calcul_Conge_UU
	*/
	@Override
	public void setHR_Calcul_Conge_UU (String HR_Calcul_Conge_UU)
	{
		set_Value (COLUMNNAME_HR_Calcul_Conge_UU, HR_Calcul_Conge_UU);
	}

	/** Get HR_Calcul_Conge_UU.
		@return HR_Calcul_Conge_UU	  */
	@Override
	public String getHR_Calcul_Conge_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Calcul_Conge_UU);
	}

	@Override
	public I_HR_Element_Conge getHR_Element_Conge() throws RuntimeException
	{
		return (I_HR_Element_Conge)MTable.get(getCtx(), I_HR_Element_Conge.Table_ID)
			.getPO(getHR_Element_Conge_ID(), get_TrxName());
	}

	/** Set Élément Base Indemnite Congé.
		@param HR_Element_Conge_ID Élément Base Indemnite Congé
	*/
	@Override
	public void setHR_Element_Conge_ID (int HR_Element_Conge_ID)
	{
		if (HR_Element_Conge_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Conge_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Conge_ID, Integer.valueOf(HR_Element_Conge_ID));
		}
	}

	/** Get Élément Base Indemnite Congé.
		@return Élément Base Indemnite Congé	  */
	@Override
	public int getHR_Element_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Element_Conge_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
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

	/** Set Montant.
		@param Montant Montant
	*/
	@Override
	public void setMontant (BigDecimal Montant)
	{
		set_Value (COLUMNNAME_Montant, Montant);
	}

	/** Get Montant.
		@return Montant
	  */
	@Override
	public BigDecimal getMontant()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}
}