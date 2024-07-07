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
package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_EmployeeJob
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_EmployeeJob")
public class X_HR_EmployeeJob extends PO implements I_HR_EmployeeJob, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240220L;

    /** Standard Constructor */
    public X_HR_EmployeeJob (Properties ctx, int HR_EmployeeJob_ID, String trxName)
    {
      super (ctx, HR_EmployeeJob_ID, trxName);
      /** if (HR_EmployeeJob_ID == 0)
        {
			setHR_EmployeeJob_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EmployeeJob (Properties ctx, int HR_EmployeeJob_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EmployeeJob_ID, trxName, virtualColumns);
      /** if (HR_EmployeeJob_ID == 0)
        {
			setHR_EmployeeJob_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EmployeeJob (Properties ctx, String HR_EmployeeJob_UU, String trxName)
    {
      super (ctx, HR_EmployeeJob_UU, trxName);
      /** if (HR_EmployeeJob_UU == null)
        {
			setHR_EmployeeJob_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_EmployeeJob (Properties ctx, String HR_EmployeeJob_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EmployeeJob_UU, trxName, virtualColumns);
      /** if (HR_EmployeeJob_UU == null)
        {
			setHR_EmployeeJob_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_EmployeeJob (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EmployeeJob[")
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

	/** Set Date From.
		@param DateFrom Starting date for a range
	*/
	public void setDateFrom (Timestamp DateFrom)
	{
		set_Value (COLUMNNAME_DateFrom, DateFrom);
	}

	/** Get Date From.
		@return Starting date for a range
	  */
	public Timestamp getDateFrom()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFrom);
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

	/** Set Employee Job.
		@param HR_EmployeeJob_ID Employee Job
	*/
	public void setHR_EmployeeJob_ID (int HR_EmployeeJob_ID)
	{
		if (HR_EmployeeJob_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeJob_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeJob_ID, Integer.valueOf(HR_EmployeeJob_ID));
	}

	/** Get Employee Job.
		@return Employee Job	  */
	public int getHR_EmployeeJob_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EmployeeJob_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_EmployeeJob_UU.
		@param HR_EmployeeJob_UU HR_EmployeeJob_UU
	*/
	public void setHR_EmployeeJob_UU (String HR_EmployeeJob_UU)
	{
		set_Value (COLUMNNAME_HR_EmployeeJob_UU, HR_EmployeeJob_UU);
	}

	/** Get HR_EmployeeJob_UU.
		@return HR_EmployeeJob_UU	  */
	public String getHR_EmployeeJob_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EmployeeJob_UU);
	}

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getHR_Job_ID(), get_TrxName());
	}

	/** Set Payroll Job.
		@param HR_Job_ID Payroll Job
	*/
	public void setHR_Job_ID (int HR_Job_ID)
	{
		if (HR_Job_ID < 1)
			set_Value (COLUMNNAME_HR_Job_ID, null);
		else
			set_Value (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	public int getHR_Job_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}