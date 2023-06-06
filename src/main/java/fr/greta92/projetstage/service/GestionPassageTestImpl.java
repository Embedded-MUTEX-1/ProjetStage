package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.PassageTest;
import fr.greta92.projetstage.entity.Status;
import fr.greta92.projetstage.entity.TestData;
import fr.greta92.projetstage.exception.PassageTestDejaExistantException;
import fr.greta92.projetstage.exception.PassageTestNonExistantException;
import fr.greta92.projetstage.exception.PassageTestTooEarlyException;
import fr.greta92.projetstage.exception.PassageTestTooLateException;
import fr.greta92.projetstage.repository.PassageTestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class GestionPassageTestImpl implements GestionPassageTest{

    @Autowired
    private PassageTestRepo passageTestRepo;
    @Override
    public PassageTest getPassageTest(Long id) {
        return passageTestRepo.getById(id);
    }

    @Override
    public List<PassageTest> getAllPassageTest(Status status) {
        return passageTestRepo.getPassageTestByStatus(status);
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
        PassageTest passageTest = passageTestRepo.getById(testId);

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
    public void verifierPassageTest(TestData testData) {

    }

    @Override
    public void annulerPassageTest(PassageTest passageTest) {

    }
}
