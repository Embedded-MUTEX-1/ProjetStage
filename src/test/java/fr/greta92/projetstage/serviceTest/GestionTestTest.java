package fr.greta92.projetstage.serviceTest;

import fr.greta92.projetstage.entity.Role;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.exception.TestNonExistantException;
import fr.greta92.projetstage.repository.TestRepo;
import fr.greta92.projetstage.repository.UtilisateurRepo;
import fr.greta92.projetstage.service.GestionTest;
import fr.greta92.projetstage.service.GestionTestImpl;
import fr.greta92.projetstage.service.GestionUtilisateur;
import fr.greta92.projetstage.service.GestionUtilisateurImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
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

@RunWith(MockitoJUnitRunner.class)
@PowerMockIgnore("javax.management.*")
@SpringBootTest
public class GestionTestTest {
    @Mock
    TestRepo testRepo;
    @InjectMocks
    GestionTest gestionTest = new GestionTestImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnTestTest() throws TestNonExistantException {
//        Mockito.when(utilisateurRepo.existsByEmail("toto@gmail.com")).thenReturn(true);
        Mockito.when(testRepo.findByNom("test_aptitude")).thenReturn(Optional.of(new fr.greta92.projetstage.entity.Test(Long.valueOf(1), "test_aptitude", null)));

        fr.greta92.projetstage.entity.Test test = gestionTest.getTest("test_aptitude");

        assertThat(test).isNotNull();
        assertThat(test.getId()).isEqualTo(1);
    }
}
