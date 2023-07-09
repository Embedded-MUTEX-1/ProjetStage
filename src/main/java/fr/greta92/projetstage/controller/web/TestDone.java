package fr.greta92.projetstage.controller.web;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.PassageTest;
import fr.greta92.projetstage.entity.Status;
import fr.greta92.projetstage.service.GestionPassageTest;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class TestDone {
    @Autowired
    private GestionUtilisateur gestionUtilisateur;
    @Autowired
    private GestionPassageTest gestionPassageTest;

    @GetMapping("/TestDone")
    public String testToDo(Model model, Principal principal)
    {
        try {
            Candidat candidat = gestionUtilisateur.getCandidat(principal.getName());
            List<PassageTest> passageTests = new ArrayList<>();

            passageTests.addAll(gestionPassageTest.getAllPassageTestFromCandidat(candidat, Status.DONE));
            passageTests.addAll(gestionPassageTest.getAllPassageTestFromCandidat(candidat, Status.ANNULER));
            passageTests.addAll(gestionPassageTest.getAllPassageTestFromCandidat(candidat, Status.CORRIGE));

            model.addAttribute("candidat", candidat);
            model.addAttribute("passageTests", passageTests);
            return "testDone";

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return "redirect:/Connexion";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Connexion";
        }

    }
}
