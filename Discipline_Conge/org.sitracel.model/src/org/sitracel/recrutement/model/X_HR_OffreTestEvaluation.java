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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_OffreTestEvaluation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_OffreTestEvaluation")
public class X_HR_OffreTestEvaluation extends PO implements I_HR_OffreTestEvaluation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241208L;

    /** Standard Constructor */
    public X_HR_OffreTestEvaluation (Properties ctx, int HR_OffreTestEvaluation_ID, String trxName)
    {
      super (ctx, HR_OffreTestEvaluation_ID, trxName);
      /** if (HR_OffreTestEvaluation_ID == 0)
        {
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreTestEvaluation (Properties ctx, int HR_OffreTestEvaluation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreTestEvaluation_ID, trxName, virtualColumns);
      /** if (HR_OffreTestEvaluation_ID == 0)
        {
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreTestEvaluation (Properties ctx, String HR_OffreTestEvaluation_UU, String trxName)
    {
      super (ctx, HR_OffreTestEvaluation_UU, trxName);
      /** if (HR_OffreTestEvaluation_UU == null)
        {
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreTestEvaluation (Properties ctx, String HR_OffreTestEvaluation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreTestEvaluation_UU, trxName, virtualColumns);
      /** if (HR_OffreTestEvaluation_UU == null)
        {
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_OffreTestEvaluation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_OffreTestEvaluation[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Date de Création.
		@param Date_Creation Date de Création
	*/
	public void setDate_Creation (Timestamp Date_Creation)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Creation, Date_Creation);
	}

	/** Get Date de Création.
		@return Date de Création	  */
	public Timestamp getDate_Creation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Creation);
	}

	/** Set Date de Validation.
		@param Date_Validation Date de Validation
	*/
	public void setDate_Validation (Timestamp Date_Validation)
	{
		set_Value (COLUMNNAME_Date_Validation, Date_Validation);
	}

	/** Get Date de Validation.
		@return Date de Validation
	  */
	public Timestamp getDate_Validation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Validation);
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

	/** Set Matricule Emetteur.
		@param Emis_Par_Matricule Matricule Emetteur
	*/
	public void setEmis_Par_Matricule (String Emis_Par_Matricule)
	{
		set_Value (COLUMNNAME_Emis_Par_Matricule, Emis_Par_Matricule);
	}

	/** Get Matricule Emetteur.
		@return Matricule Emetteur
	  */
	public String getEmis_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Emis_Par_Matricule);
	}

	public org.compiere.model.I_C_BPartner getEmis_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEmis_Par_Nom_ID(), get_TrxName());
	}

	/** Set Nom Emetteur.
		@param Emis_Par_Nom_ID Nom Emetteur
	*/
	public void setEmis_Par_Nom_ID (int Emis_Par_Nom_ID)
	{
		if (Emis_Par_Nom_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Emis_Par_Nom_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Emis_Par_Nom_ID, Integer.valueOf(Emis_Par_Nom_ID));
	}

	/** Get Nom Emetteur.
		@return Nom Emetteur
	  */
	public int getEmis_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emis_Par_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getEmis_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getEmis_Par_Poste_ID(), get_TrxName());
	}

	/** Set Poste Emetteur.
		@param Emis_Par_Poste_ID Poste Emetteur
	*/
	public void setEmis_Par_Poste_ID (int Emis_Par_Poste_ID)
	{
		if (Emis_Par_Poste_ID < 1)
			set_Value (COLUMNNAME_Emis_Par_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Emis_Par_Poste_ID, Integer.valueOf(Emis_Par_Poste_ID));
	}

	/** Get Poste Emetteur.
		@return Poste Emetteur
	  */
	public int getEmis_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emis_Par_Poste_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Test d&#039;Évaluation.
		@param HR_OffreTestEvaluation_ID Test d&#039;Évaluation
	*/
	public void setHR_OffreTestEvaluation_ID (int HR_OffreTestEvaluation_ID)
	{
		if (HR_OffreTestEvaluation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_OffreTestEvaluation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_OffreTestEvaluation_ID, Integer.valueOf(HR_OffreTestEvaluation_ID));
	}

	/** Get Test d&#039;Évaluation.
		@return Test d&#039;Évaluation	  */
	public int getHR_OffreTestEvaluation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreTestEvaluation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_OffreTestEvaluation_UU.
		@param HR_OffreTestEvaluation_UU HR_OffreTestEvaluation_UU
	*/
	public void setHR_OffreTestEvaluation_UU (String HR_OffreTestEvaluation_UU)
	{
		set_Value (COLUMNNAME_HR_OffreTestEvaluation_UU, HR_OffreTestEvaluation_UU);
	}

	/** Get HR_OffreTestEvaluation_UU.
		@return HR_OffreTestEvaluation_UU	  */
	public String getHR_OffreTestEvaluation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_OffreTestEvaluation_UU);
	}

	/** Set Validé(e).
		@param IsValidee Validé(e)
	*/
	public void setIsValidee (boolean IsValidee)
	{
		set_Value (COLUMNNAME_IsValidee, Boolean.valueOf(IsValidee));
	}

	/** Get Validé(e).
		@return Validé(e)
	  */
	public boolean isValidee()
	{
		Object oo = get_Value(COLUMNNAME_IsValidee);
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

	/** Set Validé/rejeté par (Matricule) :.
		@param Valide_Rejete_Par_Matricule Validé/rejeté par (Matricule) :
	*/
	public void setValide_Rejete_Par_Matricule (String Valide_Rejete_Par_Matricule)
	{
		set_Value (COLUMNNAME_Valide_Rejete_Par_Matricule, Valide_Rejete_Par_Matricule);
	}

	/** Get Validé/rejeté par (Matricule) :.
		@return Validé/rejeté par (Matricule) :
	  */
	public String getValide_Rejete_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Valide_Rejete_Par_Matricule);
	}

	public org.compiere.model.I_C_BPartner getValide_Rejete_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getValide_Rejete_Par_Nom_ID(), get_TrxName());
	}

	/** Set Validé/rejeté par (Nom) :.
		@param Valide_Rejete_Par_Nom_ID Validé/rejeté par (Nom) :
	*/
	public void setValide_Rejete_Par_Nom_ID (int Valide_Rejete_Par_Nom_ID)
	{
		if (Valide_Rejete_Par_Nom_ID < 1)
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, null);
		else
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, Integer.valueOf(Valide_Rejete_Par_Nom_ID));
	}

	/** Get Validé/rejeté par (Nom) :.
		@return Validé/rejeté par (Nom) :
	  */
	public int getValide_Rejete_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valide_Rejete_Par_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getValide_Rejete_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getValide_Rejete_Par_Poste_ID(), get_TrxName());
	}

	/** Set Validé/rejeté par (Poste) :.
		@param Valide_Rejete_Par_Poste_ID Validé/rejeté par (Poste) :
	*/
	public void setValide_Rejete_Par_Poste_ID (int Valide_Rejete_Par_Poste_ID)
	{
		if (Valide_Rejete_Par_Poste_ID < 1)
			set_Value (COLUMNNAME_Valide_Rejete_Par_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Valide_Rejete_Par_Poste_ID, Integer.valueOf(Valide_Rejete_Par_Poste_ID));
	}

	/** Get Validé/rejeté par (Poste) :.
		@return Validé/rejeté par (Poste) :
	  */
	public int getValide_Rejete_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valide_Rejete_Par_Poste_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valider .
		@param Valider Valider 
	*/
	public void setValider (String Valider)
	{
		set_Value (COLUMNNAME_Valider, Valider);
	}

	/** Get Valider .
		@return Valider 
	  */
	public String getValider()
	{
		return (String)get_Value(COLUMNNAME_Valider);
	}
}