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
package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_FormationParticipant
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_FormationParticipant")
public class X_HR_FormationParticipant extends PO implements I_HR_FormationParticipant, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260810L;

    /** Standard Constructor */
    public X_HR_FormationParticipant (Properties ctx, int HR_FormationParticipant_ID, String trxName)
    {
      super (ctx, HR_FormationParticipant_ID, trxName);
      /** if (HR_FormationParticipant_ID == 0)
        {
			setHR_FormationParticipant_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationParticipant (Properties ctx, int HR_FormationParticipant_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationParticipant_ID, trxName, virtualColumns);
      /** if (HR_FormationParticipant_ID == 0)
        {
			setHR_FormationParticipant_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationParticipant (Properties ctx, String HR_FormationParticipant_UU, String trxName)
    {
      super (ctx, HR_FormationParticipant_UU, trxName);
      /** if (HR_FormationParticipant_UU == null)
        {
			setHR_FormationParticipant_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationParticipant (Properties ctx, String HR_FormationParticipant_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationParticipant_UU, trxName, virtualColumns);
      /** if (HR_FormationParticipant_UU == null)
        {
			setHR_FormationParticipant_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_FormationParticipant (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_FormationParticipant[")
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

	public I_HR_FormationDemande getHR_FormationDemande() throws RuntimeException
	{
		return (I_HR_FormationDemande)MTable.get(getCtx(), I_HR_FormationDemande.Table_ID)
			.getPO(getHR_FormationDemande_ID(), get_TrxName());
	}

	/** Set Demande de Participation Formation.
		@param HR_FormationDemande_ID Demande de Participation Formation
	*/
	public void setHR_FormationDemande_ID (int HR_FormationDemande_ID)
	{
		if (HR_FormationDemande_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationDemande_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationDemande_ID, Integer.valueOf(HR_FormationDemande_ID));
	}

	/** Get Demande de Participation Formation.
		@return Demande de Participation Formation	  */
	public int getHR_FormationDemande_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationDemande_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Participants Formation.
		@param HR_FormationParticipant_ID Participants Formation
	*/
	public void setHR_FormationParticipant_ID (int HR_FormationParticipant_ID)
	{
		if (HR_FormationParticipant_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationParticipant_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationParticipant_ID, Integer.valueOf(HR_FormationParticipant_ID));
	}

	/** Get Participants Formation.
		@return Participants Formation	  */
	public int getHR_FormationParticipant_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationParticipant_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_FormationParticipant_UU.
		@param HR_FormationParticipant_UU HR_FormationParticipant_UU
	*/
	public void setHR_FormationParticipant_UU (String HR_FormationParticipant_UU)
	{
		set_Value (COLUMNNAME_HR_FormationParticipant_UU, HR_FormationParticipant_UU);
	}

	/** Get HR_FormationParticipant_UU.
		@return HR_FormationParticipant_UU	  */
	public String getHR_FormationParticipant_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_FormationParticipant_UU);
	}

	public I_HR_FormationPlanning getHR_FormationPlanning() throws RuntimeException
	{
		return (I_HR_FormationPlanning)MTable.get(getCtx(), I_HR_FormationPlanning.Table_ID)
			.getPO(getHR_FormationPlanning_ID(), get_TrxName());
	}

	/** Set Planning de Formation.
		@param HR_FormationPlanning_ID Planning de Formation
	*/
	public void setHR_FormationPlanning_ID (int HR_FormationPlanning_ID)
	{
		if (HR_FormationPlanning_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanning_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanning_ID, Integer.valueOf(HR_FormationPlanning_ID));
	}

	/** Get Planning de Formation.
		@return Planning de Formation	  */
	public int getHR_FormationPlanning_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationPlanning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_FormationSession getHR_FormationSession() throws RuntimeException
	{
		return (I_HR_FormationSession)MTable.get(getCtx(), I_HR_FormationSession.Table_ID)
			.getPO(getHR_FormationSession_ID(), get_TrxName());
	}

	/** Set Session de Formation.
		@param HR_FormationSession_ID Session de Formation
	*/
	public void setHR_FormationSession_ID (int HR_FormationSession_ID)
	{
		if (HR_FormationSession_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationSession_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationSession_ID, Integer.valueOf(HR_FormationSession_ID));
	}

	/** Get Session de Formation.
		@return Session de Formation	  */
	public int getHR_FormationSession_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationSession_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_FormationStatutP getHR_FormationStatutP() throws RuntimeException
	{
		return (I_HR_FormationStatutP)MTable.get(getCtx(), I_HR_FormationStatutP.Table_ID)
			.getPO(getHR_FormationStatutP_ID(), get_TrxName());
	}

	/** Set Statut Participants Formation.
		@param HR_FormationStatutP_ID Statut Participants Formation
	*/
	public void setHR_FormationStatutP_ID (int HR_FormationStatutP_ID)
	{
		if (HR_FormationStatutP_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationStatutP_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationStatutP_ID, Integer.valueOf(HR_FormationStatutP_ID));
	}

	/** Get Statut Participants Formation.
		@return Statut Participants Formation	  */
	public int getHR_FormationStatutP_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationStatutP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}