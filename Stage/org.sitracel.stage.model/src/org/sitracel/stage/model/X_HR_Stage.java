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
import org.sitracel.conge.model.I_HR_Autorisation_Conge;
import org.sitracel.contrat.model.I_HR_Contrat;
import org.sitracel.mission.model.I_HR_Appreciation;

/** Generated Model for HR_Stage
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Stage")
public class X_HR_Stage extends PO implements I_HR_Stage, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260813L;

    /** Standard Constructor */
    public X_HR_Stage (Properties ctx, int HR_Stage_ID, String trxName)
    {
      super (ctx, HR_Stage_ID, trxName);
      /** if (HR_Stage_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Stage_ID (0);
			setIsRejetee (false);
			setIsValidee (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_Stage (Properties ctx, int HR_Stage_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Stage_ID, trxName, virtualColumns);
      /** if (HR_Stage_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Stage_ID (0);
			setIsRejetee (false);
			setIsValidee (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_Stage (Properties ctx, String HR_Stage_UU, String trxName)
    {
      super (ctx, HR_Stage_UU, trxName);
      /** if (HR_Stage_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Stage_ID (0);
			setIsRejetee (false);
			setIsValidee (false);
        } */
    }

    /** Standard Constructor */
    public X_HR_Stage (Properties ctx, String HR_Stage_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Stage_UU, trxName, virtualColumns);
      /** if (HR_Stage_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Stage_ID (0);
			setIsRejetee (false);
			setIsValidee (false);
        } */
    }

    /** Load Constructor */
    public X_HR_Stage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Stage[")
        .append(get_ID()).append("]");
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

	/** Set Commentaire_Global.
		@param Commentaire_Global Commentaire_Global
	*/
	public void setCommentaire_Global (String Commentaire_Global)
	{
		set_Value (COLUMNNAME_Commentaire_Global, Commentaire_Global);
	}

	/** Get Commentaire_Global.
		@return Commentaire_Global	  */
	public String getCommentaire_Global()
	{
		return (String)get_Value(COLUMNNAME_Commentaire_Global);
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

	/** Set GenererPlanning.
		@param GenererPlanning GenererPlanning
	*/
	public void setGenererPlanning (String GenererPlanning)
	{
		set_Value (COLUMNNAME_GenererPlanning, GenererPlanning);
	}

	/** Get GenererPlanning.
		@return GenererPlanning	  */
	public String getGenererPlanning()
	{
		return (String)get_Value(COLUMNNAME_GenererPlanning);
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

	public I_HR_Contrat getHR_Contrat() throws RuntimeException
	{
		return (I_HR_Contrat)MTable.get(getCtx(), I_HR_Contrat.Table_ID)
			.getPO(getHR_Contrat_ID(), get_TrxName());
	}

	/** Set Contrat.
		@param HR_Contrat_ID Contrat
	*/
	public void setHR_Contrat_ID (int HR_Contrat_ID)
	{
		if (HR_Contrat_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Contrat_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Contrat_ID, Integer.valueOf(HR_Contrat_ID));
	}

	/** Get Contrat.
		@return Contrat	  */
	public int getHR_Contrat_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contrat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_StageProgramme getHR_StageProgramme() throws RuntimeException
	{
		return (I_HR_StageProgramme)MTable.get(getCtx(), I_HR_StageProgramme.Table_ID)
			.getPO(getHR_StageProgramme_ID(), get_TrxName());
	}

	/** Set Programme Stage.
		@param HR_StageProgramme_ID Programme Stage
	*/
	public void setHR_StageProgramme_ID (int HR_StageProgramme_ID)
	{
		if (HR_StageProgramme_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_StageProgramme_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_StageProgramme_ID, Integer.valueOf(HR_StageProgramme_ID));
	}

	/** Get Programme Stage.
		@return Programme Stage	  */
	public int getHR_StageProgramme_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_StageProgramme_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set HR_Stage_UU.
		@param HR_Stage_UU HR_Stage_UU
	*/
	public void setHR_Stage_UU (String HR_Stage_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_Stage_UU, HR_Stage_UU);
	}

	/** Get HR_Stage_UU.
		@return HR_Stage_UU	  */
	public String getHR_Stage_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Stage_UU);
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

	/** Set Objectifs Définis.
		@param NombreDefinis Objectifs Définis
	*/
	public void setNombreDefinis (int NombreDefinis)
	{
		throw new IllegalArgumentException ("NombreDefinis is virtual column");	}

	/** Get Objectifs Définis.
		@return Objectifs Définis	  */
	public int getNombreDefinis()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreDefinis);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Objectifs Évalués.
		@param NombreEvalues Objectifs Évalués
	*/
	public void setNombreEvalues (int NombreEvalues)
	{
		throw new IllegalArgumentException ("NombreEvalues is virtual column");	}

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
		throw new IllegalArgumentException ("NombreObjectifs is virtual column");	}

	/** Get Nombre d&#039;Objectifs.
		@return Nombre d&#039;Objectifs	  */
	public int getNombreObjectifs()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NombreObjectifs);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Avancement (%).
		@param PourcentageAvancement Avancement (%)
	*/
	public void setPourcentageAvancement (int PourcentageAvancement)
	{
		throw new IllegalArgumentException ("PourcentageAvancement is virtual column");	}

	/** Get Avancement (%).
		@return Avancement (%)	  */
	public int getPourcentageAvancement()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PourcentageAvancement);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Score Max Total.
		@param ScoreMax_Total Score Max Total
	*/
	public void setScoreMax_Total (BigDecimal ScoreMax_Total)
	{
		set_Value (COLUMNNAME_ScoreMax_Total, ScoreMax_Total);
	}

	/** Get Score Max Total.
		@return Score Max Total	  */
	public BigDecimal getScoreMax_Total()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreMax_Total);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score Total.
		@param Score_Total Score Total
	*/
	public void setScore_Total (BigDecimal Score_Total)
	{
		set_Value (COLUMNNAME_Score_Total, Score_Total);
	}

	/** Get Score Total.
		@return Score Total	  */
	public BigDecimal getScore_Total()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Score_Total);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Theme.
		@param Theme Theme name
	*/
	public void setTheme (String Theme)
	{
		set_Value (COLUMNNAME_Theme, Theme);
	}

	/** Get Theme.
		@return Theme name
	  */
	public String getTheme()
	{
		return (String)get_Value(COLUMNNAME_Theme);
	}

	public I_HR_Autorisation_Conge getTuteur() throws RuntimeException
	{
		return (I_HR_Autorisation_Conge)MTable.get(getCtx(), I_HR_Autorisation_Conge.Table_ID)
			.getPO(getTuteur_ID(), get_TrxName());
	}

	/** Set Tuteur_ID.
		@param Tuteur_ID Tuteur_ID
	*/
	public void setTuteur_ID (int Tuteur_ID)
	{
		if (Tuteur_ID < 1)
			set_Value (COLUMNNAME_Tuteur_ID, null);
		else
			set_Value (COLUMNNAME_Tuteur_ID, Integer.valueOf(Tuteur_ID));
	}

	/** Get Tuteur_ID.
		@return Tuteur_ID	  */
	public int getTuteur_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Tuteur_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valider .
		@param Valider Valider 
	*/
	public void setValider (String Valider)
	{
		set_Value (COLUMNNAME_Valider, Valider);
	}

	/** Get Valider .
		@return Valider 
	  */
	public String getValider()
	{
		return (String)get_Value(COLUMNNAME_Valider);
	}
}