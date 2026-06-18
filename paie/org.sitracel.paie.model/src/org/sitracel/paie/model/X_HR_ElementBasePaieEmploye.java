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
/** Generated Model - DO NOT CHANGE */
package org.sitracel.paie.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for HR_ElementBasePaieEmploye
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_ElementBasePaieEmploye")
public class X_HR_ElementBasePaieEmploye extends PO implements I_HR_ElementBasePaieEmploye, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250819L;

    /** Standard Constructor */
    public X_HR_ElementBasePaieEmploye (Properties ctx, int HR_ElementBasePaieEmploye_ID, String trxName)
    {
      super (ctx, HR_ElementBasePaieEmploye_ID, trxName);
      /** if (HR_ElementBasePaieEmploye_ID == 0)
        {
			setC_BPartner_ID (0);
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_ElementBasePaieEmploye_ID (0);
			setHR_Job_ID (0);
			setHR_Taux_Salarial_ID (0);
			setHR_TypeContrat_ID (0);
			setSalaire_Base (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_ElementBasePaieEmploye (Properties ctx, int HR_ElementBasePaieEmploye_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_ElementBasePaieEmploye_ID, trxName, virtualColumns);
      /** if (HR_ElementBasePaieEmploye_ID == 0)
        {
			setC_BPartner_ID (0);
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_ElementBasePaieEmploye_ID (0);
			setHR_Job_ID (0);
			setHR_Taux_Salarial_ID (0);
			setHR_TypeContrat_ID (0);
			setSalaire_Base (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_ElementBasePaieEmploye (Properties ctx, String HR_ElementBasePaieEmploye_UU, String trxName)
    {
      super (ctx, HR_ElementBasePaieEmploye_UU, trxName);
      /** if (HR_ElementBasePaieEmploye_UU == null)
        {
			setC_BPartner_ID (0);
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_ElementBasePaieEmploye_ID (0);
			setHR_Job_ID (0);
			setHR_Taux_Salarial_ID (0);
			setHR_TypeContrat_ID (0);
			setSalaire_Base (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_HR_ElementBasePaieEmploye (Properties ctx, String HR_ElementBasePaieEmploye_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_ElementBasePaieEmploye_UU, trxName, virtualColumns);
      /** if (HR_ElementBasePaieEmploye_UU == null)
        {
			setC_BPartner_ID (0);
			setDate_Debut (new Timestamp( System.currentTimeMillis() ));
			setHR_ElementBasePaieEmploye_ID (0);
			setHR_Job_ID (0);
			setHR_Taux_Salarial_ID (0);
			setHR_TypeContrat_ID (0);
			setSalaire_Base (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_ElementBasePaieEmploye (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    @Override
	protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    @Override
	protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    @Override
	public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_ElementBasePaieEmploye[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	@Override
	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner .
		@param C_BPartner_ID Identifies a Business Partner
	*/
	@Override
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
		}
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	@Override
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Charger Dernier Enregistrement.
		@param Charger_Dernier Charger Dernier Enregistrement
	*/
	@Override
	public void setCharger_Dernier (boolean Charger_Dernier)
	{
		set_ValueNoCheck (COLUMNNAME_Charger_Dernier, Boolean.valueOf(Charger_Dernier));
	}

	/** Get Charger Dernier Enregistrement.
		@return Charger Dernier Enregistrement	  */
	@Override
	public boolean isCharger_Dernier()
	{
		Object oo = get_Value(COLUMNNAME_Charger_Dernier);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Complément Salaire.
		@param Complement_Salaire Complément Salaire
	*/
	@Override
	public void setComplement_Salaire (BigDecimal Complement_Salaire)
	{
		set_ValueNoCheck (COLUMNNAME_Complement_Salaire, Complement_Salaire);
	}

	/** Get Complément Salaire.
		@return Complément Salaire
	  */
	@Override
	public BigDecimal getComplement_Salaire()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Complement_Salaire);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	@Override
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	@Override
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
	}

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	@Override
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	@Override
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
	}

	/** Set Domesticité.
		@param Domesticite Domesticité
	*/
	@Override
	public void setDomesticite (BigDecimal Domesticite)
	{
		set_ValueNoCheck (COLUMNNAME_Domesticite, Domesticite);
	}

	/** Get Domesticité.
		@return Domesticité
	  */
	@Override
	public BigDecimal getDomesticite()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Domesticite);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	@Override
	public I_HR_CategorieProfessionnelle getHR_CategorieProfessionnelle() throws RuntimeException
	{
		return (I_HR_CategorieProfessionnelle)MTable.get(getCtx(), I_HR_CategorieProfessionnelle.Table_ID)
			.getPO(getHR_CategorieProfessionnelle_ID(), get_TrxName());
	}

	/** Set Catégorie Professionnelle.
		@param HR_CategorieProfessionnelle_ID Catégorie Professionnelle
	*/
	@Override
	public void setHR_CategorieProfessionnelle_ID (int HR_CategorieProfessionnelle_ID)
	{
		if (HR_CategorieProfessionnelle_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_CategorieProfessionnelle_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_CategorieProfessionnelle_ID, Integer.valueOf(HR_CategorieProfessionnelle_ID));
		}
	}

	/** Get Catégorie Professionnelle.
		@return Catégorie Professionnelle	  */
	@Override
	public int getHR_CategorieProfessionnelle_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_CategorieProfessionnelle_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Echelon getHR_Echelon() throws RuntimeException
	{
		return (I_HR_Echelon)MTable.get(getCtx(), I_HR_Echelon.Table_ID)
			.getPO(getHR_Echelon_ID(), get_TrxName());
	}

	/** Set Échelon Professionnel.
		@param HR_Echelon_ID Échelon Professionnel
	*/
	@Override
	public void setHR_Echelon_ID (int HR_Echelon_ID)
	{
		if (HR_Echelon_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Echelon_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Echelon_ID, Integer.valueOf(HR_Echelon_ID));
		}
	}

	/** Get Échelon Professionnel.
		@return Échelon Professionnel	  */
	@Override
	public int getHR_Echelon_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Echelon_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Élément de Base de la Paie Employé.
		@param HR_ElementBasePaieEmploye_ID Élément de Base de la Paie Employé
	*/
	@Override
	public void setHR_ElementBasePaieEmploye_ID (int HR_ElementBasePaieEmploye_ID)
	{
		if (HR_ElementBasePaieEmploye_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_ElementBasePaieEmploye_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_ElementBasePaieEmploye_ID, Integer.valueOf(HR_ElementBasePaieEmploye_ID));
		}
	}

	/** Get Élément de Base de la Paie Employé.
		@return Élément de Base de la Paie Employé	  */
	@Override
	public int getHR_ElementBasePaieEmploye_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ElementBasePaieEmploye_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_ElementBasePaieEmploye_UU.
		@param HR_ElementBasePaieEmploye_UU HR_ElementBasePaieEmploye_UU
	*/
	@Override
	public void setHR_ElementBasePaieEmploye_UU (String HR_ElementBasePaieEmploye_UU)
	{
		set_Value (COLUMNNAME_HR_ElementBasePaieEmploye_UU, HR_ElementBasePaieEmploye_UU);
	}

	/** Get HR_ElementBasePaieEmploye_UU.
		@return HR_ElementBasePaieEmploye_UU	  */
	@Override
	public String getHR_ElementBasePaieEmploye_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_ElementBasePaieEmploye_UU);
	}

	@Override
	public I_HR_GestionPaieEmploye getHR_GestionPaieEmploye() throws RuntimeException
	{
		return (I_HR_GestionPaieEmploye)MTable.get(getCtx(), I_HR_GestionPaieEmploye.Table_ID)
			.getPO(getHR_GestionPaieEmploye_ID(), get_TrxName());
	}

	/** Set Gestion des Éléments de Base de la Paie Employé.
		@param HR_GestionPaieEmploye_ID Gestion des Éléments de Base de la Paie Employé
	*/
	@Override
	public void setHR_GestionPaieEmploye_ID (int HR_GestionPaieEmploye_ID)
	{
		if (HR_GestionPaieEmploye_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_GestionPaieEmploye_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_GestionPaieEmploye_ID, Integer.valueOf(HR_GestionPaieEmploye_ID));
		}
	}

	/** Get Gestion des Éléments de Base de la Paie Employé.
		@return Gestion des Éléments de Base de la Paie Employé	  */
	@Override
	public int getHR_GestionPaieEmploye_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_GestionPaieEmploye_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException
	{
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_ID)
			.getPO(getHR_Job_ID(), get_TrxName());
	}

	/** Set Payroll Job.
		@param HR_Job_ID Payroll Job
	*/
	@Override
	public void setHR_Job_ID (int HR_Job_ID)
	{
		if (HR_Job_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Job_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
		}
	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	@Override
	public int getHR_Job_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Taux_Salarial getHR_Taux_Salarial() throws RuntimeException
	{
		return (I_HR_Taux_Salarial)MTable.get(getCtx(), I_HR_Taux_Salarial.Table_ID)
			.getPO(getHR_Taux_Salarial_ID(), get_TrxName());
	}

	/** Set Taux Salarial.
		@param HR_Taux_Salarial_ID Taux Salarial
	*/
	@Override
	public void setHR_Taux_Salarial_ID (int HR_Taux_Salarial_ID)
	{
		if (HR_Taux_Salarial_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Taux_Salarial_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Taux_Salarial_ID, Integer.valueOf(HR_Taux_Salarial_ID));
		}
	}

	/** Get Taux Salarial.
		@return Taux Salarial	  */
	@Override
	public int getHR_Taux_Salarial_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Taux_Salarial_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_TypeContrat getHR_TypeContrat() throws RuntimeException
	{
		return (I_HR_TypeContrat)MTable.get(getCtx(), I_HR_TypeContrat.Table_ID)
			.getPO(getHR_TypeContrat_ID(), get_TrxName());
	}

	/** Set Type de Contrat.
		@param HR_TypeContrat_ID Type de Contrat
	*/
	@Override
	public void setHR_TypeContrat_ID (int HR_TypeContrat_ID)
	{
		if (HR_TypeContrat_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_TypeContrat_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_TypeContrat_ID, Integer.valueOf(HR_TypeContrat_ID));
		}
	}

	/** Get Type de Contrat.
		@return Type de Contrat	  */
	@Override
	public int getHR_TypeContrat_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_TypeContrat_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Indemnité de Décès.
		@param Indemnite_Deces Indemnité de Décès
	*/
	@Override
	public void setIndemnite_Deces (BigDecimal Indemnite_Deces)
	{
		set_ValueNoCheck (COLUMNNAME_Indemnite_Deces, Indemnite_Deces);
	}

	/** Get Indemnité de Décès.
		@return Indemnité de Décès
	  */
	@Override
	public BigDecimal getIndemnite_Deces()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Indemnite_Deces);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Indemnité de Logement.
		@param Indemnite_Logement Indemnité de Logement
	*/
	@Override
	public void setIndemnite_Logement (BigDecimal Indemnite_Logement)
	{
		set_ValueNoCheck (COLUMNNAME_Indemnite_Logement, Indemnite_Logement);
	}

	/** Get Indemnité de Logement.
		@return Indemnité de Logement
	  */
	@Override
	public BigDecimal getIndemnite_Logement()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Indemnite_Logement);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Complément Indemnité Logement.
		@param Indemnite_Logement_Complement Complément Indemnité Logement
	*/
	@Override
	public void setIndemnite_Logement_Complement (BigDecimal Indemnite_Logement_Complement)
	{
		set_ValueNoCheck (COLUMNNAME_Indemnite_Logement_Complement, Indemnite_Logement_Complement);
	}

	/** Get Complément Indemnité Logement.
		@return Complément Indemnité Logement	  */
	@Override
	public BigDecimal getIndemnite_Logement_Complement()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Indemnite_Logement_Complement);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Indemnité de Panier de Nuit.
		@param Indemnite_Panier_Nuit Indemnité de Panier de Nuit
	*/
	@Override
	public void setIndemnite_Panier_Nuit (BigDecimal Indemnite_Panier_Nuit)
	{
		set_ValueNoCheck (COLUMNNAME_Indemnite_Panier_Nuit, Indemnite_Panier_Nuit);
	}

	/** Get Indemnité de Panier de Nuit.
		@return Indemnité de Panier de Nuit
	  */
	@Override
	public BigDecimal getIndemnite_Panier_Nuit()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Indemnite_Panier_Nuit);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Indemnité de Représentation.
		@param Indemnite_Representation Indemnité de Représentation
	*/
	@Override
	public void setIndemnite_Representation (BigDecimal Indemnite_Representation)
	{
		set_ValueNoCheck (COLUMNNAME_Indemnite_Representation, Indemnite_Representation);
	}

	/** Get Indemnité de Représentation.
		@return Indemnité de Représentation
	  */
	@Override
	public BigDecimal getIndemnite_Representation()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Indemnite_Representation);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Indemnité de Transport.
		@param Indemnite_Transport Indemnité de Transport
	*/
	@Override
	public void setIndemnite_Transport (BigDecimal Indemnite_Transport)
	{
		set_ValueNoCheck (COLUMNNAME_Indemnite_Transport, Indemnite_Transport);
	}

	/** Get Indemnité de Transport.
		@return Indemnité de Transport
	  */
	@Override
	public BigDecimal getIndemnite_Transport()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Indemnite_Transport);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Complément Indemnité Transport.
		@param Indemnite_Transport_Complement Complément Indemnité Transport
	*/
	@Override
	public void setIndemnite_Transport_Complement (BigDecimal Indemnite_Transport_Complement)
	{
		set_ValueNoCheck (COLUMNNAME_Indemnite_Transport_Complement, Indemnite_Transport_Complement);
	}

	/** Get Complément Indemnité Transport.
		@return Complément Indemnité Transport	  */
	@Override
	public BigDecimal getIndemnite_Transport_Complement()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Indemnite_Transport_Complement);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Prime de Caisse.
		@param Prime_Caisse Prime de Caisse
	*/
	@Override
	public void setPrime_Caisse (BigDecimal Prime_Caisse)
	{
		set_ValueNoCheck (COLUMNNAME_Prime_Caisse, Prime_Caisse);
	}

	/** Get Prime de Caisse.
		@return Prime de Caisse
	  */
	@Override
	public BigDecimal getPrime_Caisse()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Prime_Caisse);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Prime d&#039;Outillage.
		@param Prime_Outillage Prime d&#039;Outillage
	*/
	@Override
	public void setPrime_Outillage (BigDecimal Prime_Outillage)
	{
		set_ValueNoCheck (COLUMNNAME_Prime_Outillage, Prime_Outillage);
	}

	/** Get Prime d&#039;Outillage.
		@return Prime d&#039;Outillage
	  */
	@Override
	public BigDecimal getPrime_Outillage()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Prime_Outillage);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Prime de Rendement.
		@param Prime_Rendement Prime de Rendement
	*/
	@Override
	public void setPrime_Rendement (BigDecimal Prime_Rendement)
	{
		set_ValueNoCheck (COLUMNNAME_Prime_Rendement, Prime_Rendement);
	}

	/** Get Prime de Rendement.
		@return Prime de Rendement
	  */
	@Override
	public BigDecimal getPrime_Rendement()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Prime_Rendement);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Prime de Responsabilité.
		@param Prime_Responsabilite Prime de Responsabilité
	*/
	@Override
	public void setPrime_Responsabilite (BigDecimal Prime_Responsabilite)
	{
		set_ValueNoCheck (COLUMNNAME_Prime_Responsabilite, Prime_Responsabilite);
	}

	/** Get Prime de Responsabilité.
		@return Prime de Responsabilité	  */
	@Override
	public BigDecimal getPrime_Responsabilite()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Prime_Responsabilite);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Prime de Risque.
		@param Prime_Risque Prime de Risque
	*/
	@Override
	public void setPrime_Risque (BigDecimal Prime_Risque)
	{
		set_ValueNoCheck (COLUMNNAME_Prime_Risque, Prime_Risque);
	}

	/** Get Prime de Risque.
		@return Prime de Risque
	  */
	@Override
	public BigDecimal getPrime_Risque()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Prime_Risque);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Rappel Imposable.
		@param Rappel_Imposable Rappel Imposable
	*/
	@Override
	public void setRappel_Imposable (BigDecimal Rappel_Imposable)
	{
		set_ValueNoCheck (COLUMNNAME_Rappel_Imposable, Rappel_Imposable);
	}

	/** Get Rappel Imposable.
		@return Rappel Imposable
	  */
	@Override
	public BigDecimal getRappel_Imposable()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rappel_Imposable);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Rappel Non Imposable.
		@param Rappel_Non_Imposable Rappel Non Imposable
	*/
	@Override
	public void setRappel_Non_Imposable (BigDecimal Rappel_Non_Imposable)
	{
		set_ValueNoCheck (COLUMNNAME_Rappel_Non_Imposable, Rappel_Non_Imposable);
	}

	/** Get Rappel Non Imposable.
		@return Rappel Non Imposable
	  */
	@Override
	public BigDecimal getRappel_Non_Imposable()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rappel_Non_Imposable);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Salaire de Base.
		@param Salaire_Base Salaire de Base
	*/
	@Override
	public void setSalaire_Base (BigDecimal Salaire_Base)
	{
		set_ValueNoCheck (COLUMNNAME_Salaire_Base, Salaire_Base);
	}

	/** Get Salaire de Base.
		@return Salaire de Base
	  */
	@Override
	public BigDecimal getSalaire_Base()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salaire_Base);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}
}