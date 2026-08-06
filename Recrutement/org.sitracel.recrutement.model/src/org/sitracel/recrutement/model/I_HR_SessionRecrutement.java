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
package org.sitracel.recrutement.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_SessionRecrutement
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_SessionRecrutement 
{

    /** TableName=HR_SessionRecrutement */
    public static final String Table_Name = "HR_SessionRecrutement";

    /** AD_Table_ID=1007475 */
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

    /** Column name Date_Creation */
    public static final String COLUMNNAME_Date_Creation = "Date_Creation";

	/** Set Date de Création	  */
	public void setDate_Creation (Timestamp Date_Creation);

	/** Get Date de Création	  */
	public Timestamp getDate_Creation();

    /** Column name Date_Debut */
    public static final String COLUMNNAME_Date_Debut = "Date_Debut";

	/** Set à Partir de :.
	  * à Partir de :
	  */
	public void setDate_Debut (Timestamp Date_Debut);

	/** Get à Partir de :.
	  * à Partir de :
	  */
	public Timestamp getDate_Debut();

    /** Column name Date_Fin */
    public static final String COLUMNNAME_Date_Fin = "Date_Fin";

	/** Set Jusqu&#039;
au :.
	  * Jusqu&#039;
au :
	  */
	public void setDate_Fin (Timestamp Date_Fin);

	/** Get Jusqu&#039;
au :.
	  * Jusqu&#039;
au :
	  */
	public Timestamp getDate_Fin();

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

    /** Column name HR_OffreEmploi_ID */
    public static final String COLUMNNAME_HR_OffreEmploi_ID = "HR_OffreEmploi_ID";

	/** Set Offre d&#039;
Emploi	  */
	public void setHR_OffreEmploi_ID (int HR_OffreEmploi_ID);

	/** Get Offre d&#039;
Emploi	  */
	public int getHR_OffreEmploi_ID();

	public I_HR_OffreEmploi getHR_OffreEmploi() throws RuntimeException;

    /** Column name HR_OffreTestEvaluation_ID */
    public static final String COLUMNNAME_HR_OffreTestEvaluation_ID = "HR_OffreTestEvaluation_ID";

	/** Set Test d&#039;
Évaluation	  */
	public void setHR_OffreTestEvaluation_ID (int HR_OffreTestEvaluation_ID);

	/** Get Test d&#039;
Évaluation	  */
	public int getHR_OffreTestEvaluation_ID();

	public I_HR_OffreTestEvaluation getHR_OffreTestEvaluation() throws RuntimeException;

    /** Column name HR_SessionRecrutement_ID */
    public static final String COLUMNNAME_HR_SessionRecrutement_ID = "HR_SessionRecrutement_ID";

	/** Set Session de Recrutement	  */
	public void setHR_SessionRecrutement_ID (int HR_SessionRecrutement_ID);

	/** Get Session de Recrutement	  */
	public int getHR_SessionRecrutement_ID();

    /** Column name HR_SessionRecrutement_UU */
    public static final String COLUMNNAME_HR_SessionRecrutement_UU = "HR_SessionRecrutement_UU";

	/** Set HR_SessionRecrutement_UU	  */
	public void setHR_SessionRecrutement_UU (String HR_SessionRecrutement_UU);

	/** Get HR_SessionRecrutement_UU	  */
	public String getHR_SessionRecrutement_UU();

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

    /** Column name Nombre_Candidat */
    public static final String COLUMNNAME_Nombre_Candidat = "Nombre_Candidat";

	/** Set Nombre de Candidat Recherché	  */
	public void setNombre_Candidat (int Nombre_Candidat);

	/** Get Nombre de Candidat Recherché	  */
	public int getNombre_Candidat();

    /** Column name PeutValider */
    public static final String COLUMNNAME_PeutValider = "PeutValider";

	/** Set Peut Valider.
	  * Peut Valider
	  */
	public void setPeutValider (boolean PeutValider);

	/** Get Peut Valider.
	  * Peut Valider
	  */
	public boolean isPeutValider();

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
}
