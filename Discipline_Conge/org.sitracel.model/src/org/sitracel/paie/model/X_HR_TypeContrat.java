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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_TypeContrat
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_TypeContrat")
public class X_HR_TypeContrat extends PO implements I_HR_TypeContrat, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250811L;

    /** Standard Constructor */
    public X_HR_TypeContrat (Properties ctx, int HR_TypeContrat_ID, String trxName)
    {
      super (ctx, HR_TypeContrat_ID, trxName);
      /** if (HR_TypeContrat_ID == 0)
        {
			setHR_TypeContrat_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeContrat (Properties ctx, int HR_TypeContrat_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeContrat_ID, trxName, virtualColumns);
      /** if (HR_TypeContrat_ID == 0)
        {
			setHR_TypeContrat_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeContrat (Properties ctx, String HR_TypeContrat_UU, String trxName)
    {
      super (ctx, HR_TypeContrat_UU, trxName);
      /** if (HR_TypeContrat_UU == null)
        {
			setHR_TypeContrat_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeContrat (Properties ctx, String HR_TypeContrat_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeContrat_UU, trxName, virtualColumns);
      /** if (HR_TypeContrat_UU == null)
        {
			setHR_TypeContrat_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_TypeContrat (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_TypeContrat[")
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

	/** Set Type de Contrat.
		@param HR_TypeContrat_ID Type de Contrat
	*/
	public void setHR_TypeContrat_ID (int HR_TypeContrat_ID)
	{
		if (HR_TypeContrat_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeContrat_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeContrat_ID, Integer.valueOf(HR_TypeContrat_ID));
	}

	/** Get Type de Contrat.
		@return Type de Contrat	  */
	public int getHR_TypeContrat_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeContrat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_TypeContrat_UU.
		@param HR_TypeContrat_UU HR_TypeContrat_UU
	*/
	public void setHR_TypeContrat_UU (String HR_TypeContrat_UU)
	{
		set_Value (COLUMNNAME_HR_TypeContrat_UU, HR_TypeContrat_UU);
	}

	/** Get HR_TypeContrat_UU.
		@return HR_TypeContrat_UU	  */
	public String getHR_TypeContrat_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_TypeContrat_UU);
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