package fr.greta92.projetstage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="question_type")
public abstract class Question {
    @Id
    private Long id;
    private int nonmbrePoints;
    @OneToMany(mappedBy = "question")
    private List<Reponse> reponses;
}
