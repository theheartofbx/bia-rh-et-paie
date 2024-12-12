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

/** Generated Model for HR_SessionRecrutement
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_SessionRecrutement")
public class X_HR_SessionRecrutement extends PO implements I_HR_SessionRecrutement, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241208L;

    /** Standard Constructor */
    public X_HR_SessionRecrutement (Properties ctx, int HR_SessionRecrutement_ID, String trxName)
    {
      super (ctx, HR_SessionRecrutement_ID, trxName);
      /** if (HR_SessionRecrutement_ID == 0)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_SessionRecrutement (Properties ctx, int HR_SessionRecrutement_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_SessionRecrutement_ID, trxName, virtualColumns);
      /** if (HR_SessionRecrutement_ID == 0)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_SessionRecrutement (Properties ctx, String HR_SessionRecrutement_UU, String trxName)
    {
      super (ctx, HR_SessionRecrutement_UU, trxName);
      /** if (HR_SessionRecrutement_UU == null)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_SessionRecrutement (Properties ctx, String HR_SessionRecrutement_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_SessionRecrutement_UU, trxName, virtualColumns);
      /** if (HR_SessionRecrutement_UU == null)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_SessionRecrutement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_SessionRecrutement[")
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

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_Value (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
	}

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_Value (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
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

	public I_HR_OffreEmploi getHR_OffreEmploi() throws RuntimeException
	{
		return (I_HR_OffreEmploi)MTable.get(getCtx(), I_HR_OffreEmploi.Table_ID)
			.getPO(getHR_OffreEmploi_ID(), get_TrxName());
	}

	/** Set Offre d&#039;Emploi.
		@param HR_OffreEmploi_ID Offre d&#039;Emploi
	*/
	public void setHR_OffreEmploi_ID (int HR_OffreEmploi_ID)
	{
		if (HR_OffreEmploi_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_OffreEmploi_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_OffreEmploi_ID, Integer.valueOf(HR_OffreEmploi_ID));
	}

	/** Get Offre d&#039;Emploi.
		@return Offre d&#039;Emploi	  */
	public int getHR_OffreEmploi_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreEmploi_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_OffreTestEvaluation getHR_OffreTestEvaluation() throws RuntimeException
	{
		return (I_HR_OffreTestEvaluation)MTable.get(getCtx(), I_HR_OffreTestEvaluation.Table_ID)
			.getPO(getHR_OffreTestEvaluation_ID(), get_TrxName());
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

	/** Set Session de Recrutement.
		@param HR_SessionRecrutement_ID Session de Recrutement
	*/
	public void setHR_SessionRecrutement_ID (int HR_SessionRecrutement_ID)
	{
		if (HR_SessionRecrutement_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_SessionRecrutement_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_SessionRecrutement_ID, Integer.valueOf(HR_SessionRecrutement_ID));
	}

	/** Get Session de Recrutement.
		@return Session de Recrutement	  */
	public int getHR_SessionRecrutement_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_SessionRecrutement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_SessionRecrutement_UU.
		@param HR_SessionRecrutement_UU HR_SessionRecrutement_UU
	*/
	public void setHR_SessionRecrutement_UU (String HR_SessionRecrutement_UU)
	{
		set_Value (COLUMNNAME_HR_SessionRecrutement_UU, HR_SessionRecrutement_UU);
	}

	/** Get HR_SessionRecrutement_UU.
		@return HR_SessionRecrutement_UU	  */
	public String getHR_SessionRecrutement_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_SessionRecrutement_UU);
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

	/** Set Nombre de Candidat Recherché.
		@param Nombre_Candidat Nombre de Candidat Recherché
	*/
	public void setNombre_Candidat (int Nombre_Candidat)
	{
		set_Value (COLUMNNAME_Nombre_Candidat, Integer.valueOf(Nombre_Candidat));
	}

	/** Get Nombre de Candidat Recherché.
		@return Nombre de Candidat Recherché	  */
	public int getNombre_Candidat()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Candidat);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rejeter.
		@param Rejeter Rejeter
	*/
	public void setRejeter (String Rejeter)
	{
		set_Value (COLUMNNAME_Rejeter, Rejeter);
	}

	/** Get Rejeter.
		@return Rejeter
	  */
	public String getRejeter()
	{
		return (String)get_Value(COLUMNNAME_Rejeter);
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