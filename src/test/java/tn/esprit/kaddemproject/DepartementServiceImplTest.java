package tn.esprit.kaddemproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.repositories.DepartementRepository;
import tn.esprit.kaddemproject.services.IDepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private IDepartementServiceImpl departementService;

    @Test
    public void testRetrieveAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "IT", null));
        departements.add(new Departement(2, "HR", null));

        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(departements, result);
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

  
}
