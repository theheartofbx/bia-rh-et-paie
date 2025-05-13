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
package org.sitracel.paie.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Element_Base_Paie
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Element_Base_Paie 
{

    /** TableName=HR_Element_Base_Paie */
    public static final String Table_Name = "HR_Element_Base_Paie";

    /** AD_Table_ID=1008687 */
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

    /** Column name Base_Calcul_ID */
    public static final String COLUMNNAME_Base_Calcul_ID = "Base_Calcul_ID";

	/** Set Base de Calcul.
	  * Base de Calcul
	  */
	public void setBase_Calcul_ID (int Base_Calcul_ID);

	/** Get Base de Calcul.
	  * Base de Calcul
	  */
	public int getBase_Calcul_ID();

	public I_HR_Element_Base_Paie getBase_Calcul() throws RuntimeException;

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

    /** Column name Formule */
    public static final String COLUMNNAME_Formule = "Formule";

	/** Set Formule	  */
	public void setFormule (String Formule);

	/** Get Formule	  */
	public String getFormule();

    /** Column name HR_Element_Base_Paie_ID */
    public static final String COLUMNNAME_HR_Element_Base_Paie_ID = "HR_Element_Base_Paie_ID";

	/** Set Élément de Paie	  */
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID);

	/** Get Élément de Paie	  */
	public int getHR_Element_Base_Paie_ID();

    /** Column name HR_Element_Base_Paie_UU */
    public static final String COLUMNNAME_HR_Element_Base_Paie_UU = "HR_Element_Base_Paie_UU";

	/** Set HR_Element_Base_Paie_UU	  */
	public void setHR_Element_Base_Paie_UU (String HR_Element_Base_Paie_UU);

	/** Get HR_Element_Base_Paie_UU	  */
	public String getHR_Element_Base_Paie_UU();

    /** Column name HR_Rang_Calcul_ID */
    public static final String COLUMNNAME_HR_Rang_Calcul_ID = "HR_Rang_Calcul_ID";

	/** Set Rang de Calcul	  */
	public void setHR_Rang_Calcul_ID (int HR_Rang_Calcul_ID);

	/** Get Rang de Calcul	  */
	public int getHR_Rang_Calcul_ID();

	public I_HR_Rang_Calcul getHR_Rang_Calcul() throws RuntimeException;

    /** Column name HR_TypeDeCharge_ID */
    public static final String COLUMNNAME_HR_TypeDeCharge_ID = "HR_TypeDeCharge_ID";

	/** Set Type de Charge	  */
	public void setHR_TypeDeCharge_ID (int HR_TypeDeCharge_ID);

	/** Get Type de Charge	  */
	public int getHR_TypeDeCharge_ID();

	public I_HR_TypeDeCharge getHR_TypeDeCharge() throws RuntimeException;

    /** Column name HR_Type_Calcul_ID */
    public static final String COLUMNNAME_HR_Type_Calcul_ID = "HR_Type_Calcul_ID";

	/** Set Type de Calcul	  */
	public void setHR_Type_Calcul_ID (int HR_Type_Calcul_ID);

	/** Get Type de Calcul	  */
	public int getHR_Type_Calcul_ID();

	public I_HR_Type_Calcul getHR_Type_Calcul() throws RuntimeException;

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

    /** Column name IsCalcul_Initial */
    public static final String COLUMNNAME_IsCalcul_Initial = "IsCalcul_Initial";

	/** Set Est Inclus lors du Calcul Initial.
	  * Est Inclus lors du Calcul Initial
	  */
	public void setIsCalcul_Initial (boolean IsCalcul_Initial);

	/** Get Est Inclus lors du Calcul Initial.
	  * Est Inclus lors du Calcul Initial
	  */
	public boolean isCalcul_Initial();

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

    /** Column name Pourcentage */
    public static final String COLUMNNAME_Pourcentage = "Pourcentage";

	/** Set Pourcentage.
	  * Pourcentage
	  */
	public void setPourcentage (BigDecimal Pourcentage);

	/** Get Pourcentage.
	  * Pourcentage
	  */
	public BigDecimal getPourcentage();

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
