package tn.esprit.kaddemproject.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Etudiant;
import tn.esprit.kaddemproject.entities.Niveau;
import tn.esprit.kaddemproject.generic.IGenericServiceImp;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@Service
public class IEquipeServiceImpl extends IGenericServiceImp<Equipe,Integer> implements IEquipeService{



    @Transactional
  //  @Scheduled(cron = "*/20 * * * * *")
    @Override
    public void faireEvoluerEquipes() {

        List<Equipe>  equipes = this.retrieveAll();

        /*for (Equipe equipe: equipes) {
            //find out if this team needs to be upgraded
            if(needsToUpgrade(equipe)){
                switch (equipe.getNiveau()){
                    case JUNIOR:
                        equipe.setNiveau(Niveau.SENIOR);
                        break;
                    case SENIOR:
                        equipe.setNiveau(Niveau.EXPERT);
                        break;
                }
            }
        }*/

        this.retrieveAll().stream()
                .filter(this::needsToUpgrade)
                .filter(equipe -> !equipe.getNiveau().equals(Niveau.EXPERT))
                .forEach(equipe ->{
                            switch (equipe.getNiveau()){
                                case JUNIOR : equipe.setNiveau(Niveau.SENIOR); break;
                                case SENIOR : equipe.setNiveau(Niveau.EXPERT); break;
                            }
                });

    }

    public Boolean needsToUpgrade(Equipe equipe){

       int nbContrat =  equipe.getEtudiants().stream()
                .map(Etudiant::getContrats)
                .flatMap(Collection::stream)
                .filter(contrat ->
                         contrat.getArchive().equals(false) &&
                         ChronoUnit.YEARS.between(contrat.getDateDebutContrat(),contrat.getDateFinContrat())> 1  )
                .map(Contrat::getEtudiant)
                .distinct()
                .collect(Collectors.toList())
                .size();

        return nbContrat>2;
    }

}