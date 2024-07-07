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
package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Categorie_Responsabilite
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_Categorie_Responsabilite")
public class X_HR_Categorie_Responsabilite extends PO implements I_HR_Categorie_Responsabilite, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240322L;

    /** Standard Constructor */
    public X_HR_Categorie_Responsabilite (Properties ctx, int HR_Categorie_Responsabilite_ID, String trxName)
    {
      super (ctx, HR_Categorie_Responsabilite_ID, trxName);
      /** if (HR_Categorie_Responsabilite_ID == 0)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Categorie_Responsabilite (Properties ctx, int HR_Categorie_Responsabilite_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Categorie_Responsabilite_ID, trxName, virtualColumns);
      /** if (HR_Categorie_Responsabilite_ID == 0)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Categorie_Responsabilite (Properties ctx, String HR_Categorie_Responsabilite_UU, String trxName)
    {
      super (ctx, HR_Categorie_Responsabilite_UU, trxName);
      /** if (HR_Categorie_Responsabilite_UU == null)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Categorie_Responsabilite (Properties ctx, String HR_Categorie_Responsabilite_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Categorie_Responsabilite_UU, trxName, virtualColumns);
      /** if (HR_Categorie_Responsabilite_UU == null)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Categorie_Responsabilite (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System
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
      StringBuilder sb = new StringBuilder ("X_HR_Categorie_Responsabilite[")
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

	/** Set Catégorie de Responsabilité.
		@param HR_Categorie_Responsabilite_ID Catégorie de Responsabilité
	*/
	public void setHR_Categorie_Responsabilite_ID (int HR_Categorie_Responsabilite_ID)
	{
		if (HR_Categorie_Responsabilite_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Categorie_Responsabilite_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Categorie_Responsabilite_ID, Integer.valueOf(HR_Categorie_Responsabilite_ID));
	}

	/** Get Catégorie de Responsabilité.
		@return Catégorie de Responsabilité	  */
	public int getHR_Categorie_Responsabilite_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Categorie_Responsabilite_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Categorie_Responsabilite_UU.
		@param HR_Categorie_Responsabilite_UU HR_Categorie_Responsabilite_UU
	*/
	public void setHR_Categorie_Responsabilite_UU (String HR_Categorie_Responsabilite_UU)
	{
		set_Value (COLUMNNAME_HR_Categorie_Responsabilite_UU, HR_Categorie_Responsabilite_UU);
	}

	/** Get HR_Categorie_Responsabilite_UU.
		@return HR_Categorie_Responsabilite_UU	  */
	public String getHR_Categorie_Responsabilite_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Categorie_Responsabilite_UU);
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