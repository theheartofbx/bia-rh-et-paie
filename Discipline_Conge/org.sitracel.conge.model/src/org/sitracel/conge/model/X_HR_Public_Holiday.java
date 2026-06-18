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
package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for HR_Public_Holiday
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Public_Holiday")
public class X_HR_Public_Holiday extends PO implements I_HR_Public_Holiday, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250421L;

    /** Standard Constructor */
    public X_HR_Public_Holiday (Properties ctx, int HR_Public_Holiday_ID, String trxName)
    {
      super (ctx, HR_Public_Holiday_ID, trxName);
      /** if (HR_Public_Holiday_ID == 0)
        {
			setHR_Public_Holiday_ID (0);
			setNom_Jour_Ferie (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Public_Holiday (Properties ctx, int HR_Public_Holiday_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Public_Holiday_ID, trxName, virtualColumns);
      /** if (HR_Public_Holiday_ID == 0)
        {
			setHR_Public_Holiday_ID (0);
			setNom_Jour_Ferie (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Public_Holiday (Properties ctx, String HR_Public_Holiday_UU, String trxName)
    {
      super (ctx, HR_Public_Holiday_UU, trxName);
      /** if (HR_Public_Holiday_UU == null)
        {
			setHR_Public_Holiday_ID (0);
			setNom_Jour_Ferie (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Public_Holiday (Properties ctx, String HR_Public_Holiday_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Public_Holiday_UU, trxName, virtualColumns);
      /** if (HR_Public_Holiday_UU == null)
        {
			setHR_Public_Holiday_ID (0);
			setNom_Jour_Ferie (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Public_Holiday (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Public_Holiday[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date du Jour Férié.
		@param Date_Jour_Ferie Date du Jour Férié
	*/
	@Override
	public void setDate_Jour_Ferie (Timestamp Date_Jour_Ferie)
	{
		set_Value (COLUMNNAME_Date_Jour_Ferie, Date_Jour_Ferie);
	}

	/** Get Date du Jour Férié.
		@return Date du Jour Férié
	  */
	@Override
	public Timestamp getDate_Jour_Ferie()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Jour_Ferie);
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

	/** Set Jours Fériés.
		@param HR_Public_Holiday_ID Jours Fériés
	*/
	@Override
	public void setHR_Public_Holiday_ID (int HR_Public_Holiday_ID)
	{
		if (HR_Public_Holiday_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Public_Holiday_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Public_Holiday_ID, Integer.valueOf(HR_Public_Holiday_ID));
		}
	}

	/** Get Jours Fériés.
		@return Jours Fériés	  */
	@Override
	public int getHR_Public_Holiday_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Public_Holiday_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Public_Holiday_UU.
		@param HR_Public_Holiday_UU HR_Public_Holiday_UU
	*/
	@Override
	public void setHR_Public_Holiday_UU (String HR_Public_Holiday_UU)
	{
		set_Value (COLUMNNAME_HR_Public_Holiday_UU, HR_Public_Holiday_UU);
	}

	/** Get HR_Public_Holiday_UU.
		@return HR_Public_Holiday_UU	  */
	@Override
	public String getHR_Public_Holiday_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Public_Holiday_UU);
	}

	/** Set Nom du Jour Férié.
		@param Nom_Jour_Ferie Nom du Jour Férié
	*/
	@Override
	public void setNom_Jour_Ferie (String Nom_Jour_Ferie)
	{
		set_Value (COLUMNNAME_Nom_Jour_Ferie, Nom_Jour_Ferie);
	}

	/** Get Nom du Jour Férié.
		@return Nom du Jour Férié
	  */
	@Override
	public String getNom_Jour_Ferie()
	{
		return (String)get_Value(COLUMNNAME_Nom_Jour_Ferie);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	@Override
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	@Override
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}