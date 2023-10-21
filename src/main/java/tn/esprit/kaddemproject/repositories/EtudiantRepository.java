package tn.esprit.kaddemproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.kaddemproject.entities.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {
//   List<Etudiant> findEtudiantsByDepartement_IdDepart(Integer idDepart);
//    @Query("Select e From Etudiant e where e.nomE= :nomE and e.prenomE= :prenomE")
//   Etudiant findByNomEAndPrenomE(@Param("nomE") String nomE, @Param("prenomE") String prenomE);

     List<Etudiant> findByDepartementIdDepart(Integer idDepartement);
     Etudiant findByNomEAndPrenomE(String nom, String prenom);


}
