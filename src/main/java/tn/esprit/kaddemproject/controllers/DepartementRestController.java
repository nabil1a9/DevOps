package tn.esprit.kaddemproject.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.generic.GenericController;
import tn.esprit.kaddemproject.services.IDepartementService;
import tn.esprit.kaddemproject.services.IDepartementServiceImpl;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController extends GenericController<Departement,Integer> {
    IDepartementServiceImpl departementService;
    // http://localhost:8089/departement/retrieve-all-departements
    @GetMapping("/retrieve-all-departements")
    public List<Departement> getDepartements() {
        List<Departement> listDepartements = departementService.retrieveAll();
        return listDepartements;
    }
    // http://localhost:8089/departement/retrieve-departement/8
    @GetMapping("/retrieve-departement/{departement-id}")
    public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }

    // http://localhost:8089/departement/add-departement
    @PostMapping("/add-departement")
    public Departement addDepartement(@RequestBody Departement d) {
        Departement departement = departementService.addDepartement(d);
        return departement;
    }

    // http://localhost:8089/departement/remove-departement/1
    @DeleteMapping("/remove-departement/{departement-id}")
    public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
        departementService.deleteDepartement(departementId);
    }

    // http://localhost:8089/departement/update-departement
    @PutMapping("/update-departement")
    public Departement updateDepartement(@RequestBody Departement e) {
        Departement departement= departementService.updateDepartement(e);
        return departement;
    }

}


