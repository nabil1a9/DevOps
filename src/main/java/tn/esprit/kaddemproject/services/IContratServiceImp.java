package tn.esprit.kaddemproject.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.kaddemproject.entities.Contrat;
import tn.esprit.kaddemproject.entities.Equipe;
import tn.esprit.kaddemproject.entities.Etudiant;
import tn.esprit.kaddemproject.generic.IGenericServiceImp;
import tn.esprit.kaddemproject.repositories.ContratRepository;
import tn.esprit.kaddemproject.repositories.EtudiantRepository;
import tn.esprit.kaddemproject.util.HelperClass;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IContratServiceImp extends IGenericServiceImp<Contrat,Integer> implements IContratService{

    private final ContratRepository contratRepository;
    private final EtudiantRepository etudiantRepository;



    @Override
    public Contrat affectContratToEtudiant(Integer idContrat, String nomE, String prenomE) {

        Contrat contrat = this.retrieveById(idContrat);
        Etudiant etudiant = etudiantRepository.findByNomEAndPrenomE(nomE,prenomE);
        if(contrat != null && etudiant!= null){

          int activeContract = 0;
          activeContract =  etudiant.getContrats().stream()
                    .filter(contrat1 -> contrat1.getArchive().equals(true))
                    .collect(Collectors.toList())
                    .size();

          activeContract = contratRepository.countByArchiveIsTrueAndEtudiantIdEtudiant(etudiant.getIdEtudiant());

          if(activeContract<5){
              contrat.setEtudiant(etudiant);
          }

        }

        return contrat;
    }

    @Override
    public Integer nbContratsValides(LocalDate startDate, LocalDate endDate) {
        log.info("IN method nbContratsValides");
        Integer var = contratRepository.countByArchiveIsFalseAndDateDebutContratAfterAndDateFinContratBefore(startDate,endDate);
        log.info("out of method nbContratsValides");
        return var;
    }

//    @Scheduled(cron = "*/30 * * * * ?")
    @Override
    public void retrieveAndUpdateStatusContrat() {

        // Archive all expired contracts
        this.archiveExpiredContracts();

        contratRepository.findByArchiveIsFalse().stream()
                .filter(contrat -> ChronoUnit.DAYS.between(LocalDate.now(),contrat.getDateFinContrat()) < 16 )
                .forEach(contrat ->
                        log.info(
                                "Contrat num: " +contrat.getIdContrat() +
                                " de l'etudiant " + contrat.getEtudiant().getNomE() + contrat.getEtudiant().getPrenomE() +
                                " expirera le " + contrat.getDateFinContrat() +
                                 " / "+ ChronoUnit.DAYS.between(LocalDate.now(),contrat.getDateFinContrat())
                        )
                );
    }

    @Transactional// Use this annotation to commit all managed entities without using the save function << contratRepository.save >>
    @Override
    public void archiveExpiredContracts() {
        contratRepository.findByArchiveIsFalseAndDateFinContrat(LocalDate.now())
                .stream()
                .forEach(contrat -> contrat.setArchive(true));
    }
}
