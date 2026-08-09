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
package org.sitracel.formation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_FormationCatalogue
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_FormationCatalogue 
{

    /** TableName=HR_FormationCatalogue */
    public static final String Table_Name = "HR_FormationCatalogue";

    /** AD_Table_ID=1015454 */
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

    /** Column name Duree_Prevue */
    public static final String COLUMNNAME_Duree_Prevue = "Duree_Prevue";

	/** Set Nombre d&#039;
Heures Prévue.
	  * Nombre d&#039;
Heures Prévue
	  */
	public void setDuree_Prevue (int Duree_Prevue);

	/** Get Nombre d&#039;
Heures Prévue.
	  * Nombre d&#039;
Heures Prévue
	  */
	public int getDuree_Prevue();

    /** Column name HR_FormationCatalogue_ID */
    public static final String COLUMNNAME_HR_FormationCatalogue_ID = "HR_FormationCatalogue_ID";

	/** Set Catalogue de Formation	  */
	public void setHR_FormationCatalogue_ID (int HR_FormationCatalogue_ID);

	/** Get Catalogue de Formation	  */
	public int getHR_FormationCatalogue_ID();

    /** Column name HR_FormationCatalogue_UU */
    public static final String COLUMNNAME_HR_FormationCatalogue_UU = "HR_FormationCatalogue_UU";

	/** Set HR_FormationCatalogue_UU	  */
	public void setHR_FormationCatalogue_UU (String HR_FormationCatalogue_UU);

	/** Get HR_FormationCatalogue_UU	  */
	public String getHR_FormationCatalogue_UU();

    /** Column name HR_FormationDomaine_ID */
    public static final String COLUMNNAME_HR_FormationDomaine_ID = "HR_FormationDomaine_ID";

	/** Set Domaine de Formation	  */
	public void setHR_FormationDomaine_ID (int HR_FormationDomaine_ID);

	/** Get Domaine de Formation	  */
	public int getHR_FormationDomaine_ID();

	public I_HR_FormationDomaine getHR_FormationDomaine() throws RuntimeException;

    /** Column name HR_FormationType_ID */
    public static final String COLUMNNAME_HR_FormationType_ID = "HR_FormationType_ID";

	/** Set Type de Formation	  */
	public void setHR_FormationType_ID (int HR_FormationType_ID);

	/** Get Type de Formation	  */
	public int getHR_FormationType_ID();

	public I_HR_FormationType getHR_FormationType() throws RuntimeException;

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

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
