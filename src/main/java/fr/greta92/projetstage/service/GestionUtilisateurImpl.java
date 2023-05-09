package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.EmailDetails;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.exception.UtilisateurDejaExistant;
import fr.greta92.projetstage.exception.UtilisateurNonExistant;
import fr.greta92.projetstage.repository.CandidatRepo;
import fr.greta92.projetstage.repository.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionUtilisateurImpl implements GestionUtilisateur {

    @Autowired
    private UtilisateurRepo utilisateurRepo;
    @Autowired
    private CandidatRepo candidatRepo;
    @Autowired
    EmailService emailService;

    @Override
    public Boolean existByEmail(String email) {
        return utilisateurRepo.existsByEmail(email);
    }

    @Override
    public Utilisateur getUtilisateur(String email) {
        return utilisateurRepo.findUtilisateurByEmail(email).orElseThrow();
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) throws UtilisateurDejaExistant {
        if(existByEmail(utilisateur.getEmail()))
        {
            throw new UtilisateurDejaExistant();
        }
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) throws UtilisateurNonExistant {
        if(existByEmail(utilisateur.getEmail())) {
            utilisateurRepo.save(utilisateur);
        }
        else {
            throw new UtilisateurNonExistant();
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
    public void ajouterCandidat(Candidat candidat) throws UtilisateurDejaExistant {
        if(existByEmail(candidat.getEmail()))
        {
            throw new UtilisateurDejaExistant();
        }
        candidatRepo.save(candidat);
    }

    @Override
    public void modifierCandidat(Candidat candidat) throws UtilisateurNonExistant {
        if(existByEmail(candidat.getEmail())) {
            candidatRepo.save(candidat);
        }
        else {
            throw new UtilisateurNonExistant();
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
