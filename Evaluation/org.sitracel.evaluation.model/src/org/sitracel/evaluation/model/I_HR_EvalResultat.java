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
package org.sitracel.evaluation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_EvalResultat
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_EvalResultat 
{

    /** TableName=HR_EvalResultat */
    public static final String Table_Name = "HR_EvalResultat";

    /** AD_Table_ID=1018585 */
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

    /** Column name Formule */
    public static final String COLUMNNAME_Formule = "Formule";

	/** Set Formule	  */
	public void setFormule (String Formule);

	/** Get Formule	  */
	public String getFormule();

    /** Column name HR_EvalGrilleFormule_ID */
    public static final String COLUMNNAME_HR_EvalGrilleFormule_ID = "HR_EvalGrilleFormule_ID";

	/** Set Formule Grille Évaluation	  */
	public void setHR_EvalGrilleFormule_ID (int HR_EvalGrilleFormule_ID);

	/** Get Formule Grille Évaluation	  */
	public int getHR_EvalGrilleFormule_ID();

	public I_HR_EvalGrilleFormule getHR_EvalGrilleFormule() throws RuntimeException;

    /** Column name HR_EvalResultat_ID */
    public static final String COLUMNNAME_HR_EvalResultat_ID = "HR_EvalResultat_ID";

	/** Set Résultat Formule Évaluation	  */
	public void setHR_EvalResultat_ID (int HR_EvalResultat_ID);

	/** Get Résultat Formule Évaluation	  */
	public int getHR_EvalResultat_ID();

    /** Column name HR_EvalResultat_UU */
    public static final String COLUMNNAME_HR_EvalResultat_UU = "HR_EvalResultat_UU";

	/** Set HR_EvalResultat_UU	  */
	public void setHR_EvalResultat_UU (String HR_EvalResultat_UU);

	/** Get HR_EvalResultat_UU	  */
	public String getHR_EvalResultat_UU();

    /** Column name HR_Eval_ID */
    public static final String COLUMNNAME_HR_Eval_ID = "HR_Eval_ID";

	/** Set Évaluation	  */
	public void setHR_Eval_ID (int HR_Eval_ID);

	/** Get Évaluation	  */
	public int getHR_Eval_ID();

	public I_HR_Eval getHR_Eval() throws RuntimeException;

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

    /** Column name IsCalcule */
    public static final String COLUMNNAME_IsCalcule = "IsCalcule";

	/** Set IsCalcule	  */
	public void setIsCalcule (boolean IsCalcule);

	/** Get IsCalcule	  */
	public boolean isCalcule();

    /** Column name IsPrincipale */
    public static final String COLUMNNAME_IsPrincipale = "IsPrincipale";

	/** Set Principale	  */
	public void setIsPrincipale (boolean IsPrincipale);

	/** Get Principale	  */
	public boolean isPrincipale();

    /** Column name MessageErreur */
    public static final String COLUMNNAME_MessageErreur = "MessageErreur";

	/** Set Message d&#039;
Erreur	  */
	public void setMessageErreur (String MessageErreur);

	/** Get Message d&#039;
Erreur	  */
	public String getMessageErreur();

    /** Column name Resultat */
    public static final String COLUMNNAME_Resultat = "Resultat";

	/** Set Resultat	  */
	public void setResultat (BigDecimal Resultat);

	/** Get Resultat	  */
	public BigDecimal getResultat();

    /** Column name Score */
    public static final String COLUMNNAME_Score = "Score";

	/** Set Score	  */
	public void setScore (BigDecimal Score);

	/** Get Score	  */
	public BigDecimal getScore();

    /** Column name ScoreMax */
    public static final String COLUMNNAME_ScoreMax = "ScoreMax";

	/** Set Score Maximum	  */
	public void setScoreMax (BigDecimal ScoreMax);

	/** Get Score Maximum	  */
	public BigDecimal getScoreMax();

    /** Column name ScoreMin */
    public static final String COLUMNNAME_ScoreMin = "ScoreMin";

	/** Set Score Mininum	  */
	public void setScoreMin (BigDecimal ScoreMin);

	/** Get Score Mininum	  */
	public BigDecimal getScoreMin();

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
