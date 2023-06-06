package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@Repository
public interface ReponseRepo extends JpaRepository<Reponse, Long>, JpaSpecificationExecutor<Reponse> {
}
