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

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.sitracel.model.I_HR_Categorie_Responsabilite;

/** Generated Model for HR_Autorisation_Absence
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Autorisation_Absence")
public class X_HR_Autorisation_Absence extends PO implements I_HR_Autorisation_Absence, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250421L;

    /** Standard Constructor */
    public X_HR_Autorisation_Absence (Properties ctx, int HR_Autorisation_Absence_ID, String trxName)
    {
      super (ctx, HR_Autorisation_Absence_ID, trxName);
      /** if (HR_Autorisation_Absence_ID == 0)
        {
			setHR_Autorisation_Absence_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Absence_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Autorisation_Absence (Properties ctx, int HR_Autorisation_Absence_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Autorisation_Absence_ID, trxName, virtualColumns);
      /** if (HR_Autorisation_Absence_ID == 0)
        {
			setHR_Autorisation_Absence_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Absence_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Autorisation_Absence (Properties ctx, String HR_Autorisation_Absence_UU, String trxName)
    {
      super (ctx, HR_Autorisation_Absence_UU, trxName);
      /** if (HR_Autorisation_Absence_UU == null)
        {
			setHR_Autorisation_Absence_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Absence_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Autorisation_Absence (Properties ctx, String HR_Autorisation_Absence_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Autorisation_Absence_UU, trxName, virtualColumns);
      /** if (HR_Autorisation_Absence_UU == null)
        {
			setHR_Autorisation_Absence_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Absence_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Autorisation_Absence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Autorisation_Absence[")
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

	/** Set Autorisation Absence.
		@param HR_Autorisation_Absence_ID Autorisation Absence
	*/
	public void setHR_Autorisation_Absence_ID (int HR_Autorisation_Absence_ID)
	{
		if (HR_Autorisation_Absence_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Autorisation_Absence_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Autorisation_Absence_ID, Integer.valueOf(HR_Autorisation_Absence_ID));
	}

	/** Get Autorisation Absence.
		@return Autorisation Absence	  */
	public int getHR_Autorisation_Absence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Autorisation_Absence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Autorisation_Absence_UU.
		@param HR_Autorisation_Absence_UU HR_Autorisation_Absence_UU
	*/
	public void setHR_Autorisation_Absence_UU (String HR_Autorisation_Absence_UU)
	{
		set_Value (COLUMNNAME_HR_Autorisation_Absence_UU, HR_Autorisation_Absence_UU);
	}

	/** Get HR_Autorisation_Absence_UU.
		@return HR_Autorisation_Absence_UU	  */
	public String getHR_Autorisation_Absence_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Autorisation_Absence_UU);
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

	public I_HR_Type_Absence getHR_Type_Absence() throws RuntimeException
	{
		return (I_HR_Type_Absence)MTable.get(getCtx(), I_HR_Type_Absence.Table_ID)
			.getPO(getHR_Type_Absence_ID(), get_TrxName());
	}

	/** Set Type d&#039;Absence.
		@param HR_Type_Absence_ID Type d&#039;Absence
	*/
	public void setHR_Type_Absence_ID (int HR_Type_Absence_ID)
	{
		if (HR_Type_Absence_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Type_Absence_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Type_Absence_ID, Integer.valueOf(HR_Type_Absence_ID));
	}

	/** Get Type d&#039;Absence.
		@return Type d&#039;Absence	  */
	public int getHR_Type_Absence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Absence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Peut Emettre.
		@param IsEmission Peut Emettre
	*/
	public void setIsEmission (boolean IsEmission)
	{
		set_Value (COLUMNNAME_IsEmission, Boolean.valueOf(IsEmission));
	}

	/** Get Peut Emettre.
		@return Peut Emettre
	  */
	public boolean isEmission()
	{
		Object oo = get_Value(COLUMNNAME_IsEmission);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Alerte Affiché.
		@param IsMessageAlerteDisplayed Message d&#039;Alerte Affiché
	*/
	public void setIsMessageAlerteDisplayed (boolean IsMessageAlerteDisplayed)
	{
		set_Value (COLUMNNAME_IsMessageAlerteDisplayed, Boolean.valueOf(IsMessageAlerteDisplayed));
	}

	/** Get Message d&#039;Alerte Affiché.
		@return Message d&#039;Alerte Affiché
	  */
	public boolean isMessageAlerteDisplayed()
	{
		Object oo = get_Value(COLUMNNAME_IsMessageAlerteDisplayed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Alerte.
		@param Message_Alerte Message d&#039;Alerte
	*/
	public void setMessage_Alerte (String Message_Alerte)
	{
		set_Value (COLUMNNAME_Message_Alerte, Message_Alerte);
	}

	/** Get Message d&#039;Alerte.
		@return Message d&#039;Alerte
	  */
	public String getMessage_Alerte()
	{
		return (String)get_Value(COLUMNNAME_Message_Alerte);
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