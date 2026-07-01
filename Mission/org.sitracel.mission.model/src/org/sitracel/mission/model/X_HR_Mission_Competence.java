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

import org.sitracel.recrutement.model.I_HR_Competences;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Mission_Competence
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mission_Competence")
public class X_HR_Mission_Competence extends PO implements I_HR_Mission_Competence, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260630L;

    /** Standard Constructor */
    public X_HR_Mission_Competence (Properties ctx, int HR_Mission_Competence_ID, String trxName)
    {
      super (ctx, HR_Mission_Competence_ID, trxName);
      /** if (HR_Mission_Competence_ID == 0)
        {
			setHR_Mission_Competence_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Competence (Properties ctx, int HR_Mission_Competence_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Competence_ID, trxName, virtualColumns);
      /** if (HR_Mission_Competence_ID == 0)
        {
			setHR_Mission_Competence_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Competence (Properties ctx, String HR_Mission_Competence_UU, String trxName)
    {
      super (ctx, HR_Mission_Competence_UU, trxName);
      /** if (HR_Mission_Competence_UU == null)
        {
			setHR_Mission_Competence_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mission_Competence (Properties ctx, String HR_Mission_Competence_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mission_Competence_UU, trxName, virtualColumns);
      /** if (HR_Mission_Competence_UU == null)
        {
			setHR_Mission_Competence_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Mission_Competence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Mission_Competence[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	public I_HR_Competences getHR_Competences() throws RuntimeException
	{
		return (I_HR_Competences)MTable.get(getCtx(), I_HR_Competences.Table_ID)
			.getPO(getHR_Competences_ID(), get_TrxName());
	}

	/** Set Competences.
		@param HR_Competences_ID Competences
	*/
	public void setHR_Competences_ID (int HR_Competences_ID)
	{
		if (HR_Competences_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, Integer.valueOf(HR_Competences_ID));
	}

	/** Get Competences.
		@return Competences	  */
	public int getHR_Competences_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Competences_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Compétences Requises Pour les Missions.
		@param HR_Mission_Competence_ID Compétences Requises Pour les Missions
	*/
	public void setHR_Mission_Competence_ID (int HR_Mission_Competence_ID)
	{
		if (HR_Mission_Competence_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Competence_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mission_Competence_ID, Integer.valueOf(HR_Mission_Competence_ID));
	}

	/** Get Compétences Requises Pour les Missions.
		@return Compétences Requises Pour les Missions	  */
	public int getHR_Mission_Competence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mission_Competence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Mission_Competence_UU.
		@param HR_Mission_Competence_UU HR_Mission_Competence_UU
	*/
	public void setHR_Mission_Competence_UU (String HR_Mission_Competence_UU)
	{
		set_Value (COLUMNNAME_HR_Mission_Competence_UU, HR_Mission_Competence_UU);
	}

	/** Get HR_Mission_Competence_UU.
		@return HR_Mission_Competence_UU	  */
	public String getHR_Mission_Competence_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mission_Competence_UU);
	}

	public I_HR_Mission getHR_Mission() throws RuntimeException
	{
		return (I_HR_Mission)MTable.get(getCtx(), I_HR_Mission.Table_ID)
			.getPO(getHR_Mission_ID(), get_TrxName());
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

	public I_HR_Niveau_Importance getHR_Niveau_Importance() throws RuntimeException
	{
		return (I_HR_Niveau_Importance)MTable.get(getCtx(), I_HR_Niveau_Importance.Table_ID)
			.getPO(getHR_Niveau_Importance_ID(), get_TrxName());
	}

	/** Set Niveau d&#039;Importance.
		@param HR_Niveau_Importance_ID Niveau d&#039;Importance
	*/
	public void setHR_Niveau_Importance_ID (int HR_Niveau_Importance_ID)
	{
		if (HR_Niveau_Importance_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Niveau_Importance_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Niveau_Importance_ID, Integer.valueOf(HR_Niveau_Importance_ID));
	}

	/** Get Niveau d&#039;Importance.
		@return Niveau d&#039;Importance	  */
	public int getHR_Niveau_Importance_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Niveau_Importance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}