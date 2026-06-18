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
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.sitracel.model.I_HR_CibleType;

/** Generated Model for HR_NotificationAcces
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_NotificationAcces")
public class X_HR_NotificationAcces extends PO implements I_HR_NotificationAcces, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260211L;

    /** Standard Constructor */
    public X_HR_NotificationAcces (Properties ctx, int HR_NotificationAcces_ID, String trxName)
    {
      super (ctx, HR_NotificationAcces_ID, trxName);
      /** if (HR_NotificationAcces_ID == 0)
        {
			setHR_NotificationAcces_ID (0);
			setPriority (0);
// 100
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationAcces (Properties ctx, int HR_NotificationAcces_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationAcces_ID, trxName, virtualColumns);
      /** if (HR_NotificationAcces_ID == 0)
        {
			setHR_NotificationAcces_ID (0);
			setPriority (0);
// 100
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationAcces (Properties ctx, String HR_NotificationAcces_UU, String trxName)
    {
      super (ctx, HR_NotificationAcces_UU, trxName);
      /** if (HR_NotificationAcces_UU == null)
        {
			setHR_NotificationAcces_ID (0);
			setPriority (0);
// 100
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationAcces (Properties ctx, String HR_NotificationAcces_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationAcces_UU, trxName, virtualColumns);
      /** if (HR_NotificationAcces_UU == null)
        {
			setHR_NotificationAcces_ID (0);
			setPriority (0);
// 100
        } */
    }

    /** Load Constructor */
    public X_HR_NotificationAcces (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_NotificationAcces[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ID de la Cible.
		@param Cible_ID ID de la Cible
	*/
	public void setCible_ID (int Cible_ID)
	{
		if (Cible_ID < 1)
			set_ValueNoCheck (COLUMNNAME_Cible_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Cible_ID, Integer.valueOf(Cible_ID));
	}

	/** Get ID de la Cible.
		@return ID de la Cible	  */
	public int getCible_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Cible_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_CibleType getHR_CibleType() throws RuntimeException
	{
		return (I_HR_CibleType)MTable.get(getCtx(), I_HR_CibleType.Table_ID)
			.getPO(getHR_CibleType_ID(), get_TrxName());
	}

	/** Set Type de Cible.
		@param HR_CibleType_ID Type de Cible
	*/
	public void setHR_CibleType_ID (int HR_CibleType_ID)
	{
		if (HR_CibleType_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_CibleType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_CibleType_ID, Integer.valueOf(HR_CibleType_ID));
	}

	/** Get Type de Cible.
		@return Type de Cible	  */
	public int getHR_CibleType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_CibleType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_DestinataireType getHR_DestinataireType() throws RuntimeException
	{
		return (I_HR_DestinataireType)MTable.get(getCtx(), I_HR_DestinataireType.Table_ID)
			.getPO(getHR_DestinataireType_ID(), get_TrxName());
	}

	/** Set Type de Destinataire.
		@param HR_DestinataireType_ID Type de Destinataire
	*/
	public void setHR_DestinataireType_ID (int HR_DestinataireType_ID)
	{
		if (HR_DestinataireType_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_DestinataireType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_DestinataireType_ID, Integer.valueOf(HR_DestinataireType_ID));
	}

	/** Get Type de Destinataire.
		@return Type de Destinataire	  */
	public int getHR_DestinataireType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_DestinataireType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Paramètres des Accès des Notifications.
		@param HR_NotificationAcces_ID Paramètres des Accès des Notifications
	*/
	public void setHR_NotificationAcces_ID (int HR_NotificationAcces_ID)
	{
		if (HR_NotificationAcces_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationAcces_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationAcces_ID, Integer.valueOf(HR_NotificationAcces_ID));
	}

	/** Get Paramètres des Accès des Notifications.
		@return Paramètres des Accès des Notifications	  */
	public int getHR_NotificationAcces_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationAcces_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_NotificationAcces_UU.
		@param HR_NotificationAcces_UU HR_NotificationAcces_UU
	*/
	public void setHR_NotificationAcces_UU (String HR_NotificationAcces_UU)
	{
		set_Value (COLUMNNAME_HR_NotificationAcces_UU, HR_NotificationAcces_UU);
	}

	/** Get HR_NotificationAcces_UU.
		@return HR_NotificationAcces_UU	  */
	public String getHR_NotificationAcces_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_NotificationAcces_UU);
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

	public I_HR_NotificationType getHR_NotificationType() throws RuntimeException
	{
		return (I_HR_NotificationType)MTable.get(getCtx(), I_HR_NotificationType.Table_ID)
			.getPO(getHR_NotificationType_ID(), get_TrxName());
	}

	/** Set Type de Notification.
		@param HR_NotificationType_ID Type de Notification
	*/
	public void setHR_NotificationType_ID (int HR_NotificationType_ID)
	{
		if (HR_NotificationType_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationType_ID, Integer.valueOf(HR_NotificationType_ID));
	}

	/** Get Type de Notification.
		@return Type de Notification	  */
	public int getHR_NotificationType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Priority.
		@param Priority Indicates if this request is of a high, medium or low priority.
	*/
	public void setPriority (int Priority)
	{
		set_Value (COLUMNNAME_Priority, Integer.valueOf(Priority));
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Priority);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}