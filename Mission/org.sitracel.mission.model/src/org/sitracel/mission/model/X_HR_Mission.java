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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.sitracel.model.I_HR_Categorie_Responsabilite;

/** Generated Model for HR_Mission
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mission")
public class X_HR_Mission extends PO implements I_HR_Mission, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251224L;

    /** Standard Constructor */
    public X_HR_Mission (Properties ctx, int HR_Mission_ID, String trxName)
    {
      super (ctx, HR_Mission_ID, trxName);
      /** if (HR_Mission_ID == 0)
        {
			setBudget_Ajuste (Env.ZERO);
			setBudget_Previsionnel (Env.ZERO);
			setC_City_ID (0);
			setC_Country_ID (0);
			setHR_Mission_ID (0);
			setHR_Type_Mission_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission (Properties ctx, int HR_Mission_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_ID, trxName, virtualColumns);
      /** if (HR_Mission_ID == 0)
        {
			setBudget_Ajuste (Env.ZERO);
			setBudget_Previsionnel (Env.ZERO);
			setC_City_ID (0);
			setC_Country_ID (0);
			setHR_Mission_ID (0);
			setHR_Type_Mission_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission (Properties ctx, String HR_Mission_UU, String trxName)
    {
      super (ctx, HR_Mission_UU, trxName);
      /** if (HR_Mission_UU == null)
        {
			setBudget_Ajuste (Env.ZERO);
			setBudget_Previsionnel (Env.ZERO);
			setC_City_ID (0);
			setC_Country_ID (0);
			setHR_Mission_ID (0);
			setHR_Type_Mission_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission (Properties ctx, String HR_Mission_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_UU, trxName, virtualColumns);
      /** if (HR_Mission_UU == null)
        {
			setBudget_Ajuste (Env.ZERO);
			setBudget_Previsionnel (Env.ZERO);
			setC_City_ID (0);
			setC_Country_ID (0);
			setHR_Mission_ID (0);
			setHR_Type_Mission_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Mission (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Mission[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Budget Ajusté.
		@param Budget_Ajuste Budget Ajusté
	*/
	public void setBudget_Ajuste (BigDecimal Budget_Ajuste)
	{
		set_ValueNoCheck (COLUMNNAME_Budget_Ajuste, Budget_Ajuste);
	}

	/** Get Budget Ajusté.
		@return Budget Ajusté	  */
	public BigDecimal getBudget_Ajuste()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Budget_Ajuste);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Budget Prévisionnel.
		@param Budget_Previsionnel Budget Prévisionnel
	*/
	public void setBudget_Previsionnel (BigDecimal Budget_Previsionnel)
	{
		set_ValueNoCheck (COLUMNNAME_Budget_Previsionnel, Budget_Previsionnel);
	}

	/** Get Budget Prévisionnel.
		@return Budget Prévisionnel	  */
	public BigDecimal getBudget_Previsionnel()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Budget_Previsionnel);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_City getC_City() throws RuntimeException
	{
		return (org.compiere.model.I_C_City)MTable.get(getCtx(), org.compiere.model.I_C_City.Table_ID)
			.getPO(getC_City_ID(), get_TrxName());
	}

	/** Set City.
		@param C_City_ID City
	*/
	public void setC_City_ID (int C_City_ID)
	{
		if (C_City_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_City_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_City_ID, Integer.valueOf(C_City_ID));
	}

	/** Get City.
		@return City
	  */
	public int getC_City_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_City_ID);
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
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
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

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
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

	public I_HR_Etat_Mission getHR_Etat_Mission() throws RuntimeException
	{
		return (I_HR_Etat_Mission)MTable.get(getCtx(), I_HR_Etat_Mission.Table_ID)
			.getPO(getHR_Etat_Mission_ID(), get_TrxName());
	}

	/** Set État de la Mission.
		@param HR_Etat_Mission_ID État de la Mission
	*/
	public void setHR_Etat_Mission_ID (int HR_Etat_Mission_ID)
	{
		if (HR_Etat_Mission_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Etat_Mission_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Etat_Mission_ID, Integer.valueOf(HR_Etat_Mission_ID));
	}

	/** Get État de la Mission.
		@return État de la Mission	  */
	public int getHR_Etat_Mission_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Etat_Mission_ID);
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

	/** Set HR_Mission_UU.
		@param HR_Mission_UU HR_Mission_UU
	*/
	public void setHR_Mission_UU (String HR_Mission_UU)
	{
		set_Value (COLUMNNAME_HR_Mission_UU, HR_Mission_UU);
	}

	/** Get HR_Mission_UU.
		@return HR_Mission_UU	  */
	public String getHR_Mission_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mission_UU);
	}

	public I_HR_Type_Mission getHR_Type_Mission() throws RuntimeException
	{
		return (I_HR_Type_Mission)MTable.get(getCtx(), I_HR_Type_Mission.Table_ID)
			.getPO(getHR_Type_Mission_ID(), get_TrxName());
	}

	/** Set Type de Mission.
		@param HR_Type_Mission_ID Type de Mission
	*/
	public void setHR_Type_Mission_ID (int HR_Type_Mission_ID)
	{
		if (HR_Type_Mission_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Type_Mission_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Type_Mission_ID, Integer.valueOf(HR_Type_Mission_ID));
	}

	/** Get Type de Mission.
		@return Type de Mission	  */
	public int getHR_Type_Mission_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Mission_ID);
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

	/** Set Est Affecté à la Mission.
		@param IsAffecteMission Est Affecté à la Mission
	*/
	public void setIsAffecteMission (boolean IsAffecteMission)
	{
		throw new IllegalArgumentException ("IsAffecteMission is virtual column");	}

	/** Get Est Affecté à la Mission.
		@return Est Affecté à la Mission
	  */
	public boolean isAffecteMission()
	{
		Object oo = get_Value(COLUMNNAME_IsAffecteMission);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_ValueNoCheck (COLUMNNAME_Name, Name);
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
}