package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.Question;
import fr.greta92.projetstage.entity.Reponse;
import fr.greta92.projetstage.entity.Test;

public interface GestionTest {
    public void getTest(String name);
    public void ajouterTest(Test test);
    public void modifierTest(Test test);
    public void supprimerTest(Test test);
    public void ajouterQuestion(Question question);
    public void modifierQuestion(Question question);
    public void supprimerQuestion(Question question);
    public void ajouterReponse(Reponse reponse);
    public void modifierReponse(Reponse reponse);
    public void supprimerReponse(Reponse reponse);
}
