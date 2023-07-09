package fr.greta92.projetstage.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="question_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "ttype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionQcm.class, name = "qcm"),
})
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nombrePoints;
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<Reponse> reponses;

    @JsonProperty("ttype")
    public abstract String getChildType();
}
