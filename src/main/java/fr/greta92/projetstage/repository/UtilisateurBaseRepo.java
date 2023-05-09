package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UtilisateurBaseRepo<T extends Utilisateur> extends JpaRepository<T, Long> {
    public Optional<T> findUtilisateurByEmail(String email);
    public boolean existsByEmail(String email);
}
