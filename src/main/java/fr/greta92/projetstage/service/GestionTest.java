package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.Question;
import fr.greta92.projetstage.entity.Reponse;
import fr.greta92.projetstage.entity.Test;
import fr.greta92.projetstage.exception.TestDejaExistantException;
import fr.greta92.projetstage.exception.TestNonExistantException;

import java.util.List;

public interface GestionTest {
    public Test getTest(String name) throws TestNonExistantException;
    public List<Test> getAllTest();
    public void ajouterTest(Test test) throws TestDejaExistantException;
    public void modifierTest(Test test) throws TestNonExistantException;
    public void supprimerTest(Test test);
    public void ajouterQuestion(Question question);
    public void modifierQuestion(Question question);
    public void supprimerQuestion(Question question);
    public void ajouterReponse(Reponse reponse);
    public void modifierReponse(Reponse reponse);
    public void supprimerReponse(Reponse reponse);
}
