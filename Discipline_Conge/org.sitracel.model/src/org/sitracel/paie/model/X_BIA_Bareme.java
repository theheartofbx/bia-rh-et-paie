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
package org.sitracel.paie.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for BIA_Bareme
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="BIA_Bareme")
public class X_BIA_Bareme extends PO implements I_BIA_Bareme, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250326L;

    /** Standard Constructor */
    public X_BIA_Bareme (Properties ctx, int BIA_Bareme_ID, String trxName)
    {
      super (ctx, BIA_Bareme_ID, trxName);
      /** if (BIA_Bareme_ID == 0)
        {
			setBIA_Bareme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_BIA_Bareme (Properties ctx, int BIA_Bareme_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, BIA_Bareme_ID, trxName, virtualColumns);
      /** if (BIA_Bareme_ID == 0)
        {
			setBIA_Bareme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_BIA_Bareme (Properties ctx, String BIA_Bareme_UU, String trxName)
    {
      super (ctx, BIA_Bareme_UU, trxName);
      /** if (BIA_Bareme_UU == null)
        {
			setBIA_Bareme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_BIA_Bareme (Properties ctx, String BIA_Bareme_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, BIA_Bareme_UU, trxName, virtualColumns);
      /** if (BIA_Bareme_UU == null)
        {
			setBIA_Bareme_ID (0);
        } */
    }

    /** Load Constructor */
    public X_BIA_Bareme (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_BIA_Bareme[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AmountFrom.
		@param AmountFrom Amount in a defined currency
	*/
	public void setAmountFrom (BigDecimal AmountFrom)
	{
		set_Value (COLUMNNAME_AmountFrom, AmountFrom);
	}

	/** Get AmountFrom.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmountFrom()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountFrom);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set AmountTo.
		@param AmountTo Amount in a defined currency
	*/
	public void setAmountTo (BigDecimal AmountTo)
	{
		set_Value (COLUMNNAME_AmountTo, AmountTo);
	}

	/** Get AmountTo.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmountTo()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountTo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount.
		@param Amt Amount
	*/
	public void setAmt (BigDecimal Amt)
	{
		set_Value (COLUMNNAME_Amt, Amt);
	}

	/** Get Amount.
		@return Amount
	  */
	public BigDecimal getAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Bareme.
		@param BIA_Bareme_ID Bareme
	*/
	public void setBIA_Bareme_ID (int BIA_Bareme_ID)
	{
		if (BIA_Bareme_ID < 1)
			set_ValueNoCheck (COLUMNNAME_BIA_Bareme_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_BIA_Bareme_ID, Integer.valueOf(BIA_Bareme_ID));
	}

	/** Get Bareme.
		@return Bareme	  */
	public int getBIA_Bareme_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BIA_Bareme_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BIA_Bareme_UU.
		@param BIA_Bareme_UU BIA_Bareme_UU
	*/
	public void setBIA_Bareme_UU (String BIA_Bareme_UU)
	{
		set_Value (COLUMNNAME_BIA_Bareme_UU, BIA_Bareme_UU);
	}

	/** Get BIA_Bareme_UU.
		@return BIA_Bareme_UU	  */
	public String getBIA_Bareme_UU()
	{
		return (String)get_Value(COLUMNNAME_BIA_Bareme_UU);
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

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_ID)
			.getPO(getHR_Concept_ID(), get_TrxName());
	}

	/** Set Payroll Concept.
		@param HR_Concept_ID Payroll Concept
	*/
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1)
			set_Value (COLUMNNAME_HR_Concept_ID, null);
		else
			set_Value (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
	}

	/** Get Payroll Concept.
		@return Payroll Concept	  */
	public int getHR_Concept_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set formule.
		@param formule formule
	*/
	public void setformule (String formule)
	{
		set_Value (COLUMNNAME_formule, formule);
	}

	/** Get formule.
		@return formule	  */
	public String getformule()
	{
		return (String)get_Value(COLUMNNAME_formule);
	}
}