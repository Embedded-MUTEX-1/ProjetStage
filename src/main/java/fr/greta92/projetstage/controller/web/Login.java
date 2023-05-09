package fr.greta92.projetstage.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Login {
    @GetMapping(value = { "/", "Connexion" })
    public String login(Model model, Principal principal)
    {
        if(principal == null)
        {
            return "login";
        }
        else
        {
            return "redirect:/TestToDo";
        }
    }
}
