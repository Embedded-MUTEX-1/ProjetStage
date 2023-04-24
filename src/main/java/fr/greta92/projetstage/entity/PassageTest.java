package fr.greta92.projetstage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PassageTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private LocalDateTime datePassage;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private Duration timeout;
    private Duration duration;
    @ManyToOne
    @JoinColumn(name="id_candidat")
    private Candidat candidat;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_test")
    private Test test;
    @OneToMany
    @JoinColumn(name = "id_passage_test")
    private List<ReponseCandidat> reponseCandidats;
}
