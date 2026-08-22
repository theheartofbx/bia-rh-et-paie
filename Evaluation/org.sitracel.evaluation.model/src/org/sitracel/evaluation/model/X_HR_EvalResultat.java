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

/** Generated Model for HR_EvalResultat
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_EvalResultat")
public class X_HR_EvalResultat extends PO implements I_HR_EvalResultat, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260822L;

    /** Standard Constructor */
    public X_HR_EvalResultat (Properties ctx, int HR_EvalResultat_ID, String trxName)
    {
      super (ctx, HR_EvalResultat_ID, trxName);
      /** if (HR_EvalResultat_ID == 0)
        {
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalResultat_ID (0);
			setHR_Eval_ID (0);
			setIsCalcule (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalResultat (Properties ctx, int HR_EvalResultat_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalResultat_ID, trxName, virtualColumns);
      /** if (HR_EvalResultat_ID == 0)
        {
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalResultat_ID (0);
			setHR_Eval_ID (0);
			setIsCalcule (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalResultat (Properties ctx, String HR_EvalResultat_UU, String trxName)
    {
      super (ctx, HR_EvalResultat_UU, trxName);
      /** if (HR_EvalResultat_UU == null)
        {
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalResultat_ID (0);
			setHR_Eval_ID (0);
			setIsCalcule (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalResultat (Properties ctx, String HR_EvalResultat_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalResultat_UU, trxName, virtualColumns);
      /** if (HR_EvalResultat_UU == null)
        {
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalResultat_ID (0);
			setHR_Eval_ID (0);
			setIsCalcule (false);
        } */
    }

    /** Load Constructor */
    public X_HR_EvalResultat (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EvalResultat[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Formule.
		@param Formule Formule
	*/
	public void setFormule (String Formule)
	{
		set_Value (COLUMNNAME_Formule, Formule);
	}

	/** Get Formule.
		@return Formule	  */
	public String getFormule()
	{
		return (String)get_Value(COLUMNNAME_Formule);
	}

	public I_HR_EvalGrilleFormule getHR_EvalGrilleFormule() throws RuntimeException
	{
		return (I_HR_EvalGrilleFormule)MTable.get(getCtx(), I_HR_EvalGrilleFormule.Table_ID)
			.getPO(getHR_EvalGrilleFormule_ID(), get_TrxName());
	}

	/** Set Formule Grille Évaluation.
		@param HR_EvalGrilleFormule_ID Formule Grille Évaluation
	*/
	public void setHR_EvalGrilleFormule_ID (int HR_EvalGrilleFormule_ID)
	{
		if (HR_EvalGrilleFormule_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleFormule_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleFormule_ID, Integer.valueOf(HR_EvalGrilleFormule_ID));
	}

	/** Get Formule Grille Évaluation.
		@return Formule Grille Évaluation	  */
	public int getHR_EvalGrilleFormule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalGrilleFormule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Résultat Formule Évaluation.
		@param HR_EvalResultat_ID Résultat Formule Évaluation
	*/
	public void setHR_EvalResultat_ID (int HR_EvalResultat_ID)
	{
		if (HR_EvalResultat_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalResultat_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalResultat_ID, Integer.valueOf(HR_EvalResultat_ID));
	}

	/** Get Résultat Formule Évaluation.
		@return Résultat Formule Évaluation	  */
	public int getHR_EvalResultat_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalResultat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_EvalResultat_UU.
		@param HR_EvalResultat_UU HR_EvalResultat_UU
	*/
	public void setHR_EvalResultat_UU (String HR_EvalResultat_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_EvalResultat_UU, HR_EvalResultat_UU);
	}

	/** Get HR_EvalResultat_UU.
		@return HR_EvalResultat_UU	  */
	public String getHR_EvalResultat_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EvalResultat_UU);
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

	/** Set IsCalcule.
		@param IsCalcule IsCalcule
	*/
	public void setIsCalcule (boolean IsCalcule)
	{
		set_Value (COLUMNNAME_IsCalcule, Boolean.valueOf(IsCalcule));
	}

	/** Get IsCalcule.
		@return IsCalcule	  */
	public boolean isCalcule()
	{
		Object oo = get_Value(COLUMNNAME_IsCalcule);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Principale.
		@param IsPrincipale Principale
	*/
	public void setIsPrincipale (boolean IsPrincipale)
	{
		set_Value (COLUMNNAME_IsPrincipale, Boolean.valueOf(IsPrincipale));
	}

	/** Get Principale.
		@return Principale	  */
	public boolean isPrincipale()
	{
		Object oo = get_Value(COLUMNNAME_IsPrincipale);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Erreur.
		@param MessageErreur Message d&#039;Erreur
	*/
	public void setMessageErreur (String MessageErreur)
	{
		set_Value (COLUMNNAME_MessageErreur, MessageErreur);
	}

	/** Get Message d&#039;Erreur.
		@return Message d&#039;Erreur	  */
	public String getMessageErreur()
	{
		return (String)get_Value(COLUMNNAME_MessageErreur);
	}

	/** Set Resultat.
		@param Resultat Resultat
	*/
	public void setResultat (BigDecimal Resultat)
	{
		set_Value (COLUMNNAME_Resultat, Resultat);
	}

	/** Get Resultat.
		@return Resultat	  */
	public BigDecimal getResultat()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Resultat);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score.
		@param Score Score
	*/
	public void setScore (BigDecimal Score)
	{
		set_ValueNoCheck (COLUMNNAME_Score, Score);
	}

	/** Get Score.
		@return Score	  */
	public BigDecimal getScore()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Score);
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
}