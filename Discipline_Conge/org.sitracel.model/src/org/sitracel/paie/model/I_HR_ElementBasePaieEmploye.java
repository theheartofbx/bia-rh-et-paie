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

/** Generated Interface for HR_ElementBasePaieEmploye
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_HR_ElementBasePaieEmploye 
{

    /** TableName=HR_ElementBasePaieEmploye */
    public static final String Table_Name = "HR_ElementBasePaieEmploye";

    /** AD_Table_ID=1009394 */
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

    /** Column name Complement_Salaire */
    public static final String COLUMNNAME_Complement_Salaire = "Complement_Salaire";

	/** Set Complément Salaire.
	  * Complément Salaire
	  */
	public void setComplement_Salaire (BigDecimal Complement_Salaire);

	/** Get Complément Salaire.
	  * Complément Salaire
	  */
	public BigDecimal getComplement_Salaire();

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

    /** Column name Domesticite */
    public static final String COLUMNNAME_Domesticite = "Domesticite";

	/** Set Domesticité.
	  * Domesticité
	  */
	public void setDomesticite (BigDecimal Domesticite);

	/** Get Domesticité.
	  * Domesticité
	  */
	public BigDecimal getDomesticite();

    /** Column name HR_CategorieProfessionnelle_ID */
    public static final String COLUMNNAME_HR_CategorieProfessionnelle_ID = "HR_CategorieProfessionnelle_ID";

	/** Set Catégorie Professionnelle	  */
	public void setHR_CategorieProfessionnelle_ID (int HR_CategorieProfessionnelle_ID);

	/** Get Catégorie Professionnelle	  */
	public int getHR_CategorieProfessionnelle_ID();

	public I_HR_CategorieProfessionnelle getHR_CategorieProfessionnelle() throws RuntimeException;

    /** Column name HR_Echelon_ID */
    public static final String COLUMNNAME_HR_Echelon_ID = "HR_Echelon_ID";

	/** Set Échelon Professionnel	  */
	public void setHR_Echelon_ID (int HR_Echelon_ID);

	/** Get Échelon Professionnel	  */
	public int getHR_Echelon_ID();

	public I_HR_Echelon getHR_Echelon() throws RuntimeException;

    /** Column name HR_ElementBasePaieEmploye_ID */
    public static final String COLUMNNAME_HR_ElementBasePaieEmploye_ID = "HR_ElementBasePaieEmploye_ID";

	/** Set Élément de Base de la Paie Employé	  */
	public void setHR_ElementBasePaieEmploye_ID (int HR_ElementBasePaieEmploye_ID);

	/** Get Élément de Base de la Paie Employé	  */
	public int getHR_ElementBasePaieEmploye_ID();

    /** Column name HR_ElementBasePaieEmploye_UU */
    public static final String COLUMNNAME_HR_ElementBasePaieEmploye_UU = "HR_ElementBasePaieEmploye_UU";

	/** Set HR_ElementBasePaieEmploye_UU	  */
	public void setHR_ElementBasePaieEmploye_UU (String HR_ElementBasePaieEmploye_UU);

	/** Get HR_ElementBasePaieEmploye_UU	  */
	public String getHR_ElementBasePaieEmploye_UU();

    /** Column name HR_Job_ID */
    public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

	/** Set Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID);

	/** Get Payroll Job	  */
	public int getHR_Job_ID();

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException;

    /** Column name Indemnite_Deces */
    public static final String COLUMNNAME_Indemnite_Deces = "Indemnite_Deces";

	/** Set Indemnité de Décès.
	  * Indemnité de Décès
	  */
	public void setIndemnite_Deces (BigDecimal Indemnite_Deces);

	/** Get Indemnité de Décès.
	  * Indemnité de Décès
	  */
	public BigDecimal getIndemnite_Deces();

    /** Column name Indemnite_Logement */
    public static final String COLUMNNAME_Indemnite_Logement = "Indemnite_Logement";

	/** Set Indemnité de Logement.
	  * Indemnité de Logement
	  */
	public void setIndemnite_Logement (BigDecimal Indemnite_Logement);

	/** Get Indemnité de Logement.
	  * Indemnité de Logement
	  */
	public BigDecimal getIndemnite_Logement();

    /** Column name Indemnite_Logement_Complement */
    public static final String COLUMNNAME_Indemnite_Logement_Complement = "Indemnite_Logement_Complement";

	/** Set Complément Indemnité Logement	  */
	public void setIndemnite_Logement_Complement (BigDecimal Indemnite_Logement_Complement);

	/** Get Complément Indemnité Logement	  */
	public BigDecimal getIndemnite_Logement_Complement();

    /** Column name Indemnite_Panier_Nuit */
    public static final String COLUMNNAME_Indemnite_Panier_Nuit = "Indemnite_Panier_Nuit";

	/** Set Indemnité de Panier de Nuit.
	  * Indemnité de Panier de Nuit
	  */
	public void setIndemnite_Panier_Nuit (BigDecimal Indemnite_Panier_Nuit);

	/** Get Indemnité de Panier de Nuit.
	  * Indemnité de Panier de Nuit
	  */
	public BigDecimal getIndemnite_Panier_Nuit();

    /** Column name Indemnite_Representation */
    public static final String COLUMNNAME_Indemnite_Representation = "Indemnite_Representation";

	/** Set Indemnité de Représentation.
	  * Indemnité de Représentation
	  */
	public void setIndemnite_Representation (BigDecimal Indemnite_Representation);

	/** Get Indemnité de Représentation.
	  * Indemnité de Représentation
	  */
	public BigDecimal getIndemnite_Representation();

    /** Column name Indemnite_Transport */
    public static final String COLUMNNAME_Indemnite_Transport = "Indemnite_Transport";

	/** Set Indemnité de Transport.
	  * Indemnité de Transport
	  */
	public void setIndemnite_Transport (BigDecimal Indemnite_Transport);

	/** Get Indemnité de Transport.
	  * Indemnité de Transport
	  */
	public BigDecimal getIndemnite_Transport();

    /** Column name Indemnite_Transport_Complement */
    public static final String COLUMNNAME_Indemnite_Transport_Complement = "Indemnite_Transport_Complement";

	/** Set Complément Indemnité Transport	  */
	public void setIndemnite_Transport_Complement (BigDecimal Indemnite_Transport_Complement);

	/** Get Complément Indemnité Transport	  */
	public BigDecimal getIndemnite_Transport_Complement();

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

    /** Column name Prime_Caisse */
    public static final String COLUMNNAME_Prime_Caisse = "Prime_Caisse";

	/** Set Prime de Caisse.
	  * Prime de Caisse
	  */
	public void setPrime_Caisse (BigDecimal Prime_Caisse);

	/** Get Prime de Caisse.
	  * Prime de Caisse
	  */
	public BigDecimal getPrime_Caisse();

    /** Column name Prime_Outillage */
    public static final String COLUMNNAME_Prime_Outillage = "Prime_Outillage";

	/** Set Prime d&#039;
Outillage.
	  * Prime d&#039;
Outillage
	  */
	public void setPrime_Outillage (BigDecimal Prime_Outillage);

	/** Get Prime d&#039;
Outillage.
	  * Prime d&#039;
Outillage
	  */
	public BigDecimal getPrime_Outillage();

    /** Column name Prime_Rendement */
    public static final String COLUMNNAME_Prime_Rendement = "Prime_Rendement";

	/** Set Prime de Rendement.
	  * Prime de Rendement
	  */
	public void setPrime_Rendement (BigDecimal Prime_Rendement);

	/** Get Prime de Rendement.
	  * Prime de Rendement
	  */
	public BigDecimal getPrime_Rendement();

    /** Column name Prime_Responsabilite */
    public static final String COLUMNNAME_Prime_Responsabilite = "Prime_Responsabilite";

	/** Set Prime de Responsabilité	  */
	public void setPrime_Responsabilite (BigDecimal Prime_Responsabilite);

	/** Get Prime de Responsabilité	  */
	public BigDecimal getPrime_Responsabilite();

    /** Column name Prime_Risque */
    public static final String COLUMNNAME_Prime_Risque = "Prime_Risque";

	/** Set Prime de Risque.
	  * Prime de Risque
	  */
	public void setPrime_Risque (BigDecimal Prime_Risque);

	/** Get Prime de Risque.
	  * Prime de Risque
	  */
	public BigDecimal getPrime_Risque();

    /** Column name Rappel_Imposable */
    public static final String COLUMNNAME_Rappel_Imposable = "Rappel_Imposable";

	/** Set Rappel Imposable.
	  * Rappel Imposable
	  */
	public void setRappel_Imposable (BigDecimal Rappel_Imposable);

	/** Get Rappel Imposable.
	  * Rappel Imposable
	  */
	public BigDecimal getRappel_Imposable();

    /** Column name Rappel_Non_Imposable */
    public static final String COLUMNNAME_Rappel_Non_Imposable = "Rappel_Non_Imposable";

	/** Set Rappel Non Imposable.
	  * Rappel Non Imposable
	  */
	public void setRappel_Non_Imposable (BigDecimal Rappel_Non_Imposable);

	/** Get Rappel Non Imposable.
	  * Rappel Non Imposable
	  */
	public BigDecimal getRappel_Non_Imposable();

    /** Column name Salaire_Base */
    public static final String COLUMNNAME_Salaire_Base = "Salaire_Base";

	/** Set Salaire de Base.
	  * Salaire de Base
	  */
	public void setSalaire_Base (BigDecimal Salaire_Base);

	/** Get Salaire de Base.
	  * Salaire de Base
	  */
	public BigDecimal getSalaire_Base();

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
