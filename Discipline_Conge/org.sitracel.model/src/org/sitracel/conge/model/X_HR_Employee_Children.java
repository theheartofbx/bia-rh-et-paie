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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Employee_Children
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Employee_Children")
public class X_HR_Employee_Children extends PO implements I_HR_Employee_Children, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250421L;

    /** Standard Constructor */
    public X_HR_Employee_Children (Properties ctx, int HR_Employee_Children_ID, String trxName)
    {
      super (ctx, HR_Employee_Children_ID, trxName);
      /** if (HR_Employee_Children_ID == 0)
        {
			setDate_Naissance (new Timestamp( System.currentTimeMillis() ));
			setHR_Employee_Children_ID (0);
			setNom_Enfant (null);
			setPrenom_Enfant (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Employee_Children (Properties ctx, int HR_Employee_Children_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Employee_Children_ID, trxName, virtualColumns);
      /** if (HR_Employee_Children_ID == 0)
        {
			setDate_Naissance (new Timestamp( System.currentTimeMillis() ));
			setHR_Employee_Children_ID (0);
			setNom_Enfant (null);
			setPrenom_Enfant (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Employee_Children (Properties ctx, String HR_Employee_Children_UU, String trxName)
    {
      super (ctx, HR_Employee_Children_UU, trxName);
      /** if (HR_Employee_Children_UU == null)
        {
			setDate_Naissance (new Timestamp( System.currentTimeMillis() ));
			setHR_Employee_Children_ID (0);
			setNom_Enfant (null);
			setPrenom_Enfant (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Employee_Children (Properties ctx, String HR_Employee_Children_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Employee_Children_UU, trxName, virtualColumns);
      /** if (HR_Employee_Children_UU == null)
        {
			setDate_Naissance (new Timestamp( System.currentTimeMillis() ));
			setHR_Employee_Children_ID (0);
			setNom_Enfant (null);
			setPrenom_Enfant (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Employee_Children (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Employee_Children[")
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

	/** Set Date de Naissance.
		@param Date_Naissance Date de Naissance
	*/
	public void setDate_Naissance (Timestamp Date_Naissance)
	{
		set_Value (COLUMNNAME_Date_Naissance, Date_Naissance);
	}

	/** Get Date de Naissance.
		@return Date de Naissance
	  */
	public Timestamp getDate_Naissance()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Naissance);
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

	/** Set Enfant(s).
		@param HR_Employee_Children_ID Enfant(s)
	*/
	public void setHR_Employee_Children_ID (int HR_Employee_Children_ID)
	{
		if (HR_Employee_Children_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Employee_Children_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Employee_Children_ID, Integer.valueOf(HR_Employee_Children_ID));
	}

	/** Get Enfant(s).
		@return Enfant(s)	  */
	public int getHR_Employee_Children_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Employee_Children_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Employee_Children_UU.
		@param HR_Employee_Children_UU HR_Employee_Children_UU
	*/
	public void setHR_Employee_Children_UU (String HR_Employee_Children_UU)
	{
		set_Value (COLUMNNAME_HR_Employee_Children_UU, HR_Employee_Children_UU);
	}

	/** Get HR_Employee_Children_UU.
		@return HR_Employee_Children_UU	  */
	public String getHR_Employee_Children_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Employee_Children_UU);
	}

	/** Set Nom(s) de l&#039;Enfant.
		@param Nom_Enfant Nom(s) de l&#039;Enfant
	*/
	public void setNom_Enfant (String Nom_Enfant)
	{
		set_Value (COLUMNNAME_Nom_Enfant, Nom_Enfant);
	}

	/** Get Nom(s) de l&#039;Enfant.
		@return Nom(s) de l&#039;Enfant
	  */
	public String getNom_Enfant()
	{
		return (String)get_Value(COLUMNNAME_Nom_Enfant);
	}

	/** Set Prénom(s) de l&#039;Enfant.
		@param Prenom_Enfant Prénom(s) de l&#039;Enfant
	*/
	public void setPrenom_Enfant (String Prenom_Enfant)
	{
		set_Value (COLUMNNAME_Prenom_Enfant, Prenom_Enfant);
	}

	/** Get Prénom(s) de l&#039;Enfant.
		@return Prénom(s) de l&#039;Enfant
	  */
	public String getPrenom_Enfant()
	{
		return (String)get_Value(COLUMNNAME_Prenom_Enfant);
	}

	/** Femme = F */
	public static final String SEX_Femme = "F";
	/** Homme = H */
	public static final String SEX_Homme = "H";
	/** Set Sex.
		@param Sex Sex
	*/
	public void setSex (String Sex)
	{

		set_Value (COLUMNNAME_Sex, Sex);
	}

	/** Get Sex.
		@return Sex	  */
	public String getSex()
	{
		return (String)get_Value(COLUMNNAME_Sex);
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
}