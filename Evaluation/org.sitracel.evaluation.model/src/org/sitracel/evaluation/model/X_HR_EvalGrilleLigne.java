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

/** Generated Model for HR_EvalGrilleLigne
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_EvalGrilleLigne")
public class X_HR_EvalGrilleLigne extends PO implements I_HR_EvalGrilleLigne, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260819L;

    /** Standard Constructor */
    public X_HR_EvalGrilleLigne (Properties ctx, int HR_EvalGrilleLigne_ID, String trxName)
    {
      super (ctx, HR_EvalGrilleLigne_ID, trxName);
      /** if (HR_EvalGrilleLigne_ID == 0)
        {
			setAcronyme (null);
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalObjectif_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalGrilleLigne (Properties ctx, int HR_EvalGrilleLigne_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalGrilleLigne_ID, trxName, virtualColumns);
      /** if (HR_EvalGrilleLigne_ID == 0)
        {
			setAcronyme (null);
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalObjectif_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalGrilleLigne (Properties ctx, String HR_EvalGrilleLigne_UU, String trxName)
    {
      super (ctx, HR_EvalGrilleLigne_UU, trxName);
      /** if (HR_EvalGrilleLigne_UU == null)
        {
			setAcronyme (null);
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalObjectif_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalGrilleLigne (Properties ctx, String HR_EvalGrilleLigne_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalGrilleLigne_UU, trxName, virtualColumns);
      /** if (HR_EvalGrilleLigne_UU == null)
        {
			setAcronyme (null);
			setHR_EvalGrilleLigne_ID (0);
			setHR_EvalGrille_ID (0);
			setHR_EvalObjectif_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_EvalGrilleLigne (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EvalGrilleLigne[")
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

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
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

	/** Set HR_EvalGrilleLigne_UU.
		@param HR_EvalGrilleLigne_UU HR_EvalGrilleLigne_UU
	*/
	public void setHR_EvalGrilleLigne_UU (String HR_EvalGrilleLigne_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleLigne_UU, HR_EvalGrilleLigne_UU);
	}

	/** Get HR_EvalGrilleLigne_UU.
		@return HR_EvalGrilleLigne_UU	  */
	public String getHR_EvalGrilleLigne_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EvalGrilleLigne_UU);
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

	public I_HR_EvalObjectif getHR_EvalObjectif() throws RuntimeException
	{
		return (I_HR_EvalObjectif)MTable.get(getCtx(), I_HR_EvalObjectif.Table_ID)
			.getPO(getHR_EvalObjectif_ID(), get_TrxName());
	}

	/** Set Objectif d&#039;Évaluation.
		@param HR_EvalObjectif_ID Objectif d&#039;Évaluation
	*/
	public void setHR_EvalObjectif_ID (int HR_EvalObjectif_ID)
	{
		if (HR_EvalObjectif_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalObjectif_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalObjectif_ID, Integer.valueOf(HR_EvalObjectif_ID));
	}

	/** Get Objectif d&#039;Évaluation.
		@return Objectif d&#039;Évaluation	  */
	public int getHR_EvalObjectif_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalObjectif_ID);
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

	/** Set Score Test.
		@param ScoreTest Score Test
	*/
	public void setScoreTest (BigDecimal ScoreTest)
	{
		set_Value (COLUMNNAME_ScoreTest, ScoreTest);
	}

	/** Get Score Test.
		@return Score Test	  */
	public BigDecimal getScoreTest()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreTest);
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
}