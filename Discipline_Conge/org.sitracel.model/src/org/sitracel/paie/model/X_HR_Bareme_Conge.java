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

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for HR_Bareme_Conge
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Bareme_Conge")
public class X_HR_Bareme_Conge extends PO implements I_HR_Bareme_Conge, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250512L;

    /** Standard Constructor */
    public X_HR_Bareme_Conge (Properties ctx, int HR_Bareme_Conge_ID, String trxName)
    {
      super (ctx, HR_Bareme_Conge_ID, trxName);
      /** if (HR_Bareme_Conge_ID == 0)
        {
			setHR_Bareme_Conge_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Bareme_Conge (Properties ctx, int HR_Bareme_Conge_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Bareme_Conge_ID, trxName, virtualColumns);
      /** if (HR_Bareme_Conge_ID == 0)
        {
			setHR_Bareme_Conge_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Bareme_Conge (Properties ctx, String HR_Bareme_Conge_UU, String trxName)
    {
      super (ctx, HR_Bareme_Conge_UU, trxName);
      /** if (HR_Bareme_Conge_UU == null)
        {
			setHR_Bareme_Conge_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Bareme_Conge (Properties ctx, String HR_Bareme_Conge_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Bareme_Conge_UU, trxName, virtualColumns);
      /** if (HR_Bareme_Conge_UU == null)
        {
			setHR_Bareme_Conge_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Bareme_Conge (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Bareme_Conge[")
        .append(get_ID()).append("]");
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

	/** Set Formule.
		@param Formule Formule
	*/
	@Override
	public void setFormule (String Formule)
	{
		set_Value (COLUMNNAME_Formule, Formule);
	}

	/** Get Formule.
		@return Formule	  */
	@Override
	public String getFormule()
	{
		return (String)get_Value(COLUMNNAME_Formule);
	}

	/** Set Barème Congé.
		@param HR_Bareme_Conge_ID Barème Congé
	*/
	@Override
	public void setHR_Bareme_Conge_ID (int HR_Bareme_Conge_ID)
	{
		if (HR_Bareme_Conge_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Bareme_Conge_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Bareme_Conge_ID, Integer.valueOf(HR_Bareme_Conge_ID));
		}
	}

	/** Get Barème Congé.
		@return Barème Congé	  */
	@Override
	public int getHR_Bareme_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Bareme_Conge_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Bareme_Conge_UU.
		@param HR_Bareme_Conge_UU HR_Bareme_Conge_UU
	*/
	@Override
	public void setHR_Bareme_Conge_UU (String HR_Bareme_Conge_UU)
	{
		set_Value (COLUMNNAME_HR_Bareme_Conge_UU, HR_Bareme_Conge_UU);
	}

	/** Get HR_Bareme_Conge_UU.
		@return HR_Bareme_Conge_UU	  */
	@Override
	public String getHR_Bareme_Conge_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Bareme_Conge_UU);
	}

	@Override
	public I_HR_Element_Conge getHR_Element_Conge() throws RuntimeException
	{
		return (I_HR_Element_Conge)MTable.get(getCtx(), I_HR_Element_Conge.Table_ID)
			.getPO(getHR_Element_Conge_ID(), get_TrxName());
	}

	/** Set Élément Base Indemnite Congé.
		@param HR_Element_Conge_ID Élément Base Indemnite Congé
	*/
	@Override
	public void setHR_Element_Conge_ID (int HR_Element_Conge_ID)
	{
		if (HR_Element_Conge_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Conge_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Conge_ID, Integer.valueOf(HR_Element_Conge_ID));
		}
	}

	/** Get Élément Base Indemnite Congé.
		@return Élément Base Indemnite Congé	  */
	@Override
	public int getHR_Element_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Element_Conge_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Montant.
		@param Montant Montant
	*/
	@Override
	public void setMontant (BigDecimal Montant)
	{
		set_Value (COLUMNNAME_Montant, Montant);
	}

	/** Get Montant.
		@return Montant
	  */
	@Override
	public BigDecimal getMontant()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Montant à Partir de .
		@param Montant_Debut Montant à Partir de
	*/
	@Override
	public void setMontant_Debut (BigDecimal Montant_Debut)
	{
		set_Value (COLUMNNAME_Montant_Debut, Montant_Debut);
	}

	/** Get Montant à Partir de .
		@return Montant à Partir de
	  */
	@Override
	public BigDecimal getMontant_Debut()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Debut);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Montant Jusqu&#039;à.
		@param Montant_Fin Montant Jusqu&#039;à
	*/
	@Override
	public void setMontant_Fin (BigDecimal Montant_Fin)
	{
		set_Value (COLUMNNAME_Montant_Fin, Montant_Fin);
	}

	/** Get Montant Jusqu&#039;à.
		@return Montant Jusqu&#039;à
	  */
	@Override
	public BigDecimal getMontant_Fin()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Fin);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}
}