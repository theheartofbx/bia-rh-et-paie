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
package org.sitracel.model.v2;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Delai_Quantite
 *  @author iDempiere (generated) 
 *  @version Release 11
 */
@SuppressWarnings("all")
public interface I_HR_Delai_Quantite 
{

    /** TableName=HR_Delai_Quantite */
    public static final String Table_Name = "HR_Delai_Quantite";

    /** AD_Table_ID=1000003 */
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

    /** Column name Affichage_Delai_Quantite */
    public static final String COLUMNNAME_Affichage_Delai_Quantite = "Affichage_Delai_Quantite";

	/** Set Nom de la Quantité ou du Délai.
	  * Nom de la Quantité ou du Délai
	  */
	public void setAffichage_Delai_Quantite (String Affichage_Delai_Quantite);

	/** Get Nom de la Quantité ou du Délai.
	  * Nom de la Quantité ou du Délai
	  */
	public String getAffichage_Delai_Quantite();

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

    /** Column name HR_Delai_Quantite_ID */
    public static final String COLUMNNAME_HR_Delai_Quantite_ID = "HR_Delai_Quantite_ID";

	/** Set Table Délais et Quantités	  */
	public void setHR_Delai_Quantite_ID (int HR_Delai_Quantite_ID);

	/** Get Table Délais et Quantités	  */
	public int getHR_Delai_Quantite_ID();

    /** Column name HR_Delai_Quantite_UU */
    public static final String COLUMNNAME_HR_Delai_Quantite_UU = "HR_Delai_Quantite_UU";

	/** Set HR_Delai_Quantite_UU	  */
	public void setHR_Delai_Quantite_UU (String HR_Delai_Quantite_UU);

	/** Get HR_Delai_Quantite_UU	  */
	public String getHR_Delai_Quantite_UU();

    /** Column name Index_Tri_Sex1 */
    public static final String COLUMNNAME_Index_Tri_Sex1 = "Index_Tri_Sex1";

	/** Set Premier Index de Tri Sex.
	  * Premier Index de Tri Sex
	  */
	public void setIndex_Tri_Sex1 (String Index_Tri_Sex1);

	/** Get Premier Index de Tri Sex.
	  * Premier Index de Tri Sex
	  */
	public String getIndex_Tri_Sex1();

    /** Column name Index_Tri_Sex2 */
    public static final String COLUMNNAME_Index_Tri_Sex2 = "Index_Tri_Sex2";

	/** Set Deuxième Index de Tri Sex.
	  * Deuxième Index de Tri Sex
	  */
	public void setIndex_Tri_Sex2 (String Index_Tri_Sex2);

	/** Get Deuxième Index de Tri Sex.
	  * Deuxième Index de Tri Sex
	  */
	public String getIndex_Tri_Sex2();

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

    /** Column name Quantite_Delai */
    public static final String COLUMNNAME_Quantite_Delai = "Quantite_Delai";

	/** Set Valeur de la Quantite ou du Délai.
	  * Valeur de la Quantite ou du Délai
	  */
	public void setQuantite_Delai (int Quantite_Delai);

	/** Get Valeur de la Quantite ou du Délai.
	  * Valeur de la Quantite ou du Délai
	  */
	public int getQuantite_Delai();

    /** Column name Quantite_Delai_Avant_Echeance */
    public static final String COLUMNNAME_Quantite_Delai_Avant_Echeance = "Quantite_Delai_Avant_Echeance";

	/** Set Quantite ou Délai Avant Echeance.
	  * Quantite ou Délai Avant Echeance
	  */
	public void setQuantite_Delai_Avant_Echeance (int Quantite_Delai_Avant_Echeance);

	/** Get Quantite ou Délai Avant Echeance.
	  * Quantite ou Délai Avant Echeance
	  */
	public int getQuantite_Delai_Avant_Echeance();

    /** Column name Type_Delai_Quantite */
    public static final String COLUMNNAME_Type_Delai_Quantite = "Type_Delai_Quantite";

	/** Set Type de Délai ou Quantité.
	  * Type de Délai ou Quantité
	  */
	public void setType_Delai_Quantite (String Type_Delai_Quantite);

	/** Get Type de Délai ou Quantité.
	  * Type de Délai ou Quantité
	  */
	public String getType_Delai_Quantite();

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
