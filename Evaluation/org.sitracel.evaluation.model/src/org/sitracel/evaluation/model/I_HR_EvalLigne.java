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

/** Generated Interface for HR_EvalLigne
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_EvalLigne 
{

    /** TableName=HR_EvalLigne */
    public static final String Table_Name = "HR_EvalLigne";

    /** AD_Table_ID=1018484 */
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

    /** Column name Acronyme */
    public static final String COLUMNNAME_Acronyme = "Acronyme";

	/** Set Acronyme	  */
	public void setAcronyme (String Acronyme);

	/** Get Acronyme	  */
	public String getAcronyme();

    /** Column name Commentaire_Employe */
    public static final String COLUMNNAME_Commentaire_Employe = "Commentaire_Employe";

	/** Set Commentaire Employé(e)	  */
	public void setCommentaire_Employe (String Commentaire_Employe);

	/** Get Commentaire Employé(e)	  */
	public String getCommentaire_Employe();

    /** Column name Commentaire_N1 */
    public static final String COLUMNNAME_Commentaire_N1 = "Commentaire_N1";

	/** Set Commentaire N1	  */
	public void setCommentaire_N1 (String Commentaire_N1);

	/** Get Commentaire N1	  */
	public String getCommentaire_N1();

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

    /** Column name HR_EvalAppreciation_ID */
    public static final String COLUMNNAME_HR_EvalAppreciation_ID = "HR_EvalAppreciation_ID";

	/** Set Appréciation Évaluation	  */
	public void setHR_EvalAppreciation_ID (int HR_EvalAppreciation_ID);

	/** Get Appréciation Évaluation	  */
	public int getHR_EvalAppreciation_ID();

	public I_HR_EvalAppreciation getHR_EvalAppreciation() throws RuntimeException;

    /** Column name HR_EvalGrilleLigne_ID */
    public static final String COLUMNNAME_HR_EvalGrilleLigne_ID = "HR_EvalGrilleLigne_ID";

	/** Set Ligne Grille Évaluation	  */
	public void setHR_EvalGrilleLigne_ID (int HR_EvalGrilleLigne_ID);

	/** Get Ligne Grille Évaluation	  */
	public int getHR_EvalGrilleLigne_ID();

	public I_HR_EvalGrilleLigne getHR_EvalGrilleLigne() throws RuntimeException;

    /** Column name HR_EvalLigne_ID */
    public static final String COLUMNNAME_HR_EvalLigne_ID = "HR_EvalLigne_ID";

	/** Set Ligne Évaluation	  */
	public void setHR_EvalLigne_ID (int HR_EvalLigne_ID);

	/** Get Ligne Évaluation	  */
	public int getHR_EvalLigne_ID();

    /** Column name HR_EvalLigne_UU */
    public static final String COLUMNNAME_HR_EvalLigne_UU = "HR_EvalLigne_UU";

	/** Set HR_EvalLigne_UU	  */
	public void setHR_EvalLigne_UU (String HR_EvalLigne_UU);

	/** Get HR_EvalLigne_UU	  */
	public String getHR_EvalLigne_UU();

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

    /** Column name IsBinaire */
    public static final String COLUMNNAME_IsBinaire = "IsBinaire";

	/** Set Est Binaire	  */
	public void setIsBinaire (boolean IsBinaire);

	/** Get Est Binaire	  */
	public boolean isBinaire();

    /** Column name IsEliminatoire */
    public static final String COLUMNNAME_IsEliminatoire = "IsEliminatoire";

	/** Set Éliminatoire	  */
	public void setIsEliminatoire (boolean IsEliminatoire);

	/** Get Éliminatoire	  */
	public boolean isEliminatoire();

    /** Column name IsEvalue */
    public static final String COLUMNNAME_IsEvalue = "IsEvalue";

	/** Set Évalué	  */
	public void setIsEvalue (boolean IsEvalue);

	/** Get Évalué	  */
	public boolean isEvalue();

    /** Column name IsPourcentage */
    public static final String COLUMNNAME_IsPourcentage = "IsPourcentage";

	/** Set En Pourcentage	  */
	public void setIsPourcentage (boolean IsPourcentage);

	/** Get En Pourcentage	  */
	public boolean isPourcentage();

    /** Column name IsProgressif */
    public static final String COLUMNNAME_IsProgressif = "IsProgressif";

	/** Set Est Progressif	  */
	public void setIsProgressif (boolean IsProgressif);

	/** Get Est Progressif	  */
	public boolean isProgressif();

    /** Column name IsSubjectif */
    public static final String COLUMNNAME_IsSubjectif = "IsSubjectif";

	/** Set Est Subjectif	  */
	public void setIsSubjectif (boolean IsSubjectif);

	/** Get Est Subjectif	  */
	public boolean isSubjectif();

    /** Column name ScoreFinal */
    public static final String COLUMNNAME_ScoreFinal = "ScoreFinal";

	/** Set Score Final	  */
	public void setScoreFinal (BigDecimal ScoreFinal);

	/** Get Score Final	  */
	public BigDecimal getScoreFinal();

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

    /** Column name Score_Employe */
    public static final String COLUMNNAME_Score_Employe = "Score_Employe";

	/** Set Score par l&#039;
Employé(e)	  */
	public void setScore_Employe (BigDecimal Score_Employe);

	/** Get Score par l&#039;
Employé(e)	  */
	public BigDecimal getScore_Employe();

    /** Column name Score_N1 */
    public static final String COLUMNNAME_Score_N1 = "Score_N1";

	/** Set Score par N1	  */
	public void setScore_N1 (BigDecimal Score_N1);

	/** Get Score par N1	  */
	public BigDecimal getScore_N1();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name SeuilEchec */
    public static final String COLUMNNAME_SeuilEchec = "SeuilEchec";

	/** Set Seuil Échec	  */
	public void setSeuilEchec (BigDecimal SeuilEchec);

	/** Get Seuil Échec	  */
	public BigDecimal getSeuilEchec();

    /** Column name SeuilValidation */
    public static final String COLUMNNAME_SeuilValidation = "SeuilValidation";

	/** Set Seuil de Validation	  */
	public void setSeuilValidation (BigDecimal SeuilValidation);

	/** Get Seuil de Validation	  */
	public BigDecimal getSeuilValidation();

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

    /** Column name ValeurCible */
    public static final String COLUMNNAME_ValeurCible = "ValeurCible";

	/** Set Valeur Cible	  */
	public void setValeurCible (BigDecimal ValeurCible);

	/** Get Valeur Cible	  */
	public BigDecimal getValeurCible();

    /** Column name ValeurMax */
    public static final String COLUMNNAME_ValeurMax = "ValeurMax";

	/** Set Valeur Max	  */
	public void setValeurMax (BigDecimal ValeurMax);

	/** Get Valeur Max	  */
	public BigDecimal getValeurMax();

    /** Column name ValeurMin */
    public static final String COLUMNNAME_ValeurMin = "ValeurMin";

	/** Set Valeur Min	  */
	public void setValeurMin (BigDecimal ValeurMin);

	/** Get Valeur Min	  */
	public BigDecimal getValeurMin();

    /** Column name ValeurRealisee */
    public static final String COLUMNNAME_ValeurRealisee = "ValeurRealisee";

	/** Set Valeur Réalisée	  */
	public void setValeurRealisee (BigDecimal ValeurRealisee);

	/** Get Valeur Réalisée	  */
	public BigDecimal getValeurRealisee();
}
