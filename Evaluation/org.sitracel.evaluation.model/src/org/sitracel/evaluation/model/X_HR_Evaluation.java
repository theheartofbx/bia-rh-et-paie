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
/** Generated Model - DO NOT CHANGE */
package org.sitracel.evaluation.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Evaluation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Evaluation")
public class X_HR_Evaluation extends PO implements I_HR_Evaluation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260820L;

    /** Standard Constructor */
    public X_HR_Evaluation (Properties ctx, int HR_Evaluation_ID, String trxName)
    {
      super (ctx, HR_Evaluation_ID, trxName);
      /** if (HR_Evaluation_ID == 0)
        {
			setHR_Evaluation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Evaluation (Properties ctx, int HR_Evaluation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Evaluation_ID, trxName, virtualColumns);
      /** if (HR_Evaluation_ID == 0)
        {
			setHR_Evaluation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Evaluation (Properties ctx, String HR_Evaluation_UU, String trxName)
    {
      super (ctx, HR_Evaluation_UU, trxName);
      /** if (HR_Evaluation_UU == null)
        {
			setHR_Evaluation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Evaluation (Properties ctx, String HR_Evaluation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Evaluation_UU, trxName, virtualColumns);
      /** if (HR_Evaluation_UU == null)
        {
			setHR_Evaluation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Evaluation (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_Evaluation[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amt Amount
	*/
	public void setAmt (BigDecimal Amt)
	{
		throw new IllegalArgumentException ("Amt is virtual column");	}

	/** Get Amount.
		@return Amount
	  */
	public BigDecimal getAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner .
		@param C_BPartner_ID Identifies a Business Partner
	*/
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Close Date.
		@param CloseDate Close Date
	*/
	public void setCloseDate (Timestamp CloseDate)
	{
		set_Value (COLUMNNAME_CloseDate, CloseDate);
	}

	/** Get Close Date.
		@return Close Date
	  */
	public Timestamp getCloseDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_CloseDate);
	}

	/** Set Code Evaluation.
		@param CodeEvaluation Code Evaluation
	*/
	public void setCodeEvaluation (String CodeEvaluation)
	{
		set_Value (COLUMNNAME_CodeEvaluation, CodeEvaluation);
	}

	/** Get Code Evaluation.
		@return Code Evaluation	  */
	public String getCodeEvaluation()
	{
		return (String)get_Value(COLUMNNAME_CodeEvaluation);
	}

	/** Set Commentaire Comité Evaluation.
		@param CommentaireComite Commentaire Comité Evaluation
	*/
	public void setCommentaireComite (String CommentaireComite)
	{
		set_Value (COLUMNNAME_CommentaireComite, CommentaireComite);
	}

	/** Get Commentaire Comité Evaluation.
		@return Commentaire Comité Evaluation	  */
	public String getCommentaireComite()
	{
		return (String)get_Value(COLUMNNAME_CommentaireComite);
	}

	/** Set Commentaire Evaluateur.
		@param CommentaireEvaluateur Commentaire Evaluateur
	*/
	public void setCommentaireEvaluateur (String CommentaireEvaluateur)
	{
		set_Value (COLUMNNAME_CommentaireEvaluateur, CommentaireEvaluateur);
	}

	/** Get Commentaire Evaluateur.
		@return Commentaire Evaluateur	  */
	public String getCommentaireEvaluateur()
	{
		return (String)get_Value(COLUMNNAME_CommentaireEvaluateur);
	}

	/** Set Commentaire Evalue.
		@param CommentaireEvalue Commentaire Evalue
	*/
	public void setCommentaireEvalue (String CommentaireEvalue)
	{
		set_Value (COLUMNNAME_CommentaireEvalue, CommentaireEvalue);
	}

	/** Get Commentaire Evalue.
		@return Commentaire Evalue	  */
	public String getCommentaireEvalue()
	{
		return (String)get_Value(COLUMNNAME_CommentaireEvalue);
	}

	/** Set Transaction Date.
		@param DateTrx Transaction Date
	*/
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_ID)
			.getPO(getHR_Department_ID(), get_TrxName());
	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department
	*/
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1)
			set_Value (COLUMNNAME_HR_Department_ID, null);
		else
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Evaluation getHR_EvaluationSource() throws RuntimeException
	{
		return (I_HR_Evaluation)MTable.get(getCtx(), I_HR_Evaluation.Table_ID)
			.getPO(getHR_EvaluationSource_ID(), get_TrxName());
	}

	/** Set Evaluation Source.
		@param HR_EvaluationSource_ID Evaluation Source
	*/
	public void setHR_EvaluationSource_ID (int HR_EvaluationSource_ID)
	{
		if (HR_EvaluationSource_ID < 1)
			set_Value (COLUMNNAME_HR_EvaluationSource_ID, null);
		else
			set_Value (COLUMNNAME_HR_EvaluationSource_ID, Integer.valueOf(HR_EvaluationSource_ID));
	}

	/** Get Evaluation Source.
		@return Evaluation Source	  */
	public int getHR_EvaluationSource_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvaluationSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Evaluation.
		@param HR_Evaluation_ID Evaluation
	*/
	public void setHR_Evaluation_ID (int HR_Evaluation_ID)
	{
		if (HR_Evaluation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Evaluation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Evaluation_ID, Integer.valueOf(HR_Evaluation_ID));
	}

	/** Get Evaluation.
		@return Evaluation	  */
	public int getHR_Evaluation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Evaluation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Evaluation_UU.
		@param HR_Evaluation_UU HR_Evaluation_UU
	*/
	public void setHR_Evaluation_UU (String HR_Evaluation_UU)
	{
		set_Value (COLUMNNAME_HR_Evaluation_UU, HR_Evaluation_UU);
	}

	/** Get HR_Evaluation_UU.
		@return HR_Evaluation_UU	  */
	public String getHR_Evaluation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Evaluation_UU);
	}

	public I_HR_Formulaire getHR_Formulaire() throws RuntimeException
	{
		return (I_HR_Formulaire)MTable.get(getCtx(), I_HR_Formulaire.Table_ID)
			.getPO(getHR_Formulaire_ID(), get_TrxName());
	}

	/** Set Formulaire.
		@param HR_Formulaire_ID Formulaire
	*/
	public void setHR_Formulaire_ID (int HR_Formulaire_ID)
	{
		if (HR_Formulaire_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Formulaire_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Formulaire_ID, Integer.valueOf(HR_Formulaire_ID));
	}

	/** Get Formulaire.
		@return Formulaire	  */
	public int getHR_Formulaire_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Formulaire_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Status.
		@param HR_Status Status
	*/
	public void setHR_Status (String HR_Status)
	{
		set_Value (COLUMNNAME_HR_Status, HR_Status);
	}

	/** Get Status.
		@return Status	  */
	public String getHR_Status()
	{
		return (String)get_Value(COLUMNNAME_HR_Status);
	}

	/** Evaluation Groupée = G */
	public static final String HR_TYPEEVALUATION_EvaluationGroupée = "G";
	/** Set Type Evaluation.
		@param HR_TypeEvaluation Type Evaluation
	*/
	public void setHR_TypeEvaluation (String HR_TypeEvaluation)
	{

		set_Value (COLUMNNAME_HR_TypeEvaluation, HR_TypeEvaluation);
	}

	/** Get Type Evaluation.
		@return Type Evaluation	  */
	public String getHR_TypeEvaluation()
	{
		return (String)get_Value(COLUMNNAME_HR_TypeEvaluation);
	}

	public org.eevolution.model.I_HR_Department getIEXT_HR_Department_Parent() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_ID)
			.getPO(getIEXT_HR_Department_Parent_ID(), get_TrxName());
	}

	/** Set HR Departement Parent.
		@param IEXT_HR_Department_Parent_ID HR Departement Parent
	*/
	public void setIEXT_HR_Department_Parent_ID (int IEXT_HR_Department_Parent_ID)
	{
		throw new IllegalArgumentException ("IEXT_HR_Department_Parent_ID is virtual column");	}

	/** Get HR Departement Parent.
		@return HR Departement Parent	  */
	public int getIEXT_HR_Department_Parent_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IEXT_HR_Department_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Note Auto Evaluation.
		@param IEXT_NoteAutoEval Note Auto Evaluation
	*/
	public void setIEXT_NoteAutoEval (BigDecimal IEXT_NoteAutoEval)
	{
		throw new IllegalArgumentException ("IEXT_NoteAutoEval is virtual column");	}

	/** Get Note Auto Evaluation.
		@return Note Auto Evaluation	  */
	public BigDecimal getIEXT_NoteAutoEval()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IEXT_NoteAutoEval);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processing.
		@param IsProcessing Processing
	*/
	public void setIsProcessing (boolean IsProcessing)
	{
		set_Value (COLUMNNAME_IsProcessing, Boolean.valueOf(IsProcessing));
	}

	/** Get Processing.
		@return Processing	  */
	public boolean isProcessing()
	{
		Object oo = get_Value(COLUMNNAME_IsProcessing);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Note Evaluation.
		@param NoteEvaluation Note Evaluation
	*/
	public void setNoteEvaluation (int NoteEvaluation)
	{
		throw new IllegalArgumentException ("NoteEvaluation is virtual column");	}

	/** Get Note Evaluation.
		@return Note Evaluation	  */
	public int getNoteEvaluation()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoteEvaluation);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of runs.
		@param NumberOfRuns Frequency of processing Perpetual Inventory
	*/
	public void setNumberOfRuns (int NumberOfRuns)
	{
		throw new IllegalArgumentException ("NumberOfRuns is virtual column");	}

	/** Get Number of runs.
		@return Frequency of processing Perpetual Inventory
	  */
	public int getNumberOfRuns()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumberOfRuns);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** AGENT_MAITRISE = AGENT_MAITRISE */
	public static final String STATUS_AGENT_MAITRISE = "AGENT_MAITRISE";
	/** CADRE = CADRE */
	public static final String STATUS_CADRE = "CADRE";
	/** Set Status.
		@param Status Status of the currently running check
	*/
	public void setStatus (String Status)
	{

		set_ValueNoCheck (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus()
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	public org.compiere.model.I_C_BPartner getSuperieurEmploye() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getSuperieurEmploye_ID(), get_TrxName());
	}

	/** Set Superieur Employé.
		@param SuperieurEmploye_ID Superieur Employé
	*/
	public void setSuperieurEmploye_ID (int SuperieurEmploye_ID)
	{
		if (SuperieurEmploye_ID < 1)
			set_Value (COLUMNNAME_SuperieurEmploye_ID, null);
		else
			set_Value (COLUMNNAME_SuperieurEmploye_ID, Integer.valueOf(SuperieurEmploye_ID));
	}

	/** Get Superieur Employé.
		@return Superieur Employé	  */
	public int getSuperieurEmploye_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SuperieurEmploye_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Total Point Evaluation.
		@param TotalPointEvaluation Total Point Evaluation
	*/
	public void setTotalPointEvaluation (int TotalPointEvaluation)
	{
		set_Value (COLUMNNAME_TotalPointEvaluation, Integer.valueOf(TotalPointEvaluation));
	}

	/** Get Total Point Evaluation.
		@return Total Point Evaluation	  */
	public int getTotalPointEvaluation()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TotalPointEvaluation);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}