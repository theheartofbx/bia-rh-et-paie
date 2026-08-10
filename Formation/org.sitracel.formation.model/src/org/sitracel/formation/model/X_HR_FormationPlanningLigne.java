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

/** Generated Model for HR_FormationPlanningLigne
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_FormationPlanningLigne")
public class X_HR_FormationPlanningLigne extends PO implements I_HR_FormationPlanningLigne, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260810L;

    /** Standard Constructor */
    public X_HR_FormationPlanningLigne (Properties ctx, int HR_FormationPlanningLigne_ID, String trxName)
    {
      super (ctx, HR_FormationPlanningLigne_ID, trxName);
      /** if (HR_FormationPlanningLigne_ID == 0)
        {
			setHR_FormationPlanningLigne_ID (0);
			setHR_FormationPlanning_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationPlanningLigne (Properties ctx, int HR_FormationPlanningLigne_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationPlanningLigne_ID, trxName, virtualColumns);
      /** if (HR_FormationPlanningLigne_ID == 0)
        {
			setHR_FormationPlanningLigne_ID (0);
			setHR_FormationPlanning_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationPlanningLigne (Properties ctx, String HR_FormationPlanningLigne_UU, String trxName)
    {
      super (ctx, HR_FormationPlanningLigne_UU, trxName);
      /** if (HR_FormationPlanningLigne_UU == null)
        {
			setHR_FormationPlanningLigne_ID (0);
			setHR_FormationPlanning_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationPlanningLigne (Properties ctx, String HR_FormationPlanningLigne_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationPlanningLigne_UU, trxName, virtualColumns);
      /** if (HR_FormationPlanningLigne_UU == null)
        {
			setHR_FormationPlanningLigne_ID (0);
			setHR_FormationPlanning_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_FormationPlanningLigne (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_FormationPlanningLigne[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date.
		@param Date_Planning Date
	*/
	public void setDate_Planning (Timestamp Date_Planning)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Planning, Date_Planning);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getDate_Planning()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Planning);
	}

	public org.compiere.model.I_C_BPartner getEncadrant() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getEncadrant_ID(), get_TrxName());
	}

	/** Set Encadrant.
		@param Encadrant_ID Encadrant
	*/
	public void setEncadrant_ID (int Encadrant_ID)
	{
		if (Encadrant_ID < 1)
			set_Value (COLUMNNAME_Encadrant_ID, null);
		else
			set_Value (COLUMNNAME_Encadrant_ID, Integer.valueOf(Encadrant_ID));
	}

	/** Get Encadrant.
		@return Encadrant
	  */
	public int getEncadrant_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Encadrant_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_FormationModule getHR_FormationModule() throws RuntimeException
	{
		return (I_HR_FormationModule)MTable.get(getCtx(), I_HR_FormationModule.Table_ID)
			.getPO(getHR_FormationModule_ID(), get_TrxName());
	}

	/** Set Modules de Formation.
		@param HR_FormationModule_ID Modules de Formation
	*/
	public void setHR_FormationModule_ID (int HR_FormationModule_ID)
	{
		if (HR_FormationModule_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationModule_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationModule_ID, Integer.valueOf(HR_FormationModule_ID));
	}

	/** Get Modules de Formation.
		@return Modules de Formation	  */
	public int getHR_FormationModule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationModule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lignes Planning de Formation.
		@param HR_FormationPlanningLigne_ID Lignes Planning de Formation
	*/
	public void setHR_FormationPlanningLigne_ID (int HR_FormationPlanningLigne_ID)
	{
		if (HR_FormationPlanningLigne_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanningLigne_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanningLigne_ID, Integer.valueOf(HR_FormationPlanningLigne_ID));
	}

	/** Get Lignes Planning de Formation.
		@return Lignes Planning de Formation	  */
	public int getHR_FormationPlanningLigne_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationPlanningLigne_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_FormationPlanningLigne_UU.
		@param HR_FormationPlanningLigne_UU HR_FormationPlanningLigne_UU
	*/
	public void setHR_FormationPlanningLigne_UU (String HR_FormationPlanningLigne_UU)
	{
		set_Value (COLUMNNAME_HR_FormationPlanningLigne_UU, HR_FormationPlanningLigne_UU);
	}

	/** Get HR_FormationPlanningLigne_UU.
		@return HR_FormationPlanningLigne_UU	  */
	public String getHR_FormationPlanningLigne_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_FormationPlanningLigne_UU);
	}

	public I_HR_FormationPlanning getHR_FormationPlanning() throws RuntimeException
	{
		return (I_HR_FormationPlanning)MTable.get(getCtx(), I_HR_FormationPlanning.Table_ID)
			.getPO(getHR_FormationPlanning_ID(), get_TrxName());
	}

	/** Set Planning de Formation.
		@param HR_FormationPlanning_ID Planning de Formation
	*/
	public void setHR_FormationPlanning_ID (int HR_FormationPlanning_ID)
	{
		if (HR_FormationPlanning_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanning_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationPlanning_ID, Integer.valueOf(HR_FormationPlanning_ID));
	}

	/** Get Planning de Formation.
		@return Planning de Formation	  */
	public int getHR_FormationPlanning_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationPlanning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_FormationProgramme getHR_FormationProgramme() throws RuntimeException
	{
		return (I_HR_FormationProgramme)MTable.get(getCtx(), I_HR_FormationProgramme.Table_ID)
			.getPO(getHR_FormationProgramme_ID(), get_TrxName());
	}

	/** Set Programme de Formation.
		@param HR_FormationProgramme_ID Programme de Formation
	*/
	public void setHR_FormationProgramme_ID (int HR_FormationProgramme_ID)
	{
		if (HR_FormationProgramme_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationProgramme_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationProgramme_ID, Integer.valueOf(HR_FormationProgramme_ID));
	}

	/** Get Programme de Formation.
		@return Programme de Formation	  */
	public int getHR_FormationProgramme_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationProgramme_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Heure de Début.
		@param Heure_Debut Heure de Début
	*/
	public void setHeure_Debut (Timestamp Heure_Debut)
	{
		set_Value (COLUMNNAME_Heure_Debut, Heure_Debut);
	}

	/** Get Heure de Début.
		@return Heure de Début
	  */
	public Timestamp getHeure_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Heure_Debut);
	}

	/** Set Heure de Fin.
		@param Heure_Fin Heure de Fin
	*/
	public void setHeure_Fin (Timestamp Heure_Fin)
	{
		set_Value (COLUMNNAME_Heure_Fin, Heure_Fin);
	}

	/** Get Heure de Fin.
		@return Heure de Fin
	  */
	public Timestamp getHeure_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Heure_Fin);
	}

	/** Set Ok.
		@param IsOk Ok
	*/
	public void setIsOk (boolean IsOk)
	{
		set_Value (COLUMNNAME_IsOk, Boolean.valueOf(IsOk));
	}

	/** Get Ok.
		@return Ok	  */
	public boolean isOk()
	{
		Object oo = get_Value(COLUMNNAME_IsOk);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lieu.
		@param Lieu Lieu
	*/
	public void setLieu (String Lieu)
	{
		set_Value (COLUMNNAME_Lieu, Lieu);
	}

	/** Get Lieu.
		@return Lieu	  */
	public String getLieu()
	{
		return (String)get_Value(COLUMNNAME_Lieu);
	}

	/** Set Partie N°.
		@param Numero_Partie Partie N°
	*/
	public void setNumero_Partie (int Numero_Partie)
	{
		set_Value (COLUMNNAME_Numero_Partie, Integer.valueOf(Numero_Partie));
	}

	/** Get Partie N°.
		@return Partie N°
	  */
	public int getNumero_Partie()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Numero_Partie);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Observation.
		@param Observation Observation
	*/
	public void setObservation (String Observation)
	{
		set_Value (COLUMNNAME_Observation, Observation);
	}

	/** Get Observation.
		@return Observation	  */
	public String getObservation()
	{
		return (String)get_Value(COLUMNNAME_Observation);
	}

	/** Set Theme.
		@param Theme Theme name
	*/
	public void setTheme (String Theme)
	{
		set_Value (COLUMNNAME_Theme, Theme);
	}

	/** Get Theme.
		@return Theme name
	  */
	public String getTheme()
	{
		return (String)get_Value(COLUMNNAME_Theme);
	}
}