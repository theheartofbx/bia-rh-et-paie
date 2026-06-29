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

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;
import org.sitracel.organigramme.model.I_HR_Categorie_Responsabilite;

/** Generated Model for HR_TypeValidation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_TypeValidation")
public class X_HR_TypeValidation extends PO implements I_HR_TypeValidation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251224L;

    /** Standard Constructor */
    public X_HR_TypeValidation (Properties ctx, int HR_TypeValidation_ID, String trxName)
    {
      super (ctx, HR_TypeValidation_ID, trxName);
      /** if (HR_TypeValidation_ID == 0)
        {
			setHR_TypeValidation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeValidation (Properties ctx, int HR_TypeValidation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeValidation_ID, trxName, virtualColumns);
      /** if (HR_TypeValidation_ID == 0)
        {
			setHR_TypeValidation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeValidation (Properties ctx, String HR_TypeValidation_UU, String trxName)
    {
      super (ctx, HR_TypeValidation_UU, trxName);
      /** if (HR_TypeValidation_UU == null)
        {
			setHR_TypeValidation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_TypeValidation (Properties ctx, String HR_TypeValidation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_TypeValidation_UU, trxName, virtualColumns);
      /** if (HR_TypeValidation_UU == null)
        {
			setHR_TypeValidation_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_TypeValidation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_TypeValidation[")
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

	public I_HR_Categorie_Responsabilite getHR_Categorie_Responsabilite() throws RuntimeException
	{
		return (I_HR_Categorie_Responsabilite)MTable.get(getCtx(), I_HR_Categorie_Responsabilite.Table_ID)
			.getPO(getHR_Categorie_Responsabilite_ID(), get_TrxName());
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

	/** Set Type de Validation.
		@param HR_TypeValidation_ID Type de Validation
	*/
	public void setHR_TypeValidation_ID (int HR_TypeValidation_ID)
	{
		if (HR_TypeValidation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeValidation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeValidation_ID, Integer.valueOf(HR_TypeValidation_ID));
	}

	/** Get Type de Validation.
		@return Type de Validation	  */
	public int getHR_TypeValidation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeValidation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_TypeValidation_UU.
		@param HR_TypeValidation_UU HR_TypeValidation_UU
	*/
	public void setHR_TypeValidation_UU (String HR_TypeValidation_UU)
	{
		set_Value (COLUMNNAME_HR_TypeValidation_UU, HR_TypeValidation_UU);
	}

	/** Get HR_TypeValidation_UU.
		@return HR_TypeValidation_UU	  */
	public String getHR_TypeValidation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_TypeValidation_UU);
	}

	/** Set Obligatoire.
		@param IsObligatoire Obligatoire
	*/
	public void setIsObligatoire (boolean IsObligatoire)
	{
		set_Value (COLUMNNAME_IsObligatoire, Boolean.valueOf(IsObligatoire));
	}

	/** Get Obligatoire.
		@return Obligatoire	  */
	public boolean isObligatoire()
	{
		Object oo = get_Value(COLUMNNAME_IsObligatoire);
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