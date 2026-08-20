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

/** Generated Interface for HR_Eval
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Eval 
{

    /** TableName=HR_Eval */
    public static final String Table_Name = "HR_Eval";

    /** AD_Table_ID=1018383 */
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

    /** Column name CommentaireEmploye */
    public static final String COLUMNNAME_CommentaireEmploye = "CommentaireEmploye";

	/** Set Commentaire Employé(e)	  */
	public void setCommentaireEmploye (String CommentaireEmploye);

	/** Get Commentaire Employé(e)	  */
	public String getCommentaireEmploye();

    /** Column name CommentaireN1 */
    public static final String COLUMNNAME_CommentaireN1 = "CommentaireN1";

	/** Set Commentaire N1	  */
	public void setCommentaireN1 (String CommentaireN1);

	/** Get Commentaire N1	  */
	public String getCommentaireN1();

    /** Column name CommentaireN2 */
    public static final String COLUMNNAME_CommentaireN2 = "CommentaireN2";

	/** Set Commentaire N2	  */
	public void setCommentaireN2 (String CommentaireN2);

	/** Get Commentaire N2	  */
	public String getCommentaireN2();

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

    /** Column name Date_Soumission_Employe */
    public static final String COLUMNNAME_Date_Soumission_Employe = "Date_Soumission_Employe";

	/** Set Date de Soumission Employé(e)	  */
	public void setDate_Soumission_Employe (Timestamp Date_Soumission_Employe);

	/** Get Date de Soumission Employé(e)	  */
	public Timestamp getDate_Soumission_Employe();

    /** Column name Date_Soumission_N1 */
    public static final String COLUMNNAME_Date_Soumission_N1 = "Date_Soumission_N1";

	/** Set Date de Soumission N1	  */
	public void setDate_Soumission_N1 (Timestamp Date_Soumission_N1);

	/** Get Date de Soumission N1	  */
	public Timestamp getDate_Soumission_N1();

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

    /** Column name Evaluateur_N1_ID */
    public static final String COLUMNNAME_Evaluateur_N1_ID = "Evaluateur_N1_ID";

	/** Set Évaluateur N1	  */
	public void setEvaluateur_N1_ID (int Evaluateur_N1_ID);

	/** Get Évaluateur N1	  */
	public int getEvaluateur_N1_ID();

	public org.compiere.model.I_C_BPartner getEvaluateur_N1() throws RuntimeException;

    /** Column name Evaluateur_N2_ID */
    public static final String COLUMNNAME_Evaluateur_N2_ID = "Evaluateur_N2_ID";

	/** Set Évaluateur N2	  */
	public void setEvaluateur_N2_ID (int Evaluateur_N2_ID);

	/** Get Évaluateur N2	  */
	public int getEvaluateur_N2_ID();

	public org.compiere.model.I_C_BPartner getEvaluateur_N2() throws RuntimeException;

    /** Column name GenererEvaluation */
    public static final String COLUMNNAME_GenererEvaluation = "GenererEvaluation";

	/** Set Générer Évaluation	  */
	public void setGenererEvaluation (String GenererEvaluation);

	/** Get Générer Évaluation	  */
	public String getGenererEvaluation();

    /** Column name HR_EvalAppreciation_ID */
    public static final String COLUMNNAME_HR_EvalAppreciation_ID = "HR_EvalAppreciation_ID";

	/** Set Appréciation Évaluation	  */
	public void setHR_EvalAppreciation_ID (int HR_EvalAppreciation_ID);

	/** Get Appréciation Évaluation	  */
	public int getHR_EvalAppreciation_ID();

	public I_HR_EvalAppreciation getHR_EvalAppreciation() throws RuntimeException;

    /** Column name HR_EvalGrille_ID */
    public static final String COLUMNNAME_HR_EvalGrille_ID = "HR_EvalGrille_ID";

	/** Set Grille d&#039;
Évaluation	  */
	public void setHR_EvalGrille_ID (int HR_EvalGrille_ID);

	/** Get Grille d&#039;
Évaluation	  */
	public int getHR_EvalGrille_ID();

	public I_HR_EvalGrille getHR_EvalGrille() throws RuntimeException;

    /** Column name HR_EvalPeriode_ID */
    public static final String COLUMNNAME_HR_EvalPeriode_ID = "HR_EvalPeriode_ID";

	/** Set Période d&#039;
Évaluation	  */
	public void setHR_EvalPeriode_ID (int HR_EvalPeriode_ID);

	/** Get Période d&#039;
Évaluation	  */
	public int getHR_EvalPeriode_ID();

	public I_HR_EvalPeriode getHR_EvalPeriode() throws RuntimeException;

    /** Column name HR_EvalStatut_ID */
    public static final String COLUMNNAME_HR_EvalStatut_ID = "HR_EvalStatut_ID";

	/** Set Statut Évaluation	  */
	public void setHR_EvalStatut_ID (int HR_EvalStatut_ID);

	/** Get Statut Évaluation	  */
	public int getHR_EvalStatut_ID();

	public I_HR_EvalStatut getHR_EvalStatut() throws RuntimeException;

    /** Column name HR_Eval_ID */
    public static final String COLUMNNAME_HR_Eval_ID = "HR_Eval_ID";

	/** Set Évaluation	  */
	public void setHR_Eval_ID (int HR_Eval_ID);

	/** Get Évaluation	  */
	public int getHR_Eval_ID();

    /** Column name HR_Eval_UU */
    public static final String COLUMNNAME_HR_Eval_UU = "HR_Eval_UU";

	/** Set HR_Eval_UU	  */
	public void setHR_Eval_UU (String HR_Eval_UU);

	/** Get HR_Eval_UU	  */
	public String getHR_Eval_UU();

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

    /** Column name IsGeneree */
    public static final String COLUMNNAME_IsGeneree = "IsGeneree";

	/** Set Généré(e)	  */
	public void setIsGeneree (boolean IsGeneree);

	/** Get Généré(e)	  */
	public boolean isGeneree();

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

    /** Column name IsSoumiseEmploye */
    public static final String COLUMNNAME_IsSoumiseEmploye = "IsSoumiseEmploye";

	/** Set Soumise par Employé(e)	  */
	public void setIsSoumiseEmploye (boolean IsSoumiseEmploye);

	/** Get Soumise par Employé(e)	  */
	public boolean isSoumiseEmploye();

    /** Column name IsSoumiseN1 */
    public static final String COLUMNNAME_IsSoumiseN1 = "IsSoumiseN1";

	/** Set Soumise par N1	  */
	public void setIsSoumiseN1 (boolean IsSoumiseN1);

	/** Get Soumise par N1	  */
	public boolean isSoumiseN1();

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

    /** Column name NombreEchec */
    public static final String COLUMNNAME_NombreEchec = "NombreEchec";

	/** Set Nombre d&#039;
Objectifs Échoués	  */
	public void setNombreEchec (int NombreEchec);

	/** Get Nombre d&#039;
Objectifs Échoués	  */
	public int getNombreEchec();

    /** Column name NombreEliminatoires */
    public static final String COLUMNNAME_NombreEliminatoires = "NombreEliminatoires";

	/** Set Nombre d&#039;
Objectifs Éliminatoires	  */
	public void setNombreEliminatoires (int NombreEliminatoires);

	/** Get Nombre d&#039;
Objectifs Éliminatoires	  */
	public int getNombreEliminatoires();

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

    /** Column name NombreReussis */
    public static final String COLUMNNAME_NombreReussis = "NombreReussis";

	/** Set Nombre d&#039;
Objectifs Réussis	  */
	public void setNombreReussis (int NombreReussis);

	/** Get Nombre d&#039;
Objectifs Réussis	  */
	public int getNombreReussis();

    /** Column name PourcentageAvancement */
    public static final String COLUMNNAME_PourcentageAvancement = "PourcentageAvancement";

	/** Set Avancement (%)	  */
	public void setPourcentageAvancement (int PourcentageAvancement);

	/** Get Avancement (%)	  */
	public int getPourcentageAvancement();

    /** Column name RenvoyerVersEmploye */
    public static final String COLUMNNAME_RenvoyerVersEmploye = "RenvoyerVersEmploye";

	/** Set Renvoyer vers Employé	  */
	public void setRenvoyerVersEmploye (String RenvoyerVersEmploye);

	/** Get Renvoyer vers Employé	  */
	public String getRenvoyerVersEmploye();

    /** Column name RenvoyerVersN1 */
    public static final String COLUMNNAME_RenvoyerVersN1 = "RenvoyerVersN1";

	/** Set Renvoyer vers N1	  */
	public void setRenvoyerVersN1 (String RenvoyerVersN1);

	/** Get Renvoyer vers N1	  */
	public String getRenvoyerVersN1();

    /** Column name ScoreTotal */
    public static final String COLUMNNAME_ScoreTotal = "ScoreTotal";

	/** Set Score Total 	  */
	public void setScoreTotal (BigDecimal ScoreTotal);

	/** Get Score Total 	  */
	public BigDecimal getScoreTotal();

    /** Column name ScoreTotalMax */
    public static final String COLUMNNAME_ScoreTotalMax = "ScoreTotalMax";

	/** Set Score Total Maximum	  */
	public void setScoreTotalMax (BigDecimal ScoreTotalMax);

	/** Get Score Total Maximum	  */
	public BigDecimal getScoreTotalMax();

    /** Column name SoumettreEmploye */
    public static final String COLUMNNAME_SoumettreEmploye = "SoumettreEmploye";

	/** Set Soumettre Employé	  */
	public void setSoumettreEmploye (String SoumettreEmploye);

	/** Get Soumettre Employé	  */
	public String getSoumettreEmploye();

    /** Column name SoumettreN1 */
    public static final String COLUMNNAME_SoumettreN1 = "SoumettreN1";

	/** Set Soumettre N1	  */
	public void setSoumettreN1 (String SoumettreN1);

	/** Get Soumettre N1	  */
	public String getSoumettreN1();

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
