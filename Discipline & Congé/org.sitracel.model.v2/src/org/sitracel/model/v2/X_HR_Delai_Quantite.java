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

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for HR_Delai_Quantite
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="HR_Delai_Quantite")
public class X_HR_Delai_Quantite extends PO implements I_HR_Delai_Quantite, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240201L;

    /** Standard Constructor */
    public X_HR_Delai_Quantite (Properties ctx, int HR_Delai_Quantite_ID, String trxName)
    {
      super (ctx, HR_Delai_Quantite_ID, trxName);
      /** if (HR_Delai_Quantite_ID == 0)
        {
			setAffichage_Delai_Quantite (null);
			setHR_Delai_Quantite_ID (0);
			setQuantite_Delai (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Delai_Quantite (Properties ctx, int HR_Delai_Quantite_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Delai_Quantite_ID, trxName, virtualColumns);
      /** if (HR_Delai_Quantite_ID == 0)
        {
			setAffichage_Delai_Quantite (null);
			setHR_Delai_Quantite_ID (0);
			setQuantite_Delai (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Delai_Quantite (Properties ctx, String HR_Delai_Quantite_UU, String trxName)
    {
      super (ctx, HR_Delai_Quantite_UU, trxName);
      /** if (HR_Delai_Quantite_UU == null)
        {
			setAffichage_Delai_Quantite (null);
			setHR_Delai_Quantite_ID (0);
			setQuantite_Delai (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Delai_Quantite (Properties ctx, String HR_Delai_Quantite_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Delai_Quantite_UU, trxName, virtualColumns);
      /** if (HR_Delai_Quantite_UU == null)
        {
			setAffichage_Delai_Quantite (null);
			setHR_Delai_Quantite_ID (0);
			setQuantite_Delai (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Delai_Quantite (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Delai_Quantite[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Nom de la Quantité ou du Délai.
		@param Affichage_Delai_Quantite Nom de la Quantité ou du Délai
	*/
	public void setAffichage_Delai_Quantite (String Affichage_Delai_Quantite)
	{
		set_Value (COLUMNNAME_Affichage_Delai_Quantite, Affichage_Delai_Quantite);
	}

	/** Get Nom de la Quantité ou du Délai.
		@return Nom de la Quantité ou du Délai
	  */
	public String getAffichage_Delai_Quantite()
	{
		return (String)get_Value(COLUMNNAME_Affichage_Delai_Quantite);
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

	/** Set Table Délais et Quantités.
		@param HR_Delai_Quantite_ID Table Délais et Quantités
	*/
	public void setHR_Delai_Quantite_ID (int HR_Delai_Quantite_ID)
	{
		if (HR_Delai_Quantite_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Delai_Quantite_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Delai_Quantite_ID, Integer.valueOf(HR_Delai_Quantite_ID));
	}

	/** Get Table Délais et Quantités.
		@return Table Délais et Quantités	  */
	public int getHR_Delai_Quantite_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Delai_Quantite_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Delai_Quantite_UU.
		@param HR_Delai_Quantite_UU HR_Delai_Quantite_UU
	*/
	public void setHR_Delai_Quantite_UU (String HR_Delai_Quantite_UU)
	{
		set_Value (COLUMNNAME_HR_Delai_Quantite_UU, HR_Delai_Quantite_UU);
	}

	/** Get HR_Delai_Quantite_UU.
		@return HR_Delai_Quantite_UU	  */
	public String getHR_Delai_Quantite_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Delai_Quantite_UU);
	}

	/** F = F */
	public static final String INDEX_TRI_SEX1_F = "F";
	/** H = H */
	public static final String INDEX_TRI_SEX1_H = "H";
	/** Set Premier Index de Tri Sex.
		@param Index_Tri_Sex1 Premier Index de Tri Sex
	*/
	public void setIndex_Tri_Sex1 (String Index_Tri_Sex1)
	{

		set_Value (COLUMNNAME_Index_Tri_Sex1, Index_Tri_Sex1);
	}

	/** Get Premier Index de Tri Sex.
		@return Premier Index de Tri Sex
	  */
	public String getIndex_Tri_Sex1()
	{
		return (String)get_Value(COLUMNNAME_Index_Tri_Sex1);
	}

	/** Set Deuxième Index de Tri Sex.
		@param Index_Tri_Sex2 Deuxième Index de Tri Sex
	*/
	public void setIndex_Tri_Sex2 (String Index_Tri_Sex2)
	{
		set_Value (COLUMNNAME_Index_Tri_Sex2, Index_Tri_Sex2);
	}

	/** Get Deuxième Index de Tri Sex.
		@return Deuxième Index de Tri Sex
	  */
	public String getIndex_Tri_Sex2()
	{
		return (String)get_Value(COLUMNNAME_Index_Tri_Sex2);
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

	/** Set Valeur de la Quantite ou du Délai.
		@param Quantite_Delai Valeur de la Quantite ou du Délai
	*/
	public void setQuantite_Delai (int Quantite_Delai)
	{
		set_Value (COLUMNNAME_Quantite_Delai, Integer.valueOf(Quantite_Delai));
	}

	/** Get Valeur de la Quantite ou du Délai.
		@return Valeur de la Quantite ou du Délai
	  */
	public int getQuantite_Delai()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Quantite_Delai);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantite ou Délai Avant Echeance.
		@param Quantite_Delai_Avant_Echeance Quantite ou Délai Avant Echeance
	*/
	public void setQuantite_Delai_Avant_Echeance (int Quantite_Delai_Avant_Echeance)
	{
		set_Value (COLUMNNAME_Quantite_Delai_Avant_Echeance, Integer.valueOf(Quantite_Delai_Avant_Echeance));
	}

	/** Get Quantite ou Délai Avant Echeance.
		@return Quantite ou Délai Avant Echeance
	  */
	public int getQuantite_Delai_Avant_Echeance()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Quantite_Delai_Avant_Echeance);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Coefficient = Coefficient */
	public static final String TYPE_DELAI_QUANTITE_Coefficient = "Coefficient";
	/** Congé = Congé */
	public static final String TYPE_DELAI_QUANTITE_Congé = "Congé";
	/** Contrat = Contrat */
	public static final String TYPE_DELAI_QUANTITE_Contrat = "Contrat";
	/** Délai de la Sanction = Délai de la Sanction */
	public static final String TYPE_DELAI_QUANTITE_DélaiDeLaSanction = "Délai de la Sanction";
	/** Délai de Réponse = Délai de Réponse */
	public static final String TYPE_DELAI_QUANTITE_DélaiDeRéponse = "Délai de Réponse";
	/** Notation = Notation */
	public static final String TYPE_DELAI_QUANTITE_Notation = "Notation";
	/** Paramètre = Paramètre */
	public static final String TYPE_DELAI_QUANTITE_Paramètre = "Paramètre";
	/** Set Type de Délai ou Quantité.
		@param Type_Delai_Quantite Type de Délai ou Quantité
	*/
	public void setType_Delai_Quantite (String Type_Delai_Quantite)
	{

		set_Value (COLUMNNAME_Type_Delai_Quantite, Type_Delai_Quantite);
	}

	/** Get Type de Délai ou Quantité.
		@return Type de Délai ou Quantité
	  */
	public String getType_Delai_Quantite()
	{
		return (String)get_Value(COLUMNNAME_Type_Delai_Quantite);
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