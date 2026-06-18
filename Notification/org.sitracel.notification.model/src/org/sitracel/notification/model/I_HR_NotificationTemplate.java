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
package org.sitracel.notification.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_NotificationTemplate
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_NotificationTemplate 
{

    /** TableName=HR_NotificationTemplate */
    public static final String Table_Name = "HR_NotificationTemplate";

    /** AD_Table_ID=1013939 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name HR_NotificationTemplate_ID */
    public static final String COLUMNNAME_HR_NotificationTemplate_ID = "HR_NotificationTemplate_ID";

	/** Set Template des Notifications	  */
	public void setHR_NotificationTemplate_ID (int HR_NotificationTemplate_ID);

	/** Get Template des Notifications	  */
	public int getHR_NotificationTemplate_ID();

    /** Column name HR_NotificationTemplate_UU */
    public static final String COLUMNNAME_HR_NotificationTemplate_UU = "HR_NotificationTemplate_UU";

	/** Set HR_NotificationTemplate_UU	  */
	public void setHR_NotificationTemplate_UU (String HR_NotificationTemplate_UU);

	/** Get HR_NotificationTemplate_UU	  */
	public String getHR_NotificationTemplate_UU();

    /** Column name HR_NotificationType_ID */
    public static final String COLUMNNAME_HR_NotificationType_ID = "HR_NotificationType_ID";

	/** Set Type de Notification	  */
	public void setHR_NotificationType_ID (int HR_NotificationType_ID);

	/** Get Type de Notification	  */
	public int getHR_NotificationType_ID();

	public I_HR_NotificationType getHR_NotificationType() throws RuntimeException;

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Message_Contenu */
    public static final String COLUMNNAME_Message_Contenu = "Message_Contenu";

	/** Set Contenu du Message	  */
	public void setMessage_Contenu (String Message_Contenu);

	/** Get Contenu du Message	  */
	public String getMessage_Contenu();

    /** Column name Message_Objet */
    public static final String COLUMNNAME_Message_Objet = "Message_Objet";

	/** Set Objet du Message	  */
	public void setMessage_Objet (String Message_Objet);

	/** Get Objet du Message	  */
	public String getMessage_Objet();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
