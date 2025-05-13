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

/** Generated Model for HR_Calcul_Indemnite_Conge
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Calcul_Indemnite_Conge")
public class X_HR_Calcul_Indemnite_Conge extends PO implements I_HR_Calcul_Indemnite_Conge, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250513L;

    /** Standard Constructor */
    public X_HR_Calcul_Indemnite_Conge (Properties ctx, int HR_Calcul_Indemnite_Conge_ID, String trxName)
    {
      super (ctx, HR_Calcul_Indemnite_Conge_ID, trxName);
      /** if (HR_Calcul_Indemnite_Conge_ID == 0)
        {
			setHR_Calcul_Indemnite_Conge_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Calcul_Indemnite_Conge (Properties ctx, int HR_Calcul_Indemnite_Conge_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Calcul_Indemnite_Conge_ID, trxName, virtualColumns);
      /** if (HR_Calcul_Indemnite_Conge_ID == 0)
        {
			setHR_Calcul_Indemnite_Conge_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Calcul_Indemnite_Conge (Properties ctx, String HR_Calcul_Indemnite_Conge_UU, String trxName)
    {
      super (ctx, HR_Calcul_Indemnite_Conge_UU, trxName);
      /** if (HR_Calcul_Indemnite_Conge_UU == null)
        {
			setHR_Calcul_Indemnite_Conge_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Calcul_Indemnite_Conge (Properties ctx, String HR_Calcul_Indemnite_Conge_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Calcul_Indemnite_Conge_UU, trxName, virtualColumns);
      /** if (HR_Calcul_Indemnite_Conge_UU == null)
        {
			setHR_Calcul_Indemnite_Conge_ID (0);
			setHR_Periode_Salariale_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Calcul_Indemnite_Conge (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Calcul_Indemnite_Conge[")
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

	/** Set Calcul Indemnité Congé.
		@param HR_Calcul_Indemnite_Conge_ID Calcul Indemnité Congé
	*/
	public void setHR_Calcul_Indemnite_Conge_ID (int HR_Calcul_Indemnite_Conge_ID)
	{
		if (HR_Calcul_Indemnite_Conge_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Calcul_Indemnite_Conge_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Calcul_Indemnite_Conge_ID, Integer.valueOf(HR_Calcul_Indemnite_Conge_ID));
	}

	/** Get Calcul Indemnité Congé.
		@return Calcul Indemnité Congé	  */
	public int getHR_Calcul_Indemnite_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Calcul_Indemnite_Conge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Calcul_Indemnite_Conge_UU.
		@param HR_Calcul_Indemnite_Conge_UU HR_Calcul_Indemnite_Conge_UU
	*/
	public void setHR_Calcul_Indemnite_Conge_UU (String HR_Calcul_Indemnite_Conge_UU)
	{
		set_Value (COLUMNNAME_HR_Calcul_Indemnite_Conge_UU, HR_Calcul_Indemnite_Conge_UU);
	}

	/** Get HR_Calcul_Indemnite_Conge_UU.
		@return HR_Calcul_Indemnite_Conge_UU	  */
	public String getHR_Calcul_Indemnite_Conge_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Calcul_Indemnite_Conge_UU);
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

	/** Set Salaire Cotisable .
		@param Salaire_Cotisable Salaire Cotisable 
	*/
	public void setSalaire_Cotisable (BigDecimal Salaire_Cotisable)
	{
		set_Value (COLUMNNAME_Salaire_Cotisable, Salaire_Cotisable);
	}

	/** Get Salaire Cotisable .
		@return Salaire Cotisable 
	  */
	public BigDecimal getSalaire_Cotisable()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salaire_Cotisable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}