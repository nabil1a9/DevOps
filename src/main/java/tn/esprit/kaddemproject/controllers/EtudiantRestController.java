package tn.esprit.kaddemproject.controllers;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tn.esprit.kaddemproject.entities.Etudiant;
import tn.esprit.kaddemproject.generic.GenericController;
import tn.esprit.kaddemproject.services.IEtudiantService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController extends GenericController<Etudiant,Integer> {

    private final IEtudiantService etudiantService;

    @PostMapping("/contract/{idContrat}/equipe/{idEquipe}")
    public Etudiant addAndAssignEtudiantToEquipeAndContract(@RequestBody Etudiant e,@PathVariable Integer idContrat, @PathVariable Integer idEquipe){
        return etudiantService.addAndAssignEtudiantToEquipeAndContract(e,idContrat,idEquipe);
    }


    @PutMapping("/{etudiantId}/departement/{departementId}")
    public void assignEtudiantToDepartement (@PathVariable Integer etudiantId,
                                             @PathVariable Integer departementId){
        etudiantService.assignEtudiantToDepartement(etudiantId,departementId);
    }

    @GetMapping("/departement/{idDepartement}")
    public List<Etudiant> getEtudiantsByDepartement (@PathVariable Integer idDepartement){
        return etudiantService.getEtudiantsByDepartement(idDepartement);
    }
}


