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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Concept
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Concept")
public class X_HR_Concept extends PO implements I_HR_Concept, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250327L;

    /** Standard Constructor */
    public X_HR_Concept (Properties ctx, int HR_Concept_ID, String trxName)
    {
      super (ctx, HR_Concept_ID, trxName);
      /** if (HR_Concept_ID == 0)
        {
			setColumnType (null);
			setHR_Concept_ID (0);
			setHR_TypeDeCharge_ID (0);
			setName (null);
			setType (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Concept (Properties ctx, int HR_Concept_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Concept_ID, trxName, virtualColumns);
      /** if (HR_Concept_ID == 0)
        {
			setColumnType (null);
			setHR_Concept_ID (0);
			setHR_TypeDeCharge_ID (0);
			setName (null);
			setType (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Concept (Properties ctx, String HR_Concept_UU, String trxName)
    {
      super (ctx, HR_Concept_UU, trxName);
      /** if (HR_Concept_UU == null)
        {
			setColumnType (null);
			setHR_Concept_ID (0);
			setHR_TypeDeCharge_ID (0);
			setName (null);
			setType (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Concept (Properties ctx, String HR_Concept_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Concept_UU, trxName, virtualColumns);
      /** if (HR_Concept_UU == null)
        {
			setColumnType (null);
			setHR_Concept_ID (0);
			setHR_TypeDeCharge_ID (0);
			setName (null);
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Concept (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org
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
      StringBuilder sb = new StringBuilder ("X_HR_Concept[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	@Override
	public org.compiere.model.I_AD_Reference getAD_Reference() throws RuntimeException
	{
		return (org.compiere.model.I_AD_Reference)MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_ID)
			.getPO(getAD_Reference_ID(), get_TrxName());
	}

	/** Set Reference.
		@param AD_Reference_ID System Reference and Validation
	*/
	@Override
	public void setAD_Reference_ID (int AD_Reference_ID)
	{
		if (AD_Reference_ID < 1) {
			set_Value (COLUMNNAME_AD_Reference_ID, null);
		} else {
			set_Value (COLUMNNAME_AD_Reference_ID, Integer.valueOf(AD_Reference_ID));
		}
	}

	/** Get Reference.
		@return System Reference and Validation
	  */
	@Override
	public int getAD_Reference_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** AccountSign AD_Reference_ID=118 */
	public static final int ACCOUNTSIGN_AD_Reference_ID=118;
	/** Credit = C */
	public static final String ACCOUNTSIGN_Credit = "C";
	/** Debit = D */
	public static final String ACCOUNTSIGN_Debit = "D";
	/** Natural = N */
	public static final String ACCOUNTSIGN_Natural = "N";
	/** Set Account Sign.
		@param AccountSign Indicates the Natural Sign of the Account as a Debit or Credit
	*/
	@Override
	public void setAccountSign (String AccountSign)
	{

		set_Value (COLUMNNAME_AccountSign, AccountSign);
	}

	/** Get Account Sign.
		@return Indicates the Natural Sign of the Account as a Debit or Credit
	  */
	@Override
	public String getAccountSign()
	{
		return (String)get_Value(COLUMNNAME_AccountSign);
	}

	@Override
	public org.eevolution.model.I_HR_Concept getBia_BaseElmt() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_ID)
			.getPO(getBia_BaseElmt_ID(), get_TrxName());
	}

	/** Set Bia Base Element ID.
		@param Bia_BaseElmt_ID Bia Base Element ID
	*/
	@Override
	public void setBia_BaseElmt_ID (int Bia_BaseElmt_ID)
	{
		if (Bia_BaseElmt_ID < 1) {
			set_Value (COLUMNNAME_Bia_BaseElmt_ID, null);
		} else {
			set_Value (COLUMNNAME_Bia_BaseElmt_ID, Integer.valueOf(Bia_BaseElmt_ID));
		}
	}

	/** Get Bia Base Element ID.
		@return Bia Base Element ID	  */
	@Override
	public int getBia_BaseElmt_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Bia_BaseElmt_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Pourcentage.
		@param Bia_Pourcentage Pourcentage
	*/
	@Override
	public void setBia_Pourcentage (BigDecimal Bia_Pourcentage)
	{
		set_Value (COLUMNNAME_Bia_Pourcentage, Bia_Pourcentage);
	}

	/** Get Pourcentage.
		@return Pourcentage	  */
	@Override
	public BigDecimal getBia_Pourcentage()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Bia_Pourcentage);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** ColumnType AD_Reference_ID=53243 */
	public static final int COLUMNTYPE_AD_Reference_ID=53243;
	/** Amount = A */
	public static final String COLUMNTYPE_Amount = "A";
	/** Date = D */
	public static final String COLUMNTYPE_Date = "D";
	/** Quantity = Q */
	public static final String COLUMNTYPE_Quantity = "Q";
	/** Text = T */
	public static final String COLUMNTYPE_Text = "T";
	/** Set Column Type.
		@param ColumnType Column Type
	*/
	@Override
	public void setColumnType (String ColumnType)
	{

		set_Value (COLUMNNAME_ColumnType, ColumnType);
	}

	/** Get Column Type.
		@return Column Type	  */
	@Override
	public String getColumnType()
	{
		return (String)get_Value(COLUMNNAME_ColumnType);
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
	public org.eevolution.model.I_HR_Concept_Category getHR_Concept_Category() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Concept_Category)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept_Category.Table_ID)
			.getPO(getHR_Concept_Category_ID(), get_TrxName());
	}

	/** Set Payroll Concept Category.
		@param HR_Concept_Category_ID Payroll Concept Category
	*/
	@Override
	public void setHR_Concept_Category_ID (int HR_Concept_Category_ID)
	{
		if (HR_Concept_Category_ID < 1) {
			set_Value (COLUMNNAME_HR_Concept_Category_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_Concept_Category_ID, Integer.valueOf(HR_Concept_Category_ID));
		}
	}

	/** Get Payroll Concept Category.
		@return Payroll Concept Category	  */
	@Override
	public int getHR_Concept_Category_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_Category_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Payroll Concept.
		@param HR_Concept_ID Payroll Concept
	*/
	@Override
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Concept_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
		}
	}

	/** Get Payroll Concept.
		@return Payroll Concept	  */
	@Override
	public int getHR_Concept_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Concept_UU.
		@param HR_Concept_UU HR_Concept_UU
	*/
	@Override
	public void setHR_Concept_UU (String HR_Concept_UU)
	{
		set_Value (COLUMNNAME_HR_Concept_UU, HR_Concept_UU);
	}

	/** Get HR_Concept_UU.
		@return HR_Concept_UU	  */
	@Override
	public String getHR_Concept_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Concept_UU);
	}

	@Override
	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_ID)
			.getPO(getHR_Department_ID(), get_TrxName());
	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department
	*/
	@Override
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) {
			set_Value (COLUMNNAME_HR_Department_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
		}
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	@Override
	public int getHR_Department_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getHR_Job_ID(), get_TrxName());
	}

	/** Set Payroll Job.
		@param HR_Job_ID Payroll Job
	*/
	@Override
	public void setHR_Job_ID (int HR_Job_ID)
	{
		if (HR_Job_ID < 1) {
			set_Value (COLUMNNAME_HR_Job_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
		}
	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	@Override
	public int getHR_Job_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_HR_Payroll getHR_Payroll() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Payroll)MTable.get(getCtx(), org.eevolution.model.I_HR_Payroll.Table_ID)
			.getPO(getHR_Payroll_ID(), get_TrxName());
	}

	/** Set Payroll.
		@param HR_Payroll_ID Payroll
	*/
	@Override
	public void setHR_Payroll_ID (int HR_Payroll_ID)
	{
		if (HR_Payroll_ID < 1) {
			set_Value (COLUMNNAME_HR_Payroll_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_Payroll_ID, Integer.valueOf(HR_Payroll_ID));
		}
	}

	/** Get Payroll.
		@return Payroll	  */
	@Override
	public int getHR_Payroll_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Payroll_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_TypeDeCharge getHR_TypeDeCharge() throws RuntimeException
	{
		return (I_HR_TypeDeCharge)MTable.get(getCtx(), I_HR_TypeDeCharge.Table_ID)
			.getPO(getHR_TypeDeCharge_ID(), get_TrxName());
	}

	/** Set Type de Charge.
		@param HR_TypeDeCharge_ID Type de Charge
	*/
	@Override
	public void setHR_TypeDeCharge_ID (int HR_TypeDeCharge_ID)
	{
		if (HR_TypeDeCharge_ID < 1) {
			set_Value (COLUMNNAME_HR_TypeDeCharge_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_TypeDeCharge_ID, Integer.valueOf(HR_TypeDeCharge_ID));
		}
	}

	/** Get Type de Charge.
		@return Type de Charge	  */
	@Override
	public int getHR_TypeDeCharge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeDeCharge_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Indicateur Fiscal et Social.
		@param IEXT_HR_FiscalSocial_Indicator Indicateur Fiscal et Social
	*/
	@Override
	public void setIEXT_HR_FiscalSocial_Indicator (String IEXT_HR_FiscalSocial_Indicator)
	{
		set_Value (COLUMNNAME_IEXT_HR_FiscalSocial_Indicator, IEXT_HR_FiscalSocial_Indicator);
	}

	/** Get Indicateur Fiscal et Social.
		@return Indicateur Fiscal et Social
	  */
	@Override
	public String getIEXT_HR_FiscalSocial_Indicator()
	{
		return (String)get_Value(COLUMNNAME_IEXT_HR_FiscalSocial_Indicator);
	}

	/** Set Calculated.
		@param IsCalculated The value is calculated by the system
	*/
	@Override
	public void setIsCalculated (boolean IsCalculated)
	{
		set_Value (COLUMNNAME_IsCalculated, Boolean.valueOf(IsCalculated));
	}

	/** Get Calculated.
		@return The value is calculated by the system
	  */
	@Override
	public boolean isCalculated()
	{
		Object oo = get_Value(COLUMNNAME_IsCalculated);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Default.
		@param IsDefault Default value
	*/
	@Override
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	@Override
	public boolean isDefault()
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Employee.
		@param IsEmployee Indicates if  this Business Partner is an employee
	*/
	@Override
	public void setIsEmployee (boolean IsEmployee)
	{
		set_Value (COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
	}

	/** Get Employee.
		@return Indicates if  this Business Partner is an employee
	  */
	@Override
	public boolean isEmployee()
	{
		Object oo = get_Value(COLUMNNAME_IsEmployee);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paid.
		@param IsPaid The document is paid
	*/
	@Override
	public void setIsPaid (boolean IsPaid)
	{
		set_Value (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
	}

	/** Get Paid.
		@return The document is paid
	  */
	@Override
	public boolean isPaid()
	{
		Object oo = get_Value(COLUMNNAME_IsPaid);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted Indicates if this document / line is printed
	*/
	@Override
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	@Override
	public boolean isPrinted()
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Read Write.
		@param IsReadWrite Field is read / write
	*/
	@Override
	public void setIsReadWrite (boolean IsReadWrite)
	{
		set_Value (COLUMNNAME_IsReadWrite, Boolean.valueOf(IsReadWrite));
	}

	/** Get Read Write.
		@return Field is read / write
	  */
	@Override
	public boolean isReadWrite()
	{
		Object oo = get_Value(COLUMNNAME_IsReadWrite);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Receipt.
		@param IsReceipt This is a sales transaction (receipt)
	*/
	@Override
	public void setIsReceipt (boolean IsReceipt)
	{
		set_Value (COLUMNNAME_IsReceipt, Boolean.valueOf(IsReceipt));
	}

	/** Get Receipt.
		@return This is a sales transaction (receipt)
	  */
	@Override
	public boolean isReceipt()
	{
		Object oo = get_Value(COLUMNNAME_IsReceipt);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Registered.
		@param IsRegistered The application is registered.
	*/
	@Override
	public void setIsRegistered (boolean IsRegistered)
	{
		set_Value (COLUMNNAME_IsRegistered, Boolean.valueOf(IsRegistered));
	}

	/** Get Registered.
		@return The application is registered.
	  */
	@Override
	public boolean isRegistered()
	{
		Object oo = get_Value(COLUMNNAME_IsRegistered);
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

	/** Set Sequence.
		@param SeqNo Method of ordering records; lowest number comes first
	*/
	@Override
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	@Override
	public int getSeqNo()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Type AD_Reference_ID=53245 */
	public static final int TYPE_AD_Reference_ID=53245;
	/** Concept = C */
	public static final String TYPE_Concept = "C";
	/** Rule Engine = E */
	public static final String TYPE_RuleEngine = "E";
	/** Information = I */
	public static final String TYPE_Information = "I";
	/** Reference = R */
	public static final String TYPE_Reference = "R";
	/** Set Type.
		@param Type Type of Validation (SQL, Java Script, Java Language)
	*/
	@Override
	public void setType (String Type)
	{

		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	@Override
	public String getType()
	{
		return (String)get_Value(COLUMNNAME_Type);
	}

	/** Set Valid from.
		@param ValidFrom Valid from including this date (first day)
	*/
	@Override
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	@Override
	public Timestamp getValidFrom()
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo Valid to including this date (last day)
	*/
	@Override
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	@Override
	public Timestamp getValidTo()
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	@Override
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	@Override
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getValue());
    }

	/** Set formule.
		@param formule formule
	*/
	@Override
	public void setformule (String formule)
	{
		set_Value (COLUMNNAME_formule, formule);
	}

	/** Get formule.
		@return formule	  */
	@Override
	public String getformule()
	{
		return (String)get_Value(COLUMNNAME_formule);
	}
}