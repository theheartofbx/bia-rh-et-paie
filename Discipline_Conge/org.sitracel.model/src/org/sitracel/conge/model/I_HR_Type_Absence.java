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

/** Generated Interface for HR_Type_Absence
 *  @author iDempiere (generated)
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Type_Absence
{

    /** TableName=HR_Type_Absence */
    public static final String Table_Name = "HR_Type_Absence";

    /** AD_Table_ID=1001112 */
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

    /** Column name HR_Type_Absence_ID */
    public static final String COLUMNNAME_HR_Type_Absence_ID = "HR_Type_Absence_ID";

	/** Set Type d&#039;
Absence	  */
	public void setHR_Type_Absence_ID (int HR_Type_Absence_ID);

	/** Get Type d&#039;
Absence	  */
	public int getHR_Type_Absence_ID();

    /** Column name HR_Type_Absence_UU */
    public static final String COLUMNNAME_HR_Type_Absence_UU = "HR_Type_Absence_UU";

	/** Set HR_Type_Absence_UU	  */
	public void setHR_Type_Absence_UU (String HR_Type_Absence_UU);

	/** Get HR_Type_Absence_UU	  */
	public String getHR_Type_Absence_UU();

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

    /** Column name IsConge */
    public static final String COLUMNNAME_IsConge = "IsConge";

	/** Set Compte comme un Congé.
	  * Compte comme un Congé
	  */
	public void setIsConge (boolean IsConge);

	/** Get Compte comme un Congé.
	  * Compte comme un Congé
	  */
	public boolean isConge();

    /** Column name IsDemandeExplication */
    public static final String COLUMNNAME_IsDemandeExplication = "IsDemandeExplication";

	/** Set Peut conduire à une Demande d&#039;
Explication.
	  * Peut conduire à une Demande d&#039;
Explication
	  */
	public void setIsDemandeExplication (boolean IsDemandeExplication);

	/** Get Peut conduire à une Demande d&#039;
Explication.
	  * Peut conduire à une Demande d&#039;
Explication
	  */
	public boolean isDemandeExplication();

    /** Column name Nom_Absence */
    public static final String COLUMNNAME_Nom_Absence = "Nom_Absence";

	/** Set Nom de l&#039;
Absence.
	  * Nom de l&#039;
Absence
	  */
	public void setNom_Absence (String Nom_Absence);

	/** Get Nom de l&#039;
Absence.
	  * Nom de l&#039;
Absence
	  */
	public String getNom_Absence();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
