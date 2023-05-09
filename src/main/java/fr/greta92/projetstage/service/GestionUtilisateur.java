package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.Utilisateur;
import fr.greta92.projetstage.exception.UtilisateurDejaExistant;
import fr.greta92.projetstage.exception.UtilisateurNonExistant;

import java.util.List;

public interface GestionUtilisateur {
    public Boolean existByEmail(String email);
    public Utilisateur getUtilisateur(String email);
    public void ajouterUtilisateur(Utilisateur utilisateur) throws UtilisateurDejaExistant;
    public void modifierUtilisateur(Utilisateur utilisateur) throws UtilisateurNonExistant;
    public void supprimerUtilisateur(Utilisateur utilisateur);
    public Candidat getCandidat(String email);
    public List<Candidat> getAllCandidat();
    public void ajouterCandidat(Candidat candidat) throws UtilisateurDejaExistant;
    public void modifierCandidat(Candidat candidat) throws UtilisateurNonExistant;
    public void supprimerCandidat(Candidat candidat);
    public void envoyerMailConnexionCandidat(Candidat candidat);
    public void envoyerMailNotificationCandidat(Candidat candidat);
}
