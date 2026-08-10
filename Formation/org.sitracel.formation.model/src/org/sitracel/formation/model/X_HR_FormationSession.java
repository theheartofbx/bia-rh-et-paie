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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_FormationSession
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_FormationSession")
public class X_HR_FormationSession extends PO implements I_HR_FormationSession, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260810L;

    /** Standard Constructor */
    public X_HR_FormationSession (Properties ctx, int HR_FormationSession_ID, String trxName)
    {
      super (ctx, HR_FormationSession_ID, trxName);
      /** if (HR_FormationSession_ID == 0)
        {
			setHR_FormationSession_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationSession (Properties ctx, int HR_FormationSession_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationSession_ID, trxName, virtualColumns);
      /** if (HR_FormationSession_ID == 0)
        {
			setHR_FormationSession_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationSession (Properties ctx, String HR_FormationSession_UU, String trxName)
    {
      super (ctx, HR_FormationSession_UU, trxName);
      /** if (HR_FormationSession_UU == null)
        {
			setHR_FormationSession_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationSession (Properties ctx, String HR_FormationSession_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationSession_UU, trxName, virtualColumns);
      /** if (HR_FormationSession_UU == null)
        {
			setHR_FormationSession_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_FormationSession (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_FormationSession[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	public I_HR_FormationCatalogue getHR_FormationCatalogue() throws RuntimeException
	{
		return (I_HR_FormationCatalogue)MTable.get(getCtx(), I_HR_FormationCatalogue.Table_ID)
			.getPO(getHR_FormationCatalogue_ID(), get_TrxName());
	}

	/** Set Catalogue de Formation.
		@param HR_FormationCatalogue_ID Catalogue de Formation
	*/
	public void setHR_FormationCatalogue_ID (int HR_FormationCatalogue_ID)
	{
		if (HR_FormationCatalogue_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationCatalogue_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationCatalogue_ID, Integer.valueOf(HR_FormationCatalogue_ID));
	}

	/** Get Catalogue de Formation.
		@return Catalogue de Formation	  */
	public int getHR_FormationCatalogue_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationCatalogue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_FormationSessionStatut getHR_FormationSessionStatut() throws RuntimeException
	{
		return (I_HR_FormationSessionStatut)MTable.get(getCtx(), I_HR_FormationSessionStatut.Table_ID)
			.getPO(getHR_FormationSessionStatut_ID(), get_TrxName());
	}

	/** Set Statut de la Session de Formation.
		@param HR_FormationSessionStatut_ID Statut de la Session de Formation
	*/
	public void setHR_FormationSessionStatut_ID (int HR_FormationSessionStatut_ID)
	{
		if (HR_FormationSessionStatut_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationSessionStatut_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationSessionStatut_ID, Integer.valueOf(HR_FormationSessionStatut_ID));
	}

	/** Get Statut de la Session de Formation.
		@return Statut de la Session de Formation	  */
	public int getHR_FormationSessionStatut_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationSessionStatut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set HR_FormationSession_UU.
		@param HR_FormationSession_UU HR_FormationSession_UU
	*/
	public void setHR_FormationSession_UU (String HR_FormationSession_UU)
	{
		set_Value (COLUMNNAME_HR_FormationSession_UU, HR_FormationSession_UU);
	}

	/** Get HR_FormationSession_UU.
		@return HR_FormationSession_UU	  */
	public String getHR_FormationSession_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_FormationSession_UU);
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

	/** Set Nombre de Places.
		@param Nombre_Places Nombre de Places
	*/
	public void setNombre_Places (int Nombre_Places)
	{
		set_Value (COLUMNNAME_Nombre_Places, Integer.valueOf(Nombre_Places));
	}

	/** Get Nombre de Places.
		@return Nombre de Places
	  */
	public int getNombre_Places()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Places);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Peut Valider.
		@param PeutValider Peut Valider
	*/
	public void setPeutValider (boolean PeutValider)
	{
		set_ValueNoCheck (COLUMNNAME_PeutValider, Boolean.valueOf(PeutValider));
	}

	/** Get Peut Valider.
		@return Peut Valider
	  */
	public boolean isPeutValider()
	{
		Object oo = get_Value(COLUMNNAME_PeutValider);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rejeter.
		@param Rejeter Rejeter
	*/
	public void setRejeter (String Rejeter)
	{
		set_Value (COLUMNNAME_Rejeter, Rejeter);
	}

	/** Get Rejeter.
		@return Rejeter
	  */
	public String getRejeter()
	{
		return (String)get_Value(COLUMNNAME_Rejeter);
	}

	public org.compiere.model.I_C_BPartner getResponsable() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getResponsable_ID(), get_TrxName());
	}

	/** Set Responsable.
		@param Responsable_ID Responsable
	*/
	public void setResponsable_ID (int Responsable_ID)
	{
		if (Responsable_ID < 1)
			set_Value (COLUMNNAME_Responsable_ID, null);
		else
			set_Value (COLUMNNAME_Responsable_ID, Integer.valueOf(Responsable_ID));
	}

	/** Get Responsable.
		@return Responsable
	  */
	public int getResponsable_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Responsable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Valider .
		@param Valider Valider 
	*/
	public void setValider (String Valider)
	{
		set_Value (COLUMNNAME_Valider, Valider);
	}

	/** Get Valider .
		@return Valider 
	  */
	public String getValider()
	{
		return (String)get_Value(COLUMNNAME_Valider);
	}
}