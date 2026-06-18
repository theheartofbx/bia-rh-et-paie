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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for HR_OffreEmploi
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_OffreEmploi")
public class X_HR_OffreEmploi extends PO implements I_HR_OffreEmploi, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241208L;

    /** Standard Constructor */
    public X_HR_OffreEmploi (Properties ctx, int HR_OffreEmploi_ID, String trxName)
    {
      super (ctx, HR_OffreEmploi_ID, trxName);
      /** if (HR_OffreEmploi_ID == 0)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreEmploi_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreEmploi (Properties ctx, int HR_OffreEmploi_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreEmploi_ID, trxName, virtualColumns);
      /** if (HR_OffreEmploi_ID == 0)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreEmploi_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreEmploi (Properties ctx, String HR_OffreEmploi_UU, String trxName)
    {
      super (ctx, HR_OffreEmploi_UU, trxName);
      /** if (HR_OffreEmploi_UU == null)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreEmploi_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreEmploi (Properties ctx, String HR_OffreEmploi_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreEmploi_UU, trxName, virtualColumns);
      /** if (HR_OffreEmploi_UU == null)
        {
			setDate_Creation (new Timestamp( System.currentTimeMillis() ));
			setEmis_Par_Matricule (null);
			setEmis_Par_Nom_ID (0);
			setEmis_Par_Poste_ID (0);
			setHR_OffreEmploi_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_OffreEmploi (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_OffreEmploi[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Date de Création.
		@param Date_Creation Date de Création
	*/
	@Override
	public void setDate_Creation (Timestamp Date_Creation)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Creation, Date_Creation);
	}

	/** Get Date de Création.
		@return Date de Création	  */
	@Override
	public Timestamp getDate_Creation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Creation);
	}

	/** Set Date de Rejet.
		@param Date_Rejet Date de Rejet
	*/
	@Override
	public void setDate_Rejet (Timestamp Date_Rejet)
	{
		set_Value (COLUMNNAME_Date_Rejet, Date_Rejet);
	}

	/** Get Date de Rejet.
		@return Date de Rejet
	  */
	@Override
	public Timestamp getDate_Rejet()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Rejet);
	}

	/** Set Date de Validation.
		@param Date_Validation Date de Validation
	*/
	@Override
	public void setDate_Validation (Timestamp Date_Validation)
	{
		set_Value (COLUMNNAME_Date_Validation, Date_Validation);
	}

	/** Get Date de Validation.
		@return Date de Validation
	  */
	@Override
	public Timestamp getDate_Validation()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Validation);
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

	/** Set Matricule Emetteur.
		@param Emis_Par_Matricule Matricule Emetteur
	*/
	@Override
	public void setEmis_Par_Matricule (String Emis_Par_Matricule)
	{
		set_Value (COLUMNNAME_Emis_Par_Matricule, Emis_Par_Matricule);
	}

	/** Get Matricule Emetteur.
		@return Matricule Emetteur
	  */
	@Override
	public String getEmis_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Emis_Par_Matricule);
	}

	@Override
	public org.compiere.model.I_C_BPartner getEmis_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEmis_Par_Nom_ID(), get_TrxName());
	}

	/** Set Nom Emetteur.
		@param Emis_Par_Nom_ID Nom Emetteur
	*/
	@Override
	public void setEmis_Par_Nom_ID (int Emis_Par_Nom_ID)
	{
		if (Emis_Par_Nom_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_Emis_Par_Nom_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_Emis_Par_Nom_ID, Integer.valueOf(Emis_Par_Nom_ID));
		}
	}

	/** Get Nom Emetteur.
		@return Nom Emetteur
	  */
	@Override
	public int getEmis_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emis_Par_Nom_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_HR_Job getEmis_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getEmis_Par_Poste_ID(), get_TrxName());
	}

	/** Set Poste Emetteur.
		@param Emis_Par_Poste_ID Poste Emetteur
	*/
	@Override
	public void setEmis_Par_Poste_ID (int Emis_Par_Poste_ID)
	{
		if (Emis_Par_Poste_ID < 1) {
			set_Value (COLUMNNAME_Emis_Par_Poste_ID, null);
		} else {
			set_Value (COLUMNNAME_Emis_Par_Poste_ID, Integer.valueOf(Emis_Par_Poste_ID));
		}
	}

	/** Get Poste Emetteur.
		@return Poste Emetteur
	  */
	@Override
	public int getEmis_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Emis_Par_Poste_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
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

	/** Set HR_OffreEmploi_UU.
		@param HR_OffreEmploi_UU HR_OffreEmploi_UU
	*/
	@Override
	public void setHR_OffreEmploi_UU (String HR_OffreEmploi_UU)
	{
		set_Value (COLUMNNAME_HR_OffreEmploi_UU, HR_OffreEmploi_UU);
	}

	/** Get HR_OffreEmploi_UU.
		@return HR_OffreEmploi_UU	  */
	@Override
	public String getHR_OffreEmploi_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_OffreEmploi_UU);
	}

	/** Set Rejeté(e).
		@param IsRejetee Rejeté(e)
	*/
	@Override
	public void setIsRejetee (boolean IsRejetee)
	{
		set_Value (COLUMNNAME_IsRejetee, Boolean.valueOf(IsRejetee));
	}

	/** Get Rejeté(e).
		@return Rejeté(e)
	  */
	@Override
	public boolean isRejetee()
	{
		Object oo = get_Value(COLUMNNAME_IsRejetee);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Validé(e).
		@param IsValidee Validé(e)
	*/
	@Override
	public void setIsValidee (boolean IsValidee)
	{
		set_Value (COLUMNNAME_IsValidee, Boolean.valueOf(IsValidee));
	}

	/** Get Validé(e).
		@return Validé(e)
	  */
	@Override
	public boolean isValidee()
	{
		Object oo = get_Value(COLUMNNAME_IsValidee);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
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

	@Override
	public org.eevolution.model.I_HR_Job getPoste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getPoste_ID(), get_TrxName());
	}

	/** Set Nom du Poste :.
		@param Poste_ID Nom du Poste :
	*/
	@Override
	public void setPoste_ID (int Poste_ID)
	{
		if (Poste_ID < 1) {
			set_Value (COLUMNNAME_Poste_ID, null);
		} else {
			set_Value (COLUMNNAME_Poste_ID, Integer.valueOf(Poste_ID));
		}
	}

	/** Get Nom du Poste :.
		@return Nom du Poste :
	  */
	@Override
	public int getPoste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Poste_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Rejeter.
		@param Rejeter Rejeter
	*/
	@Override
	public void setRejeter (String Rejeter)
	{
		set_Value (COLUMNNAME_Rejeter, Rejeter);
	}

	/** Get Rejeter.
		@return Rejeter
	  */
	@Override
	public String getRejeter()
	{
		return (String)get_Value(COLUMNNAME_Rejeter);
	}

	/** Set Validé/rejeté par (Matricule) :.
		@param Valide_Rejete_Par_Matricule Validé/rejeté par (Matricule) :
	*/
	@Override
	public void setValide_Rejete_Par_Matricule (String Valide_Rejete_Par_Matricule)
	{
		set_Value (COLUMNNAME_Valide_Rejete_Par_Matricule, Valide_Rejete_Par_Matricule);
	}

	/** Get Validé/rejeté par (Matricule) :.
		@return Validé/rejeté par (Matricule) :
	  */
	@Override
	public String getValide_Rejete_Par_Matricule()
	{
		return (String)get_Value(COLUMNNAME_Valide_Rejete_Par_Matricule);
	}

	@Override
	public org.compiere.model.I_C_BPartner getValide_Rejete_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getValide_Rejete_Par_Nom_ID(), get_TrxName());
	}

	/** Set Validé/rejeté par (Nom) :.
		@param Valide_Rejete_Par_Nom_ID Validé/rejeté par (Nom) :
	*/
	@Override
	public void setValide_Rejete_Par_Nom_ID (int Valide_Rejete_Par_Nom_ID)
	{
		if (Valide_Rejete_Par_Nom_ID < 1) {
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, null);
		} else {
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, Integer.valueOf(Valide_Rejete_Par_Nom_ID));
		}
	}

	/** Get Validé/rejeté par (Nom) :.
		@return Validé/rejeté par (Nom) :
	  */
	@Override
	public int getValide_Rejete_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valide_Rejete_Par_Nom_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_HR_Job getValide_Rejete_Par_Poste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getValide_Rejete_Par_Poste_ID(), get_TrxName());
	}

	/** Set Validé/rejeté par (Poste) :.
		@param Valide_Rejete_Par_Poste_ID Validé/rejeté par (Poste) :
	*/
	@Override
	public void setValide_Rejete_Par_Poste_ID (int Valide_Rejete_Par_Poste_ID)
	{
		if (Valide_Rejete_Par_Poste_ID < 1) {
			set_Value (COLUMNNAME_Valide_Rejete_Par_Poste_ID, null);
		} else {
			set_Value (COLUMNNAME_Valide_Rejete_Par_Poste_ID, Integer.valueOf(Valide_Rejete_Par_Poste_ID));
		}
	}

	/** Get Validé/rejeté par (Poste) :.
		@return Validé/rejeté par (Poste) :
	  */
	@Override
	public int getValide_Rejete_Par_Poste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valide_Rejete_Par_Poste_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Valider .
		@param Valider Valider
	*/
	@Override
	public void setValider (String Valider)
	{
		set_Value (COLUMNNAME_Valider, Valider);
	}

	/** Get Valider .
		@return Valider
	  */
	@Override
	public String getValider()
	{
		return (String)get_Value(COLUMNNAME_Valider);
	}

	/** Set Nécessite une/des Compétence(s).
		@param getCompetence Nécessite une/des Compétence(s)
	*/
	@Override
	public void setgetCompetence (boolean getCompetence)
	{
		set_Value (COLUMNNAME_getCompetence, Boolean.valueOf(getCompetence));
	}

	/** Get Nécessite une/des Compétence(s).
		@return Nécessite une/des Compétence(s)	  */
	@Override
	public boolean isgetCompetence()
	{
		Object oo = get_Value(COLUMNNAME_getCompetence);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nécessite un Niveau d&#039;Étude.
		@param getEtude Nécessite un Niveau d&#039;Étude
	*/
	@Override
	public void setgetEtude (boolean getEtude)
	{
		set_Value (COLUMNNAME_getEtude, Boolean.valueOf(getEtude));
	}

	/** Get Nécessite un Niveau d&#039;Étude.
		@return Nécessite un Niveau d&#039;Étude	  */
	@Override
	public boolean isgetEtude()
	{
		Object oo = get_Value(COLUMNNAME_getEtude);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nécessite une/des Expérience(s).
		@param getExperience Nécessite une/des Expérience(s)
	*/
	@Override
	public void setgetExperience (boolean getExperience)
	{
		set_Value (COLUMNNAME_getExperience, Boolean.valueOf(getExperience));
	}

	/** Get Nécessite une/des Expérience(s).
		@return Nécessite une/des Expérience(s)	  */
	@Override
	public boolean isgetExperience()
	{
		Object oo = get_Value(COLUMNNAME_getExperience);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}
}