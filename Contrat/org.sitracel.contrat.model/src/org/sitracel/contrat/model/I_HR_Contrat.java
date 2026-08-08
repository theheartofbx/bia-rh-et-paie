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
package org.sitracel.contrat.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;
import org.sitracel.recrutement.model.I_HR_Candidature;

/** Generated Interface for HR_Contrat
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Contrat 
{

    /** TableName=HR_Contrat */
    public static final String Table_Name = "HR_Contrat";

    /** AD_Table_ID=1014747 */
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

    /** Column name Date_Fin_Prevue */
    public static final String COLUMNNAME_Date_Fin_Prevue = "Date_Fin_Prevue";

	/** Set Date de Fin Prévue	  */
	public void setDate_Fin_Prevue (Timestamp Date_Fin_Prevue);

	/** Get Date de Fin Prévue	  */
	public Timestamp getDate_Fin_Prevue();

    /** Column name Date_Rupture */
    public static final String COLUMNNAME_Date_Rupture = "Date_Rupture";

	/** Set Date de Rupture	  */
	public void setDate_Rupture (Timestamp Date_Rupture);

	/** Get Date de Rupture	  */
	public Timestamp getDate_Rupture();

    /** Column name HR_Candidature_ID */
    public static final String COLUMNNAME_HR_Candidature_ID = "HR_Candidature_ID";

	/** Set Candidature	  */
	public void setHR_Candidature_ID (int HR_Candidature_ID);

	/** Get Candidature	  */
	public int getHR_Candidature_ID();

	public I_HR_Candidature getHR_Candidature() throws RuntimeException;

    /** Column name HR_ContratStatut_ID */
    public static final String COLUMNNAME_HR_ContratStatut_ID = "HR_ContratStatut_ID";

	/** Set Statuts Contrat de travail	  */
	public void setHR_ContratStatut_ID (int HR_ContratStatut_ID);

	/** Get Statuts Contrat de travail	  */
	public int getHR_ContratStatut_ID();

	public I_HR_ContratStatut getHR_ContratStatut() throws RuntimeException;

    /** Column name HR_ContratType_ID */
    public static final String COLUMNNAME_HR_ContratType_ID = "HR_ContratType_ID";

	/** Set Type de Contrat	  */
	public void setHR_ContratType_ID (int HR_ContratType_ID);

	/** Get Type de Contrat	  */
	public int getHR_ContratType_ID();

	public I_HR_ContratType getHR_ContratType() throws RuntimeException;

    /** Column name HR_Contrat_ID */
    public static final String COLUMNNAME_HR_Contrat_ID = "HR_Contrat_ID";

	/** Set Contrat	  */
	public void setHR_Contrat_ID (int HR_Contrat_ID);

	/** Get Contrat	  */
	public int getHR_Contrat_ID();

    /** Column name HR_Contrat_UU */
    public static final String COLUMNNAME_HR_Contrat_UU = "HR_Contrat_UU";

	/** Set HR_Contrat_UU	  */
	public void setHR_Contrat_UU (String HR_Contrat_UU);

	/** Get HR_Contrat_UU	  */
	public String getHR_Contrat_UU();

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

    /** Column name Motif_Rupture */
    public static final String COLUMNNAME_Motif_Rupture = "Motif_Rupture";

	/** Set Motif de Rupture	  */
	public void setMotif_Rupture (String Motif_Rupture);

	/** Get Motif de Rupture	  */
	public String getMotif_Rupture();

    /** Column name Reference_Document */
    public static final String COLUMNNAME_Reference_Document = "Reference_Document";

	/** Set Référence document	  */
	public void setReference_Document (String Reference_Document);

	/** Get Référence document	  */
	public String getReference_Document();

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
