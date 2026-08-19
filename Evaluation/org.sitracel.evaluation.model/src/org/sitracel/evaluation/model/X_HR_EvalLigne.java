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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_EvalLigne
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_EvalLigne")
public class X_HR_EvalLigne extends PO implements I_HR_EvalLigne, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260819L;

    /** Standard Constructor */
    public X_HR_EvalLigne (Properties ctx, int HR_EvalLigne_ID, String trxName)
    {
      super (ctx, HR_EvalLigne_ID, trxName);
      /** if (HR_EvalLigne_ID == 0)
        {
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalLigne_ID (0);
			setHR_Eval_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalLigne (Properties ctx, int HR_EvalLigne_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalLigne_ID, trxName, virtualColumns);
      /** if (HR_EvalLigne_ID == 0)
        {
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalLigne_ID (0);
			setHR_Eval_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalLigne (Properties ctx, String HR_EvalLigne_UU, String trxName)
    {
      super (ctx, HR_EvalLigne_UU, trxName);
      /** if (HR_EvalLigne_UU == null)
        {
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalLigne_ID (0);
			setHR_Eval_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalLigne (Properties ctx, String HR_EvalLigne_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalLigne_UU, trxName, virtualColumns);
      /** if (HR_EvalLigne_UU == null)
        {
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalLigne_ID (0);
			setHR_Eval_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_EvalLigne (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EvalLigne[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Acronyme.
		@param Acronyme Acronyme
	*/
	public void setAcronyme (String Acronyme)
	{
		set_Value (COLUMNNAME_Acronyme, Acronyme);
	}

	/** Get Acronyme.
		@return Acronyme	  */
	public String getAcronyme()
	{
		return (String)get_Value(COLUMNNAME_Acronyme);
	}

	/** Set Commentaire Employé(e).
		@param Commentaire_Employe Commentaire Employé(e)
	*/
	public void setCommentaire_Employe (String Commentaire_Employe)
	{
		set_Value (COLUMNNAME_Commentaire_Employe, Commentaire_Employe);
	}

	/** Get Commentaire Employé(e).
		@return Commentaire Employé(e)	  */
	public String getCommentaire_Employe()
	{
		return (String)get_Value(COLUMNNAME_Commentaire_Employe);
	}

	/** Set Commentaire N1.
		@param Commentaire_N1 Commentaire N1
	*/
	public void setCommentaire_N1 (String Commentaire_N1)
	{
		set_Value (COLUMNNAME_Commentaire_N1, Commentaire_N1);
	}

	/** Get Commentaire N1.
		@return Commentaire N1	  */
	public String getCommentaire_N1()
	{
		return (String)get_Value(COLUMNNAME_Commentaire_N1);
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
			set_Value (COLUMNNAME_HR_EvalAppreciation_ID, null);
		else
			set_Value (COLUMNNAME_HR_EvalAppreciation_ID, Integer.valueOf(HR_EvalAppreciation_ID));
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

	public I_HR_EvalGrilleLigne getHR_EvalGrilleLigne() throws RuntimeException
	{
		return (I_HR_EvalGrilleLigne)MTable.get(getCtx(), I_HR_EvalGrilleLigne.Table_ID)
			.getPO(getHR_EvalGrilleLigne_ID(), get_TrxName());
	}

	/** Set Ligne Grille Évaluation.
		@param HR_EvalGrilleLigne_ID Ligne Grille Évaluation
	*/
	public void setHR_EvalGrilleLigne_ID (int HR_EvalGrilleLigne_ID)
	{
		if (HR_EvalGrilleLigne_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleLigne_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleLigne_ID, Integer.valueOf(HR_EvalGrilleLigne_ID));
	}

	/** Get Ligne Grille Évaluation.
		@return Ligne Grille Évaluation	  */
	public int getHR_EvalGrilleLigne_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalGrilleLigne_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ligne Évaluation.
		@param HR_EvalLigne_ID Ligne Évaluation
	*/
	public void setHR_EvalLigne_ID (int HR_EvalLigne_ID)
	{
		if (HR_EvalLigne_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalLigne_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalLigne_ID, Integer.valueOf(HR_EvalLigne_ID));
	}

	/** Get Ligne Évaluation.
		@return Ligne Évaluation	  */
	public int getHR_EvalLigne_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalLigne_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_EvalLigne_UU.
		@param HR_EvalLigne_UU HR_EvalLigne_UU
	*/
	public void setHR_EvalLigne_UU (String HR_EvalLigne_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_EvalLigne_UU, HR_EvalLigne_UU);
	}

	/** Get HR_EvalLigne_UU.
		@return HR_EvalLigne_UU	  */
	public String getHR_EvalLigne_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EvalLigne_UU);
	}

	public I_HR_Eval getHR_Eval() throws RuntimeException
	{
		return (I_HR_Eval)MTable.get(getCtx(), I_HR_Eval.Table_ID)
			.getPO(getHR_Eval_ID(), get_TrxName());
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

	/** Set Est Binaire.
		@param IsBinaire Est Binaire
	*/
	public void setIsBinaire (boolean IsBinaire)
	{
		set_Value (COLUMNNAME_IsBinaire, Boolean.valueOf(IsBinaire));
	}

	/** Get Est Binaire.
		@return Est Binaire	  */
	public boolean isBinaire()
	{
		Object oo = get_Value(COLUMNNAME_IsBinaire);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Éliminatoire.
		@param IsEliminatoire Éliminatoire
	*/
	public void setIsEliminatoire (boolean IsEliminatoire)
	{
		set_Value (COLUMNNAME_IsEliminatoire, Boolean.valueOf(IsEliminatoire));
	}

	/** Get Éliminatoire.
		@return Éliminatoire	  */
	public boolean isEliminatoire()
	{
		Object oo = get_Value(COLUMNNAME_IsEliminatoire);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Évalué.
		@param IsEvalue Évalué
	*/
	public void setIsEvalue (boolean IsEvalue)
	{
		set_Value (COLUMNNAME_IsEvalue, Boolean.valueOf(IsEvalue));
	}

	/** Get Évalué.
		@return Évalué	  */
	public boolean isEvalue()
	{
		Object oo = get_Value(COLUMNNAME_IsEvalue);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set En Pourcentage.
		@param IsPourcentage En Pourcentage
	*/
	public void setIsPourcentage (boolean IsPourcentage)
	{
		set_Value (COLUMNNAME_IsPourcentage, Boolean.valueOf(IsPourcentage));
	}

	/** Get En Pourcentage.
		@return En Pourcentage	  */
	public boolean isPourcentage()
	{
		Object oo = get_Value(COLUMNNAME_IsPourcentage);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Est Progressif.
		@param IsProgressif Est Progressif
	*/
	public void setIsProgressif (boolean IsProgressif)
	{
		set_Value (COLUMNNAME_IsProgressif, Boolean.valueOf(IsProgressif));
	}

	/** Get Est Progressif.
		@return Est Progressif	  */
	public boolean isProgressif()
	{
		Object oo = get_Value(COLUMNNAME_IsProgressif);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Est Subjectif.
		@param IsSubjectif Est Subjectif
	*/
	public void setIsSubjectif (boolean IsSubjectif)
	{
		set_Value (COLUMNNAME_IsSubjectif, Boolean.valueOf(IsSubjectif));
	}

	/** Get Est Subjectif.
		@return Est Subjectif	  */
	public boolean isSubjectif()
	{
		Object oo = get_Value(COLUMNNAME_IsSubjectif);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Score Final.
		@param ScoreFinal Score Final
	*/
	public void setScoreFinal (BigDecimal ScoreFinal)
	{
		set_Value (COLUMNNAME_ScoreFinal, ScoreFinal);
	}

	/** Get Score Final.
		@return Score Final	  */
	public BigDecimal getScoreFinal()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreFinal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score Maximum.
		@param ScoreMax Score Maximum
	*/
	public void setScoreMax (BigDecimal ScoreMax)
	{
		set_Value (COLUMNNAME_ScoreMax, ScoreMax);
	}

	/** Get Score Maximum.
		@return Score Maximum	  */
	public BigDecimal getScoreMax()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score Mininum.
		@param ScoreMin Score Mininum
	*/
	public void setScoreMin (BigDecimal ScoreMin)
	{
		set_Value (COLUMNNAME_ScoreMin, ScoreMin);
	}

	/** Get Score Mininum.
		@return Score Mininum	  */
	public BigDecimal getScoreMin()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreMin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score par l&#039;Employé(e).
		@param Score_Employe Score par l&#039;Employé(e)
	*/
	public void setScore_Employe (BigDecimal Score_Employe)
	{
		set_Value (COLUMNNAME_Score_Employe, Score_Employe);
	}

	/** Get Score par l&#039;Employé(e).
		@return Score par l&#039;Employé(e)	  */
	public BigDecimal getScore_Employe()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Score_Employe);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score par N1.
		@param Score_N1 Score par N1
	*/
	public void setScore_N1 (BigDecimal Score_N1)
	{
		set_Value (COLUMNNAME_Score_N1, Score_N1);
	}

	/** Get Score par N1.
		@return Score par N1	  */
	public BigDecimal getScore_N1()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Score_N1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence.
		@param SeqNo Method of ordering records; lowest number comes first
	*/
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Seuil Échec.
		@param SeuilEchec Seuil Échec
	*/
	public void setSeuilEchec (BigDecimal SeuilEchec)
	{
		set_Value (COLUMNNAME_SeuilEchec, SeuilEchec);
	}

	/** Get Seuil Échec.
		@return Seuil Échec	  */
	public BigDecimal getSeuilEchec()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SeuilEchec);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Seuil de Validation.
		@param SeuilValidation Seuil de Validation
	*/
	public void setSeuilValidation (BigDecimal SeuilValidation)
	{
		set_Value (COLUMNNAME_SeuilValidation, SeuilValidation);
	}

	/** Get Seuil de Validation.
		@return Seuil de Validation	  */
	public BigDecimal getSeuilValidation()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SeuilValidation);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valeur Cible.
		@param ValeurCible Valeur Cible
	*/
	public void setValeurCible (BigDecimal ValeurCible)
	{
		set_Value (COLUMNNAME_ValeurCible, ValeurCible);
	}

	/** Get Valeur Cible.
		@return Valeur Cible	  */
	public BigDecimal getValeurCible()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValeurCible);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valeur Max.
		@param ValeurMax Valeur Max
	*/
	public void setValeurMax (BigDecimal ValeurMax)
	{
		set_Value (COLUMNNAME_ValeurMax, ValeurMax);
	}

	/** Get Valeur Max.
		@return Valeur Max	  */
	public BigDecimal getValeurMax()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValeurMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valeur Min.
		@param ValeurMin Valeur Min
	*/
	public void setValeurMin (BigDecimal ValeurMin)
	{
		set_Value (COLUMNNAME_ValeurMin, ValeurMin);
	}

	/** Get Valeur Min.
		@return Valeur Min	  */
	public BigDecimal getValeurMin()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValeurMin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valeur Réalisée.
		@param ValeurRealisee Valeur Réalisée
	*/
	public void setValeurRealisee (BigDecimal ValeurRealisee)
	{
		set_Value (COLUMNNAME_ValeurRealisee, ValeurRealisee);
	}

	/** Get Valeur Réalisée.
		@return Valeur Réalisée	  */
	public BigDecimal getValeurRealisee()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValeurRealisee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}