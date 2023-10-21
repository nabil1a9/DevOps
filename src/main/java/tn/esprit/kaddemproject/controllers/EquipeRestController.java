package tn.esprit.kaddemproject.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Etudiant;
import tn.esprit.kaddemproject.generic.GenericController;
import tn.esprit.kaddemproject.services.IEtudiantService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController extends GenericController<Equipe,Integer> {



}


