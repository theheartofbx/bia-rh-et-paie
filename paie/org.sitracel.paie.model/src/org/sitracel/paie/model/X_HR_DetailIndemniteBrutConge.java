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

/** Generated Model for HR_DetailIndemniteBrutConge
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_DetailIndemniteBrutConge")
public class X_HR_DetailIndemniteBrutConge extends PO implements I_HR_DetailIndemniteBrutConge, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250823L;

    /** Standard Constructor */
    public X_HR_DetailIndemniteBrutConge (Properties ctx, int HR_DetailIndemniteBrutConge_ID, String trxName)
    {
      super (ctx, HR_DetailIndemniteBrutConge_ID, trxName);
      /** if (HR_DetailIndemniteBrutConge_ID == 0)
        {
			setHR_DetailIndemniteBrutConge_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_DetailIndemniteBrutConge (Properties ctx, int HR_DetailIndemniteBrutConge_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_DetailIndemniteBrutConge_ID, trxName, virtualColumns);
      /** if (HR_DetailIndemniteBrutConge_ID == 0)
        {
			setHR_DetailIndemniteBrutConge_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_DetailIndemniteBrutConge (Properties ctx, String HR_DetailIndemniteBrutConge_UU, String trxName)
    {
      super (ctx, HR_DetailIndemniteBrutConge_UU, trxName);
      /** if (HR_DetailIndemniteBrutConge_UU == null)
        {
			setHR_DetailIndemniteBrutConge_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_DetailIndemniteBrutConge (Properties ctx, String HR_DetailIndemniteBrutConge_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_DetailIndemniteBrutConge_UU, trxName, virtualColumns);
      /** if (HR_DetailIndemniteBrutConge_UU == null)
        {
			setHR_DetailIndemniteBrutConge_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_DetailIndemniteBrutConge (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_DetailIndemniteBrutConge[")
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

	/** Set Détail de l&#039;indemnité Brut de Congé.
		@param HR_DetailIndemniteBrutConge_ID Détail de l&#039;indemnité Brut de Congé
	*/
	@Override
	public void setHR_DetailIndemniteBrutConge_ID (int HR_DetailIndemniteBrutConge_ID)
	{
		if (HR_DetailIndemniteBrutConge_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_DetailIndemniteBrutConge_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_DetailIndemniteBrutConge_ID, Integer.valueOf(HR_DetailIndemniteBrutConge_ID));
		}
	}

	/** Get Détail de l&#039;indemnité Brut de Congé.
		@return Détail de l&#039;indemnité Brut de Congé	  */
	@Override
	public int getHR_DetailIndemniteBrutConge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_DetailIndemniteBrutConge_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_DetailIndemniteBrutConge_UU.
		@param HR_DetailIndemniteBrutConge_UU HR_DetailIndemniteBrutConge_UU
	*/
	@Override
	public void setHR_DetailIndemniteBrutConge_UU (String HR_DetailIndemniteBrutConge_UU)
	{
		set_Value (COLUMNNAME_HR_DetailIndemniteBrutConge_UU, HR_DetailIndemniteBrutConge_UU);
	}

	/** Get HR_DetailIndemniteBrutConge_UU.
		@return HR_DetailIndemniteBrutConge_UU	  */
	@Override
	public String getHR_DetailIndemniteBrutConge_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_DetailIndemniteBrutConge_UU);
	}

	@Override
	public I_HR_Element_Base_Paie getHR_Element_Base_Paie() throws RuntimeException
	{
		return (I_HR_Element_Base_Paie)MTable.get(getCtx(), I_HR_Element_Base_Paie.Table_ID)
			.getPO(getHR_Element_Base_Paie_ID(), get_TrxName());
	}

	/** Set Élément de Paie.
		@param HR_Element_Base_Paie_ID Élément de Paie
	*/
	@Override
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID)
	{
		if (HR_Element_Base_Paie_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, Integer.valueOf(HR_Element_Base_Paie_ID));
		}
	}

	/** Get Élément de Paie.
		@return Élément de Paie	  */
	@Override
	public int getHR_Element_Base_Paie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Element_Base_Paie_ID);
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

	@Override
	public I_HR_Periode_Salariale getHR_Periode_Salariale() throws RuntimeException
	{
		return (I_HR_Periode_Salariale)MTable.get(getCtx(), I_HR_Periode_Salariale.Table_ID)
			.getPO(getHR_Periode_Salariale_ID(), get_TrxName());
	}

	/** Set Période Salariale.
		@param HR_Periode_Salariale_ID Période Salariale
	*/
	@Override
	public void setHR_Periode_Salariale_ID (int HR_Periode_Salariale_ID)
	{
		if (HR_Periode_Salariale_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Periode_Salariale_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Periode_Salariale_ID, Integer.valueOf(HR_Periode_Salariale_ID));
		}
	}

	/** Get Période Salariale.
		@return Période Salariale	  */
	@Override
	public int getHR_Periode_Salariale_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Periode_Salariale_ID);
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