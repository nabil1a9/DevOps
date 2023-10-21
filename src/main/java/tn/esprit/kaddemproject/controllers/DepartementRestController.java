package tn.esprit.kaddemproject.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.generic.GenericController;


@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController extends GenericController<Departement,Integer> {

}


