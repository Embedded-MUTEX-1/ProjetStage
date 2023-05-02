package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    public Test findByNom(String nom);
}
