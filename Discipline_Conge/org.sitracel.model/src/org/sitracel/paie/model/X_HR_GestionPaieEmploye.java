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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_GestionPaieEmploye
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_GestionPaieEmploye")
public class X_HR_GestionPaieEmploye extends PO implements I_HR_GestionPaieEmploye, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250507L;

    /** Standard Constructor */
    public X_HR_GestionPaieEmploye (Properties ctx, int HR_GestionPaieEmploye_ID, String trxName)
    {
      super (ctx, HR_GestionPaieEmploye_ID, trxName);
      /** if (HR_GestionPaieEmploye_ID == 0)
        {
			setHR_GestionPaieEmploye_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_GestionPaieEmploye (Properties ctx, int HR_GestionPaieEmploye_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_GestionPaieEmploye_ID, trxName, virtualColumns);
      /** if (HR_GestionPaieEmploye_ID == 0)
        {
			setHR_GestionPaieEmploye_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_GestionPaieEmploye (Properties ctx, String HR_GestionPaieEmploye_UU, String trxName)
    {
      super (ctx, HR_GestionPaieEmploye_UU, trxName);
      /** if (HR_GestionPaieEmploye_UU == null)
        {
			setHR_GestionPaieEmploye_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_GestionPaieEmploye (Properties ctx, String HR_GestionPaieEmploye_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_GestionPaieEmploye_UU, trxName, virtualColumns);
      /** if (HR_GestionPaieEmploye_UU == null)
        {
			setHR_GestionPaieEmploye_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_GestionPaieEmploye (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_GestionPaieEmploye[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Gestion des Éléments de Base de la Paie Employé.
		@param HR_GestionPaieEmploye_ID Gestion des Éléments de Base de la Paie Employé
	*/
	public void setHR_GestionPaieEmploye_ID (int HR_GestionPaieEmploye_ID)
	{
		if (HR_GestionPaieEmploye_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_GestionPaieEmploye_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_GestionPaieEmploye_ID, Integer.valueOf(HR_GestionPaieEmploye_ID));
	}

	/** Get Gestion des Éléments de Base de la Paie Employé.
		@return Gestion des Éléments de Base de la Paie Employé	  */
	public int getHR_GestionPaieEmploye_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_GestionPaieEmploye_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_GestionPaieEmploye_UU.
		@param HR_GestionPaieEmploye_UU HR_GestionPaieEmploye_UU
	*/
	public void setHR_GestionPaieEmploye_UU (String HR_GestionPaieEmploye_UU)
	{
		set_Value (COLUMNNAME_HR_GestionPaieEmploye_UU, HR_GestionPaieEmploye_UU);
	}

	/** Get HR_GestionPaieEmploye_UU.
		@return HR_GestionPaieEmploye_UU	  */
	public String getHR_GestionPaieEmploye_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_GestionPaieEmploye_UU);
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
			set_Value (COLUMNNAME_HR_Rang_Calcul_ID, null);
		else
			set_Value (COLUMNNAME_HR_Rang_Calcul_ID, Integer.valueOf(HR_Rang_Calcul_ID));
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

	/** Set Est Cotisable.
		@param IsCotisable Est Cotisable
	*/
	public void setIsCotisable (boolean IsCotisable)
	{
		set_Value (COLUMNNAME_IsCotisable, Boolean.valueOf(IsCotisable));
	}

	/** Get Est Cotisable.
		@return Est Cotisable
	  */
	public boolean isCotisable()
	{
		Object oo = get_Value(COLUMNNAME_IsCotisable);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Est Imposable.
		@param IsImposable Est Imposable
	*/
	public void setIsImposable (boolean IsImposable)
	{
		set_Value (COLUMNNAME_IsImposable, Boolean.valueOf(IsImposable));
	}

	/** Get Est Imposable.
		@return Est Imposable
	  */
	public boolean isImposable()
	{
		Object oo = get_Value(COLUMNNAME_IsImposable);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Participe au Calcul des Indemnités de Congé.
		@param IsIndemniteConge Participe au Calcul des Indemnités de Congé
	*/
	public void setIsIndemniteConge (boolean IsIndemniteConge)
	{
		set_Value (COLUMNNAME_IsIndemniteConge, Boolean.valueOf(IsIndemniteConge));
	}

	/** Get Participe au Calcul des Indemnités de Congé.
		@return Participe au Calcul des Indemnités de Congé	  */
	public boolean isIndemniteConge()
	{
		Object oo = get_Value(COLUMNNAME_IsIndemniteConge);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Est Utilisé dans le calcul de l&#039;IRPP.
		@param IsIrpp Est Utilisé dans le calcul de l&#039;IRPP
	*/
	public void setIsIrpp (boolean IsIrpp)
	{
		set_Value (COLUMNNAME_IsIrpp, Boolean.valueOf(IsIrpp));
	}

	/** Get Est Utilisé dans le calcul de l&#039;IRPP.
		@return Est Utilisé dans le calcul de l&#039;IRPP
	  */
	public boolean isIrpp()
	{
		Object oo = get_Value(COLUMNNAME_IsIrpp);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted Indicates if this document / line is printed
	*/
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted()
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
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