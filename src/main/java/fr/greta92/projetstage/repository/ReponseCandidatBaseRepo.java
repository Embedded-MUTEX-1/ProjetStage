package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.ReponseCandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReponseCandidatBaseRepo<T extends ReponseCandidat> extends JpaRepository<T, Long> {
}
