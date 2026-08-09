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
package org.sitracel.formation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_FormationSession
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_FormationSession 
{

    /** TableName=HR_FormationSession */
    public static final String Table_Name = "HR_FormationSession";

    /** AD_Table_ID=1015757 */
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

    /** Column name HR_FormationCatalogue_ID */
    public static final String COLUMNNAME_HR_FormationCatalogue_ID = "HR_FormationCatalogue_ID";

	/** Set Catalogue de Formation	  */
	public void setHR_FormationCatalogue_ID (int HR_FormationCatalogue_ID);

	/** Get Catalogue de Formation	  */
	public int getHR_FormationCatalogue_ID();

	public I_HR_FormationCatalogue getHR_FormationCatalogue() throws RuntimeException;

    /** Column name HR_FormationSessionStatut_ID */
    public static final String COLUMNNAME_HR_FormationSessionStatut_ID = "HR_FormationSessionStatut_ID";

	/** Set Statut de la Session de Formation	  */
	public void setHR_FormationSessionStatut_ID (int HR_FormationSessionStatut_ID);

	/** Get Statut de la Session de Formation	  */
	public int getHR_FormationSessionStatut_ID();

	public I_HR_FormationSessionStatut getHR_FormationSessionStatut() throws RuntimeException;

    /** Column name HR_FormationSession_ID */
    public static final String COLUMNNAME_HR_FormationSession_ID = "HR_FormationSession_ID";

	/** Set Session de Formation	  */
	public void setHR_FormationSession_ID (int HR_FormationSession_ID);

	/** Get Session de Formation	  */
	public int getHR_FormationSession_ID();

    /** Column name HR_FormationSession_UU */
    public static final String COLUMNNAME_HR_FormationSession_UU = "HR_FormationSession_UU";

	/** Set HR_FormationSession_UU	  */
	public void setHR_FormationSession_UU (String HR_FormationSession_UU);

	/** Get HR_FormationSession_UU	  */
	public String getHR_FormationSession_UU();

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

    /** Column name Nombre_Places */
    public static final String COLUMNNAME_Nombre_Places = "Nombre_Places";

	/** Set Nombre de Places.
	  * Nombre de Places
	  */
	public void setNombre_Places (int Nombre_Places);

	/** Get Nombre de Places.
	  * Nombre de Places
	  */
	public int getNombre_Places();

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

    /** Column name Responsable_ID */
    public static final String COLUMNNAME_Responsable_ID = "Responsable_ID";

	/** Set Responsable.
	  * Responsable
	  */
	public void setResponsable_ID (int Responsable_ID);

	/** Get Responsable.
	  * Responsable
	  */
	public int getResponsable_ID();

	public org.compiere.model.I_C_BPartner getResponsable() throws RuntimeException;

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
