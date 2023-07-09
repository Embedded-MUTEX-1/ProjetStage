package fr.greta92.projetstage.controllerTest;

import fr.greta92.projetstage.entity.Role;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.service.GestionTest;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRessourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestionTest gestionTest;

    @WithMockUser(username = "toto@gmail.com", authorities = { "ROLE_ADMIN" })
    @Test
    void returnTestTest() throws Exception {

        when(gestionTest.getTest("test_aptitude")).thenReturn(new fr.greta92.projetstage.entity.Test(Long.valueOf(1), "test_aptitude", "",null));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/test/{nom}", "test_aptitude")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("test_aptitude"));
    }
}
