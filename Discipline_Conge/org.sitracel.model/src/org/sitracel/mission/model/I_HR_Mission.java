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
package org.sitracel.mission.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;
import org.sitracel.model.I_HR_Categorie_Responsabilite;

/** Generated Interface for HR_Mission
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Mission 
{

    /** TableName=HR_Mission */
    public static final String Table_Name = "HR_Mission";

    /** AD_Table_ID=1002930 */
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

    /** Column name Budget_Ajuste */
    public static final String COLUMNNAME_Budget_Ajuste = "Budget_Ajuste";

	/** Set Budget Ajusté	  */
	public void setBudget_Ajuste (BigDecimal Budget_Ajuste);

	/** Get Budget Ajusté	  */
	public BigDecimal getBudget_Ajuste();

    /** Column name Budget_Previsionnel */
    public static final String COLUMNNAME_Budget_Previsionnel = "Budget_Previsionnel";

	/** Set Budget Prévisionnel	  */
	public void setBudget_Previsionnel (BigDecimal Budget_Previsionnel);

	/** Get Budget Prévisionnel	  */
	public BigDecimal getBudget_Previsionnel();

    /** Column name C_City_ID */
    public static final String COLUMNNAME_C_City_ID = "C_City_ID";

	/** Set City.
	  * City
	  */
	public void setC_City_ID (int C_City_ID);

	/** Get City.
	  * City
	  */
	public int getC_City_ID();

	public org.compiere.model.I_C_City getC_City() throws RuntimeException;

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException;

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

    /** Column name HR_Etat_Mission_ID */
    public static final String COLUMNNAME_HR_Etat_Mission_ID = "HR_Etat_Mission_ID";

	/** Set État de la Mission	  */
	public void setHR_Etat_Mission_ID (int HR_Etat_Mission_ID);

	/** Get État de la Mission	  */
	public int getHR_Etat_Mission_ID();

	public I_HR_Etat_Mission getHR_Etat_Mission() throws RuntimeException;

    /** Column name HR_Job_ID */
    public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

	/** Set Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID);

	/** Get Payroll Job	  */
	public int getHR_Job_ID();

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException;

    /** Column name HR_Mission_ID */
    public static final String COLUMNNAME_HR_Mission_ID = "HR_Mission_ID";

	/** Set Mission	  */
	public void setHR_Mission_ID (int HR_Mission_ID);

	/** Get Mission	  */
	public int getHR_Mission_ID();

    /** Column name HR_Mission_UU */
    public static final String COLUMNNAME_HR_Mission_UU = "HR_Mission_UU";

	/** Set HR_Mission_UU	  */
	public void setHR_Mission_UU (String HR_Mission_UU);

	/** Get HR_Mission_UU	  */
	public String getHR_Mission_UU();

    /** Column name HR_Type_Mission_ID */
    public static final String COLUMNNAME_HR_Type_Mission_ID = "HR_Type_Mission_ID";

	/** Set Type de Mission	  */
	public void setHR_Type_Mission_ID (int HR_Type_Mission_ID);

	/** Get Type de Mission	  */
	public int getHR_Type_Mission_ID();

	public I_HR_Type_Mission getHR_Type_Mission() throws RuntimeException;

    /** Column name Identifiant_Employe_ID */
    public static final String COLUMNNAME_Identifiant_Employe_ID = "Identifiant_Employe_ID";

	/** Set Identifiant Employé(e).
	  * Identifiant Employé(e)
	  */
	public void setIdentifiant_Employe_ID (int Identifiant_Employe_ID);

	/** Get Identifiant Employé(e).
	  * Identifiant Employé(e)
	  */
	public int getIdentifiant_Employe_ID();

	public org.compiere.model.I_AD_User getIdentifiant_Employe() throws RuntimeException;

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

    /** Column name IsAffecteMission */
    public static final String COLUMNNAME_IsAffecteMission = "IsAffecteMission";

	/** Set Est Affecté à la Mission.
	  * Est Affecté à la Mission
	  */
	public void setIsAffecteMission (boolean IsAffecteMission);

	/** Get Est Affecté à la Mission.
	  * Est Affecté à la Mission
	  */
	public boolean isAffecteMission();

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

    /** Column name Poste_Utilisateur_ID */
    public static final String COLUMNNAME_Poste_Utilisateur_ID = "Poste_Utilisateur_ID";

	/** Set Poste Utilisateur.
	  * Poste Utilisateur
	  */
	public void setPoste_Utilisateur_ID (int Poste_Utilisateur_ID);

	/** Get Poste Utilisateur.
	  * Poste Utilisateur
	  */
	public int getPoste_Utilisateur_ID();

	public org.eevolution.model.I_HR_Job getPoste_Utilisateur() throws RuntimeException;

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

    /** Column name User_Responsabilite_ID */
    public static final String COLUMNNAME_User_Responsabilite_ID = "User_Responsabilite_ID";

	/** Set Catégorie de Responsabilité de l&#039;
Utilisateur.
	  * Catégorie de Responsabilité de l&#039;
Utilisateur
	  */
	public void setUser_Responsabilite_ID (int User_Responsabilite_ID);

	/** Get Catégorie de Responsabilité de l&#039;
Utilisateur.
	  * Catégorie de Responsabilité de l&#039;
Utilisateur
	  */
	public int getUser_Responsabilite_ID();

	public I_HR_Categorie_Responsabilite getUser_Responsabilite() throws RuntimeException;
}
