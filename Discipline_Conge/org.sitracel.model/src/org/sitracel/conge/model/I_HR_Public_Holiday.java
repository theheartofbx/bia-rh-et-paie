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
package org.sitracel.conge.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Public_Holiday
 *  @author iDempiere (generated) 
 *  @version Release 11
 */
@SuppressWarnings("all")
public interface I_HR_Public_Holiday 
{

    /** TableName=HR_Public_Holiday */
    public static final String Table_Name = "HR_Public_Holiday";

    /** AD_Table_ID=1003738 */
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

    /** Column name Date_Jour_Ferie */
    public static final String COLUMNNAME_Date_Jour_Ferie = "Date_Jour_Ferie";

	/** Set Date du Jour Férié.
	  * Date du Jour Férié
	  */
	public void setDate_Jour_Ferie (Timestamp Date_Jour_Ferie);

	/** Get Date du Jour Férié.
	  * Date du Jour Férié
	  */
	public Timestamp getDate_Jour_Ferie();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name HR_Public_Holiday_ID */
    public static final String COLUMNNAME_HR_Public_Holiday_ID = "HR_Public_Holiday_ID";

	/** Set Jours Fériés	  */
	public void setHR_Public_Holiday_ID (int HR_Public_Holiday_ID);

	/** Get Jours Fériés	  */
	public int getHR_Public_Holiday_ID();

    /** Column name HR_Public_Holiday_UU */
    public static final String COLUMNNAME_HR_Public_Holiday_UU = "HR_Public_Holiday_UU";

	/** Set HR_Public_Holiday_UU	  */
	public void setHR_Public_Holiday_UU (String HR_Public_Holiday_UU);

	/** Get HR_Public_Holiday_UU	  */
	public String getHR_Public_Holiday_UU();

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

    /** Column name Nom_Jour_Ferie */
    public static final String COLUMNNAME_Nom_Jour_Ferie = "Nom_Jour_Ferie";

	/** Set Nom du Jour Férié.
	  * Nom du Jour Férié
	  */
	public void setNom_Jour_Ferie (String Nom_Jour_Ferie);

	/** Get Nom du Jour Férié.
	  * Nom du Jour Férié
	  */
	public String getNom_Jour_Ferie();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
