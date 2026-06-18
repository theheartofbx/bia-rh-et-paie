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
package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Mission_Validation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mission_Validation")
public class X_HR_Mission_Validation extends PO implements I_HR_Mission_Validation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251214L;

    /** Standard Constructor */
    public X_HR_Mission_Validation (Properties ctx, int HR_Mission_Validation_ID, String trxName)
    {
      super (ctx, HR_Mission_Validation_ID, trxName);
      /** if (HR_Mission_Validation_ID == 0)
        {
			setHR_Mission_Validation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Validation (Properties ctx, int HR_Mission_Validation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Validation_ID, trxName, virtualColumns);
      /** if (HR_Mission_Validation_ID == 0)
        {
			setHR_Mission_Validation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Validation (Properties ctx, String HR_Mission_Validation_UU, String trxName)
    {
      super (ctx, HR_Mission_Validation_UU, trxName);
      /** if (HR_Mission_Validation_UU == null)
        {
			setHR_Mission_Validation_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Validation (Properties ctx, String HR_Mission_Validation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Validation_UU, trxName, virtualColumns);
      /** if (HR_Mission_Validation_UU == null)
        {
			setHR_Mission_Validation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Mission_Validation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Mission_Validation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Comments.
		@param Comments Comments or additional information
	*/
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments()
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Date de Rejet.
		@param Date_Rejet Date de Rejet
	*/
	public void setDate_Rejet (Timestamp Date_Rejet)
	{
		set_Value (COLUMNNAME_Date_Rejet, Date_Rejet);
	}

	/** Get Date de Rejet.
		@return Date de Rejet
	  */
	public Timestamp getDate_Rejet()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Rejet);
	}

	/** Set Date de Validation.
		@param Date_Validation Date de Validation
	*/
	public void setDate_Validation (Timestamp Date_Validation)
	{
		set_Value (COLUMNNAME_Date_Validation, Date_Validation);
	}

	/** Get Date de Validation.
		@return Date de Validation
	  */
	public Timestamp getDate_Validation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Validation);
	}

	public I_HR_Mission getHR_Mission() throws RuntimeException
	{
		return (I_HR_Mission)MTable.get(getCtx(), I_HR_Mission.Table_ID)
			.getPO(getHR_Mission_ID(), get_TrxName());
	}

	/** Set Mission.
		@param HR_Mission_ID Mission
	*/
	public void setHR_Mission_ID (int HR_Mission_ID)
	{
		if (HR_Mission_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_ID, Integer.valueOf(HR_Mission_ID));
	}

	/** Get Mission.
		@return Mission	  */
	public int getHR_Mission_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Validation de Mission.
		@param HR_Mission_Validation_ID Validation de Mission
	*/
	public void setHR_Mission_Validation_ID (int HR_Mission_Validation_ID)
	{
		if (HR_Mission_Validation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Validation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Validation_ID, Integer.valueOf(HR_Mission_Validation_ID));
	}

	/** Get Validation de Mission.
		@return Validation de Mission	  */
	public int getHR_Mission_Validation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_Validation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Mission_Validation_UU.
		@param HR_Mission_Validation_UU HR_Mission_Validation_UU
	*/
	public void setHR_Mission_Validation_UU (String HR_Mission_Validation_UU)
	{
		set_Value (COLUMNNAME_HR_Mission_Validation_UU, HR_Mission_Validation_UU);
	}

	/** Get HR_Mission_Validation_UU.
		@return HR_Mission_Validation_UU	  */
	public String getHR_Mission_Validation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mission_Validation_UU);
	}

	public I_HR_TypeValidation getHR_TypeValidation() throws RuntimeException
	{
		return (I_HR_TypeValidation)MTable.get(getCtx(), I_HR_TypeValidation.Table_ID)
			.getPO(getHR_TypeValidation_ID(), get_TrxName());
	}

	/** Set Type de Validation.
		@param HR_TypeValidation_ID Type de Validation
	*/
	public void setHR_TypeValidation_ID (int HR_TypeValidation_ID)
	{
		if (HR_TypeValidation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeValidation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeValidation_ID, Integer.valueOf(HR_TypeValidation_ID));
	}

	/** Get Type de Validation.
		@return Type de Validation	  */
	public int getHR_TypeValidation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeValidation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rejeté(e).
		@param IsRejetee Rejeté(e)
	*/
	public void setIsRejetee (boolean IsRejetee)
	{
		set_Value (COLUMNNAME_IsRejetee, Boolean.valueOf(IsRejetee));
	}

	/** Get Rejeté(e).
		@return Rejeté(e)
	  */
	public boolean isRejetee()
	{
		Object oo = get_Value(COLUMNNAME_IsRejetee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Validé(e).
		@param IsValidee Validé(e)
	*/
	public void setIsValidee (boolean IsValidee)
	{
		set_Value (COLUMNNAME_IsValidee, Boolean.valueOf(IsValidee));
	}

	/** Get Validé(e).
		@return Validé(e)
	  */
	public boolean isValidee()
	{
		Object oo = get_Value(COLUMNNAME_IsValidee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Validé/rejeté par (Matricule) :.
		@param Valide_Rejete_Par_Matricule Validé/rejeté par (Matricule) :
	*/
	public void setValide_Rejete_Par_Matricule (String Valide_Rejete_Par_Matricule)
	{
		set_Value (COLUMNNAME_Valide_Rejete_Par_Matricule, Valide_Rejete_Par_Matricule);
	}

	/** Get Validé/rejeté par (Matricule) :.
		@return Validé/rejeté par (Matricule) :
	  */
	public String getValide_Rejete_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Valide_Rejete_Par_Matricule);
	}

	public org.compiere.model.I_C_BPartner getValide_Rejete_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getValide_Rejete_Par_Nom_ID(), get_TrxName());
	}

	/** Set Validé/rejeté par (Nom) :.
		@param Valide_Rejete_Par_Nom_ID Validé/rejeté par (Nom) :
	*/
	public void setValide_Rejete_Par_Nom_ID (int Valide_Rejete_Par_Nom_ID)
	{
		if (Valide_Rejete_Par_Nom_ID < 1)
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, null);
		else
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, Integer.valueOf(Valide_Rejete_Par_Nom_ID));
	}

	/** Get Validé/rejeté par (Nom) :.
		@return Validé/rejeté par (Nom) :
	  */
	public int getValide_Rejete_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valide_Rejete_Par_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getValide_Rejete_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getValide_Rejete_Par_Poste_ID(), get_TrxName());
	}

	/** Set Validé/rejeté par (Poste) :.
		@param Valide_Rejete_Par_Poste_ID Validé/rejeté par (Poste) :
	*/
	public void setValide_Rejete_Par_Poste_ID (int Valide_Rejete_Par_Poste_ID)
	{
		if (Valide_Rejete_Par_Poste_ID < 1)
			set_Value (COLUMNNAME_Valide_Rejete_Par_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Valide_Rejete_Par_Poste_ID, Integer.valueOf(Valide_Rejete_Par_Poste_ID));
	}

	/** Get Validé/rejeté par (Poste) :.
		@return Validé/rejeté par (Poste) :
	  */
	public int getValide_Rejete_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valide_Rejete_Par_Poste_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}