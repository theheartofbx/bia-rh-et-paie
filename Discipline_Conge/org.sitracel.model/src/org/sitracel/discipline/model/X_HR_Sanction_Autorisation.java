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
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;
import org.sitracel.model.I_HR_Categorie_Responsabilite;

/** Generated Model for HR_Sanction_Autorisation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Sanction_Autorisation")
public class X_HR_Sanction_Autorisation extends PO implements I_HR_Sanction_Autorisation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250421L;

    /** Standard Constructor */
    public X_HR_Sanction_Autorisation (Properties ctx, int HR_Sanction_Autorisation_ID, String trxName)
    {
      super (ctx, HR_Sanction_Autorisation_ID, trxName);
      /** if (HR_Sanction_Autorisation_ID == 0)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Sanction_Autorisation_ID (0);
			setHR_TypeSanction_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Sanction_Autorisation (Properties ctx, int HR_Sanction_Autorisation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Sanction_Autorisation_ID, trxName, virtualColumns);
      /** if (HR_Sanction_Autorisation_ID == 0)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Sanction_Autorisation_ID (0);
			setHR_TypeSanction_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Sanction_Autorisation (Properties ctx, String HR_Sanction_Autorisation_UU, String trxName)
    {
      super (ctx, HR_Sanction_Autorisation_UU, trxName);
      /** if (HR_Sanction_Autorisation_UU == null)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Sanction_Autorisation_ID (0);
			setHR_TypeSanction_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Sanction_Autorisation (Properties ctx, String HR_Sanction_Autorisation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Sanction_Autorisation_UU, trxName, virtualColumns);
      /** if (HR_Sanction_Autorisation_UU == null)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Sanction_Autorisation_ID (0);
			setHR_TypeSanction_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Sanction_Autorisation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Sanction_Autorisation[")
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

	@Override
	public I_HR_Categorie_Responsabilite getHR_Categorie_Responsabilite() throws RuntimeException
	{
		return (I_HR_Categorie_Responsabilite)MTable.get(getCtx(), I_HR_Categorie_Responsabilite.Table_ID)
			.getPO(getHR_Categorie_Responsabilite_ID(), get_TrxName());
	}

	/** Set Catégorie de Responsabilité.
		@param HR_Categorie_Responsabilite_ID Catégorie de Responsabilité
	*/
	@Override
	public void setHR_Categorie_Responsabilite_ID (int HR_Categorie_Responsabilite_ID)
	{
		if (HR_Categorie_Responsabilite_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Categorie_Responsabilite_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Categorie_Responsabilite_ID, Integer.valueOf(HR_Categorie_Responsabilite_ID));
		}
	}

	/** Get Catégorie de Responsabilité.
		@return Catégorie de Responsabilité	  */
	@Override
	public int getHR_Categorie_Responsabilite_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Categorie_Responsabilite_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Autorisation Sanction.
		@param HR_Sanction_Autorisation_ID Autorisation Sanction
	*/
	@Override
	public void setHR_Sanction_Autorisation_ID (int HR_Sanction_Autorisation_ID)
	{
		if (HR_Sanction_Autorisation_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Sanction_Autorisation_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Sanction_Autorisation_ID, Integer.valueOf(HR_Sanction_Autorisation_ID));
		}
	}

	/** Get Autorisation Sanction.
		@return Autorisation Sanction	  */
	@Override
	public int getHR_Sanction_Autorisation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Sanction_Autorisation_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Sanction_Autorisation_UU.
		@param HR_Sanction_Autorisation_UU HR_Sanction_Autorisation_UU
	*/
	@Override
	public void setHR_Sanction_Autorisation_UU (String HR_Sanction_Autorisation_UU)
	{
		set_Value (COLUMNNAME_HR_Sanction_Autorisation_UU, HR_Sanction_Autorisation_UU);
	}

	/** Get HR_Sanction_Autorisation_UU.
		@return HR_Sanction_Autorisation_UU	  */
	@Override
	public String getHR_Sanction_Autorisation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Sanction_Autorisation_UU);
	}

	@Override
	public I_HR_TypeSanction getHR_TypeSanction() throws RuntimeException
	{
		return (I_HR_TypeSanction)MTable.get(getCtx(), I_HR_TypeSanction.Table_ID)
			.getPO(getHR_TypeSanction_ID(), get_TrxName());
	}

	/** Set Punishment Type.
		@param HR_TypeSanction_ID Punishment Type
	*/
	@Override
	public void setHR_TypeSanction_ID (int HR_TypeSanction_ID)
	{
		if (HR_TypeSanction_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_TypeSanction_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_TypeSanction_ID, Integer.valueOf(HR_TypeSanction_ID));
		}
	}

	/** Get Punishment Type.
		@return Punishment Type	  */
	@Override
	public int getHR_TypeSanction_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeSanction_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Peut Approuver.
		@param IsApprobation Peut Approuver
	*/
	@Override
	public void setIsApprobation (boolean IsApprobation)
	{
		set_Value (COLUMNNAME_IsApprobation, Boolean.valueOf(IsApprobation));
	}

	/** Get Peut Approuver.
		@return Peut Approuver
	  */
	@Override
	public boolean isApprobation()
	{
		Object oo = get_Value(COLUMNNAME_IsApprobation);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Peut Emettre.
		@param IsEmission Peut Emettre
	*/
	@Override
	public void setIsEmission (boolean IsEmission)
	{
		set_Value (COLUMNNAME_IsEmission, Boolean.valueOf(IsEmission));
	}

	/** Get Peut Emettre.
		@return Peut Emettre
	  */
	@Override
	public boolean isEmission()
	{
		Object oo = get_Value(COLUMNNAME_IsEmission);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Alerte Affiché.
		@param IsMessageAlerteDisplayed Message d&#039;Alerte Affiché
	*/
	@Override
	public void setIsMessageAlerteDisplayed (boolean IsMessageAlerteDisplayed)
	{
		set_Value (COLUMNNAME_IsMessageAlerteDisplayed, Boolean.valueOf(IsMessageAlerteDisplayed));
	}

	/** Get Message d&#039;Alerte Affiché.
		@return Message d&#039;Alerte Affiché
	  */
	@Override
	public boolean isMessageAlerteDisplayed()
	{
		Object oo = get_Value(COLUMNNAME_IsMessageAlerteDisplayed);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Peut Valider.
		@param IsValidation Peut Valider
	*/
	@Override
	public void setIsValidation (boolean IsValidation)
	{
		set_Value (COLUMNNAME_IsValidation, Boolean.valueOf(IsValidation));
	}

	/** Get Peut Valider.
		@return Peut Valider
	  */
	@Override
	public boolean isValidation()
	{
		Object oo = get_Value(COLUMNNAME_IsValidation);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Alerte.
		@param Message_Alerte Message d&#039;Alerte
	*/
	@Override
	public void setMessage_Alerte (String Message_Alerte)
	{
		set_Value (COLUMNNAME_Message_Alerte, Message_Alerte);
	}

	/** Get Message d&#039;Alerte.
		@return Message d&#039;Alerte
	  */
	@Override
	public String getMessage_Alerte()
	{
		return (String)get_Value(COLUMNNAME_Message_Alerte);
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