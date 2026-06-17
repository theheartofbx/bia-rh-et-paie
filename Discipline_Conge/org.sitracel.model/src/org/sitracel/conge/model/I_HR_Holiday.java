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
package org.sitracel.conge.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;
import org.sitracel.model.I_HR_Ampliation;

/** Generated Interface for HR_Holiday
 *  @author iDempiere (generated)
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Holiday
{

    /** TableName=HR_Holiday */
    public static final String Table_Name = "HR_Holiday";

    /** AD_Table_ID=1004142 */
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

    /** Column name Actualiser_Absences */
    public static final String COLUMNNAME_Actualiser_Absences = "Actualiser_Absences";

	/** Set Actualiser Absences	  */
	public void setActualiser_Absences (String Actualiser_Absences);

	/** Get Actualiser Absences	  */
	public String getActualiser_Absences();

    /** Column name Annee_Anciennete */
    public static final String COLUMNNAME_Annee_Anciennete = "Annee_Anciennete";

	/** Set Nombre d&#039;
Année d&#039;
Ancienneté.
	  * Nombre d&#039;
Année d&#039;
Ancienneté
	  */
	public void setAnnee_Anciennete (int Annee_Anciennete);

	/** Get Nombre d&#039;
Année d&#039;
Ancienneté.
	  * Nombre d&#039;
Année d&#039;
Ancienneté
	  */
	public int getAnnee_Anciennete();

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

    /** Column name Date_Absence_Compense_Debut */
    public static final String COLUMNNAME_Date_Absence_Compense_Debut = "Date_Absence_Compense_Debut";

	/** Set Dates des Absences Compensés en Début de Congé.
	  * Dates des Absences Compensés en Début de Congé
	  */
	public void setDate_Absence_Compense_Debut (String Date_Absence_Compense_Debut);

	/** Get Dates des Absences Compensés en Début de Congé.
	  * Dates des Absences Compensés en Début de Congé
	  */
	public String getDate_Absence_Compense_Debut();

    /** Column name Date_Absence_Compense_Fin */
    public static final String COLUMNNAME_Date_Absence_Compense_Fin = "Date_Absence_Compense_Fin";

	/** Set Dates des Absences Compensés en Fin de Congé.
	  * Dates des Absences Compensés en Fin de Congé
	  */
	public void setDate_Absence_Compense_Fin (String Date_Absence_Compense_Fin);

	/** Get Dates des Absences Compensés en Fin de Congé.
	  * Dates des Absences Compensés en Fin de Congé
	  */
	public String getDate_Absence_Compense_Fin();

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

    /** Column name Date_Debut_Ajustee */
    public static final String COLUMNNAME_Date_Debut_Ajustee = "Date_Debut_Ajustee";

	/** Set Date de Début Ajustée.
	  * Date de Début Ajustée
	  */
	public void setDate_Debut_Ajustee (Timestamp Date_Debut_Ajustee);

	/** Get Date de Début Ajustée.
	  * Date de Début Ajustée
	  */
	public Timestamp getDate_Debut_Ajustee();

    /** Column name Date_Debut_Compensee */
    public static final String COLUMNNAME_Date_Debut_Compensee = "Date_Debut_Compensee";

	/** Set Date de Début Compensée.
	  * Date de Début Compensée
	  */
	public void setDate_Debut_Compensee (Timestamp Date_Debut_Compensee);

	/** Get Date de Début Compensée.
	  * Date de Début Compensée
	  */
	public Timestamp getDate_Debut_Compensee();

    /** Column name Date_Debut_Effective */
    public static final String COLUMNNAME_Date_Debut_Effective = "Date_Debut_Effective";

	/** Set Date de Début Effective.
	  * Date de Début Effective
	  */
	public void setDate_Debut_Effective (Timestamp Date_Debut_Effective);

	/** Get Date de Début Effective.
	  * Date de Début Effective
	  */
	public Timestamp getDate_Debut_Effective();

    /** Column name Date_Debut_Souhaitee */
    public static final String COLUMNNAME_Date_Debut_Souhaitee = "Date_Debut_Souhaitee";

	/** Set Date de Début Souhaitée.
	  * Date de Début Souhaitée
	  */
	public void setDate_Debut_Souhaitee (Timestamp Date_Debut_Souhaitee);

	/** Get Date de Début Souhaitée.
	  * Date de Début Souhaitée
	  */
	public Timestamp getDate_Debut_Souhaitee();

    /** Column name Date_Dernier_Conge */
    public static final String COLUMNNAME_Date_Dernier_Conge = "Date_Dernier_Conge";

	/** Set Date du dernier Congé.
	  * Date du dernier Congé
	  */
	public void setDate_Dernier_Conge (Timestamp Date_Dernier_Conge);

	/** Get Date du dernier Congé.
	  * Date du dernier Congé
	  */
	public Timestamp getDate_Dernier_Conge();

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

    /** Column name Date_Fin_Ajustee */
    public static final String COLUMNNAME_Date_Fin_Ajustee = "Date_Fin_Ajustee";

	/** Set Date de Fin Ajustée.
	  * Date de Fin Ajustée
	  */
	public void setDate_Fin_Ajustee (Timestamp Date_Fin_Ajustee);

	/** Get Date de Fin Ajustée.
	  * Date de Fin Ajustée
	  */
	public Timestamp getDate_Fin_Ajustee();

    /** Column name Date_Fin_Compensee */
    public static final String COLUMNNAME_Date_Fin_Compensee = "Date_Fin_Compensee";

	/** Set Date de Fin Compensée.
	  * Date de Fin Compensée
	  */
	public void setDate_Fin_Compensee (Timestamp Date_Fin_Compensee);

	/** Get Date de Fin Compensée.
	  * Date de Fin Compensée
	  */
	public Timestamp getDate_Fin_Compensee();

    /** Column name Date_Fin_Effective */
    public static final String COLUMNNAME_Date_Fin_Effective = "Date_Fin_Effective";

	/** Set Date de Fin Effective.
	  * Date de Fin Effective
	  */
	public void setDate_Fin_Effective (Timestamp Date_Fin_Effective);

	/** Get Date de Fin Effective.
	  * Date de Fin Effective
	  */
	public Timestamp getDate_Fin_Effective();

    /** Column name Date_Fin_Souhaitee */
    public static final String COLUMNNAME_Date_Fin_Souhaitee = "Date_Fin_Souhaitee";

	/** Set Date de Fin Souhaitée.
	  * Date de Fin Souhaitée
	  */
	public void setDate_Fin_Souhaitee (Timestamp Date_Fin_Souhaitee);

	/** Get Date de Fin Souhaitée.
	  * Date de Fin Souhaitée
	  */
	public Timestamp getDate_Fin_Souhaitee();

    /** Column name Date_Probable_Accouchement */
    public static final String COLUMNNAME_Date_Probable_Accouchement = "Date_Probable_Accouchement";

	/** Set Date Probable d&#039;
Accouchement.
	  * Date Probable d&#039;
Accouchement
	  */
	public void setDate_Probable_Accouchement (Timestamp Date_Probable_Accouchement);

	/** Get Date Probable d&#039;
Accouchement.
	  * Date Probable d&#039;
Accouchement
	  */
	public Timestamp getDate_Probable_Accouchement();

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

    /** Column name Disponibilite_Departement */
    public static final String COLUMNNAME_Disponibilite_Departement = "Disponibilite_Departement";

	/** Set Disponibilité du Département si la Demande est Acceptée.
	  * Disponibilité du Département si la Demande est Acceptée
	  */
	public void setDisponibilite_Departement (BigDecimal Disponibilite_Departement);

	/** Get Disponibilité du Département si la Demande est Acceptée.
	  * Disponibilité du Département si la Demande est Acceptée
	  */
	public BigDecimal getDisponibilite_Departement();

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

    /** Column name Emission_Conge_Employe_ID */
    public static final String COLUMNNAME_Emission_Conge_Employe_ID = "Emission_Conge_Employe_ID";

	/** Set Type de Congé.
	  * Type de Congé
	  */
	public void setEmission_Conge_Employe_ID (int Emission_Conge_Employe_ID);

	/** Get Type de Congé.
	  * Type de Congé
	  */
	public int getEmission_Conge_Employe_ID();

	public I_HR_Type_Conge getEmission_Conge_Employe() throws RuntimeException;

    /** Column name Emission_Conge_ID */
    public static final String COLUMNNAME_Emission_Conge_ID = "Emission_Conge_ID";

	/** Set Type de Congé.
	  * Type de Congé
	  */
	public void setEmission_Conge_ID (int Emission_Conge_ID);

	/** Get Type de Congé.
	  * Type de Congé
	  */
	public int getEmission_Conge_ID();

	public I_HR_Type_Conge getEmission_Conge() throws RuntimeException;

    /** Column name Emission_Conge_Responsable_ID */
    public static final String COLUMNNAME_Emission_Conge_Responsable_ID = "Emission_Conge_Responsable_ID";

	/** Set Type de Congé.
	  * Type de Congé
	  */
	public void setEmission_Conge_Responsable_ID (int Emission_Conge_Responsable_ID);

	/** Get Type de Congé.
	  * Type de Congé
	  */
	public int getEmission_Conge_Responsable_ID();

	public I_HR_Autorisation_Conge getEmission_Conge_Responsable() throws RuntimeException;

    /** Column name Enfant_Conge */
    public static final String COLUMNNAME_Enfant_Conge = "Enfant_Conge";

	/** Set Enfants Comptabilisés pour Congé.
	  * Enfants Comptabilisés pour Congé
	  */
	public void setEnfant_Conge (int Enfant_Conge);

	/** Get Enfants Comptabilisés pour Congé.
	  * Enfants Comptabilisés pour Congé
	  */
	public int getEnfant_Conge();

    /** Column name HR_Ampliation_ID */
    public static final String COLUMNNAME_HR_Ampliation_ID = "HR_Ampliation_ID";

	/** Set Ampliation	  */
	public void setHR_Ampliation_ID (int HR_Ampliation_ID);

	/** Get Ampliation	  */
	public int getHR_Ampliation_ID();

	public I_HR_Ampliation getHR_Ampliation() throws RuntimeException;

    /** Column name HR_Holiday_ID */
    public static final String COLUMNNAME_HR_Holiday_ID = "HR_Holiday_ID";

	/** Set Congé	  */
	public void setHR_Holiday_ID (int HR_Holiday_ID);

	/** Get Congé	  */
	public int getHR_Holiday_ID();

    /** Column name HR_Holiday_UU */
    public static final String COLUMNNAME_HR_Holiday_UU = "HR_Holiday_UU";

	/** Set HR_Holiday_UU	  */
	public void setHR_Holiday_UU (String HR_Holiday_UU);

	/** Get HR_Holiday_UU	  */
	public String getHR_Holiday_UU();

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

    /** Column name IsCongeAnnuel */
    public static final String COLUMNNAME_IsCongeAnnuel = "IsCongeAnnuel";

	/** Set Est déduit des jours de congé annuel.
	  * Est déduit des jours de congé annuel
	  */
	public void setIsCongeAnnuel (boolean IsCongeAnnuel);

	/** Get Est déduit des jours de congé annuel.
	  * Est déduit des jours de congé annuel
	  */
	public boolean isCongeAnnuel();

    /** Column name IsDefinir_Periode_Conge */
    public static final String COLUMNNAME_IsDefinir_Periode_Conge = "IsDefinir_Periode_Conge";

	/** Set Définir la période de Congé.
	  * Définir la période de Congé
	  */
	public void setIsDefinir_Periode_Conge (boolean IsDefinir_Periode_Conge);

	/** Get Définir la période de Congé.
	  * Définir la période de Congé
	  */
	public boolean isDefinir_Periode_Conge();

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

    /** Column name Jour_Conge_Max_Depart */
    public static final String COLUMNNAME_Jour_Conge_Max_Depart = "Jour_Conge_Max_Depart";

	/** Set Jour de Conge avec le moins d&#039;
Employé.
	  * Jour de Conge avec le moins d&#039;
Employé
	  */
	public void setJour_Conge_Max_Depart (Timestamp Jour_Conge_Max_Depart);

	/** Get Jour de Conge avec le moins d&#039;
Employé.
	  * Jour de Conge avec le moins d&#039;
Employé
	  */
	public Timestamp getJour_Conge_Max_Depart();

    /** Column name Jours_Conge_A_Compenser */
    public static final String COLUMNNAME_Jours_Conge_A_Compenser = "Jours_Conge_A_Compenser";

	/** Set Nombre de Jour de Congé à Compenser.
	  * Nombre de Jour de Congé à Compenser
	  */
	public void setJours_Conge_A_Compenser (int Jours_Conge_A_Compenser);

	/** Get Nombre de Jour de Congé à Compenser.
	  * Nombre de Jour de Congé à Compenser
	  */
	public int getJours_Conge_A_Compenser();

    /** Column name Jours_Conge_Correspondant */
    public static final String COLUMNNAME_Jours_Conge_Correspondant = "Jours_Conge_Correspondant";

	/** Set Nombre de Jour de Congé Correspondant à la Sélection.
	  * Nombre de Jour de Congé Correspondant à la Sélection
	  */
	public void setJours_Conge_Correspondant (int Jours_Conge_Correspondant);

	/** Get Nombre de Jour de Congé Correspondant à la Sélection.
	  * Nombre de Jour de Congé Correspondant à la Sélection
	  */
	public int getJours_Conge_Correspondant();

    /** Column name Jours_Conge_Deja_Utilise */
    public static final String COLUMNNAME_Jours_Conge_Deja_Utilise = "Jours_Conge_Deja_Utilise";

	/** Set Nombre de Jour de Congé Déjà Utilisé.
	  * Nombre de Jour de Congé Déjà Utilisé
	  */
	public void setJours_Conge_Deja_Utilise (int Jours_Conge_Deja_Utilise);

	/** Get Nombre de Jour de Congé Déjà Utilisé.
	  * Nombre de Jour de Congé Déjà Utilisé
	  */
	public int getJours_Conge_Deja_Utilise();

    /** Column name Jours_Conge_Total */
    public static final String COLUMNNAME_Jours_Conge_Total = "Jours_Conge_Total";

	/** Set Nombre de Jour de Congé Total.
	  * Nombre de Jour de Congé Total
	  */
	public void setJours_Conge_Total (int Jours_Conge_Total);

	/** Get Nombre de Jour de Congé Total.
	  * Nombre de Jour de Congé Total
	  */
	public int getJours_Conge_Total();

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

    /** Column name Nombre_Employe_Departement */
    public static final String COLUMNNAME_Nombre_Employe_Departement = "Nombre_Employe_Departement";

	/** Set Nombre Total d&#039;
Emplyé(e)s du Département.
	  * Nombre Total d&#039;
Emplyé(e)s du Département
	  */
	public void setNombre_Employe_Departement (int Nombre_Employe_Departement);

	/** Get Nombre Total d&#039;
Emplyé(e)s du Département.
	  * Nombre Total d&#039;
Emplyé(e)s du Département
	  */
	public int getNombre_Employe_Departement();

    /** Column name Nombre_Employe_Departement_Hol */
    public static final String COLUMNNAME_Nombre_Employe_Departement_Hol = "Nombre_Employe_Departement_Hol";

	/** Set Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour.
	  * Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour
	  */
	public void setNombre_Employe_Departement_Hol (int Nombre_Employe_Departement_Hol);

	/** Get Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour.
	  * Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour
	  */
	public int getNombre_Employe_Departement_Hol();

    /** Column name Nombre_Jour_Compense_Ici */
    public static final String COLUMNNAME_Nombre_Jour_Compense_Ici = "Nombre_Jour_Compense_Ici";

	/** Set Nombre de Jour(s) d&#039;
Absence Compensé sur le Congé.
	  * Nombre de Jour(s) d&#039;
Absence Compensé sur le Congé
	  */
	public void setNombre_Jour_Compense_Ici (int Nombre_Jour_Compense_Ici);

	/** Get Nombre de Jour(s) d&#039;
Absence Compensé sur le Congé.
	  * Nombre de Jour(s) d&#039;
Absence Compensé sur le Congé
	  */
	public int getNombre_Jour_Compense_Ici();

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

    /** Column name Sex */
    public static final String COLUMNNAME_Sex = "Sex";

	/** Set Sex	  */
	public void setSex (String Sex);

	/** Get Sex	  */
	public String getSex();

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
}
