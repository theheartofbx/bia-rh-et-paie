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
package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Absence_Compensation
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Absence_Compensation")
public class X_HR_Absence_Compensation extends PO implements I_HR_Absence_Compensation, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260630L;

    /** Standard Constructor */
    public X_HR_Absence_Compensation (Properties ctx, int HR_Absence_Compensation_ID, String trxName)
    {
      super (ctx, HR_Absence_Compensation_ID, trxName);
      /** if (HR_Absence_Compensation_ID == 0)
        {
			setAbsence_ID (0);
			setC_BPartner_ID (0);
			setConge_ID (0);
			setHR_Absence_Compensation_ID (0);
			setMode_Compensation (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Absence_Compensation (Properties ctx, int HR_Absence_Compensation_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Absence_Compensation_ID, trxName, virtualColumns);
      /** if (HR_Absence_Compensation_ID == 0)
        {
			setAbsence_ID (0);
			setC_BPartner_ID (0);
			setConge_ID (0);
			setHR_Absence_Compensation_ID (0);
			setMode_Compensation (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Absence_Compensation (Properties ctx, String HR_Absence_Compensation_UU, String trxName)
    {
      super (ctx, HR_Absence_Compensation_UU, trxName);
      /** if (HR_Absence_Compensation_UU == null)
        {
			setAbsence_ID (0);
			setC_BPartner_ID (0);
			setConge_ID (0);
			setHR_Absence_Compensation_ID (0);
			setMode_Compensation (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Absence_Compensation (Properties ctx, String HR_Absence_Compensation_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Absence_Compensation_UU, trxName, virtualColumns);
      /** if (HR_Absence_Compensation_UU == null)
        {
			setAbsence_ID (0);
			setC_BPartner_ID (0);
			setConge_ID (0);
			setHR_Absence_Compensation_ID (0);
			setMode_Compensation (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Absence_Compensation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Absence_Compensation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_HR_Absence getAbsence() throws RuntimeException
	{
		return (I_HR_Absence)MTable.get(getCtx(), I_HR_Absence.Table_ID)
			.getPO(getAbsence_ID(), get_TrxName());
	}

	/** Set Absence.
		@param Absence_ID Absence
	*/
	public void setAbsence_ID (int Absence_ID)
	{
		if (Absence_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Absence_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Absence_ID, Integer.valueOf(Absence_ID));
	}

	/** Get Absence.
		@return Absence
	  */
	public int getAbsence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Absence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_HR_Holiday getConge() throws RuntimeException
	{
		return (I_HR_Holiday)MTable.get(getCtx(), I_HR_Holiday.Table_ID)
			.getPO(getConge_ID(), get_TrxName());
	}

	/** Set Congé.
		@param Conge_ID Congé
	*/
	public void setConge_ID (int Conge_ID)
	{
		if (Conge_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Conge_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Conge_ID, Integer.valueOf(Conge_ID));
	}

	/** Get Congé.
		@return Congé
	  */
	public int getConge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Conge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date d&#039;Emission.
		@param Date_Emission Date d&#039;Emission
	*/
	public void setDate_Emission (Timestamp Date_Emission)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Emission, Date_Emission);
	}

	/** Get Date d&#039;Emission.
		@return Date d&#039;Emission
	  */
	public Timestamp getDate_Emission()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Emission);
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

	/** Set Matricule Emetteur.
		@param Emis_Par_Matricule Matricule Emetteur
	*/
	public void setEmis_Par_Matricule (String Emis_Par_Matricule)
	{
		set_Value (COLUMNNAME_Emis_Par_Matricule, Emis_Par_Matricule);
	}

	/** Get Matricule Emetteur.
		@return Matricule Emetteur
	  */
	public String getEmis_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Emis_Par_Matricule);
	}

	public org.compiere.model.I_C_BPartner getEmis_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEmis_Par_Nom_ID(), get_TrxName());
	}

	/** Set Nom Emetteur.
		@param Emis_Par_Nom_ID Nom Emetteur
	*/
	public void setEmis_Par_Nom_ID (int Emis_Par_Nom_ID)
	{
		if (Emis_Par_Nom_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Emis_Par_Nom_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Emis_Par_Nom_ID, Integer.valueOf(Emis_Par_Nom_ID));
	}

	/** Get Nom Emetteur.
		@return Nom Emetteur
	  */
	public int getEmis_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emis_Par_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getEmis_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getEmis_Par_Poste_ID(), get_TrxName());
	}

	/** Set Poste Emetteur.
		@param Emis_Par_Poste_ID Poste Emetteur
	*/
	public void setEmis_Par_Poste_ID (int Emis_Par_Poste_ID)
	{
		if (Emis_Par_Poste_ID < 1)
			set_Value (COLUMNNAME_Emis_Par_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Emis_Par_Poste_ID, Integer.valueOf(Emis_Par_Poste_ID));
	}

	/** Get Poste Emetteur.
		@return Poste Emetteur
	  */
	public int getEmis_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emis_Par_Poste_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Compensation des Absences.
		@param HR_Absence_Compensation_ID Compensation des Absences
	*/
	public void setHR_Absence_Compensation_ID (int HR_Absence_Compensation_ID)
	{
		if (HR_Absence_Compensation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Absence_Compensation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Absence_Compensation_ID, Integer.valueOf(HR_Absence_Compensation_ID));
	}

	/** Get Compensation des Absences.
		@return Compensation des Absences	  */
	public int getHR_Absence_Compensation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Absence_Compensation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Absence_Compensation_UU.
		@param HR_Absence_Compensation_UU HR_Absence_Compensation_UU
	*/
	public void setHR_Absence_Compensation_UU (String HR_Absence_Compensation_UU)
	{
		set_Value (COLUMNNAME_HR_Absence_Compensation_UU, HR_Absence_Compensation_UU);
	}

	/** Get HR_Absence_Compensation_UU.
		@return HR_Absence_Compensation_UU	  */
	public String getHR_Absence_Compensation_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Absence_Compensation_UU);
	}

	/** Set Matricule de l&#039;Employé.
		@param Matricule_Employe Matricule de l&#039;Employé
	*/
	public void setMatricule_Employe (String Matricule_Employe)
	{
		set_Value (COLUMNNAME_Matricule_Employe, Matricule_Employe);
	}

	/** Get Matricule de l&#039;Employé.
		@return Matricule de l&#039;Employé
	  */
	public String getMatricule_Employe()
	{
		return (String)get_Value(COLUMNNAME_Matricule_Employe);
	}

	/** Compenser au Début du Congé = Compenser au Début du Congé */
	public static final String MODE_COMPENSATION_CompenserAuDébutDuCongé = "Compenser au Début du Congé";
	/** Compenser à la Fin du Congé = Compenser à la Fin du Congé */
	public static final String MODE_COMPENSATION_CompenserÀLaFinDuCongé = "Compenser à la Fin du Congé";
	/** Set Mode de Compensation.
		@param Mode_Compensation Mode de Compensation
	*/
	public void setMode_Compensation (String Mode_Compensation)
	{

		set_Value (COLUMNNAME_Mode_Compensation, Mode_Compensation);
	}

	/** Get Mode de Compensation.
		@return Mode de Compensation
	  */
	public String getMode_Compensation()
	{
		return (String)get_Value(COLUMNNAME_Mode_Compensation);
	}

	public org.eevolution.model.I_HR_Job getPoste_Employe() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getPoste_Employe_ID(), get_TrxName());
	}

	/** Set Poste de l&#039;Employé.
		@param Poste_Employe_ID Poste de l&#039;Employé
	*/
	public void setPoste_Employe_ID (int Poste_Employe_ID)
	{
		if (Poste_Employe_ID < 1)
			set_Value (COLUMNNAME_Poste_Employe_ID, null);
		else
			set_Value (COLUMNNAME_Poste_Employe_ID, Integer.valueOf(Poste_Employe_ID));
	}

	/** Get Poste de l&#039;Employé.
		@return Poste de l&#039;Employé
	  */
	public int getPoste_Employe_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Poste_Employe_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}