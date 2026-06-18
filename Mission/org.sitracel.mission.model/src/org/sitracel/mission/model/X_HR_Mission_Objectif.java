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
package org.sitracel.mission.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_Mission_Objectif
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mission_Objectif")
public class X_HR_Mission_Objectif extends PO implements I_HR_Mission_Objectif, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251214L;

    /** Standard Constructor */
    public X_HR_Mission_Objectif (Properties ctx, int HR_Mission_Objectif_ID, String trxName)
    {
      super (ctx, HR_Mission_Objectif_ID, trxName);
      /** if (HR_Mission_Objectif_ID == 0)
        {
			setHR_Mission_Objectif_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Objectif (Properties ctx, int HR_Mission_Objectif_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Objectif_ID, trxName, virtualColumns);
      /** if (HR_Mission_Objectif_ID == 0)
        {
			setHR_Mission_Objectif_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Objectif (Properties ctx, String HR_Mission_Objectif_UU, String trxName)
    {
      super (ctx, HR_Mission_Objectif_UU, trxName);
      /** if (HR_Mission_Objectif_UU == null)
        {
			setHR_Mission_Objectif_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Objectif (Properties ctx, String HR_Mission_Objectif_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Objectif_UU, trxName, virtualColumns);
      /** if (HR_Mission_Objectif_UU == null)
        {
			setHR_Mission_Objectif_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Mission_Objectif (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Mission_Objectif[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Commentaire Evaluateur.
		@param CommentaireEvaluateur Commentaire Evaluateur
	*/
	public void setCommentaireEvaluateur (String CommentaireEvaluateur)
	{
		set_ValueNoCheck (COLUMNNAME_CommentaireEvaluateur, CommentaireEvaluateur);
	}

	/** Get Commentaire Evaluateur.
		@return Commentaire Evaluateur	  */
	public String getCommentaireEvaluateur()
	{
		return (String)get_Value(COLUMNNAME_CommentaireEvaluateur);
	}

	/** Set Date de l&#039;Évaluation.
		@param Date_Evaluation Date de l&#039;Évaluation
	*/
	public void setDate_Evaluation (Timestamp Date_Evaluation)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Evaluation, Date_Evaluation);
	}

	/** Get Date de l&#039;Évaluation.
		@return Date de l&#039;Évaluation	  */
	public Timestamp getDate_Evaluation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Evaluation);
	}

	/** Set Matricule de l&#039;Évaluateur.
		@param Evalue_Par_Matricule Matricule de l&#039;Évaluateur
	*/
	public void setEvalue_Par_Matricule (String Evalue_Par_Matricule)
	{
		set_Value (COLUMNNAME_Evalue_Par_Matricule, Evalue_Par_Matricule);
	}

	/** Get Matricule de l&#039;Évaluateur.
		@return Matricule de l&#039;Évaluateur	  */
	public String getEvalue_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Evalue_Par_Matricule);
	}

	public org.compiere.model.I_C_BPartner getEvalue_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEvalue_Par_Nom_ID(), get_TrxName());
	}

	/** Set Nom de l&#039;Évaluateur.
		@param Evalue_Par_Nom_ID Nom de l&#039;Évaluateur
	*/
	public void setEvalue_Par_Nom_ID (int Evalue_Par_Nom_ID)
	{
		if (Evalue_Par_Nom_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Evalue_Par_Nom_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Evalue_Par_Nom_ID, Integer.valueOf(Evalue_Par_Nom_ID));
	}

	/** Get Nom de l&#039;Évaluateur.
		@return Nom de l&#039;Évaluateur	  */
	public int getEvalue_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Evalue_Par_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getEvalue_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getEvalue_Par_Poste_ID(), get_TrxName());
	}

	/** Set Poste de l&#039;Évaluateur.
		@param Evalue_Par_Poste_ID Poste de l&#039;Évaluateur
	*/
	public void setEvalue_Par_Poste_ID (int Evalue_Par_Poste_ID)
	{
		if (Evalue_Par_Poste_ID < 1)
			set_Value (COLUMNNAME_Evalue_Par_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Evalue_Par_Poste_ID, Integer.valueOf(Evalue_Par_Poste_ID));
	}

	/** Get Poste de l&#039;Évaluateur.
		@return Poste de l&#039;Évaluateur	  */
	public int getEvalue_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Evalue_Par_Poste_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Appreciation getHR_Appreciation() throws RuntimeException
	{
		return (I_HR_Appreciation)MTable.get(getCtx(), I_HR_Appreciation.Table_ID)
			.getPO(getHR_Appreciation_ID(), get_TrxName());
	}

	/** Set Appréciation.
		@param HR_Appreciation_ID Appréciation
	*/
	public void setHR_Appreciation_ID (int HR_Appreciation_ID)
	{
		if (HR_Appreciation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Appreciation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Appreciation_ID, Integer.valueOf(HR_Appreciation_ID));
	}

	/** Get Appréciation.
		@return Appréciation	  */
	public int getHR_Appreciation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Appreciation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_EtatObjectifMission getHR_EtatObjectifMission() throws RuntimeException
	{
		return (I_HR_EtatObjectifMission)MTable.get(getCtx(), I_HR_EtatObjectifMission.Table_ID)
			.getPO(getHR_EtatObjectifMission_ID(), get_TrxName());
	}

	/** Set État de l&#039;Objectif.
		@param HR_EtatObjectifMission_ID État de l&#039;Objectif
	*/
	public void setHR_EtatObjectifMission_ID (int HR_EtatObjectifMission_ID)
	{
		if (HR_EtatObjectifMission_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EtatObjectifMission_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EtatObjectifMission_ID, Integer.valueOf(HR_EtatObjectifMission_ID));
	}

	/** Get État de l&#039;Objectif.
		@return État de l&#039;Objectif	  */
	public int getHR_EtatObjectifMission_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EtatObjectifMission_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Mission getHR_Mission() throws RuntimeException
	{
		return (I_HR_Mission)MTable.get(getCtx(), I_HR_Mission.Table_ID)
			.getPO(getHR_Mission_ID(), get_TrxName());
	}

	/** Set Mission.
		@param HR_Mission_ID Mission
	*/
	public void setHR_Mission_ID (int HR_Mission_ID)
	{
		if (HR_Mission_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_ID, Integer.valueOf(HR_Mission_ID));
	}

	/** Get Mission.
		@return Mission	  */
	public int getHR_Mission_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Objectifs de Mission.
		@param HR_Mission_Objectif_ID Objectifs de Mission
	*/
	public void setHR_Mission_Objectif_ID (int HR_Mission_Objectif_ID)
	{
		if (HR_Mission_Objectif_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Objectif_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Objectif_ID, Integer.valueOf(HR_Mission_Objectif_ID));
	}

	/** Get Objectifs de Mission.
		@return Objectifs de Mission	  */
	public int getHR_Mission_Objectif_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_Objectif_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Mission_Objectif_UU.
		@param HR_Mission_Objectif_UU HR_Mission_Objectif_UU
	*/
	public void setHR_Mission_Objectif_UU (String HR_Mission_Objectif_UU)
	{
		set_Value (COLUMNNAME_HR_Mission_Objectif_UU, HR_Mission_Objectif_UU);
	}

	/** Get HR_Mission_Objectif_UU.
		@return HR_Mission_Objectif_UU	  */
	public String getHR_Mission_Objectif_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mission_Objectif_UU);
	}

	public I_HR_TypeObjectif getHR_TypeObjectif() throws RuntimeException
	{
		return (I_HR_TypeObjectif)MTable.get(getCtx(), I_HR_TypeObjectif.Table_ID)
			.getPO(getHR_TypeObjectif_ID(), get_TrxName());
	}

	/** Set Type d&#039;Objectif.
		@param HR_TypeObjectif_ID Type d&#039;Objectif
	*/
	public void setHR_TypeObjectif_ID (int HR_TypeObjectif_ID)
	{
		if (HR_TypeObjectif_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeObjectif_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeObjectif_ID, Integer.valueOf(HR_TypeObjectif_ID));
	}

	/** Get Type d&#039;Objectif.
		@return Type d&#039;Objectif	  */
	public int getHR_TypeObjectif_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeObjectif_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}