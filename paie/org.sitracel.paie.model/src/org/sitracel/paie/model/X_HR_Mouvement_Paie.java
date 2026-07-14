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
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_Mouvement_Paie
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Mouvement_Paie")
public class X_HR_Mouvement_Paie extends PO implements I_HR_Mouvement_Paie, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260714L;

    /** Standard Constructor */
    public X_HR_Mouvement_Paie (Properties ctx, int HR_Mouvement_Paie_ID, String trxName)
    {
      super (ctx, HR_Mouvement_Paie_ID, trxName);
      /** if (HR_Mouvement_Paie_ID == 0)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_MouvementPaieType_ID (0);
			setHR_Mouvement_Paie_ID (0);
			setIsIndemnite (false);
// 'N'
			setIsRecurrent (false);
// 'N'
			setMontant_Total (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mouvement_Paie (Properties ctx, int HR_Mouvement_Paie_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mouvement_Paie_ID, trxName, virtualColumns);
      /** if (HR_Mouvement_Paie_ID == 0)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_MouvementPaieType_ID (0);
			setHR_Mouvement_Paie_ID (0);
			setIsIndemnite (false);
// 'N'
			setIsRecurrent (false);
// 'N'
			setMontant_Total (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mouvement_Paie (Properties ctx, String HR_Mouvement_Paie_UU, String trxName)
    {
      super (ctx, HR_Mouvement_Paie_UU, trxName);
      /** if (HR_Mouvement_Paie_UU == null)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_MouvementPaieType_ID (0);
			setHR_Mouvement_Paie_ID (0);
			setIsIndemnite (false);
// 'N'
			setIsRecurrent (false);
// 'N'
			setMontant_Total (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Mouvement_Paie (Properties ctx, String HR_Mouvement_Paie_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Mouvement_Paie_UU, trxName, virtualColumns);
      /** if (HR_Mouvement_Paie_UU == null)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_MouvementPaieType_ID (0);
			setHR_Mouvement_Paie_ID (0);
			setIsIndemnite (false);
// 'N'
			setIsRecurrent (false);
// 'N'
			setMontant_Total (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Mouvement_Paie (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_Mouvement_Paie[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Acompte déjà Versé.
		@param Acompte Acompte déjà Versé
	*/
	public void setAcompte (BigDecimal Acompte)
	{
		set_Value (COLUMNNAME_Acompte, Acompte);
	}

	/** Get Acompte déjà Versé.
		@return Acompte déjà Versé
	  */
	public BigDecimal getAcompte()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Acompte);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner .
		@param C_BPartner_ID Identifies a Business Partner
	*/
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set à Partir de :.
		@param Date_Debut à Partir de :
	*/
	public void setDate_Debut (Timestamp Date_Debut)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Debut, Date_Debut);
	}

	/** Get à Partir de :.
		@return à Partir de :
	  */
	public Timestamp getDate_Debut()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Debut);
	}

	/** Set Jusqu&#039;au :.
		@param Date_Fin Jusqu&#039;au :
	*/
	public void setDate_Fin (Timestamp Date_Fin)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Fin, Date_Fin);
	}

	/** Get Jusqu&#039;au :.
		@return Jusqu&#039;au :
	  */
	public Timestamp getDate_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Fin);
	}

	public I_HR_Periode_Salariale getDebut_Prelevement() throws RuntimeException
	{
		return (I_HR_Periode_Salariale)MTable.get(getCtx(), I_HR_Periode_Salariale.Table_ID)
			.getPO(getDebut_Prelevement_ID(), get_TrxName());
	}

	/** Set Prélèvement à Partir de .
		@param Debut_Prelevement_ID Prélèvement à Partir de 
	*/
	public void setDebut_Prelevement_ID (int Debut_Prelevement_ID)
	{
		if (Debut_Prelevement_ID < 1)
			set_Value (COLUMNNAME_Debut_Prelevement_ID, null);
		else
			set_Value (COLUMNNAME_Debut_Prelevement_ID, Integer.valueOf(Debut_Prelevement_ID));
	}

	/** Get Prélèvement à Partir de .
		@return Prélèvement à Partir de 
	  */
	public int getDebut_Prelevement_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Debut_Prelevement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Periode_Salariale getFin_Prelevement() throws RuntimeException
	{
		return (I_HR_Periode_Salariale)MTable.get(getCtx(), I_HR_Periode_Salariale.Table_ID)
			.getPO(getFin_Prelevement_ID(), get_TrxName());
	}

	/** Set Prélèvement Jusqu&#039;à  .
		@param Fin_Prelevement_ID Prélèvement Jusqu&#039;à  
	*/
	public void setFin_Prelevement_ID (int Fin_Prelevement_ID)
	{
		if (Fin_Prelevement_ID < 1)
			set_Value (COLUMNNAME_Fin_Prelevement_ID, null);
		else
			set_Value (COLUMNNAME_Fin_Prelevement_ID, Integer.valueOf(Fin_Prelevement_ID));
	}

	/** Get Prélèvement Jusqu&#039;à  .
		@return Prélèvement Jusqu&#039;à  
	  */
	public int getFin_Prelevement_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fin_Prelevement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Annee getHR_Annee() throws RuntimeException
	{
		return (I_HR_Annee)MTable.get(getCtx(), I_HR_Annee.Table_ID)
			.getPO(getHR_Annee_ID(), get_TrxName());
	}

	/** Set Année.
		@param HR_Annee_ID Année
	*/
	public void setHR_Annee_ID (int HR_Annee_ID)
	{
		if (HR_Annee_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Annee_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Annee_ID, Integer.valueOf(HR_Annee_ID));
	}

	/** Get Année.
		@return Année	  */
	public int getHR_Annee_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Annee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Element_Base_Paie getHR_Element_Base_Paie() throws RuntimeException
	{
		return (I_HR_Element_Base_Paie)MTable.get(getCtx(), I_HR_Element_Base_Paie.Table_ID)
			.getPO(getHR_Element_Base_Paie_ID(), get_TrxName());
	}

	/** Set Élément de Paie.
		@param HR_Element_Base_Paie_ID Élément de Paie
	*/
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID)
	{
		if (HR_Element_Base_Paie_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, Integer.valueOf(HR_Element_Base_Paie_ID));
	}

	/** Get Élément de Paie.
		@return Élément de Paie	  */
	public int getHR_Element_Base_Paie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Element_Base_Paie_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Mois getHR_Mois() throws RuntimeException
	{
		return (I_HR_Mois)MTable.get(getCtx(), I_HR_Mois.Table_ID)
			.getPO(getHR_Mois_ID(), get_TrxName());
	}

	/** Set Mois.
		@param HR_Mois_ID Mois
	*/
	public void setHR_Mois_ID (int HR_Mois_ID)
	{
		if (HR_Mois_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mois_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mois_ID, Integer.valueOf(HR_Mois_ID));
	}

	/** Get Mois.
		@return Mois	  */
	public int getHR_Mois_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mois_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_MouvementPaieType getHR_MouvementPaieType() throws RuntimeException
	{
		return (I_HR_MouvementPaieType)MTable.get(getCtx(), I_HR_MouvementPaieType.Table_ID)
			.getPO(getHR_MouvementPaieType_ID(), get_TrxName());
	}

	/** Set Type de Mouvement Paie.
		@param HR_MouvementPaieType_ID Type de Mouvement Paie
	*/
	public void setHR_MouvementPaieType_ID (int HR_MouvementPaieType_ID)
	{
		if (HR_MouvementPaieType_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_MouvementPaieType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_MouvementPaieType_ID, Integer.valueOf(HR_MouvementPaieType_ID));
	}

	/** Get Type de Mouvement Paie.
		@return Type de Mouvement Paie	  */
	public int getHR_MouvementPaieType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_MouvementPaieType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Mouvement de Paie.
		@param HR_Mouvement_Paie_ID Mouvement de Paie
	*/
	public void setHR_Mouvement_Paie_ID (int HR_Mouvement_Paie_ID)
	{
		if (HR_Mouvement_Paie_ID < 1)
			set_ValueNoCheck (COLUMNNAME_HR_Mouvement_Paie_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_HR_Mouvement_Paie_ID, Integer.valueOf(HR_Mouvement_Paie_ID));
	}

	/** Get Mouvement de Paie.
		@return Mouvement de Paie	  */
	public int getHR_Mouvement_Paie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mouvement_Paie_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Retenue_Salariale_UU.
		@param HR_Mouvement_Paie_UU HR_Retenue_Salariale_UU
	*/
	public void setHR_Mouvement_Paie_UU (String HR_Mouvement_Paie_UU)
	{
		set_Value (COLUMNNAME_HR_Mouvement_Paie_UU, HR_Mouvement_Paie_UU);
	}

	/** Get HR_Retenue_Salariale_UU.
		@return HR_Retenue_Salariale_UU	  */
	public String getHR_Mouvement_Paie_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Mouvement_Paie_UU);
	}

	/** Set Est une Indemnité.
		@param IsIndemnite Est une Indemnité
	*/
	public void setIsIndemnite (boolean IsIndemnite)
	{
		set_Value (COLUMNNAME_IsIndemnite, Boolean.valueOf(IsIndemnite));
	}

	/** Get Est une Indemnité.
		@return Est une Indemnité	  */
	public boolean isIndemnite()
	{
		Object oo = get_Value(COLUMNNAME_IsIndemnite);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Participe au Calcul des Indemnités de Licenciement.
		@param IsIndemniteLicenciement Participe au Calcul des Indemnités de Licenciement
	*/
	public void setIsIndemniteLicenciement (boolean IsIndemniteLicenciement)
	{
		set_Value (COLUMNNAME_IsIndemniteLicenciement, Boolean.valueOf(IsIndemniteLicenciement));
	}

	/** Get Participe au Calcul des Indemnités de Licenciement.
		@return Participe au Calcul des Indemnités de Licenciement	  */
	public boolean isIndemniteLicenciement()
	{
		Object oo = get_Value(COLUMNNAME_IsIndemniteLicenciement);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Participe au Calcul des Indemnités de Départ à la Retraite.
		@param IsIndemniteRetraite Participe au Calcul des Indemnités de Départ à la Retraite
	*/
	public void setIsIndemniteRetraite (boolean IsIndemniteRetraite)
	{
		set_Value (COLUMNNAME_IsIndemniteRetraite, Boolean.valueOf(IsIndemniteRetraite));
	}

	/** Get Participe au Calcul des Indemnités de Départ à la Retraite.
		@return Participe au Calcul des Indemnités de Départ à la Retraite	  */
	public boolean isIndemniteRetraite()
	{
		Object oo = get_Value(COLUMNNAME_IsIndemniteRetraite);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Est Récurrent.
		@param IsRecurrent Est Récurrent
	*/
	public void setIsRecurrent (boolean IsRecurrent)
	{
		set_Value (COLUMNNAME_IsRecurrent, Boolean.valueOf(IsRecurrent));
	}

	/** Get Est Récurrent.
		@return Est Récurrent	  */
	public boolean isRecurrent()
	{
		Object oo = get_Value(COLUMNNAME_IsRecurrent);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Montant de la Dernière Mensualité.
		@param Montant_Derniere_Mensualite Montant de la Dernière Mensualité
	*/
	public void setMontant_Derniere_Mensualite (BigDecimal Montant_Derniere_Mensualite)
	{
		set_Value (COLUMNNAME_Montant_Derniere_Mensualite, Montant_Derniere_Mensualite);
	}

	/** Get Montant de la Dernière Mensualité.
		@return Montant de la Dernière Mensualité
	  */
	public BigDecimal getMontant_Derniere_Mensualite()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Derniere_Mensualite);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Montant des Mensualités.
		@param Montant_Mensualite Montant des Mensualités
	*/
	public void setMontant_Mensualite (BigDecimal Montant_Mensualite)
	{
		set_Value (COLUMNNAME_Montant_Mensualite, Montant_Mensualite);
	}

	/** Get Montant des Mensualités.
		@return Montant des Mensualités
	  */
	public BigDecimal getMontant_Mensualite()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Mensualite);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Montant Total.
		@param Montant_Total Montant Total de la Retenue
	*/
	public void setMontant_Total (BigDecimal Montant_Total)
	{
		set_Value (COLUMNNAME_Montant_Total, Montant_Total);
	}

	/** Get Montant Total.
		@return Montant Total de la Retenue
	  */
	public BigDecimal getMontant_Total()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Total);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Nombre de Mensualité.
		@param Nombre_Mensualite Nombre de Mensualité
	*/
	public void setNombre_Mensualite (int Nombre_Mensualite)
	{
		set_Value (COLUMNNAME_Nombre_Mensualite, Integer.valueOf(Nombre_Mensualite));
	}

	/** Get Nombre de Mensualité.
		@return Nombre de Mensualité
	  */
	public int getNombre_Mensualite()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Mensualite);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Solde.
		@param Solde Montant Restant
	*/
	public void setSolde (BigDecimal Solde)
	{
		set_Value (COLUMNNAME_Solde, Solde);
	}

	/** Get Solde.
		@return Montant Restant
	  */
	public BigDecimal getSolde()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Solde);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}