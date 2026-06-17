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

import org.compiere.model.I_C_ValidCombination;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.sitracel.conge.model.I_HR_Holiday;

/** Generated Model for HR_Attribute
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Attribute")
public class X_HR_Attribute extends PO implements I_HR_Attribute, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250318L;

    /** Standard Constructor */
    public X_HR_Attribute (Properties ctx, int HR_Attribute_ID, String trxName)
    {
      super (ctx, HR_Attribute_ID, trxName);
      /** if (HR_Attribute_ID == 0)
        {
			setHR_Attribute_ID (0);
			setHR_Concept_ID (0);
			setLine (0);
// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM HR_Attribute WHERE HR_Attribute_ID=@HR_Attribute_ID@
        } */
    }

    /** Standard Constructor */
    public X_HR_Attribute (Properties ctx, int HR_Attribute_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Attribute_ID, trxName, virtualColumns);
      /** if (HR_Attribute_ID == 0)
        {
			setHR_Attribute_ID (0);
			setHR_Concept_ID (0);
			setLine (0);
// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM HR_Attribute WHERE HR_Attribute_ID=@HR_Attribute_ID@
        } */
    }

    /** Standard Constructor */
    public X_HR_Attribute (Properties ctx, String HR_Attribute_UU, String trxName)
    {
      super (ctx, HR_Attribute_UU, trxName);
      /** if (HR_Attribute_UU == null)
        {
			setHR_Attribute_ID (0);
			setHR_Concept_ID (0);
			setLine (0);
// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM HR_Attribute WHERE HR_Attribute_ID=@HR_Attribute_ID@
        } */
    }

    /** Standard Constructor */
    public X_HR_Attribute (Properties ctx, String HR_Attribute_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Attribute_UU, trxName, virtualColumns);
      /** if (HR_Attribute_UU == null)
        {
			setHR_Attribute_ID (0);
			setHR_Concept_ID (0);
			setLine (0);
// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM HR_Attribute WHERE HR_Attribute_ID=@HR_Attribute_ID@
        } */
    }

    /** Load Constructor */
    public X_HR_Attribute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Attribute[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	@Override
	public org.compiere.model.I_AD_Rule getAD_Rule() throws RuntimeException
	{
		return (org.compiere.model.I_AD_Rule)MTable.get(getCtx(), org.compiere.model.I_AD_Rule.Table_ID)
			.getPO(getAD_Rule_ID(), get_TrxName());
	}

	/** Set Rule.
		@param AD_Rule_ID Rule
	*/
	@Override
	public void setAD_Rule_ID (int AD_Rule_ID)
	{
		if (AD_Rule_ID < 1) {
			set_Value (COLUMNNAME_AD_Rule_ID, null);
		} else {
			set_Value (COLUMNNAME_AD_Rule_ID, Integer.valueOf(AD_Rule_ID));
		}
	}

	/** Get Rule.
		@return Rule	  */
	@Override
	public int getAD_Rule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Rule_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Actualiser.
		@param Actualiser Actualiser
	*/
	@Override
	public void setActualiser (String Actualiser)
	{
		set_Value (COLUMNNAME_Actualiser, Actualiser);
	}

	/** Get Actualiser.
		@return Actualiser
	  */
	@Override
	public String getActualiser()
	{
		return (String)get_Value(COLUMNNAME_Actualiser);
	}

	/** Set Amount.
		@param Amount Amount in a defined currency
	*/
	@Override
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	@Override
	public BigDecimal getAmount()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	@Override
	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner .
		@param C_BPartner_ID Identifies a Business Partner
	*/
	@Override
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) {
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		} else {
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
		}
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	@Override
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
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
	public I_C_ValidCombination getHR_Attribute_A() throws RuntimeException
	{
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_ID)
			.getPO(getHR_Attribute_Acct(), get_TrxName());
	}

	/** Set Payroll Attribute Account.
		@param HR_Attribute_Acct Payroll Attribute Account
	*/
	@Override
	public void setHR_Attribute_Acct (int HR_Attribute_Acct)
	{
		set_Value (COLUMNNAME_HR_Attribute_Acct, Integer.valueOf(HR_Attribute_Acct));
	}

	/** Get Payroll Attribute Account.
		@return Payroll Attribute Account	  */
	@Override
	public int getHR_Attribute_Acct()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Attribute_Acct);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Payroll Employee Attribute.
		@param HR_Attribute_ID Payroll Employee Attribute
	*/
	@Override
	public void setHR_Attribute_ID (int HR_Attribute_ID)
	{
		if (HR_Attribute_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Attribute_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Attribute_ID, Integer.valueOf(HR_Attribute_ID));
		}
	}

	/** Get Payroll Employee Attribute.
		@return Payroll Employee Attribute	  */
	@Override
	public int getHR_Attribute_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Attribute_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Attribute_UU.
		@param HR_Attribute_UU HR_Attribute_UU
	*/
	@Override
	public void setHR_Attribute_UU (String HR_Attribute_UU)
	{
		set_Value (COLUMNNAME_HR_Attribute_UU, HR_Attribute_UU);
	}

	/** Get HR_Attribute_UU.
		@return HR_Attribute_UU	  */
	@Override
	public String getHR_Attribute_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Attribute_UU);
	}

	/** Set Concept From ID.
		@param HR_ConceptFrom_ID Concept From ID
	*/
	@Override
	public void setHR_ConceptFrom_ID (int HR_ConceptFrom_ID)
	{
		if (HR_ConceptFrom_ID < 1) {
			set_Value (COLUMNNAME_HR_ConceptFrom_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_ConceptFrom_ID, Integer.valueOf(HR_ConceptFrom_ID));
		}
	}

	/** Get Concept From ID.
		@return Concept From ID	  */
	@Override
	public int getHR_ConceptFrom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ConceptFrom_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_ID)
			.getPO(getHR_Concept_ID(), get_TrxName());
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
	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Employee)MTable.get(getCtx(), org.eevolution.model.I_HR_Employee.Table_ID)
			.getPO(getHR_Employee_ID(), get_TrxName());
	}

	/** Set Payroll Employee.
		@param HR_Employee_ID Payroll Employee
	*/
	@Override
	public void setHR_Employee_ID (int HR_Employee_ID)
	{
		if (HR_Employee_ID < 1) {
			set_Value (COLUMNNAME_HR_Employee_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_Employee_ID, Integer.valueOf(HR_Employee_ID));
		}
	}

	/** Get Payroll Employee.
		@return Payroll Employee	  */
	@Override
	public int getHR_Employee_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Employee_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Holiday getHR_Holiday() throws RuntimeException
	{
		return (I_HR_Holiday)MTable.get(getCtx(), I_HR_Holiday.Table_ID)
			.getPO(getHR_Holiday_ID(), get_TrxName());
	}

	/** Set Congé.
		@param HR_Holiday_ID Congé
	*/
	@Override
	public void setHR_Holiday_ID (int HR_Holiday_ID)
	{
		if (HR_Holiday_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, Integer.valueOf(HR_Holiday_ID));
		}
	}

	/** Get Congé.
		@return Congé	  */
	@Override
	public int getHR_Holiday_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Holiday_ID);
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
	public org.eevolution.model.I_HR_Process getHR_Process() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Process)MTable.get(getCtx(), org.eevolution.model.I_HR_Process.Table_ID)
			.getPO(getHR_Process_ID(), get_TrxName());
	}

	/** Set Payroll Process.
		@param HR_Process_ID Payroll Process
	*/
	@Override
	public void setHR_Process_ID (int HR_Process_ID)
	{
		if (HR_Process_ID < 1) {
			set_Value (COLUMNNAME_HR_Process_ID, null);
		} else {
			set_Value (COLUMNNAME_HR_Process_ID, Integer.valueOf(HR_Process_ID));
		}
	}

	/** Get Payroll Process.
		@return Payroll Process	  */
	@Override
	public int getHR_Process_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Process_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Ambas = AM */
	public static final String HR_REGION_Ambas = "AM";
	/** Coast = CO */
	public static final String HR_REGION_Coast = "CO";
	/** East = EA */
	public static final String HR_REGION_East = "EA";
	/** Sierra = SI */
	public static final String HR_REGION_Sierra = "SI";
	/** Set Region.
		@param HR_Region Region
	*/
	@Override
	public void setHR_Region (String HR_Region)
	{

		set_Value (COLUMNNAME_HR_Region, HR_Region);
	}

	/** Get Region.
		@return Region	  */
	@Override
	public String getHR_Region()
	{
		return (String)get_Value(COLUMNNAME_HR_Region);
	}

	/** Set Taux Horaire.
		@param IEXT_HourlyRate Taux Horaire
	*/
	@Override
	public void setIEXT_HourlyRate (BigDecimal IEXT_HourlyRate)
	{
		set_Value (COLUMNNAME_IEXT_HourlyRate, IEXT_HourlyRate);
	}

	/** Get Taux Horaire.
		@return Taux Horaire
	  */
	@Override
	public BigDecimal getIEXT_HourlyRate()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IEXT_HourlyRate);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Heures Standards.
		@param IEXT_StandartHours Heures Standards
	*/
	@Override
	public void setIEXT_StandartHours (BigDecimal IEXT_StandartHours)
	{
		set_Value (COLUMNNAME_IEXT_StandartHours, IEXT_StandartHours);
	}

	/** Get Heures Standards.
		@return Heures Standards
	  */
	@Override
	public BigDecimal getIEXT_StandartHours()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IEXT_StandartHours);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
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

	/** Set Line No.
		@param Line Unique line for this document
	*/
	@Override
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	@Override
	public int getLine()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Max Value.
		@param MaxValue Max Value
	*/
	@Override
	public void setMaxValue (int MaxValue)
	{
		set_Value (COLUMNNAME_MaxValue, Integer.valueOf(MaxValue));
	}

	/** Get Max Value.
		@return Max Value	  */
	@Override
	public int getMaxValue()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxValue);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Min Value.
		@param MinValue Min Value
	*/
	@Override
	public void setMinValue (int MinValue)
	{
		set_Value (COLUMNNAME_MinValue, Integer.valueOf(MinValue));
	}

	/** Get Min Value.
		@return Min Value	  */
	@Override
	public int getMinValue()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MinValue);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé.
		@param Nombre_Jour_Conge Nombre de Jour de Congé
	*/
	@Override
	public void setNombre_Jour_Conge (int Nombre_Jour_Conge)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Conge, Integer.valueOf(Nombre_Jour_Conge));
	}

	/** Get Nombre de Jour de Congé.
		@return Nombre de Jour de Congé
	  */
	@Override
	public int getNombre_Jour_Conge()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Conge);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Nombre de Jour de Suspension.
		@param Nombre_Jour_Suspension Nombre de Jour de Suspension
	*/
	@Override
	public void setNombre_Jour_Suspension (int Nombre_Jour_Suspension)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Suspension, Integer.valueOf(Nombre_Jour_Suspension));
	}

	/** Get Nombre de Jour de Suspension.
		@return Nombre de Jour de Suspension
	  */
	@Override
	public int getNombre_Jour_Suspension()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Suspension);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Début de la période de Reférence.
		@param Periode_Reference_Debut Début de la période de Reférence
	*/
	@Override
	public void setPeriode_Reference_Debut (Timestamp Periode_Reference_Debut)
	{
		set_ValueNoCheck (COLUMNNAME_Periode_Reference_Debut, Periode_Reference_Debut);
	}

	/** Get Début de la période de Reférence.
		@return Début de la période de Reférence
	  */
	@Override
	public Timestamp getPeriode_Reference_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Periode_Reference_Debut);
	}

	/** Set Fin de la période de Reférence.
		@param Periode_Reference_Fin Fin de la période de Reférence
	*/
	@Override
	public void setPeriode_Reference_Fin (Timestamp Periode_Reference_Fin)
	{
		set_ValueNoCheck (COLUMNNAME_Periode_Reference_Fin, Periode_Reference_Fin);
	}

	/** Get Fin de la période de Reférence.
		@return Fin de la période de Reférence
	  */
	@Override
	public Timestamp getPeriode_Reference_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Periode_Reference_Fin);
	}

	/** Set Quantity.
		@param Qty Quantity
	*/
	@Override
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	@Override
	public BigDecimal getQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Service date.
		@param ServiceDate Date service was provided
	*/
	@Override
	public void setServiceDate (Timestamp ServiceDate)
	{
		set_Value (COLUMNNAME_ServiceDate, ServiceDate);
	}

	/** Get Service date.
		@return Date service was provided
	  */
	@Override
	public Timestamp getServiceDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_ServiceDate);
	}

	/** Set Text Message.
		@param TextMsg Text Message
	*/
	@Override
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	@Override
	public String getTextMsg()
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
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
}