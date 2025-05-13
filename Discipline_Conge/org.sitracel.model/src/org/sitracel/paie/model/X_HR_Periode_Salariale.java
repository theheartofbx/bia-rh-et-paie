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
package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Periode_Salariale
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Periode_Salariale")
public class X_HR_Periode_Salariale extends PO implements I_HR_Periode_Salariale, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250413L;

    /** Standard Constructor */
    public X_HR_Periode_Salariale (Properties ctx, int HR_Periode_Salariale_ID, String trxName)
    {
      super (ctx, HR_Periode_Salariale_ID, trxName);
      /** if (HR_Periode_Salariale_ID == 0)
        {
			setDate_Debut_Defaut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin_Defaut (new Timestamp( System.currentTimeMillis() ));
			setHR_Periode_Salariale_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Periode_Salariale (Properties ctx, int HR_Periode_Salariale_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Periode_Salariale_ID, trxName, virtualColumns);
      /** if (HR_Periode_Salariale_ID == 0)
        {
			setDate_Debut_Defaut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin_Defaut (new Timestamp( System.currentTimeMillis() ));
			setHR_Periode_Salariale_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Periode_Salariale (Properties ctx, String HR_Periode_Salariale_UU, String trxName)
    {
      super (ctx, HR_Periode_Salariale_UU, trxName);
      /** if (HR_Periode_Salariale_UU == null)
        {
			setDate_Debut_Defaut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin_Defaut (new Timestamp( System.currentTimeMillis() ));
			setHR_Periode_Salariale_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Periode_Salariale (Properties ctx, String HR_Periode_Salariale_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Periode_Salariale_UU, trxName, virtualColumns);
      /** if (HR_Periode_Salariale_UU == null)
        {
			setDate_Debut_Defaut (new Timestamp( System.currentTimeMillis() ));
			setDate_Fin_Defaut (new Timestamp( System.currentTimeMillis() ));
			setHR_Periode_Salariale_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Periode_Salariale (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Periode_Salariale[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set à Partir de (par défaut) :.
		@param Date_Debut_Defaut à Partir de (par défaut) :
	*/
	public void setDate_Debut_Defaut (Timestamp Date_Debut_Defaut)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut_Defaut, Date_Debut_Defaut);
	}

	/** Get à Partir de (par défaut) :.
		@return à Partir de (par défaut) :
	  */
	public Timestamp getDate_Debut_Defaut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut_Defaut);
	}

	/** Set Jusqu&#039;au (par défaut) :.
		@param Date_Fin_Defaut Jusqu&#039;au (par défaut) :
	*/
	public void setDate_Fin_Defaut (Timestamp Date_Fin_Defaut)
	{
		set_Value (COLUMNNAME_Date_Fin_Defaut, Date_Fin_Defaut);
	}

	/** Get Jusqu&#039;au (par défaut) :.
		@return Jusqu&#039;au (par défaut) :
	  */
	public Timestamp getDate_Fin_Defaut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin_Defaut);
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

	/** Set Période Salariale.
		@param HR_Periode_Salariale_ID Période Salariale
	*/
	public void setHR_Periode_Salariale_ID (int HR_Periode_Salariale_ID)
	{
		if (HR_Periode_Salariale_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Periode_Salariale_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Periode_Salariale_ID, Integer.valueOf(HR_Periode_Salariale_ID));
	}

	/** Get Période Salariale.
		@return Période Salariale	  */
	public int getHR_Periode_Salariale_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Periode_Salariale_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Periode_Salariale_UU.
		@param HR_Periode_Salariale_UU HR_Periode_Salariale_UU
	*/
	public void setHR_Periode_Salariale_UU (String HR_Periode_Salariale_UU)
	{
		set_Value (COLUMNNAME_HR_Periode_Salariale_UU, HR_Periode_Salariale_UU);
	}

	/** Get HR_Periode_Salariale_UU.
		@return HR_Periode_Salariale_UU	  */
	public String getHR_Periode_Salariale_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Periode_Salariale_UU);
	}

	/** Set Déduire les Jours Avant le Début du Contrat.
		@param IsAvantDebutContratDeduit Déduire les Jours Avant le Début du Contrat
	*/
	public void setIsAvantDebutContratDeduit (boolean IsAvantDebutContratDeduit)
	{
		set_Value (COLUMNNAME_IsAvantDebutContratDeduit, Boolean.valueOf(IsAvantDebutContratDeduit));
	}

	/** Get Déduire les Jours Avant le Début du Contrat.
		@return Déduire les Jours Avant le Début du Contrat
	  */
	public boolean isAvantDebutContratDeduit()
	{
		Object oo = get_Value(COLUMNNAME_IsAvantDebutContratDeduit);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Déduire les Jours de Congé Annuel.
		@param IsCongeAnnuelDeduit Déduire les Jours de Congé Annuel
	*/
	public void setIsCongeAnnuelDeduit (boolean IsCongeAnnuelDeduit)
	{
		set_Value (COLUMNNAME_IsCongeAnnuelDeduit, Boolean.valueOf(IsCongeAnnuelDeduit));
	}

	/** Get Déduire les Jours de Congé Annuel.
		@return Déduire les Jours de Congé Annuel
	  */
	public boolean isCongeAnnuelDeduit()
	{
		Object oo = get_Value(COLUMNNAME_IsCongeAnnuelDeduit);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Déduire les Jours de Congé Maternité/Paternité.
		@param IsCongeMatPatlDeduit Déduire les Jours de Congé Maternité/Paternité
	*/
	public void setIsCongeMatPatlDeduit (boolean IsCongeMatPatlDeduit)
	{
		set_Value (COLUMNNAME_IsCongeMatPatlDeduit, Boolean.valueOf(IsCongeMatPatlDeduit));
	}

	/** Get Déduire les Jours de Congé Maternité/Paternité.
		@return Déduire les Jours de Congé Maternité/Paternité
	  */
	public boolean isCongeMatPatlDeduit()
	{
		Object oo = get_Value(COLUMNNAME_IsCongeMatPatlDeduit);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gérer Automatiquement la Présence.
		@param IsGestionPresenceAuto Gérer Automatiquement la Présence
	*/
	public void setIsGestionPresenceAuto (boolean IsGestionPresenceAuto)
	{
		set_Value (COLUMNNAME_IsGestionPresenceAuto, Boolean.valueOf(IsGestionPresenceAuto));
	}

	/** Get Gérer Automatiquement la Présence.
		@return Gérer Automatiquement la Présence
	  */
	public boolean isGestionPresenceAuto()
	{
		Object oo = get_Value(COLUMNNAME_IsGestionPresenceAuto);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Déduire les Jours de Suspension.
		@param IsSuspensionDeduit Déduire les Jours de Suspension
	*/
	public void setIsSuspensionDeduit (boolean IsSuspensionDeduit)
	{
		set_Value (COLUMNNAME_IsSuspensionDeduit, Boolean.valueOf(IsSuspensionDeduit));
	}

	/** Get Déduire les Jours de Suspension.
		@return Déduire les Jours de Suspension
	  */
	public boolean isSuspensionDeduit()
	{
		Object oo = get_Value(COLUMNNAME_IsSuspensionDeduit);
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

	/** Set Nombre de Jour Salarial.
		@param Nombre_Jour_Salarial Nombre de Jour Salarial
	*/
	public void setNombre_Jour_Salarial (int Nombre_Jour_Salarial)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Salarial, Integer.valueOf(Nombre_Jour_Salarial));
	}

	/** Get Nombre de Jour Salarial.
		@return Nombre de Jour Salarial
	  */
	public int getNombre_Jour_Salarial()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Salarial);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}