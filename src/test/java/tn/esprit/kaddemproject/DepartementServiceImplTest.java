package tn.esprit.kaddemproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.repositories.DepartementRepository;
import tn.esprit.kaddemproject.services.IDepartementServiceImpl;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private IDepartementServiceImpl departementService;



    @Test
    public void testAddDepartement() {
        Departement dep = new Departement();


        when(departementRepository.save(dep)).thenReturn(dep);

        Departement result = departementService.addDepartement(dep);
        assertEquals(dep, result);
    }


}
