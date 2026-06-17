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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Mission_Objectif
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Mission_Objectif 
{

    /** TableName=HR_Mission_Objectif */
    public static final String Table_Name = "HR_Mission_Objectif";

    /** AD_Table_ID=1013030 */
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

    /** Column name CommentaireEvaluateur */
    public static final String COLUMNNAME_CommentaireEvaluateur = "CommentaireEvaluateur";

	/** Set Commentaire Evaluateur	  */
	public void setCommentaireEvaluateur (String CommentaireEvaluateur);

	/** Get Commentaire Evaluateur	  */
	public String getCommentaireEvaluateur();

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

    /** Column name Date_Evaluation */
    public static final String COLUMNNAME_Date_Evaluation = "Date_Evaluation";

	/** Set Date de l&#039;
Évaluation	  */
	public void setDate_Evaluation (Timestamp Date_Evaluation);

	/** Get Date de l&#039;
Évaluation	  */
	public Timestamp getDate_Evaluation();

    /** Column name Evalue_Par_Matricule */
    public static final String COLUMNNAME_Evalue_Par_Matricule = "Evalue_Par_Matricule";

	/** Set Matricule de l&#039;
Évaluateur	  */
	public void setEvalue_Par_Matricule (String Evalue_Par_Matricule);

	/** Get Matricule de l&#039;
Évaluateur	  */
	public String getEvalue_Par_Matricule();

    /** Column name Evalue_Par_Nom_ID */
    public static final String COLUMNNAME_Evalue_Par_Nom_ID = "Evalue_Par_Nom_ID";

	/** Set Nom de l&#039;
Évaluateur	  */
	public void setEvalue_Par_Nom_ID (int Evalue_Par_Nom_ID);

	/** Get Nom de l&#039;
Évaluateur	  */
	public int getEvalue_Par_Nom_ID();

	public org.compiere.model.I_C_BPartner getEvalue_Par_Nom() throws RuntimeException;

    /** Column name Evalue_Par_Poste_ID */
    public static final String COLUMNNAME_Evalue_Par_Poste_ID = "Evalue_Par_Poste_ID";

	/** Set Poste de l&#039;
Évaluateur	  */
	public void setEvalue_Par_Poste_ID (int Evalue_Par_Poste_ID);

	/** Get Poste de l&#039;
Évaluateur	  */
	public int getEvalue_Par_Poste_ID();

	public org.eevolution.model.I_HR_Job getEvalue_Par_Poste() throws RuntimeException;

    /** Column name HR_Appreciation_ID */
    public static final String COLUMNNAME_HR_Appreciation_ID = "HR_Appreciation_ID";

	/** Set Appréciation	  */
	public void setHR_Appreciation_ID (int HR_Appreciation_ID);

	/** Get Appréciation	  */
	public int getHR_Appreciation_ID();

	public I_HR_Appreciation getHR_Appreciation() throws RuntimeException;

    /** Column name HR_EtatObjectifMission_ID */
    public static final String COLUMNNAME_HR_EtatObjectifMission_ID = "HR_EtatObjectifMission_ID";

	/** Set État de l&#039;
Objectif	  */
	public void setHR_EtatObjectifMission_ID (int HR_EtatObjectifMission_ID);

	/** Get État de l&#039;
Objectif	  */
	public int getHR_EtatObjectifMission_ID();

	public I_HR_EtatObjectifMission getHR_EtatObjectifMission() throws RuntimeException;

    /** Column name HR_Mission_ID */
    public static final String COLUMNNAME_HR_Mission_ID = "HR_Mission_ID";

	/** Set Mission	  */
	public void setHR_Mission_ID (int HR_Mission_ID);

	/** Get Mission	  */
	public int getHR_Mission_ID();

	public I_HR_Mission getHR_Mission() throws RuntimeException;

    /** Column name HR_Mission_Objectif_ID */
    public static final String COLUMNNAME_HR_Mission_Objectif_ID = "HR_Mission_Objectif_ID";

	/** Set Objectifs de Mission	  */
	public void setHR_Mission_Objectif_ID (int HR_Mission_Objectif_ID);

	/** Get Objectifs de Mission	  */
	public int getHR_Mission_Objectif_ID();

    /** Column name HR_Mission_Objectif_UU */
    public static final String COLUMNNAME_HR_Mission_Objectif_UU = "HR_Mission_Objectif_UU";

	/** Set HR_Mission_Objectif_UU	  */
	public void setHR_Mission_Objectif_UU (String HR_Mission_Objectif_UU);

	/** Get HR_Mission_Objectif_UU	  */
	public String getHR_Mission_Objectif_UU();

    /** Column name HR_TypeObjectif_ID */
    public static final String COLUMNNAME_HR_TypeObjectif_ID = "HR_TypeObjectif_ID";

	/** Set Type d&#039;
Objectif	  */
	public void setHR_TypeObjectif_ID (int HR_TypeObjectif_ID);

	/** Get Type d&#039;
Objectif	  */
	public int getHR_TypeObjectif_ID();

	public I_HR_TypeObjectif getHR_TypeObjectif() throws RuntimeException;

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
