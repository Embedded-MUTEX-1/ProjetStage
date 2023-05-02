package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.PassageTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassageTestRepo extends JpaRepository<PassageTest, Long> {
    public List<PassageTest> findPassageTestByCandidat(Candidat candidat);
}
