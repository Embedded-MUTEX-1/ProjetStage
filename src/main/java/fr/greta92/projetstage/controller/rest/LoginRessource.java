package fr.greta92.projetstage.controller.rest;

import fr.greta92.projetstage.entity.AuthenticationResponse;
import fr.greta92.projetstage.entity.LoginData;
import fr.greta92.projetstage.exception.BadEmailPasswordException;
import fr.greta92.projetstage.exception.UtilisateurNonExistantException;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
public class LoginRessource {
    @Autowired
    private GestionUtilisateur gestionUtilisateur;
    @PostMapping("/api/Connexion")
    public AuthenticationResponse login(@RequestBody LoginData loginData, Principal principal)
    {
        try {
            return gestionUtilisateur.login(loginData);
        }
        catch (BadEmailPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (UtilisateurNonExistantException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
