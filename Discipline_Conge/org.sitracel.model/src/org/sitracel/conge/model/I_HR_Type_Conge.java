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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Type_Conge
 *  @author iDempiere (generated) 
 *  @version Release 11
 */
@SuppressWarnings("all")
public interface I_HR_Type_Conge 
{

    /** TableName=HR_Type_Conge */
    public static final String Table_Name = "HR_Type_Conge";

    /** AD_Table_ID=1003839 */
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

    /** Column name Genre_Employe */
    public static final String COLUMNNAME_Genre_Employe = "Genre_Employe";

	/** Set Genre de l&#039;
Employé(e) Concerné(e).
	  * Genre de l&#039;
Employé(e) Concerné(e)
	  */
	public void setGenre_Employe (String Genre_Employe);

	/** Get Genre de l&#039;
Employé(e) Concerné(e).
	  * Genre de l&#039;
Employé(e) Concerné(e)
	  */
	public String getGenre_Employe();

    /** Column name HR_Type_Conge_ID */
    public static final String COLUMNNAME_HR_Type_Conge_ID = "HR_Type_Conge_ID";

	/** Set Type de Congé	  */
	public void setHR_Type_Conge_ID (int HR_Type_Conge_ID);

	/** Get Type de Congé	  */
	public int getHR_Type_Conge_ID();

    /** Column name HR_Type_Conge_UU */
    public static final String COLUMNNAME_HR_Type_Conge_UU = "HR_Type_Conge_UU";

	/** Set HR_Type_Conge_UU	  */
	public void setHR_Type_Conge_UU (String HR_Type_Conge_UU);

	/** Get HR_Type_Conge_UU	  */
	public String getHR_Type_Conge_UU();

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

    /** Column name IsCongeAnnuel */
    public static final String COLUMNNAME_IsCongeAnnuel = "IsCongeAnnuel";

	/** Set Est déduit des jours de congé annuel.
	  * Est déduit des jours de congé annuel
	  */
	public void setIsCongeAnnuel (boolean IsCongeAnnuel);

	/** Get Est déduit des jours de congé annuel.
	  * Est déduit des jours de congé annuel
	  */
	public boolean isCongeAnnuel();

    /** Column name IsDefinir_Periode_Conge */
    public static final String COLUMNNAME_IsDefinir_Periode_Conge = "IsDefinir_Periode_Conge";

	/** Set Définir la période de Congé.
	  * Définir la période de Congé
	  */
	public void setIsDefinir_Periode_Conge (boolean IsDefinir_Periode_Conge);

	/** Get Définir la période de Congé.
	  * Définir la période de Congé
	  */
	public boolean isDefinir_Periode_Conge();

    /** Column name Nombre_Jour_Après_Echeance */
    public static final String COLUMNNAME_Nombre_Jour_Après_Echeance = "Nombre_Jour_Après_Echeance";

	/** Set Nombre de Jour(s) Après Echéance.
	  * Nombre de Jour(s) Après Echéance
	  */
	public void setNombre_Jour_Après_Echeance (int Nombre_Jour_Après_Echeance);

	/** Get Nombre de Jour(s) Après Echéance.
	  * Nombre de Jour(s) Après Echéance
	  */
	public int getNombre_Jour_Après_Echeance();

    /** Column name Nombre_Jour_Avant_Echeance */
    public static final String COLUMNNAME_Nombre_Jour_Avant_Echeance = "Nombre_Jour_Avant_Echeance";

	/** Set Nombre de Jour(s) Avant Echéance.
	  * Nombre de Jour(s) Avant Echéance
	  */
	public void setNombre_Jour_Avant_Echeance (int Nombre_Jour_Avant_Echeance);

	/** Get Nombre de Jour(s) Avant Echéance.
	  * Nombre de Jour(s) Avant Echéance
	  */
	public int getNombre_Jour_Avant_Echeance();

    /** Column name Nom_Conge */
    public static final String COLUMNNAME_Nom_Conge = "Nom_Conge";

	/** Set Nom du Congé.
	  * Nom du Congé
	  */
	public void setNom_Conge (String Nom_Conge);

	/** Get Nom du Congé.
	  * Nom du Congé
	  */
	public String getNom_Conge();

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
