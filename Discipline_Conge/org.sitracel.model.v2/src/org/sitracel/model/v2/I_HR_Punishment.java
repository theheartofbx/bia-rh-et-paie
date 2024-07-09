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
package org.sitracel.model.v2;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Punishment
 *  @author iDempiere (generated) 
 *  @version Release 11
 */
@SuppressWarnings("all")
public interface I_HR_Punishment 
{

    /** TableName=HR_Punishment */
    public static final String Table_Name = "HR_Punishment";

    /** AD_Table_ID=1000910 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Approuve_Desapprouve_Matricule */
    public static final String COLUMNNAME_Approuve_Desapprouve_Matricule = "Approuve_Desapprouve_Matricule";

	/** Set Approuve/Desapprouvé par (Matricule) :.
	  * Approuve/Desapprouvé par (Matricule) :
	  */
	public void setApprouve_Desapprouve_Matricule (String Approuve_Desapprouve_Matricule);

	/** Get Approuve/Desapprouvé par (Matricule) :.
	  * Approuve/Desapprouvé par (Matricule) :
	  */
	public String getApprouve_Desapprouve_Matricule();

    /** Column name Approuve_Desapprouve_Nom_ID */
    public static final String COLUMNNAME_Approuve_Desapprouve_Nom_ID = "Approuve_Desapprouve_Nom_ID";

	/** Set Approuve/Desapprouvé par (Nom) :.
	  * Approuve/Desapprouvé par (Nom) :
	  */
	public void setApprouve_Desapprouve_Nom_ID (int Approuve_Desapprouve_Nom_ID);

	/** Get Approuve/Desapprouvé par (Nom) :.
	  * Approuve/Desapprouvé par (Nom) :
	  */
	public int getApprouve_Desapprouve_Nom_ID();

	public org.compiere.model.I_C_BPartner getApprouve_Desapprouve_Nom() throws RuntimeException;

    /** Column name Approuve_Desapprouve_Poste_ID */
    public static final String COLUMNNAME_Approuve_Desapprouve_Poste_ID = "Approuve_Desapprouve_Poste_ID";

	/** Set Approuve/Desapprouvé par (Poste) :.
	  * Approuve/Desapprouvé par (Poste) :
	  */
	public void setApprouve_Desapprouve_Poste_ID (int Approuve_Desapprouve_Poste_ID);

	/** Get Approuve/Desapprouvé par (Poste) :.
	  * Approuve/Desapprouvé par (Poste) :
	  */
	public int getApprouve_Desapprouve_Poste_ID();

	public org.eevolution.model.I_HR_Job getApprouve_Desapprouve_Poste() throws RuntimeException;

    /** Column name Approuver */
    public static final String COLUMNNAME_Approuver = "Approuver";

	/** Set Approuver.
	  * Approuver
	  */
	public void setApprouver (String Approuver);

	/** Get Approuver.
	  * Approuver
	  */
	public String getApprouver();

    /** Column name Approuver_Createur */
    public static final String COLUMNNAME_Approuver_Createur = "Approuver_Createur";

	/** Set Approuver.
	  * Approuver
	  */
	public void setApprouver_Createur (String Approuver_Createur);

	/** Get Approuver.
	  * Approuver
	  */
	public String getApprouver_Createur();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Date_Approbation */
    public static final String COLUMNNAME_Date_Approbation = "Date_Approbation";

	/** Set Date Approbation.
	  * Date Approbation
	  */
	public void setDate_Approbation (Timestamp Date_Approbation);

	/** Get Date Approbation.
	  * Date Approbation
	  */
	public Timestamp getDate_Approbation();

    /** Column name Date_DE */
    public static final String COLUMNNAME_Date_DE = "Date_DE";

	/** Set Date d&#039;
Emission de la Demande d&#039;
Explication.
	  * Date d&#039;
Emission de la Demande d&#039;
Explication
	  */
	public void setDate_DE (Timestamp Date_DE);

	/** Get Date d&#039;
Emission de la Demande d&#039;
Explication.
	  * Date d&#039;
Emission de la Demande d&#039;
Explication
	  */
	public Timestamp getDate_DE();

    /** Column name Date_Debut_Application */
    public static final String COLUMNNAME_Date_Debut_Application = "Date_Debut_Application";

	/** Set Date de Début d&#039;
Application.
	  * Date de Début d&#039;
Application
	  */
	public void setDate_Debut_Application (Timestamp Date_Debut_Application);

	/** Get Date de Début d&#039;
Application.
	  * Date de Début d&#039;
Application
	  */
	public Timestamp getDate_Debut_Application();

    /** Column name Date_Desapprobation */
    public static final String COLUMNNAME_Date_Desapprobation = "Date_Desapprobation";

	/** Set Date Désapprobation.
	  * Date Désapprobation
	  */
	public void setDate_Desapprobation (Timestamp Date_Desapprobation);

	/** Get Date Désapprobation.
	  * Date Désapprobation
	  */
	public Timestamp getDate_Desapprobation();

    /** Column name Date_Emission */
    public static final String COLUMNNAME_Date_Emission = "Date_Emission";

	/** Set Date d&#039;
Emission.
	  * Date d&#039;
Emission
	  */
	public void setDate_Emission (Timestamp Date_Emission);

	/** Get Date d&#039;
Emission.
	  * Date d&#039;
Emission
	  */
	public Timestamp getDate_Emission();

    /** Column name Date_Fin_Application */
    public static final String COLUMNNAME_Date_Fin_Application = "Date_Fin_Application";

	/** Set Date de Fin d&#039;
Application.
	  * Date de Fin d&#039;
Application
	  */
	public void setDate_Fin_Application (Timestamp Date_Fin_Application);

	/** Get Date de Fin d&#039;
Application.
	  * Date de Fin d&#039;
Application
	  */
	public Timestamp getDate_Fin_Application();

    /** Column name Date_Rejet */
    public static final String COLUMNNAME_Date_Rejet = "Date_Rejet";

	/** Set Date de Rejet.
	  * Date de Rejet
	  */
	public void setDate_Rejet (Timestamp Date_Rejet);

	/** Get Date de Rejet.
	  * Date de Rejet
	  */
	public Timestamp getDate_Rejet();

    /** Column name Date_Reponse_DE */
    public static final String COLUMNNAME_Date_Reponse_DE = "Date_Reponse_DE";

	/** Set Date Réponse Demande d&#039;
Explication.
	  * Date Réponse Demande d&#039;
Explication
	  */
	public void setDate_Reponse_DE (Timestamp Date_Reponse_DE);

	/** Get Date Réponse Demande d&#039;
Explication.
	  * Date Réponse Demande d&#039;
Explication
	  */
	public Timestamp getDate_Reponse_DE();

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name Date_Validation */
    public static final String COLUMNNAME_Date_Validation = "Date_Validation";

	/** Set Date de Validation.
	  * Date de Validation
	  */
	public void setDate_Validation (Timestamp Date_Validation);

	/** Get Date de Validation.
	  * Date de Validation
	  */
	public Timestamp getDate_Validation();

    /** Column name Delai_Reponse_DE_ID */
    public static final String COLUMNNAME_Delai_Reponse_DE_ID = "Delai_Reponse_DE_ID";

	/** Set Délai de Réponse de la Demande d&#039;
Explication.
	  * Délai de Réponse de la Demande d&#039;
Explication
	  */
	public void setDelai_Reponse_DE_ID (int Delai_Reponse_DE_ID);

	/** Get Délai de Réponse de la Demande d&#039;
Explication.
	  * Délai de Réponse de la Demande d&#039;
Explication
	  */
	public int getDelai_Reponse_DE_ID();

	public I_HR_Delai_Reponse getDelai_Reponse_DE() throws RuntimeException;

    /** Column name Demande_Explication_ID */
    public static final String COLUMNNAME_Demande_Explication_ID = "Demande_Explication_ID";

	/** Set Demande d&#039;
Explication.
	  * Demande d&#039;
Explication
	  */
	public void setDemande_Explication_ID (int Demande_Explication_ID);

	/** Get Demande d&#039;
Explication.
	  * Demande d&#039;
Explication
	  */
	public int getDemande_Explication_ID();

	public I_HR_Demande_Explication getDemande_Explication() throws RuntimeException;

    /** Column name Desapprouver */
    public static final String COLUMNNAME_Desapprouver = "Desapprouver";

	/** Set Désapprouver.
	  * Désapprouver
	  */
	public void setDesapprouver (String Desapprouver);

	/** Get Désapprouver.
	  * Désapprouver
	  */
	public String getDesapprouver();

    /** Column name Desapprouver_Createur */
    public static final String COLUMNNAME_Desapprouver_Createur = "Desapprouver_Createur";

	/** Set Désapprouver.
	  * Désapprouver
	  */
	public void setDesapprouver_Createur (String Desapprouver_Createur);

	/** Get Désapprouver.
	  * Désapprouver
	  */
	public String getDesapprouver_Createur();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Emis_Par_Matricule */
    public static final String COLUMNNAME_Emis_Par_Matricule = "Emis_Par_Matricule";

	/** Set Matricule Emetteur.
	  * Matricule Emetteur
	  */
	public void setEmis_Par_Matricule (String Emis_Par_Matricule);

	/** Get Matricule Emetteur.
	  * Matricule Emetteur
	  */
	public String getEmis_Par_Matricule();

    /** Column name Emis_Par_Nom_ID */
    public static final String COLUMNNAME_Emis_Par_Nom_ID = "Emis_Par_Nom_ID";

	/** Set Nom Emetteur.
	  * Nom Emetteur
	  */
	public void setEmis_Par_Nom_ID (int Emis_Par_Nom_ID);

	/** Get Nom Emetteur.
	  * Nom Emetteur
	  */
	public int getEmis_Par_Nom_ID();

	public org.compiere.model.I_C_BPartner getEmis_Par_Nom() throws RuntimeException;

    /** Column name Emis_Par_Poste_ID */
    public static final String COLUMNNAME_Emis_Par_Poste_ID = "Emis_Par_Poste_ID";

	/** Set Poste Emetteur.
	  * Poste Emetteur
	  */
	public void setEmis_Par_Poste_ID (int Emis_Par_Poste_ID);

	/** Get Poste Emetteur.
	  * Poste Emetteur
	  */
	public int getEmis_Par_Poste_ID();

	public org.eevolution.model.I_HR_Job getEmis_Par_Poste() throws RuntimeException;

    /** Column name Emission_Sanction_ID */
    public static final String COLUMNNAME_Emission_Sanction_ID = "Emission_Sanction_ID";

	/** Set Type de Sanction.
	  * Type de Sanction
	  */
	public void setEmission_Sanction_ID (int Emission_Sanction_ID);

	/** Get Type de Sanction.
	  * Type de Sanction
	  */
	public int getEmission_Sanction_ID();

	public I_HR_Sanction_Autorisation getEmission_Sanction() throws RuntimeException;

    /** Column name HR_Ampliation_ID */
    public static final String COLUMNNAME_HR_Ampliation_ID = "HR_Ampliation_ID";

	/** Set Ampliation	  */
	public void setHR_Ampliation_ID (int HR_Ampliation_ID);

	/** Get Ampliation	  */
	public int getHR_Ampliation_ID();

	public I_HR_Ampliation getHR_Ampliation() throws RuntimeException;

    /** Column name HR_Duree_Sanction_ID */
    public static final String COLUMNNAME_HR_Duree_Sanction_ID = "HR_Duree_Sanction_ID";

	/** Set Durée Sanction	  */
	public void setHR_Duree_Sanction_ID (int HR_Duree_Sanction_ID);

	/** Get Durée Sanction	  */
	public int getHR_Duree_Sanction_ID();

	public I_HR_Duree_Sanction getHR_Duree_Sanction() throws RuntimeException;

    /** Column name HR_Punishment_ID */
    public static final String COLUMNNAME_HR_Punishment_ID = "HR_Punishment_ID";

	/** Set Punishment	  */
	public void setHR_Punishment_ID (int HR_Punishment_ID);

	/** Get Punishment	  */
	public int getHR_Punishment_ID();

    /** Column name HR_Punishment_UU */
    public static final String COLUMNNAME_HR_Punishment_UU = "HR_Punishment_UU";

	/** Set HR_Punishment_UU	  */
	public void setHR_Punishment_UU (String HR_Punishment_UU);

	/** Get HR_Punishment_UU	  */
	public String getHR_Punishment_UU();

    /** Column name Initial */
    public static final String COLUMNNAME_Initial = "Initial";

	/** Set Initial.
	  * Initial
	  */
	public void setInitial (String Initial);

	/** Get Initial.
	  * Initial
	  */
	public String getInitial();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsApprobation */
    public static final String COLUMNNAME_IsApprobation = "IsApprobation";

	/** Set Peut Approuver.
	  * Peut Approuver
	  */
	public void setIsApprobation (boolean IsApprobation);

	/** Get Peut Approuver.
	  * Peut Approuver
	  */
	public boolean isApprobation();

    /** Column name IsApprobation_Createur */
    public static final String COLUMNNAME_IsApprobation_Createur = "IsApprobation_Createur";

	/** Set Peut Approuver.
	  * Peut Approuver
	  */
	public void setIsApprobation_Createur (boolean IsApprobation_Createur);

	/** Get Peut Approuver.
	  * Peut Approuver
	  */
	public boolean isApprobation_Createur();

    /** Column name IsApprouve */
    public static final String COLUMNNAME_IsApprouve = "IsApprouve";

	/** Set Approuvé(e).
	  * Approuvé(e)
	  */
	public void setIsApprouve (boolean IsApprouve);

	/** Get Approuvé(e).
	  * Approuvé(e)
	  */
	public boolean isApprouve();

    /** Column name IsDesapprouve */
    public static final String COLUMNNAME_IsDesapprouve = "IsDesapprouve";

	/** Set Désapprouvé(e).
	  * Désapprouvé(e)
	  */
	public void setIsDesapprouve (boolean IsDesapprouve);

	/** Get Désapprouvé(e).
	  * Désapprouvé(e)
	  */
	public boolean isDesapprouve();

    /** Column name IsLicenciement */
    public static final String COLUMNNAME_IsLicenciement = "IsLicenciement";

	/** Set Entraîne le Licenciement.
	  * Entraîne le Licenciement
	  */
	public void setIsLicenciement (boolean IsLicenciement);

	/** Get Entraîne le Licenciement.
	  * Entraîne le Licenciement
	  */
	public boolean isLicenciement();

    /** Column name IsMessageAlerteDisplayed */
    public static final String COLUMNNAME_IsMessageAlerteDisplayed = "IsMessageAlerteDisplayed";

	/** Set Message d&#039;
Alerte Affiché.
	  * Message d&#039;
Alerte Affiché
	  */
	public void setIsMessageAlerteDisplayed (boolean IsMessageAlerteDisplayed);

	/** Get Message d&#039;
Alerte Affiché.
	  * Message d&#039;
Alerte Affiché
	  */
	public boolean isMessageAlerteDisplayed();

    /** Column name IsPeriodSuspension */
    public static final String COLUMNNAME_IsPeriodSuspension = "IsPeriodSuspension";

	/** Set Entraîne une Période de Suspension.
	  * Entraîne une Période de Suspension
	  */
	public void setIsPeriodSuspension (boolean IsPeriodSuspension);

	/** Get Entraîne une Période de Suspension.
	  * Entraîne une Période de Suspension
	  */
	public boolean isPeriodSuspension();

    /** Column name isRapport_Personnalise */
    public static final String COLUMNNAME_isRapport_Personnalise = "isRapport_Personnalise";

	/** Set Personnaliser le Rapport.
	  * Personnaliser le Rapport
	  */
	public void setisRapport_Personnalise (boolean isRapport_Personnalise);

	/** Get Personnaliser le Rapport.
	  * Personnaliser le Rapport
	  */
	public boolean isRapport_Personnalise();

    /** Column name IsRejetee */
    public static final String COLUMNNAME_IsRejetee = "IsRejetee";

	/** Set Rejeté(e).
	  * Rejeté(e)
	  */
	public void setIsRejetee (boolean IsRejetee);

	/** Get Rejeté(e).
	  * Rejeté(e)
	  */
	public boolean isRejetee();

    /** Column name IsTraitee */
    public static final String COLUMNNAME_IsTraitee = "IsTraitee";

	/** Set Traité(e).
	  * Traité(e)
	  */
	public void setIsTraitee (boolean IsTraitee);

	/** Get Traité(e).
	  * Traité(e)
	  */
	public boolean isTraitee();

    /** Column name IsValidation */
    public static final String COLUMNNAME_IsValidation = "IsValidation";

	/** Set Peut Valider.
	  * Peut Valider
	  */
	public void setIsValidation (boolean IsValidation);

	/** Get Peut Valider.
	  * Peut Valider
	  */
	public boolean isValidation();

    /** Column name IsValidation_Createur */
    public static final String COLUMNNAME_IsValidation_Createur = "IsValidation_Createur";

	/** Set Peut Valider.
	  * Peut Valider
	  */
	public void setIsValidation_Createur (boolean IsValidation_Createur);

	/** Get Peut Valider.
	  * Peut Valider
	  */
	public boolean isValidation_Createur();

    /** Column name IsValidee */
    public static final String COLUMNNAME_IsValidee = "IsValidee";

	/** Set Validé(e).
	  * Validé(e)
	  */
	public void setIsValidee (boolean IsValidee);

	/** Get Validé(e).
	  * Validé(e)
	  */
	public boolean isValidee();

    /** Column name Matricule_Employe */
    public static final String COLUMNNAME_Matricule_Employe = "Matricule_Employe";

	/** Set Matricule de l&#039;
Employé.
	  * Matricule de l&#039;
Employé
	  */
	public void setMatricule_Employe (String Matricule_Employe);

	/** Get Matricule de l&#039;
Employé.
	  * Matricule de l&#039;
Employé
	  */
	public String getMatricule_Employe();

    /** Column name Message_Alerte */
    public static final String COLUMNNAME_Message_Alerte = "Message_Alerte";

	/** Set Message d&#039;
Alerte.
	  * Message d&#039;
Alerte
	  */
	public void setMessage_Alerte (String Message_Alerte);

	/** Get Message d&#039;
Alerte.
	  * Message d&#039;
Alerte
	  */
	public String getMessage_Alerte();

    /** Column name Motif_Demande_Explication */
    public static final String COLUMNNAME_Motif_Demande_Explication = "Motif_Demande_Explication";

	/** Set Motif de la demande d&#039;
Expliaction.
	  * Motif de la demande d&#039;
Expliaction
	  */
	public void setMotif_Demande_Explication (String Motif_Demande_Explication);

	/** Get Motif de la demande d&#039;
Expliaction.
	  * Motif de la demande d&#039;
Expliaction
	  */
	public String getMotif_Demande_Explication();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Notifier */
    public static final String COLUMNNAME_Notifier = "Notifier";

	/** Set Notifier.
	  * Notifier
	  */
	public void setNotifier (String Notifier);

	/** Get Notifier.
	  * Notifier
	  */
	public String getNotifier();

    /** Column name Poste_Employe_ID */
    public static final String COLUMNNAME_Poste_Employe_ID = "Poste_Employe_ID";

	/** Set Poste de l&#039;
Employé.
	  * Poste de l&#039;
Employé
	  */
	public void setPoste_Employe_ID (int Poste_Employe_ID);

	/** Get Poste de l&#039;
Employé.
	  * Poste de l&#039;
Employé
	  */
	public int getPoste_Employe_ID();

	public org.eevolution.model.I_HR_Job getPoste_Employe() throws RuntimeException;

    /** Column name Rapport */
    public static final String COLUMNNAME_Rapport = "Rapport";

	/** Set Rapport.
	  * Rapport
	  */
	public void setRapport (String Rapport);

	/** Get Rapport.
	  * Rapport
	  */
	public String getRapport();

    /** Column name Rejeter */
    public static final String COLUMNNAME_Rejeter = "Rejeter";

	/** Set Rejeter.
	  * Rejeter
	  */
	public void setRejeter (String Rejeter);

	/** Get Rejeter.
	  * Rejeter
	  */
	public String getRejeter();

    /** Column name Rejeter_Createur */
    public static final String COLUMNNAME_Rejeter_Createur = "Rejeter_Createur";

	/** Set Rejeter.
	  * Rejeter
	  */
	public void setRejeter_Createur (String Rejeter_Createur);

	/** Get Rejeter.
	  * Rejeter
	  */
	public String getRejeter_Createur();

    /** Column name Reponse_Demande_Explication */
    public static final String COLUMNNAME_Reponse_Demande_Explication = "Reponse_Demande_Explication";

	/** Set Réponse à la Demande d&#039;
Explication.
	  * Réponse à la Demande d&#039;
Explication
	  */
	public void setReponse_Demande_Explication (String Reponse_Demande_Explication);

	/** Get Réponse à la Demande d&#039;
Explication.
	  * Réponse à la Demande d&#039;
Explication
	  */
	public String getReponse_Demande_Explication();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Valider */
    public static final String COLUMNNAME_Valider = "Valider";

	/** Set Valider .
	  * Valider 
	  */
	public void setValider (String Valider);

	/** Get Valider .
	  * Valider 
	  */
	public String getValider();

    /** Column name Valider_Createur */
    public static final String COLUMNNAME_Valider_Createur = "Valider_Createur";

	/** Set Valider .
	  * Valider 
	  */
	public void setValider_Createur (String Valider_Createur);

	/** Get Valider .
	  * Valider 
	  */
	public String getValider_Createur();

    /** Column name Valide_Rejete_Par_Matricule */
    public static final String COLUMNNAME_Valide_Rejete_Par_Matricule = "Valide_Rejete_Par_Matricule";

	/** Set Validé/rejeté par (Matricule) :.
	  * Validé/rejeté par (Matricule) :
	  */
	public void setValide_Rejete_Par_Matricule (String Valide_Rejete_Par_Matricule);

	/** Get Validé/rejeté par (Matricule) :.
	  * Validé/rejeté par (Matricule) :
	  */
	public String getValide_Rejete_Par_Matricule();

    /** Column name Valide_Rejete_Par_Nom_ID */
    public static final String COLUMNNAME_Valide_Rejete_Par_Nom_ID = "Valide_Rejete_Par_Nom_ID";

	/** Set Validé/rejeté par (Nom) :.
	  * Validé/rejeté par (Nom) :
	  */
	public void setValide_Rejete_Par_Nom_ID (int Valide_Rejete_Par_Nom_ID);

	/** Get Validé/rejeté par (Nom) :.
	  * Validé/rejeté par (Nom) :
	  */
	public int getValide_Rejete_Par_Nom_ID();

	public org.compiere.model.I_C_BPartner getValide_Rejete_Par_Nom() throws RuntimeException;

    /** Column name Valide_Rejete_Par_Poste_ID */
    public static final String COLUMNNAME_Valide_Rejete_Par_Poste_ID = "Valide_Rejete_Par_Poste_ID";

	/** Set Validé/rejeté par (Poste) :.
	  * Validé/rejeté par (Poste) :
	  */
	public void setValide_Rejete_Par_Poste_ID (int Valide_Rejete_Par_Poste_ID);

	/** Get Validé/rejeté par (Poste) :.
	  * Validé/rejeté par (Poste) :
	  */
	public int getValide_Rejete_Par_Poste_ID();

	public org.eevolution.model.I_HR_Job getValide_Rejete_Par_Poste() throws RuntimeException;
}
