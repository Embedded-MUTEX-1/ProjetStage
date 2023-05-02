package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Question;
import fr.greta92.projetstage.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReponseBaseRepo<T extends Reponse> extends JpaRepository<T, Long> {
}
