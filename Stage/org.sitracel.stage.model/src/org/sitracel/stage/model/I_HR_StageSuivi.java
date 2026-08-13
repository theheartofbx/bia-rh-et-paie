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
package org.sitracel.stage.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;
import org.sitracel.mission.model.I_HR_Appreciation;

/** Generated Interface for HR_StageSuivi
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_StageSuivi 
{

    /** TableName=HR_StageSuivi */
    public static final String Table_Name = "HR_StageSuivi";

    /** AD_Table_ID=1017474 */
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

    /** Column name Commentaire */
    public static final String COLUMNNAME_Commentaire = "Commentaire";

	/** Set Commentaire	  */
	public void setCommentaire (String Commentaire);

	/** Get Commentaire	  */
	public String getCommentaire();

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

    /** Column name Date_Evaluation */
    public static final String COLUMNNAME_Date_Evaluation = "Date_Evaluation";

	/** Set Date de l&#039;
Évaluation	  */
	public void setDate_Evaluation (Timestamp Date_Evaluation);

	/** Get Date de l&#039;
Évaluation	  */
	public Timestamp getDate_Evaluation();

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

    /** Column name HR_Appreciation_ID */
    public static final String COLUMNNAME_HR_Appreciation_ID = "HR_Appreciation_ID";

	/** Set Appréciation	  */
	public void setHR_Appreciation_ID (int HR_Appreciation_ID);

	/** Get Appréciation	  */
	public int getHR_Appreciation_ID();

	public I_HR_Appreciation getHR_Appreciation() throws RuntimeException;

    /** Column name HR_StageEtatObjectif_ID */
    public static final String COLUMNNAME_HR_StageEtatObjectif_ID = "HR_StageEtatObjectif_ID";

	/** Set État Objectif Stage	  */
	public void setHR_StageEtatObjectif_ID (int HR_StageEtatObjectif_ID);

	/** Get État Objectif Stage	  */
	public int getHR_StageEtatObjectif_ID();

	public I_HR_StageEtatObjectif getHR_StageEtatObjectif() throws RuntimeException;

    /** Column name HR_StageObjectif_ID */
    public static final String COLUMNNAME_HR_StageObjectif_ID = "HR_StageObjectif_ID";

	/** Set Objectif Stage	  */
	public void setHR_StageObjectif_ID (int HR_StageObjectif_ID);

	/** Get Objectif Stage	  */
	public int getHR_StageObjectif_ID();

	public I_HR_StageObjectif getHR_StageObjectif() throws RuntimeException;

    /** Column name HR_StageProgrammeLigne_ID */
    public static final String COLUMNNAME_HR_StageProgrammeLigne_ID = "HR_StageProgrammeLigne_ID";

	/** Set Détail Programme Stage	  */
	public void setHR_StageProgrammeLigne_ID (int HR_StageProgrammeLigne_ID);

	/** Get Détail Programme Stage	  */
	public int getHR_StageProgrammeLigne_ID();

	public I_HR_StageProgrammeLigne getHR_StageProgrammeLigne() throws RuntimeException;

    /** Column name HR_StageSuivi_ID */
    public static final String COLUMNNAME_HR_StageSuivi_ID = "HR_StageSuivi_ID";

	/** Set Suivi Stage	  */
	public void setHR_StageSuivi_ID (int HR_StageSuivi_ID);

	/** Get Suivi Stage	  */
	public int getHR_StageSuivi_ID();

    /** Column name HR_StageSuivi_UU */
    public static final String COLUMNNAME_HR_StageSuivi_UU = "HR_StageSuivi_UU";

	/** Set HR_StageSuivi_UU	  */
	public void setHR_StageSuivi_UU (String HR_StageSuivi_UU);

	/** Get HR_StageSuivi_UU	  */
	public String getHR_StageSuivi_UU();

    /** Column name HR_Stage_ID */
    public static final String COLUMNNAME_HR_Stage_ID = "HR_Stage_ID";

	/** Set Stage	  */
	public void setHR_Stage_ID (int HR_Stage_ID);

	/** Get Stage	  */
	public int getHR_Stage_ID();

	public I_HR_Stage getHR_Stage() throws RuntimeException;

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

    /** Column name IsEvalue */
    public static final String COLUMNNAME_IsEvalue = "IsEvalue";

	/** Set IsEvalue	  */
	public void setIsEvalue (boolean IsEvalue);

	/** Get IsEvalue	  */
	public boolean isEvalue();

    /** Column name IsOk */
    public static final String COLUMNNAME_IsOk = "IsOk";

	/** Set Ok	  */
	public void setIsOk (boolean IsOk);

	/** Get Ok	  */
	public boolean isOk();

    /** Column name Lieu */
    public static final String COLUMNNAME_Lieu = "Lieu";

	/** Set Lieu	  */
	public void setLieu (String Lieu);

	/** Get Lieu	  */
	public String getLieu();

    /** Column name Ponderation */
    public static final String COLUMNNAME_Ponderation = "Ponderation";

	/** Set Ponderation	  */
	public void setPonderation (int Ponderation);

	/** Get Ponderation	  */
	public int getPonderation();

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

    /** Column name Superviseur_ID */
    public static final String COLUMNNAME_Superviseur_ID = "Superviseur_ID";

	/** Set Superviseur	  */
	public void setSuperviseur_ID (int Superviseur_ID);

	/** Get Superviseur	  */
	public int getSuperviseur_ID();

	public org.compiere.model.I_C_BPartner getSuperviseur() throws RuntimeException;

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
