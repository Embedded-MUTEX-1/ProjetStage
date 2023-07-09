package fr.greta92.projetstage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestResponse {
    private Long passageTestId;
    HashMap<Long, ReponseCandidat> reponseCandidats;
}
