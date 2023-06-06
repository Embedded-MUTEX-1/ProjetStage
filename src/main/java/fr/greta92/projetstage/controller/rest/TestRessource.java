package fr.greta92.projetstage.controller.rest;

import fr.greta92.projetstage.entity.Test;
import fr.greta92.projetstage.exception.TestDejaExistantException;
import fr.greta92.projetstage.exception.TestNonExistantException;
import fr.greta92.projetstage.service.GestionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class TestRessource {
    @Autowired
    private GestionTest gestionTest;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/test/{nom}")
    public Test getTest(@PathVariable("nom") String nom)
    {
        try {
            return gestionTest.getTest(nom);
        } catch (TestNonExistantException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/tests")
    public List<Test> getTests()
    {
        try {
            return gestionTest.getAllTest();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/test")
    public void setTest(@RequestBody Test test)
    {
        try {
            gestionTest.ajouterTest(test);
        } catch (TestDejaExistantException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/test")
    public void updateTest(@RequestBody Test test)
    {
        try {
            gestionTest.modifierTest(test);
        } catch (TestNonExistantException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
