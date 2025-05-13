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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_Historique_Paie
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Historique_Paie")
public class X_HR_Historique_Paie extends PO implements I_HR_Historique_Paie, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250513L;

    /** Standard Constructor */
    public X_HR_Historique_Paie (Properties ctx, int HR_Historique_Paie_ID, String trxName)
    {
      super (ctx, HR_Historique_Paie_ID, trxName);
      /** if (HR_Historique_Paie_ID == 0)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_Historique_Paie_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Historique_Paie (Properties ctx, int HR_Historique_Paie_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Historique_Paie_ID, trxName, virtualColumns);
      /** if (HR_Historique_Paie_ID == 0)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_Historique_Paie_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Historique_Paie (Properties ctx, String HR_Historique_Paie_UU, String trxName)
    {
      super (ctx, HR_Historique_Paie_UU, trxName);
      /** if (HR_Historique_Paie_UU == null)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_Historique_Paie_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Historique_Paie (Properties ctx, String HR_Historique_Paie_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Historique_Paie_UU, trxName, virtualColumns);
      /** if (HR_Historique_Paie_UU == null)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_Historique_Paie_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Historique_Paie (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_Historique_Paie[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner .
		@param C_BPartner_ID Identifies a Business Partner
	*/
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public I_HR_Element_Base_Paie getHR_Element_Base_Paie() throws RuntimeException
	{
		return (I_HR_Element_Base_Paie)MTable.get(getCtx(), I_HR_Element_Base_Paie.Table_ID)
			.getPO(getHR_Element_Base_Paie_ID(), get_TrxName());
	}

	/** Set Élément de Paie.
		@param HR_Element_Base_Paie_ID Élément de Paie
	*/
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID)
	{
		if (HR_Element_Base_Paie_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, Integer.valueOf(HR_Element_Base_Paie_ID));
	}

	/** Get Élément de Paie.
		@return Élément de Paie	  */
	public int getHR_Element_Base_Paie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Element_Base_Paie_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Historique Paie.
		@param HR_Historique_Paie_ID Historique Paie
	*/
	public void setHR_Historique_Paie_ID (int HR_Historique_Paie_ID)
	{
		if (HR_Historique_Paie_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Historique_Paie_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Historique_Paie_ID, Integer.valueOf(HR_Historique_Paie_ID));
	}

	/** Get Historique Paie.
		@return Historique Paie	  */
	public int getHR_Historique_Paie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Historique_Paie_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Historique_Paie_UU.
		@param HR_Historique_Paie_UU HR_Historique_Paie_UU
	*/
	public void setHR_Historique_Paie_UU (String HR_Historique_Paie_UU)
	{
		set_Value (COLUMNNAME_HR_Historique_Paie_UU, HR_Historique_Paie_UU);
	}

	/** Get HR_Historique_Paie_UU.
		@return HR_Historique_Paie_UU	  */
	public String getHR_Historique_Paie_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Historique_Paie_UU);
	}

	public I_HR_Periode_Salariale getHR_Periode_Salariale() throws RuntimeException
	{
		return (I_HR_Periode_Salariale)MTable.get(getCtx(), I_HR_Periode_Salariale.Table_ID)
			.getPO(getHR_Periode_Salariale_ID(), get_TrxName());
	}

	/** Set Période Salariale.
		@param HR_Periode_Salariale_ID Période Salariale
	*/
	public void setHR_Periode_Salariale_ID (int HR_Periode_Salariale_ID)
	{
		if (HR_Periode_Salariale_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Periode_Salariale_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Periode_Salariale_ID, Integer.valueOf(HR_Periode_Salariale_ID));
	}

	/** Get Période Salariale.
		@return Période Salariale	  */
	public int getHR_Periode_Salariale_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Periode_Salariale_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Montant.
		@param Montant Montant
	*/
	public void setMontant (BigDecimal Montant)
	{
		set_Value (COLUMNNAME_Montant, Montant);
	}

	/** Get Montant.
		@return Montant
	  */
	public BigDecimal getMontant()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}