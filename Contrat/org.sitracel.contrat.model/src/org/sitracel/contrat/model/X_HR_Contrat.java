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
package org.sitracel.contrat.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.sitracel.recrutement.model.I_HR_Candidature;

/** Generated Model for HR_Contrat
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Contrat")
public class X_HR_Contrat extends PO implements I_HR_Contrat, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260807L;

    /** Standard Constructor */
    public X_HR_Contrat (Properties ctx, int HR_Contrat_ID, String trxName)
    {
      super (ctx, HR_Contrat_ID, trxName);
      /** if (HR_Contrat_ID == 0)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Contrat_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Contrat (Properties ctx, int HR_Contrat_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Contrat_ID, trxName, virtualColumns);
      /** if (HR_Contrat_ID == 0)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Contrat_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Contrat (Properties ctx, String HR_Contrat_UU, String trxName)
    {
      super (ctx, HR_Contrat_UU, trxName);
      /** if (HR_Contrat_UU == null)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Contrat_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Contrat (Properties ctx, String HR_Contrat_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Contrat_UU, trxName, virtualColumns);
      /** if (HR_Contrat_UU == null)
        {
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_Contrat_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Contrat (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Contrat[")
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

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_Value (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
	}

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_Value (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
	}

	/** Set Date de Fin Prévue.
		@param Date_Fin_Prevue Date de Fin Prévue
	*/
	public void setDate_Fin_Prevue (Timestamp Date_Fin_Prevue)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin_Prevue, Date_Fin_Prevue);
	}

	/** Get Date de Fin Prévue.
		@return Date de Fin Prévue	  */
	public Timestamp getDate_Fin_Prevue()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin_Prevue);
	}

	/** Set Date de Rupture.
		@param Date_Rupture Date de Rupture
	*/
	public void setDate_Rupture (Timestamp Date_Rupture)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Rupture, Date_Rupture);
	}

	/** Get Date de Rupture.
		@return Date de Rupture	  */
	public Timestamp getDate_Rupture()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Rupture);
	}

	public I_HR_Candidature getHR_Candidature() throws RuntimeException
	{
		return (I_HR_Candidature)MTable.get(getCtx(), I_HR_Candidature.Table_ID)
			.getPO(getHR_Candidature_ID(), get_TrxName());
	}

	/** Set Candidature.
		@param HR_Candidature_ID Candidature
	*/
	public void setHR_Candidature_ID (int HR_Candidature_ID)
	{
		if (HR_Candidature_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Candidature_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Candidature_ID, Integer.valueOf(HR_Candidature_ID));
	}

	/** Get Candidature.
		@return Candidature	  */
	public int getHR_Candidature_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Candidature_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_ContratStatut getHR_ContratStatut() throws RuntimeException
	{
		return (I_HR_ContratStatut)MTable.get(getCtx(), I_HR_ContratStatut.Table_ID)
			.getPO(getHR_ContratStatut_ID(), get_TrxName());
	}

	/** Set Statuts Contrat de travail.
		@param HR_ContratStatut_ID Statuts Contrat de travail
	*/
	public void setHR_ContratStatut_ID (int HR_ContratStatut_ID)
	{
		if (HR_ContratStatut_ID < 1)
			set_Value (COLUMNNAME_HR_ContratStatut_ID, null);
		else
			set_Value (COLUMNNAME_HR_ContratStatut_ID, Integer.valueOf(HR_ContratStatut_ID));
	}

	/** Get Statuts Contrat de travail.
		@return Statuts Contrat de travail	  */
	public int getHR_ContratStatut_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ContratStatut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_ContratType getHR_ContratType() throws RuntimeException
	{
		return (I_HR_ContratType)MTable.get(getCtx(), I_HR_ContratType.Table_ID)
			.getPO(getHR_ContratType_ID(), get_TrxName());
	}

	/** Set Type de Contrat.
		@param HR_ContratType_ID Type de Contrat
	*/
	public void setHR_ContratType_ID (int HR_ContratType_ID)
	{
		if (HR_ContratType_ID < 1)
			set_Value (COLUMNNAME_HR_ContratType_ID, null);
		else
			set_Value (COLUMNNAME_HR_ContratType_ID, Integer.valueOf(HR_ContratType_ID));
	}

	/** Get Type de Contrat.
		@return Type de Contrat	  */
	public int getHR_ContratType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ContratType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Contrat.
		@param HR_Contrat_ID Contrat
	*/
	public void setHR_Contrat_ID (int HR_Contrat_ID)
	{
		if (HR_Contrat_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Contrat_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Contrat_ID, Integer.valueOf(HR_Contrat_ID));
	}

	/** Get Contrat.
		@return Contrat	  */
	public int getHR_Contrat_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contrat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Contrat_UU.
		@param HR_Contrat_UU HR_Contrat_UU
	*/
	public void setHR_Contrat_UU (String HR_Contrat_UU)
	{
		set_Value (COLUMNNAME_HR_Contrat_UU, HR_Contrat_UU);
	}

	/** Get HR_Contrat_UU.
		@return HR_Contrat_UU	  */
	public String getHR_Contrat_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Contrat_UU);
	}

	/** Set Motif de Rupture.
		@param Motif_Rupture Motif de Rupture
	*/
	public void setMotif_Rupture (String Motif_Rupture)
	{
		set_Value (COLUMNNAME_Motif_Rupture, Motif_Rupture);
	}

	/** Get Motif de Rupture.
		@return Motif de Rupture	  */
	public String getMotif_Rupture()
	{
		return (String)get_Value(COLUMNNAME_Motif_Rupture);
	}

	/** Set Référence document.
		@param Reference_Document Référence document
	*/
	public void setReference_Document (String Reference_Document)
	{
		set_Value (COLUMNNAME_Reference_Document, Reference_Document);
	}

	/** Get Référence document.
		@return Référence document	  */
	public String getReference_Document()
	{
		return (String)get_Value(COLUMNNAME_Reference_Document);
	}
}