package tn.esprit.kaddemproject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.kaddemproject.controllers.ContratRestController;
import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.services.IContratService;
import tn.esprit.kaddemproject.generic.IGenericService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratRestControllerTest {

    @Mock
    private IContratService contratService;

    @InjectMocks
    private ContratRestController contratRestController;

    @Test
    void testAddContrat() {
        // Arrange
        Contrat contratToAdd = new Contrat(); // Initialize with necessary values

        // Act
        when(contratService.add(any(Contrat.class))).thenReturn(contratToAdd);
        Contrat result = contratRestController.add(contratToAdd);

        // Assert
        assertEquals(contratToAdd, result);
        verify(contratService, times(1)).add(any(Contrat.class));
    }

    // Similar modifications for other test methods

    // Additional tests for your specific controller methods
}
