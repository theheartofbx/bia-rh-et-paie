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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;
import org.sitracel.model.I_HR_Formation;
import org.sitracel.model.I_NiveauEtude;

/** Generated Model for HR_OffreNiveauEtude
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_OffreNiveauEtude")
public class X_HR_OffreNiveauEtude extends PO implements I_HR_OffreNiveauEtude, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241208L;

    /** Standard Constructor */
    public X_HR_OffreNiveauEtude (Properties ctx, int HR_OffreNiveauEtude_ID, String trxName)
    {
      super (ctx, HR_OffreNiveauEtude_ID, trxName);
      /** if (HR_OffreNiveauEtude_ID == 0)
        {
			setHR_Formation_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreNiveauEtude_ID (0);
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreNiveauEtude (Properties ctx, int HR_OffreNiveauEtude_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreNiveauEtude_ID, trxName, virtualColumns);
      /** if (HR_OffreNiveauEtude_ID == 0)
        {
			setHR_Formation_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreNiveauEtude_ID (0);
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreNiveauEtude (Properties ctx, String HR_OffreNiveauEtude_UU, String trxName)
    {
      super (ctx, HR_OffreNiveauEtude_UU, trxName);
      /** if (HR_OffreNiveauEtude_UU == null)
        {
			setHR_Formation_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreNiveauEtude_ID (0);
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreNiveauEtude (Properties ctx, String HR_OffreNiveauEtude_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreNiveauEtude_UU, trxName, virtualColumns);
      /** if (HR_OffreNiveauEtude_UU == null)
        {
			setHR_Formation_ID (0);
			setHR_OffreEmploi_ID (0);
			setHR_OffreNiveauEtude_ID (0);
			setName (null);
			setNiveauEtude_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_OffreNiveauEtude (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_OffreNiveauEtude[")
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

	public I_HR_Formation getHR_Formation() throws RuntimeException
	{
		return (I_HR_Formation)MTable.get(getCtx(), I_HR_Formation.Table_ID)
			.getPO(getHR_Formation_ID(), get_TrxName());
	}

	/** Set Formation.
		@param HR_Formation_ID Formation
	*/
	public void setHR_Formation_ID (int HR_Formation_ID)
	{
		if (HR_Formation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Formation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Formation_ID, Integer.valueOf(HR_Formation_ID));
	}

	/** Get Formation.
		@return Formation	  */
	public int getHR_Formation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Formation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_OffreEmploi getHR_OffreEmploi() throws RuntimeException
	{
		return (I_HR_OffreEmploi)MTable.get(getCtx(), I_HR_OffreEmploi.Table_ID)
			.getPO(getHR_OffreEmploi_ID(), get_TrxName());
	}

	/** Set Offre d&#039;Emploi.
		@param HR_OffreEmploi_ID Offre d&#039;Emploi
	*/
	public void setHR_OffreEmploi_ID (int HR_OffreEmploi_ID)
	{
		if (HR_OffreEmploi_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_OffreEmploi_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_OffreEmploi_ID, Integer.valueOf(HR_OffreEmploi_ID));
	}

	/** Get Offre d&#039;Emploi.
		@return Offre d&#039;Emploi	  */
	public int getHR_OffreEmploi_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreEmploi_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Niveau d&#039;Etude Offre.
		@param HR_OffreNiveauEtude_ID Niveau d&#039;Etude Offre
	*/
	public void setHR_OffreNiveauEtude_ID (int HR_OffreNiveauEtude_ID)
	{
		if (HR_OffreNiveauEtude_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_OffreNiveauEtude_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_OffreNiveauEtude_ID, Integer.valueOf(HR_OffreNiveauEtude_ID));
	}

	/** Get Niveau d&#039;Etude Offre.
		@return Niveau d&#039;Etude Offre	  */
	public int getHR_OffreNiveauEtude_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreNiveauEtude_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_OffreNiveauEtude_UU.
		@param HR_OffreNiveauEtude_UU HR_OffreNiveauEtude_UU
	*/
	public void setHR_OffreNiveauEtude_UU (String HR_OffreNiveauEtude_UU)
	{
		set_Value (COLUMNNAME_HR_OffreNiveauEtude_UU, HR_OffreNiveauEtude_UU);
	}

	/** Get HR_OffreNiveauEtude_UU.
		@return HR_OffreNiveauEtude_UU	  */
	public String getHR_OffreNiveauEtude_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_OffreNiveauEtude_UU);
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

	public I_NiveauEtude getNiveauEtude() throws RuntimeException
	{
		return (I_NiveauEtude)MTable.get(getCtx(), I_NiveauEtude.Table_ID)
			.getPO(getNiveauEtude_ID(), get_TrxName());
	}

	/** Set Niveau D&#039;Etude.
		@param NiveauEtude_ID Niveau D&#039;Etude
	*/
	public void setNiveauEtude_ID (int NiveauEtude_ID)
	{
		if (NiveauEtude_ID < 1)
			set_ValueNoCheck (COLUMNNAME_NiveauEtude_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_NiveauEtude_ID, Integer.valueOf(NiveauEtude_ID));
	}

	/** Get Niveau D&#039;Etude.
		@return Niveau D&#039;Etude	  */
	public int getNiveauEtude_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NiveauEtude_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}