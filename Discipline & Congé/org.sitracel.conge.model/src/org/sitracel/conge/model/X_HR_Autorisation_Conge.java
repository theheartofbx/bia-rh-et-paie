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
import org.sitracel.model.I_HR_Categorie_Responsabilite;

/** Generated Model for HR_Autorisation_Conge
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_Autorisation_Conge")
public class X_HR_Autorisation_Conge extends PO implements I_HR_Autorisation_Conge, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240418L;

    /** Standard Constructor */
    public X_HR_Autorisation_Conge (Properties ctx, int HR_Autorisation_Conge_ID, String trxName)
    {
      super (ctx, HR_Autorisation_Conge_ID, trxName);
      /** if (HR_Autorisation_Conge_ID == 0)
        {
			setHR_Autorisation_Conge_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Conge_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Autorisation_Conge (Properties ctx, int HR_Autorisation_Conge_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Autorisation_Conge_ID, trxName, virtualColumns);
      /** if (HR_Autorisation_Conge_ID == 0)
        {
			setHR_Autorisation_Conge_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Conge_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Autorisation_Conge (Properties ctx, String HR_Autorisation_Conge_UU, String trxName)
    {
      super (ctx, HR_Autorisation_Conge_UU, trxName);
      /** if (HR_Autorisation_Conge_UU == null)
        {
			setHR_Autorisation_Conge_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Conge_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Autorisation_Conge (Properties ctx, String HR_Autorisation_Conge_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Autorisation_Conge_UU, trxName, virtualColumns);
      /** if (HR_Autorisation_Conge_UU == null)
        {
			setHR_Autorisation_Conge_ID (0);
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Type_Conge_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Autorisation_Conge (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Autorisation_Conge[")
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

	/** Femme = F */
	public static final String GENRE_EMPLOYE_Femme = "F";
	/** Homme = H */
	public static final String GENRE_EMPLOYE_Homme = "H";
	/** Mixte = M */
	public static final String GENRE_EMPLOYE_Mixte = "M";
	/** Set Genre de l&#039;Employé(e) Concerné(e).
		@param Genre_Employe Genre de l&#039;Employé(e) Concerné(e)
	*/
	public void setGenre_Employe (String Genre_Employe)
	{

		set_Value (COLUMNNAME_Genre_Employe, Genre_Employe);
	}

	/** Get Genre de l&#039;Employé(e) Concerné(e).
		@return Genre de l&#039;Employé(e) Concerné(e)
	  */
	public String getGenre_Employe()
	{
		return (String)get_Value(COLUMNNAME_Genre_Employe);
	}

	/** Set Autorisation Congé.
		@param HR_Autorisation_Conge_ID Autorisation Congé
	*/
	public void setHR_Autorisation_Conge_ID (int HR_Autorisation_Conge_ID)
	{
		if (HR_Autorisation_Conge_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Autorisation_Conge_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Autorisation_Conge_ID, Integer.valueOf(HR_Autorisation_Conge_ID));
	}

	/** Get Autorisation Congé.
		@return Autorisation Congé	  */
	public int getHR_Autorisation_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Autorisation_Conge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Autorisation_Conge_UU.
		@param HR_Autorisation_Conge_UU HR_Autorisation_Conge_UU
	*/
	public void setHR_Autorisation_Conge_UU (String HR_Autorisation_Conge_UU)
	{
		set_Value (COLUMNNAME_HR_Autorisation_Conge_UU, HR_Autorisation_Conge_UU);
	}

	/** Get HR_Autorisation_Conge_UU.
		@return HR_Autorisation_Conge_UU	  */
	public String getHR_Autorisation_Conge_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Autorisation_Conge_UU);
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

	public I_HR_Type_Conge getHR_Type_Conge() throws RuntimeException
	{
		return (I_HR_Type_Conge)MTable.get(getCtx(), I_HR_Type_Conge.Table_ID)
			.getPO(getHR_Type_Conge_ID(), get_TrxName());
	}

	/** Set Type de Congé.
		@param HR_Type_Conge_ID Type de Congé
	*/
	public void setHR_Type_Conge_ID (int HR_Type_Conge_ID)
	{
		if (HR_Type_Conge_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Type_Conge_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Type_Conge_ID, Integer.valueOf(HR_Type_Conge_ID));
	}

	/** Get Type de Congé.
		@return Type de Congé	  */
	public int getHR_Type_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Conge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Peut Approuver.
		@param IsApprobation Peut Approuver
	*/
	public void setIsApprobation (boolean IsApprobation)
	{
		set_Value (COLUMNNAME_IsApprobation, Boolean.valueOf(IsApprobation));
	}

	/** Get Peut Approuver.
		@return Peut Approuver
	  */
	public boolean isApprobation()
	{
		Object oo = get_Value(COLUMNNAME_IsApprobation);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Peut Compenser une Absence sur le Congé.
		@param IsCompensation Peut Compenser une Absence sur le Congé
	*/
	public void setIsCompensation (boolean IsCompensation)
	{
		set_Value (COLUMNNAME_IsCompensation, Boolean.valueOf(IsCompensation));
	}

	/** Get Peut Compenser une Absence sur le Congé.
		@return Peut Compenser une Absence sur le Congé
	  */
	public boolean isCompensation()
	{
		Object oo = get_Value(COLUMNNAME_IsCompensation);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Est déduit des jours de congé annuel.
		@param IsCongeAnnuel Est déduit des jours de congé annuel
	*/
	public void setIsCongeAnnuel (boolean IsCongeAnnuel)
	{
		set_Value (COLUMNNAME_IsCongeAnnuel, Boolean.valueOf(IsCongeAnnuel));
	}

	/** Get Est déduit des jours de congé annuel.
		@return Est déduit des jours de congé annuel
	  */
	public boolean isCongeAnnuel()
	{
		Object oo = get_Value(COLUMNNAME_IsCongeAnnuel);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Peut Valider.
		@param IsValidation Peut Valider
	*/
	public void setIsValidation (boolean IsValidation)
	{
		set_Value (COLUMNNAME_IsValidation, Boolean.valueOf(IsValidation));
	}

	/** Get Peut Valider.
		@return Peut Valider
	  */
	public boolean isValidation()
	{
		Object oo = get_Value(COLUMNNAME_IsValidation);
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