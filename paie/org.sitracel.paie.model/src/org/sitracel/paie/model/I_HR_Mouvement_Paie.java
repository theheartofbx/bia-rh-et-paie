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

/** Generated Interface for HR_Mouvement_Paie
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_Mouvement_Paie 
{

    /** TableName=HR_Mouvement_Paie */
    public static final String Table_Name = "HR_Mouvement_Paie";

    /** AD_Table_ID=1010101 */
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

    /** Column name Acompte */
    public static final String COLUMNNAME_Acompte = "Acompte";

	/** Set Acompte déjà Versé.
	  * Acompte déjà Versé
	  */
	public void setAcompte (BigDecimal Acompte);

	/** Get Acompte déjà Versé.
	  * Acompte déjà Versé
	  */
	public BigDecimal getAcompte();

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

    /** Column name Debut_Prelevement_ID */
    public static final String COLUMNNAME_Debut_Prelevement_ID = "Debut_Prelevement_ID";

	/** Set Prélèvement à Partir de .
	  * Prélèvement à Partir de 
	  */
	public void setDebut_Prelevement_ID (int Debut_Prelevement_ID);

	/** Get Prélèvement à Partir de .
	  * Prélèvement à Partir de 
	  */
	public int getDebut_Prelevement_ID();

	public I_HR_Periode_Salariale getDebut_Prelevement() throws RuntimeException;

    /** Column name Fin_Prelevement_ID */
    public static final String COLUMNNAME_Fin_Prelevement_ID = "Fin_Prelevement_ID";

	/** Set Prélèvement Jusqu&#039;
à  .
	  * Prélèvement Jusqu&#039;
à  
	  */
	public void setFin_Prelevement_ID (int Fin_Prelevement_ID);

	/** Get Prélèvement Jusqu&#039;
à  .
	  * Prélèvement Jusqu&#039;
à  
	  */
	public int getFin_Prelevement_ID();

	public I_HR_Periode_Salariale getFin_Prelevement() throws RuntimeException;

    /** Column name HR_Annee_ID */
    public static final String COLUMNNAME_HR_Annee_ID = "HR_Annee_ID";

	/** Set Année	  */
	public void setHR_Annee_ID (int HR_Annee_ID);

	/** Get Année	  */
	public int getHR_Annee_ID();

	public I_HR_Annee getHR_Annee() throws RuntimeException;

    /** Column name HR_Element_Base_Paie_ID */
    public static final String COLUMNNAME_HR_Element_Base_Paie_ID = "HR_Element_Base_Paie_ID";

	/** Set Élément de Paie	  */
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID);

	/** Get Élément de Paie	  */
	public int getHR_Element_Base_Paie_ID();

	public I_HR_Element_Base_Paie getHR_Element_Base_Paie() throws RuntimeException;

    /** Column name HR_Mois_ID */
    public static final String COLUMNNAME_HR_Mois_ID = "HR_Mois_ID";

	/** Set Mois	  */
	public void setHR_Mois_ID (int HR_Mois_ID);

	/** Get Mois	  */
	public int getHR_Mois_ID();

	public I_HR_Mois getHR_Mois() throws RuntimeException;

    /** Column name HR_MouvementPaieType_ID */
    public static final String COLUMNNAME_HR_MouvementPaieType_ID = "HR_MouvementPaieType_ID";

	/** Set Type de Mouvement Paie	  */
	public void setHR_MouvementPaieType_ID (int HR_MouvementPaieType_ID);

	/** Get Type de Mouvement Paie	  */
	public int getHR_MouvementPaieType_ID();

	public I_HR_MouvementPaieType getHR_MouvementPaieType() throws RuntimeException;

    /** Column name HR_Mouvement_Paie_ID */
    public static final String COLUMNNAME_HR_Mouvement_Paie_ID = "HR_Mouvement_Paie_ID";

	/** Set Mouvement de Paie	  */
	public void setHR_Mouvement_Paie_ID (int HR_Mouvement_Paie_ID);

	/** Get Mouvement de Paie	  */
	public int getHR_Mouvement_Paie_ID();

    /** Column name HR_Mouvement_Paie_UU */
    public static final String COLUMNNAME_HR_Mouvement_Paie_UU = "HR_Mouvement_Paie_UU";

	/** Set HR_Retenue_Salariale_UU	  */
	public void setHR_Mouvement_Paie_UU (String HR_Mouvement_Paie_UU);

	/** Get HR_Retenue_Salariale_UU	  */
	public String getHR_Mouvement_Paie_UU();

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

    /** Column name IsIndemnite */
    public static final String COLUMNNAME_IsIndemnite = "IsIndemnite";

	/** Set Est une Indemnité	  */
	public void setIsIndemnite (boolean IsIndemnite);

	/** Get Est une Indemnité	  */
	public boolean isIndemnite();

    /** Column name IsIndemniteLicenciement */
    public static final String COLUMNNAME_IsIndemniteLicenciement = "IsIndemniteLicenciement";

	/** Set Participe au Calcul des Indemnités de Licenciement	  */
	public void setIsIndemniteLicenciement (boolean IsIndemniteLicenciement);

	/** Get Participe au Calcul des Indemnités de Licenciement	  */
	public boolean isIndemniteLicenciement();

    /** Column name IsIndemniteRetraite */
    public static final String COLUMNNAME_IsIndemniteRetraite = "IsIndemniteRetraite";

	/** Set Participe au Calcul des Indemnités de Départ à la Retraite	  */
	public void setIsIndemniteRetraite (boolean IsIndemniteRetraite);

	/** Get Participe au Calcul des Indemnités de Départ à la Retraite	  */
	public boolean isIndemniteRetraite();

    /** Column name IsRecurrent */
    public static final String COLUMNNAME_IsRecurrent = "IsRecurrent";

	/** Set Est Récurrent	  */
	public void setIsRecurrent (boolean IsRecurrent);

	/** Get Est Récurrent	  */
	public boolean isRecurrent();

    /** Column name Montant_Derniere_Mensualite */
    public static final String COLUMNNAME_Montant_Derniere_Mensualite = "Montant_Derniere_Mensualite";

	/** Set Montant de la Dernière Mensualité.
	  * Montant de la Dernière Mensualité
	  */
	public void setMontant_Derniere_Mensualite (BigDecimal Montant_Derniere_Mensualite);

	/** Get Montant de la Dernière Mensualité.
	  * Montant de la Dernière Mensualité
	  */
	public BigDecimal getMontant_Derniere_Mensualite();

    /** Column name Montant_Mensualite */
    public static final String COLUMNNAME_Montant_Mensualite = "Montant_Mensualite";

	/** Set Montant des Mensualités.
	  * Montant des Mensualités
	  */
	public void setMontant_Mensualite (BigDecimal Montant_Mensualite);

	/** Get Montant des Mensualités.
	  * Montant des Mensualités
	  */
	public BigDecimal getMontant_Mensualite();

    /** Column name Montant_Total */
    public static final String COLUMNNAME_Montant_Total = "Montant_Total";

	/** Set Montant Total.
	  * Montant Total de la Retenue
	  */
	public void setMontant_Total (BigDecimal Montant_Total);

	/** Get Montant Total.
	  * Montant Total de la Retenue
	  */
	public BigDecimal getMontant_Total();

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

    /** Column name Nombre_Mensualite */
    public static final String COLUMNNAME_Nombre_Mensualite = "Nombre_Mensualite";

	/** Set Nombre de Mensualité.
	  * Nombre de Mensualité
	  */
	public void setNombre_Mensualite (int Nombre_Mensualite);

	/** Get Nombre de Mensualité.
	  * Nombre de Mensualité
	  */
	public int getNombre_Mensualite();

    /** Column name Solde */
    public static final String COLUMNNAME_Solde = "Solde";

	/** Set Solde.
	  * Montant Restant
	  */
	public void setSolde (BigDecimal Solde);

	/** Get Solde.
	  * Montant Restant
	  */
	public BigDecimal getSolde();

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
