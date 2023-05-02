package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Question;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QuestionRepo extends QuestionBaseRepo<Question> {
}
