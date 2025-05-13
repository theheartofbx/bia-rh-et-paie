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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Element_Base_Paie
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Element_Base_Paie")
public class X_HR_Element_Base_Paie extends PO implements I_HR_Element_Base_Paie, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250415L;

    /** Standard Constructor */
    public X_HR_Element_Base_Paie (Properties ctx, int HR_Element_Base_Paie_ID, String trxName)
    {
      super (ctx, HR_Element_Base_Paie_ID, trxName);
      /** if (HR_Element_Base_Paie_ID == 0)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_TypeDeCharge_ID (0);
			setHR_Type_Calcul_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Element_Base_Paie (Properties ctx, int HR_Element_Base_Paie_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Element_Base_Paie_ID, trxName, virtualColumns);
      /** if (HR_Element_Base_Paie_ID == 0)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_TypeDeCharge_ID (0);
			setHR_Type_Calcul_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Element_Base_Paie (Properties ctx, String HR_Element_Base_Paie_UU, String trxName)
    {
      super (ctx, HR_Element_Base_Paie_UU, trxName);
      /** if (HR_Element_Base_Paie_UU == null)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_TypeDeCharge_ID (0);
			setHR_Type_Calcul_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Element_Base_Paie (Properties ctx, String HR_Element_Base_Paie_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Element_Base_Paie_UU, trxName, virtualColumns);
      /** if (HR_Element_Base_Paie_UU == null)
        {
			setHR_Element_Base_Paie_ID (0);
			setHR_TypeDeCharge_ID (0);
			setHR_Type_Calcul_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Element_Base_Paie (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Element_Base_Paie[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public I_HR_Element_Base_Paie getBase_Calcul() throws RuntimeException
	{
		return (I_HR_Element_Base_Paie)MTable.get(getCtx(), I_HR_Element_Base_Paie.Table_ID)
			.getPO(getBase_Calcul_ID(), get_TrxName());
	}

	/** Set Base de Calcul.
		@param Base_Calcul_ID Base de Calcul
	*/
	public void setBase_Calcul_ID (int Base_Calcul_ID)
	{
		if (Base_Calcul_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Base_Calcul_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Base_Calcul_ID, Integer.valueOf(Base_Calcul_ID));
	}

	/** Get Base de Calcul.
		@return Base de Calcul
	  */
	public int getBase_Calcul_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Base_Calcul_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Formule.
		@param Formule Formule
	*/
	public void setFormule (String Formule)
	{
		set_Value (COLUMNNAME_Formule, Formule);
	}

	/** Get Formule.
		@return Formule	  */
	public String getFormule()
	{
		return (String)get_Value(COLUMNNAME_Formule);
	}

	/** Set Élément de Paie.
		@param HR_Element_Base_Paie_ID Élément de Paie
	*/
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID)
	{
		if (HR_Element_Base_Paie_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, Integer.valueOf(HR_Element_Base_Paie_ID));
	}

	/** Get Élément de Paie.
		@return Élément de Paie	  */
	public int getHR_Element_Base_Paie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Element_Base_Paie_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Element_Base_Paie_UU.
		@param HR_Element_Base_Paie_UU HR_Element_Base_Paie_UU
	*/
	public void setHR_Element_Base_Paie_UU (String HR_Element_Base_Paie_UU)
	{
		set_Value (COLUMNNAME_HR_Element_Base_Paie_UU, HR_Element_Base_Paie_UU);
	}

	/** Get HR_Element_Base_Paie_UU.
		@return HR_Element_Base_Paie_UU	  */
	public String getHR_Element_Base_Paie_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Element_Base_Paie_UU);
	}

	public I_HR_Rang_Calcul getHR_Rang_Calcul() throws RuntimeException
	{
		return (I_HR_Rang_Calcul)MTable.get(getCtx(), I_HR_Rang_Calcul.Table_ID)
			.getPO(getHR_Rang_Calcul_ID(), get_TrxName());
	}

	/** Set Rang de Calcul.
		@param HR_Rang_Calcul_ID Rang de Calcul
	*/
	public void setHR_Rang_Calcul_ID (int HR_Rang_Calcul_ID)
	{
		if (HR_Rang_Calcul_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Rang_Calcul_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Rang_Calcul_ID, Integer.valueOf(HR_Rang_Calcul_ID));
	}

	/** Get Rang de Calcul.
		@return Rang de Calcul	  */
	public int getHR_Rang_Calcul_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Rang_Calcul_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_TypeDeCharge getHR_TypeDeCharge() throws RuntimeException
	{
		return (I_HR_TypeDeCharge)MTable.get(getCtx(), I_HR_TypeDeCharge.Table_ID)
			.getPO(getHR_TypeDeCharge_ID(), get_TrxName());
	}

	/** Set Type de Charge.
		@param HR_TypeDeCharge_ID Type de Charge
	*/
	public void setHR_TypeDeCharge_ID (int HR_TypeDeCharge_ID)
	{
		if (HR_TypeDeCharge_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_TypeDeCharge_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_TypeDeCharge_ID, Integer.valueOf(HR_TypeDeCharge_ID));
	}

	/** Get Type de Charge.
		@return Type de Charge	  */
	public int getHR_TypeDeCharge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeDeCharge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Type_Calcul getHR_Type_Calcul() throws RuntimeException
	{
		return (I_HR_Type_Calcul)MTable.get(getCtx(), I_HR_Type_Calcul.Table_ID)
			.getPO(getHR_Type_Calcul_ID(), get_TrxName());
	}

	/** Set Type de Calcul.
		@param HR_Type_Calcul_ID Type de Calcul
	*/
	public void setHR_Type_Calcul_ID (int HR_Type_Calcul_ID)
	{
		if (HR_Type_Calcul_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Type_Calcul_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Type_Calcul_ID, Integer.valueOf(HR_Type_Calcul_ID));
	}

	/** Get Type de Calcul.
		@return Type de Calcul	  */
	public int getHR_Type_Calcul_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Calcul_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Est Inclus lors du Calcul Initial.
		@param IsCalcul_Initial Est Inclus lors du Calcul Initial
	*/
	public void setIsCalcul_Initial (boolean IsCalcul_Initial)
	{
		set_ValueNoCheck (COLUMNNAME_IsCalcul_Initial, Boolean.valueOf(IsCalcul_Initial));
	}

	/** Get Est Inclus lors du Calcul Initial.
		@return Est Inclus lors du Calcul Initial
	  */
	public boolean isCalcul_Initial()
	{
		Object oo = get_Value(COLUMNNAME_IsCalcul_Initial);
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

	/** Set Pourcentage.
		@param Pourcentage Pourcentage
	*/
	public void setPourcentage (BigDecimal Pourcentage)
	{
		set_ValueNoCheck (COLUMNNAME_Pourcentage, Pourcentage);
	}

	/** Get Pourcentage.
		@return Pourcentage
	  */
	public BigDecimal getPourcentage()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pourcentage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}