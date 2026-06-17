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
package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for HR_Type_Absence
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Type_Absence")
public class X_HR_Type_Absence extends PO implements I_HR_Type_Absence, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250421L;

    /** Standard Constructor */
    public X_HR_Type_Absence (Properties ctx, int HR_Type_Absence_ID, String trxName)
    {
      super (ctx, HR_Type_Absence_ID, trxName);
      /** if (HR_Type_Absence_ID == 0)
        {
			setHR_Type_Absence_ID (0);
			setNom_Absence (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Type_Absence (Properties ctx, int HR_Type_Absence_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Type_Absence_ID, trxName, virtualColumns);
      /** if (HR_Type_Absence_ID == 0)
        {
			setHR_Type_Absence_ID (0);
			setNom_Absence (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Type_Absence (Properties ctx, String HR_Type_Absence_UU, String trxName)
    {
      super (ctx, HR_Type_Absence_UU, trxName);
      /** if (HR_Type_Absence_UU == null)
        {
			setHR_Type_Absence_ID (0);
			setNom_Absence (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Type_Absence (Properties ctx, String HR_Type_Absence_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Type_Absence_UU, trxName, virtualColumns);
      /** if (HR_Type_Absence_UU == null)
        {
			setHR_Type_Absence_ID (0);
			setNom_Absence (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Type_Absence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Type_Absence[")
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

	/** Set Type d&#039;Absence.
		@param HR_Type_Absence_ID Type d&#039;Absence
	*/
	@Override
	public void setHR_Type_Absence_ID (int HR_Type_Absence_ID)
	{
		if (HR_Type_Absence_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Type_Absence_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Type_Absence_ID, Integer.valueOf(HR_Type_Absence_ID));
		}
	}

	/** Get Type d&#039;Absence.
		@return Type d&#039;Absence	  */
	@Override
	public int getHR_Type_Absence_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Absence_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Type_Absence_UU.
		@param HR_Type_Absence_UU HR_Type_Absence_UU
	*/
	@Override
	public void setHR_Type_Absence_UU (String HR_Type_Absence_UU)
	{
		set_Value (COLUMNNAME_HR_Type_Absence_UU, HR_Type_Absence_UU);
	}

	/** Get HR_Type_Absence_UU.
		@return HR_Type_Absence_UU	  */
	@Override
	public String getHR_Type_Absence_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Type_Absence_UU);
	}

	/** Set Compte comme un Congé.
		@param IsConge Compte comme un Congé
	*/
	@Override
	public void setIsConge (boolean IsConge)
	{
		set_Value (COLUMNNAME_IsConge, Boolean.valueOf(IsConge));
	}

	/** Get Compte comme un Congé.
		@return Compte comme un Congé
	  */
	@Override
	public boolean isConge()
	{
		Object oo = get_Value(COLUMNNAME_IsConge);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Peut conduire à une Demande d&#039;Explication.
		@param IsDemandeExplication Peut conduire à une Demande d&#039;Explication
	*/
	@Override
	public void setIsDemandeExplication (boolean IsDemandeExplication)
	{
		set_Value (COLUMNNAME_IsDemandeExplication, Boolean.valueOf(IsDemandeExplication));
	}

	/** Get Peut conduire à une Demande d&#039;Explication.
		@return Peut conduire à une Demande d&#039;Explication
	  */
	@Override
	public boolean isDemandeExplication()
	{
		Object oo = get_Value(COLUMNNAME_IsDemandeExplication);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nom de l&#039;Absence.
		@param Nom_Absence Nom de l&#039;Absence
	*/
	@Override
	public void setNom_Absence (String Nom_Absence)
	{
		set_Value (COLUMNNAME_Nom_Absence, Nom_Absence);
	}

	/** Get Nom de l&#039;Absence.
		@return Nom de l&#039;Absence
	  */
	@Override
	public String getNom_Absence()
	{
		return (String)get_Value(COLUMNNAME_Nom_Absence);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	@Override
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	@Override
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}