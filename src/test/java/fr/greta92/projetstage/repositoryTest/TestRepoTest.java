package fr.greta92.projetstage.repositoryTest;

import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.repository.TestRepo;
import fr.greta92.projetstage.repository.UtilisateurRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.NoSuchElementException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepoTest {
    @Autowired
    private TestRepo testRepo;
    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void returnTestTest()
    {
        String nameExpected = "test_aptitude";
        fr.greta92.projetstage.entity.Test test = null;

        try {
            test = testRepo.findByNom(nameExpected).get();
        } catch (NoSuchElementException e) {
            fail("utilisateur is null");
        }

        then(test.getNom()).isEqualTo(nameExpected);
    }
}
