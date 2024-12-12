package org.sitracel.recrutement.modelvalidator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanCandidatEvaluation;
import org.sitracel.bean.BeanEvaluationCompetence;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.model.MHROffreEmploi;
import org.sitracel.recrutement.model.MHROffreTestEvaluation;
import org.sitracel.recrutement.model.MHRSessionRecrutement;

public class ModelValidatorControllerRecrutement {
	private static CLogger log = CLogger.getCLogger (PO.class);
	public static void CreationOffreEmploi(MHROffreEmploi offreEmploi) {
		if(offreEmploi!=null) {
			offreEmploi.setDate_Creation(new Timestamp(System.currentTimeMillis()));
		}
	}
	
	public static void CreationSessionRecrutement(MHRSessionRecrutement sessionRecrutement) {
		if(sessionRecrutement!=null) {
			sessionRecrutement.setDate_Creation(new Timestamp(System.currentTimeMillis()));
		}
	}
	
	public static void CreationTestEvaluation(MHROffreTestEvaluation testEvaluation) {
		if(testEvaluation!=null) {
			testEvaluation.setDate_Creation(new Timestamp(System.currentTimeMillis()));
		}
	}
	
	public static void creationCandidature(MHRCandidature candidature) {
		if(candidature!=null) {
			candidature.setDate_Creation(new Timestamp(System.currentTimeMillis()));
			try {
				DB.commit(true, candidature.get_TrxName());
				MHRSessionRecrutement sessionRecrutement = new MHRSessionRecrutement(Env.getCtx(), candidature.getHR_SessionRecrutement_ID(), null);
				if(sessionRecrutement!=null) {
					MHROffreTestEvaluation testEvaluation = new MHROffreTestEvaluation(Env.getCtx(), sessionRecrutement.getHR_OffreTestEvaluation_ID(), null);
					if(testEvaluation!=null) {
						ArrayList<BeanEvaluationCompetence> listeCompetence = ModelValidatorSqlControllerRecrutement.getCompetenceFromTestEvaluation(testEvaluation.getHR_OffreTestEvaluation_ID(), null);
						if(listeCompetence!=null) {
							BigDecimal scoreMax = BigDecimal.valueOf(0);
							for(BeanEvaluationCompetence competence:listeCompetence) {
								MHRCandidatEvaluation evaluationCandidat = new MHRCandidatEvaluation(Env.getCtx(), null, null);
								evaluationCandidat.setHR_Candidature_ID(candidature.getHR_Candidature_ID());
								evaluationCandidat.setHR_Competences_ID(competence.getCompetenceID());
								evaluationCandidat.setScoreMax(competence.getScoreMax());
								evaluationCandidat.setPonderation(competence.getPonderation());
								evaluationCandidat.setIsCompetenceEvalue(false);
								evaluationCandidat.save();
								scoreMax = scoreMax.add(competence.getScoreMax().multiply(BigDecimal.valueOf(competence.getPonderation())));

								DB.commit(true, evaluationCandidat.get_TrxName());
							}
							candidature.setScoreTotalMax(scoreMax);
							candidature.save();
							DB.commit(true, candidature.get_TrxName());
						}
					}
				}
			} catch (IllegalStateException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	public static void suppressionCandidature(MHRCandidature candidature) {
		if(candidature!=null) {
			ArrayList<Integer> listeCompetenceID = ModelValidatorSqlControllerRecrutement.getListeCompetenceFromCandidatureID(candidature.getHR_Candidature_ID(), null);
			for(Integer competenceID:listeCompetenceID) {
				if(competenceID!=null) {
					MHRCandidatEvaluation candidatEvaluation = new MHRCandidatEvaluation(Env.getCtx(), competenceID, null);
					if(candidatEvaluation!=null) {
						candidatEvaluation.delete(true);
					}
				}
			}
		}
	}
	
	public static void actualiserCandidature(Integer hr_sessionRecrutementID, MHRCandidatEvaluation candidatEvaluation) {
		if(hr_sessionRecrutementID!=null) {
			ArrayList<BeanCandidatEvaluation> listeCandidatures = ModelValidatorControllerRecrutement.calculerRangCandidatures(hr_sessionRecrutementID, candidatEvaluation);
			if(listeCandidatures!=null) {
				try {
					for(BeanCandidatEvaluation candidature:listeCandidatures) {
						MHRCandidature candidature0 = new MHRCandidature(Env.getCtx(), candidature.getCandidatureID(), null);
						candidature0.setRangCandidat(candidature.getRang());
						candidature0.setScoreTotal(candidature.getScoreTotal());
						candidature0.save();
						DB.commit(true, candidature0.get_TrxName());
						
					}
				} catch (IllegalStateException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ArrayList<BeanCandidatEvaluation> calculerRangCandidatures(Integer hr_sessionRecrutementID, MHRCandidatEvaluation candidatEvaluation) {
		ArrayList<BeanCandidatEvaluation> listeCandidatures = null;
		if(hr_sessionRecrutementID!=null) {
			listeCandidatures = ModelValidatorSqlControllerRecrutement.getListeCandidatureFromSessionRecrutement(hr_sessionRecrutementID, null);
			for(BeanCandidatEvaluation candidature:listeCandidatures) {
				ModelValidatorControllerRecrutement.caluculerScoreCandidature(candidature, candidatEvaluation);				
			}
			Collections.sort(listeCandidatures);
			int rang = 1;
			for(BeanCandidatEvaluation candidature:listeCandidatures) {
				candidature.setRang(rang);
				if((listeCandidatures.indexOf(candidature)+1)<listeCandidatures.size()) {
					if(candidature.getScoreTotal().compareTo(listeCandidatures.get(listeCandidatures.indexOf(candidature)+1).getScoreTotal())!=0) {
						rang++;
					}
				}
			}
		}
		return listeCandidatures;
	}
	
	public static BeanCandidatEvaluation caluculerScoreCandidature(BeanCandidatEvaluation beanCandidatEvaluation, MHRCandidatEvaluation candidatEvaluation) {
		if(beanCandidatEvaluation!=null) {
			if(beanCandidatEvaluation.getCandidatureID()>0) {
				ArrayList<BeanEvaluationCompetence> competences = ModelValidatorSqlControllerRecrutement.getScoresCandidature(beanCandidatEvaluation.getCandidatureID(), null);
				beanCandidatEvaluation.setScoreTotal(BigDecimal.valueOf(0));
				for(BeanEvaluationCompetence competence:competences) {
					if(competence!=null) {
						if(competence.getScore()!=null) {
							if(candidatEvaluation!=null) {
								if(competence.getCandidatEvaluationID()==candidatEvaluation.getHR_CandidatEvaluation_ID()) {
									beanCandidatEvaluation.setScoreTotal(beanCandidatEvaluation.getScoreTotal().add((candidatEvaluation.getScore().multiply(BigDecimal.valueOf(candidatEvaluation.getPonderation())))));
								}
								else {
									beanCandidatEvaluation.setScoreTotal(beanCandidatEvaluation.getScoreTotal().add((competence.getScore().multiply(BigDecimal.valueOf(competence.getPonderation())))));
								}	
							}
							else {
								beanCandidatEvaluation.setScoreTotal(beanCandidatEvaluation.getScoreTotal().add((competence.getScore().multiply(BigDecimal.valueOf(competence.getPonderation())))));
							}
						}
					}
				}
				if(!GeneralSqlController.idExists(MHRCandidatEvaluation.COLUMNNAME_HR_CandidatEvaluation_ID, MHRCandidatEvaluation.Table_Name, candidatEvaluation.getHR_CandidatEvaluation_ID(), null)) {
					beanCandidatEvaluation.setScoreTotal(beanCandidatEvaluation.getScoreTotal().add((candidatEvaluation.getScore().multiply(BigDecimal.valueOf(candidatEvaluation.getPonderation())))));
				}
			}
		}
		return beanCandidatEvaluation;
	}
}
