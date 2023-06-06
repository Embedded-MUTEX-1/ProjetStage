package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.Question;
import fr.greta92.projetstage.entity.Reponse;
import fr.greta92.projetstage.entity.Test;
import fr.greta92.projetstage.exception.TestDejaExistantException;
import fr.greta92.projetstage.exception.TestNonExistantException;
import fr.greta92.projetstage.repository.QuestionRepo;
import fr.greta92.projetstage.repository.ReponseRepo;
import fr.greta92.projetstage.repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionTestImpl implements GestionTest {

    @Autowired
    private TestRepo testRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ReponseRepo reponseRepo;

    @Override
    public Test getTest(String name) throws TestNonExistantException {
        Test test = testRepo.findByNom(name).orElse(null);
        if(test == null) { throw new TestNonExistantException(); }
        return test;
    }

    @Override
    public List<Test> getAllTest() {
        return testRepo.findAll();
    }

    @Override
    public void ajouterTest(Test test) throws TestDejaExistantException {
        if(testRepo.existsByNom(test.getNom()))
            throw new TestDejaExistantException();
        test.setId(Long.valueOf(0));
        testRepo.save(test);
    }

    @Override
    public void modifierTest(Test test) throws TestNonExistantException {
        if(!testRepo.existsById(test.getId()))
            throw new TestNonExistantException();
        testRepo.save(test);
    }

    @Override
    public void supprimerTest(Test test) {

    }

    @Override
    public void ajouterQuestion(Question question) {
        questionRepo.save(question);
    }

    @Override
    public void modifierQuestion(Question question) {
        questionRepo.save(question);
    }

    @Override
    public void supprimerQuestion(Question question) {

    }

    @Override
    public void ajouterReponse(Reponse reponse) {
        reponseRepo.save(reponse);
    }

    @Override
    public void modifierReponse(Reponse reponse) {
        reponseRepo.save(reponse);
    }

    @Override
    public void supprimerReponse(Reponse reponse) {

    }
}
