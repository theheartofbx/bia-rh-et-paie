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

/** Generated Interface for HR_FormationDemande
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_FormationDemande 
{

    /** TableName=HR_FormationDemande */
    public static final String Table_Name = "HR_FormationDemande";

    /** AD_Table_ID=1016363 */
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

    /** Column name Date_Decision */
    public static final String COLUMNNAME_Date_Decision = "Date_Decision";

	/** Set Date Décision	  */
	public void setDate_Decision (Timestamp Date_Decision);

	/** Get Date Décision	  */
	public Timestamp getDate_Decision();

    /** Column name Date_Demande */
    public static final String COLUMNNAME_Date_Demande = "Date_Demande";

	/** Set Date de Demande	  */
	public void setDate_Demande (Timestamp Date_Demande);

	/** Get Date de Demande	  */
	public Timestamp getDate_Demande();

    /** Column name HR_FormationDemande_ID */
    public static final String COLUMNNAME_HR_FormationDemande_ID = "HR_FormationDemande_ID";

	/** Set Demande de Participation Formation	  */
	public void setHR_FormationDemande_ID (int HR_FormationDemande_ID);

	/** Get Demande de Participation Formation	  */
	public int getHR_FormationDemande_ID();

    /** Column name HR_FormationDemande_UU */
    public static final String COLUMNNAME_HR_FormationDemande_UU = "HR_FormationDemande_UU";

	/** Set HR_FormationDemande_UU	  */
	public void setHR_FormationDemande_UU (String HR_FormationDemande_UU);

	/** Get HR_FormationDemande_UU	  */
	public String getHR_FormationDemande_UU();

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

    /** Column name IsRejetee */
    public static final String COLUMNNAME_IsRejetee = "IsRejetee";

	/** Set Rejeté(e).
	  * Rejeté(e)
	  */
	public void setIsRejetee (boolean IsRejetee);

	/** Get Rejeté(e).
	  * Rejeté(e)
	  */
	public boolean isRejetee();

    /** Column name IsValidee */
    public static final String COLUMNNAME_IsValidee = "IsValidee";

	/** Set Validé(e).
	  * Validé(e)
	  */
	public void setIsValidee (boolean IsValidee);

	/** Get Validé(e).
	  * Validé(e)
	  */
	public boolean isValidee();

    /** Column name Motif */
    public static final String COLUMNNAME_Motif = "Motif";

	/** Set Motif .
	  * Motif
	  */
	public void setMotif (String Motif);

	/** Get Motif .
	  * Motif
	  */
	public String getMotif();

    /** Column name Motif_Rejet */
    public static final String COLUMNNAME_Motif_Rejet = "Motif_Rejet";

	/** Set Motif Rejet	  */
	public void setMotif_Rejet (String Motif_Rejet);

	/** Get Motif Rejet	  */
	public String getMotif_Rejet();

    /** Column name PeutValider */
    public static final String COLUMNNAME_PeutValider = "PeutValider";

	/** Set Peut Valider.
	  * Peut Valider
	  */
	public void setPeutValider (boolean PeutValider);

	/** Get Peut Valider.
	  * Peut Valider
	  */
	public boolean isPeutValider();

    /** Column name Rejeter */
    public static final String COLUMNNAME_Rejeter = "Rejeter";

	/** Set Rejeter.
	  * Rejeter
	  */
	public void setRejeter (String Rejeter);

	/** Get Rejeter.
	  * Rejeter
	  */
	public String getRejeter();

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

    /** Column name Valide_Rejete_Par_Nom_ID */
    public static final String COLUMNNAME_Valide_Rejete_Par_Nom_ID = "Valide_Rejete_Par_Nom_ID";

	/** Set Validé/rejeté par (Nom) :.
	  * Validé/rejeté par (Nom) :
	  */
	public void setValide_Rejete_Par_Nom_ID (int Valide_Rejete_Par_Nom_ID);

	/** Get Validé/rejeté par (Nom) :.
	  * Validé/rejeté par (Nom) :
	  */
	public int getValide_Rejete_Par_Nom_ID();

	public org.compiere.model.I_C_BPartner getValide_Rejete_Par_Nom() throws RuntimeException;

    /** Column name Valider */
    public static final String COLUMNNAME_Valider = "Valider";

	/** Set Valider .
	  * Valider 
	  */
	public void setValider (String Valider);

	/** Get Valider .
	  * Valider 
	  */
	public String getValider();
}
