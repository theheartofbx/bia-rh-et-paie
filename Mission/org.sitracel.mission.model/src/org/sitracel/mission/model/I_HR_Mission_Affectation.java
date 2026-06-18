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

/** Generated Interface for HR_Mission_Affectation
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Mission_Affectation 
{

    /** TableName=HR_Mission_Affectation */
    public static final String Table_Name = "HR_Mission_Affectation";

    /** AD_Table_ID=1012626 */
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

    /** Column name Affecte_Par_Matricule */
    public static final String COLUMNNAME_Affecte_Par_Matricule = "Affecte_Par_Matricule";

	/** Set Affecté Par (Matricule) :	  */
	public void setAffecte_Par_Matricule (String Affecte_Par_Matricule);

	/** Get Affecté Par (Matricule) :	  */
	public String getAffecte_Par_Matricule();

    /** Column name Affecte_Par_Nom_ID */
    public static final String COLUMNNAME_Affecte_Par_Nom_ID = "Affecte_Par_Nom_ID";

	/** Set Affecté Par (Nom) :	  */
	public void setAffecte_Par_Nom_ID (int Affecte_Par_Nom_ID);

	/** Get Affecté Par (Nom) :	  */
	public int getAffecte_Par_Nom_ID();

	public org.compiere.model.I_C_BPartner getAffecte_Par_Nom() throws RuntimeException;

    /** Column name Affecte_Par_Poste_ID */
    public static final String COLUMNNAME_Affecte_Par_Poste_ID = "Affecte_Par_Poste_ID";

	/** Set Affecté Par (Poste) :	  */
	public void setAffecte_Par_Poste_ID (int Affecte_Par_Poste_ID);

	/** Get Affecté Par (Poste) :	  */
	public int getAffecte_Par_Poste_ID();

	public org.eevolution.model.I_HR_Job getAffecte_Par_Poste() throws RuntimeException;

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

    /** Column name Employee_ID */
    public static final String COLUMNNAME_Employee_ID = "Employee_ID";

	/** Set Emloyé(e).
	  * Emloyé(e)
	  */
	public void setEmployee_ID (int Employee_ID);

	/** Get Emloyé(e).
	  * Emloyé(e)
	  */
	public int getEmployee_ID();

	public org.compiere.model.I_C_BPartner getEmployee() throws RuntimeException;

    /** Column name HR_Mission_Affectation_ID */
    public static final String COLUMNNAME_HR_Mission_Affectation_ID = "HR_Mission_Affectation_ID";

	/** Set Affectation des employés à la mission	  */
	public void setHR_Mission_Affectation_ID (int HR_Mission_Affectation_ID);

	/** Get Affectation des employés à la mission	  */
	public int getHR_Mission_Affectation_ID();

    /** Column name HR_Mission_Affectation_UU */
    public static final String COLUMNNAME_HR_Mission_Affectation_UU = "HR_Mission_Affectation_UU";

	/** Set HR_Mission_Affectation_UU	  */
	public void setHR_Mission_Affectation_UU (String HR_Mission_Affectation_UU);

	/** Get HR_Mission_Affectation_UU	  */
	public String getHR_Mission_Affectation_UU();

    /** Column name HR_Mission_ID */
    public static final String COLUMNNAME_HR_Mission_ID = "HR_Mission_ID";

	/** Set Mission	  */
	public void setHR_Mission_ID (int HR_Mission_ID);

	/** Get Mission	  */
	public int getHR_Mission_ID();

	public I_HR_Mission getHR_Mission() throws RuntimeException;

    /** Column name HR_RoleMissionEmploye_ID */
    public static final String COLUMNNAME_HR_RoleMissionEmploye_ID = "HR_RoleMissionEmploye_ID";

	/** Set Rôle durant la Mission	  */
	public void setHR_RoleMissionEmploye_ID (int HR_RoleMissionEmploye_ID);

	/** Get Rôle durant la Mission	  */
	public int getHR_RoleMissionEmploye_ID();

	public I_HR_RoleMissionEmploye getHR_RoleMissionEmploye() throws RuntimeException;

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
