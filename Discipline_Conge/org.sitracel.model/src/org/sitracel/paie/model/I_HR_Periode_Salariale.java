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
package org.sitracel.paie.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Periode_Salariale
 *  @author iDempiere (generated)
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Periode_Salariale
{

    /** TableName=HR_Periode_Salariale */
    public static final String Table_Name = "HR_Periode_Salariale";

    /** AD_Table_ID=1009192 */
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

    /** Column name Date_Debut_Defaut */
    public static final String COLUMNNAME_Date_Debut_Defaut = "Date_Debut_Defaut";

	/** Set à Partir de (par défaut) :.
	  * à Partir de (par défaut) :
	  */
	public void setDate_Debut_Defaut (Timestamp Date_Debut_Defaut);

	/** Get à Partir de (par défaut) :.
	  * à Partir de (par défaut) :
	  */
	public Timestamp getDate_Debut_Defaut();

    /** Column name Date_Fin_Defaut */
    public static final String COLUMNNAME_Date_Fin_Defaut = "Date_Fin_Defaut";

	/** Set Jusqu&#039;
au (par défaut) :.
	  * Jusqu&#039;
au (par défaut) :
	  */
	public void setDate_Fin_Defaut (Timestamp Date_Fin_Defaut);

	/** Get Jusqu&#039;
au (par défaut) :.
	  * Jusqu&#039;
au (par défaut) :
	  */
	public Timestamp getDate_Fin_Defaut();

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

    /** Column name HR_Annee_ID */
    public static final String COLUMNNAME_HR_Annee_ID = "HR_Annee_ID";

	/** Set Année	  */
	public void setHR_Annee_ID (int HR_Annee_ID);

	/** Get Année	  */
	public int getHR_Annee_ID();

	public I_HR_Annee getHR_Annee() throws RuntimeException;

    /** Column name HR_Mois_ID */
    public static final String COLUMNNAME_HR_Mois_ID = "HR_Mois_ID";

	/** Set Mois	  */
	public void setHR_Mois_ID (int HR_Mois_ID);

	/** Get Mois	  */
	public int getHR_Mois_ID();

	public I_HR_Mois getHR_Mois() throws RuntimeException;

    /** Column name HR_Periode_Salariale_ID */
    public static final String COLUMNNAME_HR_Periode_Salariale_ID = "HR_Periode_Salariale_ID";

	/** Set Période Salariale	  */
	public void setHR_Periode_Salariale_ID (int HR_Periode_Salariale_ID);

	/** Get Période Salariale	  */
	public int getHR_Periode_Salariale_ID();

    /** Column name HR_Periode_Salariale_UU */
    public static final String COLUMNNAME_HR_Periode_Salariale_UU = "HR_Periode_Salariale_UU";

	/** Set HR_Periode_Salariale_UU	  */
	public void setHR_Periode_Salariale_UU (String HR_Periode_Salariale_UU);

	/** Get HR_Periode_Salariale_UU	  */
	public String getHR_Periode_Salariale_UU();

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

    /** Column name IsAvantDebutContratDeduit */
    public static final String COLUMNNAME_IsAvantDebutContratDeduit = "IsAvantDebutContratDeduit";

	/** Set Déduire les Jours Avant le Début du Contrat.
	  * Déduire les Jours Avant le Début du Contrat
	  */
	public void setIsAvantDebutContratDeduit (boolean IsAvantDebutContratDeduit);

	/** Get Déduire les Jours Avant le Début du Contrat.
	  * Déduire les Jours Avant le Début du Contrat
	  */
	public boolean isAvantDebutContratDeduit();

    /** Column name IsCongeAnnuelDeduit */
    public static final String COLUMNNAME_IsCongeAnnuelDeduit = "IsCongeAnnuelDeduit";

	/** Set Déduire les Jours de Congé Annuel.
	  * Déduire les Jours de Congé Annuel
	  */
	public void setIsCongeAnnuelDeduit (boolean IsCongeAnnuelDeduit);

	/** Get Déduire les Jours de Congé Annuel.
	  * Déduire les Jours de Congé Annuel
	  */
	public boolean isCongeAnnuelDeduit();

    /** Column name IsCongeMatPatlDeduit */
    public static final String COLUMNNAME_IsCongeMatPatlDeduit = "IsCongeMatPatlDeduit";

	/** Set Déduire les Jours de Congé Maternité/Paternité.
	  * Déduire les Jours de Congé Maternité/Paternité
	  */
	public void setIsCongeMatPatlDeduit (boolean IsCongeMatPatlDeduit);

	/** Get Déduire les Jours de Congé Maternité/Paternité.
	  * Déduire les Jours de Congé Maternité/Paternité
	  */
	public boolean isCongeMatPatlDeduit();

    /** Column name IsGestionPresenceAuto */
    public static final String COLUMNNAME_IsGestionPresenceAuto = "IsGestionPresenceAuto";

	/** Set Gérer Automatiquement la Présence.
	  * Gérer Automatiquement la Présence
	  */
	public void setIsGestionPresenceAuto (boolean IsGestionPresenceAuto);

	/** Get Gérer Automatiquement la Présence.
	  * Gérer Automatiquement la Présence
	  */
	public boolean isGestionPresenceAuto();

    /** Column name IsSuspensionDeduit */
    public static final String COLUMNNAME_IsSuspensionDeduit = "IsSuspensionDeduit";

	/** Set Déduire les Jours de Suspension.
	  * Déduire les Jours de Suspension
	  */
	public void setIsSuspensionDeduit (boolean IsSuspensionDeduit);

	/** Get Déduire les Jours de Suspension.
	  * Déduire les Jours de Suspension
	  */
	public boolean isSuspensionDeduit();

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

    /** Column name Nombre_Jour_Salarial */
    public static final String COLUMNNAME_Nombre_Jour_Salarial = "Nombre_Jour_Salarial";

	/** Set Nombre de Jour Salarial.
	  * Nombre de Jour Salarial
	  */
	public void setNombre_Jour_Salarial (int Nombre_Jour_Salarial);

	/** Get Nombre de Jour Salarial.
	  * Nombre de Jour Salarial
	  */
	public int getNombre_Jour_Salarial();

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
}
