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
package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_FormationPlanning
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_FormationPlanning")
public class X_HR_FormationPlanning extends PO implements I_HR_FormationPlanning, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260809L;

    /** Standard Constructor */
    public X_HR_FormationPlanning (Properties ctx, int HR_FormationPlanning_ID, String trxName)
    {
      super (ctx, HR_FormationPlanning_ID, trxName);
      /** if (HR_FormationPlanning_ID == 0)
        {
			setHR_FormationPlanning_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationPlanning (Properties ctx, int HR_FormationPlanning_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationPlanning_ID, trxName, virtualColumns);
      /** if (HR_FormationPlanning_ID == 0)
        {
			setHR_FormationPlanning_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationPlanning (Properties ctx, String HR_FormationPlanning_UU, String trxName)
    {
      super (ctx, HR_FormationPlanning_UU, trxName);
      /** if (HR_FormationPlanning_UU == null)
        {
			setHR_FormationPlanning_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationPlanning (Properties ctx, String HR_FormationPlanning_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationPlanning_UU, trxName, virtualColumns);
      /** if (HR_FormationPlanning_UU == null)
        {
			setHR_FormationPlanning_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_FormationPlanning (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_FormationPlanning[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	/** Set Planning de Formation.
		@param HR_FormationPlanning_ID Planning de Formation
	*/
	public void setHR_FormationPlanning_ID (int HR_FormationPlanning_ID)
	{
		if (HR_FormationPlanning_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanning_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanning_ID, Integer.valueOf(HR_FormationPlanning_ID));
	}

	/** Get Planning de Formation.
		@return Planning de Formation	  */
	public int getHR_FormationPlanning_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationPlanning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_FormationPlanning_UU.
		@param HR_FormationPlanning_UU HR_FormationPlanning_UU
	*/
	public void setHR_FormationPlanning_UU (String HR_FormationPlanning_UU)
	{
		set_Value (COLUMNNAME_HR_FormationPlanning_UU, HR_FormationPlanning_UU);
	}

	/** Get HR_FormationPlanning_UU.
		@return HR_FormationPlanning_UU	  */
	public String getHR_FormationPlanning_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_FormationPlanning_UU);
	}

	public I_HR_FormationSession getHR_FormationSession() throws RuntimeException
	{
		return (I_HR_FormationSession)MTable.get(getCtx(), I_HR_FormationSession.Table_ID)
			.getPO(getHR_FormationSession_ID(), get_TrxName());
	}

	/** Set Session de Formation.
		@param HR_FormationSession_ID Session de Formation
	*/
	public void setHR_FormationSession_ID (int HR_FormationSession_ID)
	{
		if (HR_FormationSession_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationSession_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationSession_ID, Integer.valueOf(HR_FormationSession_ID));
	}

	/** Get Session de Formation.
		@return Session de Formation	  */
	public int getHR_FormationSession_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationSession_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ok.
		@param IsOk Ok
	*/
	public void setIsOk (boolean IsOk)
	{
		set_Value (COLUMNNAME_IsOk, Boolean.valueOf(IsOk));
	}

	/** Get Ok.
		@return Ok	  */
	public boolean isOk()
	{
		Object oo = get_Value(COLUMNNAME_IsOk);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getName());
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