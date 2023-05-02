package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Reponse;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReponseRepo extends ReponseBaseRepo<Reponse> {
}
