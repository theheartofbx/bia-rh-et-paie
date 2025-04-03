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
package org.sitracel.discipline.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Dossier_Disciplinaire
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Dossier_Disciplinaire")
public class X_HR_Dossier_Disciplinaire extends PO implements I_HR_Dossier_Disciplinaire, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250318L;

    /** Standard Constructor */
    public X_HR_Dossier_Disciplinaire (Properties ctx, int HR_Dossier_Disciplinaire_ID, String trxName)
    {
      super (ctx, HR_Dossier_Disciplinaire_ID, trxName);
      /** if (HR_Dossier_Disciplinaire_ID == 0)
        {
			setHR_Dossier_Disciplinaire_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Dossier_Disciplinaire (Properties ctx, int HR_Dossier_Disciplinaire_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Dossier_Disciplinaire_ID, trxName, virtualColumns);
      /** if (HR_Dossier_Disciplinaire_ID == 0)
        {
			setHR_Dossier_Disciplinaire_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Dossier_Disciplinaire (Properties ctx, String HR_Dossier_Disciplinaire_UU, String trxName)
    {
      super (ctx, HR_Dossier_Disciplinaire_UU, trxName);
      /** if (HR_Dossier_Disciplinaire_UU == null)
        {
			setHR_Dossier_Disciplinaire_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Dossier_Disciplinaire (Properties ctx, String HR_Dossier_Disciplinaire_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Dossier_Disciplinaire_UU, trxName, virtualColumns);
      /** if (HR_Dossier_Disciplinaire_UU == null)
        {
			setHR_Dossier_Disciplinaire_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Dossier_Disciplinaire (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Dossier_Disciplinaire[")
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

	/** Set Dossier Disciplinaire.
		@param HR_Dossier_Disciplinaire_ID Dossier Disciplinaire
	*/
	public void setHR_Dossier_Disciplinaire_ID (int HR_Dossier_Disciplinaire_ID)
	{
		if (HR_Dossier_Disciplinaire_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Dossier_Disciplinaire_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Dossier_Disciplinaire_ID, Integer.valueOf(HR_Dossier_Disciplinaire_ID));
	}

	/** Get Dossier Disciplinaire.
		@return Dossier Disciplinaire	  */
	public int getHR_Dossier_Disciplinaire_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Dossier_Disciplinaire_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Dossier_Disciplinaire_UU.
		@param HR_Dossier_Disciplinaire_UU HR_Dossier_Disciplinaire_UU
	*/
	public void setHR_Dossier_Disciplinaire_UU (String HR_Dossier_Disciplinaire_UU)
	{
		set_Value (COLUMNNAME_HR_Dossier_Disciplinaire_UU, HR_Dossier_Disciplinaire_UU);
	}

	/** Get HR_Dossier_Disciplinaire_UU.
		@return HR_Dossier_Disciplinaire_UU	  */
	public String getHR_Dossier_Disciplinaire_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Dossier_Disciplinaire_UU);
	}

	public I_HR_Punishment getHR_Punishment() throws RuntimeException
	{
		return (I_HR_Punishment)MTable.get(getCtx(), I_HR_Punishment.Table_ID)
			.getPO(getHR_Punishment_ID(), get_TrxName());
	}

	/** Set Punishment.
		@param HR_Punishment_ID Punishment
	*/
	public void setHR_Punishment_ID (int HR_Punishment_ID)
	{
		if (HR_Punishment_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Punishment_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Punishment_ID, Integer.valueOf(HR_Punishment_ID));
	}

	/** Get Punishment.
		@return Punishment	  */
	public int getHR_Punishment_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Punishment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_TypeSanction getHR_TypeSanction() throws RuntimeException
	{
		return (I_HR_TypeSanction)MTable.get(getCtx(), I_HR_TypeSanction.Table_ID)
			.getPO(getHR_TypeSanction_ID(), get_TrxName());
	}

	/** Set Punishment Type.
		@param HR_TypeSanction_ID Punishment Type
	*/
	public void setHR_TypeSanction_ID (int HR_TypeSanction_ID)
	{
		if (HR_TypeSanction_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeSanction_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeSanction_ID, Integer.valueOf(HR_TypeSanction_ID));
	}

	/** Get Punishment Type.
		@return Punishment Type	  */
	public int getHR_TypeSanction_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeSanction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Motif .
		@param Motif Motif
	*/
	public void setMotif (String Motif)
	{
		set_ValueNoCheck (COLUMNNAME_Motif, Motif);
	}

	/** Get Motif .
		@return Motif
	  */
	public String getMotif()
	{
		return (String)get_Value(COLUMNNAME_Motif);
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