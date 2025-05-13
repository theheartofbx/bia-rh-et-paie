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

/** Generated Model for HR_Gestion_Presence
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Gestion_Presence")
public class X_HR_Gestion_Presence extends PO implements I_HR_Gestion_Presence, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250413L;

    /** Standard Constructor */
    public X_HR_Gestion_Presence (Properties ctx, int HR_Gestion_Presence_ID, String trxName)
    {
      super (ctx, HR_Gestion_Presence_ID, trxName);
      /** if (HR_Gestion_Presence_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Gestion_Presence_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Gestion_Presence (Properties ctx, int HR_Gestion_Presence_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Gestion_Presence_ID, trxName, virtualColumns);
      /** if (HR_Gestion_Presence_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Gestion_Presence_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Gestion_Presence (Properties ctx, String HR_Gestion_Presence_UU, String trxName)
    {
      super (ctx, HR_Gestion_Presence_UU, trxName);
      /** if (HR_Gestion_Presence_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Gestion_Presence_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Gestion_Presence (Properties ctx, String HR_Gestion_Presence_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Gestion_Presence_UU, trxName, virtualColumns);
      /** if (HR_Gestion_Presence_UU == null)
        {
			setC_BPartner_ID (0);
			setHR_Gestion_Presence_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Gestion_Presence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Gestion_Presence[")
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

	/** Set Gestion de la Présence.
		@param HR_Gestion_Presence_ID Gestion de la Présence
	*/
	public void setHR_Gestion_Presence_ID (int HR_Gestion_Presence_ID)
	{
		if (HR_Gestion_Presence_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Gestion_Presence_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Gestion_Presence_ID, Integer.valueOf(HR_Gestion_Presence_ID));
	}

	/** Get Gestion de la Présence.
		@return Gestion de la Présence	  */
	public int getHR_Gestion_Presence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Gestion_Presence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Gestion_Presence_UU.
		@param HR_Gestion_Presence_UU HR_Gestion_Presence_UU
	*/
	public void setHR_Gestion_Presence_UU (String HR_Gestion_Presence_UU)
	{
		set_Value (COLUMNNAME_HR_Gestion_Presence_UU, HR_Gestion_Presence_UU);
	}

	/** Get HR_Gestion_Presence_UU.
		@return HR_Gestion_Presence_UU	  */
	public String getHR_Gestion_Presence_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Gestion_Presence_UU);
	}

	public I_HR_Periode_Salariale getHR_Periode_Salariale() throws RuntimeException
	{
		return (I_HR_Periode_Salariale)MTable.get(getCtx(), I_HR_Periode_Salariale.Table_ID)
			.getPO(getHR_Periode_Salariale_ID(), get_TrxName());
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

	/** Set Nombre de Jour Avant le Début du Contrat.
		@param Nombre_Jour_Avant_DebutContrat Nombre de Jour Avant le Début du Contrat
	*/
	public void setNombre_Jour_Avant_DebutContrat (int Nombre_Jour_Avant_DebutContrat)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Avant_DebutContrat, Integer.valueOf(Nombre_Jour_Avant_DebutContrat));
	}

	/** Get Nombre de Jour Avant le Début du Contrat.
		@return Nombre de Jour Avant le Début du Contrat
	  */
	public int getNombre_Jour_Avant_DebutContrat()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Avant_DebutContrat);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé Annuel.
		@param Nombre_Jour_Conge_Annuel Nombre de Jour de Congé Annuel
	*/
	public void setNombre_Jour_Conge_Annuel (int Nombre_Jour_Conge_Annuel)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Conge_Annuel, Integer.valueOf(Nombre_Jour_Conge_Annuel));
	}

	/** Get Nombre de Jour de Congé Annuel.
		@return Nombre de Jour de Congé Annuel
	  */
	public int getNombre_Jour_Conge_Annuel()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Conge_Annuel);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé Maternité.
		@param Nombre_Jour_Conge_Maternite Nombre de Jour de Congé Maternité
	*/
	public void setNombre_Jour_Conge_Maternite (int Nombre_Jour_Conge_Maternite)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Conge_Maternite, Integer.valueOf(Nombre_Jour_Conge_Maternite));
	}

	/** Get Nombre de Jour de Congé Maternité.
		@return Nombre de Jour de Congé Maternité
	  */
	public int getNombre_Jour_Conge_Maternite()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Conge_Maternite);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Congé Paternité.
		@param Nombre_Jour_Conge_Paternite Nombre de Jour de Congé Paternité
	*/
	public void setNombre_Jour_Conge_Paternite (int Nombre_Jour_Conge_Paternite)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Conge_Paternite, Integer.valueOf(Nombre_Jour_Conge_Paternite));
	}

	/** Get Nombre de Jour de Congé Paternité.
		@return Nombre de Jour de Congé Paternité
	  */
	public int getNombre_Jour_Conge_Paternite()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Conge_Paternite);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour Effectif.
		@param Nombre_Jour_Effectif Nombre de Jour Effectif
	*/
	public void setNombre_Jour_Effectif (int Nombre_Jour_Effectif)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Effectif, Integer.valueOf(Nombre_Jour_Effectif));
	}

	/** Get Nombre de Jour Effectif.
		@return Nombre de Jour Effectif
	  */
	public int getNombre_Jour_Effectif()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Effectif);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour Max.
		@param Nombre_Jour_Max Nombre de Jour Max
	*/
	public void setNombre_Jour_Max (int Nombre_Jour_Max)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Max, Integer.valueOf(Nombre_Jour_Max));
	}

	/** Get Nombre de Jour Max.
		@return Nombre de Jour Max
	  */
	public int getNombre_Jour_Max()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Max);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour de Suspension.
		@param Nombre_Jour_Suspension Nombre de Jour de Suspension
	*/
	public void setNombre_Jour_Suspension (int Nombre_Jour_Suspension)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Suspension, Integer.valueOf(Nombre_Jour_Suspension));
	}

	/** Get Nombre de Jour de Suspension.
		@return Nombre de Jour de Suspension
	  */
	public int getNombre_Jour_Suspension()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Suspension);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}