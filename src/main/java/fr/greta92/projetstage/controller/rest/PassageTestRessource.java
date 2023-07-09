package fr.greta92.projetstage.controller.rest;

import fr.greta92.projetstage.entity.*;
import fr.greta92.projetstage.exception.PassageTestTooEarlyException;
import fr.greta92.projetstage.exception.PassageTestTooLateException;
import fr.greta92.projetstage.service.GestionPassageTest;
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
public class PassageTestRessource {
    @Autowired
    private GestionPassageTest gestionPassageTest;
    @Autowired
    private GestionUtilisateur gestionUtilisateur;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/passagetest/{id}")
    public PassageTest getPassageTest(@PathVariable("id") long id, Principal principal) {
        try {
            return gestionPassageTest.getPassageTest(Long.valueOf(id));
        }
        catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/api/passagetests/{mail}")
    public List<PassageTest> getPassageAllTestFromCandidat(@PathVariable("mail") String mail, Principal principal) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        SimpleGrantedAuthority authority = authorities.stream().findFirst().get(); /* Design pattern facade non respecté */

        if("ROLE_USER".equals(authority.getAuthority()) && isDifferentUser(principal, mail))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        try {
            Candidat candidat = gestionUtilisateur.getCandidat(mail);
            return gestionPassageTest.getAllPassageTestFromCandidat(candidat);
        }
        catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/starttest/{id}")
    public String startTest(@PathVariable("id") long id, Principal principal) {

        Candidat candidat = gestionUtilisateur.getCandidat(principal.getName());
        List<PassageTest> passageTests = gestionPassageTest.getAllPassageTestFromCandidat(candidat, Status.TODO);
        PassageTest passageTestOut = null;

        if(passageTests == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        for (PassageTest passageTest: passageTests) {
            if(passageTest.getId() == id) {
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

        return "TEST STARTED";
    }

    @PostMapping("/api/verifytest")
    public String verifyTest(@RequestBody TestResponse testResponse, Principal principal) {

        Candidat candidat = gestionUtilisateur.getCandidat(principal.getName());
        List<PassageTest> passageTests = gestionPassageTest.getAllPassageTestFromCandidat(candidat, Status.EN_COURS);
        PassageTest passageTestOut = null;

        if(passageTests == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        for (PassageTest passageTest: passageTests) {
            if(passageTest.getId() == testResponse.getPassageTestId()) {
                passageTestOut = passageTest;
                break;
            }
        }
        if(passageTestOut == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        try {
            gestionPassageTest.verifierPassageTest(testResponse);
        } catch (PassageTestTooLateException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return "TEST VERIFIED";
    }
    private boolean isDifferentUser(Principal principal, String mail)
    {
        String principalName = principal.getName(); // Email
        return !principalName.equals(mail);
    }
}
