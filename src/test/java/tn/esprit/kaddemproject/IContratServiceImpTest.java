package tn.esprit.kaddemproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.repositories.ContratRepository;
import tn.esprit.kaddemproject.repositories.EtudiantRepository;
import tn.esprit.kaddemproject.services.IContratServiceImp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IContratServiceImpTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private IContratServiceImp contratService;

    @Test
    public void testAddContrat() {
        // Arrange
        Contrat contratToSave = new Contrat();
        when(contratRepository.save(any(Contrat.class))).thenReturn(contratToSave);

        // Act
        Contrat savedContrat = contratService.add(new Contrat());

        // Assert
        assertEquals(contratToSave, savedContrat);
        verify(contratRepository, times(1)).save(any(Contrat.class));
    }

    @Test
    public void testUpdateContrat() {
        // Arrange
        Contrat existingContrat = new Contrat();
        when(contratRepository.save(any(Contrat.class))).thenReturn(existingContrat);

        // Act
        Contrat updatedContrat = contratService.update(new Contrat());

        // Assert
        assertEquals(existingContrat, updatedContrat);
        verify(contratRepository, times(1)).save(any(Contrat.class));
    }

    @Test
    public void testRetrieveContratById() {
        // Arrange
        Contrat expectedContrat = new Contrat();
        when(contratRepository.findById(anyInt())).thenReturn(Optional.of(expectedContrat));

        // Act
        Contrat retrievedContrat = contratService.retrieveById(1);

        // Assert
        assertEquals(expectedContrat, retrievedContrat);
        verify(contratRepository, times(1)).findById(anyInt());
    }

    @Test
    public void testRetrieveAllContrats() {
        // Arrange
        List<Contrat> expectedContrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratRepository.findAll()).thenReturn(expectedContrats);

        // Act
        List<Contrat> retrievedContrats = contratService.retrieveAll();

        // Assert
        assertEquals(expectedContrats, retrievedContrats);
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteContrat() {
        // Arrange
        when(contratRepository.existsById(anyInt())).thenReturn(true);

        // Act
        boolean result = contratService.delete(1);

        // Assert
        assertTrue(result);
        verify(contratRepository, times(1)).deleteById(anyInt());
    }

    // Add more test cases for other methods if needed
}
