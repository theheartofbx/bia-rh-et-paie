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

import org.sitracel.model.I_HR_Ampliation;
import org.sitracel.absence.model.I_HR_Absence;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Demande_Explication
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Demande_Explication")
public class X_HR_Demande_Explication extends PO implements I_HR_Demande_Explication, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260702L;

    /** Standard Constructor */
    public X_HR_Demande_Explication (Properties ctx, int HR_Demande_Explication_ID, String trxName)
    {
      super (ctx, HR_Demande_Explication_ID, trxName);
      /** if (HR_Demande_Explication_ID == 0)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Delai_Reponse_ID (0);
			setHR_Demande_Explication_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Demande_Explication (Properties ctx, int HR_Demande_Explication_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Demande_Explication_ID, trxName, virtualColumns);
      /** if (HR_Demande_Explication_ID == 0)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Delai_Reponse_ID (0);
			setHR_Demande_Explication_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Demande_Explication (Properties ctx, String HR_Demande_Explication_UU, String trxName)
    {
      super (ctx, HR_Demande_Explication_UU, trxName);
      /** if (HR_Demande_Explication_UU == null)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Delai_Reponse_ID (0);
			setHR_Demande_Explication_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Demande_Explication (Properties ctx, String HR_Demande_Explication_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Demande_Explication_UU, trxName, virtualColumns);
      /** if (HR_Demande_Explication_UU == null)
        {
			setC_BPartner_ID (0);
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_Delai_Reponse_ID (0);
			setHR_Demande_Explication_ID (0);
			setMatricule_Employe (null);
			setMotif_Demande_Explication (null);
			setName (null);
			setPoste_Employe_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Demande_Explication (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Demande_Explication[")
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

	/** Set Date de Réponse.
		@param Date_Reponse Date de Réponse
	*/
	public void setDate_Reponse (Timestamp Date_Reponse)
	{
		set_Value (COLUMNNAME_Date_Reponse, Date_Reponse);
	}

	/** Get Date de Réponse.
		@return Date de Réponse
	  */
	public Timestamp getDate_Reponse()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Reponse);
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

	public I_HR_Absence getHR_Absence() throws RuntimeException
	{
		return (I_HR_Absence)MTable.get(getCtx(), I_HR_Absence.Table_ID)
			.getPO(getHR_Absence_ID(), get_TrxName());
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

	public I_HR_Ampliation getHR_Ampliation() throws RuntimeException
	{
		return (I_HR_Ampliation)MTable.get(getCtx(), I_HR_Ampliation.Table_ID)
			.getPO(getHR_Ampliation_ID(), get_TrxName());
	}

	/** Set Ampliation.
		@param HR_Ampliation_ID Ampliation
	*/
	public void setHR_Ampliation_ID (int HR_Ampliation_ID)
	{
		if (HR_Ampliation_ID < 1)
			set_Value (COLUMNNAME_HR_Ampliation_ID, null);
		else
			set_Value (COLUMNNAME_HR_Ampliation_ID, Integer.valueOf(HR_Ampliation_ID));
	}

	/** Get Ampliation.
		@return Ampliation	  */
	public int getHR_Ampliation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Ampliation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Delai_Reponse getHR_Delai_Reponse() throws RuntimeException
	{
		return (I_HR_Delai_Reponse)MTable.get(getCtx(), I_HR_Delai_Reponse.Table_ID)
			.getPO(getHR_Delai_Reponse_ID(), get_TrxName());
	}

	/** Set Délai de Réponse.
		@param HR_Delai_Reponse_ID Délai de Réponse
	*/
	public void setHR_Delai_Reponse_ID (int HR_Delai_Reponse_ID)
	{
		if (HR_Delai_Reponse_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Delai_Reponse_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Delai_Reponse_ID, Integer.valueOf(HR_Delai_Reponse_ID));
	}

	/** Get Délai de Réponse.
		@return Délai de Réponse	  */
	public int getHR_Delai_Reponse_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Delai_Reponse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set HR_Demande_Explication_UU.
		@param HR_Demande_Explication_UU HR_Demande_Explication_UU
	*/
	public void setHR_Demande_Explication_UU (String HR_Demande_Explication_UU)
	{
		set_Value (COLUMNNAME_HR_Demande_Explication_UU, HR_Demande_Explication_UU);
	}

	/** Get HR_Demande_Explication_UU.
		@return HR_Demande_Explication_UU	  */
	public String getHR_Demande_Explication_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Demande_Explication_UU);
	}

	/** Set Initial.
		@param Initial Initial
	*/
	public void setInitial (String Initial)
	{
		set_Value (COLUMNNAME_Initial, Initial);
	}

	/** Get Initial.
		@return Initial
	  */
	public String getInitial()
	{
		return (String)get_Value(COLUMNNAME_Initial);
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

	/** Set Motif de la demande d&#039;Expliaction.
		@param Motif_Demande_Explication Motif de la demande d&#039;Expliaction
	*/
	public void setMotif_Demande_Explication (String Motif_Demande_Explication)
	{
		set_ValueNoCheck (COLUMNNAME_Motif_Demande_Explication, Motif_Demande_Explication);
	}

	/** Get Motif de la demande d&#039;Expliaction.
		@return Motif de la demande d&#039;Expliaction
	  */
	public String getMotif_Demande_Explication()
	{
		return (String)get_Value(COLUMNNAME_Motif_Demande_Explication);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Notifier.
		@param Notifier Notifier
	*/
	public void setNotifier (String Notifier)
	{
		set_Value (COLUMNNAME_Notifier, Notifier);
	}

	/** Get Notifier.
		@return Notifier
	  */
	public String getNotifier()
	{
		return (String)get_Value(COLUMNNAME_Notifier);
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

	/** Set Rapport.
		@param Rapport Rapport
	*/
	public void setRapport (String Rapport)
	{
		set_Value (COLUMNNAME_Rapport, Rapport);
	}

	/** Get Rapport.
		@return Rapport
	  */
	public String getRapport()
	{
		return (String)get_Value(COLUMNNAME_Rapport);
	}

	/** Set Réponse à la Demande d&#039;Explication.
		@param Reponse_Demande_Explication Réponse à la Demande d&#039;Explication
	*/
	public void setReponse_Demande_Explication (String Reponse_Demande_Explication)
	{
		set_Value (COLUMNNAME_Reponse_Demande_Explication, Reponse_Demande_Explication);
	}

	/** Get Réponse à la Demande d&#039;Explication.
		@return Réponse à la Demande d&#039;Explication
	  */
	public String getReponse_Demande_Explication()
	{
		return (String)get_Value(COLUMNNAME_Reponse_Demande_Explication);
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

	/** Set Personnaliser le Rapport.
		@param isRapport_Personnalise Personnaliser le Rapport
	*/
	public void setisRapport_Personnalise (boolean isRapport_Personnalise)
	{
		set_Value (COLUMNNAME_isRapport_Personnalise, Boolean.valueOf(isRapport_Personnalise));
	}

	/** Get Personnaliser le Rapport.
		@return Personnaliser le Rapport
	  */
	public boolean isRapport_Personnalise()
	{
		Object oo = get_Value(COLUMNNAME_isRapport_Personnalise);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}
}