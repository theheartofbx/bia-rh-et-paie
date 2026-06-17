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

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;
import org.sitracel.discipline.model.I_HR_TypeSanction;

/** Generated Interface for HR_Holiday_Department
 *  @author iDempiere (generated)
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Holiday_Department
{

    /** TableName=HR_Holiday_Department */
    public static final String Table_Name = "HR_Holiday_Department";

    /** AD_Table_ID=1011212 */
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

    /** Column name Collegue_ID */
    public static final String COLUMNNAME_Collegue_ID = "Collegue_ID";

	/** Set Collègue	  */
	public void setCollegue_ID (int Collegue_ID);

	/** Get Collègue	  */
	public int getCollegue_ID();

	public org.compiere.model.I_C_BPartner getCollegue() throws RuntimeException;

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

    /** Column name Date_Fin */
    public static final String COLUMNNAME_Date_Fin = "Date_Fin";

	/** Set Jusqu&#039;
au :.
	  * Jusqu&#039;
au :
	  */
	public void setDate_Fin (Timestamp Date_Fin);

	/** Get Jusqu&#039;
au :.
	  * Jusqu&#039;
au :
	  */
	public Timestamp getDate_Fin();

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Payroll Department	  */
	public int getHR_Department_ID();

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

    /** Column name HR_Holiday_Department_ID */
    public static final String COLUMNNAME_HR_Holiday_Department_ID = "HR_Holiday_Department_ID";

	/** Set Congé du Département	  */
	public void setHR_Holiday_Department_ID (int HR_Holiday_Department_ID);

	/** Get Congé du Département	  */
	public int getHR_Holiday_Department_ID();

    /** Column name HR_Holiday_Department_UU */
    public static final String COLUMNNAME_HR_Holiday_Department_UU = "HR_Holiday_Department_UU";

	/** Set HR_Holiday_Department_UU	  */
	public void setHR_Holiday_Department_UU (String HR_Holiday_Department_UU);

	/** Get HR_Holiday_Department_UU	  */
	public String getHR_Holiday_Department_UU();

    /** Column name HR_Holiday_ID */
    public static final String COLUMNNAME_HR_Holiday_ID = "HR_Holiday_ID";

	/** Set Congé	  */
	public void setHR_Holiday_ID (int HR_Holiday_ID);

	/** Get Congé	  */
	public int getHR_Holiday_ID();

	public I_HR_Holiday getHR_Holiday() throws RuntimeException;

    /** Column name HR_TypeSanction_ID */
    public static final String COLUMNNAME_HR_TypeSanction_ID = "HR_TypeSanction_ID";

	/** Set Punishment Type	  */
	public void setHR_TypeSanction_ID (int HR_TypeSanction_ID);

	/** Get Punishment Type	  */
	public int getHR_TypeSanction_ID();

	public I_HR_TypeSanction getHR_TypeSanction() throws RuntimeException;

    /** Column name HR_Type_Conge_ID */
    public static final String COLUMNNAME_HR_Type_Conge_ID = "HR_Type_Conge_ID";

	/** Set Type de Congé	  */
	public void setHR_Type_Conge_ID (int HR_Type_Conge_ID);

	/** Get Type de Congé	  */
	public int getHR_Type_Conge_ID();

	public I_HR_Type_Conge getHR_Type_Conge() throws RuntimeException;

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

    /** Column name Nombre_Employe_Departement_Hol */
    public static final String COLUMNNAME_Nombre_Employe_Departement_Hol = "Nombre_Employe_Departement_Hol";

	/** Set Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour.
	  * Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour
	  */
	public void setNombre_Employe_Departement_Hol (int Nombre_Employe_Departement_Hol);

	/** Get Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour.
	  * Nombre Max d&#039;
Emplyé(e)s du Département en Congé/Jour
	  */
	public int getNombre_Employe_Departement_Hol();

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
