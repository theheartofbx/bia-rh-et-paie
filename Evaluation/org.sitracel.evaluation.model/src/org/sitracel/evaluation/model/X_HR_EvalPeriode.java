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
package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_EvalPeriode
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_EvalPeriode")
public class X_HR_EvalPeriode extends PO implements I_HR_EvalPeriode, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260820L;

    /** Standard Constructor */
    public X_HR_EvalPeriode (Properties ctx, int HR_EvalPeriode_ID, String trxName)
    {
      super (ctx, HR_EvalPeriode_ID, trxName);
      /** if (HR_EvalPeriode_ID == 0)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setHR_EvalPeriode_ID (0);
			setIsCloturee (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalPeriode (Properties ctx, int HR_EvalPeriode_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalPeriode_ID, trxName, virtualColumns);
      /** if (HR_EvalPeriode_ID == 0)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setHR_EvalPeriode_ID (0);
			setIsCloturee (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalPeriode (Properties ctx, String HR_EvalPeriode_UU, String trxName)
    {
      super (ctx, HR_EvalPeriode_UU, trxName);
      /** if (HR_EvalPeriode_UU == null)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setHR_EvalPeriode_ID (0);
			setIsCloturee (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalPeriode (Properties ctx, String HR_EvalPeriode_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalPeriode_UU, trxName, virtualColumns);
      /** if (HR_EvalPeriode_UU == null)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setHR_EvalPeriode_ID (0);
			setIsCloturee (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HR_EvalPeriode (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EvalPeriode[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
	}

	/** Set Date Limite.
		@param Date_Limite Date Limite
	*/
	public void setDate_Limite (Timestamp Date_Limite)
	{
		set_Value (COLUMNNAME_Date_Limite, Date_Limite);
	}

	/** Get Date Limite.
		@return Date Limite	  */
	public Timestamp getDate_Limite()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Limite);
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

	/** Set Période d&#039;Évaluation.
		@param HR_EvalPeriode_ID Période d&#039;Évaluation
	*/
	public void setHR_EvalPeriode_ID (int HR_EvalPeriode_ID)
	{
		if (HR_EvalPeriode_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalPeriode_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalPeriode_ID, Integer.valueOf(HR_EvalPeriode_ID));
	}

	/** Get Période d&#039;Évaluation.
		@return Période d&#039;Évaluation	  */
	public int getHR_EvalPeriode_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalPeriode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_EvalPeriode_UU.
		@param HR_EvalPeriode_UU HR_EvalPeriode_UU
	*/
	public void setHR_EvalPeriode_UU (String HR_EvalPeriode_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_EvalPeriode_UU, HR_EvalPeriode_UU);
	}

	/** Get HR_EvalPeriode_UU.
		@return HR_EvalPeriode_UU	  */
	public String getHR_EvalPeriode_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EvalPeriode_UU);
	}

	/** Set IsCloturee.
		@param IsCloturee IsCloturee
	*/
	public void setIsCloturee (boolean IsCloturee)
	{
		set_Value (COLUMNNAME_IsCloturee, Boolean.valueOf(IsCloturee));
	}

	/** Get IsCloturee.
		@return IsCloturee	  */
	public boolean isCloturee()
	{
		Object oo = get_Value(COLUMNNAME_IsCloturee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}