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

/** Generated Model for HR_OffreExperience
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_OffreExperience")
public class X_HR_OffreExperience extends PO implements I_HR_OffreExperience, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241209L;

    /** Standard Constructor */
    public X_HR_OffreExperience (Properties ctx, int HR_OffreExperience_ID, String trxName)
    {
      super (ctx, HR_OffreExperience_ID, trxName);
      /** if (HR_OffreExperience_ID == 0)
        {
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreExperience_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreExperience (Properties ctx, int HR_OffreExperience_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreExperience_ID, trxName, virtualColumns);
      /** if (HR_OffreExperience_ID == 0)
        {
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreExperience_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreExperience (Properties ctx, String HR_OffreExperience_UU, String trxName)
    {
      super (ctx, HR_OffreExperience_UU, trxName);
      /** if (HR_OffreExperience_UU == null)
        {
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreExperience_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreExperience (Properties ctx, String HR_OffreExperience_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreExperience_UU, trxName, virtualColumns);
      /** if (HR_OffreExperience_UU == null)
        {
			setHR_Experience_ID (0);
			setHR_Job_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreExperience_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_OffreExperience (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_OffreExperience[")
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
	public I_HR_OffreEmploi getHR_OffreEmploi() throws RuntimeException
	{
		return (I_HR_OffreEmploi)MTable.get(getCtx(), I_HR_OffreEmploi.Table_ID)
			.getPO(getHR_OffreEmploi_ID(), get_TrxName());
	}

	/** Set Offre d&#039;Emploi.
		@param HR_OffreEmploi_ID Offre d&#039;Emploi
	*/
	@Override
	public void setHR_OffreEmploi_ID (int HR_OffreEmploi_ID)
	{
		if (HR_OffreEmploi_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_OffreEmploi_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_OffreEmploi_ID, Integer.valueOf(HR_OffreEmploi_ID));
		}
	}

	/** Get Offre d&#039;Emploi.
		@return Offre d&#039;Emploi	  */
	@Override
	public int getHR_OffreEmploi_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreEmploi_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Offre Expérience Requise.
		@param HR_OffreExperience_ID Offre Expérience Requise
	*/
	@Override
	public void setHR_OffreExperience_ID (int HR_OffreExperience_ID)
	{
		if (HR_OffreExperience_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_OffreExperience_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_OffreExperience_ID, Integer.valueOf(HR_OffreExperience_ID));
		}
	}

	/** Get Offre Expérience Requise.
		@return Offre Expérience Requise	  */
	@Override
	public int getHR_OffreExperience_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreExperience_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_OffreExperience_UU.
		@param HR_OffreExperience_UU HR_OffreExperience_UU
	*/
	@Override
	public void setHR_OffreExperience_UU (String HR_OffreExperience_UU)
	{
		set_Value (COLUMNNAME_HR_OffreExperience_UU, HR_OffreExperience_UU);
	}

	/** Get HR_OffreExperience_UU.
		@return HR_OffreExperience_UU	  */
	@Override
	public String getHR_OffreExperience_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_OffreExperience_UU);
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