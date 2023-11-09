package tn.esprit.kaddemproject.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Etudiant;
import tn.esprit.kaddemproject.generic.GenericController;
import tn.esprit.kaddemproject.services.IEquipeServiceImpl;
import tn.esprit.kaddemproject.services.IEtudiantService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController extends GenericController<Equipe,Integer> {

        @Autowired
        private IEquipeServiceImpl equipeService;

        @PostMapping
        public Equipe addEquipe(@RequestBody Equipe equipe) {
            return equipeService.addEquipe(equipe);
        }

        @PutMapping
        public Equipe updateEquipe(@RequestBody Equipe equipe) {
            return equipeService.updateEquipe(equipe);
        }

        @GetMapping("/{id}")
        public Equipe retrieveEquipeById(@PathVariable Integer id) {
            return equipeService.retrieveEquipeById(id);
        }

        @GetMapping
        public List<Equipe> retrieveAllEquipes() {
            return equipeService.retrieveAllEquipes();
        }

        @DeleteMapping("/{id}")
        public Boolean deleteEquipe(@PathVariable Integer id) {
            return equipeService.deleteEquipe(id);
        }

        @PostMapping("/faireEvoluerEquipes")
        public void faireEvoluerEquipes() {
            equipeService.faireEvoluerEquipes();
        }
    }




