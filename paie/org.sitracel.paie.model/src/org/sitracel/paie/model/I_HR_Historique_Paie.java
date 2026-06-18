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

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Historique_Paie
 *  @author iDempiere (generated)
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Historique_Paie
{

    /** TableName=HR_Historique_Paie */
    public static final String Table_Name = "HR_Historique_Paie";

    /** AD_Table_ID=1009495 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name Date_Debut */
    public static final String COLUMNNAME_Date_Debut = "Date_Debut";

	/** Set à Partir de :.
	  * à Partir de :
	  */
	public void setDate_Debut (Timestamp Date_Debut);

	/** Get à Partir de :.
	  * à Partir de :
	  */
	public Timestamp getDate_Debut();

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

    /** Column name HR_Element_Base_Paie_ID */
    public static final String COLUMNNAME_HR_Element_Base_Paie_ID = "HR_Element_Base_Paie_ID";

	/** Set Élément de Paie	  */
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID);

	/** Get Élément de Paie	  */
	public int getHR_Element_Base_Paie_ID();

	public I_HR_Element_Base_Paie getHR_Element_Base_Paie() throws RuntimeException;

    /** Column name HR_Historique_Paie_ID */
    public static final String COLUMNNAME_HR_Historique_Paie_ID = "HR_Historique_Paie_ID";

	/** Set Historique Paie	  */
	public void setHR_Historique_Paie_ID (int HR_Historique_Paie_ID);

	/** Get Historique Paie	  */
	public int getHR_Historique_Paie_ID();

    /** Column name HR_Historique_Paie_UU */
    public static final String COLUMNNAME_HR_Historique_Paie_UU = "HR_Historique_Paie_UU";

	/** Set HR_Historique_Paie_UU	  */
	public void setHR_Historique_Paie_UU (String HR_Historique_Paie_UU);

	/** Get HR_Historique_Paie_UU	  */
	public String getHR_Historique_Paie_UU();

    /** Column name HR_Periode_Salariale_ID */
    public static final String COLUMNNAME_HR_Periode_Salariale_ID = "HR_Periode_Salariale_ID";

	/** Set Période Salariale	  */
	public void setHR_Periode_Salariale_ID (int HR_Periode_Salariale_ID);

	/** Get Période Salariale	  */
	public int getHR_Periode_Salariale_ID();

	public I_HR_Periode_Salariale getHR_Periode_Salariale() throws RuntimeException;

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

    /** Column name Montant */
    public static final String COLUMNNAME_Montant = "Montant";

	/** Set Montant.
	  * Montant
	  */
	public void setMontant (BigDecimal Montant);

	/** Get Montant.
	  * Montant
	  */
	public BigDecimal getMontant();

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
