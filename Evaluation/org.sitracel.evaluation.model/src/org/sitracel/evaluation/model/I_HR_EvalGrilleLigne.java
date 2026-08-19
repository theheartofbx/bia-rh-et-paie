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

/** Generated Interface for HR_EvalGrilleLigne
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_EvalGrilleLigne 
{

    /** TableName=HR_EvalGrilleLigne */
    public static final String Table_Name = "HR_EvalGrilleLigne";

    /** AD_Table_ID=1018181 */
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

    /** Column name HR_EvalGrilleLigne_ID */
    public static final String COLUMNNAME_HR_EvalGrilleLigne_ID = "HR_EvalGrilleLigne_ID";

	/** Set Ligne Grille Évaluation	  */
	public void setHR_EvalGrilleLigne_ID (int HR_EvalGrilleLigne_ID);

	/** Get Ligne Grille Évaluation	  */
	public int getHR_EvalGrilleLigne_ID();

    /** Column name HR_EvalGrilleLigne_UU */
    public static final String COLUMNNAME_HR_EvalGrilleLigne_UU = "HR_EvalGrilleLigne_UU";

	/** Set HR_EvalGrilleLigne_UU	  */
	public void setHR_EvalGrilleLigne_UU (String HR_EvalGrilleLigne_UU);

	/** Get HR_EvalGrilleLigne_UU	  */
	public String getHR_EvalGrilleLigne_UU();

    /** Column name HR_EvalGrille_ID */
    public static final String COLUMNNAME_HR_EvalGrille_ID = "HR_EvalGrille_ID";

	/** Set Grille d&#039;
Évaluation	  */
	public void setHR_EvalGrille_ID (int HR_EvalGrille_ID);

	/** Get Grille d&#039;
Évaluation	  */
	public int getHR_EvalGrille_ID();

	public I_HR_EvalGrille getHR_EvalGrille() throws RuntimeException;

    /** Column name HR_EvalObjectif_ID */
    public static final String COLUMNNAME_HR_EvalObjectif_ID = "HR_EvalObjectif_ID";

	/** Set Objectif d&#039;
Évaluation	  */
	public void setHR_EvalObjectif_ID (int HR_EvalObjectif_ID);

	/** Get Objectif d&#039;
Évaluation	  */
	public int getHR_EvalObjectif_ID();

	public I_HR_EvalObjectif getHR_EvalObjectif() throws RuntimeException;

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

    /** Column name ScoreTest */
    public static final String COLUMNNAME_ScoreTest = "ScoreTest";

	/** Set Score Test	  */
	public void setScoreTest (BigDecimal ScoreTest);

	/** Get Score Test	  */
	public BigDecimal getScoreTest();

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
}
