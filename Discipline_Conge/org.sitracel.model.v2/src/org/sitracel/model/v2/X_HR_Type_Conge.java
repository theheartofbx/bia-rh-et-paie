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

/** Generated Model for HR_Type_Conge
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_Type_Conge")
public class X_HR_Type_Conge extends PO implements I_HR_Type_Conge, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240418L;

    /** Standard Constructor */
    public X_HR_Type_Conge (Properties ctx, int HR_Type_Conge_ID, String trxName)
    {
      super (ctx, HR_Type_Conge_ID, trxName);
      /** if (HR_Type_Conge_ID == 0)
        {
			setGenre_Employe (null);
			setHR_Type_Conge_ID (0);
			setNom_Conge (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Type_Conge (Properties ctx, int HR_Type_Conge_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Type_Conge_ID, trxName, virtualColumns);
      /** if (HR_Type_Conge_ID == 0)
        {
			setGenre_Employe (null);
			setHR_Type_Conge_ID (0);
			setNom_Conge (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Type_Conge (Properties ctx, String HR_Type_Conge_UU, String trxName)
    {
      super (ctx, HR_Type_Conge_UU, trxName);
      /** if (HR_Type_Conge_UU == null)
        {
			setGenre_Employe (null);
			setHR_Type_Conge_ID (0);
			setNom_Conge (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_Type_Conge (Properties ctx, String HR_Type_Conge_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Type_Conge_UU, trxName, virtualColumns);
      /** if (HR_Type_Conge_UU == null)
        {
			setGenre_Employe (null);
			setHR_Type_Conge_ID (0);
			setNom_Conge (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Type_Conge (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Type_Conge[")
        .append(get_ID()).append("]");
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

	/** Femme = F */
	public static final String GENRE_EMPLOYE_Femme = "F";
	/** Homme = H */
	public static final String GENRE_EMPLOYE_Homme = "H";
	/** Mixte = M */
	public static final String GENRE_EMPLOYE_Mixte = "M";
	/** Set Genre de l&#039;Employé(e) Concerné(e).
		@param Genre_Employe Genre de l&#039;Employé(e) Concerné(e)
	*/
	public void setGenre_Employe (String Genre_Employe)
	{

		set_Value (COLUMNNAME_Genre_Employe, Genre_Employe);
	}

	/** Get Genre de l&#039;Employé(e) Concerné(e).
		@return Genre de l&#039;Employé(e) Concerné(e)
	  */
	public String getGenre_Employe()
	{
		return (String)get_Value(COLUMNNAME_Genre_Employe);
	}

	/** Set Type de Congé.
		@param HR_Type_Conge_ID Type de Congé
	*/
	public void setHR_Type_Conge_ID (int HR_Type_Conge_ID)
	{
		if (HR_Type_Conge_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Type_Conge_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Type_Conge_ID, Integer.valueOf(HR_Type_Conge_ID));
	}

	/** Get Type de Congé.
		@return Type de Congé	  */
	public int getHR_Type_Conge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Type_Conge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Type_Conge_UU.
		@param HR_Type_Conge_UU HR_Type_Conge_UU
	*/
	public void setHR_Type_Conge_UU (String HR_Type_Conge_UU)
	{
		set_Value (COLUMNNAME_HR_Type_Conge_UU, HR_Type_Conge_UU);
	}

	/** Get HR_Type_Conge_UU.
		@return HR_Type_Conge_UU	  */
	public String getHR_Type_Conge_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Type_Conge_UU);
	}

	/** Set Est déduit des jours de congé annuel.
		@param IsCongeAnnuel Est déduit des jours de congé annuel
	*/
	public void setIsCongeAnnuel (boolean IsCongeAnnuel)
	{
		set_Value (COLUMNNAME_IsCongeAnnuel, Boolean.valueOf(IsCongeAnnuel));
	}

	/** Get Est déduit des jours de congé annuel.
		@return Est déduit des jours de congé annuel
	  */
	public boolean isCongeAnnuel()
	{
		Object oo = get_Value(COLUMNNAME_IsCongeAnnuel);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Définir la période de Congé.
		@param IsDefinir_Periode_Conge Définir la période de Congé
	*/
	public void setIsDefinir_Periode_Conge (boolean IsDefinir_Periode_Conge)
	{
		set_Value (COLUMNNAME_IsDefinir_Periode_Conge, Boolean.valueOf(IsDefinir_Periode_Conge));
	}

	/** Get Définir la période de Congé.
		@return Définir la période de Congé
	  */
	public boolean isDefinir_Periode_Conge()
	{
		Object oo = get_Value(COLUMNNAME_IsDefinir_Periode_Conge);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nombre de Jour(s) Après Echéance.
		@param Nombre_Jour_Après_Echeance Nombre de Jour(s) Après Echéance
	*/
	public void setNombre_Jour_Après_Echeance (int Nombre_Jour_Après_Echeance)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Après_Echeance, Integer.valueOf(Nombre_Jour_Après_Echeance));
	}

	/** Get Nombre de Jour(s) Après Echéance.
		@return Nombre de Jour(s) Après Echéance
	  */
	public int getNombre_Jour_Après_Echeance()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Après_Echeance);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nombre de Jour(s) Avant Echéance.
		@param Nombre_Jour_Avant_Echeance Nombre de Jour(s) Avant Echéance
	*/
	public void setNombre_Jour_Avant_Echeance (int Nombre_Jour_Avant_Echeance)
	{
		set_Value (COLUMNNAME_Nombre_Jour_Avant_Echeance, Integer.valueOf(Nombre_Jour_Avant_Echeance));
	}

	/** Get Nombre de Jour(s) Avant Echéance.
		@return Nombre de Jour(s) Avant Echéance
	  */
	public int getNombre_Jour_Avant_Echeance()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Jour_Avant_Echeance);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nom du Congé.
		@param Nom_Conge Nom du Congé
	*/
	public void setNom_Conge (String Nom_Conge)
	{
		set_Value (COLUMNNAME_Nom_Conge, Nom_Conge);
	}

	/** Get Nom du Congé.
		@return Nom du Congé
	  */
	public String getNom_Conge()
	{
		return (String)get_Value(COLUMNNAME_Nom_Conge);
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