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
package org.sitracel.model.v2;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Absence
 *  @author iDempiere (generated) 
 *  @version Release 11
 */
@SuppressWarnings("all")
public interface I_HR_Absence 
{

    /** TableName=HR_Absence */
    public static final String Table_Name = "HR_Absence";

    /** AD_Table_ID=1004243 */
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

    /** Column name Date_Absence */
    public static final String COLUMNNAME_Date_Absence = "Date_Absence";

	/** Set Date de l&#039;
Absence.
	  * Date de l&#039;
Absence
	  */
	public void setDate_Absence (Timestamp Date_Absence);

	/** Get Date de l&#039;
Absence.
	  * Date de l&#039;
Absence
	  */
	public Timestamp getDate_Absence();

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

    /** Column name HR_Absence_ID */
    public static final String COLUMNNAME_HR_Absence_ID = "HR_Absence_ID";

	/** Set Absence	  */
	public void setHR_Absence_ID (int HR_Absence_ID);

	/** Get Absence	  */
	public int getHR_Absence_ID();

    /** Column name HR_Absence_UU */
    public static final String COLUMNNAME_HR_Absence_UU = "HR_Absence_UU";

	/** Set HR_Absence_UU	  */
	public void setHR_Absence_UU (String HR_Absence_UU);

	/** Get HR_Absence_UU	  */
	public String getHR_Absence_UU();

    /** Column name HR_Demande_Explication_ID */
    public static final String COLUMNNAME_HR_Demande_Explication_ID = "HR_Demande_Explication_ID";

	/** Set Demande d&#039;
Explication	  */
	public void setHR_Demande_Explication_ID (int HR_Demande_Explication_ID);

	/** Get Demande d&#039;
Explication	  */
	public int getHR_Demande_Explication_ID();

	public I_HR_Demande_Explication getHR_Demande_Explication() throws RuntimeException;

    /** Column name HR_Holiday_ID */
    public static final String COLUMNNAME_HR_Holiday_ID = "HR_Holiday_ID";

	/** Set Congé	  */
	public void setHR_Holiday_ID (int HR_Holiday_ID);

	/** Get Congé	  */
	public int getHR_Holiday_ID();

	public I_HR_Holiday getHR_Holiday() throws RuntimeException;

    /** Column name HR_Type_Absence_ID */
    public static final String COLUMNNAME_HR_Type_Absence_ID = "HR_Type_Absence_ID";

	/** Set Type d&#039;
Absence	  */
	public void setHR_Type_Absence_ID (int HR_Type_Absence_ID);

	/** Get Type d&#039;
Absence	  */
	public int getHR_Type_Absence_ID();

	public I_HR_Type_Absence getHR_Type_Absence() throws RuntimeException;

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

    /** Column name IsCongeTraite */
    public static final String COLUMNNAME_IsCongeTraite = "IsCongeTraite";

	/** Set Congé Traité.
	  * Congé Traité
	  */
	public void setIsCongeTraite (boolean IsCongeTraite);

	/** Get Congé Traité.
	  * Congé Traité
	  */
	public boolean isCongeTraite();

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

    /** Column name IsDemandeExplicationTraite */
    public static final String COLUMNNAME_IsDemandeExplicationTraite = "IsDemandeExplicationTraite";

	/** Set Demande d&#039;
Explication Traitée.
	  * Demande d&#039;
Explication Traitée
	  */
	public void setIsDemandeExplicationTraite (boolean IsDemandeExplicationTraite);

	/** Get Demande d&#039;
Explication Traitée.
	  * Demande d&#039;
Explication Traitée
	  */
	public boolean isDemandeExplicationTraite();

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
