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
package org.sitracel.stage.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_StageProgrammeLigne
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_StageProgrammeLigne 
{

    /** TableName=HR_StageProgrammeLigne */
    public static final String Table_Name = "HR_StageProgrammeLigne";

    /** AD_Table_ID=1017070 */
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

    /** Column name HR_StageObjectif_ID */
    public static final String COLUMNNAME_HR_StageObjectif_ID = "HR_StageObjectif_ID";

	/** Set Objectif Stage	  */
	public void setHR_StageObjectif_ID (int HR_StageObjectif_ID);

	/** Get Objectif Stage	  */
	public int getHR_StageObjectif_ID();

	public I_HR_StageObjectif getHR_StageObjectif() throws RuntimeException;

    /** Column name HR_StageProgrammeLigne_ID */
    public static final String COLUMNNAME_HR_StageProgrammeLigne_ID = "HR_StageProgrammeLigne_ID";

	/** Set Détail Programme Stage	  */
	public void setHR_StageProgrammeLigne_ID (int HR_StageProgrammeLigne_ID);

	/** Get Détail Programme Stage	  */
	public int getHR_StageProgrammeLigne_ID();

    /** Column name HR_StageProgrammeLigne_UU */
    public static final String COLUMNNAME_HR_StageProgrammeLigne_UU = "HR_StageProgrammeLigne_UU";

	/** Set HR_StageProgrammeLigne_UU	  */
	public void setHR_StageProgrammeLigne_UU (String HR_StageProgrammeLigne_UU);

	/** Get HR_StageProgrammeLigne_UU	  */
	public String getHR_StageProgrammeLigne_UU();

    /** Column name HR_StageProgramme_ID */
    public static final String COLUMNNAME_HR_StageProgramme_ID = "HR_StageProgramme_ID";

	/** Set Programme Stage	  */
	public void setHR_StageProgramme_ID (int HR_StageProgramme_ID);

	/** Get Programme Stage	  */
	public int getHR_StageProgramme_ID();

	public I_HR_StageProgramme getHR_StageProgramme() throws RuntimeException;

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

    /** Column name Ponderation_Defaut */
    public static final String COLUMNNAME_Ponderation_Defaut = "Ponderation_Defaut";

	/** Set Ponderation par Défaut	  */
	public void setPonderation_Defaut (int Ponderation_Defaut);

	/** Get Ponderation par Défaut	  */
	public int getPonderation_Defaut();

    /** Column name ScoreMax_Defaut */
    public static final String COLUMNNAME_ScoreMax_Defaut = "ScoreMax_Defaut";

	/** Set Score Max par Défaut	  */
	public void setScoreMax_Defaut (BigDecimal ScoreMax_Defaut);

	/** Get Score Max par Défaut	  */
	public BigDecimal getScoreMax_Defaut();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
