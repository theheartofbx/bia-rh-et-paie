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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;
import org.sitracel.model.I_HR_Ampliation;

/** Generated Model for HR_Punishment
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Punishment")
public class X_HR_Punishment extends PO implements I_HR_Punishment, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250423L;

    /** Standard Constructor */
    public X_HR_Punishment (Properties ctx, int HR_Punishment_ID, String trxName)
    {
      super (ctx, HR_Punishment_ID, trxName);
      /** if (HR_Punishment_ID == 0)
        {
			setC_BPartner_ID (0);
			setDate_DE (new Timestamp( System.currentTimeMillis() ));
			setDelai_Reponse_DE_ID (0);
			setDemande_Explication_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Sanction_ID (0);
			setHR_Punishment_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Punishment (Properties ctx, int HR_Punishment_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Punishment_ID, trxName, virtualColumns);
      /** if (HR_Punishment_ID == 0)
        {
			setC_BPartner_ID (0);
			setDate_DE (new Timestamp( System.currentTimeMillis() ));
			setDelai_Reponse_DE_ID (0);
			setDemande_Explication_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Sanction_ID (0);
			setHR_Punishment_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Punishment (Properties ctx, String HR_Punishment_UU, String trxName)
    {
      super (ctx, HR_Punishment_UU, trxName);
      /** if (HR_Punishment_UU == null)
        {
			setC_BPartner_ID (0);
			setDate_DE (new Timestamp( System.currentTimeMillis() ));
			setDelai_Reponse_DE_ID (0);
			setDemande_Explication_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Sanction_ID (0);
			setHR_Punishment_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Punishment (Properties ctx, String HR_Punishment_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Punishment_UU, trxName, virtualColumns);
      /** if (HR_Punishment_UU == null)
        {
			setC_BPartner_ID (0);
			setDate_DE (new Timestamp( System.currentTimeMillis() ));
			setDelai_Reponse_DE_ID (0);
			setDemande_Explication_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setEmission_Sanction_ID (0);
			setHR_Punishment_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Punishment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Punishment[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	/** Set Date d&#039;Emission de la Demande d&#039;Explication.
		@param Date_DE Date d&#039;Emission de la Demande d&#039;Explication
	*/
	public void setDate_DE (Timestamp Date_DE)
	{
		set_ValueNoCheck (COLUMNNAME_Date_DE, Date_DE);
	}

	/** Get Date d&#039;Emission de la Demande d&#039;Explication.
		@return Date d&#039;Emission de la Demande d&#039;Explication
	  */
	public Timestamp getDate_DE()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_DE);
	}

	/** Set Date de Début d&#039;Application.
		@param Date_Debut_Application Date de Début d&#039;Application
	*/
	public void setDate_Debut_Application (Timestamp Date_Debut_Application)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut_Application, Date_Debut_Application);
	}

	/** Get Date de Début d&#039;Application.
		@return Date de Début d&#039;Application
	  */
	public Timestamp getDate_Debut_Application()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut_Application);
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

	/** Set Date de Fin d&#039;Application.
		@param Date_Fin_Application Date de Fin d&#039;Application
	*/
	public void setDate_Fin_Application (Timestamp Date_Fin_Application)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin_Application, Date_Fin_Application);
	}

	/** Get Date de Fin d&#039;Application.
		@return Date de Fin d&#039;Application
	  */
	public Timestamp getDate_Fin_Application()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin_Application);
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

	/** Set Date Réponse Demande d&#039;Explication.
		@param Date_Reponse_DE Date Réponse Demande d&#039;Explication
	*/
	public void setDate_Reponse_DE (Timestamp Date_Reponse_DE)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Reponse_DE, Date_Reponse_DE);
	}

	/** Get Date Réponse Demande d&#039;Explication.
		@return Date Réponse Demande d&#039;Explication
	  */
	public Timestamp getDate_Reponse_DE()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Reponse_DE);
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

	public I_HR_Delai_Reponse getDelai_Reponse_DE() throws RuntimeException
	{
		return (I_HR_Delai_Reponse)MTable.get(getCtx(), I_HR_Delai_Reponse.Table_ID)
			.getPO(getDelai_Reponse_DE_ID(), get_TrxName());
	}

	/** Set Délai de Réponse de la Demande d&#039;Explication.
		@param Delai_Reponse_DE_ID Délai de Réponse de la Demande d&#039;Explication
	*/
	public void setDelai_Reponse_DE_ID (int Delai_Reponse_DE_ID)
	{
		if (Delai_Reponse_DE_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Delai_Reponse_DE_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Delai_Reponse_DE_ID, Integer.valueOf(Delai_Reponse_DE_ID));
	}

	/** Get Délai de Réponse de la Demande d&#039;Explication.
		@return Délai de Réponse de la Demande d&#039;Explication
	  */
	public int getDelai_Reponse_DE_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Delai_Reponse_DE_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Demande_Explication getDemande_Explication() throws RuntimeException
	{
		return (I_HR_Demande_Explication)MTable.get(getCtx(), I_HR_Demande_Explication.Table_ID)
			.getPO(getDemande_Explication_ID(), get_TrxName());
	}

	/** Set Demande d&#039;Explication.
		@param Demande_Explication_ID Demande d&#039;Explication
	*/
	public void setDemande_Explication_ID (int Demande_Explication_ID)
	{
		if (Demande_Explication_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Demande_Explication_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Demande_Explication_ID, Integer.valueOf(Demande_Explication_ID));
	}

	/** Get Demande d&#039;Explication.
		@return Demande d&#039;Explication
	  */
	public int getDemande_Explication_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Demande_Explication_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_HR_Sanction_Autorisation getEmission_Sanction() throws RuntimeException
	{
		return (I_HR_Sanction_Autorisation)MTable.get(getCtx(), I_HR_Sanction_Autorisation.Table_ID)
			.getPO(getEmission_Sanction_ID(), get_TrxName());
	}

	/** Set Type de Sanction.
		@param Emission_Sanction_ID Type de Sanction
	*/
	public void setEmission_Sanction_ID (int Emission_Sanction_ID)
	{
		if (Emission_Sanction_ID < 1)
			set_Value (COLUMNNAME_Emission_Sanction_ID, null);
		else
			set_Value (COLUMNNAME_Emission_Sanction_ID, Integer.valueOf(Emission_Sanction_ID));
	}

	/** Get Type de Sanction.
		@return Type de Sanction
	  */
	public int getEmission_Sanction_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emission_Sanction_ID);
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

	public I_HR_Duree_Sanction getHR_Duree_Sanction() throws RuntimeException
	{
		return (I_HR_Duree_Sanction)MTable.get(getCtx(), I_HR_Duree_Sanction.Table_ID)
			.getPO(getHR_Duree_Sanction_ID(), get_TrxName());
	}

	/** Set Durée Sanction.
		@param HR_Duree_Sanction_ID Durée Sanction
	*/
	public void setHR_Duree_Sanction_ID (int HR_Duree_Sanction_ID)
	{
		if (HR_Duree_Sanction_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Duree_Sanction_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Duree_Sanction_ID, Integer.valueOf(HR_Duree_Sanction_ID));
	}

	/** Get Durée Sanction.
		@return Durée Sanction	  */
	public int getHR_Duree_Sanction_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Duree_Sanction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Punishment.
		@param HR_Punishment_ID Punishment
	*/
	public void setHR_Punishment_ID (int HR_Punishment_ID)
	{
		if (HR_Punishment_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Punishment_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Punishment_ID, Integer.valueOf(HR_Punishment_ID));
	}

	/** Get Punishment.
		@return Punishment	  */
	public int getHR_Punishment_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Punishment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Punishment_UU.
		@param HR_Punishment_UU HR_Punishment_UU
	*/
	public void setHR_Punishment_UU (String HR_Punishment_UU)
	{
		set_Value (COLUMNNAME_HR_Punishment_UU, HR_Punishment_UU);
	}

	/** Get HR_Punishment_UU.
		@return HR_Punishment_UU	  */
	public String getHR_Punishment_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Punishment_UU);
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
		@param IsApprobation Peut Approuver
	*/
	public void setIsApprobation (boolean IsApprobation)
	{
		throw new IllegalArgumentException ("IsApprobation is virtual column");	}

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

	/** Set Entraîne le Licenciement.
		@param IsLicenciement Entraîne le Licenciement
	*/
	public void setIsLicenciement (boolean IsLicenciement)
	{
		set_Value (COLUMNNAME_IsLicenciement, Boolean.valueOf(IsLicenciement));
	}

	/** Get Entraîne le Licenciement.
		@return Entraîne le Licenciement
	  */
	public boolean isLicenciement()
	{
		Object oo = get_Value(COLUMNNAME_IsLicenciement);
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

	/** Set Entraîne une Période de Suspension.
		@param IsPeriodSuspension Entraîne une Période de Suspension
	*/
	public void setIsPeriodSuspension (boolean IsPeriodSuspension)
	{
		set_Value (COLUMNNAME_IsPeriodSuspension, Boolean.valueOf(IsPeriodSuspension));
	}

	/** Get Entraîne une Période de Suspension.
		@return Entraîne une Période de Suspension
	  */
	public boolean isPeriodSuspension()
	{
		Object oo = get_Value(COLUMNNAME_IsPeriodSuspension);
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
		@param IsValidation Peut Valider
	*/
	public void setIsValidation (boolean IsValidation)
	{
		throw new IllegalArgumentException ("IsValidation is virtual column");	}

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

	/** Set Motif de la demande d&#039;Expliaction.
		@param Motif_Demande_Explication Motif de la demande d&#039;Expliaction
	*/
	public void setMotif_Demande_Explication (String Motif_Demande_Explication)
	{
		set_Value (COLUMNNAME_Motif_Demande_Explication, Motif_Demande_Explication);
	}

	/** Get Motif de la demande d&#039;Expliaction.
		@return Motif de la demande d&#039;Expliaction
	  */
	public String getMotif_Demande_Explication()
	{
		return (String)get_Value(COLUMNNAME_Motif_Demande_Explication);
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

	/** Set Réponse à la Demande d&#039;Explication.
		@param Reponse_Demande_Explication Réponse à la Demande d&#039;Explication
	*/
	public void setReponse_Demande_Explication (String Reponse_Demande_Explication)
	{
		set_ValueNoCheck (COLUMNNAME_Reponse_Demande_Explication, Reponse_Demande_Explication);
	}

	/** Get Réponse à la Demande d&#039;Explication.
		@return Réponse à la Demande d&#039;Explication
	  */
	public String getReponse_Demande_Explication()
	{
		return (String)get_Value(COLUMNNAME_Reponse_Demande_Explication);
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