package fr.greta92.projetstage.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeName("qcm")
public class QuestionQcm extends Question{
    private String question;

    @Transient
    @Override
    public String getChildType() {
        return "qcm";
    }
}
