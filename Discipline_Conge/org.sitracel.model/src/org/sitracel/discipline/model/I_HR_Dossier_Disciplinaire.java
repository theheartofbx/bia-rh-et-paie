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
package org.sitracel.discipline.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Dossier_Disciplinaire
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Dossier_Disciplinaire 
{

    /** TableName=HR_Dossier_Disciplinaire */
    public static final String Table_Name = "HR_Dossier_Disciplinaire";

    /** AD_Table_ID=1003435 */
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

    /** Column name HR_Dossier_Disciplinaire_ID */
    public static final String COLUMNNAME_HR_Dossier_Disciplinaire_ID = "HR_Dossier_Disciplinaire_ID";

	/** Set Dossier Disciplinaire	  */
	public void setHR_Dossier_Disciplinaire_ID (int HR_Dossier_Disciplinaire_ID);

	/** Get Dossier Disciplinaire	  */
	public int getHR_Dossier_Disciplinaire_ID();

    /** Column name HR_Dossier_Disciplinaire_UU */
    public static final String COLUMNNAME_HR_Dossier_Disciplinaire_UU = "HR_Dossier_Disciplinaire_UU";

	/** Set HR_Dossier_Disciplinaire_UU	  */
	public void setHR_Dossier_Disciplinaire_UU (String HR_Dossier_Disciplinaire_UU);

	/** Get HR_Dossier_Disciplinaire_UU	  */
	public String getHR_Dossier_Disciplinaire_UU();

    /** Column name HR_Punishment_ID */
    public static final String COLUMNNAME_HR_Punishment_ID = "HR_Punishment_ID";

	/** Set Punishment	  */
	public void setHR_Punishment_ID (int HR_Punishment_ID);

	/** Get Punishment	  */
	public int getHR_Punishment_ID();

	public I_HR_Punishment getHR_Punishment() throws RuntimeException;

    /** Column name HR_TypeSanction_ID */
    public static final String COLUMNNAME_HR_TypeSanction_ID = "HR_TypeSanction_ID";

	/** Set Punishment Type	  */
	public void setHR_TypeSanction_ID (int HR_TypeSanction_ID);

	/** Get Punishment Type	  */
	public int getHR_TypeSanction_ID();

	public I_HR_TypeSanction getHR_TypeSanction() throws RuntimeException;

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

    /** Column name Motif */
    public static final String COLUMNNAME_Motif = "Motif";

	/** Set Motif .
	  * Motif
	  */
	public void setMotif (String Motif);

	/** Get Motif .
	  * Motif
	  */
	public String getMotif();

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
