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
public abstract class ReponseCandidat {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusReponse statusReponse;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_question")
    private Question question;
}
