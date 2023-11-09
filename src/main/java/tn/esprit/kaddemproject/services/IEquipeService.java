package tn.esprit.kaddemproject.services;


import org.springframework.transaction.annotation.Transactional;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.generic.IGenericService;

import java.util.List;

public interface IEquipeService extends IGenericService<Equipe,Integer> {
    @Transactional
  //  @Scheduled(cron = "*/20 * * * * *")
    void faireEvoluerEquipes();
    public Equipe addEquipe(Equipe equipe);
    public Equipe updateEquipe(Equipe equipe);
    public Equipe retrieveEquipeById(Integer id);
    public List<Equipe> retrieveAllEquipes();
    public Boolean deleteEquipe(Integer id);




}
