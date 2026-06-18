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

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Candidat_Expérience
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Candidat_Expérience")
public class X_HR_Candidat_Expérience extends PO implements I_HR_Candidat_Expérience, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241210L;

    /** Standard Constructor */
    public X_HR_Candidat_Expérience (Properties ctx, int HR_Candidat_Expérience_ID, String trxName)
    {
      super (ctx, HR_Candidat_Expérience_ID, trxName);
      /** if (HR_Candidat_Expérience_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Candidat_Expérience_ID (0);
			setHR_Entreprise_ID (0);
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Candidat_Expérience (Properties ctx, int HR_Candidat_Expérience_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Candidat_Expérience_ID, trxName, virtualColumns);
      /** if (HR_Candidat_Expérience_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Candidat_Expérience_ID (0);
			setHR_Entreprise_ID (0);
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Candidat_Expérience (Properties ctx, String HR_Candidat_Expérience_UU, String trxName)
    {
      super (ctx, HR_Candidat_Expérience_UU, trxName);
      /** if (HR_Candidat_Expérience_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Candidat_Expérience_ID (0);
			setHR_Entreprise_ID (0);
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Candidat_Expérience (Properties ctx, String HR_Candidat_Expérience_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Candidat_Expérience_UU, trxName, virtualColumns);
      /** if (HR_Candidat_Expérience_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Candidat_Expérience_ID (0);
			setHR_Entreprise_ID (0);
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Candidat_Expérience (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Candidat_Expérience[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set Expérience des Candidats.
		@param HR_Candidat_Expérience_ID Expérience des Candidats
	*/
	@Override
	public void setHR_Candidat_Expérience_ID (int HR_Candidat_Expérience_ID)
	{
		if (HR_Candidat_Expérience_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Candidat_Expérience_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Candidat_Expérience_ID, Integer.valueOf(HR_Candidat_Expérience_ID));
		}
	}

	/** Get Expérience des Candidats.
		@return Expérience des Candidats	  */
	@Override
	public int getHR_Candidat_Expérience_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Candidat_Expérience_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Candidat_Expérience_UU.
		@param HR_Candidat_Expérience_UU HR_Candidat_Expérience_UU
	*/
	@Override
	public void setHR_Candidat_Expérience_UU (String HR_Candidat_Expérience_UU)
	{
		set_Value (COLUMNNAME_HR_Candidat_Expérience_UU, HR_Candidat_Expérience_UU);
	}

	/** Get HR_Candidat_Expérience_UU.
		@return HR_Candidat_Expérience_UU	  */
	@Override
	public String getHR_Candidat_Expérience_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Candidat_Expérience_UU);
	}

	@Override
	public I_HR_Entreprise getHR_Entreprise() throws RuntimeException
	{
		return (I_HR_Entreprise)MTable.get(getCtx(), I_HR_Entreprise.Table_ID)
			.getPO(getHR_Entreprise_ID(), get_TrxName());
	}

	/** Set Entreprise.
		@param HR_Entreprise_ID Entreprise
	*/
	@Override
	public void setHR_Entreprise_ID (int HR_Entreprise_ID)
	{
		if (HR_Entreprise_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Entreprise_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Entreprise_ID, Integer.valueOf(HR_Entreprise_ID));
		}
	}

	/** Get Entreprise.
		@return Entreprise	  */
	@Override
	public int getHR_Entreprise_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Entreprise_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Experience getHR_Experience() throws RuntimeException
	{
		return (I_HR_Experience)MTable.get(getCtx(), I_HR_Experience.Table_ID)
			.getPO(getHR_Experience_ID(), get_TrxName());
	}

	/** Set Experience.
		@param HR_Experience_ID Experience
	*/
	@Override
	public void setHR_Experience_ID (int HR_Experience_ID)
	{
		if (HR_Experience_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Experience_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Experience_ID, Integer.valueOf(HR_Experience_ID));
		}
	}

	/** Get Experience.
		@return Experience	  */
	@Override
	public int getHR_Experience_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Experience_ID);
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
			set_ValueNoCheck (COLUMNNAME_HR_Job_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
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
	public I_HR_Pertinence getHR_Pertinence() throws RuntimeException
	{
		return (I_HR_Pertinence)MTable.get(getCtx(), I_HR_Pertinence.Table_ID)
			.getPO(getHR_Pertinence_ID(), get_TrxName());
	}

	/** Set Pertinence de l&#039;Information.
		@param HR_Pertinence_ID Pertinence de l&#039;Information
	*/
	@Override
	public void setHR_Pertinence_ID (int HR_Pertinence_ID)
	{
		if (HR_Pertinence_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Pertinence_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Pertinence_ID, Integer.valueOf(HR_Pertinence_ID));
		}
	}

	/** Get Pertinence de l&#039;Information.
		@return Pertinence de l&#039;Information	  */
	@Override
	public int getHR_Pertinence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Pertinence_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
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