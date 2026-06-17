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
package org.sitracel.recrutement.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_TypeEtablissement
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_TypeEtablissement")
public class X_HR_TypeEtablissement extends PO implements I_HR_TypeEtablissement, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241201L;

    /** Standard Constructor */
    public X_HR_TypeEtablissement (Properties ctx, int HR_TypeEtablissement_ID, String trxName)
    {
      super (ctx, HR_TypeEtablissement_ID, trxName);
      /** if (HR_TypeEtablissement_ID == 0)
        {
			setHR_TypeEtablissement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeEtablissement (Properties ctx, int HR_TypeEtablissement_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeEtablissement_ID, trxName, virtualColumns);
      /** if (HR_TypeEtablissement_ID == 0)
        {
			setHR_TypeEtablissement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeEtablissement (Properties ctx, String HR_TypeEtablissement_UU, String trxName)
    {
      super (ctx, HR_TypeEtablissement_UU, trxName);
      /** if (HR_TypeEtablissement_UU == null)
        {
			setHR_TypeEtablissement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeEtablissement (Properties ctx, String HR_TypeEtablissement_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeEtablissement_UU, trxName, virtualColumns);
      /** if (HR_TypeEtablissement_UU == null)
        {
			setHR_TypeEtablissement_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_TypeEtablissement (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    @Override
	protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    @Override
	protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    @Override
	public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_TypeEtablissement[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description Optional short description of the record
	*/
	@Override
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	@Override
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Type Etablissement.
		@param HR_TypeEtablissement_ID Type Etablissement
	*/
	@Override
	public void setHR_TypeEtablissement_ID (int HR_TypeEtablissement_ID)
	{
		if (HR_TypeEtablissement_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_TypeEtablissement_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_TypeEtablissement_ID, Integer.valueOf(HR_TypeEtablissement_ID));
		}
	}

	/** Get Type Etablissement.
		@return Type Etablissement	  */
	@Override
	public int getHR_TypeEtablissement_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeEtablissement_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_TypeEtablissement_UU.
		@param HR_TypeEtablissement_UU HR_TypeEtablissement_UU
	*/
	@Override
	public void setHR_TypeEtablissement_UU (String HR_TypeEtablissement_UU)
	{
		set_Value (COLUMNNAME_HR_TypeEtablissement_UU, HR_TypeEtablissement_UU);
	}

	/** Get HR_TypeEtablissement_UU.
		@return HR_TypeEtablissement_UU	  */
	@Override
	public String getHR_TypeEtablissement_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_TypeEtablissement_UU);
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	@Override
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	@Override
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