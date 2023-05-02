package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.QuestionQcm;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QuestionQcmRepo extends QuestionBaseRepo<QuestionQcm> {
}
