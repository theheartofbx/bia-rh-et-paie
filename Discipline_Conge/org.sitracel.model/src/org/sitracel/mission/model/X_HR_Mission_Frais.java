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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Mission_Frais
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mission_Frais")
public class X_HR_Mission_Frais extends PO implements I_HR_Mission_Frais, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251216L;

    /** Standard Constructor */
    public X_HR_Mission_Frais (Properties ctx, int HR_Mission_Frais_ID, String trxName)
    {
      super (ctx, HR_Mission_Frais_ID, trxName);
      /** if (HR_Mission_Frais_ID == 0)
        {
			setHR_Mission_Frais_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Frais (Properties ctx, int HR_Mission_Frais_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Frais_ID, trxName, virtualColumns);
      /** if (HR_Mission_Frais_ID == 0)
        {
			setHR_Mission_Frais_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Frais (Properties ctx, String HR_Mission_Frais_UU, String trxName)
    {
      super (ctx, HR_Mission_Frais_UU, trxName);
      /** if (HR_Mission_Frais_UU == null)
        {
			setHR_Mission_Frais_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Frais (Properties ctx, String HR_Mission_Frais_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Frais_UU, trxName, virtualColumns);
      /** if (HR_Mission_Frais_UU == null)
        {
			setHR_Mission_Frais_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Mission_Frais (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Mission_Frais[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Date de la Dépense.
		@param Date_Depense Date de la Dépense
	*/
	public void setDate_Depense (Timestamp Date_Depense)
	{
		set_Value (COLUMNNAME_Date_Depense, Date_Depense);
	}

	/** Get Date de la Dépense.
		@return Date de la Dépense
	  */
	public Timestamp getDate_Depense()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Depense);
	}

	/** Set Date de Rejet.
		@param Date_Rejet Date de Rejet
	*/
	public void setDate_Rejet (Timestamp Date_Rejet)
	{
		set_Value (COLUMNNAME_Date_Rejet, Date_Rejet);
	}

	/** Get Date de Rejet.
		@return Date de Rejet
	  */
	public Timestamp getDate_Rejet()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Rejet);
	}

	/** Set Date de Remboursement.
		@param Date_Remboursement Date de Remboursement
	*/
	public void setDate_Remboursement (Timestamp Date_Remboursement)
	{
		set_Value (COLUMNNAME_Date_Remboursement, Date_Remboursement);
	}

	/** Get Date de Remboursement.
		@return Date de Remboursement	  */
	public Timestamp getDate_Remboursement()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Remboursement);
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

	/** Set Frais de Mission.
		@param HR_Mission_Frais_ID Frais de Mission
	*/
	public void setHR_Mission_Frais_ID (int HR_Mission_Frais_ID)
	{
		if (HR_Mission_Frais_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Frais_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Frais_ID, Integer.valueOf(HR_Mission_Frais_ID));
	}

	/** Get Frais de Mission.
		@return Frais de Mission	  */
	public int getHR_Mission_Frais_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_Frais_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Mission_Frais_UU.
		@param HR_Mission_Frais_UU HR_Mission_Frais_UU
	*/
	public void setHR_Mission_Frais_UU (String HR_Mission_Frais_UU)
	{
		set_Value (COLUMNNAME_HR_Mission_Frais_UU, HR_Mission_Frais_UU);
	}

	/** Get HR_Mission_Frais_UU.
		@return HR_Mission_Frais_UU	  */
	public String getHR_Mission_Frais_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mission_Frais_UU);
	}

	public I_HR_Mission getHR_Mission() throws RuntimeException
	{
		return (I_HR_Mission)MTable.get(getCtx(), I_HR_Mission.Table_ID)
			.getPO(getHR_Mission_ID(), get_TrxName());
	}

	/** Set Mission.
		@param HR_Mission_ID Mission
	*/
	public void setHR_Mission_ID (int HR_Mission_ID)
	{
		if (HR_Mission_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_ID, Integer.valueOf(HR_Mission_ID));
	}

	/** Get Mission.
		@return Mission	  */
	public int getHR_Mission_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_TypeFrais getHR_TypeFrais() throws RuntimeException
	{
		return (I_HR_TypeFrais)MTable.get(getCtx(), I_HR_TypeFrais.Table_ID)
			.getPO(getHR_TypeFrais_ID(), get_TrxName());
	}

	/** Set Type de Frais .
		@param HR_TypeFrais_ID Type de Frais 
	*/
	public void setHR_TypeFrais_ID (int HR_TypeFrais_ID)
	{
		if (HR_TypeFrais_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeFrais_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeFrais_ID, Integer.valueOf(HR_TypeFrais_ID));
	}

	/** Get Type de Frais .
		@return Type de Frais 	  */
	public int getHR_TypeFrais_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeFrais_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Possède des Pièces Justificatives.
		@param IsJustifie Possède des Pièces Justificatives
	*/
	public void setIsJustifie (boolean IsJustifie)
	{
		set_Value (COLUMNNAME_IsJustifie, Boolean.valueOf(IsJustifie));
	}

	/** Get Possède des Pièces Justificatives.
		@return Possède des Pièces Justificatives	  */
	public boolean isJustifie()
	{
		Object oo = get_Value(COLUMNNAME_IsJustifie);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rejeté(e).
		@param IsRejetee Rejeté(e)
	*/
	public void setIsRejetee (boolean IsRejetee)
	{
		set_Value (COLUMNNAME_IsRejetee, Boolean.valueOf(IsRejetee));
	}

	/** Get Rejeté(e).
		@return Rejeté(e)
	  */
	public boolean isRejetee()
	{
		Object oo = get_Value(COLUMNNAME_IsRejetee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set A été remboursé.
		@param IsRembourse A été remboursé
	*/
	public void setIsRembourse (boolean IsRembourse)
	{
		set_Value (COLUMNNAME_IsRembourse, Boolean.valueOf(IsRembourse));
	}

	/** Get A été remboursé.
		@return A été remboursé	  */
	public boolean isRembourse()
	{
		Object oo = get_Value(COLUMNNAME_IsRembourse);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Montant.
		@param Montant Montant
	*/
	public void setMontant (BigDecimal Montant)
	{
		set_Value (COLUMNNAME_Montant, Montant);
	}

	/** Get Montant.
		@return Montant
	  */
	public BigDecimal getMontant()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Quantité.
		@param Quantite Quantité
	*/
	public void setQuantite (int Quantite)
	{
		set_Value (COLUMNNAME_Quantite, Integer.valueOf(Quantite));
	}

	/** Get Quantité.
		@return Quantité
	  */
	public int getQuantite()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Quantite);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}