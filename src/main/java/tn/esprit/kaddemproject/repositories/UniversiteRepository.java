package tn.esprit.kaddemproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.kaddemproject.entities.Universite;

import java.util.Date;


public interface UniversiteRepository extends JpaRepository<Universite,Integer> {

   //float findByIdUnivAndDepartementsEtudiantsContratsDateFinContratAndDepartementsEtudiantsContratsdateFinContrat(int idUniv, Date dateD, Date dateF);

    float countUniversite_Departements_Etudiants_Contrats_montant_ContratByIdUnivOrderByDepartementsEtudiantsContratsSpecialite(int idUniv);


}
