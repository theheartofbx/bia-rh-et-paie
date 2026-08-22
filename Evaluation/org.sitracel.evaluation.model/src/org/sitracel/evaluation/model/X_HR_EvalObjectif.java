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

/** Generated Model for HR_EvalObjectif
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_EvalObjectif")
public class X_HR_EvalObjectif extends PO implements I_HR_EvalObjectif, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260822L;

    /** Standard Constructor */
    public X_HR_EvalObjectif (Properties ctx, int HR_EvalObjectif_ID, String trxName)
    {
      super (ctx, HR_EvalObjectif_ID, trxName);
      /** if (HR_EvalObjectif_ID == 0)
        {
			setHR_EvalObjectif_ID (0);
			setIsBinaire (false);
			setIsPourcentage (false);
			setIsProgressif (false);
			setIsSubjectif (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalObjectif (Properties ctx, int HR_EvalObjectif_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalObjectif_ID, trxName, virtualColumns);
      /** if (HR_EvalObjectif_ID == 0)
        {
			setHR_EvalObjectif_ID (0);
			setIsBinaire (false);
			setIsPourcentage (false);
			setIsProgressif (false);
			setIsSubjectif (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalObjectif (Properties ctx, String HR_EvalObjectif_UU, String trxName)
    {
      super (ctx, HR_EvalObjectif_UU, trxName);
      /** if (HR_EvalObjectif_UU == null)
        {
			setHR_EvalObjectif_ID (0);
			setIsBinaire (false);
			setIsPourcentage (false);
			setIsProgressif (false);
			setIsSubjectif (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalObjectif (Properties ctx, String HR_EvalObjectif_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalObjectif_UU, trxName, virtualColumns);
      /** if (HR_EvalObjectif_UU == null)
        {
			setHR_EvalObjectif_ID (0);
			setIsBinaire (false);
			setIsPourcentage (false);
			setIsProgressif (false);
			setIsSubjectif (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HR_EvalObjectif (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EvalObjectif[")
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

	public I_HR_EvalCategorie getHR_EvalCategorie() throws RuntimeException
	{
		return (I_HR_EvalCategorie)MTable.get(getCtx(), I_HR_EvalCategorie.Table_ID)
			.getPO(getHR_EvalCategorie_ID(), get_TrxName());
	}

	/** Set Catégorie d&#039;Évaluation.
		@param HR_EvalCategorie_ID Catégorie d&#039;Évaluation
	*/
	public void setHR_EvalCategorie_ID (int HR_EvalCategorie_ID)
	{
		if (HR_EvalCategorie_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalCategorie_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalCategorie_ID, Integer.valueOf(HR_EvalCategorie_ID));
	}

	/** Get Catégorie d&#039;Évaluation.
		@return Catégorie d&#039;Évaluation	  */
	public int getHR_EvalCategorie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalCategorie_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set HR_EvalObjectif_UU.
		@param HR_EvalObjectif_UU HR_EvalObjectif_UU
	*/
	public void setHR_EvalObjectif_UU (String HR_EvalObjectif_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_EvalObjectif_UU, HR_EvalObjectif_UU);
	}

	/** Get HR_EvalObjectif_UU.
		@return HR_EvalObjectif_UU	  */
	public String getHR_EvalObjectif_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EvalObjectif_UU);
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

	/** Set Score Max par Défaut.
		@param ScoreMax_Defaut Score Max par Défaut
	*/
	public void setScoreMax_Defaut (BigDecimal ScoreMax_Defaut)
	{
		set_Value (COLUMNNAME_ScoreMax_Defaut, ScoreMax_Defaut);
	}

	/** Get Score Max par Défaut.
		@return Score Max par Défaut	  */
	public BigDecimal getScoreMax_Defaut()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScoreMax_Defaut);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}