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
package org.sitracel.recrutement.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for HR_CandidatEvaluation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_CandidatEvaluation")
public class X_HR_CandidatEvaluation extends PO implements I_HR_CandidatEvaluation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241210L;

    /** Standard Constructor */
    public X_HR_CandidatEvaluation (Properties ctx, int HR_CandidatEvaluation_ID, String trxName)
    {
      super (ctx, HR_CandidatEvaluation_ID, trxName);
      /** if (HR_CandidatEvaluation_ID == 0)
        {
			setHR_CandidatEvaluation_ID (0);
			setHR_Candidature_ID (0);
			setHR_Competences_ID (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_CandidatEvaluation (Properties ctx, int HR_CandidatEvaluation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_CandidatEvaluation_ID, trxName, virtualColumns);
      /** if (HR_CandidatEvaluation_ID == 0)
        {
			setHR_CandidatEvaluation_ID (0);
			setHR_Candidature_ID (0);
			setHR_Competences_ID (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_CandidatEvaluation (Properties ctx, String HR_CandidatEvaluation_UU, String trxName)
    {
      super (ctx, HR_CandidatEvaluation_UU, trxName);
      /** if (HR_CandidatEvaluation_UU == null)
        {
			setHR_CandidatEvaluation_ID (0);
			setHR_Candidature_ID (0);
			setHR_Competences_ID (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_CandidatEvaluation (Properties ctx, String HR_CandidatEvaluation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_CandidatEvaluation_UU, trxName, virtualColumns);
      /** if (HR_CandidatEvaluation_UU == null)
        {
			setHR_CandidatEvaluation_ID (0);
			setHR_Candidature_ID (0);
			setHR_Competences_ID (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_CandidatEvaluation (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    @Override
	protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    @Override
	protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    @Override
	public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_CandidatEvaluation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description Optional short description of the record
	*/
	@Override
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	@Override
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Evaluation des Candidats.
		@param HR_CandidatEvaluation_ID Evaluation des Candidats
	*/
	@Override
	public void setHR_CandidatEvaluation_ID (int HR_CandidatEvaluation_ID)
	{
		if (HR_CandidatEvaluation_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_CandidatEvaluation_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_CandidatEvaluation_ID, Integer.valueOf(HR_CandidatEvaluation_ID));
		}
	}

	/** Get Evaluation des Candidats.
		@return Evaluation des Candidats	  */
	@Override
	public int getHR_CandidatEvaluation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_CandidatEvaluation_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_CandidatEvaluation_UU.
		@param HR_CandidatEvaluation_UU HR_CandidatEvaluation_UU
	*/
	@Override
	public void setHR_CandidatEvaluation_UU (String HR_CandidatEvaluation_UU)
	{
		set_Value (COLUMNNAME_HR_CandidatEvaluation_UU, HR_CandidatEvaluation_UU);
	}

	/** Get HR_CandidatEvaluation_UU.
		@return HR_CandidatEvaluation_UU	  */
	@Override
	public String getHR_CandidatEvaluation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_CandidatEvaluation_UU);
	}

	@Override
	public I_HR_Candidature getHR_Candidature() throws RuntimeException
	{
		return (I_HR_Candidature)MTable.get(getCtx(), I_HR_Candidature.Table_ID)
			.getPO(getHR_Candidature_ID(), get_TrxName());
	}

	/** Set Candidature.
		@param HR_Candidature_ID Candidature
	*/
	@Override
	public void setHR_Candidature_ID (int HR_Candidature_ID)
	{
		if (HR_Candidature_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Candidature_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Candidature_ID, Integer.valueOf(HR_Candidature_ID));
		}
	}

	/** Get Candidature.
		@return Candidature	  */
	@Override
	public int getHR_Candidature_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Candidature_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Competences getHR_Competences() throws RuntimeException
	{
		return (I_HR_Competences)MTable.get(getCtx(), I_HR_Competences.Table_ID)
			.getPO(getHR_Competences_ID(), get_TrxName());
	}

	/** Set Competences.
		@param HR_Competences_ID Competences
	*/
	@Override
	public void setHR_Competences_ID (int HR_Competences_ID)
	{
		if (HR_Competences_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, Integer.valueOf(HR_Competences_ID));
		}
	}

	/** Get Competences.
		@return Competences	  */
	@Override
	public int getHR_Competences_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Competences_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Évaluation de la Compétence Terminée .
		@param IsCompetenceEvalue Évaluation de la Compétence Terminée
	*/
	@Override
	public void setIsCompetenceEvalue (boolean IsCompetenceEvalue)
	{
		set_Value (COLUMNNAME_IsCompetenceEvalue, Boolean.valueOf(IsCompetenceEvalue));
	}

	/** Get Évaluation de la Compétence Terminée .
		@return Évaluation de la Compétence Terminée 	  */
	@Override
	public boolean isCompetenceEvalue()
	{
		Object oo = get_Value(COLUMNNAME_IsCompetenceEvalue);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Ponderation.
		@param Ponderation Ponderation
	*/
	@Override
	public void setPonderation (int Ponderation)
	{
		set_Value (COLUMNNAME_Ponderation, Integer.valueOf(Ponderation));
	}

	/** Get Ponderation.
		@return Ponderation	  */
	@Override
	public int getPonderation()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ponderation);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Score.
		@param Score Score
	*/
	@Override
	public void setScore (BigDecimal Score)
	{
		set_Value (COLUMNNAME_Score, Score);
	}

	/** Get Score.
		@return Score	  */
	@Override
	public BigDecimal getScore()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Score);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Score Maximum.
		@param ScoreMax Score Maximum
	*/
	@Override
	public void setScoreMax (BigDecimal ScoreMax)
	{
		set_Value (COLUMNNAME_ScoreMax, ScoreMax);
	}

	/** Get Score Maximum.
		@return Score Maximum	  */
	@Override
	public BigDecimal getScoreMax()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreMax);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}
}