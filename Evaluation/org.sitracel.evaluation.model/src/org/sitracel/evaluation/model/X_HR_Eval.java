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

/** Generated Model for HR_Eval
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Eval")
public class X_HR_Eval extends PO implements I_HR_Eval, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260819L;

    /** Standard Constructor */
    public X_HR_Eval (Properties ctx, int HR_Eval_ID, String trxName)
    {
      super (ctx, HR_Eval_ID, trxName);
      /** if (HR_Eval_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalPeriode_ID (0);
			setHR_Eval_ID (0);
			setIsGeneree (false);
// N
			setIsRejetee (false);
// N
			setIsSoumiseEmploye (false);
// N
			setIsSoumiseN1 (false);
// N
			setIsValidee (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_HR_Eval (Properties ctx, int HR_Eval_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Eval_ID, trxName, virtualColumns);
      /** if (HR_Eval_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalPeriode_ID (0);
			setHR_Eval_ID (0);
			setIsGeneree (false);
// N
			setIsRejetee (false);
// N
			setIsSoumiseEmploye (false);
// N
			setIsSoumiseN1 (false);
// N
			setIsValidee (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_HR_Eval (Properties ctx, String HR_Eval_UU, String trxName)
    {
      super (ctx, HR_Eval_UU, trxName);
      /** if (HR_Eval_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalPeriode_ID (0);
			setHR_Eval_ID (0);
			setIsGeneree (false);
// N
			setIsRejetee (false);
// N
			setIsSoumiseEmploye (false);
// N
			setIsSoumiseN1 (false);
// N
			setIsValidee (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_HR_Eval (Properties ctx, String HR_Eval_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Eval_UU, trxName, virtualColumns);
      /** if (HR_Eval_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalPeriode_ID (0);
			setHR_Eval_ID (0);
			setIsGeneree (false);
// N
			setIsRejetee (false);
// N
			setIsSoumiseEmploye (false);
// N
			setIsSoumiseN1 (false);
// N
			setIsValidee (false);
// N
        } */
    }

    /** Load Constructor */
    public X_HR_Eval (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Eval[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	/** Set Commentaire Employé(e).
		@param CommentaireEmploye Commentaire Employé(e)
	*/
	public void setCommentaireEmploye (String CommentaireEmploye)
	{
		set_Value (COLUMNNAME_CommentaireEmploye, CommentaireEmploye);
	}

	/** Get Commentaire Employé(e).
		@return Commentaire Employé(e)	  */
	public String getCommentaireEmploye()
	{
		return (String)get_Value(COLUMNNAME_CommentaireEmploye);
	}

	/** Set Commentaire N1.
		@param CommentaireN1 Commentaire N1
	*/
	public void setCommentaireN1 (String CommentaireN1)
	{
		set_Value (COLUMNNAME_CommentaireN1, CommentaireN1);
	}

	/** Get Commentaire N1.
		@return Commentaire N1	  */
	public String getCommentaireN1()
	{
		return (String)get_Value(COLUMNNAME_CommentaireN1);
	}

	/** Set Commentaire N2.
		@param CommentaireN2 Commentaire N2
	*/
	public void setCommentaireN2 (String CommentaireN2)
	{
		set_Value (COLUMNNAME_CommentaireN2, CommentaireN2);
	}

	/** Get Commentaire N2.
		@return Commentaire N2	  */
	public String getCommentaireN2()
	{
		return (String)get_Value(COLUMNNAME_CommentaireN2);
	}

	/** Set Date de Soumission Employé(e).
		@param Date_Soumission_Employe Date de Soumission Employé(e)
	*/
	public void setDate_Soumission_Employe (Timestamp Date_Soumission_Employe)
	{
		set_Value (COLUMNNAME_Date_Soumission_Employe, Date_Soumission_Employe);
	}

	/** Get Date de Soumission Employé(e).
		@return Date de Soumission Employé(e)	  */
	public Timestamp getDate_Soumission_Employe()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Soumission_Employe);
	}

	/** Set Date de Soumission N1.
		@param Date_Soumission_N1 Date de Soumission N1
	*/
	public void setDate_Soumission_N1 (Timestamp Date_Soumission_N1)
	{
		set_Value (COLUMNNAME_Date_Soumission_N1, Date_Soumission_N1);
	}

	/** Get Date de Soumission N1.
		@return Date de Soumission N1	  */
	public Timestamp getDate_Soumission_N1()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Soumission_N1);
	}

	/** Set Date de Validation.
		@param Date_Validation Date de Validation
	*/
	public void setDate_Validation (Timestamp Date_Validation)
	{
		set_Value (COLUMNNAME_Date_Validation, Date_Validation);
	}

	/** Get Date de Validation.
		@return Date de Validation
	  */
	public Timestamp getDate_Validation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Validation);
	}

	public org.compiere.model.I_C_BPartner getEvaluateur_N1() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEvaluateur_N1_ID(), get_TrxName());
	}

	/** Set Évaluateur N1.
		@param Evaluateur_N1_ID Évaluateur N1
	*/
	public void setEvaluateur_N1_ID (int Evaluateur_N1_ID)
	{
		if (Evaluateur_N1_ID < 1)
			set_Value (COLUMNNAME_Evaluateur_N1_ID, null);
		else
			set_Value (COLUMNNAME_Evaluateur_N1_ID, Integer.valueOf(Evaluateur_N1_ID));
	}

	/** Get Évaluateur N1.
		@return Évaluateur N1	  */
	public int getEvaluateur_N1_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Evaluateur_N1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getEvaluateur_N2() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEvaluateur_N2_ID(), get_TrxName());
	}

	/** Set Évaluateur N2.
		@param Evaluateur_N2_ID Évaluateur N2
	*/
	public void setEvaluateur_N2_ID (int Evaluateur_N2_ID)
	{
		if (Evaluateur_N2_ID < 1)
			set_Value (COLUMNNAME_Evaluateur_N2_ID, null);
		else
			set_Value (COLUMNNAME_Evaluateur_N2_ID, Integer.valueOf(Evaluateur_N2_ID));
	}

	/** Get Évaluateur N2.
		@return Évaluateur N2	  */
	public int getEvaluateur_N2_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Evaluateur_N2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_EvalAppreciation getHR_EvalAppreciation() throws RuntimeException
	{
		return (I_HR_EvalAppreciation)MTable.get(getCtx(), I_HR_EvalAppreciation.Table_ID)
			.getPO(getHR_EvalAppreciation_ID(), get_TrxName());
	}

	/** Set Appréciation Évaluation.
		@param HR_EvalAppreciation_ID Appréciation Évaluation
	*/
	public void setHR_EvalAppreciation_ID (int HR_EvalAppreciation_ID)
	{
		if (HR_EvalAppreciation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalAppreciation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalAppreciation_ID, Integer.valueOf(HR_EvalAppreciation_ID));
	}

	/** Get Appréciation Évaluation.
		@return Appréciation Évaluation	  */
	public int getHR_EvalAppreciation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalAppreciation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_EvalGrille getHR_EvalGrille() throws RuntimeException
	{
		return (I_HR_EvalGrille)MTable.get(getCtx(), I_HR_EvalGrille.Table_ID)
			.getPO(getHR_EvalGrille_ID(), get_TrxName());
	}

	/** Set Grille d&#039;Évaluation.
		@param HR_EvalGrille_ID Grille d&#039;Évaluation
	*/
	public void setHR_EvalGrille_ID (int HR_EvalGrille_ID)
	{
		if (HR_EvalGrille_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrille_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrille_ID, Integer.valueOf(HR_EvalGrille_ID));
	}

	/** Get Grille d&#039;Évaluation.
		@return Grille d&#039;Évaluation	  */
	public int getHR_EvalGrille_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalGrille_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_EvalPeriode getHR_EvalPeriode() throws RuntimeException
	{
		return (I_HR_EvalPeriode)MTable.get(getCtx(), I_HR_EvalPeriode.Table_ID)
			.getPO(getHR_EvalPeriode_ID(), get_TrxName());
	}

	/** Set Période d&#039;Évaluation.
		@param HR_EvalPeriode_ID Période d&#039;Évaluation
	*/
	public void setHR_EvalPeriode_ID (int HR_EvalPeriode_ID)
	{
		if (HR_EvalPeriode_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalPeriode_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalPeriode_ID, Integer.valueOf(HR_EvalPeriode_ID));
	}

	/** Get Période d&#039;Évaluation.
		@return Période d&#039;Évaluation	  */
	public int getHR_EvalPeriode_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalPeriode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_EvalStatut getHR_EvalStatut() throws RuntimeException
	{
		return (I_HR_EvalStatut)MTable.get(getCtx(), I_HR_EvalStatut.Table_ID)
			.getPO(getHR_EvalStatut_ID(), get_TrxName());
	}

	/** Set Statut Évaluation.
		@param HR_EvalStatut_ID Statut Évaluation
	*/
	public void setHR_EvalStatut_ID (int HR_EvalStatut_ID)
	{
		if (HR_EvalStatut_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalStatut_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalStatut_ID, Integer.valueOf(HR_EvalStatut_ID));
	}

	/** Get Statut Évaluation.
		@return Statut Évaluation	  */
	public int getHR_EvalStatut_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalStatut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Évaluation.
		@param HR_Eval_ID Évaluation
	*/
	public void setHR_Eval_ID (int HR_Eval_ID)
	{
		if (HR_Eval_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Eval_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Eval_ID, Integer.valueOf(HR_Eval_ID));
	}

	/** Get Évaluation.
		@return Évaluation	  */
	public int getHR_Eval_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Eval_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Eval_UU.
		@param HR_Eval_UU HR_Eval_UU
	*/
	public void setHR_Eval_UU (String HR_Eval_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_Eval_UU, HR_Eval_UU);
	}

	/** Get HR_Eval_UU.
		@return HR_Eval_UU	  */
	public String getHR_Eval_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Eval_UU);
	}

	/** Set Généré(e).
		@param IsGeneree Généré(e)
	*/
	public void setIsGeneree (boolean IsGeneree)
	{
		set_Value (COLUMNNAME_IsGeneree, Boolean.valueOf(IsGeneree));
	}

	/** Get Généré(e).
		@return Généré(e)	  */
	public boolean isGeneree()
	{
		Object oo = get_Value(COLUMNNAME_IsGeneree);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rejeté(e).
		@param IsRejetee Rejeté(e)
	*/
	public void setIsRejetee (boolean IsRejetee)
	{
		set_Value (COLUMNNAME_IsRejetee, Boolean.valueOf(IsRejetee));
	}

	/** Get Rejeté(e).
		@return Rejeté(e)
	  */
	public boolean isRejetee()
	{
		Object oo = get_Value(COLUMNNAME_IsRejetee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Soumise par Employé(e).
		@param IsSoumiseEmploye Soumise par Employé(e)
	*/
	public void setIsSoumiseEmploye (boolean IsSoumiseEmploye)
	{
		set_Value (COLUMNNAME_IsSoumiseEmploye, Boolean.valueOf(IsSoumiseEmploye));
	}

	/** Get Soumise par Employé(e).
		@return Soumise par Employé(e)	  */
	public boolean isSoumiseEmploye()
	{
		Object oo = get_Value(COLUMNNAME_IsSoumiseEmploye);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Soumise par N1.
		@param IsSoumiseN1 Soumise par N1
	*/
	public void setIsSoumiseN1 (boolean IsSoumiseN1)
	{
		set_Value (COLUMNNAME_IsSoumiseN1, Boolean.valueOf(IsSoumiseN1));
	}

	/** Get Soumise par N1.
		@return Soumise par N1	  */
	public boolean isSoumiseN1()
	{
		Object oo = get_Value(COLUMNNAME_IsSoumiseN1);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Validé(e).
		@param IsValidee Validé(e)
	*/
	public void setIsValidee (boolean IsValidee)
	{
		set_Value (COLUMNNAME_IsValidee, Boolean.valueOf(IsValidee));
	}

	/** Get Validé(e).
		@return Validé(e)
	  */
	public boolean isValidee()
	{
		Object oo = get_Value(COLUMNNAME_IsValidee);
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

	/** Set Nombre d&#039;Objectifs Échoués.
		@param NombreEchec Nombre d&#039;Objectifs Échoués
	*/
	public void setNombreEchec (int NombreEchec)
	{
		set_ValueNoCheck (COLUMNNAME_NombreEchec, Integer.valueOf(NombreEchec));
	}

	/** Get Nombre d&#039;Objectifs Échoués.
		@return Nombre d&#039;Objectifs Échoués	  */
	public int getNombreEchec()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreEchec);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre d&#039;Objectifs Éliminatoires.
		@param NombreEliminatoires Nombre d&#039;Objectifs Éliminatoires
	*/
	public void setNombreEliminatoires (int NombreEliminatoires)
	{
		set_ValueNoCheck (COLUMNNAME_NombreEliminatoires, Integer.valueOf(NombreEliminatoires));
	}

	/** Get Nombre d&#039;Objectifs Éliminatoires.
		@return Nombre d&#039;Objectifs Éliminatoires	  */
	public int getNombreEliminatoires()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreEliminatoires);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Objectifs Évalués.
		@param NombreEvalues Objectifs Évalués
	*/
	public void setNombreEvalues (int NombreEvalues)
	{
		set_ValueNoCheck (COLUMNNAME_NombreEvalues, Integer.valueOf(NombreEvalues));
	}

	/** Get Objectifs Évalués.
		@return Objectifs Évalués	  */
	public int getNombreEvalues()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreEvalues);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre d&#039;Objectifs.
		@param NombreObjectifs Nombre d&#039;Objectifs
	*/
	public void setNombreObjectifs (int NombreObjectifs)
	{
		set_ValueNoCheck (COLUMNNAME_NombreObjectifs, Integer.valueOf(NombreObjectifs));
	}

	/** Get Nombre d&#039;Objectifs.
		@return Nombre d&#039;Objectifs	  */
	public int getNombreObjectifs()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreObjectifs);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre d&#039;Objectifs Réussis.
		@param NombreReussis Nombre d&#039;Objectifs Réussis
	*/
	public void setNombreReussis (int NombreReussis)
	{
		set_ValueNoCheck (COLUMNNAME_NombreReussis, Integer.valueOf(NombreReussis));
	}

	/** Get Nombre d&#039;Objectifs Réussis.
		@return Nombre d&#039;Objectifs Réussis	  */
	public int getNombreReussis()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreReussis);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Avancement (%).
		@param PourcentageAvancement Avancement (%)
	*/
	public void setPourcentageAvancement (int PourcentageAvancement)
	{
		set_ValueNoCheck (COLUMNNAME_PourcentageAvancement, Integer.valueOf(PourcentageAvancement));
	}

	/** Get Avancement (%).
		@return Avancement (%)	  */
	public int getPourcentageAvancement()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PourcentageAvancement);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Score Total .
		@param ScoreTotal Score Total 
	*/
	public void setScoreTotal (BigDecimal ScoreTotal)
	{
		set_ValueNoCheck (COLUMNNAME_ScoreTotal, ScoreTotal);
	}

	/** Get Score Total .
		@return Score Total 	  */
	public BigDecimal getScoreTotal()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score Total Maximum.
		@param ScoreTotalMax Score Total Maximum
	*/
	public void setScoreTotalMax (BigDecimal ScoreTotalMax)
	{
		set_ValueNoCheck (COLUMNNAME_ScoreTotalMax, ScoreTotalMax);
	}

	/** Get Score Total Maximum.
		@return Score Total Maximum	  */
	public BigDecimal getScoreTotalMax()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreTotalMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}