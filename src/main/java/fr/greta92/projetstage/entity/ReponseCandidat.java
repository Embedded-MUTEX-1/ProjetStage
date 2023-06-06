package fr.greta92.projetstage.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "@ttype")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReponseQcm.class, name = "qcm"),
})
public abstract class ReponseCandidat {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusReponse statusReponse;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_question")
    private Question question;

    @JsonProperty("@ttype")
    public abstract String getChildType();
}
