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
    @Test
    void retrieveAllDepartments() {
        List<Departement> departments = new ArrayList<>();
        when(departementRepository.findAll()).thenReturn(departments);
        List<Departement> result = departementService.retrieveAllDepartements();
        assertEquals(departments, result);
    }
    @Test
    void deleteDepartment() {
        int id = 1;
        doNothing().when(departementRepository).deleteById(id);
        departementService.deleteDepartement(id);
        verify(departementRepository, times(1)).deleteById(id);
    }
    @Test
    void retrieveDepartment() {
        int id = 1;
        Departement dep = new Departement();
        when(departementRepository.findById(id)).thenReturn(Optional.of(dep));
        Departement result = departementService.retrieveDepartement(id);
        assertEquals(dep, result);
    }



}
