package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QuestionBaseRepo<T extends Question> extends JpaRepository<T, Long> {

}
