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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_BPartner
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="C_BPartner")
public class X_C_BPartner extends PO implements I_C_BPartner, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240322L;

    /** Standard Constructor */
    public X_C_BPartner (Properties ctx, int C_BPartner_ID, String trxName)
    {
      super (ctx, C_BPartner_ID, trxName);
      /** if (C_BPartner_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_BP_Group_ID (0);
			setIs1099Vendor (false);
// N
			setIsCustomer (false);
			setIsEmployee (false);
			setIsOneTime (false);
			setIsPOTaxExempt (false);
// N
			setIsProspect (false);
// N
			setIsSalesRep (false);
			setIsSummary (false);
			setIsVendor (false);
			setName (null);
			setSendEMail (false);
			setSO_CreditLimit (Env.ZERO);
			setSO_CreditUsed (Env.ZERO);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_C_BPartner (Properties ctx, int C_BPartner_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, C_BPartner_ID, trxName, virtualColumns);
      /** if (C_BPartner_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_BP_Group_ID (0);
			setIs1099Vendor (false);
// N
			setIsCustomer (false);
			setIsEmployee (false);
			setIsOneTime (false);
			setIsPOTaxExempt (false);
// N
			setIsProspect (false);
// N
			setIsSalesRep (false);
			setIsSummary (false);
			setIsVendor (false);
			setName (null);
			setSendEMail (false);
			setSO_CreditLimit (Env.ZERO);
			setSO_CreditUsed (Env.ZERO);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_C_BPartner (Properties ctx, String C_BPartner_UU, String trxName)
    {
      super (ctx, C_BPartner_UU, trxName);
      /** if (C_BPartner_UU == null)
        {
			setC_BPartner_ID (0);
			setC_BP_Group_ID (0);
			setIs1099Vendor (false);
// N
			setIsCustomer (false);
			setIsEmployee (false);
			setIsOneTime (false);
			setIsPOTaxExempt (false);
// N
			setIsProspect (false);
// N
			setIsSalesRep (false);
			setIsSummary (false);
			setIsVendor (false);
			setName (null);
			setSendEMail (false);
			setSO_CreditLimit (Env.ZERO);
			setSO_CreditUsed (Env.ZERO);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_C_BPartner (Properties ctx, String C_BPartner_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, C_BPartner_UU, trxName, virtualColumns);
      /** if (C_BPartner_UU == null)
        {
			setC_BPartner_ID (0);
			setC_BP_Group_ID (0);
			setIs1099Vendor (false);
// N
			setIsCustomer (false);
			setIsEmployee (false);
			setIsOneTime (false);
			setIsPOTaxExempt (false);
// N
			setIsProspect (false);
// N
			setIsSalesRep (false);
			setIsSummary (false);
			setIsVendor (false);
			setName (null);
			setSendEMail (false);
			setSO_CreditLimit (Env.ZERO);
			setSO_CreditUsed (Env.ZERO);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_BPartner (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_C_BPartner[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Acquisition Cost.
		@param AcqusitionCost The cost of gaining the prospect as a customer
	*/
	public void setAcqusitionCost (BigDecimal AcqusitionCost)
	{
		set_Value (COLUMNNAME_AcqusitionCost, AcqusitionCost);
	}

	/** Get Acquisition Cost.
		@return The cost of gaining the prospect as a customer
	  */
	public BigDecimal getAcqusitionCost()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AcqusitionCost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Actual Life Time Value.
		@param ActualLifeTimeValue Actual Life Time Revenue
	*/
	public void setActualLifeTimeValue (BigDecimal ActualLifeTimeValue)
	{
		set_Value (COLUMNNAME_ActualLifeTimeValue, ActualLifeTimeValue);
	}

	/** Get Actual Life Time Value.
		@return Actual Life Time Revenue
	  */
	public BigDecimal getActualLifeTimeValue()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ActualLifeTimeValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Image.
		@param AD_Image_ID Image or Icon
	*/
	public void setAD_Image_ID (int AD_Image_ID)
	{
		if (AD_Image_ID < 1)
			set_Value (COLUMNNAME_AD_Image_ID, null);
		else
			set_Value (COLUMNNAME_AD_Image_ID, Integer.valueOf(AD_Image_ID));
	}

	/** Get Image.
		@return Image or Icon
	  */
	public int getAD_Image_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Image_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** AD_Language AD_Reference_ID=327 */
	public static final int AD_LANGUAGE_AD_Reference_ID=327;
	/** Set Language.
		@param AD_Language Language for this entity
	*/
	public void setAD_Language (String AD_Language)
	{

		set_Value (COLUMNNAME_AD_Language, AD_Language);
	}

	/** Get Language.
		@return Language for this entity
	  */
	public String getAD_Language()
	{
		return (String)get_Value(COLUMNNAME_AD_Language);
	}

	/** Set Linked Organization.
		@param AD_OrgBP_ID The Business Partner is another Organization for explicit Inter-Org transactions
	*/
	public void setAD_OrgBP_ID (int AD_OrgBP_ID)
	{
		if (AD_OrgBP_ID < 1)
			set_Value (COLUMNNAME_AD_OrgBP_ID, null);
		else
			set_Value (COLUMNNAME_AD_OrgBP_ID, Integer.valueOf(AD_OrgBP_ID));
	}

	/** Get Linked Organization.
		@return The Business Partner is another Organization for explicit Inter-Org transactions
	  */
	public int getAD_OrgBP_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgBP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Adresse.
		@param Adresse Adresse
	*/
	public void setAdresse (String Adresse)
	{
		set_Value (COLUMNNAME_Adresse, Adresse);
	}

	/** Get Adresse.
		@return Adresse	  */
	public String getAdresse()
	{
		return (String)get_Value(COLUMNNAME_Adresse);
	}

	/** Set Birthday.
		@param Birthday Birthday or Anniversary day
	*/
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday()
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	/** Equation Based = Equation Based */
	public static final String BONUSTYPE_EquationBased = "Equation Based";
	/** Level Based = Level Based */
	public static final String BONUSTYPE_LevelBased = "Level Based";
	/** Set bonusType.
		@param bonusType bonusType
	*/
	public void setbonusType (String bonusType)
	{

		set_Value (COLUMNNAME_bonusType, bonusType);
	}

	/** Get bonusType.
		@return bonusType	  */
	public String getbonusType()
	{
		return (String)get_Value(COLUMNNAME_bonusType);
	}

	/** Set Business Partner Code.
		@param BPartnerCode Business Partner Code
	*/
	public void setBPartnerCode (String BPartnerCode)
	{
		set_Value (COLUMNNAME_BPartnerCode, BPartnerCode);
	}

	/** Get Business Partner Code.
		@return Business Partner Code	  */
	public String getBPartnerCode()
	{
		return (String)get_Value(COLUMNNAME_BPartnerCode);
	}

	/** Set Partner Parent.
		@param BPartner_Parent_ID Business Partner Parent
	*/
	public void setBPartner_Parent_ID (int BPartner_Parent_ID)
	{
		if (BPartner_Parent_ID < 1)
			set_Value (COLUMNNAME_BPartner_Parent_ID, null);
		else
			set_Value (COLUMNNAME_BPartner_Parent_ID, Integer.valueOf(BPartner_Parent_ID));
	}

	/** Get Partner Parent.
		@return Business Partner Parent
	  */
	public int getBPartner_Parent_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPartner_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** On Credit = 10000000 */
	public static final String BPARTNERPAYTYPE_OnCredit = "10000000";
	/** Cash = 10000001 */
	public static final String BPARTNERPAYTYPE_Cash = "10000001";
	/** Set Business Partner Pay Type.
		@param BPartnerPayType Business Partner Pay Type
	*/
	public void setBPartnerPayType (String BPartnerPayType)
	{

		set_Value (COLUMNNAME_BPartnerPayType, BPartnerPayType);
	}

	/** Get Business Partner Pay Type.
		@return Business Partner Pay Type	  */
	public String getBPartnerPayType()
	{
		return (String)get_Value(COLUMNNAME_BPartnerPayType);
	}

	/** Set BP Code.
		@param BPCode BP Code
	*/
	public void setBPCode (String BPCode)
	{
		set_Value (COLUMNNAME_BPCode, BPCode);
	}

	/** Get BP Code.
		@return BP Code	  */
	public String getBPCode()
	{
		return (String)get_Value(COLUMNNAME_BPCode);
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

	/** Set C_BPartner_UU.
		@param C_BPartner_UU C_BPartner_UU
	*/
	public void setC_BPartner_UU (String C_BPartner_UU)
	{
		set_Value (COLUMNNAME_C_BPartner_UU, C_BPartner_UU);
	}

	/** Get C_BPartner_UU.
		@return C_BPartner_UU	  */
	public String getC_BPartner_UU()
	{
		return (String)get_Value(COLUMNNAME_C_BPartner_UU);
	}

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException
	{
		return (org.compiere.model.I_C_BP_Group)MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_ID)
			.getPO(getC_BP_Group_ID(), get_TrxName());
	}

	/** Set Business Partner Group.
		@param C_BP_Group_ID Business Partner Group
	*/
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1)
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Business Partner Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException
	{
		return (org.compiere.model.I_C_Country)MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_ID)
			.getPO(getC_Country_ID(), get_TrxName());
	}

	/** Set Country.
		@param C_Country_ID Country 
	*/
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1)
			set_Value (COLUMNNAME_C_Country_ID, null);
		else
			set_Value (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocTypeTarget() throws RuntimeException
	{
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_ID)
			.getPO(getC_DocTypeTarget_ID(), get_TrxName());
	}

	/** Set Target Document Type.
		@param C_DocTypeTarget_ID Target document type for conversing documents
	*/
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID)
	{
		if (C_DocTypeTarget_ID < 1)
			set_Value (COLUMNNAME_C_DocTypeTarget_ID, null);
		else
			set_Value (COLUMNNAME_C_DocTypeTarget_ID, Integer.valueOf(C_DocTypeTarget_ID));
	}

	/** Get Target Document Type.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeTarget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Dunning getC_Dunning() throws RuntimeException
	{
		return (org.compiere.model.I_C_Dunning)MTable.get(getCtx(), org.compiere.model.I_C_Dunning.Table_ID)
			.getPO(getC_Dunning_ID(), get_TrxName());
	}

	/** Set Dunning.
		@param C_Dunning_ID Dunning Rules for overdue invoices
	*/
	public void setC_Dunning_ID (int C_Dunning_ID)
	{
		if (C_Dunning_ID < 1)
			set_Value (COLUMNNAME_C_Dunning_ID, null);
		else
			set_Value (COLUMNNAME_C_Dunning_ID, Integer.valueOf(C_Dunning_ID));
	}

	/** Get Dunning.
		@return Dunning Rules for overdue invoices
	  */
	public int getC_Dunning_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Dunning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Greeting getC_Greeting() throws RuntimeException
	{
		return (org.compiere.model.I_C_Greeting)MTable.get(getCtx(), org.compiere.model.I_C_Greeting.Table_ID)
			.getPO(getC_Greeting_ID(), get_TrxName());
	}

	/** Set Greeting.
		@param C_Greeting_ID Greeting to print on correspondence
	*/
	public void setC_Greeting_ID (int C_Greeting_ID)
	{
		if (C_Greeting_ID < 1)
			set_Value (COLUMNNAME_C_Greeting_ID, null);
		else
			set_Value (COLUMNNAME_C_Greeting_ID, Integer.valueOf(C_Greeting_ID));
	}

	/** Get Greeting.
		@return Greeting to print on correspondence
	  */
	public int getC_Greeting_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Greeting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_InvoiceSchedule getC_InvoiceSchedule() throws RuntimeException
	{
		return (org.compiere.model.I_C_InvoiceSchedule)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceSchedule.Table_ID)
			.getPO(getC_InvoiceSchedule_ID(), get_TrxName());
	}

	/** Set Invoice Schedule.
		@param C_InvoiceSchedule_ID Schedule for generating Invoices
	*/
	public void setC_InvoiceSchedule_ID (int C_InvoiceSchedule_ID)
	{
		if (C_InvoiceSchedule_ID < 1)
			set_Value (COLUMNNAME_C_InvoiceSchedule_ID, null);
		else
			set_Value (COLUMNNAME_C_InvoiceSchedule_ID, Integer.valueOf(C_InvoiceSchedule_ID));
	}

	/** Get Invoice Schedule.
		@return Schedule for generating Invoices
	  */
	public int getC_InvoiceSchedule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceSchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Dr = Dr */
	public static final String CIVILITY_Dr = "Dr";
	/** ETABLISSEMENT = ETABLISSEMENT */
	public static final String CIVILITY_ETABLISSEMENT = "ETABLISSEMENT";
	/** Mlle = Mlle */
	public static final String CIVILITY_Mlle = "Mlle";
	/** Mme = Mme */
	public static final String CIVILITY_Mme = "Mme";
	/** Mr = Mr */
	public static final String CIVILITY_Mr = "Mr";
	/** OG = OG */
	public static final String CIVILITY_OG = "OG";
	/** ONG = ONG */
	public static final String CIVILITY_ONG = "ONG";
	/** PAR DEFAUT = PAR DEFAUT */
	public static final String CIVILITY_PARDEFAUT = "PAR DEFAUT";
	/** Pr = Pr */
	public static final String CIVILITY_Pr = "Pr";
	/** SA = SA */
	public static final String CIVILITY_SA = "SA";
	/** SAAS = SAAS */
	public static final String CIVILITY_SAAS = "SAAS";
	/** SARL = SARL */
	public static final String CIVILITY_SARL = "SARL";
	/** Sté = Sté */
	public static final String CIVILITY_Sté = "Sté";
	/** Set Civility.
		@param Civility Civility
	*/
	public void setCivility (String Civility)
	{

		set_Value (COLUMNNAME_Civility, Civility);
	}

	/** Get Civility.
		@return Civility	  */
	public String getCivility()
	{
		return (String)get_Value(COLUMNNAME_Civility);
	}

	/** Set Country.
		@param Country Country
	*/
	public void setCountry (String Country)
	{
		set_ValueNoCheck (COLUMNNAME_Country, Country);
	}

	/** Get Country.
		@return Country	  */
	public String getCountry()
	{
		return (String)get_Value(COLUMNNAME_Country);
	}

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException
	{
		return (org.compiere.model.I_C_PaymentTerm)MTable.get(getCtx(), org.compiere.model.I_C_PaymentTerm.Table_ID)
			.getPO(getC_PaymentTerm_ID(), get_TrxName());
	}

	/** Set Payment Term.
		@param C_PaymentTerm_ID The terms of Payment (timing, discount)
	*/
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
	{
		if (C_PaymentTerm_ID < 1)
			set_Value (COLUMNNAME_C_PaymentTerm_ID, null);
		else
			set_Value (COLUMNNAME_C_PaymentTerm_ID, Integer.valueOf(C_PaymentTerm_ID));
	}

	/** Get Payment Term.
		@return The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentTerm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_C_TaxGroup getC_TaxGroup() throws RuntimeException
	{
		return (org.eevolution.model.I_C_TaxGroup)MTable.get(getCtx(), org.eevolution.model.I_C_TaxGroup.Table_ID)
			.getPO(getC_TaxGroup_ID(), get_TrxName());
	}

	/** Set Tax Group.
		@param C_TaxGroup_ID Tax Group
	*/
	public void setC_TaxGroup_ID (int C_TaxGroup_ID)
	{
		if (C_TaxGroup_ID < 1)
			set_Value (COLUMNNAME_C_TaxGroup_ID, null);
		else
			set_Value (COLUMNNAME_C_TaxGroup_ID, Integer.valueOf(C_TaxGroup_ID));
	}

	/** Get Tax Group.
		@return Tax Group	  */
	public int getC_TaxGroup_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
	{
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_ID)
			.getPO(getC_Tax_ID(), get_TrxName());
	}

	/** Set Tax Subscription.
		@param C_Tax_ID Tax Subscription identifier
	*/
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1)
			set_Value (COLUMNNAME_C_Tax_ID, null);
		else
			set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax Subscription.
		@return Tax Subscription identifier
	  */
	public int getC_Tax_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Customer Profile ID.
		@param CustomerProfileID Customer Profile ID
	*/
	public void setCustomerProfileID (String CustomerProfileID)
	{
		set_Value (COLUMNNAME_CustomerProfileID, CustomerProfileID);
	}

	/** Get Customer Profile ID.
		@return Customer Profile ID	  */
	public String getCustomerProfileID()
	{
		return (String)get_Value(COLUMNNAME_CustomerProfileID);
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

	public org.compiere.model.I_C_1099Box getDefault1099Box() throws RuntimeException
	{
		return (org.compiere.model.I_C_1099Box)MTable.get(getCtx(), org.compiere.model.I_C_1099Box.Table_ID)
			.getPO(getDefault1099Box_ID(), get_TrxName());
	}

	/** Set Default 1099 Box.
		@param Default1099Box_ID Default 1099 Box
	*/
	public void setDefault1099Box_ID (int Default1099Box_ID)
	{
		if (Default1099Box_ID < 1)
			set_Value (COLUMNNAME_Default1099Box_ID, null);
		else
			set_Value (COLUMNNAME_Default1099Box_ID, Integer.valueOf(Default1099Box_ID));
	}

	/** Get Default 1099 Box.
		@return Default 1099 Box	  */
	public int getDefault1099Box_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Default1099Box_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DeliveryRule AD_Reference_ID=151 */
	public static final int DELIVERYRULE_AD_Reference_ID=151;
	/** Availability = A */
	public static final String DELIVERYRULE_Availability = "A";
	/** Force = F */
	public static final String DELIVERYRULE_Force = "F";
	/** Complete Line = L */
	public static final String DELIVERYRULE_CompleteLine = "L";
	/** Manual = M */
	public static final String DELIVERYRULE_Manual = "M";
	/** Complete Order = O */
	public static final String DELIVERYRULE_CompleteOrder = "O";
	/** After Receipt = R */
	public static final String DELIVERYRULE_AfterReceipt = "R";
	/** Set Delivery Rule.
		@param DeliveryRule Defines the timing of Delivery
	*/
	public void setDeliveryRule (String DeliveryRule)
	{

		set_Value (COLUMNNAME_DeliveryRule, DeliveryRule);
	}

	/** Get Delivery Rule.
		@return Defines the timing of Delivery
	  */
	public String getDeliveryRule()
	{
		return (String)get_Value(COLUMNNAME_DeliveryRule);
	}

	/** DeliveryViaRule AD_Reference_ID=152 */
	public static final int DELIVERYVIARULE_AD_Reference_ID=152;
	/** Delivery = D */
	public static final String DELIVERYVIARULE_Delivery = "D";
	/** Pickup = P */
	public static final String DELIVERYVIARULE_Pickup = "P";
	/** Shipper = S */
	public static final String DELIVERYVIARULE_Shipper = "S";
	/** Set Delivery Via.
		@param DeliveryViaRule How the order will be delivered
	*/
	public void setDeliveryViaRule (String DeliveryViaRule)
	{

		set_Value (COLUMNNAME_DeliveryViaRule, DeliveryViaRule);
	}

	/** Get Delivery Via.
		@return How the order will be delivered
	  */
	public String getDeliveryViaRule()
	{
		return (String)get_Value(COLUMNNAME_DeliveryViaRule);
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

	/** Set Document Copies.
		@param DocumentCopies Number of copies to be printed
	*/
	public void setDocumentCopies (int DocumentCopies)
	{
		set_Value (COLUMNNAME_DocumentCopies, Integer.valueOf(DocumentCopies));
	}

	/** Get Document Copies.
		@return Number of copies to be printed
	  */
	public int getDocumentCopies()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DocumentCopies);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dunning Grace Date.
		@param DunningGrace Dunning Grace Date
	*/
	public void setDunningGrace (Timestamp DunningGrace)
	{
		set_Value (COLUMNNAME_DunningGrace, DunningGrace);
	}

	/** Get Dunning Grace Date.
		@return Dunning Grace Date	  */
	public Timestamp getDunningGrace()
	{
		return (Timestamp)get_Value(COLUMNNAME_DunningGrace);
	}

	/** Set D-U-N-S.
		@param DUNS Dun &amp; Bradstreet Number
	*/
	public void setDUNS (String DUNS)
	{
		set_Value (COLUMNNAME_DUNS, DUNS);
	}

	/** Get D-U-N-S.
		@return Dun &amp; Bradstreet Number
	  */
	public String getDUNS()
	{
		return (String)get_Value(COLUMNNAME_DUNS);
	}

	/** Set EMail Address.
		@param EMail Electronic Mail Address
	*/
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail()
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set First Sale.
		@param FirstSale Date of First Sale
	*/
	public void setFirstSale (Timestamp FirstSale)
	{
		set_Value (COLUMNNAME_FirstSale, FirstSale);
	}

	/** Get First Sale.
		@return Date of First Sale
	  */
	public Timestamp getFirstSale()
	{
		return (Timestamp)get_Value(COLUMNNAME_FirstSale);
	}

	/** Set Flat Discount %.
		@param FlatDiscount Flat discount percentage 
	*/
	public void setFlatDiscount (BigDecimal FlatDiscount)
	{
		set_Value (COLUMNNAME_FlatDiscount, FlatDiscount);
	}

	/** Get Flat Discount %.
		@return Flat discount percentage 
	  */
	public BigDecimal getFlatDiscount()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlatDiscount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** FreightCostRule AD_Reference_ID=153 */
	public static final int FREIGHTCOSTRULE_AD_Reference_ID=153;
	/** Calculated = C */
	public static final String FREIGHTCOSTRULE_Calculated = "C";
	/** Fix price = F */
	public static final String FREIGHTCOSTRULE_FixPrice = "F";
	/** Freight included = I */
	public static final String FREIGHTCOSTRULE_FreightIncluded = "I";
	/** Line = L */
	public static final String FREIGHTCOSTRULE_Line = "L";
	/** Set Freight Cost Rule.
		@param FreightCostRule Method for charging Freight
	*/
	public void setFreightCostRule (String FreightCostRule)
	{

		set_Value (COLUMNNAME_FreightCostRule, FreightCostRule);
	}

	/** Get Freight Cost Rule.
		@return Method for charging Freight
	  */
	public String getFreightCostRule()
	{
		return (String)get_Value(COLUMNNAME_FreightCostRule);
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_ID)
			.getPO(getHR_Department_ID(), get_TrxName());
	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department
	*/
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1)
			set_Value (COLUMNNAME_HR_Department_ID, null);
		else
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Formation getHR_Formation() throws RuntimeException
	{
		return (I_HR_Formation)MTable.get(getCtx(), I_HR_Formation.Table_ID)
			.getPO(getHR_Formation_ID(), get_TrxName());
	}

	/** Set Formation.
		@param HR_Formation_ID Formation
	*/
	public void setHR_Formation_ID (int HR_Formation_ID)
	{
		if (HR_Formation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Formation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Formation_ID, Integer.valueOf(HR_Formation_ID));
	}

	/** Get Formation.
		@return Formation	  */
	public int getHR_Formation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Formation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		throw new IllegalArgumentException ("HR_Job_ID is virtual column");	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	public int getHR_Job_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getIdentifiant_Employe() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getIdentifiant_Employe_ID(), get_TrxName());
	}

	/** Set Identifiant Employé(e).
		@param Identifiant_Employe_ID Identifiant Employé(e)
	*/
	public void setIdentifiant_Employe_ID (int Identifiant_Employe_ID)
	{
		throw new IllegalArgumentException ("Identifiant_Employe_ID is virtual column");	}

	/** Get Identifiant Employé(e).
		@return Identifiant Employé(e)
	  */
	public int getIdentifiant_Employe_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Identifiant_Employe_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Open Credit Memo.
		@param InvoicedAmt Open Credit Memo
	*/
	public void setInvoicedAmt (BigDecimal InvoicedAmt)
	{
		throw new IllegalArgumentException ("InvoicedAmt is virtual column");	}

	/** Get Open Credit Memo.
		@return Open Credit Memo
	  */
	public BigDecimal getInvoicedAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InvoicedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_AD_PrintFormat getInvoice_PrintFormat() throws RuntimeException
	{
		return (org.compiere.model.I_AD_PrintFormat)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFormat.Table_ID)
			.getPO(getInvoice_PrintFormat_ID(), get_TrxName());
	}

	/** Set Invoice Print Format.
		@param Invoice_PrintFormat_ID Print Format for printing Invoices
	*/
	public void setInvoice_PrintFormat_ID (int Invoice_PrintFormat_ID)
	{
		if (Invoice_PrintFormat_ID < 1)
			set_Value (COLUMNNAME_Invoice_PrintFormat_ID, null);
		else
			set_Value (COLUMNNAME_Invoice_PrintFormat_ID, Integer.valueOf(Invoice_PrintFormat_ID));
	}

	/** Get Invoice Print Format.
		@return Print Format for printing Invoices
	  */
	public int getInvoice_PrintFormat_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Invoice_PrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** InvoiceRule AD_Reference_ID=150 */
	public static final int INVOICERULE_AD_Reference_ID=150;
	/** After Delivery = D */
	public static final String INVOICERULE_AfterDelivery = "D";
	/** Immediate = I */
	public static final String INVOICERULE_Immediate = "I";
	/** After Order delivered = O */
	public static final String INVOICERULE_AfterOrderDelivered = "O";
	/** Customer Schedule after Delivery = S */
	public static final String INVOICERULE_CustomerScheduleAfterDelivery = "S";
	/** Set Invoice Rule.
		@param InvoiceRule Frequency and method of invoicing 
	*/
	public void setInvoiceRule (String InvoiceRule)
	{

		set_Value (COLUMNNAME_InvoiceRule, InvoiceRule);
	}

	/** Get Invoice Rule.
		@return Frequency and method of invoicing 
	  */
	public String getInvoiceRule()
	{
		return (String)get_Value(COLUMNNAME_InvoiceRule);
	}

	/** Set 1099 Vendor.
		@param Is1099Vendor 1099 Vendor
	*/
	public void setIs1099Vendor (boolean Is1099Vendor)
	{
		set_Value (COLUMNNAME_Is1099Vendor, Boolean.valueOf(Is1099Vendor));
	}

	/** Get 1099 Vendor.
		@return 1099 Vendor	  */
	public boolean is1099Vendor()
	{
		Object oo = get_Value(COLUMNNAME_Is1099Vendor);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Customer.
		@param IsCustomer Indicates if this Business Partner is a Customer
	*/
	public void setIsCustomer (boolean IsCustomer)
	{
		set_Value (COLUMNNAME_IsCustomer, Boolean.valueOf(IsCustomer));
	}

	/** Get Customer.
		@return Indicates if this Business Partner is a Customer
	  */
	public boolean isCustomer()
	{
		Object oo = get_Value(COLUMNNAME_IsCustomer);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Discount Printed.
		@param IsDiscountPrinted Print Discount on Invoice and Order
	*/
	public void setIsDiscountPrinted (boolean IsDiscountPrinted)
	{
		set_Value (COLUMNNAME_IsDiscountPrinted, Boolean.valueOf(IsDiscountPrinted));
	}

	/** Get Discount Printed.
		@return Print Discount on Invoice and Order
	  */
	public boolean isDiscountPrinted()
	{
		Object oo = get_Value(COLUMNNAME_IsDiscountPrinted);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Employee.
		@param IsEmployee Indicates if  this Business Partner is an employee
	*/
	public void setIsEmployee (boolean IsEmployee)
	{
		set_Value (COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
	}

	/** Get Employee.
		@return Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee()
	{
		Object oo = get_Value(COLUMNNAME_IsEmployee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Manufacturer.
		@param IsManufacturer Indicate role of this Business partner as Manufacturer
	*/
	public void setIsManufacturer (boolean IsManufacturer)
	{
		set_Value (COLUMNNAME_IsManufacturer, Boolean.valueOf(IsManufacturer));
	}

	/** Get Is Manufacturer.
		@return Indicate role of this Business partner as Manufacturer
	  */
	public boolean isManufacturer()
	{
		Object oo = get_Value(COLUMNNAME_IsManufacturer);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set One time transaction.
		@param IsOneTime One time transaction
	*/
	public void setIsOneTime (boolean IsOneTime)
	{
		set_Value (COLUMNNAME_IsOneTime, Boolean.valueOf(IsOneTime));
	}

	/** Get One time transaction.
		@return One time transaction	  */
	public boolean isOneTime()
	{
		Object oo = get_Value(COLUMNNAME_IsOneTime);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set PO Tax exempt.
		@param IsPOTaxExempt Business partner is exempt from tax on purchases
	*/
	public void setIsPOTaxExempt (boolean IsPOTaxExempt)
	{
		set_Value (COLUMNNAME_IsPOTaxExempt, Boolean.valueOf(IsPOTaxExempt));
	}

	/** Get PO Tax exempt.
		@return Business partner is exempt from tax on purchases
	  */
	public boolean isPOTaxExempt()
	{
		Object oo = get_Value(COLUMNNAME_IsPOTaxExempt);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Prospect.
		@param IsProspect Indicates this is a Prospect
	*/
	public void setIsProspect (boolean IsProspect)
	{
		set_Value (COLUMNNAME_IsProspect, Boolean.valueOf(IsProspect));
	}

	/** Get Prospect.
		@return Indicates this is a Prospect
	  */
	public boolean isProspect()
	{
		Object oo = get_Value(COLUMNNAME_IsProspect);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Representative.
		@param IsSalesRep Indicates if  the business partner is a sales representative or company agent
	*/
	public void setIsSalesRep (boolean IsSalesRep)
	{
		set_Value (COLUMNNAME_IsSalesRep, Boolean.valueOf(IsSalesRep));
	}

	/** Get Sales Representative.
		@return Indicates if  the business partner is a sales representative or company agent
	  */
	public boolean isSalesRep()
	{
		Object oo = get_Value(COLUMNNAME_IsSalesRep);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary This is a summary entity
	*/
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary()
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SO Tax exempt.
		@param IsTaxExempt Business partner is exempt from tax on sales
	*/
	public void setIsTaxExempt (boolean IsTaxExempt)
	{
		set_Value (COLUMNNAME_IsTaxExempt, Boolean.valueOf(IsTaxExempt));
	}

	/** Get SO Tax exempt.
		@return Business partner is exempt from tax on sales
	  */
	public boolean isTaxExempt()
	{
		Object oo = get_Value(COLUMNNAME_IsTaxExempt);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Vendor.
		@param IsVendor Indicates if this Business Partner is a Vendor
	*/
	public void setIsVendor (boolean IsVendor)
	{
		set_Value (COLUMNNAME_IsVendor, Boolean.valueOf(IsVendor));
	}

	/** Get Vendor.
		@return Indicates if this Business Partner is a Vendor
	  */
	public boolean isVendor()
	{
		Object oo = get_Value(COLUMNNAME_IsVendor);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Logo.
		@param Logo_ID Logo
	*/
	public void setLogo_ID (int Logo_ID)
	{
		if (Logo_ID < 1)
			set_Value (COLUMNNAME_Logo_ID, null);
		else
			set_Value (COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
	}

	/** Get Logo.
		@return Logo	  */
	public int getLogo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Logo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_DiscountSchema getM_DiscountSchema() throws RuntimeException
	{
		return (org.compiere.model.I_M_DiscountSchema)MTable.get(getCtx(), org.compiere.model.I_M_DiscountSchema.Table_ID)
			.getPO(getM_DiscountSchema_ID(), get_TrxName());
	}

	/** Set Discount Schema.
		@param M_DiscountSchema_ID Schema to calculate the trade discount percentage
	*/
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
	{
		if (M_DiscountSchema_ID < 1)
			set_Value (COLUMNNAME_M_DiscountSchema_ID, null);
		else
			set_Value (COLUMNNAME_M_DiscountSchema_ID, Integer.valueOf(M_DiscountSchema_ID));
	}

	/** Get Discount Schema.
		@return Schema to calculate the trade discount percentage
	  */
	public int getM_DiscountSchema_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DiscountSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException
	{
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_ID)
			.getPO(getM_PriceList_ID(), get_TrxName());
	}

	/** Set Price List.
		@param M_PriceList_ID Unique identifier of a Price List
	*/
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1)
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else
			set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set NAICS/SIC.
		@param NAICS Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	*/
	public void setNAICS (String NAICS)
	{
		set_Value (COLUMNNAME_NAICS, NAICS);
	}

	/** Get NAICS/SIC.
		@return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public String getNAICS()
	{
		return (String)get_Value(COLUMNNAME_NAICS);
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

	/** Set Name 2.
		@param Name2 Additional Name
	*/
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2()
	{
		return (String)get_Value(COLUMNNAME_Name2);
	}

	/** Set Nombre d&#039;enfants.
		@param NbreEnfants Nombre d&#039;enfants
	*/
	public void setNbreEnfants (int NbreEnfants)
	{
		set_Value (COLUMNNAME_NbreEnfants, Integer.valueOf(NbreEnfants));
	}

	/** Get Nombre d&#039;enfants.
		@return Nombre d&#039;enfants	  */
	public int getNbreEnfants()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NbreEnfants);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_NiveauEtude getNiveauEtude() throws RuntimeException
	{
		return (I_NiveauEtude)MTable.get(getCtx(), I_NiveauEtude.Table_ID)
			.getPO(getNiveauEtude_ID(), get_TrxName());
	}

	/** Set Niveau D&#039;Etude.
		@param NiveauEtude_ID Niveau D&#039;Etude
	*/
	public void setNiveauEtude_ID (int NiveauEtude_ID)
	{
		if (NiveauEtude_ID < 1)
			set_ValueNoCheck (COLUMNNAME_NiveauEtude_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_NiveauEtude_ID, Integer.valueOf(NiveauEtude_ID));
	}

	/** Get Niveau D&#039;Etude.
		@return Niveau D&#039;Etude	  */
	public int getNiveauEtude_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NiveauEtude_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employees.
		@param NumberEmployees Number of employees
	*/
	public void setNumberEmployees (int NumberEmployees)
	{
		set_Value (COLUMNNAME_NumberEmployees, Integer.valueOf(NumberEmployees));
	}

	/** Get Employees.
		@return Number of employees
	  */
	public int getNumberEmployees()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumberEmployees);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Numero de CNI.
		@param NumeroCni Numero de CNI
	*/
	public void setNumeroCni (String NumeroCni)
	{
		set_Value (COLUMNNAME_NumeroCni, NumeroCni);
	}

	/** Get Numero de CNI.
		@return Numero de CNI	  */
	public String getNumeroCni()
	{
		return (String)get_Value(COLUMNNAME_NumeroCni);
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Mixed POS Payment = M */
	public static final String PAYMENTRULE_MixedPOSPayment = "M";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Set Payment Rule.
		@param PaymentRule How you pay the invoice
	*/
	public void setPaymentRule (String PaymentRule)
	{

		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule()
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}

	/** PaymentRulePO AD_Reference_ID=195 */
	public static final int PAYMENTRULEPO_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULEPO_Cash = "B";
	/** Direct Debit = D */
	public static final String PAYMENTRULEPO_DirectDebit = "D";
	/** Credit Card = K */
	public static final String PAYMENTRULEPO_CreditCard = "K";
	/** Mixed POS Payment = M */
	public static final String PAYMENTRULEPO_MixedPOSPayment = "M";
	/** On Credit = P */
	public static final String PAYMENTRULEPO_OnCredit = "P";
	/** Check = S */
	public static final String PAYMENTRULEPO_Check = "S";
	/** Direct Deposit = T */
	public static final String PAYMENTRULEPO_DirectDeposit = "T";
	/** Set Payment Rule.
		@param PaymentRulePO Purchase payment option
	*/
	public void setPaymentRulePO (String PaymentRulePO)
	{

		set_Value (COLUMNNAME_PaymentRulePO, PaymentRulePO);
	}

	/** Get Payment Rule.
		@return Purchase payment option
	  */
	public String getPaymentRulePO()
	{
		return (String)get_Value(COLUMNNAME_PaymentRulePO);
	}

	/** Set Phone.
		@param Phone Identifies a telephone number
	*/
	public void setPhone (String Phone)
	{
		set_ValueNoCheck (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone()
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set Place Of Birth.
		@param PlaceofBirth Place Of Birth
	*/
	public void setPlaceofBirth (String PlaceofBirth)
	{
		set_Value (COLUMNNAME_PlaceofBirth, PlaceofBirth);
	}

	/** Get Place Of Birth.
		@return Place Of Birth	  */
	public String getPlaceofBirth()
	{
		return (String)get_Value(COLUMNNAME_PlaceofBirth);
	}

	public org.compiere.model.I_M_DiscountSchema getPO_DiscountSchema() throws RuntimeException
	{
		return (org.compiere.model.I_M_DiscountSchema)MTable.get(getCtx(), org.compiere.model.I_M_DiscountSchema.Table_ID)
			.getPO(getPO_DiscountSchema_ID(), get_TrxName());
	}

	/** Set PO Discount Schema.
		@param PO_DiscountSchema_ID Schema to calculate the purchase trade discount percentage
	*/
	public void setPO_DiscountSchema_ID (int PO_DiscountSchema_ID)
	{
		if (PO_DiscountSchema_ID < 1)
			set_Value (COLUMNNAME_PO_DiscountSchema_ID, null);
		else
			set_Value (COLUMNNAME_PO_DiscountSchema_ID, Integer.valueOf(PO_DiscountSchema_ID));
	}

	/** Get PO Discount Schema.
		@return Schema to calculate the purchase trade discount percentage
	  */
	public int getPO_DiscountSchema_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PO_DiscountSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_PaymentTerm getPO_PaymentTerm() throws RuntimeException
	{
		return (org.compiere.model.I_C_PaymentTerm)MTable.get(getCtx(), org.compiere.model.I_C_PaymentTerm.Table_ID)
			.getPO(getPO_PaymentTerm_ID(), get_TrxName());
	}

	/** Set PO Payment Term.
		@param PO_PaymentTerm_ID Payment rules for a purchase order
	*/
	public void setPO_PaymentTerm_ID (int PO_PaymentTerm_ID)
	{
		if (PO_PaymentTerm_ID < 1)
			set_Value (COLUMNNAME_PO_PaymentTerm_ID, null);
		else
			set_Value (COLUMNNAME_PO_PaymentTerm_ID, Integer.valueOf(PO_PaymentTerm_ID));
	}

	/** Get PO Payment Term.
		@return Payment rules for a purchase order
	  */
	public int getPO_PaymentTerm_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PO_PaymentTerm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList getPO_PriceList() throws RuntimeException
	{
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_ID)
			.getPO(getPO_PriceList_ID(), get_TrxName());
	}

	/** Set Purchase Pricelist.
		@param PO_PriceList_ID Price List used by this Business Partner
	*/
	public void setPO_PriceList_ID (int PO_PriceList_ID)
	{
		if (PO_PriceList_ID < 1)
			set_Value (COLUMNNAME_PO_PriceList_ID, null);
		else
			set_Value (COLUMNNAME_PO_PriceList_ID, Integer.valueOf(PO_PriceList_ID));
	}

	/** Get Purchase Pricelist.
		@return Price List used by this Business Partner
	  */
	public int getPO_PriceList_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PO_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Reference.
		@param POReference Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	*/
	public void setPOReference (String POReference)
	{
		set_Value (COLUMNNAME_POReference, POReference);
	}

	/** Get Order Reference.
		@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference()
	{
		return (String)get_Value(COLUMNNAME_POReference);
	}

	public org.eevolution.model.I_HR_Job getPoste_Utilisateur() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getPoste_Utilisateur_ID(), get_TrxName());
	}

	/** Set Poste Utilisateur.
		@param Poste_Utilisateur_ID Poste Utilisateur
	*/
	public void setPoste_Utilisateur_ID (int Poste_Utilisateur_ID)
	{
		throw new IllegalArgumentException ("Poste_Utilisateur_ID is virtual column");	}

	/** Get Poste Utilisateur.
		@return Poste Utilisateur
	  */
	public int getPoste_Utilisateur_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Poste_Utilisateur_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Potential Life Time Value.
		@param PotentialLifeTimeValue Total Revenue expected
	*/
	public void setPotentialLifeTimeValue (BigDecimal PotentialLifeTimeValue)
	{
		set_Value (COLUMNNAME_PotentialLifeTimeValue, PotentialLifeTimeValue);
	}

	/** Get Potential Life Time Value.
		@return Total Revenue expected
	  */
	public BigDecimal getPotentialLifeTimeValue()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PotentialLifeTimeValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rating.
		@param Rating Classification or Importance
	*/
	public void setRating (String Rating)
	{
		set_Value (COLUMNNAME_Rating, Rating);
	}

	/** Get Rating.
		@return Classification or Importance
	  */
	public String getRating()
	{
		return (String)get_Value(COLUMNNAME_Rating);
	}

	/** Set Reference No.
		@param ReferenceNo Your customer or vendor number at the Business Partner&#039;s site
	*/
	public void setReferenceNo (String ReferenceNo)
	{
		set_Value (COLUMNNAME_ReferenceNo, ReferenceNo);
	}

	/** Get Reference No.
		@return Your customer or vendor number at the Business Partner&#039;s site
	  */
	public String getReferenceNo()
	{
		return (String)get_Value(COLUMNNAME_ReferenceNo);
	}

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getSalesRep_ID(), get_TrxName());
	}

	/** Set Sales Representative.
		@param SalesRep_ID Sales Representative or Company Agent
	*/
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1)
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Volume in 1.000.
		@param SalesVolume Total Volume of Sales in Thousands of Currency
	*/
	public void setSalesVolume (int SalesVolume)
	{
		set_Value (COLUMNNAME_SalesVolume, Integer.valueOf(SalesVolume));
	}

	/** Get Sales Volume in 1.000.
		@return Total Volume of Sales in Thousands of Currency
	  */
	public int getSalesVolume()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesVolume);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Send EMail.
		@param SendEMail Enable sending Document EMail
	*/
	public void setSendEMail (boolean SendEMail)
	{
		set_Value (COLUMNNAME_SendEMail, Boolean.valueOf(SendEMail));
	}

	/** Get Send EMail.
		@return Enable sending Document EMail
	  */
	public boolean isSendEMail()
	{
		Object oo = get_Value(COLUMNNAME_SendEMail);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Femme = F */
	public static final String SEX_Femme = "F";
	/** Homme = H */
	public static final String SEX_Homme = "H";
	/** Set Sex.
		@param Sex Sex
	*/
	public void setSex (String Sex)
	{

		set_Value (COLUMNNAME_Sex, Sex);
	}

	/** Get Sex.
		@return Sex	  */
	public String getSex()
	{
		return (String)get_Value(COLUMNNAME_Sex);
	}

	/** Set Share.
		@param ShareOfCustomer Share of Customer&#039;s business as a percentage
	*/
	public void setShareOfCustomer (int ShareOfCustomer)
	{
		set_Value (COLUMNNAME_ShareOfCustomer, Integer.valueOf(ShareOfCustomer));
	}

	/** Get Share.
		@return Share of Customer&#039;s business as a percentage
	  */
	public int getShareOfCustomer()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShareOfCustomer);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Min Shelf Life %.
		@param ShelfLifeMinPct Minimum Shelf Life in percent based on Product Instance Guarantee Date
	*/
	public void setShelfLifeMinPct (int ShelfLifeMinPct)
	{
		set_Value (COLUMNNAME_ShelfLifeMinPct, Integer.valueOf(ShelfLifeMinPct));
	}

	/** Get Min Shelf Life %.
		@return Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinPct()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShelfLifeMinPct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Credit Limit.
		@param SO_CreditLimit Total outstanding invoice amounts allowed
	*/
	public void setSO_CreditLimit (BigDecimal SO_CreditLimit)
	{
		set_Value (COLUMNNAME_SO_CreditLimit, SO_CreditLimit);
	}

	/** Get Credit Limit.
		@return Total outstanding invoice amounts allowed
	  */
	public BigDecimal getSO_CreditLimit()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SO_CreditLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** SOCreditStatus AD_Reference_ID=289 */
	public static final int SOCREDITSTATUS_AD_Reference_ID=289;
	/** Credit Hold = H */
	public static final String SOCREDITSTATUS_CreditHold = "H";
	/** Credit OK = O */
	public static final String SOCREDITSTATUS_CreditOK = "O";
	/** Credit Stop = S */
	public static final String SOCREDITSTATUS_CreditStop = "S";
	/** Credit Watch = W */
	public static final String SOCREDITSTATUS_CreditWatch = "W";
	/** No Credit Check = X */
	public static final String SOCREDITSTATUS_NoCreditCheck = "X";
	/** Set Credit Status.
		@param SOCreditStatus Business Partner Credit Status
	*/
	public void setSOCreditStatus (String SOCreditStatus)
	{

		set_Value (COLUMNNAME_SOCreditStatus, SOCreditStatus);
	}

	/** Get Credit Status.
		@return Business Partner Credit Status
	  */
	public String getSOCreditStatus()
	{
		return (String)get_Value(COLUMNNAME_SOCreditStatus);
	}

	/** Set Credit Used.
		@param SO_CreditUsed Current open balance
	*/
	public void setSO_CreditUsed (BigDecimal SO_CreditUsed)
	{
		set_ValueNoCheck (COLUMNNAME_SO_CreditUsed, SO_CreditUsed);
	}

	/** Get Credit Used.
		@return Current open balance
	  */
	public BigDecimal getSO_CreditUsed()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SO_CreditUsed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Description.
		@param SO_Description Description to be used on orders
	*/
	public void setSO_Description (String SO_Description)
	{
		set_Value (COLUMNNAME_SO_Description, SO_Description);
	}

	/** Get Order Description.
		@return Description to be used on orders
	  */
	public String getSO_Description()
	{
		return (String)get_Value(COLUMNNAME_SO_Description);
	}

	/** AGENT_MAITRISE = AGENT_MAITRISE */
	public static final String STATUS_AGENT_MAITRISE = "AGENT_MAITRISE";
	/** CADRE = CADRE */
	public static final String STATUS_CADRE = "CADRE";
	/** Set Status.
		@param Status Status of the currently running check
	*/
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus()
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** CELIBATAIRE = CELIBATAIRE */
	public static final String STATUSMATRIMONIAL_CELIBATAIRE = "CELIBATAIRE";
	/** DIVORCE(E) = DIVORCE(E) */
	public static final String STATUSMATRIMONIAL_DIVORCEE = "DIVORCE(E)";
	/** MARIE = MARIE */
	public static final String STATUSMATRIMONIAL_MARIE = "MARIE";
	/** VEUF(VE) = VEUF(VE) */
	public static final String STATUSMATRIMONIAL_VEUFVE = "VEUF(VE)";
	/** Set StatusMatrimonial.
		@param StatusMatrimonial StatusMatrimonial
	*/
	public void setStatusMatrimonial (String StatusMatrimonial)
	{

		set_Value (COLUMNNAME_StatusMatrimonial, StatusMatrimonial);
	}

	/** Get StatusMatrimonial.
		@return StatusMatrimonial	  */
	public String getStatusMatrimonial()
	{
		return (String)get_Value(COLUMNNAME_StatusMatrimonial);
	}

	public org.compiere.model.I_C_BPartner getSuperieurEmploye2() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getSuperieurEmploye2_ID(), get_TrxName());
	}

	/** Set Supérieur Hiérarchique (N+2).
		@param SuperieurEmploye2_ID Supérieur Hiérarchique (N+2)
	*/
	public void setSuperieurEmploye2_ID (int SuperieurEmploye2_ID)
	{
		if (SuperieurEmploye2_ID < 1)
			set_Value (COLUMNNAME_SuperieurEmploye2_ID, null);
		else
			set_Value (COLUMNNAME_SuperieurEmploye2_ID, Integer.valueOf(SuperieurEmploye2_ID));
	}

	/** Get Supérieur Hiérarchique (N+2).
		@return Supérieur Hiérarchique (N+2)	  */
	public int getSuperieurEmploye2_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SuperieurEmploye2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getSuperieurEmploye() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getSuperieurEmploye_ID(), get_TrxName());
	}

	/** Set Superieur Employé.
		@param SuperieurEmploye_ID Superieur Employé
	*/
	public void setSuperieurEmploye_ID (int SuperieurEmploye_ID)
	{
		if (SuperieurEmploye_ID < 1)
			set_Value (COLUMNNAME_SuperieurEmploye_ID, null);
		else
			set_Value (COLUMNNAME_SuperieurEmploye_ID, Integer.valueOf(SuperieurEmploye_ID));
	}

	/** Get Superieur Employé.
		@return Superieur Employé	  */
	public int getSuperieurEmploye_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SuperieurEmploye_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax ID.
		@param TaxID Tax Identification
	*/
	public void setTaxID (String TaxID)
	{
		set_Value (COLUMNNAME_TaxID, TaxID);
	}

	/** Get Tax ID.
		@return Tax Identification
	  */
	public String getTaxID()
	{
		return (String)get_Value(COLUMNNAME_TaxID);
	}

	/** Set Telephone.
		@param Telephone Telephone
	*/
	public void setTelephone (String Telephone)
	{
		set_Value (COLUMNNAME_Telephone, Telephone);
	}

	/** Get Telephone.
		@return Telephone	  */
	public String getTelephone()
	{
		return (String)get_Value(COLUMNNAME_Telephone);
	}

	/** Set Open Balance.
		@param TotalOpenBalance Total Open Balance Amount in primary Accounting Currency
	*/
	public void setTotalOpenBalance (BigDecimal TotalOpenBalance)
	{
		set_Value (COLUMNNAME_TotalOpenBalance, TotalOpenBalance);
	}

	/** Get Open Balance.
		@return Total Open Balance Amount in primary Accounting Currency
	  */
	public BigDecimal getTotalOpenBalance()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalOpenBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set town.
		@param town town
	*/
	public void settown (String town)
	{
		set_Value (COLUMNNAME_town, town);
	}

	/** Get town.
		@return town	  */
	public String gettown()
	{
		return (String)get_Value(COLUMNNAME_town);
	}

	/** Set URL.
		@param URL Full URL address - e.g. http://www.idempiere.org
	*/
	public void setURL (String URL)
	{
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.idempiere.org
	  */
	public String getURL()
	{
		return (String)get_Value(COLUMNNAME_URL);
	}

	public I_HR_Categorie_Responsabilite getUser_Responsabilite() throws RuntimeException
	{
		return (I_HR_Categorie_Responsabilite)MTable.get(getCtx(), I_HR_Categorie_Responsabilite.Table_ID)
			.getPO(getUser_Responsabilite_ID(), get_TrxName());
	}

	/** Set Catégorie de Responsabilité de l&#039;Utilisateur.
		@param User_Responsabilite_ID Catégorie de Responsabilité de l&#039;Utilisateur
	*/
	public void setUser_Responsabilite_ID (int User_Responsabilite_ID)
	{
		throw new IllegalArgumentException ("User_Responsabilite_ID is virtual column");	}

	/** Get Catégorie de Responsabilité de l&#039;Utilisateur.
		@return Catégorie de Responsabilité de l&#039;Utilisateur
	  */
	public int getUser_Responsabilite_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User_Responsabilite_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valid To.
		@param ValidTo Valid to including this date (last day)
	*/
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid To.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo()
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
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