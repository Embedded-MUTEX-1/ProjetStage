package fr.greta92.projetstage.repositoryTest;

import fr.greta92.projetstage.entity.Role;
import fr.greta92.projetstage.entity.Utilisateur;
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
public class UtilisateurRepoTest {
    @Autowired
    private UtilisateurRepo utilisateurRepo;
    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void saveUserTest()
    {
        Utilisateur utilisateur = new Utilisateur(null, "toto@gmail.com", "12345678", "", "", Role.ROLE_USER);

        utilisateurRepo.save(utilisateur);

        Utilisateur savedUtilisateur = testEntityManager.find(Utilisateur.class, Long.valueOf(2));

        then(utilisateur.getEmail()).isEqualTo(savedUtilisateur.getEmail());
    }
    @Test
    void returnUserTest()
    {
        String emailExpected = "tata@gmail.com";
        Utilisateur utilisateur = null;

        try {
            utilisateur = utilisateurRepo.findUtilisateurByEmail(emailExpected).get();
        } catch (NoSuchElementException e) {
            fail("utilisateur is null");
        }

        then(utilisateur.getEmail()).isEqualTo(emailExpected);
    }
}
