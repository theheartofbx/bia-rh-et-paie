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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_EvalAppreciation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_EvalAppreciation")
public class X_HR_EvalAppreciation extends PO implements I_HR_EvalAppreciation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260819L;

    /** Standard Constructor */
    public X_HR_EvalAppreciation (Properties ctx, int HR_EvalAppreciation_ID, String trxName)
    {
      super (ctx, HR_EvalAppreciation_ID, trxName);
      /** if (HR_EvalAppreciation_ID == 0)
        {
			setHR_EvalAppreciation_ID (0);
			setName (null);
			setPourcentage (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalAppreciation (Properties ctx, int HR_EvalAppreciation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalAppreciation_ID, trxName, virtualColumns);
      /** if (HR_EvalAppreciation_ID == 0)
        {
			setHR_EvalAppreciation_ID (0);
			setName (null);
			setPourcentage (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalAppreciation (Properties ctx, String HR_EvalAppreciation_UU, String trxName)
    {
      super (ctx, HR_EvalAppreciation_UU, trxName);
      /** if (HR_EvalAppreciation_UU == null)
        {
			setHR_EvalAppreciation_ID (0);
			setName (null);
			setPourcentage (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalAppreciation (Properties ctx, String HR_EvalAppreciation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalAppreciation_UU, trxName, virtualColumns);
      /** if (HR_EvalAppreciation_UU == null)
        {
			setHR_EvalAppreciation_ID (0);
			setName (null);
			setPourcentage (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HR_EvalAppreciation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EvalAppreciation[")
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

	/** Set Appréciation Évaluation.
		@param HR_EvalAppreciation_ID Appréciation Évaluation
	*/
	public void setHR_EvalAppreciation_ID (int HR_EvalAppreciation_ID)
	{
		if (HR_EvalAppreciation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalAppreciation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalAppreciation_ID, Integer.valueOf(HR_EvalAppreciation_ID));
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

	/** Set HR_EvalAppreciation_UU.
		@param HR_EvalAppreciation_UU HR_EvalAppreciation_UU
	*/
	public void setHR_EvalAppreciation_UU (String HR_EvalAppreciation_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_EvalAppreciation_UU, HR_EvalAppreciation_UU);
	}

	/** Get HR_EvalAppreciation_UU.
		@return HR_EvalAppreciation_UU	  */
	public String getHR_EvalAppreciation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EvalAppreciation_UU);
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

	/** Set Pourcentage.
		@param Pourcentage Pourcentage
	*/
	public void setPourcentage (int Pourcentage)
	{
		set_Value (COLUMNNAME_Pourcentage, Integer.valueOf(Pourcentage));
	}

	/** Get Pourcentage.
		@return Pourcentage
	  */
	public int getPourcentage()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Pourcentage);
		if (ii == null)
			 return 0;
		return ii.intValue();
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