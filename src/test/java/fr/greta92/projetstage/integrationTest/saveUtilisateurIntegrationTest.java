package fr.greta92.projetstage.integrationTest;

import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.repository.UtilisateurRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class saveUtilisateurIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UtilisateurRepo utilisateurRepo;
    @Test
    @WithMockUser(username = "toto@gmail.com", authorities = { "ROLE_ADMIN" })
    public void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/utilisateur")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(
                                "{\n" +
                                "  \t\"id\":0,\n" +
                                "\t\"email\":\"toto\",\n" +
                                "\t\"password\":\"toto\",\n" +
                                "\t\"role\": \"ROLE_ADMIN\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
//        System.out.println("Entities : " + utilisateurRepo.findAll());
//
//        Utilisateur savedUtilisateur = utilisateurRepo.findById(Long.valueOf(2)).orElseThrow();
//
//        then(savedUtilisateur.getEmail()).isEqualTo("toto");
    }
}
