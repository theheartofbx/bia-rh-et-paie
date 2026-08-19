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
package org.sitracel.evaluation.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_EvalGrilleFormule
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_EvalGrilleFormule")
public class X_HR_EvalGrilleFormule extends PO implements I_HR_EvalGrilleFormule, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260819L;

    /** Standard Constructor */
    public X_HR_EvalGrilleFormule (Properties ctx, int HR_EvalGrilleFormule_ID, String trxName)
    {
      super (ctx, HR_EvalGrilleFormule_ID, trxName);
      /** if (HR_EvalGrilleFormule_ID == 0)
        {
			setFormule (null);
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalGrille_ID (0);
			setIsPrincipale (false);
// N
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalGrilleFormule (Properties ctx, int HR_EvalGrilleFormule_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalGrilleFormule_ID, trxName, virtualColumns);
      /** if (HR_EvalGrilleFormule_ID == 0)
        {
			setFormule (null);
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalGrille_ID (0);
			setIsPrincipale (false);
// N
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalGrilleFormule (Properties ctx, String HR_EvalGrilleFormule_UU, String trxName)
    {
      super (ctx, HR_EvalGrilleFormule_UU, trxName);
      /** if (HR_EvalGrilleFormule_UU == null)
        {
			setFormule (null);
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalGrille_ID (0);
			setIsPrincipale (false);
// N
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_EvalGrilleFormule (Properties ctx, String HR_EvalGrilleFormule_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_EvalGrilleFormule_UU, trxName, virtualColumns);
      /** if (HR_EvalGrilleFormule_UU == null)
        {
			setFormule (null);
			setHR_EvalGrilleFormule_ID (0);
			setHR_EvalGrille_ID (0);
			setIsPrincipale (false);
// N
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_EvalGrilleFormule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_EvalGrilleFormule[")
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

	/** Set Formule Grille Évaluation.
		@param HR_EvalGrilleFormule_ID Formule Grille Évaluation
	*/
	public void setHR_EvalGrilleFormule_ID (int HR_EvalGrilleFormule_ID)
	{
		if (HR_EvalGrilleFormule_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleFormule_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleFormule_ID, Integer.valueOf(HR_EvalGrilleFormule_ID));
	}

	/** Get Formule Grille Évaluation.
		@return Formule Grille Évaluation	  */
	public int getHR_EvalGrilleFormule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalGrilleFormule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_EvalGrilleFormule_UU.
		@param HR_EvalGrilleFormule_UU HR_EvalGrilleFormule_UU
	*/
	public void setHR_EvalGrilleFormule_UU (String HR_EvalGrilleFormule_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HR_EvalGrilleFormule_UU, HR_EvalGrilleFormule_UU);
	}

	/** Get HR_EvalGrilleFormule_UU.
		@return HR_EvalGrilleFormule_UU	  */
	public String getHR_EvalGrilleFormule_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_EvalGrilleFormule_UU);
	}

	public I_HR_EvalGrille getHR_EvalGrille() throws RuntimeException
	{
		return (I_HR_EvalGrille)MTable.get(getCtx(), I_HR_EvalGrille.Table_ID)
			.getPO(getHR_EvalGrille_ID(), get_TrxName());
	}

	/** Set Grille d&#039;Évaluation.
		@param HR_EvalGrille_ID Grille d&#039;Évaluation
	*/
	public void setHR_EvalGrille_ID (int HR_EvalGrille_ID)
	{
		if (HR_EvalGrille_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrille_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_EvalGrille_ID, Integer.valueOf(HR_EvalGrille_ID));
	}

	/** Get Grille d&#039;Évaluation.
		@return Grille d&#039;Évaluation	  */
	public int getHR_EvalGrille_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EvalGrille_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Principale.
		@param IsPrincipale Principale
	*/
	public void setIsPrincipale (boolean IsPrincipale)
	{
		set_Value (COLUMNNAME_IsPrincipale, Boolean.valueOf(IsPrincipale));
	}

	/** Get Principale.
		@return Principale	  */
	public boolean isPrincipale()
	{
		Object oo = get_Value(COLUMNNAME_IsPrincipale);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Valide.
		@param IsValide Valide
	*/
	public void setIsValide (boolean IsValide)
	{
		set_Value (COLUMNNAME_IsValide, Boolean.valueOf(IsValide));
	}

	/** Get Valide.
		@return Valide	  */
	public boolean isValide()
	{
		Object oo = get_Value(COLUMNNAME_IsValide);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message d&#039;Erreur.
		@param MessageErreur Message d&#039;Erreur
	*/
	public void setMessageErreur (String MessageErreur)
	{
		set_Value (COLUMNNAME_MessageErreur, MessageErreur);
	}

	/** Get Message d&#039;Erreur.
		@return Message d&#039;Erreur	  */
	public String getMessageErreur()
	{
		return (String)get_Value(COLUMNNAME_MessageErreur);
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

	/** Set Résultat de Test.
		@param ResultatTest Résultat de Test
	*/
	public void setResultatTest (BigDecimal ResultatTest)
	{
		set_Value (COLUMNNAME_ResultatTest, ResultatTest);
	}

	/** Get Résultat de Test.
		@return Résultat de Test	  */
	public BigDecimal getResultatTest()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ResultatTest);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}