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
package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Organigramme
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_Organigramme")
public class X_HR_Organigramme extends PO implements I_HR_Organigramme, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240323L;

    /** Standard Constructor */
    public X_HR_Organigramme (Properties ctx, int HR_Organigramme_ID, String trxName)
    {
      super (ctx, HR_Organigramme_ID, trxName);
      /** if (HR_Organigramme_ID == 0)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Organigramme_ID (0);
			setPoste_ID (0);
			setPoste_Responsable_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Organigramme (Properties ctx, int HR_Organigramme_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Organigramme_ID, trxName, virtualColumns);
      /** if (HR_Organigramme_ID == 0)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Organigramme_ID (0);
			setPoste_ID (0);
			setPoste_Responsable_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Organigramme (Properties ctx, String HR_Organigramme_UU, String trxName)
    {
      super (ctx, HR_Organigramme_UU, trxName);
      /** if (HR_Organigramme_UU == null)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Organigramme_ID (0);
			setPoste_ID (0);
			setPoste_Responsable_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Organigramme (Properties ctx, String HR_Organigramme_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Organigramme_UU, trxName, virtualColumns);
      /** if (HR_Organigramme_UU == null)
        {
			setHR_Categorie_Responsabilite_ID (0);
			setHR_Organigramme_ID (0);
			setPoste_ID (0);
			setPoste_Responsable_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Organigramme (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Organigramme[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_HR_Categorie_Responsabilite getHR_Categorie_Responsabilite() throws RuntimeException
	{
		return (I_HR_Categorie_Responsabilite)MTable.get(getCtx(), I_HR_Categorie_Responsabilite.Table_ID)
			.getPO(getHR_Categorie_Responsabilite_ID(), get_TrxName());
	}

	/** Set Catégorie de Responsabilité.
		@param HR_Categorie_Responsabilite_ID Catégorie de Responsabilité
	*/
	public void setHR_Categorie_Responsabilite_ID (int HR_Categorie_Responsabilite_ID)
	{
		if (HR_Categorie_Responsabilite_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Categorie_Responsabilite_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Categorie_Responsabilite_ID, Integer.valueOf(HR_Categorie_Responsabilite_ID));
	}

	/** Get Catégorie de Responsabilité.
		@return Catégorie de Responsabilité	  */
	public int getHR_Categorie_Responsabilite_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Categorie_Responsabilite_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Organigramme.
		@param HR_Organigramme_ID Organigramme
	*/
	public void setHR_Organigramme_ID (int HR_Organigramme_ID)
	{
		if (HR_Organigramme_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Organigramme_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Organigramme_ID, Integer.valueOf(HR_Organigramme_ID));
	}

	/** Get Organigramme.
		@return Organigramme	  */
	public int getHR_Organigramme_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Organigramme_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Organigramme_UU.
		@param HR_Organigramme_UU HR_Organigramme_UU
	*/
	public void setHR_Organigramme_UU (String HR_Organigramme_UU)
	{
		set_Value (COLUMNNAME_HR_Organigramme_UU, HR_Organigramme_UU);
	}

	/** Get HR_Organigramme_UU.
		@return HR_Organigramme_UU	  */
	public String getHR_Organigramme_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Organigramme_UU);
	}

	/** Set Message d&#039;Alerte Affiché.
		@param IsMessageAlerteDisplayed Message d&#039;Alerte Affiché
	*/
	public void setIsMessageAlerteDisplayed (boolean IsMessageAlerteDisplayed)
	{
		set_Value (COLUMNNAME_IsMessageAlerteDisplayed, Boolean.valueOf(IsMessageAlerteDisplayed));
	}

	/** Get Message d&#039;Alerte Affiché.
		@return Message d&#039;Alerte Affiché
	  */
	public boolean isMessageAlerteDisplayed()
	{
		Object oo = get_Value(COLUMNNAME_IsMessageAlerteDisplayed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Alerte.
		@param Message_Alerte Message d&#039;Alerte
	*/
	public void setMessage_Alerte (String Message_Alerte)
	{
		set_Value (COLUMNNAME_Message_Alerte, Message_Alerte);
	}

	/** Get Message d&#039;Alerte.
		@return Message d&#039;Alerte
	  */
	public String getMessage_Alerte()
	{
		return (String)get_Value(COLUMNNAME_Message_Alerte);
	}

	public org.eevolution.model.I_HR_Job getPoste() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getPoste_ID(), get_TrxName());
	}

	/** Set Nom du Poste :.
		@param Poste_ID Nom du Poste :
	*/
	public void setPoste_ID (int Poste_ID)
	{
		if (Poste_ID < 1)
			set_Value (COLUMNNAME_Poste_ID, null);
		else
			set_Value (COLUMNNAME_Poste_ID, Integer.valueOf(Poste_ID));
	}

	/** Get Nom du Poste :.
		@return Nom du Poste :
	  */
	public int getPoste_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Poste_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getPoste_Responsable() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getPoste_Responsable_ID(), get_TrxName());
	}

	/** Set Poste du Responsable .
		@param Poste_Responsable_ID Poste du Responsable 
	*/
	public void setPoste_Responsable_ID (int Poste_Responsable_ID)
	{
		if (Poste_Responsable_ID < 1)
			set_Value (COLUMNNAME_Poste_Responsable_ID, null);
		else
			set_Value (COLUMNNAME_Poste_Responsable_ID, Integer.valueOf(Poste_Responsable_ID));
	}

	/** Get Poste du Responsable .
		@return Poste du Responsable 
	  */
	public int getPoste_Responsable_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Poste_Responsable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}