package fr.greta92.projetstage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="reponse_type")
public abstract class Reponse {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_question")
    private Question question;
}
