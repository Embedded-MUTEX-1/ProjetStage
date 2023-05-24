package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.AuthenticationResponse;
import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.LoginData;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.exception.BadEmailPasswordException;
import fr.greta92.projetstage.exception.UtilisateurDejaExistantException;
import fr.greta92.projetstage.exception.UtilisateurNonExistantException;

import java.util.List;

public interface GestionUtilisateur {
    public AuthenticationResponse login(LoginData loginData) throws UtilisateurNonExistantException, BadEmailPasswordException;
    public Boolean existByEmail(String email);
    public Utilisateur getUtilisateur(String email);
    public void ajouterUtilisateur(Utilisateur utilisateur) throws UtilisateurDejaExistantException;
    public void modifierUtilisateur(Utilisateur utilisateur) throws UtilisateurNonExistantException;
    public void modifierUtilisateurAvecMdp(Utilisateur utilisateur) throws UtilisateurNonExistantException;
    public void supprimerUtilisateur(Utilisateur utilisateur);
    public Candidat getCandidat(String email);
    public List<Candidat> getAllCandidat();
    public void ajouterCandidat(Candidat candidat) throws UtilisateurDejaExistantException;
    public void modifierCandidat(Candidat candidat) throws UtilisateurNonExistantException;
    public void modifierCandidatAvecMdp(Candidat candidat) throws UtilisateurNonExistantException;
    public void supprimerCandidat(Candidat candidat);
    public void envoyerMailConnexionCandidat(Candidat candidat);
    public void envoyerMailNotificationCandidat(Candidat candidat);
}
