package fr.greta92.projetstage.repository;

import fr.greta92.projetstage.entity.Candidat;
import fr.greta92.projetstage.entity.PassageTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassageTestRepo extends JpaRepository<PassageTest, Long> {
    public Optional<List<PassageTest>> findPassageTestByCandidat(Candidat candidat);
}
