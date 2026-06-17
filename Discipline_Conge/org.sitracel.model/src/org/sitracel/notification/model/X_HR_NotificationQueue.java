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

/** Generated Model for HR_NotificationQueue
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_NotificationQueue")
public class X_HR_NotificationQueue extends PO implements I_HR_NotificationQueue, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260211L;

    /** Standard Constructor */
    public X_HR_NotificationQueue (Properties ctx, int HR_NotificationQueue_ID, String trxName)
    {
      super (ctx, HR_NotificationQueue_ID, trxName);
      /** if (HR_NotificationQueue_ID == 0)
        {
			setHR_NotificationQueue_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationQueue (Properties ctx, int HR_NotificationQueue_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationQueue_ID, trxName, virtualColumns);
      /** if (HR_NotificationQueue_ID == 0)
        {
			setHR_NotificationQueue_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationQueue (Properties ctx, String HR_NotificationQueue_UU, String trxName)
    {
      super (ctx, HR_NotificationQueue_UU, trxName);
      /** if (HR_NotificationQueue_UU == null)
        {
			setHR_NotificationQueue_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationQueue (Properties ctx, String HR_NotificationQueue_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationQueue_UU, trxName, virtualColumns);
      /** if (HR_NotificationQueue_UU == null)
        {
			setHR_NotificationQueue_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_NotificationQueue (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_NotificationQueue[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date d&#039;Emission.
		@param Date_Emission Date d&#039;Emission
	*/
	public void setDate_Emission (Timestamp Date_Emission)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Emission, Date_Emission);
	}

	/** Get Date d&#039;Emission.
		@return Date d&#039;Emission
	  */
	public Timestamp getDate_Emission()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Emission);
	}

	public I_HR_NotificationCanal getHR_NotificationCanal() throws RuntimeException
	{
		return (I_HR_NotificationCanal)MTable.get(getCtx(), I_HR_NotificationCanal.Table_ID)
			.getPO(getHR_NotificationCanal_ID(), get_TrxName());
	}

	/** Set Canal de Notification.
		@param HR_NotificationCanal_ID Canal de Notification
	*/
	public void setHR_NotificationCanal_ID (int HR_NotificationCanal_ID)
	{
		if (HR_NotificationCanal_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationCanal_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationCanal_ID, Integer.valueOf(HR_NotificationCanal_ID));
	}

	/** Get Canal de Notification.
		@return Canal de Notification	  */
	public int getHR_NotificationCanal_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationCanal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pile des Notifications.
		@param HR_NotificationQueue_ID Pile des Notifications
	*/
	public void setHR_NotificationQueue_ID (int HR_NotificationQueue_ID)
	{
		if (HR_NotificationQueue_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationQueue_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationQueue_ID, Integer.valueOf(HR_NotificationQueue_ID));
	}

	/** Get Pile des Notifications.
		@return Pile des Notifications	  */
	public int getHR_NotificationQueue_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationQueue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_NotificationQueue_UU.
		@param HR_NotificationQueue_UU HR_NotificationQueue_UU
	*/
	public void setHR_NotificationQueue_UU (String HR_NotificationQueue_UU)
	{
		set_Value (COLUMNNAME_HR_NotificationQueue_UU, HR_NotificationQueue_UU);
	}

	/** Get HR_NotificationQueue_UU.
		@return HR_NotificationQueue_UU	  */
	public String getHR_NotificationQueue_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_NotificationQueue_UU);
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

	public I_HR_Notification getHR_Notification() throws RuntimeException
	{
		return (I_HR_Notification)MTable.get(getCtx(), I_HR_Notification.Table_ID)
			.getPO(getHR_Notification_ID(), get_TrxName());
	}

	/** Set Notification.
		@param HR_Notification_ID Notification
	*/
	public void setHR_Notification_ID (int HR_Notification_ID)
	{
		if (HR_Notification_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Notification_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Notification_ID, Integer.valueOf(HR_Notification_ID));
	}

	/** Get Notification.
		@return Notification	  */
	public int getHR_Notification_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Notification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Message.
		@param Message EMail Message
	*/
	public void setMessage (String Message)
	{
		set_Value (COLUMNNAME_Message, Message);
	}

	/** Get Message.
		@return EMail Message
	  */
	public String getMessage()
	{
		return (String)get_Value(COLUMNNAME_Message);
	}

	/** Set Nombre de Tentative.
		@param Nombre_Tentative Nombre de Tentative
	*/
	public void setNombre_Tentative (int Nombre_Tentative)
	{
		set_Value (COLUMNNAME_Nombre_Tentative, Integer.valueOf(Nombre_Tentative));
	}

	/** Get Nombre de Tentative.
		@return Nombre de Tentative	  */
	public int getNombre_Tentative()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Tentative);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Objet.
		@param Objet Objet
	*/
	public void setObjet (String Objet)
	{
		set_Value (COLUMNNAME_Objet, Objet);
	}

	/** Get Objet.
		@return Objet	  */
	public String getObjet()
	{
		return (String)get_Value(COLUMNNAME_Objet);
	}
}