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

/** Generated Interface for HR_FormationParticipant
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_FormationParticipant 
{

    /** TableName=HR_FormationParticipant */
    public static final String Table_Name = "HR_FormationParticipant";

    /** AD_Table_ID=1016262 */
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

    /** Column name HR_FormatioStatutP_ID */
    public static final String COLUMNNAME_HR_FormatioStatutP_ID = "HR_FormatioStatutP_ID";

	/** Set Statut Participants Formation	  */
	public void setHR_FormatioStatutP_ID (int HR_FormatioStatutP_ID);

	/** Get Statut Participants Formation	  */
	public int getHR_FormatioStatutP_ID();

	public I_HR_FormatioStatutP getHR_FormatioStatutP() throws RuntimeException;

    /** Column name HR_FormationDemande_ID */
    public static final String COLUMNNAME_HR_FormationDemande_ID = "HR_FormationDemande_ID";

	/** Set Demande de Participation Formation	  */
	public void setHR_FormationDemande_ID (int HR_FormationDemande_ID);

	/** Get Demande de Participation Formation	  */
	public int getHR_FormationDemande_ID();

	public I_HR_FormationDemande getHR_FormationDemande() throws RuntimeException;

    /** Column name HR_FormationParticipant_ID */
    public static final String COLUMNNAME_HR_FormationParticipant_ID = "HR_FormationParticipant_ID";

	/** Set Participants Formation	  */
	public void setHR_FormationParticipant_ID (int HR_FormationParticipant_ID);

	/** Get Participants Formation	  */
	public int getHR_FormationParticipant_ID();

    /** Column name HR_FormationParticipant_UU */
    public static final String COLUMNNAME_HR_FormationParticipant_UU = "HR_FormationParticipant_UU";

	/** Set HR_FormationParticipant_UU	  */
	public void setHR_FormationParticipant_UU (String HR_FormationParticipant_UU);

	/** Get HR_FormationParticipant_UU	  */
	public String getHR_FormationParticipant_UU();

    /** Column name HR_FormationPlanning_ID */
    public static final String COLUMNNAME_HR_FormationPlanning_ID = "HR_FormationPlanning_ID";

	/** Set Planning de Formation	  */
	public void setHR_FormationPlanning_ID (int HR_FormationPlanning_ID);

	/** Get Planning de Formation	  */
	public int getHR_FormationPlanning_ID();

	public I_HR_FormationPlanning getHR_FormationPlanning() throws RuntimeException;

    /** Column name HR_FormationSession_ID */
    public static final String COLUMNNAME_HR_FormationSession_ID = "HR_FormationSession_ID";

	/** Set Session de Formation	  */
	public void setHR_FormationSession_ID (int HR_FormationSession_ID);

	/** Get Session de Formation	  */
	public int getHR_FormationSession_ID();

	public I_HR_FormationSession getHR_FormationSession() throws RuntimeException;

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
