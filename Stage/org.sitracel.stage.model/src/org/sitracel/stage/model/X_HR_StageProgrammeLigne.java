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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_StageProgrammeLigne
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_StageProgrammeLigne")
public class X_HR_StageProgrammeLigne extends PO implements I_HR_StageProgrammeLigne, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260813L;

    /** Standard Constructor */
    public X_HR_StageProgrammeLigne (Properties ctx, int HR_StageProgrammeLigne_ID, String trxName)
    {
      super (ctx, HR_StageProgrammeLigne_ID, trxName);
      /** if (HR_StageProgrammeLigne_ID == 0)
        {
			setHR_StageObjectif_ID (0);
			setHR_StageProgrammeLigne_ID (0);
			setHR_StageProgramme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageProgrammeLigne (Properties ctx, int HR_StageProgrammeLigne_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_StageProgrammeLigne_ID, trxName, virtualColumns);
      /** if (HR_StageProgrammeLigne_ID == 0)
        {
			setHR_StageObjectif_ID (0);
			setHR_StageProgrammeLigne_ID (0);
			setHR_StageProgramme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageProgrammeLigne (Properties ctx, String HR_StageProgrammeLigne_UU, String trxName)
    {
      super (ctx, HR_StageProgrammeLigne_UU, trxName);
      /** if (HR_StageProgrammeLigne_UU == null)
        {
			setHR_StageObjectif_ID (0);
			setHR_StageProgrammeLigne_ID (0);
			setHR_StageProgramme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_StageProgrammeLigne (Properties ctx, String HR_StageProgrammeLigne_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_StageProgrammeLigne_UU, trxName, virtualColumns);
      /** if (HR_StageProgrammeLigne_UU == null)
        {
			setHR_StageObjectif_ID (0);
			setHR_StageProgrammeLigne_ID (0);
			setHR_StageProgramme_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_StageProgrammeLigne (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_StageProgrammeLigne[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set HR_StageProgrammeLigne_UU.
		@param HR_StageProgrammeLigne_UU HR_StageProgrammeLigne_UU
	*/
	public void setHR_StageProgrammeLigne_UU (String HR_StageProgrammeLigne_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_StageProgrammeLigne_UU, HR_StageProgrammeLigne_UU);
	}

	/** Get HR_StageProgrammeLigne_UU.
		@return HR_StageProgrammeLigne_UU	  */
	public String getHR_StageProgrammeLigne_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_StageProgrammeLigne_UU);
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

	/** Set Ponderation par Défaut.
		@param Ponderation_Defaut Ponderation par Défaut
	*/
	public void setPonderation_Defaut (int Ponderation_Defaut)
	{
		set_Value (COLUMNNAME_Ponderation_Defaut, Integer.valueOf(Ponderation_Defaut));
	}

	/** Get Ponderation par Défaut.
		@return Ponderation par Défaut	  */
	public int getPonderation_Defaut()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ponderation_Defaut);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}