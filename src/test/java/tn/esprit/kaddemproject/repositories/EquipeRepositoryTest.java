package tn.esprit.kaddemproject.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Niveau;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test") // Specify the "test" profile
class EquipeRepositoryTest {
    @Autowired
    private EquipeRepository equipeRepository;

    private Equipe equipe1;
    private Equipe equipe2;
    private Equipe equipe3;

    @BeforeEach
    void init() {
        equipe1 = new Equipe(1, "Esprit", Niveau.EXPERT);
        equipe2 = new Equipe(2, "TEKUP", Niveau.SENIOR);
        equipe3 = new Equipe(3, "SESAME", Niveau.JUNIOR);
    }

    @Test
    @DisplayName("equipe saved normally")
    void save() {
        Equipe newEquipe = equipeRepository.save(equipe1);
        assertNotNull(newEquipe);
        AssertionsForClassTypes.assertThat(newEquipe.getIdEquipe()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("2 is the correct answer")
    void getEquipes() {
        equipeRepository.save(equipe2);
        equipeRepository.save(equipe3);

        List<Equipe> list = (List<Equipe>) equipeRepository.findAll();

        assertNotNull(list);
        AssertionsForClassTypes.assertThat(list).isNotNull();
        assertEquals(2, list.size());
    }

}