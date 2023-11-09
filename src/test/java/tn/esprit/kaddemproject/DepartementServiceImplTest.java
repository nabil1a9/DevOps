package tn.esprit.kaddemproject;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.repositories.DepartementRepository;
import tn.esprit.kaddemproject.services.IDepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private IDepartementServiceImpl departementService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllDepartements() {
        Departement departement1 = new Departement(1, "IT", null);
        Departement departement2 = new Departement(2, "HR", null);
        List<Departement> departements = Arrays.asList(departement1, departement2);

        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(2, result.size());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    public void testAddDepartement() {
        Departement departement = new Departement(null, "Marketing", null);
        Departement savedDepartement = new Departement(3, "Marketing", null);

        when(departementRepository.save(any(Departement.class))).thenReturn(savedDepartement);

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result);
        assertEquals("Marketing", result.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement(1, "IT", null);

        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        Departement result = departementService.updateDepartement(departement);

        assertNotNull(result);
        assertEquals("IT", result.getNomDepart());
    }

    @Test
    public void testRetrieveDepartement() {
        Departement departement = new Departement(1, "Engineering", null);

        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(1);

        assertNotNull(result);
        assertEquals("Engineering", result.getNomDepart());
    }

    @Test
    public void testDeleteDepartement() {
        Departement departement = new Departement(1, "Sales", null);

        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        doNothing().when(departementRepository).delete(any(Departement.class));

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).delete(departement);
    }
}
