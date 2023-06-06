package fr.greta92.projetstage.controller.rest;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.exception.UtilisateurDejaExistantException;
import fr.greta92.projetstage.exception.UtilisateurNonExistantException;
import fr.greta92.projetstage.service.GestionUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UtilisateurRessource {
    @Autowired
    private GestionUtilisateur gestionUtilisateur;

    /* Seul l'utilisateur à le droit de obtenir ses information pas les autres utilisateurs */
    @GetMapping("/api/candidat/{mail}")
    public Candidat getCandidat(@PathVariable("mail") String mail, Principal principal)
    {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        SimpleGrantedAuthority authority = authorities.stream().findFirst().get(); /* Design pattern facade non respecté */

        if("ROLE_USER".equals(authority.getAuthority()) && isDifferentUser(principal, mail))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
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
    @PreAuthorize("hasRole('ADMIN')")
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
    /*                           TODO Mettre en place la validation                        */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/candidat")
    public void setCandidat(@RequestBody Candidat candidat)
    {
        try {
            gestionUtilisateur.ajouterCandidat(candidat);
        } catch (UtilisateurDejaExistantException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity already exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*                           TODO Mettre en place la validation                        */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/candidat")
    public void updateCandidat(@RequestBody Candidat candidat)
    {
        try {
            if(candidat.getPassword() == null || candidat.getPassword().isEmpty()) {
                gestionUtilisateur.modifierUtilisateur(candidat);
            }
            else {
                gestionUtilisateur.modifierUtilisateurAvecMdp(candidat);
            }
        } catch (UtilisateurNonExistantException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity don't exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/utilisateur/{mail}")
    public Utilisateur getUtilisateur(@PathVariable("mail") String mail, Principal principal)
    {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        SimpleGrantedAuthority authority = authorities.stream().findFirst().get();

        if(isDifferentUser(principal, mail))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
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
    /*                           TODO Mettre en place la validation                        */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/utilisateur")
    public void setUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        try {
            gestionUtilisateur.ajouterUtilisateur(utilisateur);
        } catch (UtilisateurDejaExistantException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity already exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*                           TODO Mettre en place la validation                        */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/utilisateur")
    public void updateUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        try {
            if(utilisateur.getPassword() == null || utilisateur.getPassword().isEmpty())
            {
                gestionUtilisateur.modifierUtilisateur(utilisateur);
            }
            else {
                gestionUtilisateur.modifierUtilisateurAvecMdp(utilisateur);
            }
        } catch (UtilisateurNonExistantException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "entity don't exist"
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private boolean isDifferentUser(Principal principal, Utilisateur utilisateur)
    {
        String principalName = principal.getName(); // Email
        String userName = utilisateur.getUsername(); // Email
        return !principalName.equals(userName);
    }
    private boolean isDifferentUser(Principal principal, String mail)
    {
        String principalName = principal.getName(); // Email
        return !principalName.equals(mail);
    }
}
