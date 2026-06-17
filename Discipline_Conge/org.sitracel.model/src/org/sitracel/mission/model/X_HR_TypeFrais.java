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
package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_TypeFrais
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_TypeFrais")
public class X_HR_TypeFrais extends PO implements I_HR_TypeFrais, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251211L;

    /** Standard Constructor */
    public X_HR_TypeFrais (Properties ctx, int HR_TypeFrais_ID, String trxName)
    {
      super (ctx, HR_TypeFrais_ID, trxName);
      /** if (HR_TypeFrais_ID == 0)
        {
			setHR_TypeFrais_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeFrais (Properties ctx, int HR_TypeFrais_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeFrais_ID, trxName, virtualColumns);
      /** if (HR_TypeFrais_ID == 0)
        {
			setHR_TypeFrais_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeFrais (Properties ctx, String HR_TypeFrais_UU, String trxName)
    {
      super (ctx, HR_TypeFrais_UU, trxName);
      /** if (HR_TypeFrais_UU == null)
        {
			setHR_TypeFrais_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeFrais (Properties ctx, String HR_TypeFrais_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeFrais_UU, trxName, virtualColumns);
      /** if (HR_TypeFrais_UU == null)
        {
			setHR_TypeFrais_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_TypeFrais (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_TypeFrais[")
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

	/** Set Type de Frais .
		@param HR_TypeFrais_ID Type de Frais 
	*/
	public void setHR_TypeFrais_ID (int HR_TypeFrais_ID)
	{
		if (HR_TypeFrais_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeFrais_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeFrais_ID, Integer.valueOf(HR_TypeFrais_ID));
	}

	/** Get Type de Frais .
		@return Type de Frais 	  */
	public int getHR_TypeFrais_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeFrais_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_TypeFrais_UU.
		@param HR_TypeFrais_UU HR_TypeFrais_UU
	*/
	public void setHR_TypeFrais_UU (String HR_TypeFrais_UU)
	{
		set_Value (COLUMNNAME_HR_TypeFrais_UU, HR_TypeFrais_UU);
	}

	/** Get HR_TypeFrais_UU.
		@return HR_TypeFrais_UU	  */
	public String getHR_TypeFrais_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_TypeFrais_UU);
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
}