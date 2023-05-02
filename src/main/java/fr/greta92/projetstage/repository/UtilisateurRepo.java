package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UtilisateurRepo extends UtilisateurBaseRepo<Utilisateur> {
}
