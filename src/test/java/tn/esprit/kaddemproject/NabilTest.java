package tn.esprit.kaddemproject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Etudiant;
import tn.esprit.kaddemproject.repositories.ContratRepository;
import tn.esprit.kaddemproject.repositories.DepartementRepository;
import tn.esprit.kaddemproject.repositories.EquipeRepository;
import tn.esprit.kaddemproject.repositories.EtudiantRepository;
import tn.esprit.kaddemproject.services.IEtudiantService;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class NabilTest {
    Etudiant e = new Etudiant();
    Equipe eq = new Equipe(1);
    Contrat c = new Contrat(1);
    @Autowired
   private IEtudiantService service;

    @Mock
    private EtudiantRepository repo;
    @Mock
    private ContratRepository repo1;
    @Mock
    private EquipeRepository repo2;

    @Mock
    private DepartementRepository depRep;
    public void add(){



    }
    @Test
    void testAddActivitySector(){

        Mockito.when(repo.save(ArgumentMatchers.any(Etudiant.class))).thenReturn(e);
        Mockito.when(repo1.save(ArgumentMatchers.any(Contrat.class))).thenReturn(c);
        Mockito.when(repo2.save(ArgumentMatchers.any(Equipe.class))).thenReturn(eq);
        Etudiant etudiant =service.addAndAssignEtudiantToEquipeAndContract(e,eq.getIdEquipe(),c.getIdContrat());
//       log.info(""+etudiant.getIdEtudiant());
      //  Mockito.verify(repo, Mockito.times(1)).save(eq(e));

        // Verify
       // assertEquals(2L, etudiant.getNumCourse().longValue());
        //assertEquals(0, etudiant.getLevel());
    }
    @Test
    void getalletuidant(){
        Etudiant e=new Etudiant();
        Departement d =new Departement();
        d.setIdDepart(1);
        e.setIdEtudiant(1);
        e.setDepartement(d);
        e.setNomE("nabil");
        Etudiant e1=new Etudiant();
        e1.setIdEtudiant(2);
        e1.setDepartement(d);
        e1.setNomE("aze");
        Mockito.when(depRep.save(ArgumentMatchers.any(Departement.class))).thenReturn(d);
        Mockito.when(repo.save(ArgumentMatchers.any(Etudiant.class))).thenReturn(e);
        Mockito.when(repo.save(ArgumentMatchers.any(Etudiant.class))).thenReturn(e1);



        Mockito.when(repo.findAll()).thenReturn(Arrays.asList(e,e1));
        //List<Etudiant> etud=repo.findByDepartementIdDepart(1);
        assertEquals(2,repo.findAll().size());
    }
}



