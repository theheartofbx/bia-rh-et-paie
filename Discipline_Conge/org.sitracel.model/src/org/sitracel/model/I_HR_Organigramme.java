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
package org.sitracel.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Organigramme
 *  @author iDempiere (generated)
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Organigramme
{

    /** TableName=HR_Organigramme */
    public static final String Table_Name = "HR_Organigramme";

    /** AD_Table_ID=1003334 */
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

    /** Column name HR_Categorie_Responsabilite_ID */
    public static final String COLUMNNAME_HR_Categorie_Responsabilite_ID = "HR_Categorie_Responsabilite_ID";

	/** Set Catégorie de Responsabilité	  */
	public void setHR_Categorie_Responsabilite_ID (int HR_Categorie_Responsabilite_ID);

	/** Get Catégorie de Responsabilité	  */
	public int getHR_Categorie_Responsabilite_ID();

	public I_HR_Categorie_Responsabilite getHR_Categorie_Responsabilite() throws RuntimeException;

    /** Column name HR_Organigramme_ID */
    public static final String COLUMNNAME_HR_Organigramme_ID = "HR_Organigramme_ID";

	/** Set Organigramme	  */
	public void setHR_Organigramme_ID (int HR_Organigramme_ID);

	/** Get Organigramme	  */
	public int getHR_Organigramme_ID();

    /** Column name HR_Organigramme_UU */
    public static final String COLUMNNAME_HR_Organigramme_UU = "HR_Organigramme_UU";

	/** Set HR_Organigramme_UU	  */
	public void setHR_Organigramme_UU (String HR_Organigramme_UU);

	/** Get HR_Organigramme_UU	  */
	public String getHR_Organigramme_UU();

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

    /** Column name IsMessageAlerteDisplayed */
    public static final String COLUMNNAME_IsMessageAlerteDisplayed = "IsMessageAlerteDisplayed";

	/** Set Message d&#039;
Alerte Affiché.
	  * Message d&#039;
Alerte Affiché
	  */
	public void setIsMessageAlerteDisplayed (boolean IsMessageAlerteDisplayed);

	/** Get Message d&#039;
Alerte Affiché.
	  * Message d&#039;
Alerte Affiché
	  */
	public boolean isMessageAlerteDisplayed();

    /** Column name Message_Alerte */
    public static final String COLUMNNAME_Message_Alerte = "Message_Alerte";

	/** Set Message d&#039;
Alerte.
	  * Message d&#039;
Alerte
	  */
	public void setMessage_Alerte (String Message_Alerte);

	/** Get Message d&#039;
Alerte.
	  * Message d&#039;
Alerte
	  */
	public String getMessage_Alerte();

    /** Column name Poste_ID */
    public static final String COLUMNNAME_Poste_ID = "Poste_ID";

	/** Set Nom du Poste :.
	  * Nom du Poste :
	  */
	public void setPoste_ID (int Poste_ID);

	/** Get Nom du Poste :.
	  * Nom du Poste :
	  */
	public int getPoste_ID();

	public org.eevolution.model.I_HR_Job getPoste() throws RuntimeException;

    /** Column name Poste_Responsable_ID */
    public static final String COLUMNNAME_Poste_Responsable_ID = "Poste_Responsable_ID";

	/** Set Poste du Responsable .
	  * Poste du Responsable
	  */
	public void setPoste_Responsable_ID (int Poste_Responsable_ID);

	/** Get Poste du Responsable .
	  * Poste du Responsable
	  */
	public int getPoste_Responsable_ID();

	public org.eevolution.model.I_HR_Job getPoste_Responsable() throws RuntimeException;

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
