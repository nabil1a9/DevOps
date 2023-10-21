package tn.esprit.kaddemproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.kaddemproject.entities.Departement;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement,Integer> {

    //JPQL
    @Query("SELECT u.departements " +
            "FROM Universite u " +
            "where u.idUniv = :idUniversite")
    List<Departement> retrieveDepartementsByUniversiteJPQL(@Param("idUniversite") Integer idUniversite);

    //SQL
	@Query(value="SELECT *" +
            " FROM departement d" +
            " JOIN universite_departements ud " +
            "  ON d.id_depart = ud.departements_id_depart " +
            " JOIN universite u " +
            "  ON ud.universite_id_univ = u.id_univ " +
            " WHERE u.id_univ=1;",nativeQuery =true)
    List<Departement> retrieveDepartementsByUniversiteSQL(@Param("idUniversite") Integer idUniversite);

    //JPQL
    @Query("SELECT d " +
            "FROM Departement d " +
            "ORDER BY size(d.etudiants) DESC")
    List<Departement> getDepartementByStudentsNbrJPQL();

    //SQL
    @Query(value ="SELECT * " +
            "FROM departement d " +
            "ORDER BY ((select COUNT(*) FROM etudiant e WHERE e.departement_id_depart = d.id_depart)) DESC; ", nativeQuery = true)
    List<Departement> getDepartementByStudentsNbrSQL();




}
