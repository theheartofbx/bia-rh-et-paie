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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Mission_Validation
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Mission_Validation 
{

    /** TableName=HR_Mission_Validation */
    public static final String Table_Name = "HR_Mission_Validation";

    /** AD_Table_ID=1012222 */
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

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name HR_Mission_ID */
    public static final String COLUMNNAME_HR_Mission_ID = "HR_Mission_ID";

	/** Set Mission	  */
	public void setHR_Mission_ID (int HR_Mission_ID);

	/** Get Mission	  */
	public int getHR_Mission_ID();

	public I_HR_Mission getHR_Mission() throws RuntimeException;

    /** Column name HR_Mission_Validation_ID */
    public static final String COLUMNNAME_HR_Mission_Validation_ID = "HR_Mission_Validation_ID";

	/** Set Validation de Mission	  */
	public void setHR_Mission_Validation_ID (int HR_Mission_Validation_ID);

	/** Get Validation de Mission	  */
	public int getHR_Mission_Validation_ID();

    /** Column name HR_Mission_Validation_UU */
    public static final String COLUMNNAME_HR_Mission_Validation_UU = "HR_Mission_Validation_UU";

	/** Set HR_Mission_Validation_UU	  */
	public void setHR_Mission_Validation_UU (String HR_Mission_Validation_UU);

	/** Get HR_Mission_Validation_UU	  */
	public String getHR_Mission_Validation_UU();

    /** Column name HR_TypeValidation_ID */
    public static final String COLUMNNAME_HR_TypeValidation_ID = "HR_TypeValidation_ID";

	/** Set Type de Validation	  */
	public void setHR_TypeValidation_ID (int HR_TypeValidation_ID);

	/** Get Type de Validation	  */
	public int getHR_TypeValidation_ID();

	public I_HR_TypeValidation getHR_TypeValidation() throws RuntimeException;

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
}
