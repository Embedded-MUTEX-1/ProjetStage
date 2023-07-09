package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.*;
import fr.greta92.projetstage.exception.PassageTestDejaExistantException;
import fr.greta92.projetstage.exception.PassageTestNonExistantException;
import fr.greta92.projetstage.exception.PassageTestTooEarlyException;
import fr.greta92.projetstage.exception.PassageTestTooLateException;
import fr.greta92.projetstage.repository.PassageTestRepo;
import fr.greta92.projetstage.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GestionPassageTestImpl implements GestionPassageTest{

    @Autowired
    private PassageTestRepo passageTestRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Override
    public PassageTest getPassageTest(Long id) {
        return passageTestRepo.findById(id).orElseThrow();
    }

    @Override
    public List<PassageTest> getAllPassageTest(Status status) {
        return passageTestRepo.getPassageTestByStatus(status);
    }

    @Override
    public List<PassageTest> getAllPassageTestFromCandidat(Candidat candidat) {
        return passageTestRepo.findPassageTestByCandidat(candidat);
    }

    @Override
    public List<PassageTest> getAllPassageTestFromCandidat(Candidat candidat, Status status) {
        return passageTestRepo.findPassageTestByCandidatAndStatus(candidat, status);
    }

    @Override
    public List<PassageTest> getAllPassageTest() {
        return passageTestRepo.findAll();
    }

    @Override
    public void ajouterPassageTest(PassageTest passageTest) throws PassageTestDejaExistantException {
        if(passageTestRepo.existsById(passageTest.getId()))
        {
            throw new PassageTestDejaExistantException();
        }
        passageTestRepo.save(passageTest);
    }

    @Override
    public void modifierPassageTest(PassageTest passageTest) throws PassageTestNonExistantException {
        if(!passageTestRepo.existsById(passageTest.getId()))
        {
            throw new PassageTestNonExistantException();
        }
        passageTestRepo.save(passageTest);
    }

    @Override
    public void supprimerTest(PassageTest passageTest) {

    }

    @Override
    public void lancerPassageTest(Long testId) throws PassageTestTooEarlyException, PassageTestTooLateException {
        PassageTest passageTest = passageTestRepo.findById(testId).orElseThrow();

        LocalDateTime debut = passageTest.getDebut();
        LocalDateTime fin = passageTest.getFin();
        LocalDateTime now = LocalDateTime.now();

        if(now.isBefore(debut))
        {
            throw new PassageTestTooEarlyException();
        }
        else if(now.isAfter(fin))
        {
            passageTest.setStatus(Status.ANNULER);
            passageTestRepo.save(passageTest);
            throw new PassageTestTooLateException();
        }

        passageTest.setStatus(Status.EN_COURS);
        passageTest.setDatePassage(now);
        passageTestRepo.save(passageTest);
    }

    @Override
    @Transactional
    public void verifierPassageTest(TestResponse testResponse) throws PassageTestTooLateException {
        Map<Long, ReponseCandidat> reponseCandidatsIn = testResponse.getReponseCandidats();
        List<ReponseCandidat> reponseCandidatsOut = new ArrayList<>();
        PassageTest passageTest = passageTestRepo.findById(testResponse.getPassageTestId()).orElseThrow();


        LocalDateTime fin = passageTest.getFin();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(passageTest.getDatePassage(), now);
        Duration timeout = passageTest.getTimeout();

        if(now.isAfter(fin) || duration.compareTo(timeout) >= 1)
        {
            passageTest.setStatus(Status.ANNULER);
            passageTestRepo.save(passageTest);
            throw new PassageTestTooLateException();
        }

        for (Long responseId: testResponse.getReponseCandidats().keySet()) {
            Question question = questionRepo.getById(responseId);
            ReponseCandidat reponseCandidat = reponseCandidatsIn.get(responseId);


            reponseCandidat.setQuestion(question);
            reponseCandidatsOut.add(reponseCandidat);
        }

        passageTest.setReponseCandidats(reponseCandidatsOut);
        passageTest.setStatus(Status.DONE);
        passageTest.setDuration(duration);

        passageTestRepo.save(passageTest);
    }

    @Override
    public void annulerPassageTest(PassageTest passageTest) {

    }
}
 //Traitement et verification...