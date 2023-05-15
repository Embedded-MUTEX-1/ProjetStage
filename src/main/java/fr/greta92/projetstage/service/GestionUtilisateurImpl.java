package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.*;
import fr.greta92.projetstage.exception.BadPasswordException;
import fr.greta92.projetstage.exception.UtilisateurDejaExistantException;
import fr.greta92.projetstage.exception.UtilisateurNonExistantException;
import fr.greta92.projetstage.repository.CandidatRepo;
import fr.greta92.projetstage.repository.UtilisateurRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GestionUtilisateurImpl implements GestionUtilisateur {

    @Autowired
    private UtilisateurRepo utilisateurRepo;
    @Autowired
    private CandidatRepo candidatRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;

    @Override
    public AuthenticationResponse login(LoginData loginData) throws UtilisateurNonExistantException, BadPasswordException {
        Utilisateur utilisateur = null;
        try {
            utilisateur = getUtilisateur(loginData.getEmail());
        } catch (NoSuchElementException e) {
            throw new UtilisateurNonExistantException();
        }

        if (!BCrypt.checkpw(loginData.getPassword(), utilisateur.getPassword())) {
            throw new BadPasswordException();
        }

        return new AuthenticationResponse(jwtService.generateToken(utilisateur));
    }

    @Override
    public Boolean existByEmail(String email) {
        return utilisateurRepo.existsByEmail(email);
    }

    @Override
    public Utilisateur getUtilisateur(String email) {
        return utilisateurRepo.findUtilisateurByEmail(email).orElseThrow();
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) throws UtilisateurDejaExistantException {
        if(existByEmail(utilisateur.getEmail()))
        {
            throw new UtilisateurDejaExistantException();
        }
        utilisateur.setPassword(BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt(12)));
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) throws UtilisateurNonExistantException {
        if(!existByEmail(utilisateur.getEmail()) && utilisateurRepo.existsById(utilisateur.getId())) {

            Utilisateur prevUtilisateur = getUtilisateur(utilisateur.getEmail());
            utilisateur.setPassword(prevUtilisateur.getPassword());
            utilisateurRepo.save(utilisateur);

        }
        else {
            throw new UtilisateurNonExistantException();
        }
    }

    @Override
    public void modifierUtilisateurAvecMdp(Utilisateur utilisateur) throws UtilisateurNonExistantException {
        if(!existByEmail(utilisateur.getEmail()) && utilisateurRepo.existsById(utilisateur.getId())) {
            utilisateur.setPassword(BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt(12)));
            utilisateurRepo.save(utilisateur);
        }
        else {
            throw new UtilisateurNonExistantException();
        }
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        //utilisateurRepo.delete(utilisateur);
    }

    @Override
    public Candidat getCandidat(String email) {
        return candidatRepo.findUtilisateurByEmail(email).orElseThrow();
    }

    @Override
    public List<Candidat> getAllCandidat() {
        return candidatRepo.findAll();
    }

    @Override
    public void ajouterCandidat(Candidat candidat) throws UtilisateurDejaExistantException {
        if(existByEmail(candidat.getEmail()))
        {
            throw new UtilisateurDejaExistantException();
        }
        candidat.setPassword(BCrypt.hashpw(candidat.getPassword(), BCrypt.gensalt(12)));
        candidatRepo.save(candidat);
    }

    @Override
    public void modifierCandidat(Candidat candidat) throws UtilisateurNonExistantException {
        if(!existByEmail(candidat.getEmail()) && candidatRepo.existsById(candidat.getId())) {
            Candidat prevCandidat = getCandidat(candidat.getEmail());
            candidat.setPassword(prevCandidat.getPassword());
            candidatRepo.save(candidat);
        }
        else {
            throw new UtilisateurNonExistantException();
        }
    }

    @Override
    public void modifierCandidatAvecMdp(Candidat candidat) throws UtilisateurNonExistantException {
        if(!existByEmail(candidat.getEmail()) && candidatRepo.existsById(candidat.getId())) {
            candidat.setPassword(BCrypt.hashpw(candidat.getPassword(), BCrypt.gensalt(12)));
            candidatRepo.save(candidat);
        }
        else {
            throw new UtilisateurNonExistantException();
        }
    }

    @Override
    public void supprimerCandidat(Candidat candidat) {
        //candidatRepo.delete(candidat);
    }

    @Override
    public void envoyerMailConnexionCandidat(Candidat candidat) {
        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(candidat.getEmail());
        emailDetails.setSubject("Identifiant connexion");
        emailDetails.setMsgBody(
                  "Bonjour" + "\n"
                + "\n"
                + "Nous vous informons que votre compte sur l'application CritTest à été créer" + "\n"
                + "Voici  donc vos identifiant :" + "\n"
                + "login : " + candidat.getEmail() + "\n"
                + "mot de passe" + candidat.getPassword() + "\n"
                + "\n"
                + "Cordialemnet," + "\n"
                + "L'équipe Crit"

        );

        emailService.sendSimpleMail(emailDetails);
    }

    @Override
    public void envoyerMailNotificationCandidat(Candidat candidat) {
        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(candidat.getEmail());
        emailDetails.setSubject("Resultat test");
        emailDetails.setMsgBody(
                          "Bonjour" + "\n"
                        + "\n"
                        + "Nous vous informons que les resultats de vos tests sont disponible" + "\n"
                        + "\n"
                        + "Cordialemnet," + "\n"
                        + "L'équipe Crit"

        );

        emailService.sendSimpleMail(emailDetails);
    }
}
