package fr.greta92.projetstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("candidat")
public class Candidat extends Utilisateur {
    @JsonIgnore
    @OneToMany(mappedBy = "candidat")
    private List<PassageTest> passageTests;
}
