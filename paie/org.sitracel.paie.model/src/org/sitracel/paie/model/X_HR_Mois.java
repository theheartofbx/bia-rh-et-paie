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

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Mois
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mois")
public class X_HR_Mois extends PO implements I_HR_Mois, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250823L;

    /** Standard Constructor */
    public X_HR_Mois (Properties ctx, int HR_Mois_ID, String trxName)
    {
      super (ctx, HR_Mois_ID, trxName);
      /** if (HR_Mois_ID == 0)
        {
			setHR_Mois_ID (0);
			setName (null);
			setValeur_Integer (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mois (Properties ctx, int HR_Mois_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mois_ID, trxName, virtualColumns);
      /** if (HR_Mois_ID == 0)
        {
			setHR_Mois_ID (0);
			setName (null);
			setValeur_Integer (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mois (Properties ctx, String HR_Mois_UU, String trxName)
    {
      super (ctx, HR_Mois_UU, trxName);
      /** if (HR_Mois_UU == null)
        {
			setHR_Mois_ID (0);
			setName (null);
			setValeur_Integer (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mois (Properties ctx, String HR_Mois_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mois_UU, trxName, virtualColumns);
      /** if (HR_Mois_UU == null)
        {
			setHR_Mois_ID (0);
			setName (null);
			setValeur_Integer (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Mois (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Mois[")
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

	/** Set Mois.
		@param HR_Mois_ID Mois
	*/
	@Override
	public void setHR_Mois_ID (int HR_Mois_ID)
	{
		if (HR_Mois_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Mois_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Mois_ID, Integer.valueOf(HR_Mois_ID));
		}
	}

	/** Get Mois.
		@return Mois	  */
	@Override
	public int getHR_Mois_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mois_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Mois_UU.
		@param HR_Mois_UU HR_Mois_UU
	*/
	@Override
	public void setHR_Mois_UU (String HR_Mois_UU)
	{
		set_Value (COLUMNNAME_HR_Mois_UU, HR_Mois_UU);
	}

	/** Get HR_Mois_UU.
		@return HR_Mois_UU	  */
	@Override
	public String getHR_Mois_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mois_UU);
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

	/** Set Valeur.
		@param Valeur_Integer Valeur
	*/
	@Override
	public void setValeur_Integer (int Valeur_Integer)
	{
		set_Value (COLUMNNAME_Valeur_Integer, Integer.valueOf(Valeur_Integer));
	}

	/** Get Valeur.
		@return Valeur
	  */
	@Override
	public int getValeur_Integer()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valeur_Integer);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}
}