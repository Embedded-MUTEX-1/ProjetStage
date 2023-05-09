package fr.greta92.projetstage.controller.web;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.repository.CandidatRepo;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
public class TestToDo {

    @Autowired
    private GestionUtilisateur gestionUtilisateur;

    @GetMapping("/TestToDo")
    public String testToDo(Model model, Principal principal)
    {
        try {
            Candidat candidat = gestionUtilisateur.getCandidat(principal.getName());
            model.addAttribute("candidat", candidat);
            return "testTodo";

        } catch (NoSuchElementException e) {
            return "redirect:/Connexion";
        } catch (Exception e) {
            return "redirect:/Connexion";
        }

    }
}
