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
import org.compiere.model.*;

/** Generated Model for HR_NotificationTemplate
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_NotificationTemplate")
public class X_HR_NotificationTemplate extends PO implements I_HR_NotificationTemplate, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260211L;

    /** Standard Constructor */
    public X_HR_NotificationTemplate (Properties ctx, int HR_NotificationTemplate_ID, String trxName)
    {
      super (ctx, HR_NotificationTemplate_ID, trxName);
      /** if (HR_NotificationTemplate_ID == 0)
        {
			setHR_NotificationTemplate_ID (0);
			setHR_NotificationType_ID (0);
			setMessage_Contenu (null);
			setMessage_Objet (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationTemplate (Properties ctx, int HR_NotificationTemplate_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationTemplate_ID, trxName, virtualColumns);
      /** if (HR_NotificationTemplate_ID == 0)
        {
			setHR_NotificationTemplate_ID (0);
			setHR_NotificationType_ID (0);
			setMessage_Contenu (null);
			setMessage_Objet (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationTemplate (Properties ctx, String HR_NotificationTemplate_UU, String trxName)
    {
      super (ctx, HR_NotificationTemplate_UU, trxName);
      /** if (HR_NotificationTemplate_UU == null)
        {
			setHR_NotificationTemplate_ID (0);
			setHR_NotificationType_ID (0);
			setMessage_Contenu (null);
			setMessage_Objet (null);
        } */
    }

    /** Standard Constructor */
    public X_HR_NotificationTemplate (Properties ctx, String HR_NotificationTemplate_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_NotificationTemplate_UU, trxName, virtualColumns);
      /** if (HR_NotificationTemplate_UU == null)
        {
			setHR_NotificationTemplate_ID (0);
			setHR_NotificationType_ID (0);
			setMessage_Contenu (null);
			setMessage_Objet (null);
        } */
    }

    /** Load Constructor */
    public X_HR_NotificationTemplate (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_NotificationTemplate[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AD_Language AD_Reference_ID=106 */
	public static final int AD_LANGUAGE_AD_Reference_ID=106;
	/** Set Language.
		@param AD_Language Language for this entity
	*/
	public void setAD_Language (String AD_Language)
	{

		set_ValueNoCheck (COLUMNNAME_AD_Language, AD_Language);
	}

	/** Get Language.
		@return Language for this entity
	  */
	public String getAD_Language()
	{
		return (String)get_Value(COLUMNNAME_AD_Language);
	}

	/** Set Template des Notifications.
		@param HR_NotificationTemplate_ID Template des Notifications
	*/
	public void setHR_NotificationTemplate_ID (int HR_NotificationTemplate_ID)
	{
		if (HR_NotificationTemplate_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_NotificationTemplate_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_NotificationTemplate_ID, Integer.valueOf(HR_NotificationTemplate_ID));
	}

	/** Get Template des Notifications.
		@return Template des Notifications	  */
	public int getHR_NotificationTemplate_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NotificationTemplate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_NotificationTemplate_UU.
		@param HR_NotificationTemplate_UU HR_NotificationTemplate_UU
	*/
	public void setHR_NotificationTemplate_UU (String HR_NotificationTemplate_UU)
	{
		set_Value (COLUMNNAME_HR_NotificationTemplate_UU, HR_NotificationTemplate_UU);
	}

	/** Get HR_NotificationTemplate_UU.
		@return HR_NotificationTemplate_UU	  */
	public String getHR_NotificationTemplate_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_NotificationTemplate_UU);
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

	/** Set Contenu du Message.
		@param Message_Contenu Contenu du Message
	*/
	public void setMessage_Contenu (String Message_Contenu)
	{
		set_Value (COLUMNNAME_Message_Contenu, Message_Contenu);
	}

	/** Get Contenu du Message.
		@return Contenu du Message	  */
	public String getMessage_Contenu()
	{
		return (String)get_Value(COLUMNNAME_Message_Contenu);
	}

	/** Set Objet du Message.
		@param Message_Objet Objet du Message
	*/
	public void setMessage_Objet (String Message_Objet)
	{
		set_Value (COLUMNNAME_Message_Objet, Message_Objet);
	}

	/** Get Objet du Message.
		@return Objet du Message	  */
	public String getMessage_Objet()
	{
		return (String)get_Value(COLUMNNAME_Message_Objet);
	}
}