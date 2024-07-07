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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Absence
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_Absence")
public class X_HR_Absence extends PO implements I_HR_Absence, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240418L;

    /** Standard Constructor */
    public X_HR_Absence (Properties ctx, int HR_Absence_ID, String trxName)
    {
      super (ctx, HR_Absence_ID, trxName);
      /** if (HR_Absence_ID == 0)
        {
			setC_BPartner_ID (0);
			setDate_Absence (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Absence_ID (0);
			setMatricule_Employe (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Absence (Properties ctx, int HR_Absence_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Absence_ID, trxName, virtualColumns);
      /** if (HR_Absence_ID == 0)
        {
			setC_BPartner_ID (0);
			setDate_Absence (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Absence_ID (0);
			setMatricule_Employe (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Absence (Properties ctx, String HR_Absence_UU, String trxName)
    {
      super (ctx, HR_Absence_UU, trxName);
      /** if (HR_Absence_UU == null)
        {
			setC_BPartner_ID (0);
			setDate_Absence (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Absence_ID (0);
			setMatricule_Employe (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Absence (Properties ctx, String HR_Absence_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Absence_UU, trxName, virtualColumns);
      /** if (HR_Absence_UU == null)
        {
			setC_BPartner_ID (0);
			setDate_Absence (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Absence_ID (0);
			setMatricule_Employe (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Absence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Absence[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
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

	/** Set Date de l&#039;Absence.
		@param Date_Absence Date de l&#039;Absence
	*/
	public void setDate_Absence (Timestamp Date_Absence)
	{
		set_Value (COLUMNNAME_Date_Absence, Date_Absence);
	}

	/** Get Date de l&#039;Absence.
		@return Date de l&#039;Absence
	  */
	public Timestamp getDate_Absence()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Absence);
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
			set_Value (COLUMNNAME_Emis_Par_Nom_ID, null);
		else
			set_Value (COLUMNNAME_Emis_Par_Nom_ID, Integer.valueOf(Emis_Par_Nom_ID));
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

	/** Set Absence.
		@param HR_Absence_ID Absence
	*/
	public void setHR_Absence_ID (int HR_Absence_ID)
	{
		if (HR_Absence_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Absence_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Absence_ID, Integer.valueOf(HR_Absence_ID));
	}

	/** Get Absence.
		@return Absence	  */
	public int getHR_Absence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Absence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Absence_UU.
		@param HR_Absence_UU HR_Absence_UU
	*/
	public void setHR_Absence_UU (String HR_Absence_UU)
	{
		set_Value (COLUMNNAME_HR_Absence_UU, HR_Absence_UU);
	}

	/** Get HR_Absence_UU.
		@return HR_Absence_UU	  */
	public String getHR_Absence_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Absence_UU);
	}

	public I_HR_Demande_Explication getHR_Demande_Explication() throws RuntimeException
	{
		return (I_HR_Demande_Explication)MTable.get(getCtx(), I_HR_Demande_Explication.Table_ID)
			.getPO(getHR_Demande_Explication_ID(), get_TrxName());
	}

	/** Set Demande d&#039;Explication.
		@param HR_Demande_Explication_ID Demande d&#039;Explication
	*/
	public void setHR_Demande_Explication_ID (int HR_Demande_Explication_ID)
	{
		if (HR_Demande_Explication_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Demande_Explication_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Demande_Explication_ID, Integer.valueOf(HR_Demande_Explication_ID));
	}

	/** Get Demande d&#039;Explication.
		@return Demande d&#039;Explication	  */
	public int getHR_Demande_Explication_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Demande_Explication_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Holiday getHR_Holiday() throws RuntimeException
	{
		return (I_HR_Holiday)MTable.get(getCtx(), I_HR_Holiday.Table_ID)
			.getPO(getHR_Holiday_ID(), get_TrxName());
	}

	/** Set Congé.
		@param HR_Holiday_ID Congé
	*/
	public void setHR_Holiday_ID (int HR_Holiday_ID)
	{
		if (HR_Holiday_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Holiday_ID, Integer.valueOf(HR_Holiday_ID));
	}

	/** Get Congé.
		@return Congé	  */
	public int getHR_Holiday_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Holiday_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Type_Absence getHR_Type_Absence() throws RuntimeException
	{
		return (I_HR_Type_Absence)MTable.get(getCtx(), I_HR_Type_Absence.Table_ID)
			.getPO(getHR_Type_Absence_ID(), get_TrxName());
	}

	/** Set Type d&#039;Absence.
		@param HR_Type_Absence_ID Type d&#039;Absence
	*/
	public void setHR_Type_Absence_ID (int HR_Type_Absence_ID)
	{
		if (HR_Type_Absence_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Type_Absence_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Type_Absence_ID, Integer.valueOf(HR_Type_Absence_ID));
	}

	/** Get Type d&#039;Absence.
		@return Type d&#039;Absence	  */
	public int getHR_Type_Absence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Absence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Compte comme un Congé.
		@param IsConge Compte comme un Congé
	*/
	public void setIsConge (boolean IsConge)
	{
		set_Value (COLUMNNAME_IsConge, Boolean.valueOf(IsConge));
	}

	/** Get Compte comme un Congé.
		@return Compte comme un Congé
	  */
	public boolean isConge()
	{
		Object oo = get_Value(COLUMNNAME_IsConge);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Congé Traité.
		@param IsCongeTraite Congé Traité
	*/
	public void setIsCongeTraite (boolean IsCongeTraite)
	{
		set_Value (COLUMNNAME_IsCongeTraite, Boolean.valueOf(IsCongeTraite));
	}

	/** Get Congé Traité.
		@return Congé Traité
	  */
	public boolean isCongeTraite()
	{
		Object oo = get_Value(COLUMNNAME_IsCongeTraite);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Peut conduire à une Demande d&#039;Explication.
		@param IsDemandeExplication Peut conduire à une Demande d&#039;Explication
	*/
	public void setIsDemandeExplication (boolean IsDemandeExplication)
	{
		set_Value (COLUMNNAME_IsDemandeExplication, Boolean.valueOf(IsDemandeExplication));
	}

	/** Get Peut conduire à une Demande d&#039;Explication.
		@return Peut conduire à une Demande d&#039;Explication
	  */
	public boolean isDemandeExplication()
	{
		Object oo = get_Value(COLUMNNAME_IsDemandeExplication);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Demande d&#039;Explication Traitée.
		@param IsDemandeExplicationTraite Demande d&#039;Explication Traitée
	*/
	public void setIsDemandeExplicationTraite (boolean IsDemandeExplicationTraite)
	{
		set_Value (COLUMNNAME_IsDemandeExplicationTraite, Boolean.valueOf(IsDemandeExplicationTraite));
	}

	/** Get Demande d&#039;Explication Traitée.
		@return Demande d&#039;Explication Traitée
	  */
	public boolean isDemandeExplicationTraite()
	{
		Object oo = get_Value(COLUMNNAME_IsDemandeExplicationTraite);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Alerte Affiché.
		@param IsMessageAlerteDisplayed Message d&#039;Alerte Affiché
	*/
	public void setIsMessageAlerteDisplayed (boolean IsMessageAlerteDisplayed)
	{
		set_Value (COLUMNNAME_IsMessageAlerteDisplayed, Boolean.valueOf(IsMessageAlerteDisplayed));
	}

	/** Get Message d&#039;Alerte Affiché.
		@return Message d&#039;Alerte Affiché
	  */
	public boolean isMessageAlerteDisplayed()
	{
		Object oo = get_Value(COLUMNNAME_IsMessageAlerteDisplayed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Message d&#039;Alerte.
		@param Message_Alerte Message d&#039;Alerte
	*/
	public void setMessage_Alerte (String Message_Alerte)
	{
		set_Value (COLUMNNAME_Message_Alerte, Message_Alerte);
	}

	/** Get Message d&#039;Alerte.
		@return Message d&#039;Alerte
	  */
	public String getMessage_Alerte()
	{
		return (String)get_Value(COLUMNNAME_Message_Alerte);
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