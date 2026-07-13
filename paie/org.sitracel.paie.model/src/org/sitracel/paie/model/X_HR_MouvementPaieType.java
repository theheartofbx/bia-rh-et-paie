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

/** Generated Model for HR_MouvementPaieType
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_MouvementPaieType")
public class X_HR_MouvementPaieType extends PO implements I_HR_MouvementPaieType, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260713L;

    /** Standard Constructor */
    public X_HR_MouvementPaieType (Properties ctx, int HR_MouvementPaieType_ID, String trxName)
    {
      super (ctx, HR_MouvementPaieType_ID, trxName);
      /** if (HR_MouvementPaieType_ID == 0)
        {
			setHR_MouvementPaieType_ID (0);
			setIsIndemnite (false);
// 'N'
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_MouvementPaieType (Properties ctx, int HR_MouvementPaieType_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_MouvementPaieType_ID, trxName, virtualColumns);
      /** if (HR_MouvementPaieType_ID == 0)
        {
			setHR_MouvementPaieType_ID (0);
			setIsIndemnite (false);
// 'N'
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_MouvementPaieType (Properties ctx, String HR_MouvementPaieType_UU, String trxName)
    {
      super (ctx, HR_MouvementPaieType_UU, trxName);
      /** if (HR_MouvementPaieType_UU == null)
        {
			setHR_MouvementPaieType_ID (0);
			setIsIndemnite (false);
// 'N'
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_MouvementPaieType (Properties ctx, String HR_MouvementPaieType_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_MouvementPaieType_UU, trxName, virtualColumns);
      /** if (HR_MouvementPaieType_UU == null)
        {
			setHR_MouvementPaieType_ID (0);
			setIsIndemnite (false);
// 'N'
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_MouvementPaieType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_MouvementPaieType[")
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

	/** Set Type de Mouvement Paie.
		@param HR_MouvementPaieType_ID Type de Mouvement Paie
	*/
	public void setHR_MouvementPaieType_ID (int HR_MouvementPaieType_ID)
	{
		if (HR_MouvementPaieType_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_MouvementPaieType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_MouvementPaieType_ID, Integer.valueOf(HR_MouvementPaieType_ID));
	}

	/** Get Type de Mouvement Paie.
		@return Type de Mouvement Paie	  */
	public int getHR_MouvementPaieType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_MouvementPaieType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_MouvementPaieType_UU.
		@param HR_MouvementPaieType_UU HR_MouvementPaieType_UU
	*/
	public void setHR_MouvementPaieType_UU (String HR_MouvementPaieType_UU)
	{
		set_Value (COLUMNNAME_HR_MouvementPaieType_UU, HR_MouvementPaieType_UU);
	}

	/** Get HR_MouvementPaieType_UU.
		@return HR_MouvementPaieType_UU	  */
	public String getHR_MouvementPaieType_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_MouvementPaieType_UU);
	}

	/** Set Est une Indemnité.
		@param IsIndemnite Est une Indemnité
	*/
	public void setIsIndemnite (boolean IsIndemnite)
	{
		set_Value (COLUMNNAME_IsIndemnite, Boolean.valueOf(IsIndemnite));
	}

	/** Get Est une Indemnité.
		@return Est une Indemnité	  */
	public boolean isIndemnite()
	{
		Object oo = get_Value(COLUMNNAME_IsIndemnite);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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