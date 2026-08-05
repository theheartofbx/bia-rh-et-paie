package org.sitracel.recrutement.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.recrutement.process.candidature.SitracelProcessRejeterCandidature;
import org.sitracel.recrutement.process.candidature.SitracelProcessValiderCandidature;
import org.sitracel.recrutement.process.offreemploi.SitracelProcessRejeterOffreEmploi;
import org.sitracel.recrutement.process.offreemploi.SitracelProcessValiderOffreEmploi;
import org.sitracel.recrutement.process.testevaluation.SitracelProcessValiderTestEvaluation;
import org.sitracel.recrutement.sessionrecrutement.SitracelProcessRejeterSessionRecrutement;
import org.sitracel.recrutement.sessionrecrutement.SitracelProcessValiderSessionRecrutement;

/**
 * Factory des processus Recrutement.
 * Corrigé Session 18 : ajout Valider/Rejeter Candidature.
 */
public class SitracelRecrutementProcessFactory implements IProcessFactory {

    @Override
    public ProcessCall newProcessInstance(String className) {
        if (className.equals(SitracelProcessValiderOffreEmploi.class.getName()))
            return new SitracelProcessValiderOffreEmploi();
        if (className.equals(SitracelProcessRejeterOffreEmploi.class.getName()))
            return new SitracelProcessRejeterOffreEmploi();
        if (className.equals(SitracelProcessValiderSessionRecrutement.class.getName()))
            return new SitracelProcessValiderSessionRecrutement();
        if (className.equals(SitracelProcessRejeterSessionRecrutement.class.getName()))
            return new SitracelProcessRejeterSessionRecrutement();
        if (className.equals(SitracelProcessValiderTestEvaluation.class.getName()))
            return new SitracelProcessValiderTestEvaluation();
        if (className.equals(SitracelProcessValiderCandidature.class.getName()))
            return new SitracelProcessValiderCandidature();
        if (className.equals(SitracelProcessRejeterCandidature.class.getName()))
            return new SitracelProcessRejeterCandidature();
        return null;
    }
}
