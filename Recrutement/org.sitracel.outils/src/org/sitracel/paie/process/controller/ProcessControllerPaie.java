package org.sitracel.paie.process.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptException;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.sitracel.bean.BeanBareme;
import org.sitracel.bean.BeanElmtPaie;
import org.sitracel.bean.BeanJourNonTravaille;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.model.MHRAttribute;
import org.sitracel.paie.model.MHRConcept;

public class ProcessControllerPaie {
	private static CLogger log = CLogger.getCLogger (PO.class);
	public static void mAJJNonPaie(Integer RecordID) {
		if(RecordID!=null) {
			MHRAttribute attribut = new MHRAttribute(Env.getCtx(),RecordID,null);
			if(attribut!=null) {
				BeanJourNonTravaille jourNT = ProcessSqlControllerPaie.getNonJourPaie(attribut.getC_BPartner_ID(), attribut.getValidFrom(), attribut.getValidTo(), null);
				if(jourNT!=null) {
					if(jourNT.getNombreJourConge()!=null) {
						attribut.setNombre_Jour_Conge(jourNT.getNombreJourConge());
					}
					else {
						attribut.setNombre_Jour_Conge(0);
					}
					if(jourNT.getNombreJourSuspendu()!=null) {
						attribut.setNombre_Jour_Suspension(jourNT.getNombreJourSuspendu());
					}
					else {
						attribut.setNombre_Jour_Suspension(0);
					}
					attribut.save();
				}
			}			
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
							BeanBareme beanBareme = ProcessSqlControllerPaie.getBareme(conceptInfo.getConceptID(), conceptBaseAmount, null);
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
	
	public static BigDecimal evaluerFormule(String formule, Map<String, BigDecimal> variables) throws ScriptException {
	        // Créer un moteur de script JavaScript
		log.warning("\nFORMULE : "+formule);
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
}
