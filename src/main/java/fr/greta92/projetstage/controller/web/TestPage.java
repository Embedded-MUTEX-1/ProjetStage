package fr.greta92.projetstage.controller.web;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.PassageTest;
import fr.greta92.projetstage.entity.Status;
import fr.greta92.projetstage.exception.PassageTestTooEarlyException;
import fr.greta92.projetstage.exception.PassageTestTooLateException;
import fr.greta92.projetstage.service.GestionPassageTest;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
public class TestPage {
    @Autowired
    private GestionPassageTest gestionPassageTest;
    @Autowired
    private GestionUtilisateur gestionUtilisateur;

    @GetMapping("/TestPage/{id}")
    public String startTest(Model model, Principal principal, @PathVariable(required = true) String id) {
        Candidat candidat = gestionUtilisateur.getCandidat(principal.getName());
        List<PassageTest> passageTests = gestionPassageTest.getAllPassageTestFromCandidat(candidat, Status.TODO);
        PassageTest passageTestOut = null;

        if(passageTests == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        for (PassageTest passageTest: passageTests) {
            if(passageTest.getId() == Long.valueOf(id)) {
                passageTestOut = passageTest;
                break;
            }
        }
        if(passageTestOut == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        try {
            gestionPassageTest.lancerPassageTest(passageTestOut.getId());
        } catch (PassageTestTooLateException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (PassageTestTooEarlyException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        model.addAttribute("candidat", candidat);
        model.addAttribute("passageTest", passageTestOut);
        model.addAttribute("test", passageTestOut.getTest());

        return "testPage";
    }
}
