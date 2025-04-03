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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Absence_Compensation
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Absence_Compensation 
{

    /** TableName=HR_Absence_Compensation */
    public static final String Table_Name = "HR_Absence_Compensation";

    /** AD_Table_ID=1004344 */
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

    /** Column name Absence_ID */
    public static final String COLUMNNAME_Absence_ID = "Absence_ID";

	/** Set Absence.
	  * Absence
	  */
	public void setAbsence_ID (int Absence_ID);

	/** Get Absence.
	  * Absence
	  */
	public int getAbsence_ID();

	public I_HR_Absence getAbsence() throws RuntimeException;

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

    /** Column name Conge_ID */
    public static final String COLUMNNAME_Conge_ID = "Conge_ID";

	/** Set Congé.
	  * Congé
	  */
	public void setConge_ID (int Conge_ID);

	/** Get Congé.
	  * Congé
	  */
	public int getConge_ID();

	public I_HR_Holiday getConge() throws RuntimeException;

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

    /** Column name HR_Absence_Compensation_ID */
    public static final String COLUMNNAME_HR_Absence_Compensation_ID = "HR_Absence_Compensation_ID";

	/** Set Compensation des Absences	  */
	public void setHR_Absence_Compensation_ID (int HR_Absence_Compensation_ID);

	/** Get Compensation des Absences	  */
	public int getHR_Absence_Compensation_ID();

    /** Column name HR_Absence_Compensation_UU */
    public static final String COLUMNNAME_HR_Absence_Compensation_UU = "HR_Absence_Compensation_UU";

	/** Set HR_Absence_Compensation_UU	  */
	public void setHR_Absence_Compensation_UU (String HR_Absence_Compensation_UU);

	/** Get HR_Absence_Compensation_UU	  */
	public String getHR_Absence_Compensation_UU();

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

    /** Column name Mode_Compensation */
    public static final String COLUMNNAME_Mode_Compensation = "Mode_Compensation";

	/** Set Mode de Compensation.
	  * Mode de Compensation
	  */
	public void setMode_Compensation (String Mode_Compensation);

	/** Get Mode de Compensation.
	  * Mode de Compensation
	  */
	public String getMode_Compensation();

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
