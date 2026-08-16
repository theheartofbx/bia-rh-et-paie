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
import org.sitracel.contrat.model.I_HR_Contrat;
import org.sitracel.mission.model.I_HR_Appreciation;

/** Generated Interface for HR_Stage
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Stage 
{

    /** TableName=HR_Stage */
    public static final String Table_Name = "HR_Stage";

    /** AD_Table_ID=1017171 */
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

    /** Column name Commentaire_Global */
    public static final String COLUMNNAME_Commentaire_Global = "Commentaire_Global";

	/** Set Commentaire_Global	  */
	public void setCommentaire_Global (String Commentaire_Global);

	/** Get Commentaire_Global	  */
	public String getCommentaire_Global();

    /** Column name Contrat_ID */
    public static final String COLUMNNAME_Contrat_ID = "Contrat_ID";

	/** Set Contrat	  */
	public void setContrat_ID (int Contrat_ID);

	/** Get Contrat	  */
	public int getContrat_ID();

	public I_HR_Contrat getContrat() throws RuntimeException;

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

    /** Column name GenererPlanning */
    public static final String COLUMNNAME_GenererPlanning = "GenererPlanning";

	/** Set GenererPlanning	  */
	public void setGenererPlanning (String GenererPlanning);

	/** Get GenererPlanning	  */
	public String getGenererPlanning();

    /** Column name HR_Appreciation_ID */
    public static final String COLUMNNAME_HR_Appreciation_ID = "HR_Appreciation_ID";

	/** Set Appréciation	  */
	public void setHR_Appreciation_ID (int HR_Appreciation_ID);

	/** Get Appréciation	  */
	public int getHR_Appreciation_ID();

	public I_HR_Appreciation getHR_Appreciation() throws RuntimeException;

    /** Column name HR_StageProgramme_ID */
    public static final String COLUMNNAME_HR_StageProgramme_ID = "HR_StageProgramme_ID";

	/** Set Programme Stage	  */
	public void setHR_StageProgramme_ID (int HR_StageProgramme_ID);

	/** Get Programme Stage	  */
	public int getHR_StageProgramme_ID();

	public I_HR_StageProgramme getHR_StageProgramme() throws RuntimeException;

    /** Column name HR_Stage_ID */
    public static final String COLUMNNAME_HR_Stage_ID = "HR_Stage_ID";

	/** Set Stage	  */
	public void setHR_Stage_ID (int HR_Stage_ID);

	/** Get Stage	  */
	public int getHR_Stage_ID();

    /** Column name HR_Stage_UU */
    public static final String COLUMNNAME_HR_Stage_UU = "HR_Stage_UU";

	/** Set HR_Stage_UU	  */
	public void setHR_Stage_UU (String HR_Stage_UU);

	/** Get HR_Stage_UU	  */
	public String getHR_Stage_UU();

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

    /** Column name IsRejetee */
    public static final String COLUMNNAME_IsRejetee = "IsRejetee";

	/** Set Rejeté(e).
	  * Rejeté(e)
	  */
	public void setIsRejetee (boolean IsRejetee);

	/** Get Rejeté(e).
	  * Rejeté(e)
	  */
	public boolean isRejetee();

    /** Column name IsValidee */
    public static final String COLUMNNAME_IsValidee = "IsValidee";

	/** Set Validé(e).
	  * Validé(e)
	  */
	public void setIsValidee (boolean IsValidee);

	/** Get Validé(e).
	  * Validé(e)
	  */
	public boolean isValidee();

    /** Column name NombreDefinis */
    public static final String COLUMNNAME_NombreDefinis = "NombreDefinis";

	/** Set Objectifs Définis	  */
	public void setNombreDefinis (int NombreDefinis);

	/** Get Objectifs Définis	  */
	public int getNombreDefinis();

    /** Column name NombreEvalues */
    public static final String COLUMNNAME_NombreEvalues = "NombreEvalues";

	/** Set Objectifs Évalués	  */
	public void setNombreEvalues (int NombreEvalues);

	/** Get Objectifs Évalués	  */
	public int getNombreEvalues();

    /** Column name NombreObjectifs */
    public static final String COLUMNNAME_NombreObjectifs = "NombreObjectifs";

	/** Set Nombre d&#039;
Objectifs	  */
	public void setNombreObjectifs (int NombreObjectifs);

	/** Get Nombre d&#039;
Objectifs	  */
	public int getNombreObjectifs();

    /** Column name PourcentageAvancement */
    public static final String COLUMNNAME_PourcentageAvancement = "PourcentageAvancement";

	/** Set Avancement (%)	  */
	public void setPourcentageAvancement (int PourcentageAvancement);

	/** Get Avancement (%)	  */
	public int getPourcentageAvancement();

    /** Column name ScoreMax_Total */
    public static final String COLUMNNAME_ScoreMax_Total = "ScoreMax_Total";

	/** Set Score Max Total	  */
	public void setScoreMax_Total (BigDecimal ScoreMax_Total);

	/** Get Score Max Total	  */
	public BigDecimal getScoreMax_Total();

    /** Column name Score_Total */
    public static final String COLUMNNAME_Score_Total = "Score_Total";

	/** Set Score Total	  */
	public void setScore_Total (BigDecimal Score_Total);

	/** Get Score Total	  */
	public BigDecimal getScore_Total();

    /** Column name Theme */
    public static final String COLUMNNAME_Theme = "Theme";

	/** Set Theme.
	  * Theme name
	  */
	public void setTheme (String Theme);

	/** Get Theme.
	  * Theme name
	  */
	public String getTheme();

    /** Column name Tuteur_ID */
    public static final String COLUMNNAME_Tuteur_ID = "Tuteur_ID";

	/** Set Tuteur	  */
	public void setTuteur_ID (int Tuteur_ID);

	/** Get Tuteur	  */
	public int getTuteur_ID();

	public org.compiere.model.I_C_BPartner getTuteur() throws RuntimeException;

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

    /** Column name Valider */
    public static final String COLUMNNAME_Valider = "Valider";

	/** Set Valider .
	  * Valider 
	  */
	public void setValider (String Valider);

	/** Get Valider .
	  * Valider 
	  */
	public String getValider();
}
