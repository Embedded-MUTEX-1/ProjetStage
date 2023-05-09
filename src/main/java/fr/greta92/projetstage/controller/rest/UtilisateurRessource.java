package fr.greta92.projetstage.controller.rest;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.exception.UtilisateurDejaExistant;
import fr.greta92.projetstage.exception.UtilisateurNonExistant;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UtilisateurRessource {
    @Autowired
    private GestionUtilisateur gestionUtilisateur;

    @GetMapping("/api/candidat/{mail}")
    public Candidat getCandidat(@PathVariable("mail") String mail)
    {
        try {
            return gestionUtilisateur.getCandidat(mail);
        }
        catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/candidats")
    public List<Candidat> getCandidat()
    {
        try {
            return gestionUtilisateur.getAllCandidat();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/api/candidat")
    public void setCandidat(@RequestBody Candidat candidat)
    {
        try {
            gestionUtilisateur.ajouterUtilisateur(candidat);
        } catch (UtilisateurDejaExistant e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity already exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/api/candidat")
    public void updateCandidat(@RequestBody Candidat candidat)
    {
        try {
            gestionUtilisateur.modifierUtilisateur(candidat);
        } catch (UtilisateurNonExistant e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity don't exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/utilisateur/{mail}")
    public Utilisateur getUtilisateur(@PathVariable("mail") String mail)
    {
        try {
            return gestionUtilisateur.getUtilisateur(mail);
        }
        catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/api/utilisateur")
    public void setUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        try {
            gestionUtilisateur.ajouterUtilisateur(utilisateur);
        } catch (UtilisateurDejaExistant e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity already exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/api/utilisateur")
    public void updateUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        try {
            gestionUtilisateur.modifierUtilisateur(utilisateur);
        } catch (UtilisateurNonExistant e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity don't exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
