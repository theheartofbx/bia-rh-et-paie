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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Objectif
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Objectif")
public class X_HR_Objectif extends PO implements I_HR_Objectif, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251211L;

    /** Standard Constructor */
    public X_HR_Objectif (Properties ctx, int HR_Objectif_ID, String trxName)
    {
      super (ctx, HR_Objectif_ID, trxName);
      /** if (HR_Objectif_ID == 0)
        {
			setHR_Objectif_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Objectif (Properties ctx, int HR_Objectif_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Objectif_ID, trxName, virtualColumns);
      /** if (HR_Objectif_ID == 0)
        {
			setHR_Objectif_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Objectif (Properties ctx, String HR_Objectif_UU, String trxName)
    {
      super (ctx, HR_Objectif_UU, trxName);
      /** if (HR_Objectif_UU == null)
        {
			setHR_Objectif_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Objectif (Properties ctx, String HR_Objectif_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Objectif_UU, trxName, virtualColumns);
      /** if (HR_Objectif_UU == null)
        {
			setHR_Objectif_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Objectif (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Objectif[")
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

	/** Set Transaction Date.
		@param DateTrx Transaction Date
	*/
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
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

	/** Set Objectif.
		@param HR_Objectif_ID Objectif
	*/
	public void setHR_Objectif_ID (int HR_Objectif_ID)
	{
		if (HR_Objectif_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Objectif_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Objectif_ID, Integer.valueOf(HR_Objectif_ID));
	}

	/** Get Objectif.
		@return Objectif	  */
	public int getHR_Objectif_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Objectif_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Objectif_UU.
		@param HR_Objectif_UU HR_Objectif_UU
	*/
	public void setHR_Objectif_UU (String HR_Objectif_UU)
	{
		set_Value (COLUMNNAME_HR_Objectif_UU, HR_Objectif_UU);
	}

	/** Get HR_Objectif_UU.
		@return HR_Objectif_UU	  */
	public String getHR_Objectif_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Objectif_UU);
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

	/** Set Ponderation.
		@param Ponderation Ponderation
	*/
	public void setPonderation (int Ponderation)
	{
		set_Value (COLUMNNAME_Ponderation, Integer.valueOf(Ponderation));
	}

	/** Get Ponderation.
		@return Ponderation	  */
	public int getPonderation()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ponderation);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inventory Quantity.
		@param QtyRange Inventory Quantity
	*/
	public void setQtyRange (int QtyRange)
	{
		set_Value (COLUMNNAME_QtyRange, Integer.valueOf(QtyRange));
	}

	/** Get Inventory Quantity.
		@return Inventory Quantity	  */
	public int getQtyRange()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QtyRange);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Numéric = N */
	public static final String TYPEQUESTION_Numéric = "N";
	/** Pourcentage = P */
	public static final String TYPEQUESTION_Pourcentage = "P";
	/** Reponse Multiple = RM */
	public static final String TYPEQUESTION_ReponseMultiple = "RM";
	/** Reponse Unique = RU */
	public static final String TYPEQUESTION_ReponseUnique = "RU";
	/** Texte = T */
	public static final String TYPEQUESTION_Texte = "T";
	/** Set Type Question.
		@param TypeQuestion Type Question
	*/
	public void setTypeQuestion (String TypeQuestion)
	{

		set_Value (COLUMNNAME_TypeQuestion, TypeQuestion);
	}

	/** Get Type Question.
		@return Type Question	  */
	public String getTypeQuestion()
	{
		return (String)get_Value(COLUMNNAME_TypeQuestion);
	}
}