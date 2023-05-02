package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UtilisateurBaseRepo<T extends Utilisateur> extends JpaRepository<T, Long> {
    public T findUtilisateurByEmail(String email);
}
