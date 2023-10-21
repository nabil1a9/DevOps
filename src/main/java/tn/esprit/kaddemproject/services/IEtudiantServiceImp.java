package tn.esprit.kaddemproject.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Etudiant;
import tn.esprit.kaddemproject.generic.IGenericServiceImp;
import tn.esprit.kaddemproject.repositories.ContratRepository;
import tn.esprit.kaddemproject.repositories.EquipeRepository;
import tn.esprit.kaddemproject.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IEtudiantServiceImp extends IGenericServiceImp<Etudiant,Integer> implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;
    @Lazy
    private final IContratService iContratService;
    @Lazy
    private final IEquipeService iEquipeService;

    private final IDepartementService departementService;

    @Transactional
    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {

        Departement departement = departementService.retrieveById(departementId);
        Etudiant etudiant = this.retrieveById(etudiantId);

        etudiant.setDepartement(departement);

    }

    @Transactional
    @Override
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {

        Contrat contrat = iContratService.retrieveById(idContrat);
        Equipe equipe = iEquipeService.retrieveById(idEquipe);

        if(contrat!=null && equipe != null){
            Etudiant etudiant = this.add(e);

            List<Equipe> equipes = new ArrayList<Equipe>();
            equipes.add(equipe);
            etudiant.setEquipes(equipes);
            //etudiant.getEquipes().add(equipe);

             contrat.setEtudiant(etudiant);
             return etudiant;
        }

        return null;
    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {

        return etudiantRepository.findByDepartementIdDepart(idDepartement);
    }
}
