package tn.esprit.kaddemproject.services;


import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.generic.IGenericService;

import java.util.List;

public interface IEquipeService extends IGenericService<Equipe,Integer> {

    void faireEvoluerEquipes();

}
