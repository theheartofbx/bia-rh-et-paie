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
package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for NiveauEtude
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="NiveauEtude")
public class X_NiveauEtude extends PO implements I_NiveauEtude, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240220L;

    /** Standard Constructor */
    public X_NiveauEtude (Properties ctx, int NiveauEtude_ID, String trxName)
    {
      super (ctx, NiveauEtude_ID, trxName);
      /** if (NiveauEtude_ID == 0)
        {
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_NiveauEtude (Properties ctx, int NiveauEtude_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, NiveauEtude_ID, trxName, virtualColumns);
      /** if (NiveauEtude_ID == 0)
        {
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_NiveauEtude (Properties ctx, String NiveauEtude_UU, String trxName)
    {
      super (ctx, NiveauEtude_UU, trxName);
      /** if (NiveauEtude_UU == null)
        {
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_NiveauEtude (Properties ctx, String NiveauEtude_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, NiveauEtude_UU, trxName, virtualColumns);
      /** if (NiveauEtude_UU == null)
        {
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Load Constructor */
    public X_NiveauEtude (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_NiveauEtude[")
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

	/** Set Niveau D&#039;Etude.
		@param NiveauEtude_ID Niveau D&#039;Etude
	*/
	public void setNiveauEtude_ID (int NiveauEtude_ID)
	{
		if (NiveauEtude_ID < 1)
			set_ValueNoCheck (COLUMNNAME_NiveauEtude_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_NiveauEtude_ID, Integer.valueOf(NiveauEtude_ID));
	}

	/** Get Niveau D&#039;Etude.
		@return Niveau D&#039;Etude	  */
	public int getNiveauEtude_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NiveauEtude_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set NiveauEtude_UU.
		@param NiveauEtude_UU NiveauEtude_UU
	*/
	public void setNiveauEtude_UU (String NiveauEtude_UU)
	{
		set_Value (COLUMNNAME_NiveauEtude_UU, NiveauEtude_UU);
	}

	/** Get NiveauEtude_UU.
		@return NiveauEtude_UU	  */
	public String getNiveauEtude_UU()
	{
		return (String)get_Value(COLUMNNAME_NiveauEtude_UU);
	}
}