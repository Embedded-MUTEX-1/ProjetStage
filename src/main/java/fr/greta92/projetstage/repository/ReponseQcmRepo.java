package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.ReponseQcm;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReponseQcmRepo extends ReponseBaseRepo<ReponseQcm> {
}
