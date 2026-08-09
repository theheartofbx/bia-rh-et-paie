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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_FormationDemande
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_FormationDemande")
public class X_HR_FormationDemande extends PO implements I_HR_FormationDemande, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260809L;

    /** Standard Constructor */
    public X_HR_FormationDemande (Properties ctx, int HR_FormationDemande_ID, String trxName)
    {
      super (ctx, HR_FormationDemande_ID, trxName);
      /** if (HR_FormationDemande_ID == 0)
        {
			setHR_FormationDemande_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationDemande (Properties ctx, int HR_FormationDemande_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationDemande_ID, trxName, virtualColumns);
      /** if (HR_FormationDemande_ID == 0)
        {
			setHR_FormationDemande_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationDemande (Properties ctx, String HR_FormationDemande_UU, String trxName)
    {
      super (ctx, HR_FormationDemande_UU, trxName);
      /** if (HR_FormationDemande_UU == null)
        {
			setHR_FormationDemande_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_FormationDemande (Properties ctx, String HR_FormationDemande_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_FormationDemande_UU, trxName, virtualColumns);
      /** if (HR_FormationDemande_UU == null)
        {
			setHR_FormationDemande_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_FormationDemande (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_FormationDemande[")
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

	/** Set Date Décision.
		@param Date_Decision Date Décision
	*/
	public void setDate_Decision (Timestamp Date_Decision)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Decision, Date_Decision);
	}

	/** Get Date Décision.
		@return Date Décision	  */
	public Timestamp getDate_Decision()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Decision);
	}

	/** Set Date de Demande.
		@param Date_Demande Date de Demande
	*/
	public void setDate_Demande (Timestamp Date_Demande)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Demande, Date_Demande);
	}

	/** Get Date de Demande.
		@return Date de Demande	  */
	public Timestamp getDate_Demande()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Demande);
	}

	/** Set Demande de Participation Formation.
		@param HR_FormationDemande_ID Demande de Participation Formation
	*/
	public void setHR_FormationDemande_ID (int HR_FormationDemande_ID)
	{
		if (HR_FormationDemande_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationDemande_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationDemande_ID, Integer.valueOf(HR_FormationDemande_ID));
	}

	/** Get Demande de Participation Formation.
		@return Demande de Participation Formation	  */
	public int getHR_FormationDemande_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationDemande_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_FormationDemande_UU.
		@param HR_FormationDemande_UU HR_FormationDemande_UU
	*/
	public void setHR_FormationDemande_UU (String HR_FormationDemande_UU)
	{
		set_Value (COLUMNNAME_HR_FormationDemande_UU, HR_FormationDemande_UU);
	}

	/** Get HR_FormationDemande_UU.
		@return HR_FormationDemande_UU	  */
	public String getHR_FormationDemande_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_FormationDemande_UU);
	}

	public I_HR_FormationSession getHR_FormationSession() throws RuntimeException
	{
		return (I_HR_FormationSession)MTable.get(getCtx(), I_HR_FormationSession.Table_ID)
			.getPO(getHR_FormationSession_ID(), get_TrxName());
	}

	/** Set Session de Formation.
		@param HR_FormationSession_ID Session de Formation
	*/
	public void setHR_FormationSession_ID (int HR_FormationSession_ID)
	{
		if (HR_FormationSession_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_FormationSession_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_FormationSession_ID, Integer.valueOf(HR_FormationSession_ID));
	}

	/** Get Session de Formation.
		@return Session de Formation	  */
	public int getHR_FormationSession_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_FormationSession_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rejeté(e).
		@param IsRejetee Rejeté(e)
	*/
	public void setIsRejetee (boolean IsRejetee)
	{
		set_Value (COLUMNNAME_IsRejetee, Boolean.valueOf(IsRejetee));
	}

	/** Get Rejeté(e).
		@return Rejeté(e)
	  */
	public boolean isRejetee()
	{
		Object oo = get_Value(COLUMNNAME_IsRejetee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Validé(e).
		@param IsValidee Validé(e)
	*/
	public void setIsValidee (boolean IsValidee)
	{
		set_Value (COLUMNNAME_IsValidee, Boolean.valueOf(IsValidee));
	}

	/** Get Validé(e).
		@return Validé(e)
	  */
	public boolean isValidee()
	{
		Object oo = get_Value(COLUMNNAME_IsValidee);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Motif .
		@param Motif Motif
	*/
	public void setMotif (String Motif)
	{
		set_ValueNoCheck (COLUMNNAME_Motif, Motif);
	}

	/** Get Motif .
		@return Motif
	  */
	public String getMotif()
	{
		return (String)get_Value(COLUMNNAME_Motif);
	}

	/** Set Motif Rejet.
		@param Motif_Rejet Motif Rejet
	*/
	public void setMotif_Rejet (String Motif_Rejet)
	{
		set_Value (COLUMNNAME_Motif_Rejet, Motif_Rejet);
	}

	/** Get Motif Rejet.
		@return Motif Rejet	  */
	public String getMotif_Rejet()
	{
		return (String)get_Value(COLUMNNAME_Motif_Rejet);
	}

	/** Set Peut Valider.
		@param PeutValider Peut Valider
	*/
	public void setPeutValider (boolean PeutValider)
	{
		set_ValueNoCheck (COLUMNNAME_PeutValider, Boolean.valueOf(PeutValider));
	}

	/** Get Peut Valider.
		@return Peut Valider
	  */
	public boolean isPeutValider()
	{
		Object oo = get_Value(COLUMNNAME_PeutValider);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rejeter.
		@param Rejeter Rejeter
	*/
	public void setRejeter (String Rejeter)
	{
		set_Value (COLUMNNAME_Rejeter, Rejeter);
	}

	/** Get Rejeter.
		@return Rejeter
	  */
	public String getRejeter()
	{
		return (String)get_Value(COLUMNNAME_Rejeter);
	}

	public org.compiere.model.I_C_BPartner getValide_Rejete_Par_Nom() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getValide_Rejete_Par_Nom_ID(), get_TrxName());
	}

	/** Set Validé/rejeté par (Nom) :.
		@param Valide_Rejete_Par_Nom_ID Validé/rejeté par (Nom) :
	*/
	public void setValide_Rejete_Par_Nom_ID (int Valide_Rejete_Par_Nom_ID)
	{
		if (Valide_Rejete_Par_Nom_ID < 1)
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, null);
		else
			set_Value (COLUMNNAME_Valide_Rejete_Par_Nom_ID, Integer.valueOf(Valide_Rejete_Par_Nom_ID));
	}

	/** Get Validé/rejeté par (Nom) :.
		@return Validé/rejeté par (Nom) :
	  */
	public int getValide_Rejete_Par_Nom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Valide_Rejete_Par_Nom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valider .
		@param Valider Valider 
	*/
	public void setValider (String Valider)
	{
		set_Value (COLUMNNAME_Valider, Valider);
	}

	/** Get Valider .
		@return Valider 
	  */
	public String getValider()
	{
		return (String)get_Value(COLUMNNAME_Valider);
	}
}