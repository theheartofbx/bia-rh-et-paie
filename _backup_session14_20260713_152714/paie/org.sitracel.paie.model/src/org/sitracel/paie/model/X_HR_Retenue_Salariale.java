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

/** Generated Model for HR_Retenue_Salariale
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="HR_Retenue_Salariale")
public class X_HR_Retenue_Salariale extends PO implements I_HR_Retenue_Salariale, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250823L;

    /** Standard Constructor */
    public X_HR_Retenue_Salariale (Properties ctx, int HR_Retenue_Salariale_ID, String trxName)
    {
      super (ctx, HR_Retenue_Salariale_ID, trxName);
      /** if (HR_Retenue_Salariale_ID == 0)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_Element_Base_Paie_ID (0);
			setHR_Retenue_Salariale_ID (0);
			setMontant_Total_Retenue (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Retenue_Salariale (Properties ctx, int HR_Retenue_Salariale_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Retenue_Salariale_ID, trxName, virtualColumns);
      /** if (HR_Retenue_Salariale_ID == 0)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_Element_Base_Paie_ID (0);
			setHR_Retenue_Salariale_ID (0);
			setMontant_Total_Retenue (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Retenue_Salariale (Properties ctx, String HR_Retenue_Salariale_UU, String trxName)
    {
      super (ctx, HR_Retenue_Salariale_UU, trxName);
      /** if (HR_Retenue_Salariale_UU == null)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_Element_Base_Paie_ID (0);
			setHR_Retenue_Salariale_ID (0);
			setMontant_Total_Retenue (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Standard Constructor */
    public X_HR_Retenue_Salariale (Properties ctx, String HR_Retenue_Salariale_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, HR_Retenue_Salariale_UU, trxName, virtualColumns);
      /** if (HR_Retenue_Salariale_UU == null)
        {
			setC_BPartner_ID (0);
			setDebut_Prelevement_ID (0);
			setHR_Element_Base_Paie_ID (0);
			setHR_Retenue_Salariale_ID (0);
			setMontant_Total_Retenue (Env.ZERO);
			setName (null);
			setNombre_Mensualite (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Retenue_Salariale (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Retenue_Salariale[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Acompte déjà Versé.
		@param Acompte Acompte déjà Versé
	*/
	@Override
	public void setAcompte (BigDecimal Acompte)
	{
		set_Value (COLUMNNAME_Acompte, Acompte);
	}

	/** Get Acompte déjà Versé.
		@return Acompte déjà Versé
	  */
	@Override
	public BigDecimal getAcompte()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Acompte);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
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

	@Override
	public I_HR_Periode_Salariale getDebut_Prelevement() throws RuntimeException
	{
		return (I_HR_Periode_Salariale)MTable.get(getCtx(), I_HR_Periode_Salariale.Table_ID)
			.getPO(getDebut_Prelevement_ID(), get_TrxName());
	}

	/** Set Prélèvement à Partir de .
		@param Debut_Prelevement_ID Prélèvement à Partir de
	*/
	@Override
	public void setDebut_Prelevement_ID (int Debut_Prelevement_ID)
	{
		if (Debut_Prelevement_ID < 1) {
			set_Value (COLUMNNAME_Debut_Prelevement_ID, null);
		} else {
			set_Value (COLUMNNAME_Debut_Prelevement_ID, Integer.valueOf(Debut_Prelevement_ID));
		}
	}

	/** Get Prélèvement à Partir de .
		@return Prélèvement à Partir de
	  */
	@Override
	public int getDebut_Prelevement_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Debut_Prelevement_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Periode_Salariale getFin_Prelevement() throws RuntimeException
	{
		return (I_HR_Periode_Salariale)MTable.get(getCtx(), I_HR_Periode_Salariale.Table_ID)
			.getPO(getFin_Prelevement_ID(), get_TrxName());
	}

	/** Set Prélèvement Jusqu&#039;à  .
		@param Fin_Prelevement_ID Prélèvement Jusqu&#039;à
	*/
	@Override
	public void setFin_Prelevement_ID (int Fin_Prelevement_ID)
	{
		if (Fin_Prelevement_ID < 1) {
			set_Value (COLUMNNAME_Fin_Prelevement_ID, null);
		} else {
			set_Value (COLUMNNAME_Fin_Prelevement_ID, Integer.valueOf(Fin_Prelevement_ID));
		}
	}

	/** Get Prélèvement Jusqu&#039;à  .
		@return Prélèvement Jusqu&#039;à
	  */
	@Override
	public int getFin_Prelevement_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fin_Prelevement_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Annee getHR_Annee() throws RuntimeException
	{
		return (I_HR_Annee)MTable.get(getCtx(), I_HR_Annee.Table_ID)
			.getPO(getHR_Annee_ID(), get_TrxName());
	}

	/** Set Année.
		@param HR_Annee_ID Année
	*/
	@Override
	public void setHR_Annee_ID (int HR_Annee_ID)
	{
		if (HR_Annee_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Annee_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Annee_ID, Integer.valueOf(HR_Annee_ID));
		}
	}

	/** Get Année.
		@return Année	  */
	@Override
	public int getHR_Annee_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Annee_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Element_Base_Paie getHR_Element_Base_Paie() throws RuntimeException
	{
		return (I_HR_Element_Base_Paie)MTable.get(getCtx(), I_HR_Element_Base_Paie.Table_ID)
			.getPO(getHR_Element_Base_Paie_ID(), get_TrxName());
	}

	/** Set Élément de Paie.
		@param HR_Element_Base_Paie_ID Élément de Paie
	*/
	@Override
	public void setHR_Element_Base_Paie_ID (int HR_Element_Base_Paie_ID)
	{
		if (HR_Element_Base_Paie_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Element_Base_Paie_ID, Integer.valueOf(HR_Element_Base_Paie_ID));
		}
	}

	/** Get Élément de Paie.
		@return Élément de Paie	  */
	@Override
	public int getHR_Element_Base_Paie_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Element_Base_Paie_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	@Override
	public I_HR_Mois getHR_Mois() throws RuntimeException
	{
		return (I_HR_Mois)MTable.get(getCtx(), I_HR_Mois.Table_ID)
			.getPO(getHR_Mois_ID(), get_TrxName());
	}

	/** Set Mois.
		@param HR_Mois_ID Mois
	*/
	@Override
	public void setHR_Mois_ID (int HR_Mois_ID)
	{
		if (HR_Mois_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Mois_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Mois_ID, Integer.valueOf(HR_Mois_ID));
		}
	}

	/** Get Mois.
		@return Mois	  */
	@Override
	public int getHR_Mois_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Mois_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Retenue Salariale.
		@param HR_Retenue_Salariale_ID Retenue Salariale
	*/
	@Override
	public void setHR_Retenue_Salariale_ID (int HR_Retenue_Salariale_ID)
	{
		if (HR_Retenue_Salariale_ID < 1) {
			set_ValueNoCheck (COLUMNNAME_HR_Retenue_Salariale_ID, null);
		} else {
			set_ValueNoCheck (COLUMNNAME_HR_Retenue_Salariale_ID, Integer.valueOf(HR_Retenue_Salariale_ID));
		}
	}

	/** Get Retenue Salariale.
		@return Retenue Salariale	  */
	@Override
	public int getHR_Retenue_Salariale_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Retenue_Salariale_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set HR_Retenue_Salariale_UU.
		@param HR_Retenue_Salariale_UU HR_Retenue_Salariale_UU
	*/
	@Override
	public void setHR_Retenue_Salariale_UU (String HR_Retenue_Salariale_UU)
	{
		set_Value (COLUMNNAME_HR_Retenue_Salariale_UU, HR_Retenue_Salariale_UU);
	}

	/** Get HR_Retenue_Salariale_UU.
		@return HR_Retenue_Salariale_UU	  */
	@Override
	public String getHR_Retenue_Salariale_UU()
	{
		return (String)get_Value(COLUMNNAME_HR_Retenue_Salariale_UU);
	}

	/** Set Participe au Calcul des Indemnités de Licenciement.
		@param IsIndemniteLicenciement Participe au Calcul des Indemnités de Licenciement
	*/
	@Override
	public void setIsIndemniteLicenciement (boolean IsIndemniteLicenciement)
	{
		set_Value (COLUMNNAME_IsIndemniteLicenciement, Boolean.valueOf(IsIndemniteLicenciement));
	}

	/** Get Participe au Calcul des Indemnités de Licenciement.
		@return Participe au Calcul des Indemnités de Licenciement	  */
	@Override
	public boolean isIndemniteLicenciement()
	{
		Object oo = get_Value(COLUMNNAME_IsIndemniteLicenciement);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Participe au Calcul des Indemnités de Départ à la Retraite.
		@param IsIndemniteRetraite Participe au Calcul des Indemnités de Départ à la Retraite
	*/
	@Override
	public void setIsIndemniteRetraite (boolean IsIndemniteRetraite)
	{
		set_Value (COLUMNNAME_IsIndemniteRetraite, Boolean.valueOf(IsIndemniteRetraite));
	}

	/** Get Participe au Calcul des Indemnités de Départ à la Retraite.
		@return Participe au Calcul des Indemnités de Départ à la Retraite	  */
	@Override
	public boolean isIndemniteRetraite()
	{
		Object oo = get_Value(COLUMNNAME_IsIndemniteRetraite);
		if (oo != null)
		{
			 if (oo instanceof Boolean) {
				return ((Boolean)oo).booleanValue();
			}
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Montant de la Dernière Mensualité.
		@param Montant_Derniere_Mensualite Montant de la Dernière Mensualité
	*/
	@Override
	public void setMontant_Derniere_Mensualite (BigDecimal Montant_Derniere_Mensualite)
	{
		set_Value (COLUMNNAME_Montant_Derniere_Mensualite, Montant_Derniere_Mensualite);
	}

	/** Get Montant de la Dernière Mensualité.
		@return Montant de la Dernière Mensualité
	  */
	@Override
	public BigDecimal getMontant_Derniere_Mensualite()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Derniere_Mensualite);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Montant des Mensualités.
		@param Montant_Mensualite Montant des Mensualités
	*/
	@Override
	public void setMontant_Mensualite (BigDecimal Montant_Mensualite)
	{
		set_Value (COLUMNNAME_Montant_Mensualite, Montant_Mensualite);
	}

	/** Get Montant des Mensualités.
		@return Montant des Mensualités
	  */
	@Override
	public BigDecimal getMontant_Mensualite()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Mensualite);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Montant Total de la Retenue.
		@param Montant_Total_Retenue Montant Total de la Retenue
	*/
	@Override
	public void setMontant_Total_Retenue (BigDecimal Montant_Total_Retenue)
	{
		set_Value (COLUMNNAME_Montant_Total_Retenue, Montant_Total_Retenue);
	}

	/** Get Montant Total de la Retenue.
		@return Montant Total de la Retenue
	  */
	@Override
	public BigDecimal getMontant_Total_Retenue()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Montant_Total_Retenue);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	@Override
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	@Override
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Nombre de Mensualité.
		@param Nombre_Mensualite Nombre de Mensualité
	*/
	@Override
	public void setNombre_Mensualite (int Nombre_Mensualite)
	{
		set_Value (COLUMNNAME_Nombre_Mensualite, Integer.valueOf(Nombre_Mensualite));
	}

	/** Get Nombre de Mensualité.
		@return Nombre de Mensualité
	  */
	@Override
	public int getNombre_Mensualite()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nombre_Mensualite);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}

	/** Set Montant Restant.
		@param Reste_Retenue Montant Restant
	*/
	@Override
	public void setReste_Retenue (BigDecimal Reste_Retenue)
	{
		set_Value (COLUMNNAME_Reste_Retenue, Reste_Retenue);
	}

	/** Get Montant Restant.
		@return Montant Restant
	  */
	@Override
	public BigDecimal getReste_Retenue()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Reste_Retenue);
		if (bd == null) {
			return Env.ZERO;
		}
		return bd;
	}
}