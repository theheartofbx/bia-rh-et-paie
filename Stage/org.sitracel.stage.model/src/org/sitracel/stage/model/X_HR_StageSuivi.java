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
package org.sitracel.stage.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.sitracel.mission.model.I_HR_Appreciation;

/** Generated Model for HR_StageSuivi
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_StageSuivi")
public class X_HR_StageSuivi extends PO implements I_HR_StageSuivi, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260813L;

    /** Standard Constructor */
    public X_HR_StageSuivi (Properties ctx, int HR_StageSuivi_ID, String trxName)
    {
      super (ctx, HR_StageSuivi_ID, trxName);
      /** if (HR_StageSuivi_ID == 0)
        {
			setHR_StageSuivi_ID (0);
			setHR_Stage_ID (0);
			setIsEvalue (false);
// 'N'
			setIsOk (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageSuivi (Properties ctx, int HR_StageSuivi_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_StageSuivi_ID, trxName, virtualColumns);
      /** if (HR_StageSuivi_ID == 0)
        {
			setHR_StageSuivi_ID (0);
			setHR_Stage_ID (0);
			setIsEvalue (false);
// 'N'
			setIsOk (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageSuivi (Properties ctx, String HR_StageSuivi_UU, String trxName)
    {
      super (ctx, HR_StageSuivi_UU, trxName);
      /** if (HR_StageSuivi_UU == null)
        {
			setHR_StageSuivi_ID (0);
			setHR_Stage_ID (0);
			setIsEvalue (false);
// 'N'
			setIsOk (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageSuivi (Properties ctx, String HR_StageSuivi_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_StageSuivi_UU, trxName, virtualColumns);
      /** if (HR_StageSuivi_UU == null)
        {
			setHR_StageSuivi_ID (0);
			setHR_Stage_ID (0);
			setIsEvalue (false);
// 'N'
			setIsOk (false);
        } */
    }

    /** Load Constructor */
    public X_HR_StageSuivi (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_StageSuivi[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Commentaire.
		@param Commentaire Commentaire
	*/
	public void setCommentaire (String Commentaire)
	{
		set_Value (COLUMNNAME_Commentaire, Commentaire);
	}

	/** Get Commentaire.
		@return Commentaire	  */
	public String getCommentaire()
	{
		return (String)get_Value(COLUMNNAME_Commentaire);
	}

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
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

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
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

	public I_HR_StageEtatObjectif getHR_StageEtatObjectif() throws RuntimeException
	{
		return (I_HR_StageEtatObjectif)MTable.get(getCtx(), I_HR_StageEtatObjectif.Table_ID)
			.getPO(getHR_StageEtatObjectif_ID(), get_TrxName());
	}

	/** Set État Objectif Stage.
		@param HR_StageEtatObjectif_ID État Objectif Stage
	*/
	public void setHR_StageEtatObjectif_ID (int HR_StageEtatObjectif_ID)
	{
		if (HR_StageEtatObjectif_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_StageEtatObjectif_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_StageEtatObjectif_ID, Integer.valueOf(HR_StageEtatObjectif_ID));
	}

	/** Get État Objectif Stage.
		@return État Objectif Stage	  */
	public int getHR_StageEtatObjectif_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_StageEtatObjectif_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_StageObjectif getHR_StageObjectif() throws RuntimeException
	{
		return (I_HR_StageObjectif)MTable.get(getCtx(), I_HR_StageObjectif.Table_ID)
			.getPO(getHR_StageObjectif_ID(), get_TrxName());
	}

	/** Set Objectif Stage.
		@param HR_StageObjectif_ID Objectif Stage
	*/
	public void setHR_StageObjectif_ID (int HR_StageObjectif_ID)
	{
		if (HR_StageObjectif_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_StageObjectif_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_StageObjectif_ID, Integer.valueOf(HR_StageObjectif_ID));
	}

	/** Get Objectif Stage.
		@return Objectif Stage	  */
	public int getHR_StageObjectif_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_StageObjectif_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_StageProgrammeLigne getHR_StageProgrammeLigne() throws RuntimeException
	{
		return (I_HR_StageProgrammeLigne)MTable.get(getCtx(), I_HR_StageProgrammeLigne.Table_ID)
			.getPO(getHR_StageProgrammeLigne_ID(), get_TrxName());
	}

	/** Set Détail Programme Stage.
		@param HR_StageProgrammeLigne_ID Détail Programme Stage
	*/
	public void setHR_StageProgrammeLigne_ID (int HR_StageProgrammeLigne_ID)
	{
		if (HR_StageProgrammeLigne_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_StageProgrammeLigne_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_StageProgrammeLigne_ID, Integer.valueOf(HR_StageProgrammeLigne_ID));
	}

	/** Get Détail Programme Stage.
		@return Détail Programme Stage	  */
	public int getHR_StageProgrammeLigne_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_StageProgrammeLigne_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Suivi Stage.
		@param HR_StageSuivi_ID Suivi Stage
	*/
	public void setHR_StageSuivi_ID (int HR_StageSuivi_ID)
	{
		if (HR_StageSuivi_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_StageSuivi_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_StageSuivi_ID, Integer.valueOf(HR_StageSuivi_ID));
	}

	/** Get Suivi Stage.
		@return Suivi Stage	  */
	public int getHR_StageSuivi_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_StageSuivi_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_StageSuivi_UU.
		@param HR_StageSuivi_UU HR_StageSuivi_UU
	*/
	public void setHR_StageSuivi_UU (String HR_StageSuivi_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_StageSuivi_UU, HR_StageSuivi_UU);
	}

	/** Get HR_StageSuivi_UU.
		@return HR_StageSuivi_UU	  */
	public String getHR_StageSuivi_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_StageSuivi_UU);
	}

	public I_HR_Stage getHR_Stage() throws RuntimeException
	{
		return (I_HR_Stage)MTable.get(getCtx(), I_HR_Stage.Table_ID)
			.getPO(getHR_Stage_ID(), get_TrxName());
	}

	/** Set Stage.
		@param HR_Stage_ID Stage
	*/
	public void setHR_Stage_ID (int HR_Stage_ID)
	{
		if (HR_Stage_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Stage_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Stage_ID, Integer.valueOf(HR_Stage_ID));
	}

	/** Get Stage.
		@return Stage	  */
	public int getHR_Stage_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Stage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IsEvalue.
		@param IsEvalue IsEvalue
	*/
	public void setIsEvalue (boolean IsEvalue)
	{
		set_Value (COLUMNNAME_IsEvalue, Boolean.valueOf(IsEvalue));
	}

	/** Get IsEvalue.
		@return IsEvalue	  */
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

	/** Set Ok.
		@param IsOk Ok
	*/
	public void setIsOk (boolean IsOk)
	{
		set_Value (COLUMNNAME_IsOk, Boolean.valueOf(IsOk));
	}

	/** Get Ok.
		@return Ok	  */
	public boolean isOk()
	{
		Object oo = get_Value(COLUMNNAME_IsOk);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lieu.
		@param Lieu Lieu
	*/
	public void setLieu (String Lieu)
	{
		set_Value (COLUMNNAME_Lieu, Lieu);
	}

	/** Get Lieu.
		@return Lieu	  */
	public String getLieu()
	{
		return (String)get_Value(COLUMNNAME_Lieu);
	}

	/** Set Ponderation.
		@param Ponderation Ponderation
	*/
	public void setPonderation (int Ponderation)
	{
		set_Value (COLUMNNAME_Ponderation, Integer.valueOf(Ponderation));
	}

	/** Get Ponderation.
		@return Ponderation	  */
	public int getPonderation()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ponderation);
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

	public org.compiere.model.I_C_BPartner getSuperviseur() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getSuperviseur_ID(), get_TrxName());
	}

	/** Set Superviseur.
		@param Superviseur_ID Superviseur
	*/
	public void setSuperviseur_ID (int Superviseur_ID)
	{
		if (Superviseur_ID < 1)
			set_Value (COLUMNNAME_Superviseur_ID, null);
		else
			set_Value (COLUMNNAME_Superviseur_ID, Integer.valueOf(Superviseur_ID));
	}

	/** Get Superviseur.
		@return Superviseur	  */
	public int getSuperviseur_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Superviseur_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}