package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.PassageTest;
import fr.greta92.projetstage.entity.Test;
import fr.greta92.projetstage.entity.TestData;

public interface GestionPassageTest {
    public void ajouterPassageTest(PassageTest passageTest);
    public void modifierPassageTest(PassageTest passageTest);
    public void supprimerTest(PassageTest passageTest);
    public void lancerPassageTest(PassageTest passageTest);
    public void verifierPassageTest(TestData testData);
    public void annulerPassageTest(PassageTest passageTest);
}
