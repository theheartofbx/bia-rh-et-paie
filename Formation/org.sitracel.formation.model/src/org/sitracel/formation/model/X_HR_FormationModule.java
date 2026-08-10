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

/** Generated Model for HR_FormationModule
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_FormationModule")
public class X_HR_FormationModule extends PO implements I_HR_FormationModule, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260810L;

    /** Standard Constructor */
    public X_HR_FormationModule (Properties ctx, int HR_FormationModule_ID, String trxName)
    {
      super (ctx, HR_FormationModule_ID, trxName);
      /** if (HR_FormationModule_ID == 0)
        {
			setHR_FormationModule_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationModule (Properties ctx, int HR_FormationModule_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationModule_ID, trxName, virtualColumns);
      /** if (HR_FormationModule_ID == 0)
        {
			setHR_FormationModule_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationModule (Properties ctx, String HR_FormationModule_UU, String trxName)
    {
      super (ctx, HR_FormationModule_UU, trxName);
      /** if (HR_FormationModule_UU == null)
        {
			setHR_FormationModule_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationModule (Properties ctx, String HR_FormationModule_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationModule_UU, trxName, virtualColumns);
      /** if (HR_FormationModule_UU == null)
        {
			setHR_FormationModule_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_FormationModule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_FormationModule[")
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

	/** Set Modules de Formation.
		@param HR_FormationModule_ID Modules de Formation
	*/
	public void setHR_FormationModule_ID (int HR_FormationModule_ID)
	{
		if (HR_FormationModule_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationModule_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationModule_ID, Integer.valueOf(HR_FormationModule_ID));
	}

	/** Get Modules de Formation.
		@return Modules de Formation	  */
	public int getHR_FormationModule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationModule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_FormationModule_UU.
		@param HR_FormationModule_UU HR_FormationModule_UU
	*/
	public void setHR_FormationModule_UU (String HR_FormationModule_UU)
	{
		set_Value (COLUMNNAME_HR_FormationModule_UU, HR_FormationModule_UU);
	}

	/** Get HR_FormationModule_UU.
		@return HR_FormationModule_UU	  */
	public String getHR_FormationModule_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_FormationModule_UU);
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