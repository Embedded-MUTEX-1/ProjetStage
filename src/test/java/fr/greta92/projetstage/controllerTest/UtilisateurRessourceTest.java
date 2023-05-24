package fr.greta92.projetstage.controllerTest;

import fr.greta92.projetstage.entity.Role;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurRessourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestionUtilisateur gestionUtilisateur;

    @WithMockUser(username = "toto@gmail.com", authorities = { "ROLE_ADMIN" })
    @Test
    void returnUtilisateurTest() throws Exception {

        when(gestionUtilisateur.getUtilisateur("toto@gmail.com")).thenReturn(new Utilisateur(Long.valueOf(1), "toto@gmail.com", "12345678", Role.ROLE_ADMIN));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/utilisateur/{mail}", "toto@gmail.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("toto@gmail.com"));
    }

}
