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
package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_StageEtatObjectif
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_StageEtatObjectif")
public class X_HR_StageEtatObjectif extends PO implements I_HR_StageEtatObjectif, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260813L;

    /** Standard Constructor */
    public X_HR_StageEtatObjectif (Properties ctx, int HR_StageEtatObjectif_ID, String trxName)
    {
      super (ctx, HR_StageEtatObjectif_ID, trxName);
      /** if (HR_StageEtatObjectif_ID == 0)
        {
			setHR_StageEtatObjectif_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageEtatObjectif (Properties ctx, int HR_StageEtatObjectif_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_StageEtatObjectif_ID, trxName, virtualColumns);
      /** if (HR_StageEtatObjectif_ID == 0)
        {
			setHR_StageEtatObjectif_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageEtatObjectif (Properties ctx, String HR_StageEtatObjectif_UU, String trxName)
    {
      super (ctx, HR_StageEtatObjectif_UU, trxName);
      /** if (HR_StageEtatObjectif_UU == null)
        {
			setHR_StageEtatObjectif_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageEtatObjectif (Properties ctx, String HR_StageEtatObjectif_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_StageEtatObjectif_UU, trxName, virtualColumns);
      /** if (HR_StageEtatObjectif_UU == null)
        {
			setHR_StageEtatObjectif_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HR_StageEtatObjectif (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_StageEtatObjectif[")
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

	/** Set État Objectif Stage.
		@param HR_StageEtatObjectif_ID État Objectif Stage
	*/
	public void setHR_StageEtatObjectif_ID (int HR_StageEtatObjectif_ID)
	{
		if (HR_StageEtatObjectif_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_StageEtatObjectif_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_StageEtatObjectif_ID, Integer.valueOf(HR_StageEtatObjectif_ID));
	}

	/** Get État Objectif Stage.
		@return État Objectif Stage	  */
	public int getHR_StageEtatObjectif_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_StageEtatObjectif_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_StageEtatObjectif_UU.
		@param HR_StageEtatObjectif_UU HR_StageEtatObjectif_UU
	*/
	public void setHR_StageEtatObjectif_UU (String HR_StageEtatObjectif_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_StageEtatObjectif_UU, HR_StageEtatObjectif_UU);
	}

	/** Get HR_StageEtatObjectif_UU.
		@return HR_StageEtatObjectif_UU	  */
	public String getHR_StageEtatObjectif_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_StageEtatObjectif_UU);
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