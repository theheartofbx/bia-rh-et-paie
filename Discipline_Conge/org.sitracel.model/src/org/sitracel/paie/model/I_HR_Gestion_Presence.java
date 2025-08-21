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

/** Generated Interface for HR_Gestion_Presence
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Gestion_Presence 
{

    /** TableName=HR_Gestion_Presence */
    public static final String Table_Name = "HR_Gestion_Presence";

    /** AD_Table_ID=1008586 */
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

    /** Column name HR_Gestion_Presence_ID */
    public static final String COLUMNNAME_HR_Gestion_Presence_ID = "HR_Gestion_Presence_ID";

	/** Set Gestion de la Présence	  */
	public void setHR_Gestion_Presence_ID (int HR_Gestion_Presence_ID);

	/** Get Gestion de la Présence	  */
	public int getHR_Gestion_Presence_ID();

    /** Column name HR_Gestion_Presence_UU */
    public static final String COLUMNNAME_HR_Gestion_Presence_UU = "HR_Gestion_Presence_UU";

	/** Set HR_Gestion_Presence_UU	  */
	public void setHR_Gestion_Presence_UU (String HR_Gestion_Presence_UU);

	/** Get HR_Gestion_Presence_UU	  */
	public String getHR_Gestion_Presence_UU();

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

    /** Column name Nombre_Heure_Travaille */
    public static final String COLUMNNAME_Nombre_Heure_Travaille = "Nombre_Heure_Travaille";

	/** Set Nombre d&#039;
Heures Travaillés	  */
	public void setNombre_Heure_Travaille (int Nombre_Heure_Travaille);

	/** Get Nombre d&#039;
Heures Travaillés	  */
	public int getNombre_Heure_Travaille();

    /** Column name Nombre_Heure_Travaille_Max */
    public static final String COLUMNNAME_Nombre_Heure_Travaille_Max = "Nombre_Heure_Travaille_Max";

	/** Set Nombre d&#039;
Heures Travaillés Max	  */
	public void setNombre_Heure_Travaille_Max (int Nombre_Heure_Travaille_Max);

	/** Get Nombre d&#039;
Heures Travaillés Max	  */
	public int getNombre_Heure_Travaille_Max();

    /** Column name Nombre_Jour_Avant_DebutContrat */
    public static final String COLUMNNAME_Nombre_Jour_Avant_DebutContrat = "Nombre_Jour_Avant_DebutContrat";

	/** Set Nombre de Jour Avant le Début du Contrat.
	  * Nombre de Jour Avant le Début du Contrat
	  */
	public void setNombre_Jour_Avant_DebutContrat (int Nombre_Jour_Avant_DebutContrat);

	/** Get Nombre de Jour Avant le Début du Contrat.
	  * Nombre de Jour Avant le Début du Contrat
	  */
	public int getNombre_Jour_Avant_DebutContrat();

    /** Column name Nombre_Jour_Conge_Annuel */
    public static final String COLUMNNAME_Nombre_Jour_Conge_Annuel = "Nombre_Jour_Conge_Annuel";

	/** Set Nombre de Jour de Congé Annuel.
	  * Nombre de Jour de Congé Annuel
	  */
	public void setNombre_Jour_Conge_Annuel (int Nombre_Jour_Conge_Annuel);

	/** Get Nombre de Jour de Congé Annuel.
	  * Nombre de Jour de Congé Annuel
	  */
	public int getNombre_Jour_Conge_Annuel();

    /** Column name Nombre_Jour_Conge_Maternite */
    public static final String COLUMNNAME_Nombre_Jour_Conge_Maternite = "Nombre_Jour_Conge_Maternite";

	/** Set Nombre de Jour de Congé Maternité.
	  * Nombre de Jour de Congé Maternité
	  */
	public void setNombre_Jour_Conge_Maternite (int Nombre_Jour_Conge_Maternite);

	/** Get Nombre de Jour de Congé Maternité.
	  * Nombre de Jour de Congé Maternité
	  */
	public int getNombre_Jour_Conge_Maternite();

    /** Column name Nombre_Jour_Conge_Paternite */
    public static final String COLUMNNAME_Nombre_Jour_Conge_Paternite = "Nombre_Jour_Conge_Paternite";

	/** Set Nombre de Jour de Congé Paternité.
	  * Nombre de Jour de Congé Paternité
	  */
	public void setNombre_Jour_Conge_Paternite (int Nombre_Jour_Conge_Paternite);

	/** Get Nombre de Jour de Congé Paternité.
	  * Nombre de Jour de Congé Paternité
	  */
	public int getNombre_Jour_Conge_Paternite();

    /** Column name Nombre_Jour_Effectif */
    public static final String COLUMNNAME_Nombre_Jour_Effectif = "Nombre_Jour_Effectif";

	/** Set Nombre de Jour Effectif.
	  * Nombre de Jour Effectif
	  */
	public void setNombre_Jour_Effectif (int Nombre_Jour_Effectif);

	/** Get Nombre de Jour Effectif.
	  * Nombre de Jour Effectif
	  */
	public int getNombre_Jour_Effectif();

    /** Column name Nombre_Jour_Max */
    public static final String COLUMNNAME_Nombre_Jour_Max = "Nombre_Jour_Max";

	/** Set Nombre de Jour Max.
	  * Nombre de Jour Max
	  */
	public void setNombre_Jour_Max (int Nombre_Jour_Max);

	/** Get Nombre de Jour Max.
	  * Nombre de Jour Max
	  */
	public int getNombre_Jour_Max();

    /** Column name Nombre_Jour_Suspension */
    public static final String COLUMNNAME_Nombre_Jour_Suspension = "Nombre_Jour_Suspension";

	/** Set Nombre de Jour de Suspension.
	  * Nombre de Jour de Suspension
	  */
	public void setNombre_Jour_Suspension (int Nombre_Jour_Suspension);

	/** Get Nombre de Jour de Suspension.
	  * Nombre de Jour de Suspension
	  */
	public int getNombre_Jour_Suspension();

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
