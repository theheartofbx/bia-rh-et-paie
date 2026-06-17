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
package org.sitracel.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Evaluation
 *  @author iDempiere (generated)
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Evaluation
{

    /** TableName=HR_Evaluation */
    public static final String Table_Name = "HR_Evaluation";

    /** AD_Table_ID=1002223 */
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

    /** Column name Amt */
    public static final String COLUMNNAME_Amt = "Amt";

	/** Set Amount.
	  * Amount
	  */
	public void setAmt (BigDecimal Amt);

	/** Get Amount.
	  * Amount
	  */
	public BigDecimal getAmt();

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

    /** Column name CloseDate */
    public static final String COLUMNNAME_CloseDate = "CloseDate";

	/** Set Close Date.
	  * Close Date
	  */
	public void setCloseDate (Timestamp CloseDate);

	/** Get Close Date.
	  * Close Date
	  */
	public Timestamp getCloseDate();

    /** Column name CodeEvaluation */
    public static final String COLUMNNAME_CodeEvaluation = "CodeEvaluation";

	/** Set Code Evaluation	  */
	public void setCodeEvaluation (String CodeEvaluation);

	/** Get Code Evaluation	  */
	public String getCodeEvaluation();

    /** Column name CommentaireComite */
    public static final String COLUMNNAME_CommentaireComite = "CommentaireComite";

	/** Set Commentaire Comité Evaluation	  */
	public void setCommentaireComite (String CommentaireComite);

	/** Get Commentaire Comité Evaluation	  */
	public String getCommentaireComite();

    /** Column name CommentaireEvaluateur */
    public static final String COLUMNNAME_CommentaireEvaluateur = "CommentaireEvaluateur";

	/** Set Commentaire Evaluateur	  */
	public void setCommentaireEvaluateur (String CommentaireEvaluateur);

	/** Get Commentaire Evaluateur	  */
	public String getCommentaireEvaluateur();

    /** Column name CommentaireEvalue */
    public static final String COLUMNNAME_CommentaireEvalue = "CommentaireEvalue";

	/** Set Commentaire Evalue	  */
	public void setCommentaireEvalue (String CommentaireEvalue);

	/** Get Commentaire Evalue	  */
	public String getCommentaireEvalue();

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

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

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

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Payroll Department	  */
	public int getHR_Department_ID();

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

    /** Column name HR_EvaluationSource_ID */
    public static final String COLUMNNAME_HR_EvaluationSource_ID = "HR_EvaluationSource_ID";

	/** Set Evaluation Source	  */
	public void setHR_EvaluationSource_ID (int HR_EvaluationSource_ID);

	/** Get Evaluation Source	  */
	public int getHR_EvaluationSource_ID();

	public I_HR_Evaluation getHR_EvaluationSource() throws RuntimeException;

    /** Column name HR_Evaluation_ID */
    public static final String COLUMNNAME_HR_Evaluation_ID = "HR_Evaluation_ID";

	/** Set Evaluation	  */
	public void setHR_Evaluation_ID (int HR_Evaluation_ID);

	/** Get Evaluation	  */
	public int getHR_Evaluation_ID();

    /** Column name HR_Evaluation_UU */
    public static final String COLUMNNAME_HR_Evaluation_UU = "HR_Evaluation_UU";

	/** Set HR_Evaluation_UU	  */
	public void setHR_Evaluation_UU (String HR_Evaluation_UU);

	/** Get HR_Evaluation_UU	  */
	public String getHR_Evaluation_UU();

    /** Column name HR_Formulaire_ID */
    public static final String COLUMNNAME_HR_Formulaire_ID = "HR_Formulaire_ID";

	/** Set Formulaire	  */
	public void setHR_Formulaire_ID (int HR_Formulaire_ID);

	/** Get Formulaire	  */
	public int getHR_Formulaire_ID();

	public I_HR_Formulaire getHR_Formulaire() throws RuntimeException;

    /** Column name HR_Status */
    public static final String COLUMNNAME_HR_Status = "HR_Status";

	/** Set Status	  */
	public void setHR_Status (String HR_Status);

	/** Get Status	  */
	public String getHR_Status();

    /** Column name HR_TypeEvaluation */
    public static final String COLUMNNAME_HR_TypeEvaluation = "HR_TypeEvaluation";

	/** Set Type Evaluation	  */
	public void setHR_TypeEvaluation (String HR_TypeEvaluation);

	/** Get Type Evaluation	  */
	public String getHR_TypeEvaluation();

    /** Column name IEXT_HR_Department_Parent_ID */
    public static final String COLUMNNAME_IEXT_HR_Department_Parent_ID = "IEXT_HR_Department_Parent_ID";

	/** Set HR Departement Parent	  */
	public void setIEXT_HR_Department_Parent_ID (int IEXT_HR_Department_Parent_ID);

	/** Get HR Departement Parent	  */
	public int getIEXT_HR_Department_Parent_ID();

	public org.eevolution.model.I_HR_Department getIEXT_HR_Department_Parent() throws RuntimeException;

    /** Column name IEXT_NoteAutoEval */
    public static final String COLUMNNAME_IEXT_NoteAutoEval = "IEXT_NoteAutoEval";

	/** Set Note Auto Evaluation	  */
	public void setIEXT_NoteAutoEval (BigDecimal IEXT_NoteAutoEval);

	/** Get Note Auto Evaluation	  */
	public BigDecimal getIEXT_NoteAutoEval();

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

    /** Column name IsProcessing */
    public static final String COLUMNNAME_IsProcessing = "IsProcessing";

	/** Set Processing	  */
	public void setIsProcessing (boolean IsProcessing);

	/** Get Processing	  */
	public boolean isProcessing();

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

    /** Column name NoteEvaluation */
    public static final String COLUMNNAME_NoteEvaluation = "NoteEvaluation";

	/** Set Note Evaluation	  */
	public void setNoteEvaluation (int NoteEvaluation);

	/** Get Note Evaluation	  */
	public int getNoteEvaluation();

    /** Column name NumberOfRuns */
    public static final String COLUMNNAME_NumberOfRuns = "NumberOfRuns";

	/** Set Number of runs.
	  * Frequency of processing Perpetual Inventory
	  */
	public void setNumberOfRuns (int NumberOfRuns);

	/** Get Number of runs.
	  * Frequency of processing Perpetual Inventory
	  */
	public int getNumberOfRuns();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name SuperieurEmploye_ID */
    public static final String COLUMNNAME_SuperieurEmploye_ID = "SuperieurEmploye_ID";

	/** Set Superieur Employé	  */
	public void setSuperieurEmploye_ID (int SuperieurEmploye_ID);

	/** Get Superieur Employé	  */
	public int getSuperieurEmploye_ID();

	public org.compiere.model.I_C_BPartner getSuperieurEmploye() throws RuntimeException;

    /** Column name TotalPointEvaluation */
    public static final String COLUMNNAME_TotalPointEvaluation = "TotalPointEvaluation";

	/** Set Total Point Evaluation	  */
	public void setTotalPointEvaluation (int TotalPointEvaluation);

	/** Get Total Point Evaluation	  */
	public int getTotalPointEvaluation();

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
