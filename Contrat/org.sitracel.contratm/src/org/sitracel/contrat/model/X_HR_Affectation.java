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
package org.sitracel.contrat.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Affectation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Affectation")
public class X_HR_Affectation extends PO implements I_HR_Affectation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260630L;

    /** Standard Constructor */
    public X_HR_Affectation (Properties ctx, int HR_Affectation_ID, String trxName)
    {
      super (ctx, HR_Affectation_ID, trxName);
      /** if (HR_Affectation_ID == 0)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Affectation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Affectation (Properties ctx, int HR_Affectation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Affectation_ID, trxName, virtualColumns);
      /** if (HR_Affectation_ID == 0)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Affectation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Affectation (Properties ctx, String HR_Affectation_UU, String trxName)
    {
      super (ctx, HR_Affectation_UU, trxName);
      /** if (HR_Affectation_UU == null)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Affectation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Affectation (Properties ctx, String HR_Affectation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Affectation_UU, trxName, virtualColumns);
      /** if (HR_Affectation_UU == null)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Affectation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Affectation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Affectation[")
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

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
	}

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
	}

	/** Set Affectation .
		@param HR_Affectation_ID Affectation 
	*/
	public void setHR_Affectation_ID (int HR_Affectation_ID)
	{
		if (HR_Affectation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Affectation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Affectation_ID, Integer.valueOf(HR_Affectation_ID));
	}

	/** Get Affectation .
		@return Affectation 	  */
	public int getHR_Affectation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Affectation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Affectation_UU.
		@param HR_Affectation_UU HR_Affectation_UU
	*/
	public void setHR_Affectation_UU (String HR_Affectation_UU)
	{
		set_Value (COLUMNNAME_HR_Affectation_UU, HR_Affectation_UU);
	}

	/** Get HR_Affectation_UU.
		@return HR_Affectation_UU	  */
	public String getHR_Affectation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Affectation_UU);
	}

	public I_HR_Contrat getHR_Contrat() throws RuntimeException
	{
		return (I_HR_Contrat)MTable.get(getCtx(), I_HR_Contrat.Table_ID)
			.getPO(getHR_Contrat_ID(), get_TrxName());
	}

	/** Set Contrat.
		@param HR_Contrat_ID Contrat
	*/
	public void setHR_Contrat_ID (int HR_Contrat_ID)
	{
		if (HR_Contrat_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Contrat_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Contrat_ID, Integer.valueOf(HR_Contrat_ID));
	}

	/** Get Contrat.
		@return Contrat	  */
	public int getHR_Contrat_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contrat_ID);
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

	/** Set Motif du changement.
		@param Motif_Changement Motif du changement
	*/
	public void setMotif_Changement (String Motif_Changement)
	{
		set_Value (COLUMNNAME_Motif_Changement, Motif_Changement);
	}

	/** Get Motif du changement.
		@return Motif du changement	  */
	public String getMotif_Changement()
	{
		return (String)get_Value(COLUMNNAME_Motif_Changement);
	}
}