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

/** Generated Interface for HR_GestionPaieEmploye
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_GestionPaieEmploye 
{

    /** TableName=HR_GestionPaieEmploye */
    public static final String Table_Name = "HR_GestionPaieEmploye";

    /** AD_Table_ID=1009899 */
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

    /** Column name HR_GestionPaieEmploye_ID */
    public static final String COLUMNNAME_HR_GestionPaieEmploye_ID = "HR_GestionPaieEmploye_ID";

	/** Set Gestion des Éléments de Base de la Paie Employé	  */
	public void setHR_GestionPaieEmploye_ID (int HR_GestionPaieEmploye_ID);

	/** Get Gestion des Éléments de Base de la Paie Employé	  */
	public int getHR_GestionPaieEmploye_ID();

    /** Column name HR_GestionPaieEmploye_UU */
    public static final String COLUMNNAME_HR_GestionPaieEmploye_UU = "HR_GestionPaieEmploye_UU";

	/** Set HR_GestionPaieEmploye_UU	  */
	public void setHR_GestionPaieEmploye_UU (String HR_GestionPaieEmploye_UU);

	/** Get HR_GestionPaieEmploye_UU	  */
	public String getHR_GestionPaieEmploye_UU();

    /** Column name HR_Rang_Calcul_ID */
    public static final String COLUMNNAME_HR_Rang_Calcul_ID = "HR_Rang_Calcul_ID";

	/** Set Rang de Calcul	  */
	public void setHR_Rang_Calcul_ID (int HR_Rang_Calcul_ID);

	/** Get Rang de Calcul	  */
	public int getHR_Rang_Calcul_ID();

	public I_HR_Rang_Calcul getHR_Rang_Calcul() throws RuntimeException;

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

    /** Column name IsCotisable */
    public static final String COLUMNNAME_IsCotisable = "IsCotisable";

	/** Set Est Cotisable.
	  * Est Cotisable
	  */
	public void setIsCotisable (boolean IsCotisable);

	/** Get Est Cotisable.
	  * Est Cotisable
	  */
	public boolean isCotisable();

    /** Column name IsImposable */
    public static final String COLUMNNAME_IsImposable = "IsImposable";

	/** Set Est Imposable.
	  * Est Imposable
	  */
	public void setIsImposable (boolean IsImposable);

	/** Get Est Imposable.
	  * Est Imposable
	  */
	public boolean isImposable();

    /** Column name IsIndemniteConge */
    public static final String COLUMNNAME_IsIndemniteConge = "IsIndemniteConge";

	/** Set Participe au Calcul des Indemnités de Congé	  */
	public void setIsIndemniteConge (boolean IsIndemniteConge);

	/** Get Participe au Calcul des Indemnités de Congé	  */
	public boolean isIndemniteConge();

    /** Column name IsIrpp */
    public static final String COLUMNNAME_IsIrpp = "IsIrpp";

	/** Set Est Utilisé dans le calcul de l&#039;
IRPP.
	  * Est Utilisé dans le calcul de l&#039;
IRPP
	  */
	public void setIsIrpp (boolean IsIrpp);

	/** Get Est Utilisé dans le calcul de l&#039;
IRPP.
	  * Est Utilisé dans le calcul de l&#039;
IRPP
	  */
	public boolean isIrpp();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name IsProportionnelTravail */
    public static final String COLUMNNAME_IsProportionnelTravail = "IsProportionnelTravail";

	/** Set Est Proportionnel au Temps de Travail	  */
	public void setIsProportionnelTravail (boolean IsProportionnelTravail);

	/** Get Est Proportionnel au Temps de Travail	  */
	public boolean isProportionnelTravail();

    /** Column name Libelle */
    public static final String COLUMNNAME_Libelle = "Libelle";

	/** Set Libellé	  */
	public void setLibelle (String Libelle);

	/** Get Libellé	  */
	public String getLibelle();

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
