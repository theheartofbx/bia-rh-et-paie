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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_CongeStatut
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_CongeStatut")
public class X_HR_CongeStatut extends PO implements I_HR_CongeStatut, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260708L;

    /** Standard Constructor */
    public X_HR_CongeStatut (Properties ctx, int HR_CongeStatut_ID, String trxName)
    {
      super (ctx, HR_CongeStatut_ID, trxName);
      /** if (HR_CongeStatut_ID == 0)
        {
			setHR_CongeStatut_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_CongeStatut (Properties ctx, int HR_CongeStatut_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_CongeStatut_ID, trxName, virtualColumns);
      /** if (HR_CongeStatut_ID == 0)
        {
			setHR_CongeStatut_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_CongeStatut (Properties ctx, String HR_CongeStatut_UU, String trxName)
    {
      super (ctx, HR_CongeStatut_UU, trxName);
      /** if (HR_CongeStatut_UU == null)
        {
			setHR_CongeStatut_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_CongeStatut (Properties ctx, String HR_CongeStatut_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_CongeStatut_UU, trxName, virtualColumns);
      /** if (HR_CongeStatut_UU == null)
        {
			setHR_CongeStatut_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_CongeStatut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_CongeStatut[")
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

	/** Set Statut des Congés.
		@param HR_CongeStatut_ID Statut des Congés
	*/
	public void setHR_CongeStatut_ID (int HR_CongeStatut_ID)
	{
		if (HR_CongeStatut_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_CongeStatut_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_CongeStatut_ID, Integer.valueOf(HR_CongeStatut_ID));
	}

	/** Get Statut des Congés.
		@return Statut des Congés	  */
	public int getHR_CongeStatut_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_CongeStatut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_CongeStatut_UU.
		@param HR_CongeStatut_UU HR_CongeStatut_UU
	*/
	public void setHR_CongeStatut_UU (String HR_CongeStatut_UU)
	{
		set_Value (COLUMNNAME_HR_CongeStatut_UU, HR_CongeStatut_UU);
	}

	/** Get HR_CongeStatut_UU.
		@return HR_CongeStatut_UU	  */
	public String getHR_CongeStatut_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_CongeStatut_UU);
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