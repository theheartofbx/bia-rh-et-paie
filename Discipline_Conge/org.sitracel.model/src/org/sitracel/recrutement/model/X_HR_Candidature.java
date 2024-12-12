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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Candidature
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Candidature")
public class X_HR_Candidature extends PO implements I_HR_Candidature, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241210L;

    /** Standard Constructor */
    public X_HR_Candidature (Properties ctx, int HR_Candidature_ID, String trxName)
    {
      super (ctx, HR_Candidature_ID, trxName);
      /** if (HR_Candidature_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Candidature_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Candidature (Properties ctx, int HR_Candidature_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Candidature_ID, trxName, virtualColumns);
      /** if (HR_Candidature_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Candidature_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Candidature (Properties ctx, String HR_Candidature_UU, String trxName)
    {
      super (ctx, HR_Candidature_UU, trxName);
      /** if (HR_Candidature_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Candidature_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Candidature (Properties ctx, String HR_Candidature_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Candidature_UU, trxName, virtualColumns);
      /** if (HR_Candidature_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Candidature_ID (0);
			setHR_SessionRecrutement_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Candidature (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Candidature[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner .
		@param C_BPartner_ID Identifies a Business Partner
	*/
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		set_ValueNoCheck (COLUMNNAME_Emis_Par_Matricule, Emis_Par_Matricule);
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

	/** Set Candidature.
		@param HR_Candidature_ID Candidature
	*/
	public void setHR_Candidature_ID (int HR_Candidature_ID)
	{
		if (HR_Candidature_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Candidature_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Candidature_ID, Integer.valueOf(HR_Candidature_ID));
	}

	/** Get Candidature.
		@return Candidature	  */
	public int getHR_Candidature_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Candidature_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Candidature_UU.
		@param HR_Candidature_UU HR_Candidature_UU
	*/
	public void setHR_Candidature_UU (String HR_Candidature_UU)
	{
		set_Value (COLUMNNAME_HR_Candidature_UU, HR_Candidature_UU);
	}

	/** Get HR_Candidature_UU.
		@return HR_Candidature_UU	  */
	public String getHR_Candidature_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Candidature_UU);
	}

	public I_HR_SessionRecrutement getHR_SessionRecrutement() throws RuntimeException
	{
		return (I_HR_SessionRecrutement)MTable.get(getCtx(), I_HR_SessionRecrutement.Table_ID)
			.getPO(getHR_SessionRecrutement_ID(), get_TrxName());
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

	/** Set Nombre de Candidat Total.
		@param NombreCandidatTotal Nombre de Candidat Total
	*/
	public void setNombreCandidatTotal (int NombreCandidatTotal)
	{
		throw new IllegalArgumentException ("NombreCandidatTotal is virtual column");	}

	/** Get Nombre de Candidat Total.
		@return Nombre de Candidat Total	  */
	public int getNombreCandidatTotal()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreCandidatTotal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Compétence Évalué.
		@param NombreCompetenceEvalue Nombre de Compétence Évalué
	*/
	public void setNombreCompetenceEvalue (int NombreCompetenceEvalue)
	{
		throw new IllegalArgumentException ("NombreCompetenceEvalue is virtual column");	}

	/** Get Nombre de Compétence Évalué.
		@return Nombre de Compétence Évalué	  */
	public int getNombreCompetenceEvalue()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreCompetenceEvalue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Compétence Total.
		@param NombreCompetenceTotal Nombre de Compétence Total
	*/
	public void setNombreCompetenceTotal (int NombreCompetenceTotal)
	{
		throw new IllegalArgumentException ("NombreCompetenceTotal is virtual column");	}

	/** Get Nombre de Compétence Total.
		@return Nombre de Compétence Total	  */
	public int getNombreCompetenceTotal()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreCompetenceTotal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rang du Candidat.
		@param RangCandidat Rang du Candidat
	*/
	public void setRangCandidat (int RangCandidat)
	{
		set_Value (COLUMNNAME_RangCandidat, Integer.valueOf(RangCandidat));
	}

	/** Get Rang du Candidat.
		@return Rang du Candidat	  */
	public int getRangCandidat()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RangCandidat);
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

	/** Set Score Total .
		@param ScoreTotal Score Total 
	*/
	public void setScoreTotal (BigDecimal ScoreTotal)
	{
		set_Value (COLUMNNAME_ScoreTotal, ScoreTotal);
	}

	/** Get Score Total .
		@return Score Total 	  */
	public BigDecimal getScoreTotal()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score Total Maximum.
		@param ScoreTotalMax Score Total Maximum
	*/
	public void setScoreTotalMax (BigDecimal ScoreTotalMax)
	{
		set_Value (COLUMNNAME_ScoreTotalMax, ScoreTotalMax);
	}

	/** Get Score Total Maximum.
		@return Score Total Maximum	  */
	public BigDecimal getScoreTotalMax()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreTotalMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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