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
package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Formation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Formation")
public class X_HR_Formation extends PO implements I_HR_Formation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250421L;

    /** Standard Constructor */
    public X_HR_Formation (Properties ctx, int HR_Formation_ID, String trxName)
    {
      super (ctx, HR_Formation_ID, trxName);
      /** if (HR_Formation_ID == 0)
        {
			setHR_Formation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Formation (Properties ctx, int HR_Formation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Formation_ID, trxName, virtualColumns);
      /** if (HR_Formation_ID == 0)
        {
			setHR_Formation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Formation (Properties ctx, String HR_Formation_UU, String trxName)
    {
      super (ctx, HR_Formation_UU, trxName);
      /** if (HR_Formation_UU == null)
        {
			setHR_Formation_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Formation (Properties ctx, String HR_Formation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Formation_UU, trxName, virtualColumns);
      /** if (HR_Formation_UU == null)
        {
			setHR_Formation_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Formation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Formation[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
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

	@Override
	public I_HR_Evaluation getHR_Evaluation() throws RuntimeException
	{
		return (I_HR_Evaluation)MTable.get(getCtx(), I_HR_Evaluation.Table_ID)
			.getPO(getHR_Evaluation_ID(), get_TrxName());
	}

	/** Set Evaluation.
		@param HR_Evaluation_ID Evaluation
	*/
	@Override
	public void setHR_Evaluation_ID (int HR_Evaluation_ID)
	{
		if (HR_Evaluation_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Evaluation_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Evaluation_ID, Integer.valueOf(HR_Evaluation_ID));
		}
	}

	/** Get Evaluation.
		@return Evaluation	  */
	@Override
	public int getHR_Evaluation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Evaluation_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Formation.
		@param HR_Formation_ID Formation
	*/
	@Override
	public void setHR_Formation_ID (int HR_Formation_ID)
	{
		if (HR_Formation_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Formation_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Formation_ID, Integer.valueOf(HR_Formation_ID));
		}
	}

	/** Get Formation.
		@return Formation	  */
	@Override
	public int getHR_Formation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Formation_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Formation_UU.
		@param HR_Formation_UU HR_Formation_UU
	*/
	@Override
	public void setHR_Formation_UU (String HR_Formation_UU)
	{
		set_Value (COLUMNNAME_HR_Formation_UU, HR_Formation_UU);
	}

	/** Get HR_Formation_UU.
		@return HR_Formation_UU	  */
	@Override
	public String getHR_Formation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Formation_UU);
	}

	/** Set Processing.
		@param IsProcessing Processing
	*/
	@Override
	public void setIsProcessing (boolean IsProcessing)
	{
		set_Value (COLUMNNAME_IsProcessing, Boolean.valueOf(IsProcessing));
	}

	/** Get Processing.
		@return Processing	  */
	@Override
	public boolean isProcessing()
	{
		Object oo = get_Value(COLUMNNAME_IsProcessing);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	@Override
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	@Override
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
}