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

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_OffreCompetences
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_OffreCompetences")
public class X_HR_OffreCompetences extends PO implements I_HR_OffreCompetences, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241208L;

    /** Standard Constructor */
    public X_HR_OffreCompetences (Properties ctx, int HR_OffreCompetences_ID, String trxName)
    {
      super (ctx, HR_OffreCompetences_ID, trxName);
      /** if (HR_OffreCompetences_ID == 0)
        {
			setHR_OffreCompetences_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreCompetences (Properties ctx, int HR_OffreCompetences_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreCompetences_ID, trxName, virtualColumns);
      /** if (HR_OffreCompetences_ID == 0)
        {
			setHR_OffreCompetences_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreCompetences (Properties ctx, String HR_OffreCompetences_UU, String trxName)
    {
      super (ctx, HR_OffreCompetences_UU, trxName);
      /** if (HR_OffreCompetences_UU == null)
        {
			setHR_OffreCompetences_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_OffreCompetences (Properties ctx, String HR_OffreCompetences_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_OffreCompetences_UU, trxName, virtualColumns);
      /** if (HR_OffreCompetences_UU == null)
        {
			setHR_OffreCompetences_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_OffreCompetences (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_OffreCompetences[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	@Override
	public I_HR_Competences getHR_Competences() throws RuntimeException
	{
		return (I_HR_Competences)MTable.get(getCtx(), I_HR_Competences.Table_ID)
			.getPO(getHR_Competences_ID(), get_TrxName());
	}

	/** Set Competences.
		@param HR_Competences_ID Competences
	*/
	@Override
	public void setHR_Competences_ID (int HR_Competences_ID)
	{
		if (HR_Competences_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Competences_ID, Integer.valueOf(HR_Competences_ID));
		}
	}

	/** Get Competences.
		@return Competences	  */
	@Override
	public int getHR_Competences_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Competences_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Offre Compétences.
		@param HR_OffreCompetences_ID Offre Compétences
	*/
	@Override
	public void setHR_OffreCompetences_ID (int HR_OffreCompetences_ID)
	{
		if (HR_OffreCompetences_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_OffreCompetences_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_OffreCompetences_ID, Integer.valueOf(HR_OffreCompetences_ID));
		}
	}

	/** Get Offre Compétences.
		@return Offre Compétences	  */
	@Override
	public int getHR_OffreCompetences_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OffreCompetences_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_OffreCompetences_UU.
		@param HR_OffreCompetences_UU HR_OffreCompetences_UU
	*/
	@Override
	public void setHR_OffreCompetences_UU (String HR_OffreCompetences_UU)
	{
		set_Value (COLUMNNAME_HR_OffreCompetences_UU, HR_OffreCompetences_UU);
	}

	/** Get HR_OffreCompetences_UU.
		@return HR_OffreCompetences_UU	  */
	@Override
	public String getHR_OffreCompetences_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_OffreCompetences_UU);
	}

	@Override
	public I_HR_OffreEmploi getHR_OffreEmploi() throws RuntimeException
	{
		return (I_HR_OffreEmploi)MTable.get(getCtx(), I_HR_OffreEmploi.Table_ID)
			.getPO(getHR_OffreEmploi_ID(), get_TrxName());
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getName());
    }
}