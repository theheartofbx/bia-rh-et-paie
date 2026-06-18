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

/** Generated Interface for HR_Notification
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Notification 
{

    /** TableName=HR_Notification */
    public static final String Table_Name = "HR_Notification";

    /** AD_Table_ID=1013434 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

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

    /** Column name Date_Emission */
    public static final String COLUMNNAME_Date_Emission = "Date_Emission";

	/** Set Date d&#039;
Emission.
	  * Date d&#039;
Emission
	  */
	public void setDate_Emission (Timestamp Date_Emission);

	/** Get Date d&#039;
Emission.
	  * Date d&#039;
Emission
	  */
	public Timestamp getDate_Emission();

    /** Column name HR_NotificationType_ID */
    public static final String COLUMNNAME_HR_NotificationType_ID = "HR_NotificationType_ID";

	/** Set Type de Notification	  */
	public void setHR_NotificationType_ID (int HR_NotificationType_ID);

	/** Get Type de Notification	  */
	public int getHR_NotificationType_ID();

	public I_HR_NotificationType getHR_NotificationType() throws RuntimeException;

    /** Column name HR_Notification_ID */
    public static final String COLUMNNAME_HR_Notification_ID = "HR_Notification_ID";

	/** Set Notification	  */
	public void setHR_Notification_ID (int HR_Notification_ID);

	/** Get Notification	  */
	public int getHR_Notification_ID();

    /** Column name HR_Notification_UU */
    public static final String COLUMNNAME_HR_Notification_UU = "HR_Notification_UU";

	/** Set HR_Notification_UU	  */
	public void setHR_Notification_UU (String HR_Notification_UU);

	/** Get HR_Notification_UU	  */
	public String getHR_Notification_UU();

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

    /** Column name Numero_Enregistrement */
    public static final String COLUMNNAME_Numero_Enregistrement = "Numero_Enregistrement";

	/** Set ID de l&#039;
enregistrement	  */
	public void setNumero_Enregistrement (int Numero_Enregistrement);

	/** Get ID de l&#039;
enregistrement	  */
	public int getNumero_Enregistrement();

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
