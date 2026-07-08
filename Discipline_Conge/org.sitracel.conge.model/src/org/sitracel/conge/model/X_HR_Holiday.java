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

import org.sitracel.model.I_HR_Ampliation;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Holiday
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Holiday")
public class X_HR_Holiday extends PO implements I_HR_Holiday, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260708L;

    /** Standard Constructor */
    public X_HR_Holiday (Properties ctx, int HR_Holiday_ID, String trxName)
    {
      super (ctx, HR_Holiday_ID, trxName);
      /** if (HR_Holiday_ID == 0)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Conge_ID (0);
			setHR_CongeStatut_ID (0);
			setHR_Holiday_ID (0);
			setMatricule_Employe (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Holiday (Properties ctx, int HR_Holiday_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Holiday_ID, trxName, virtualColumns);
      /** if (HR_Holiday_ID == 0)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Conge_ID (0);
			setHR_CongeStatut_ID (0);
			setHR_Holiday_ID (0);
			setMatricule_Employe (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Holiday (Properties ctx, String HR_Holiday_UU, String trxName)
    {
      super (ctx, HR_Holiday_UU, trxName);
      /** if (HR_Holiday_UU == null)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Conge_ID (0);
			setHR_CongeStatut_ID (0);
			setHR_Holiday_ID (0);
			setMatricule_Employe (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Holiday (Properties ctx, String HR_Holiday_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Holiday_UU, trxName, virtualColumns);
      /** if (HR_Holiday_UU == null)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Conge_ID (0);
			setHR_CongeStatut_ID (0);
			setHR_Holiday_ID (0);
			setMatricule_Employe (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Holiday (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Holiday[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Actualiser Absences.
		@param Actualiser_Absences Actualiser Absences
	*/
	public void setActualiser_Absences (String Actualiser_Absences)
	{
		set_Value (COLUMNNAME_Actualiser_Absences, Actualiser_Absences);
	}

	/** Get Actualiser Absences.
		@return Actualiser Absences	  */
	public String getActualiser_Absences()
	{
		return (String)get_Value(COLUMNNAME_Actualiser_Absences);
	}

	/** Set Nombre d&#039;Année d&#039;Ancienneté.
		@param Annee_Anciennete Nombre d&#039;Année d&#039;Ancienneté
	*/
	public void setAnnee_Anciennete (int Annee_Anciennete)
	{
		set_Value (COLUMNNAME_Annee_Anciennete, Integer.valueOf(Annee_Anciennete));
	}

	/** Get Nombre d&#039;Année d&#039;Ancienneté.
		@return Nombre d&#039;Année d&#039;Ancienneté
	  */
	public int getAnnee_Anciennete()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Annee_Anciennete);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approuve/Desapprouvé par (Matricule) :.
		@param Approuve_Desapprouve_Matricule Approuve/Desapprouvé par (Matricule) :
	*/
	public void setApprouve_Desapprouve_Matricule (String Approuve_Desapprouve_Matricule)
	{
		set_Value (COLUMNNAME_Approuve_Desapprouve_Matricule, Approuve_Desapprouve_Matricule);
	}

	/** Get Approuve/Desapprouvé par (Matricule) :.
		@return Approuve/Desapprouvé par (Matricule) :
	  */
	public String getApprouve_Desapprouve_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Approuve_Desapprouve_Matricule);
	}

	public org.compiere.model.I_C_BPartner getApprouve_Desapprouve_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getApprouve_Desapprouve_Nom_ID(), get_TrxName());
	}

	/** Set Approuve/Desapprouvé par (Nom) :.
		@param Approuve_Desapprouve_Nom_ID Approuve/Desapprouvé par (Nom) :
	*/
	public void setApprouve_Desapprouve_Nom_ID (int Approuve_Desapprouve_Nom_ID)
	{
		if (Approuve_Desapprouve_Nom_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Approuve_Desapprouve_Nom_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Approuve_Desapprouve_Nom_ID, Integer.valueOf(Approuve_Desapprouve_Nom_ID));
	}

	/** Get Approuve/Desapprouvé par (Nom) :.
		@return Approuve/Desapprouvé par (Nom) :
	  */
	public int getApprouve_Desapprouve_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Approuve_Desapprouve_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getApprouve_Desapprouve_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getApprouve_Desapprouve_Poste_ID(), get_TrxName());
	}

	/** Set Approuve/Desapprouvé par (Poste) :.
		@param Approuve_Desapprouve_Poste_ID Approuve/Desapprouvé par (Poste) :
	*/
	public void setApprouve_Desapprouve_Poste_ID (int Approuve_Desapprouve_Poste_ID)
	{
		if (Approuve_Desapprouve_Poste_ID < 1)
			set_Value (COLUMNNAME_Approuve_Desapprouve_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Approuve_Desapprouve_Poste_ID, Integer.valueOf(Approuve_Desapprouve_Poste_ID));
	}

	/** Get Approuve/Desapprouvé par (Poste) :.
		@return Approuve/Desapprouvé par (Poste) :
	  */
	public int getApprouve_Desapprouve_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Approuve_Desapprouve_Poste_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approuver.
		@param Approuver Approuver
	*/
	public void setApprouver (String Approuver)
	{
		set_Value (COLUMNNAME_Approuver, Approuver);
	}

	/** Get Approuver.
		@return Approuver
	  */
	public String getApprouver()
	{
		return (String)get_Value(COLUMNNAME_Approuver);
	}

	/** Set Approuver.
		@param Approuver_Createur Approuver
	*/
	public void setApprouver_Createur (String Approuver_Createur)
	{
		set_Value (COLUMNNAME_Approuver_Createur, Approuver_Createur);
	}

	/** Get Approuver.
		@return Approuver
	  */
	public String getApprouver_Createur()
	{
		return (String)get_Value(COLUMNNAME_Approuver_Createur);
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

	/** Set Transaction Date.
		@param DateTrx Transaction Date
	*/
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Dates des Absences Compensés en Début de Congé.
		@param Date_Absence_Compense_Debut Dates des Absences Compensés en Début de Congé
	*/
	public void setDate_Absence_Compense_Debut (String Date_Absence_Compense_Debut)
	{
		set_Value (COLUMNNAME_Date_Absence_Compense_Debut, Date_Absence_Compense_Debut);
	}

	/** Get Dates des Absences Compensés en Début de Congé.
		@return Dates des Absences Compensés en Début de Congé
	  */
	public String getDate_Absence_Compense_Debut()
	{
		return (String)get_Value(COLUMNNAME_Date_Absence_Compense_Debut);
	}

	/** Set Dates des Absences Compensés en Fin de Congé.
		@param Date_Absence_Compense_Fin Dates des Absences Compensés en Fin de Congé
	*/
	public void setDate_Absence_Compense_Fin (String Date_Absence_Compense_Fin)
	{
		set_Value (COLUMNNAME_Date_Absence_Compense_Fin, Date_Absence_Compense_Fin);
	}

	/** Get Dates des Absences Compensés en Fin de Congé.
		@return Dates des Absences Compensés en Fin de Congé
	  */
	public String getDate_Absence_Compense_Fin()
	{
		return (String)get_Value(COLUMNNAME_Date_Absence_Compense_Fin);
	}

	/** Set Date Approbation.
		@param Date_Approbation Date Approbation
	*/
	public void setDate_Approbation (Timestamp Date_Approbation)
	{
		set_Value (COLUMNNAME_Date_Approbation, Date_Approbation);
	}

	/** Get Date Approbation.
		@return Date Approbation
	  */
	public Timestamp getDate_Approbation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Approbation);
	}

	/** Set Date de Début Ajustée.
		@param Date_Debut_Ajustee Date de Début Ajustée
	*/
	public void setDate_Debut_Ajustee (Timestamp Date_Debut_Ajustee)
	{
		set_Value (COLUMNNAME_Date_Debut_Ajustee, Date_Debut_Ajustee);
	}

	/** Get Date de Début Ajustée.
		@return Date de Début Ajustée
	  */
	public Timestamp getDate_Debut_Ajustee()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut_Ajustee);
	}

	/** Set Date de Début Compensée.
		@param Date_Debut_Compensee Date de Début Compensée
	*/
	public void setDate_Debut_Compensee (Timestamp Date_Debut_Compensee)
	{
		set_Value (COLUMNNAME_Date_Debut_Compensee, Date_Debut_Compensee);
	}

	/** Get Date de Début Compensée.
		@return Date de Début Compensée
	  */
	public Timestamp getDate_Debut_Compensee()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut_Compensee);
	}

	/** Set Date de Début Effective.
		@param Date_Debut_Effective Date de Début Effective
	*/
	public void setDate_Debut_Effective (Timestamp Date_Debut_Effective)
	{
		set_Value (COLUMNNAME_Date_Debut_Effective, Date_Debut_Effective);
	}

	/** Get Date de Début Effective.
		@return Date de Début Effective
	  */
	public Timestamp getDate_Debut_Effective()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut_Effective);
	}

	/** Set Date de Début Souhaitée.
		@param Date_Debut_Souhaitee Date de Début Souhaitée
	*/
	public void setDate_Debut_Souhaitee (Timestamp Date_Debut_Souhaitee)
	{
		set_Value (COLUMNNAME_Date_Debut_Souhaitee, Date_Debut_Souhaitee);
	}

	/** Get Date de Début Souhaitée.
		@return Date de Début Souhaitée
	  */
	public Timestamp getDate_Debut_Souhaitee()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut_Souhaitee);
	}

	/** Set Date du dernier Congé.
		@param Date_Dernier_Conge Date du dernier Congé
	*/
	public void setDate_Dernier_Conge (Timestamp Date_Dernier_Conge)
	{
		set_Value (COLUMNNAME_Date_Dernier_Conge, Date_Dernier_Conge);
	}

	/** Get Date du dernier Congé.
		@return Date du dernier Congé
	  */
	public Timestamp getDate_Dernier_Conge()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Dernier_Conge);
	}

	/** Set Date Désapprobation.
		@param Date_Desapprobation Date Désapprobation
	*/
	public void setDate_Desapprobation (Timestamp Date_Desapprobation)
	{
		set_Value (COLUMNNAME_Date_Desapprobation, Date_Desapprobation);
	}

	/** Get Date Désapprobation.
		@return Date Désapprobation
	  */
	public Timestamp getDate_Desapprobation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Desapprobation);
	}

	/** Set Date d&#039;Emission.
		@param Date_Emission Date d&#039;Emission
	*/
	public void setDate_Emission (Timestamp Date_Emission)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Emission, Date_Emission);
	}

	/** Get Date d&#039;Emission.
		@return Date d&#039;Emission
	  */
	public Timestamp getDate_Emission()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Emission);
	}

	/** Set Date de Fin Ajustée.
		@param Date_Fin_Ajustee Date de Fin Ajustée
	*/
	public void setDate_Fin_Ajustee (Timestamp Date_Fin_Ajustee)
	{
		set_Value (COLUMNNAME_Date_Fin_Ajustee, Date_Fin_Ajustee);
	}

	/** Get Date de Fin Ajustée.
		@return Date de Fin Ajustée
	  */
	public Timestamp getDate_Fin_Ajustee()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin_Ajustee);
	}

	/** Set Date de Fin Compensée.
		@param Date_Fin_Compensee Date de Fin Compensée
	*/
	public void setDate_Fin_Compensee (Timestamp Date_Fin_Compensee)
	{
		set_Value (COLUMNNAME_Date_Fin_Compensee, Date_Fin_Compensee);
	}

	/** Get Date de Fin Compensée.
		@return Date de Fin Compensée
	  */
	public Timestamp getDate_Fin_Compensee()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin_Compensee);
	}

	/** Set Date de Fin Effective.
		@param Date_Fin_Effective Date de Fin Effective
	*/
	public void setDate_Fin_Effective (Timestamp Date_Fin_Effective)
	{
		set_Value (COLUMNNAME_Date_Fin_Effective, Date_Fin_Effective);
	}

	/** Get Date de Fin Effective.
		@return Date de Fin Effective
	  */
	public Timestamp getDate_Fin_Effective()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin_Effective);
	}

	/** Set Date de Fin Souhaitée.
		@param Date_Fin_Souhaitee Date de Fin Souhaitée
	*/
	public void setDate_Fin_Souhaitee (Timestamp Date_Fin_Souhaitee)
	{
		set_Value (COLUMNNAME_Date_Fin_Souhaitee, Date_Fin_Souhaitee);
	}

	/** Get Date de Fin Souhaitée.
		@return Date de Fin Souhaitée
	  */
	public Timestamp getDate_Fin_Souhaitee()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin_Souhaitee);
	}

	/** Set Date Probable d&#039;Accouchement.
		@param Date_Probable_Accouchement Date Probable d&#039;Accouchement
	*/
	public void setDate_Probable_Accouchement (Timestamp Date_Probable_Accouchement)
	{
		set_Value (COLUMNNAME_Date_Probable_Accouchement, Date_Probable_Accouchement);
	}

	/** Get Date Probable d&#039;Accouchement.
		@return Date Probable d&#039;Accouchement
	  */
	public Timestamp getDate_Probable_Accouchement()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Probable_Accouchement);
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

	/** Set Désapprouver.
		@param Desapprouver Désapprouver
	*/
	public void setDesapprouver (String Desapprouver)
	{
		set_Value (COLUMNNAME_Desapprouver, Desapprouver);
	}

	/** Get Désapprouver.
		@return Désapprouver
	  */
	public String getDesapprouver()
	{
		return (String)get_Value(COLUMNNAME_Desapprouver);
	}

	/** Set Désapprouver.
		@param Desapprouver_Createur Désapprouver
	*/
	public void setDesapprouver_Createur (String Desapprouver_Createur)
	{
		set_Value (COLUMNNAME_Desapprouver_Createur, Desapprouver_Createur);
	}

	/** Get Désapprouver.
		@return Désapprouver
	  */
	public String getDesapprouver_Createur()
	{
		return (String)get_Value(COLUMNNAME_Desapprouver_Createur);
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

	/** Set Disponibilité du Département si la Demande est Acceptée.
		@param Disponibilite_Departement Disponibilité du Département si la Demande est Acceptée
	*/
	public void setDisponibilite_Departement (BigDecimal Disponibilite_Departement)
	{
		set_Value (COLUMNNAME_Disponibilite_Departement, Disponibilite_Departement);
	}

	/** Get Disponibilité du Département si la Demande est Acceptée.
		@return Disponibilité du Département si la Demande est Acceptée
	  */
	public BigDecimal getDisponibilite_Departement()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Disponibilite_Departement);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_HR_Type_Conge getEmission_Conge_Employe() throws RuntimeException
	{
		return (I_HR_Type_Conge)MTable.get(getCtx(), I_HR_Type_Conge.Table_ID)
			.getPO(getEmission_Conge_Employe_ID(), get_TrxName());
	}

	/** Set Type de Congé.
		@param Emission_Conge_Employe_ID Type de Congé
	*/
	public void setEmission_Conge_Employe_ID (int Emission_Conge_Employe_ID)
	{
		if (Emission_Conge_Employe_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Emission_Conge_Employe_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Emission_Conge_Employe_ID, Integer.valueOf(Emission_Conge_Employe_ID));
	}

	/** Get Type de Congé.
		@return Type de Congé
	  */
	public int getEmission_Conge_Employe_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emission_Conge_Employe_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Type_Conge getEmission_Conge() throws RuntimeException
	{
		return (I_HR_Type_Conge)MTable.get(getCtx(), I_HR_Type_Conge.Table_ID)
			.getPO(getEmission_Conge_ID(), get_TrxName());
	}

	/** Set Type de Congé.
		@param Emission_Conge_ID Type de Congé
	*/
	public void setEmission_Conge_ID (int Emission_Conge_ID)
	{
		if (Emission_Conge_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Emission_Conge_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Emission_Conge_ID, Integer.valueOf(Emission_Conge_ID));
	}

	/** Get Type de Congé.
		@return Type de Congé
	  */
	public int getEmission_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emission_Conge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Autorisation_Conge getEmission_Conge_Responsable() throws RuntimeException
	{
		return (I_HR_Autorisation_Conge)MTable.get(getCtx(), I_HR_Autorisation_Conge.Table_ID)
			.getPO(getEmission_Conge_Responsable_ID(), get_TrxName());
	}

	/** Set Type de Congé.
		@param Emission_Conge_Responsable_ID Type de Congé
	*/
	public void setEmission_Conge_Responsable_ID (int Emission_Conge_Responsable_ID)
	{
		if (Emission_Conge_Responsable_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Emission_Conge_Responsable_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Emission_Conge_Responsable_ID, Integer.valueOf(Emission_Conge_Responsable_ID));
	}

	/** Get Type de Congé.
		@return Type de Congé
	  */
	public int getEmission_Conge_Responsable_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emission_Conge_Responsable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Enfants Comptabilisés pour Congé.
		@param Enfant_Conge Enfants Comptabilisés pour Congé
	*/
	public void setEnfant_Conge (int Enfant_Conge)
	{
		set_Value (COLUMNNAME_Enfant_Conge, Integer.valueOf(Enfant_Conge));
	}

	/** Get Enfants Comptabilisés pour Congé.
		@return Enfants Comptabilisés pour Congé
	  */
	public int getEnfant_Conge()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Enfant_Conge);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Ampliation getHR_Ampliation() throws RuntimeException
	{
		return (I_HR_Ampliation)MTable.get(getCtx(), I_HR_Ampliation.Table_ID)
			.getPO(getHR_Ampliation_ID(), get_TrxName());
	}

	/** Set Ampliation.
		@param HR_Ampliation_ID Ampliation
	*/
	public void setHR_Ampliation_ID (int HR_Ampliation_ID)
	{
		if (HR_Ampliation_ID < 1)
			set_Value (COLUMNNAME_HR_Ampliation_ID, null);
		else
			set_Value (COLUMNNAME_HR_Ampliation_ID, Integer.valueOf(HR_Ampliation_ID));
	}

	/** Get Ampliation.
		@return Ampliation	  */
	public int getHR_Ampliation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Ampliation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_CongeStatut getHR_CongeStatut() throws RuntimeException
	{
		return (I_HR_CongeStatut)MTable.get(getCtx(), I_HR_CongeStatut.Table_ID)
			.getPO(getHR_CongeStatut_ID(), get_TrxName());
	}

	/** Set Statut des Congés.
		@param HR_CongeStatut_ID Statut des Congés
	*/
	public void setHR_CongeStatut_ID (int HR_CongeStatut_ID)
	{
		if (HR_CongeStatut_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_CongeStatut_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_CongeStatut_ID, Integer.valueOf(HR_CongeStatut_ID));
	}

	/** Get Statut des Congés.
		@return Statut des Congés	  */
	public int getHR_CongeStatut_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_CongeStatut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Congé.
		@param HR_Holiday_ID Congé
	*/
	public void setHR_Holiday_ID (int HR_Holiday_ID)
	{
		if (HR_Holiday_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, Integer.valueOf(HR_Holiday_ID));
	}

	/** Get Congé.
		@return Congé	  */
	public int getHR_Holiday_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Holiday_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Holiday_UU.
		@param HR_Holiday_UU HR_Holiday_UU
	*/
	public void setHR_Holiday_UU (String HR_Holiday_UU)
	{
		set_Value (COLUMNNAME_HR_Holiday_UU, HR_Holiday_UU);
	}

	/** Get HR_Holiday_UU.
		@return HR_Holiday_UU	  */
	public String getHR_Holiday_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Holiday_UU);
	}

	/** Set Initial.
		@param Initial Initial
	*/
	public void setInitial (String Initial)
	{
		set_Value (COLUMNNAME_Initial, Initial);
	}

	/** Get Initial.
		@return Initial
	  */
	public String getInitial()
	{
		return (String)get_Value(COLUMNNAME_Initial);
	}

	/** Set Peut Approuver.
		@param IsApprobation_Createur Peut Approuver
	*/
	public void setIsApprobation_Createur (boolean IsApprobation_Createur)
	{
		set_ValueNoCheck (COLUMNNAME_IsApprobation_Createur, Boolean.valueOf(IsApprobation_Createur));
	}

	/** Get Peut Approuver.
		@return Peut Approuver
	  */
	public boolean isApprobation_Createur()
	{
		Object oo = get_Value(COLUMNNAME_IsApprobation_Createur);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Approuvé(e).
		@param IsApprouve Approuvé(e)
	*/
	public void setIsApprouve (boolean IsApprouve)
	{
		set_Value (COLUMNNAME_IsApprouve, Boolean.valueOf(IsApprouve));
	}

	/** Get Approuvé(e).
		@return Approuvé(e)
	  */
	public boolean isApprouve()
	{
		Object oo = get_Value(COLUMNNAME_IsApprouve);
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

	/** Set Définir la période de Congé.
		@param IsDefinir_Periode_Conge Définir la période de Congé
	*/
	public void setIsDefinir_Periode_Conge (boolean IsDefinir_Periode_Conge)
	{
		set_Value (COLUMNNAME_IsDefinir_Periode_Conge, Boolean.valueOf(IsDefinir_Periode_Conge));
	}

	/** Get Définir la période de Congé.
		@return Définir la période de Congé
	  */
	public boolean isDefinir_Periode_Conge()
	{
		Object oo = get_Value(COLUMNNAME_IsDefinir_Periode_Conge);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Désapprouvé(e).
		@param IsDesapprouve Désapprouvé(e)
	*/
	public void setIsDesapprouve (boolean IsDesapprouve)
	{
		set_Value (COLUMNNAME_IsDesapprouve, Boolean.valueOf(IsDesapprouve));
	}

	/** Get Désapprouvé(e).
		@return Désapprouvé(e)
	  */
	public boolean isDesapprouve()
	{
		Object oo = get_Value(COLUMNNAME_IsDesapprouve);
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

	/** Set Traité(e).
		@param IsTraitee Traité(e)
	*/
	public void setIsTraitee (boolean IsTraitee)
	{
		set_Value (COLUMNNAME_IsTraitee, Boolean.valueOf(IsTraitee));
	}

	/** Get Traité(e).
		@return Traité(e)
	  */
	public boolean isTraitee()
	{
		Object oo = get_Value(COLUMNNAME_IsTraitee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Peut Valider.
		@param IsValidation_Createur Peut Valider
	*/
	public void setIsValidation_Createur (boolean IsValidation_Createur)
	{
		set_ValueNoCheck (COLUMNNAME_IsValidation_Createur, Boolean.valueOf(IsValidation_Createur));
	}

	/** Get Peut Valider.
		@return Peut Valider
	  */
	public boolean isValidation_Createur()
	{
		Object oo = get_Value(COLUMNNAME_IsValidation_Createur);
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

	/** Set Jour de Conge avec le moins d&#039;Employé.
		@param Jour_Conge_Max_Depart Jour de Conge avec le moins d&#039;Employé
	*/
	public void setJour_Conge_Max_Depart (Timestamp Jour_Conge_Max_Depart)
	{
		set_Value (COLUMNNAME_Jour_Conge_Max_Depart, Jour_Conge_Max_Depart);
	}

	/** Get Jour de Conge avec le moins d&#039;Employé.
		@return Jour de Conge avec le moins d&#039;Employé
	  */
	public Timestamp getJour_Conge_Max_Depart()
	{
		return (Timestamp)get_Value(COLUMNNAME_Jour_Conge_Max_Depart);
	}

	/** Set Nombre de Jour de Congé à Compenser.
		@param Jours_Conge_A_Compenser Nombre de Jour de Congé à Compenser
	*/
	public void setJours_Conge_A_Compenser (int Jours_Conge_A_Compenser)
	{
		set_Value (COLUMNNAME_Jours_Conge_A_Compenser, Integer.valueOf(Jours_Conge_A_Compenser));
	}

	/** Get Nombre de Jour de Congé à Compenser.
		@return Nombre de Jour de Congé à Compenser
	  */
	public int getJours_Conge_A_Compenser()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Jours_Conge_A_Compenser);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé Correspondant à la Sélection.
		@param Jours_Conge_Correspondant Nombre de Jour de Congé Correspondant à la Sélection
	*/
	public void setJours_Conge_Correspondant (int Jours_Conge_Correspondant)
	{
		set_Value (COLUMNNAME_Jours_Conge_Correspondant, Integer.valueOf(Jours_Conge_Correspondant));
	}

	/** Get Nombre de Jour de Congé Correspondant à la Sélection.
		@return Nombre de Jour de Congé Correspondant à la Sélection
	  */
	public int getJours_Conge_Correspondant()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Jours_Conge_Correspondant);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé Déjà Utilisé.
		@param Jours_Conge_Deja_Utilise Nombre de Jour de Congé Déjà Utilisé
	*/
	public void setJours_Conge_Deja_Utilise (int Jours_Conge_Deja_Utilise)
	{
		set_Value (COLUMNNAME_Jours_Conge_Deja_Utilise, Integer.valueOf(Jours_Conge_Deja_Utilise));
	}

	/** Get Nombre de Jour de Congé Déjà Utilisé.
		@return Nombre de Jour de Congé Déjà Utilisé
	  */
	public int getJours_Conge_Deja_Utilise()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Jours_Conge_Deja_Utilise);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé Total.
		@param Jours_Conge_Total Nombre de Jour de Congé Total
	*/
	public void setJours_Conge_Total (int Jours_Conge_Total)
	{
		set_Value (COLUMNNAME_Jours_Conge_Total, Integer.valueOf(Jours_Conge_Total));
	}

	/** Get Nombre de Jour de Congé Total.
		@return Nombre de Jour de Congé Total
	  */
	public int getJours_Conge_Total()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Jours_Conge_Total);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Matricule de l&#039;Employé.
		@param Matricule_Employe Matricule de l&#039;Employé
	*/
	public void setMatricule_Employe (String Matricule_Employe)
	{
		set_Value (COLUMNNAME_Matricule_Employe, Matricule_Employe);
	}

	/** Get Matricule de l&#039;Employé.
		@return Matricule de l&#039;Employé
	  */
	public String getMatricule_Employe()
	{
		return (String)get_Value(COLUMNNAME_Matricule_Employe);
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

	/** Set Nombre Total d&#039;Emplyé(e)s du Département.
		@param Nombre_Employe_Departement Nombre Total d&#039;Emplyé(e)s du Département
	*/
	public void setNombre_Employe_Departement (int Nombre_Employe_Departement)
	{
		set_Value (COLUMNNAME_Nombre_Employe_Departement, Integer.valueOf(Nombre_Employe_Departement));
	}

	/** Get Nombre Total d&#039;Emplyé(e)s du Département.
		@return Nombre Total d&#039;Emplyé(e)s du Département
	  */
	public int getNombre_Employe_Departement()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Employe_Departement);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour.
		@param Nombre_Employe_Departement_Hol Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour
	*/
	public void setNombre_Employe_Departement_Hol (int Nombre_Employe_Departement_Hol)
	{
		set_Value (COLUMNNAME_Nombre_Employe_Departement_Hol, Integer.valueOf(Nombre_Employe_Departement_Hol));
	}

	/** Get Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour.
		@return Nombre Max d&#039;Emplyé(e)s du Département en Congé/Jour
	  */
	public int getNombre_Employe_Departement_Hol()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Employe_Departement_Hol);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour(s) d&#039;Absence Compensé sur le Congé.
		@param Nombre_Jour_Compense_Ici Nombre de Jour(s) d&#039;Absence Compensé sur le Congé
	*/
	public void setNombre_Jour_Compense_Ici (int Nombre_Jour_Compense_Ici)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Compense_Ici, Integer.valueOf(Nombre_Jour_Compense_Ici));
	}

	/** Get Nombre de Jour(s) d&#039;Absence Compensé sur le Congé.
		@return Nombre de Jour(s) d&#039;Absence Compensé sur le Congé
	  */
	public int getNombre_Jour_Compense_Ici()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Compense_Ici);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé.
		@param Nombre_Jour_Conge Nombre de Jour de Congé
	*/
	public void setNombre_Jour_Conge (int Nombre_Jour_Conge)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Conge, Integer.valueOf(Nombre_Jour_Conge));
	}

	/** Get Nombre de Jour de Congé.
		@return Nombre de Jour de Congé
	  */
	public int getNombre_Jour_Conge()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Conge);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Notifier.
		@param Notifier Notifier
	*/
	public void setNotifier (String Notifier)
	{
		set_Value (COLUMNNAME_Notifier, Notifier);
	}

	/** Get Notifier.
		@return Notifier
	  */
	public String getNotifier()
	{
		return (String)get_Value(COLUMNNAME_Notifier);
	}

	/** Set Peut Approuver.
		@param PeutApprouver Peut Approuver
	*/
	public void setPeutApprouver (boolean PeutApprouver)
	{
		throw new IllegalArgumentException ("PeutApprouver is virtual column");	}

	/** Get Peut Approuver.
		@return Peut Approuver
	  */
	public boolean isPeutApprouver()
	{
		Object oo = get_Value(COLUMNNAME_PeutApprouver);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Peut Valider.
		@param PeutValider Peut Valider
	*/
	public void setPeutValider (boolean PeutValider)
	{
		throw new IllegalArgumentException ("PeutValider is virtual column");	}

	/** Get Peut Valider.
		@return Peut Valider
	  */
	public boolean isPeutValider()
	{
		Object oo = get_Value(COLUMNNAME_PeutValider);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	public org.eevolution.model.I_HR_Job getPoste_Employe() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getPoste_Employe_ID(), get_TrxName());
	}

	/** Set Poste de l&#039;Employé.
		@param Poste_Employe_ID Poste de l&#039;Employé
	*/
	public void setPoste_Employe_ID (int Poste_Employe_ID)
	{
		if (Poste_Employe_ID < 1)
			set_Value (COLUMNNAME_Poste_Employe_ID, null);
		else
			set_Value (COLUMNNAME_Poste_Employe_ID, Integer.valueOf(Poste_Employe_ID));
	}

	/** Get Poste de l&#039;Employé.
		@return Poste de l&#039;Employé
	  */
	public int getPoste_Employe_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Poste_Employe_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rapport.
		@param Rapport Rapport
	*/
	public void setRapport (String Rapport)
	{
		set_Value (COLUMNNAME_Rapport, Rapport);
	}

	/** Get Rapport.
		@return Rapport
	  */
	public String getRapport()
	{
		return (String)get_Value(COLUMNNAME_Rapport);
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

	/** Set Rejeter.
		@param Rejeter_Createur Rejeter
	*/
	public void setRejeter_Createur (String Rejeter_Createur)
	{
		set_Value (COLUMNNAME_Rejeter_Createur, Rejeter_Createur);
	}

	/** Get Rejeter.
		@return Rejeter
	  */
	public String getRejeter_Createur()
	{
		return (String)get_Value(COLUMNNAME_Rejeter_Createur);
	}

	/** Set Salaire Cotisable .
		@param Salaire_Cotisable Salaire Cotisable 
	*/
	public void setSalaire_Cotisable (BigDecimal Salaire_Cotisable)
	{
		set_Value (COLUMNNAME_Salaire_Cotisable, Salaire_Cotisable);
	}

	/** Get Salaire Cotisable .
		@return Salaire Cotisable 
	  */
	public BigDecimal getSalaire_Cotisable()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salaire_Cotisable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Femme = F */
	public static final String SEX_Femme = "F";
	/** Homme = H */
	public static final String SEX_Homme = "H";
	/** Set Sex.
		@param Sex Sex
	*/
	public void setSex (String Sex)
	{

		set_Value (COLUMNNAME_Sex, Sex);
	}

	/** Get Sex.
		@return Sex	  */
	public String getSex()
	{
		return (String)get_Value(COLUMNNAME_Sex);
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

	/** Set Valider .
		@param Valider_Createur Valider 
	*/
	public void setValider_Createur (String Valider_Createur)
	{
		set_Value (COLUMNNAME_Valider_Createur, Valider_Createur);
	}

	/** Get Valider .
		@return Valider 
	  */
	public String getValider_Createur()
	{
		return (String)get_Value(COLUMNNAME_Valider_Createur);
	}

	/** Set Personnaliser le Rapport.
		@param isRapport_Personnalise Personnaliser le Rapport
	*/
	public void setisRapport_Personnalise (boolean isRapport_Personnalise)
	{
		set_Value (COLUMNNAME_isRapport_Personnalise, Boolean.valueOf(isRapport_Personnalise));
	}

	/** Get Personnaliser le Rapport.
		@return Personnaliser le Rapport
	  */
	public boolean isRapport_Personnalise()
	{
		Object oo = get_Value(COLUMNNAME_isRapport_Personnalise);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}
}