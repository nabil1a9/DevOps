package tn.esprit.kaddemproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Niveau;
import tn.esprit.kaddemproject.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IEquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private IEquipeServiceImpl equipeService;
    private Equipe equipe1;
    private Equipe equipe2;
    private Equipe equipe3;

    @BeforeEach
    public void setUp() {
        equipe1 = new Equipe(1, "Esprit", Niveau.EXPERT);
        equipe2 = new Equipe(2, "TEKUP", Niveau.SENIOR);
        equipe3 = new Equipe(3, "SESAME", Niveau.JUNIOR);
    }

    @Test
    void testRetrieveAllEquipes() {
        List<Equipe> list = new ArrayList<>();
        list.add(equipe1);
        list.add(equipe2);

        when(equipeRepository.findAll()).thenReturn(list);

        List<Equipe> universites = equipeService.retrieveAllEquipes();

        assertEquals(2, universites.size());
        assertNotNull(universites);
    }

    @Test
    void testAddEquipe() {
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe1);
        Equipe newEquipe = equipeService.addEquipe(equipe1);
        assertNotNull(newEquipe);
        assertThat(newEquipe.getNomEquipe()==("Esprit"));
    }




    // Add more tests for updateEquipe, deleteEquipe, evoluerEquipes, etc.
}
