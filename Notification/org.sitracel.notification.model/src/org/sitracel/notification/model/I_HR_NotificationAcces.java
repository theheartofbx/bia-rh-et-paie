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

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;
import org.sitracel.model.I_HR_CibleType;

/** Generated Interface for HR_NotificationAcces
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_NotificationAcces 
{

    /** TableName=HR_NotificationAcces */
    public static final String Table_Name = "HR_NotificationAcces";

    /** AD_Table_ID=1014343 */
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

    /** Column name Cible_ID */
    public static final String COLUMNNAME_Cible_ID = "Cible_ID";

	/** Set ID de la Cible	  */
	public void setCible_ID (int Cible_ID);

	/** Get ID de la Cible	  */
	public int getCible_ID();

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

    /** Column name HR_CibleType_ID */
    public static final String COLUMNNAME_HR_CibleType_ID = "HR_CibleType_ID";

	/** Set Type de Cible	  */
	public void setHR_CibleType_ID (int HR_CibleType_ID);

	/** Get Type de Cible	  */
	public int getHR_CibleType_ID();

	public I_HR_CibleType getHR_CibleType() throws RuntimeException;

    /** Column name HR_DestinataireType_ID */
    public static final String COLUMNNAME_HR_DestinataireType_ID = "HR_DestinataireType_ID";

	/** Set Type de Destinataire	  */
	public void setHR_DestinataireType_ID (int HR_DestinataireType_ID);

	/** Get Type de Destinataire	  */
	public int getHR_DestinataireType_ID();

	public I_HR_DestinataireType getHR_DestinataireType() throws RuntimeException;

    /** Column name HR_NotificationAcces_ID */
    public static final String COLUMNNAME_HR_NotificationAcces_ID = "HR_NotificationAcces_ID";

	/** Set Paramètres des Accès des Notifications	  */
	public void setHR_NotificationAcces_ID (int HR_NotificationAcces_ID);

	/** Get Paramètres des Accès des Notifications	  */
	public int getHR_NotificationAcces_ID();

    /** Column name HR_NotificationAcces_UU */
    public static final String COLUMNNAME_HR_NotificationAcces_UU = "HR_NotificationAcces_UU";

	/** Set HR_NotificationAcces_UU	  */
	public void setHR_NotificationAcces_UU (String HR_NotificationAcces_UU);

	/** Get HR_NotificationAcces_UU	  */
	public String getHR_NotificationAcces_UU();

    /** Column name HR_NotificationCanal_ID */
    public static final String COLUMNNAME_HR_NotificationCanal_ID = "HR_NotificationCanal_ID";

	/** Set Canal de Notification	  */
	public void setHR_NotificationCanal_ID (int HR_NotificationCanal_ID);

	/** Get Canal de Notification	  */
	public int getHR_NotificationCanal_ID();

	public I_HR_NotificationCanal getHR_NotificationCanal() throws RuntimeException;

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

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority();

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
