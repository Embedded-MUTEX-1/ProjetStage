package fr.greta92.projetstage.service;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.Utilisateur;

public interface GestionUtilisateur {
    public Utilisateur getUtilisateur(String email);
    public void ajouterUtilisateur(Utilisateur utilisateur);
    public void modifierUtilisateur(Utilisateur utilisateur);
    public void supprimerUtilisateur(Utilisateur utilisateur);
    public Candidat getCandidat(String email);
    public void ajouterCandidat(Candidat candidat);
    public void modifierCandidat(Candidat candidat);
    public void supprimerCandidat(Candidat candidat);
    public void envoyerMailConnexionCandidat(Candidat candidat);
    public void envoyerMailNotificationCandidat(Candidat candidat);
}
