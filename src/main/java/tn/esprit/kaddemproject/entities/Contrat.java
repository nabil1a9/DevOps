package tn.esprit.kaddemproject.entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@ToString
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
//@Table(name = "contracts")
public class Contrat implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // we use this annotation to suppress the setter for idContrat field.
    private Integer idContrat;
    /*
    when dealing with LocalDate, @Temporal annotation is not needed.
    It was needed for java.util.Date

    //@Temporal(TemporalType.DATE)
    private java.util.Date dateDebutContrat;
    */

    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;

    @ManyToOne
    private Etudiant etudiant;


}
