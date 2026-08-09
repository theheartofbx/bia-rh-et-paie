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

/** Generated Interface for HR_FormationPlanningLigne
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_FormationPlanningLigne 
{

    /** TableName=HR_FormationPlanningLigne */
    public static final String Table_Name = "HR_FormationPlanningLigne";

    /** AD_Table_ID=1016161 */
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

    /** Column name Date_Planning */
    public static final String COLUMNNAME_Date_Planning = "Date_Planning";

	/** Set Date.
	  * Date
	  */
	public void setDate_Planning (Timestamp Date_Planning);

	/** Get Date.
	  * Date
	  */
	public Timestamp getDate_Planning();

    /** Column name Encadrant_ID */
    public static final String COLUMNNAME_Encadrant_ID = "Encadrant_ID";

	/** Set Encadrant.
	  * Encadrant
	  */
	public void setEncadrant_ID (int Encadrant_ID);

	/** Get Encadrant.
	  * Encadrant
	  */
	public int getEncadrant_ID();

	public org.compiere.model.I_C_BPartner getEncadrant() throws RuntimeException;

    /** Column name HR_FormationModule_ID */
    public static final String COLUMNNAME_HR_FormationModule_ID = "HR_FormationModule_ID";

	/** Set Modules de Formation	  */
	public void setHR_FormationModule_ID (int HR_FormationModule_ID);

	/** Get Modules de Formation	  */
	public int getHR_FormationModule_ID();

	public I_HR_FormationModule getHR_FormationModule() throws RuntimeException;

    /** Column name HR_FormationPlanningLigne_ID */
    public static final String COLUMNNAME_HR_FormationPlanningLigne_ID = "HR_FormationPlanningLigne_ID";

	/** Set Lignes Planning de Formation	  */
	public void setHR_FormationPlanningLigne_ID (int HR_FormationPlanningLigne_ID);

	/** Get Lignes Planning de Formation	  */
	public int getHR_FormationPlanningLigne_ID();

    /** Column name HR_FormationPlanningLigne_UU */
    public static final String COLUMNNAME_HR_FormationPlanningLigne_UU = "HR_FormationPlanningLigne_UU";

	/** Set HR_FormationPlanningLigne_UU	  */
	public void setHR_FormationPlanningLigne_UU (String HR_FormationPlanningLigne_UU);

	/** Get HR_FormationPlanningLigne_UU	  */
	public String getHR_FormationPlanningLigne_UU();

    /** Column name HR_FormationPlanning_ID */
    public static final String COLUMNNAME_HR_FormationPlanning_ID = "HR_FormationPlanning_ID";

	/** Set Planning de Formation	  */
	public void setHR_FormationPlanning_ID (int HR_FormationPlanning_ID);

	/** Get Planning de Formation	  */
	public int getHR_FormationPlanning_ID();

	public I_HR_FormationPlanning getHR_FormationPlanning() throws RuntimeException;

    /** Column name HR_FormationProgramme_ID */
    public static final String COLUMNNAME_HR_FormationProgramme_ID = "HR_FormationProgramme_ID";

	/** Set Programme de Formation	  */
	public void setHR_FormationProgramme_ID (int HR_FormationProgramme_ID);

	/** Get Programme de Formation	  */
	public int getHR_FormationProgramme_ID();

	public I_HR_FormationProgramme getHR_FormationProgramme() throws RuntimeException;

    /** Column name Heure_Debut */
    public static final String COLUMNNAME_Heure_Debut = "Heure_Debut";

	/** Set Heure de Début.
	  * Heure de Début
	  */
	public void setHeure_Debut (Timestamp Heure_Debut);

	/** Get Heure de Début.
	  * Heure de Début
	  */
	public Timestamp getHeure_Debut();

    /** Column name Heure_Fin */
    public static final String COLUMNNAME_Heure_Fin = "Heure_Fin";

	/** Set Heure de Fin.
	  * Heure de Fin
	  */
	public void setHeure_Fin (Timestamp Heure_Fin);

	/** Get Heure de Fin.
	  * Heure de Fin
	  */
	public Timestamp getHeure_Fin();

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

    /** Column name IsOk */
    public static final String COLUMNNAME_IsOk = "IsOk";

	/** Set Ok	  */
	public void setIsOk (boolean IsOk);

	/** Get Ok	  */
	public boolean isOk();

    /** Column name Lieu */
    public static final String COLUMNNAME_Lieu = "Lieu";

	/** Set Lieu	  */
	public void setLieu (String Lieu);

	/** Get Lieu	  */
	public String getLieu();

    /** Column name Numero_Partie */
    public static final String COLUMNNAME_Numero_Partie = "Numero_Partie";

	/** Set Partie N°.
	  * Partie N°
	  */
	public void setNumero_Partie (int Numero_Partie);

	/** Get Partie N°.
	  * Partie N°
	  */
	public int getNumero_Partie();

    /** Column name Observation */
    public static final String COLUMNNAME_Observation = "Observation";

	/** Set Observation	  */
	public void setObservation (String Observation);

	/** Get Observation	  */
	public String getObservation();

    /** Column name Theme */
    public static final String COLUMNNAME_Theme = "Theme";

	/** Set Theme.
	  * Theme name
	  */
	public void setTheme (String Theme);

	/** Get Theme.
	  * Theme name
	  */
	public String getTheme();

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
