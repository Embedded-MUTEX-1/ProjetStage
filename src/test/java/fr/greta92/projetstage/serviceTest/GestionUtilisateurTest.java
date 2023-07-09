package fr.greta92.projetstage.serviceTest;

import fr.greta92.projetstage.entity.Role;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.exception.UtilisateurNonExistantException;
import fr.greta92.projetstage.repository.UtilisateurRepo;
import fr.greta92.projetstage.service.GestionUtilisateur;
import fr.greta92.projetstage.service.GestionUtilisateurImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
@PowerMockIgnore("javax.management.*")
@SpringBootTest
public class GestionUtilisateurTest {
    @Mock
    UtilisateurRepo utilisateurRepo;
    @InjectMocks
    GestionUtilisateur gestionUtilisateur = new GestionUtilisateurImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnUtilisateurTest()
    {
//        Mockito.when(utilisateurRepo.existsByEmail("toto@gmail.com")).thenReturn(true);
        Mockito.when(utilisateurRepo.findUtilisateurByEmail("toto@gmail.com")).thenReturn(Optional.of(new Utilisateur(Long.valueOf(1), "toto@gmail.com", "Toto", "Tata", "12345678", Role.ROLE_USER)));

        Utilisateur utilisateur = gestionUtilisateur.getUtilisateur("toto@gmail.com");

        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getId()).isEqualTo(1);
    }
    @Test
    public void returnExceptionWhenSaveTest()
    {
        Mockito.when(utilisateurRepo.existsByEmail("toto@gmail.com")).thenReturn(true);

        try {
            gestionUtilisateur.modifierUtilisateur(new Utilisateur(Long.valueOf(1), "toto@gmail.com", "", "", "12345678", Role.ROLE_USER));
            fail("modifierUtilisateur didn't throws exception");
        } catch (UtilisateurNonExistantException e) {
            // Success
        }
    }
}
