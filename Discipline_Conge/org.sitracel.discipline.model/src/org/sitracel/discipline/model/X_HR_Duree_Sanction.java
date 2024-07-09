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
package org.sitracel.discipline.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Duree_Sanction
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_Duree_Sanction")
public class X_HR_Duree_Sanction extends PO implements I_HR_Duree_Sanction, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240220L;

    /** Standard Constructor */
    public X_HR_Duree_Sanction (Properties ctx, int HR_Duree_Sanction_ID, String trxName)
    {
      super (ctx, HR_Duree_Sanction_ID, trxName);
      /** if (HR_Duree_Sanction_ID == 0)
        {
			setHR_Duree_Sanction_ID (0);
			setName (null);
			setNombre_De_Jour (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Duree_Sanction (Properties ctx, int HR_Duree_Sanction_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Duree_Sanction_ID, trxName, virtualColumns);
      /** if (HR_Duree_Sanction_ID == 0)
        {
			setHR_Duree_Sanction_ID (0);
			setName (null);
			setNombre_De_Jour (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Duree_Sanction (Properties ctx, String HR_Duree_Sanction_UU, String trxName)
    {
      super (ctx, HR_Duree_Sanction_UU, trxName);
      /** if (HR_Duree_Sanction_UU == null)
        {
			setHR_Duree_Sanction_ID (0);
			setName (null);
			setNombre_De_Jour (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Duree_Sanction (Properties ctx, String HR_Duree_Sanction_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Duree_Sanction_UU, trxName, virtualColumns);
      /** if (HR_Duree_Sanction_UU == null)
        {
			setHR_Duree_Sanction_ID (0);
			setName (null);
			setNombre_De_Jour (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Duree_Sanction (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Duree_Sanction[")
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

	/** Set Durée Sanction.
		@param HR_Duree_Sanction_ID Durée Sanction
	*/
	public void setHR_Duree_Sanction_ID (int HR_Duree_Sanction_ID)
	{
		if (HR_Duree_Sanction_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Duree_Sanction_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Duree_Sanction_ID, Integer.valueOf(HR_Duree_Sanction_ID));
	}

	/** Get Durée Sanction.
		@return Durée Sanction	  */
	public int getHR_Duree_Sanction_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Duree_Sanction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Duree_Sanction_UU.
		@param HR_Duree_Sanction_UU HR_Duree_Sanction_UU
	*/
	public void setHR_Duree_Sanction_UU (String HR_Duree_Sanction_UU)
	{
		set_Value (COLUMNNAME_HR_Duree_Sanction_UU, HR_Duree_Sanction_UU);
	}

	/** Get HR_Duree_Sanction_UU.
		@return HR_Duree_Sanction_UU	  */
	public String getHR_Duree_Sanction_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Duree_Sanction_UU);
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

	/** Set Nombre de Jour.
		@param Nombre_De_Jour Nombre de Jour
	*/
	public void setNombre_De_Jour (int Nombre_De_Jour)
	{
		set_Value (COLUMNNAME_Nombre_De_Jour, Integer.valueOf(Nombre_De_Jour));
	}

	/** Get Nombre de Jour.
		@return Nombre de Jour
	  */
	public int getNombre_De_Jour()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_De_Jour);
		if (ii == null)
			 return 0;
		return ii.intValue();
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