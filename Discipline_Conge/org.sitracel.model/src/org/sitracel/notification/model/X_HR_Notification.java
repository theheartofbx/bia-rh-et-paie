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

/** Generated Model for HR_Notification
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Notification")
public class X_HR_Notification extends PO implements I_HR_Notification, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260211L;

    /** Standard Constructor */
    public X_HR_Notification (Properties ctx, int HR_Notification_ID, String trxName)
    {
      super (ctx, HR_Notification_ID, trxName);
      /** if (HR_Notification_ID == 0)
        {
			setHR_Notification_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Notification (Properties ctx, int HR_Notification_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Notification_ID, trxName, virtualColumns);
      /** if (HR_Notification_ID == 0)
        {
			setHR_Notification_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Notification (Properties ctx, String HR_Notification_UU, String trxName)
    {
      super (ctx, HR_Notification_UU, trxName);
      /** if (HR_Notification_UU == null)
        {
			setHR_Notification_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Notification (Properties ctx, String HR_Notification_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Notification_UU, trxName, virtualColumns);
      /** if (HR_Notification_UU == null)
        {
			setHR_Notification_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Notification (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Notification[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
	{
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_ID)
			.getPO(getAD_Table_ID(), get_TrxName());
	}

	/** Set Table.
		@param AD_Table_ID Database Table information
	*/
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set HR_Notification_UU.
		@param HR_Notification_UU HR_Notification_UU
	*/
	public void setHR_Notification_UU (String HR_Notification_UU)
	{
		set_Value (COLUMNNAME_HR_Notification_UU, HR_Notification_UU);
	}

	/** Get HR_Notification_UU.
		@return HR_Notification_UU	  */
	public String getHR_Notification_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Notification_UU);
	}

	/** Set ID de l&#039;enregistrement.
		@param Numero_Enregistrement ID de l&#039;enregistrement
	*/
	public void setNumero_Enregistrement (int Numero_Enregistrement)
	{
		set_ValueNoCheck (COLUMNNAME_Numero_Enregistrement, Integer.valueOf(Numero_Enregistrement));
	}

	/** Get ID de l&#039;enregistrement.
		@return ID de l&#039;enregistrement	  */
	public int getNumero_Enregistrement()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Numero_Enregistrement);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}