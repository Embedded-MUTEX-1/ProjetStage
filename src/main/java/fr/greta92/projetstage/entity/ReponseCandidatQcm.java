package fr.greta92.projetstage.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("qcm")
public class ReponseCandidatQcm extends ReponseCandidat {
    private String reponse;

    @Transient
    @Override
    public String getChildType() {
        return "qcm";
    }
}
