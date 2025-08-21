package org.sitracel.paie.process.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptException;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.sitracel.bean.BeanBareme;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanElmtPaie;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.callout.CalloutControllerPaie;
import org.sitracel.paie.model.MHRAttribute;
import org.sitracel.paie.model.MHRBareme;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRCalculIndemniteConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRConcept;
import org.sitracel.paie.model.MHRElementBasePaie;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRElementConge;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.model.MHRRetenueSalariale;
import org.sitracel.paie.model.MHRTypeDeCalcul;

public class ProcessControllerPaie {
	private static CLogger log = CLogger.getCLogger (PO.class);
	public static void mAJJNonPaie(Integer RecordID) {
		if(RecordID!=null) {
			
		}
	}
	
	public static void calculPaie(Integer recordID) {
		if(recordID!=null) {
			MHRAttribute attribut = new MHRAttribute(Env.getCtx(), recordID, null);
			if(attribut!=null) {
				MCBPartner bpartner = new MCBPartner(Env.getCtx(), attribut.getC_BPartner_ID(), null);
				if(bpartner!=null) {
					Integer salaireBaseID = ProcessSqlControllerPaie.getAttributIDbyValue(bpartner.getC_BPartner_ID(), "SB", null);
					if(salaireBaseID!=null) {
						MHRAttribute salaireBase = new MHRAttribute(Env.getCtx(), salaireBaseID, null);
						if(salaireBase!=null) {
							BigDecimal indemniteLogement = ProcessSqlControllerPaie.getAttributAmountbyValue(bpartner.getC_BPartner_ID(), "IL", null);
							BigDecimal indemniteTransport = ProcessSqlControllerPaie.getAttributAmountbyValue(bpartner.getC_BPartner_ID(), "IT", null);
							BigDecimal indemniteRepresentation = ProcessSqlControllerPaie.getAttributAmountbyValue(bpartner.getC_BPartner_ID(), "IR", null);
							BigDecimal indemniteConge = ProcessSqlControllerPaie.getAttributAmountbyValue(bpartner.getC_BPartner_ID(), "IC", null);
							BigDecimal primeOutillage = ProcessSqlControllerPaie.getAttributAmountbyValue(bpartner.getC_BPartner_ID(), "PO", null);
							BigDecimal autres = ProcessSqlControllerPaie.getAttributAmountbyValue(bpartner.getC_BPartner_ID(), "AU", null);
							Map<String, BigDecimal> variables = new HashMap<>();
						    variables.put("SB", salaireBase.getAmount()!=null?salaireBase.getAmount():BigDecimal.ZERO);
						    variables.put("IL", indemniteLogement!=null?indemniteLogement:BigDecimal.ZERO);
						    variables.put("IT", indemniteTransport!=null?indemniteTransport:BigDecimal.ZERO);
						    variables.put("IR", indemniteRepresentation!=null?indemniteRepresentation:BigDecimal.ZERO);
						    variables.put("IC", indemniteConge!=null?indemniteConge:BigDecimal.ZERO);
						    variables.put("PO", primeOutillage!=null?primeOutillage:BigDecimal.ZERO);
						    variables.put("AU", autres!=null?autres:BigDecimal.ZERO);
						    ArrayList<String> conceptValues = ProcessSqlControllerPaie.getConceptValues(null);
						    if(conceptValues!=null) {
							    for(String value : conceptValues) {
									ProcessControllerPaie.genererElementPaie(bpartner.getC_BPartner_ID(), value, variables, salaireBase.getValidFrom());
							    }
							    BigDecimal salaireBrut = ProcessSqlControllerPaie.getAttributAmountbyValue(bpartner.getC_BPartner_ID(), "SBR", null);
							    BigDecimal chargesSalariales = ProcessSqlControllerPaie.getChargesSalariales(bpartner.getC_BPartner_ID(), null);
							    if(salaireBrut!=null) {
									Integer attributNPID = ProcessSqlControllerPaie.getAttributIDbyValue(bpartner.getC_BPartner_ID(), "NP", null);
									MHRAttribute attributNP = null;
									if(attributNPID!=null) {
										attributNP = new MHRAttribute(Env.getCtx(), attributNPID, null);
									}
									BeanElmtPaie concept = ProcessSqlControllerPaie.getConceptInfobyValue("NP", null);
									if(concept!=null) {
										concept.setCbpartnerID(bpartner.getC_BPartner_ID());
										if(!ProcessSqlControllerPaie.isAttributExist(bpartner.getC_BPartner_ID(), "NP", null)) {
											attributNP = new MHRAttribute(Env.getCtx(), 0, null);
											attributNP.setHR_Concept_ID(concept.getConceptID());
											attributNP.setC_BPartner_ID(bpartner.getC_BPartner_ID());
											attributNP.setHR_Attribute_ID(DB.getNextID(Env.getCtx(), MHRAttribute.Table_Name, null));
										}
										attributNP.setQty(new BigDecimal(1));
										attributNP.setValidFrom(salaireBase.getValidFrom());
										attributNP.setLine(0);
										attributNP.setMaxValue(0);
										attributNP.setMinValue(0);
										if(salaireBrut!=null) {
											attributNP.setAmount(salaireBrut.subtract(chargesSalariales!=null?chargesSalariales:BigDecimal.ZERO));
										}
										attributNP.save();
									}
							    }
						    }
						}
					}
				}
			}
		}
	}

	public static void calculerPaie(Integer bpartnerID, Integer periodeSalarialeID) {
		String trxName =null;
		if(bpartnerID!=null && periodeSalarialeID!=null) {
			MCBPartner bpartner = new MCBPartner(Env.getCtx(), bpartnerID, null);
			MHRPeriodeSalariale periodeSalariale = new MHRPeriodeSalariale(Env.getCtx(), periodeSalarialeID, null);			
			if(bpartner!=null && periodeSalariale!=null) {
				GeneralSqlController.resetCalculPaie(bpartnerID, trxName);
				MHRElementBasePaieEmploye elementBasePaieEmploye = CalloutControllerPaie.getElementBaseCalculPaie(bpartner, periodeSalariale, trxName);
				MHRElementBasePaieEmploye dernierContrat = GeneralController.getDateDernierContrat(bpartnerID, periodeSalariale.getDate_Fin_Defaut());
				if (dernierContrat != null) {
					bpartner.setDate_Debut_Contrat_Relative(dernierContrat.getDate_Debut());
					bpartner.save();
				}
				if(elementBasePaieEmploye==null) {		
					elementBasePaieEmploye = new MHRElementBasePaieEmploye(Env.getCtx(), 0, null);
				}
				ArrayList<MHRGestionPaieEmploye> listeGestionPaieEmployes = GeneralSqlController.getAllGestionPaieEmploye(trxName);
				Map<String, BigDecimal> variables = new HashMap<>();
				if(!listeGestionPaieEmployes.isEmpty()) {
					for(MHRGestionPaieEmploye gestionPaieEmploye : listeGestionPaieEmployes) {
						ProcessControllerPaie.setCalculPaieElmtBase(bpartner, periodeSalariale, (BigDecimal)elementBasePaieEmploye.get_Value(gestionPaieEmploye.getName()), gestionPaieEmploye, variables);
					}
				}
				variables.put("IC", BigDecimal.ZERO);
				
			    ArrayList<MHRElementBasePaie> listeElementBasePaies = ProcessSqlControllerPaie.getElementBasePaieInitialValues(null);
			    if(listeElementBasePaies!=null) {
				    for(MHRElementBasePaie elementBasePaie : listeElementBasePaies) {
						ProcessControllerPaie.setCalculPaie(bpartner, periodeSalariale, elementBasePaie, null, variables);
				    }
				    MHRCalculPaie salaireBrut = ProcessSqlControllerPaie.getCalculPaiebyValue(bpartner.getC_BPartner_ID(), "SBR", null);
				    if(salaireBrut!=null) {
				    	ArrayList<MHRRetenueSalariale> listeRetenues = GeneralSqlController.getAllRetenueEmploye(bpartner.getC_BPartner_ID(), periodeSalariale, trxName);
				    	if(!listeRetenues.isEmpty()) {
					    	for(MHRRetenueSalariale retenue : listeRetenues) {
					    		ProcessControllerPaie.setCalculRetenue(retenue, periodeSalariale, trxName);
					    	}
				    	}
					    BigDecimal chargesSalariales = ProcessSqlControllerPaie.getSumChargesSalariales(bpartner.getC_BPartner_ID(), null);
						MHRElementBasePaie concept = ProcessSqlControllerPaie.getElementBasePaieFromValue("NP", null);
						if(concept!=null) {
							MHRCalculPaie salaireNet = ProcessSqlControllerPaie.getCalculPaiebyValue(bpartner.getC_BPartner_ID(), "NP", null);
							if(salaireNet==null) {
								salaireNet = new MHRCalculPaie(Env.getCtx(), 0, null);
								salaireNet.setHR_Calcul_Paie_ID(DB.getNextID(Env.getCtx(), MHRCalculPaie.Table_Name, null));
							}
							salaireNet.setHR_Element_Base_Paie_ID(concept.getHR_Element_Base_Paie_ID());
							salaireNet.setC_BPartner_ID(bpartner.getC_BPartner_ID());
							salaireNet.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
							if(!(salaireBrut==null || salaireBrut.getMontant()==null)) {
								salaireNet.setMontant(salaireBrut.getMontant().subtract(chargesSalariales!=null?chargesSalariales:BigDecimal.ZERO));
								/*
								 * if(salaireNet.getMontant()!=null &&
								 * salaireNet.getMontant().compareTo(BigDecimal.ZERO)<0) {
								 * salaireNet.setMontant(BigDecimal.ZERO); }
								 */
							}
							salaireNet.save();
						}
				    }
			    }
			}
		}
	}
	
	public static BigDecimal calculerIndemniteConge(Integer bpartnerID, Integer holidayID) {
		BigDecimal salaireCotisableTotalBrut=null;
		if(bpartnerID!=null && holidayID!=null) {
			MCBPartner bpartner = new MCBPartner(Env.getCtx(), bpartnerID, null);
			MHRHoliday holiday = new MHRHoliday(Env.getCtx(), holidayID, null);
			Map<String, BigDecimal> variables = new HashMap<>();
			if(bpartner!=null && holiday!=null) {
				salaireCotisableTotalBrut = BigDecimal.ZERO;
				BeanConge dernierConge = BeanFactory.getBeanConge();
				MHRElementBasePaieEmploye dernierContrat = GeneralController.getDateDernierContrat(holiday.getC_BPartner_ID(), holiday.getDate_Debut_Effective());
				if(dernierContrat==null || dernierContrat.getDate_Debut()==null) {
					dernierContrat = GeneralController.getDateDernierContrat(holiday.getC_BPartner_ID(), holiday.getDate_Fin_Effective());
				}
				if (dernierContrat != null) {
					bpartner.setDate_Debut_Contrat_Relative(dernierContrat.getDate_Debut());
					bpartner.save();
				}
				Timestamp dateDebutContrat = null;
				if(dernierContrat!=null) {
					dateDebutContrat = dernierContrat.getDate_Debut();
				}
				dernierConge = GeneralSqlController.getDateDernierConge(bpartnerID, holiday.getDate_Debut_Effective(), dateDebutContrat, "Annuel", dernierConge, null);
				Timestamp dateMin = null;
				if(dernierConge!=null && dernierConge.getDateFinDernierConge()!=null && dernierConge.getDateEmbauche()!=null) {
					if(dernierConge.getDateFinDernierConge().after(dernierConge.getDateEmbauche())) {
						dateMin = dernierConge.getDateFinDernierConge();
					}
					else {
						dateMin = dernierConge.getDateEmbauche();
					}
				}
				else if(dernierConge.getDateFinDernierConge()!=null) {
					dateMin = dernierConge.getDateFinDernierConge();
				}
				else if(dernierConge.getDateEmbauche()!=null) {
					dateMin = dernierConge.getDateEmbauche();
				}
				if(dateMin!=null) {
					GeneralSqlController.resetCalculConge(bpartnerID, null);
					GeneralSqlController.resetIndemniteConge(bpartnerID, null);
					dernierConge = MHREmployeeChildren.getEnfantMoins6(bpartnerID, holiday.getDate_Debut_Effective(), dernierConge, null);
					dernierConge = GeneralController.setAnciennete(dernierConge, dernierConge.getDateEmbauche(), holiday.getDate_Debut_Effective());
					MHRElementBasePaie salaireBaseConcept = ProcessSqlControllerPaie.getElementBasePaieFromValue("SB", null);
					ArrayList<MHRPeriodeSalariale> listePeriodeSalariale = GeneralSqlController.getAllPeriodeSalarialeFromPeriodeReference(
							dateMin, holiday.getDate_Debut_Effective(), null);
					ArrayList<MHRGestionPaieEmploye> listeGestionConge = GeneralSqlController.getAllGestionCongeEmploye(null);
					if(salaireBaseConcept!=null && listePeriodeSalariale!=null && !listePeriodeSalariale.isEmpty()) {
						int nbJourCongeBase = 0;
						int annee = 0;
						Set<Integer> anneesTraitees = new HashSet<>();  
						
						for(MHRPeriodeSalariale periodeSalariale : listePeriodeSalariale) {
						    annee = periodeSalariale.getDate_Debut_Defaut().toInstant()
						            .atZone(ZoneId.systemDefault())
						            .toLocalDate().getYear();
						   
						    if (!anneesTraitees.contains(annee)) {
						        nbJourCongeBase = nbJourCongeBase + GeneralController.getNombreJourCongeAnnuelBase();						       
						        anneesTraitees.add(annee);
						    }
							MHRHistoriquePaie historiquePaie = GeneralSqlController.getHistoriquePaie(bpartnerID, salaireBaseConcept.getHR_Element_Base_Paie_ID(), 
									periodeSalariale.getHR_Periode_Salariale_ID(), null);
							if(historiquePaie==null) {
								ProcessControllerPaie.calculerPaie(bpartnerID, periodeSalariale.getHR_Periode_Salariale_ID());
								historiquePaie = GeneralSqlController.getHistoriquePaie(bpartnerID, salaireBaseConcept.getHR_Element_Base_Paie_ID(), 
										periodeSalariale.getHR_Periode_Salariale_ID(), null);								
							}
							if(historiquePaie==null) {
								return null;
							}
							MHRCalculIndemniteConge calculIndemniteConge = new MHRCalculIndemniteConge(Env.getCtx(), 0, null);
							BigDecimal salaireCotisableBrut = BigDecimal.ZERO;
							if(!listeGestionConge.isEmpty()) {
								for(MHRGestionPaieEmploye gestionCongeEmploye : listeGestionConge) {
									MHRElementBasePaie elementBasePaie = ProcessSqlControllerPaie.getElementBasePaieFromValue(gestionCongeEmploye.getValue(), null);
									MHRHistoriquePaie historiqueIndemnite = GeneralSqlController.getHistoriquePaie(bpartnerID, elementBasePaie.getHR_Element_Base_Paie_ID(), periodeSalariale.getHR_Periode_Salariale_ID(), null);
									if(historiqueIndemnite!=null) {
										salaireCotisableBrut = salaireCotisableBrut.add(historiqueIndemnite.getMontant());
									}
								}
							}
							calculIndemniteConge.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
							calculIndemniteConge.setC_BPartner_ID(bpartnerID);
							calculIndemniteConge.setSalaire_Cotisable(salaireCotisableBrut);
							calculIndemniteConge.setDate_Debut(periodeSalariale.getDate_Debut_Defaut());
							calculIndemniteConge.setHR_Calcul_Indemnite_Conge_ID(DB.getNextID(Env.getCtx(), MHRCalculIndemniteConge.Table_Name, null));
							calculIndemniteConge.save();
							salaireCotisableTotalBrut = salaireCotisableTotalBrut.add(salaireCotisableBrut);
						}
						ArrayList<MHRElementBasePaieEmploye> listeBasePaie = GeneralSqlController.getElementBasePaieEmploye(bpartnerID, holiday.getDate_Debut_Effective(), holiday.getDate_Debut_Effective(), null);
						if(!listeBasePaie.isEmpty()) {
							if(listeBasePaie.get(0)!=null && listeBasePaie.get(0).getSalaire_Base()!=null) {
								ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, listeBasePaie.get(0).getSalaire_Base(), "SB", variables);
							}
							else {
								ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, BigDecimal.ZERO, "SB", variables);
							}
							ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, salaireCotisableTotalBrut, "SCPR", variables);
							ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, BigDecimal.valueOf(nbJourCongeBase), "NJCI", variables);
							if(dernierConge!=null && dernierConge.getNombreEnfantPetit()!=null && dernierConge.getGenre()!=null 
									&& dernierConge.getGenre().equalsIgnoreCase(MCBPartner.SEX_Femme)) {
								ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, BigDecimal.valueOf((int)(dernierConge.getNombreEnfantPetit()*2)), "CEMS", variables);
							}
							else {
								ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, BigDecimal.ZERO, "CEMS", variables);
							}
							if(dernierConge!=null && dernierConge.getMoisAnciennete()!=null) {
								int nbAnnee = dernierConge.getMoisAnciennete()/36;
								nbAnnee = nbAnnee*2;
								ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, BigDecimal.valueOf(nbAnnee), "CANC", variables);
							}
							else {
								ProcessControllerPaie.setCalculCongeElmtBase(bpartner, holiday, BigDecimal.ZERO, "CANC", variables);
							}
						   ArrayList<MHRElementConge> listeElementBaseConge = ProcessSqlControllerPaie.getElementBaseCongeInitialValues(null);
						    if(listeElementBaseConge!=null) {
							    for(MHRElementConge elementBaseConge : listeElementBaseConge) {
									ProcessControllerPaie.setCalculConge(bpartner, holiday, elementBaseConge, null, variables);
							    }
							    MHRCalculConge indemniteBrutConge = ProcessSqlControllerPaie.getCalculCongebyValue(bpartner.getC_BPartner_ID(), "IBC", null);
							    if(indemniteBrutConge!=null) {
							    	BigDecimal retenuesConge = ProcessSqlControllerPaie.getSumRetenueConge(bpartner.getC_BPartner_ID(), null);
									MHRElementConge concept = ProcessSqlControllerPaie.getElementBaseCongeFromValue("NP", null);
									if(concept!=null) {
										MHRCalculConge netAPayer = ProcessSqlControllerPaie.getCalculCongebyValue(bpartner.getC_BPartner_ID(), "NP", null);
										if(netAPayer==null) {
											netAPayer = new MHRCalculConge(Env.getCtx(), 0, null);
											netAPayer.setHR_Calcul_Conge_ID(DB.getNextID(Env.getCtx(), MHRCalculConge.Table_Name, null));
										}
										netAPayer.setHR_Element_Conge_ID(concept.getHR_Element_Conge_ID());
										netAPayer.setC_BPartner_ID(bpartner.getC_BPartner_ID());
										netAPayer.setHR_Holiday_ID(holiday.getHR_Holiday_ID());
										if(!(indemniteBrutConge==null || indemniteBrutConge.getMontant()==null)) {
											netAPayer.setMontant(indemniteBrutConge.getMontant().subtract(retenuesConge!=null?retenuesConge:BigDecimal.ZERO));
											/*
											 * if(netAPayer.getMontant()!=null &&
											 * netAPayer.getMontant().compareTo(BigDecimal.ZERO)<0) {
											 * netAPayer.setMontant(BigDecimal.ZERO); }
											 */
										}
										netAPayer.save();
									}
							    }						    
						    }
						}						
					}
				}				
			}
		}
		return salaireCotisableTotalBrut;
	}	
	
	private static void genererElementPaie(Integer cbpartnerID, String value, Map<String, BigDecimal> variables, Timestamp validFrom) {
		if(cbpartnerID!=null && value!=null && validFrom!=null) {
			Integer attributID = ProcessSqlControllerPaie.getAttributIDbyValue(cbpartnerID, value, null);
			MHRAttribute attribut = null;
			if(attributID!=null) {
				attribut = new MHRAttribute(Env.getCtx(), attributID, null);
			}
			MCBPartner bpartner = new MCBPartner(Env.getCtx(), cbpartnerID, null);
			BeanElmtPaie concept = ProcessSqlControllerPaie.getConceptInfobyValue(value, null);
			if(concept!=null && bpartner!=null) {
				concept.setCbpartnerID(bpartner.getC_BPartner_ID());
				if(!ProcessSqlControllerPaie.isAttributExist(cbpartnerID, value, null)) {
					attribut = new MHRAttribute(Env.getCtx(), 0, null);
					attribut.setHR_Concept_ID(concept.getConceptID());
					attribut.setC_BPartner_ID(cbpartnerID);
					attribut.setHR_Attribute_ID(DB.getNextID(Env.getCtx(), MHRAttribute.Table_Name, null));
				}
				attribut.setQty(new BigDecimal(1));
				attribut.setValidFrom(validFrom);
				attribut.setLine(0);
				attribut.setMaxValue(0);
				attribut.setMinValue(0);
				if(variables!=null) {
					attribut.setAmount(ProcessControllerPaie.getAmountFromConcept(concept, variables));
				}
				attribut.save();
			}
		}
	}	
	
	private static BigDecimal getAmountFromConcept(BeanElmtPaie conceptInfo, Map<String, BigDecimal> variables) {
		BigDecimal resultat =null;
		if(conceptInfo!=null && variables!=null) {
			if(!(conceptInfo.getFormule() == null || conceptInfo.getFormule().trim().isEmpty())) {
				try {
					resultat = evaluerFormule(conceptInfo.getFormule(), variables);
					variables.put(conceptInfo.getValue(), resultat);
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(conceptInfo.getBaseCalculID()!=null) {
				MHRConcept conceptBase = new MHRConcept(Env.getCtx(), conceptInfo.getBaseCalculID(), null);
				if(conceptBase!=null) {
					BigDecimal conceptBaseAmount = ProcessSqlControllerPaie.getAttributAmountbyValue(conceptInfo.getCbpartnerID(), conceptBase.getValue(), null);
					if(conceptBaseAmount!=null) {
						boolean tauxZero = conceptInfo.getTaux()==null || conceptInfo.getTaux()==BigDecimal.ZERO;
						if(!tauxZero) {
							resultat = conceptBaseAmount.multiply(conceptInfo.getTaux());
							variables.put(conceptInfo.getValue(), resultat);
						}
						else{
							BeanBareme beanBareme = ProcessSqlControllerPaie.getBIABareme(conceptInfo.getConceptID(), conceptBaseAmount, null);
							if(beanBareme!=null) {
								if(beanBareme.getFormuleBareme()!=null) {								
									try {
										resultat = evaluerFormule(beanBareme.getFormuleBareme(), variables);
										variables.put(conceptInfo.getValue(), resultat);
									} catch (ScriptException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}else if(beanBareme.getAmountBareme()!=null) {
									resultat = beanBareme.getAmountBareme();
									variables.put(conceptInfo.getValue(), resultat);
								}
							}
						}
					}
				}
			}
		}
		return resultat;
	}

	private static BigDecimal getAmountFromElementPaie(Integer bpartnerID, MHRElementBasePaie elementBasePaie, Map<String, BigDecimal> variables) {
		BigDecimal resultat =null;
		if(elementBasePaie!=null && variables!=null) {
			MHRTypeDeCalcul typeDeCalcul = new MHRTypeDeCalcul(Env.getCtx(), elementBasePaie.getHR_Type_Calcul_ID(), null);
			if(typeDeCalcul!=null) {
				if(typeDeCalcul.getName().equalsIgnoreCase("Formule")) {
					try {
						resultat = evaluerFormule(elementBasePaie.getFormule(), variables);
						variables.put(elementBasePaie.getValue(), resultat);
					} catch (ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					MHRElementBasePaie elementBaseCalcul = new MHRElementBasePaie(Env.getCtx(), elementBasePaie.getBase_Calcul_ID(), null);
					MHRCalculPaie calculBasePaie = ProcessSqlControllerPaie.getCalculPaiebyValue(bpartnerID, elementBaseCalcul.getValue(), null);
					BigDecimal montant = BigDecimal.ZERO;
					if(calculBasePaie!=null && calculBasePaie.getMontant()!=null) {
						montant = calculBasePaie.getMontant();
					}
					if(typeDeCalcul.getName().equalsIgnoreCase("Pourcentage")) {
						resultat = ((BigDecimal) montant).multiply(elementBasePaie.getPourcentage());
						variables.put(elementBasePaie.getValue(), resultat);
					}
					else {
						MHRBareme bareme = ProcessSqlControllerPaie.getBareme(elementBasePaie.getHR_Element_Base_Paie_ID(), montant, null);
						if(bareme!=null) {
							if(typeDeCalcul.getName().equalsIgnoreCase("Barème")) {
								resultat = bareme.getMontant();
								variables.put(elementBasePaie.getValue(), resultat);
							}
							else if(typeDeCalcul.getName().equalsIgnoreCase("Formule-Barème")) {
								if(bareme.getFormule()!=null) {
									try {
										resultat = evaluerFormule(bareme.getFormule(), variables);
										variables.put(elementBasePaie.getValue(), resultat);
									} catch (ScriptException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		return resultat;
	}

	private static BigDecimal getAmountFromElementConge(Integer bpartnerID, MHRElementConge elementConge, Map<String, BigDecimal> variables) {
		BigDecimal resultat =null;
		if(elementConge!=null && variables!=null) {
			MHRTypeDeCalcul typeDeCalcul = new MHRTypeDeCalcul(Env.getCtx(), elementConge.getHR_Type_Calcul_ID(), null);
			if(typeDeCalcul!=null) {
				if(typeDeCalcul.getName().equalsIgnoreCase("Formule")) {
					try {
						resultat = evaluerFormule(elementConge.getFormule(), variables);
						variables.put(elementConge.getValue(), resultat);
					} catch (ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					MHRElementConge elementBaseCalculConge = new MHRElementConge(Env.getCtx(), elementConge.getBase_Calcul_ID(), null);
					MHRCalculConge calculBaseConge = ProcessSqlControllerPaie.getCalculCongebyValue(bpartnerID, elementBaseCalculConge.getValue(), null);
					BigDecimal montant = BigDecimal.ZERO;
					if(calculBaseConge!=null && calculBaseConge.getMontant()!=null) {
						montant = calculBaseConge.getMontant();
					}
					if(typeDeCalcul.getName().equalsIgnoreCase("Pourcentage")) {
						resultat = ((BigDecimal) montant).multiply(elementConge.getPourcentage());
						variables.put(elementConge.getValue(), resultat);
					}
					else {
						MHRBaremeConge bareme = ProcessSqlControllerPaie.getBaremeConge(elementConge.getHR_Element_Conge_ID(), montant, null);
						if(bareme!=null) {
							if(typeDeCalcul.getName().equalsIgnoreCase("Barème")) {
								resultat = bareme.getMontant();
								variables.put(elementConge.getValue(), resultat);
							}
							else if(typeDeCalcul.getName().equalsIgnoreCase("Formule-Barème")) {
								if(bareme.getFormule()!=null) {
									try {
										resultat = evaluerFormule(bareme.getFormule(), variables);
										variables.put(elementConge.getValue(), resultat);
									} catch (ScriptException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		return resultat;
	}
	
	public static BigDecimal evaluerFormule(String formule, Map<String, BigDecimal> variables) throws ScriptException {
	        // Créer un moteur de script JavaScript
		Context context = Context.enter();
        Scriptable scope = context.initStandardObjects();
        for (Map.Entry<String, BigDecimal> entry : variables.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                scope.put(entry.getKey(), scope, entry.getValue());
            }
        }
        Object result = context.evaluateString(scope, formule, "<cmd>", 1, null);
	    return new BigDecimal(Context.toString(result));
	}
	
	public static void setCalculPaieElmtBase(MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, BigDecimal montant, MHRGestionPaieEmploye gestionPaieEmploye, Map<String, BigDecimal> variables) {
		if(bpartner!=null && periodeSalariale!=null && gestionPaieEmploye!=null && variables!=null) {
			MHRElementBasePaie elementBasePaie = ProcessSqlControllerPaie.getElementBasePaieFromValue(gestionPaieEmploye.getValue(), null);
			if(elementBasePaie!=null) {
				MHRCalculPaie calculPaie = ProcessSqlControllerPaie.getCalculPaiebyValue(bpartner.getC_BPartner_ID(), gestionPaieEmploye.getValue(), null);			
				if(calculPaie==null) {
				   calculPaie = new MHRCalculPaie(Env.getCtx(), 0, null);
				   calculPaie.setHR_Element_Base_Paie_ID(elementBasePaie.getHR_Element_Base_Paie_ID());
				   calculPaie.setC_BPartner_ID(bpartner.getC_BPartner_ID());
				   calculPaie.setHR_Calcul_Paie_ID(DB.getNextID(Env.getCtx(), MHRCalculPaie.Table_Name, null));
				}
				calculPaie.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
				if(montant==null) {
					montant =ProcessControllerPaie.getAmountFromElementPaie(bpartner.getC_BPartner_ID(), elementBasePaie, variables);
				}
				calculPaie.setMontant(montant!=null?montant:BigDecimal.ZERO);
				if(gestionPaieEmploye.isPrinted()){
					calculPaie.save();
				}
				else {
					if(calculPaie.getMontant()!=null && calculPaie.getMontant().compareTo(BigDecimal.ZERO) != 0) {
						calculPaie.save();
					}
					else {
						calculPaie.delete(true);
					}
				}
				variables.put(gestionPaieEmploye.getValue(), montant!=null?montant:BigDecimal.ZERO);
			}
		}
	}
	

	public static void setCalculPaie(MCBPartner bpartner, MHRPeriodeSalariale periodeSalariale, MHRElementBasePaie elementBasePaie, BigDecimal montant, Map<String, BigDecimal> variables) {
		if(bpartner!=null && periodeSalariale!=null && elementBasePaie!=null && variables!=null) {
			if(elementBasePaie!=null) {
				MHRCalculPaie calculPaie = ProcessSqlControllerPaie.getCalculPaiebyValue(bpartner.getC_BPartner_ID(), elementBasePaie.getValue(), null);			
				if(calculPaie==null) {
				   calculPaie = new MHRCalculPaie(Env.getCtx(), 0, null);
				   calculPaie.setHR_Element_Base_Paie_ID(elementBasePaie.getHR_Element_Base_Paie_ID());
				   calculPaie.setC_BPartner_ID(bpartner.getC_BPartner_ID());
				   calculPaie.setHR_Calcul_Paie_ID(DB.getNextID(Env.getCtx(), MHRCalculPaie.Table_Name, null));
				}
				calculPaie.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
				if(montant==null) {
					montant =ProcessControllerPaie.getAmountFromElementPaie(bpartner.getC_BPartner_ID(), elementBasePaie, variables);
				}
				calculPaie.setMontant(montant!=null?montant:BigDecimal.ZERO);
				calculPaie.save();
				variables.put(elementBasePaie.getValue(), montant!=null?montant:BigDecimal.ZERO);
			}
		}
	}

	public static void setCalculRetenue(MHRRetenueSalariale retenueSalariale, MHRPeriodeSalariale periodeSalariale, String trxName) {
		if(retenueSalariale!=null && periodeSalariale!=null) {
			MHRElementBasePaie elementBasePaie = new MHRElementBasePaie(Env.getCtx(), retenueSalariale.getHR_Element_Base_Paie_ID(), trxName);
			if(elementBasePaie!=null) {
				MHRCalculPaie calculPaie = ProcessSqlControllerPaie.getCalculPaiebyValue(retenueSalariale.getC_BPartner_ID(), elementBasePaie.getValue(), null);			
				if(calculPaie==null) {
				   calculPaie = new MHRCalculPaie(Env.getCtx(), 0, null);
				   calculPaie.setHR_Element_Base_Paie_ID(elementBasePaie.getHR_Element_Base_Paie_ID());
				   calculPaie.setC_BPartner_ID(retenueSalariale.getC_BPartner_ID());
				   calculPaie.setHR_Calcul_Paie_ID(DB.getNextID(Env.getCtx(), MHRCalculPaie.Table_Name, null));
				}
				calculPaie.setHR_Periode_Salariale_ID(periodeSalariale.getHR_Periode_Salariale_ID());
				if(retenueSalariale.getFin_Prelevement_ID()==periodeSalariale.getHR_Periode_Salariale_ID()) {
					calculPaie.setMontant(retenueSalariale.getMontant_Derniere_Mensualite()!=null?retenueSalariale.getMontant_Derniere_Mensualite():BigDecimal.ZERO);
				}
				else {
					calculPaie.setMontant(retenueSalariale.getMontant_Mensualite()!=null ? retenueSalariale.getMontant_Mensualite() : BigDecimal.ZERO);
				}
				calculPaie.save();
			}
		}
	}

	public static void setCalculCongeElmtBase(MCBPartner bpartner, MHRHoliday holiday, BigDecimal montant, String value, Map<String, BigDecimal> variables) {
		if(bpartner!=null && holiday!=null && value!=null && variables!=null) {
			MHRElementConge elementConge = ProcessSqlControllerPaie.getElementBaseCongeFromValue(value, null);
			if(elementConge!=null) {
				MHRCalculConge calculConge = ProcessSqlControllerPaie.getCalculCongebyValue(bpartner.getC_BPartner_ID(), value, null);			
				if(calculConge==null) {
				   calculConge = new MHRCalculConge(Env.getCtx(), 0, null);
				   calculConge.setHR_Element_Conge_ID(elementConge.getHR_Element_Conge_ID());
				   calculConge.setC_BPartner_ID(bpartner.getC_BPartner_ID());
				   calculConge.setHR_Calcul_Conge_ID(DB.getNextID(Env.getCtx(), MHRCalculConge.Table_Name, null));
				}
				calculConge.setHR_Holiday_ID(holiday.getHR_Holiday_ID());
				if(montant==null) {
					montant =ProcessControllerPaie.getAmountFromElementConge(bpartner.getC_BPartner_ID(), elementConge, variables);
				}
				calculConge.setMontant(montant!=null?montant:BigDecimal.ZERO);
				calculConge.save();
				variables.put(value, montant!=null?montant:BigDecimal.ZERO);
			}
		}
	}

	public static void setCalculConge(MCBPartner bpartner, MHRHoliday holiday, MHRElementConge elementConge, BigDecimal montant, Map<String, BigDecimal> variables) {
		if(bpartner!=null && holiday!=null && elementConge!=null && variables!=null) {
			MHRCalculConge calculConge = ProcessSqlControllerPaie.getCalculCongebyValue(bpartner.getC_BPartner_ID(), elementConge.getValue(), null);			
			if(calculConge==null) {
			   calculConge = new MHRCalculConge(Env.getCtx(), 0, null);
			   calculConge.setHR_Element_Conge_ID(elementConge.getHR_Element_Conge_ID());
			   calculConge.setC_BPartner_ID(bpartner.getC_BPartner_ID());
			   calculConge.setHR_Calcul_Conge_ID(DB.getNextID(Env.getCtx(), MHRCalculConge.Table_Name, null));
			}
			calculConge.setHR_Holiday_ID(holiday.getHR_Holiday_ID());
			if(montant==null) {
				montant =ProcessControllerPaie.getAmountFromElementConge(bpartner.getC_BPartner_ID(), elementConge, variables);
			}
			calculConge.setMontant(montant!=null?montant:BigDecimal.ZERO);
			calculConge.save();
			variables.put(elementConge.getValue(), montant!=null?montant:BigDecimal.ZERO);
		}
	}	
	
}
