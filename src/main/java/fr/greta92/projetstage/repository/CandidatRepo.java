package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Candidat;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CandidatRepo extends UtilisateurBaseRepo<Candidat>{
}
