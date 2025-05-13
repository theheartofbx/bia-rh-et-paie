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

/** Generated Interface for HR_Bareme
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Bareme 
{

    /** TableName=HR_Bareme */
    public static final String Table_Name = "HR_Bareme";

    /** AD_Table_ID=1008990 */
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

    /** Column name Formule */
    public static final String COLUMNNAME_Formule = "Formule";

	/** Set Formule	  */
	public void setFormule (String Formule);

	/** Get Formule	  */
	public String getFormule();

    /** Column name HR_Bareme_ID */
    public static final String COLUMNNAME_HR_Bareme_ID = "HR_Bareme_ID";

	/** Set Barême	  */
	public void setHR_Bareme_ID (int HR_Bareme_ID);

	/** Get Barême	  */
	public int getHR_Bareme_ID();

    /** Column name HR_Bareme_UU */
    public static final String COLUMNNAME_HR_Bareme_UU = "HR_Bareme_UU";

	/** Set HR_Bareme_UU	  */
	public void setHR_Bareme_UU (String HR_Bareme_UU);

	/** Get HR_Bareme_UU	  */
	public String getHR_Bareme_UU();

    /** Column name HR_Element_Base_Paie_ID */
    public static final String COLUMNNAME_HR_Element_Base_Paie_ID = "HR_Element_Base_Paie_ID";

	/** Set Élément de Paie	  */
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID);

	/** Get Élément de Paie	  */
	public int getHR_Element_Base_Paie_ID();

	public I_HR_Element_Base_Paie getHR_Element_Base_Paie() throws RuntimeException;

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

    /** Column name Montant_Debut */
    public static final String COLUMNNAME_Montant_Debut = "Montant_Debut";

	/** Set Montant à Partir de .
	  * Montant à Partir de 
	  */
	public void setMontant_Debut (BigDecimal Montant_Debut);

	/** Get Montant à Partir de .
	  * Montant à Partir de 
	  */
	public BigDecimal getMontant_Debut();

    /** Column name Montant_Fin */
    public static final String COLUMNNAME_Montant_Fin = "Montant_Fin";

	/** Set Montant Jusqu&#039;
à.
	  * Montant Jusqu&#039;
à
	  */
	public void setMontant_Fin (BigDecimal Montant_Fin);

	/** Get Montant Jusqu&#039;
à.
	  * Montant Jusqu&#039;
à
	  */
	public BigDecimal getMontant_Fin();

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
