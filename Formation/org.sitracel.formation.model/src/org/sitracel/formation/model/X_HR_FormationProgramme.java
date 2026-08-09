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
package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_FormationProgramme
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_FormationProgramme")
public class X_HR_FormationProgramme extends PO implements I_HR_FormationProgramme, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260809L;

    /** Standard Constructor */
    public X_HR_FormationProgramme (Properties ctx, int HR_FormationProgramme_ID, String trxName)
    {
      super (ctx, HR_FormationProgramme_ID, trxName);
      /** if (HR_FormationProgramme_ID == 0)
        {
			setHR_FormationProgramme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationProgramme (Properties ctx, int HR_FormationProgramme_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationProgramme_ID, trxName, virtualColumns);
      /** if (HR_FormationProgramme_ID == 0)
        {
			setHR_FormationProgramme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationProgramme (Properties ctx, String HR_FormationProgramme_UU, String trxName)
    {
      super (ctx, HR_FormationProgramme_UU, trxName);
      /** if (HR_FormationProgramme_UU == null)
        {
			setHR_FormationProgramme_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationProgramme (Properties ctx, String HR_FormationProgramme_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationProgramme_UU, trxName, virtualColumns);
      /** if (HR_FormationProgramme_UU == null)
        {
			setHR_FormationProgramme_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_FormationProgramme (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_FormationProgramme[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Nombre Total d&#039;Heures.
		@param Duree_Totale_Heures Nombre Total d&#039;Heures
	*/
	public void setDuree_Totale_Heures (int Duree_Totale_Heures)
	{
		set_Value (COLUMNNAME_Duree_Totale_Heures, Integer.valueOf(Duree_Totale_Heures));
	}

	/** Get Nombre Total d&#039;Heures.
		@return Nombre Total d&#039;Heures
	  */
	public int getDuree_Totale_Heures()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duree_Totale_Heures);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_FormationCatalogue getHR_FormationCatalogue() throws RuntimeException
	{
		return (I_HR_FormationCatalogue)MTable.get(getCtx(), I_HR_FormationCatalogue.Table_ID)
			.getPO(getHR_FormationCatalogue_ID(), get_TrxName());
	}

	/** Set Catalogue de Formation.
		@param HR_FormationCatalogue_ID Catalogue de Formation
	*/
	public void setHR_FormationCatalogue_ID (int HR_FormationCatalogue_ID)
	{
		if (HR_FormationCatalogue_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationCatalogue_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationCatalogue_ID, Integer.valueOf(HR_FormationCatalogue_ID));
	}

	/** Get Catalogue de Formation.
		@return Catalogue de Formation	  */
	public int getHR_FormationCatalogue_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationCatalogue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_FormationModule getHR_FormationModule() throws RuntimeException
	{
		return (I_HR_FormationModule)MTable.get(getCtx(), I_HR_FormationModule.Table_ID)
			.getPO(getHR_FormationModule_ID(), get_TrxName());
	}

	/** Set Modules de Formation.
		@param HR_FormationModule_ID Modules de Formation
	*/
	public void setHR_FormationModule_ID (int HR_FormationModule_ID)
	{
		if (HR_FormationModule_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationModule_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationModule_ID, Integer.valueOf(HR_FormationModule_ID));
	}

	/** Get Modules de Formation.
		@return Modules de Formation	  */
	public int getHR_FormationModule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationModule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Programme de Formation.
		@param HR_FormationProgramme_ID Programme de Formation
	*/
	public void setHR_FormationProgramme_ID (int HR_FormationProgramme_ID)
	{
		if (HR_FormationProgramme_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationProgramme_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationProgramme_ID, Integer.valueOf(HR_FormationProgramme_ID));
	}

	/** Get Programme de Formation.
		@return Programme de Formation	  */
	public int getHR_FormationProgramme_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationProgramme_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_FormationProgramme_UU.
		@param HR_FormationProgramme_UU HR_FormationProgramme_UU
	*/
	public void setHR_FormationProgramme_UU (String HR_FormationProgramme_UU)
	{
		set_Value (COLUMNNAME_HR_FormationProgramme_UU, HR_FormationProgramme_UU);
	}

	/** Get HR_FormationProgramme_UU.
		@return HR_FormationProgramme_UU	  */
	public String getHR_FormationProgramme_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_FormationProgramme_UU);
	}

	/** Set Nombre de Partie.
		@param Nombre_Partie Nombre de Partie
	*/
	public void setNombre_Partie (int Nombre_Partie)
	{
		set_Value (COLUMNNAME_Nombre_Partie, Integer.valueOf(Nombre_Partie));
	}

	/** Get Nombre de Partie.
		@return Nombre de Partie
	  */
	public int getNombre_Partie()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Partie);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param SeqNo Method of ordering records; lowest number comes first
	*/
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}