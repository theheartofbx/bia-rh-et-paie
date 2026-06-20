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

/** Generated Model for HR_Mission_Affectation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mission_Affectation")
public class X_HR_Mission_Affectation extends PO implements I_HR_Mission_Affectation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260619L;

    /** Standard Constructor */
    public X_HR_Mission_Affectation (Properties ctx, int HR_Mission_Affectation_ID, String trxName)
    {
      super (ctx, HR_Mission_Affectation_ID, trxName);
      /** if (HR_Mission_Affectation_ID == 0)
        {
			setEmployee_ID (0);
			setHR_Mission_Affectation_ID (0);
			setHR_Mission_ID (0);
			setIsAnnulee (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Affectation (Properties ctx, int HR_Mission_Affectation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Affectation_ID, trxName, virtualColumns);
      /** if (HR_Mission_Affectation_ID == 0)
        {
			setEmployee_ID (0);
			setHR_Mission_Affectation_ID (0);
			setHR_Mission_ID (0);
			setIsAnnulee (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Affectation (Properties ctx, String HR_Mission_Affectation_UU, String trxName)
    {
      super (ctx, HR_Mission_Affectation_UU, trxName);
      /** if (HR_Mission_Affectation_UU == null)
        {
			setEmployee_ID (0);
			setHR_Mission_Affectation_ID (0);
			setHR_Mission_ID (0);
			setIsAnnulee (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Affectation (Properties ctx, String HR_Mission_Affectation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Affectation_UU, trxName, virtualColumns);
      /** if (HR_Mission_Affectation_UU == null)
        {
			setEmployee_ID (0);
			setHR_Mission_Affectation_ID (0);
			setHR_Mission_ID (0);
			setIsAnnulee (false);
// N
        } */
    }

    /** Load Constructor */
    public X_HR_Mission_Affectation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Mission_Affectation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Affecté Par (Matricule) :.
		@param Affecte_Par_Matricule Affecté Par (Matricule) :
	*/
	public void setAffecte_Par_Matricule (String Affecte_Par_Matricule)
	{
		set_Value (COLUMNNAME_Affecte_Par_Matricule, Affecte_Par_Matricule);
	}

	/** Get Affecté Par (Matricule) :.
		@return Affecté Par (Matricule) :	  */
	public String getAffecte_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Affecte_Par_Matricule);
	}

	public org.compiere.model.I_C_BPartner getAffecte_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getAffecte_Par_Nom_ID(), get_TrxName());
	}

	/** Set Affecté Par (Nom) :.
		@param Affecte_Par_Nom_ID Affecté Par (Nom) :
	*/
	public void setAffecte_Par_Nom_ID (int Affecte_Par_Nom_ID)
	{
		if (Affecte_Par_Nom_ID < 1)
			set_Value (COLUMNNAME_Affecte_Par_Nom_ID, null);
		else
			set_Value (COLUMNNAME_Affecte_Par_Nom_ID, Integer.valueOf(Affecte_Par_Nom_ID));
	}

	/** Get Affecté Par (Nom) :.
		@return Affecté Par (Nom) :	  */
	public int getAffecte_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Affecte_Par_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getAffecte_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getAffecte_Par_Poste_ID(), get_TrxName());
	}

	/** Set Affecté Par (Poste) :.
		@param Affecte_Par_Poste_ID Affecté Par (Poste) :
	*/
	public void setAffecte_Par_Poste_ID (int Affecte_Par_Poste_ID)
	{
		if (Affecte_Par_Poste_ID < 1)
			set_Value (COLUMNNAME_Affecte_Par_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Affecte_Par_Poste_ID, Integer.valueOf(Affecte_Par_Poste_ID));
	}

	/** Get Affecté Par (Poste) :.
		@return Affecté Par (Poste) :	  */
	public int getAffecte_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Affecte_Par_Poste_ID);
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

	public org.compiere.model.I_C_BPartner getEmployee() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEmployee_ID(), get_TrxName());
	}

	/** Set Emloyé(e).
		@param Employee_ID Emloyé(e)
	*/
	public void setEmployee_ID (int Employee_ID)
	{
		if (Employee_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Employee_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Employee_ID, Integer.valueOf(Employee_ID));
	}

	/** Get Emloyé(e).
		@return Emloyé(e)
	  */
	public int getEmployee_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Affectation des employés à la mission.
		@param HR_Mission_Affectation_ID Affectation des employés à la mission
	*/
	public void setHR_Mission_Affectation_ID (int HR_Mission_Affectation_ID)
	{
		if (HR_Mission_Affectation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Affectation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Affectation_ID, Integer.valueOf(HR_Mission_Affectation_ID));
	}

	/** Get Affectation des employés à la mission.
		@return Affectation des employés à la mission	  */
	public int getHR_Mission_Affectation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_Affectation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Mission_Affectation_UU.
		@param HR_Mission_Affectation_UU HR_Mission_Affectation_UU
	*/
	public void setHR_Mission_Affectation_UU (String HR_Mission_Affectation_UU)
	{
		set_Value (COLUMNNAME_HR_Mission_Affectation_UU, HR_Mission_Affectation_UU);
	}

	/** Get HR_Mission_Affectation_UU.
		@return HR_Mission_Affectation_UU	  */
	public String getHR_Mission_Affectation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mission_Affectation_UU);
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

	public I_HR_RoleMissionEmploye getHR_RoleMissionEmploye() throws RuntimeException
	{
		return (I_HR_RoleMissionEmploye)MTable.get(getCtx(), I_HR_RoleMissionEmploye.Table_ID)
			.getPO(getHR_RoleMissionEmploye_ID(), get_TrxName());
	}

	/** Set Rôle durant la Mission.
		@param HR_RoleMissionEmploye_ID Rôle durant la Mission
	*/
	public void setHR_RoleMissionEmploye_ID (int HR_RoleMissionEmploye_ID)
	{
		if (HR_RoleMissionEmploye_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_RoleMissionEmploye_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_RoleMissionEmploye_ID, Integer.valueOf(HR_RoleMissionEmploye_ID));
	}

	/** Get Rôle durant la Mission.
		@return Rôle durant la Mission	  */
	public int getHR_RoleMissionEmploye_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_RoleMissionEmploye_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Est Annulé(e).
		@param IsAnnulee Est Annulé(e)
	*/
	public void setIsAnnulee (boolean IsAnnulee)
	{
		set_Value (COLUMNNAME_IsAnnulee, Boolean.valueOf(IsAnnulee));
	}

	/** Get Est Annulé(e).
		@return Est Annulé(e)
	  */
	public boolean isAnnulee()
	{
		Object oo = get_Value(COLUMNNAME_IsAnnulee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}
}