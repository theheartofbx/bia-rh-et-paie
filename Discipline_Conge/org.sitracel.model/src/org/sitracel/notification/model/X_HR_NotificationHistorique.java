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
package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_NotificationHistorique
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_NotificationHistorique")
public class X_HR_NotificationHistorique extends PO implements I_HR_NotificationHistorique, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260211L;

    /** Standard Constructor */
    public X_HR_NotificationHistorique (Properties ctx, int HR_NotificationHistorique_ID, String trxName)
    {
      super (ctx, HR_NotificationHistorique_ID, trxName);
      /** if (HR_NotificationHistorique_ID == 0)
        {
			setHR_NotificationHistorique_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationHistorique (Properties ctx, int HR_NotificationHistorique_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationHistorique_ID, trxName, virtualColumns);
      /** if (HR_NotificationHistorique_ID == 0)
        {
			setHR_NotificationHistorique_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationHistorique (Properties ctx, String HR_NotificationHistorique_UU, String trxName)
    {
      super (ctx, HR_NotificationHistorique_UU, trxName);
      /** if (HR_NotificationHistorique_UU == null)
        {
			setHR_NotificationHistorique_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationHistorique (Properties ctx, String HR_NotificationHistorique_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationHistorique_UU, trxName, virtualColumns);
      /** if (HR_NotificationHistorique_UU == null)
        {
			setHR_NotificationHistorique_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_NotificationHistorique (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_NotificationHistorique[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date d&#039;Envoie.
		@param Date_Envoie Date d&#039;Envoie
	*/
	public void setDate_Envoie (Timestamp Date_Envoie)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Envoie, Date_Envoie);
	}

	/** Get Date d&#039;Envoie.
		@return Date d&#039;Envoie	  */
	public Timestamp getDate_Envoie()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Envoie);
	}

	public I_HR_NotificationDestinataire getHR_NotificationDestinataire() throws RuntimeException
	{
		return (I_HR_NotificationDestinataire)MTable.get(getCtx(), I_HR_NotificationDestinataire.Table_ID)
			.getPO(getHR_NotificationDestinataire_ID(), get_TrxName());
	}

	/** Set Destinataire des Notifications.
		@param HR_NotificationDestinataire_ID Destinataire des Notifications
	*/
	public void setHR_NotificationDestinataire_ID (int HR_NotificationDestinataire_ID)
	{
		if (HR_NotificationDestinataire_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationDestinataire_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationDestinataire_ID, Integer.valueOf(HR_NotificationDestinataire_ID));
	}

	/** Get Destinataire des Notifications.
		@return Destinataire des Notifications	  */
	public int getHR_NotificationDestinataire_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationDestinataire_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Historique des Notifications.
		@param HR_NotificationHistorique_ID Historique des Notifications
	*/
	public void setHR_NotificationHistorique_ID (int HR_NotificationHistorique_ID)
	{
		if (HR_NotificationHistorique_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationHistorique_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationHistorique_ID, Integer.valueOf(HR_NotificationHistorique_ID));
	}

	/** Get Historique des Notifications.
		@return Historique des Notifications	  */
	public int getHR_NotificationHistorique_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationHistorique_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_NotificationHistorique_UU.
		@param HR_NotificationHistorique_UU HR_NotificationHistorique_UU
	*/
	public void setHR_NotificationHistorique_UU (String HR_NotificationHistorique_UU)
	{
		set_Value (COLUMNNAME_HR_NotificationHistorique_UU, HR_NotificationHistorique_UU);
	}

	/** Get HR_NotificationHistorique_UU.
		@return HR_NotificationHistorique_UU	  */
	public String getHR_NotificationHistorique_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_NotificationHistorique_UU);
	}

	public I_HR_NotificationStatut getHR_NotificationStatut() throws RuntimeException
	{
		return (I_HR_NotificationStatut)MTable.get(getCtx(), I_HR_NotificationStatut.Table_ID)
			.getPO(getHR_NotificationStatut_ID(), get_TrxName());
	}

	/** Set Statut de la Notification.
		@param HR_NotificationStatut_ID Statut de la Notification
	*/
	public void setHR_NotificationStatut_ID (int HR_NotificationStatut_ID)
	{
		if (HR_NotificationStatut_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationStatut_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationStatut_ID, Integer.valueOf(HR_NotificationStatut_ID));
	}

	/** Get Statut de la Notification.
		@return Statut de la Notification	  */
	public int getHR_NotificationStatut_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationStatut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Message d&#039;Erreur.
		@param Message_Erreur Message d&#039;Erreur
	*/
	public void setMessage_Erreur (String Message_Erreur)
	{
		set_Value (COLUMNNAME_Message_Erreur, Message_Erreur);
	}

	/** Get Message d&#039;Erreur.
		@return Message d&#039;Erreur
	  */
	public String getMessage_Erreur()
	{
		return (String)get_Value(COLUMNNAME_Message_Erreur);
	}
}