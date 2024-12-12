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
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_OffreCritereEvaluation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_OffreCritereEvaluation")
public class X_HR_OffreCritereEvaluation extends PO implements I_HR_OffreCritereEvaluation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241208L;

    /** Standard Constructor */
    public X_HR_OffreCritereEvaluation (Properties ctx, int HR_OffreCritereEvaluation_ID, String trxName)
    {
      super (ctx, HR_OffreCritereEvaluation_ID, trxName);
      /** if (HR_OffreCritereEvaluation_ID == 0)
        {
			setHR_Competences_ID (0);
			setHR_OffreCritereEvaluation_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setPonderation (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreCritereEvaluation (Properties ctx, int HR_OffreCritereEvaluation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreCritereEvaluation_ID, trxName, virtualColumns);
      /** if (HR_OffreCritereEvaluation_ID == 0)
        {
			setHR_Competences_ID (0);
			setHR_OffreCritereEvaluation_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setPonderation (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreCritereEvaluation (Properties ctx, String HR_OffreCritereEvaluation_UU, String trxName)
    {
      super (ctx, HR_OffreCritereEvaluation_UU, trxName);
      /** if (HR_OffreCritereEvaluation_UU == null)
        {
			setHR_Competences_ID (0);
			setHR_OffreCritereEvaluation_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setPonderation (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreCritereEvaluation (Properties ctx, String HR_OffreCritereEvaluation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreCritereEvaluation_UU, trxName, virtualColumns);
      /** if (HR_OffreCritereEvaluation_UU == null)
        {
			setHR_Competences_ID (0);
			setHR_OffreCritereEvaluation_ID (0);
			setHR_OffreTestEvaluation_ID (0);
			setPonderation (0);
			setScoreMax (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_OffreCritereEvaluation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_OffreCritereEvaluation[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	public I_HR_Competences getHR_Competences() throws RuntimeException
	{
		return (I_HR_Competences)MTable.get(getCtx(), I_HR_Competences.Table_ID)
			.getPO(getHR_Competences_ID(), get_TrxName());
	}

	/** Set Competences.
		@param HR_Competences_ID Competences
	*/
	public void setHR_Competences_ID (int HR_Competences_ID)
	{
		if (HR_Competences_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, Integer.valueOf(HR_Competences_ID));
	}

	/** Get Competences.
		@return Competences	  */
	public int getHR_Competences_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Competences_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Critères d&#039;Évaluation.
		@param HR_OffreCritereEvaluation_ID Critères d&#039;Évaluation
	*/
	public void setHR_OffreCritereEvaluation_ID (int HR_OffreCritereEvaluation_ID)
	{
		if (HR_OffreCritereEvaluation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_OffreCritereEvaluation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_OffreCritereEvaluation_ID, Integer.valueOf(HR_OffreCritereEvaluation_ID));
	}

	/** Get Critères d&#039;Évaluation.
		@return Critères d&#039;Évaluation	  */
	public int getHR_OffreCritereEvaluation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreCritereEvaluation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_OffreCritereEvaluation_UU.
		@param HR_OffreCritereEvaluation_UU HR_OffreCritereEvaluation_UU
	*/
	public void setHR_OffreCritereEvaluation_UU (String HR_OffreCritereEvaluation_UU)
	{
		set_Value (COLUMNNAME_HR_OffreCritereEvaluation_UU, HR_OffreCritereEvaluation_UU);
	}

	/** Get HR_OffreCritereEvaluation_UU.
		@return HR_OffreCritereEvaluation_UU	  */
	public String getHR_OffreCritereEvaluation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_OffreCritereEvaluation_UU);
	}

	public I_HR_OffreTestEvaluation getHR_OffreTestEvaluation() throws RuntimeException
	{
		return (I_HR_OffreTestEvaluation)MTable.get(getCtx(), I_HR_OffreTestEvaluation.Table_ID)
			.getPO(getHR_OffreTestEvaluation_ID(), get_TrxName());
	}

	/** Set Test d&#039;Évaluation.
		@param HR_OffreTestEvaluation_ID Test d&#039;Évaluation
	*/
	public void setHR_OffreTestEvaluation_ID (int HR_OffreTestEvaluation_ID)
	{
		if (HR_OffreTestEvaluation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_OffreTestEvaluation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_OffreTestEvaluation_ID, Integer.valueOf(HR_OffreTestEvaluation_ID));
	}

	/** Get Test d&#039;Évaluation.
		@return Test d&#039;Évaluation	  */
	public int getHR_OffreTestEvaluation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreTestEvaluation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getName());
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