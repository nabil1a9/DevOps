package tn.esprit.kaddemproject.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer idDepart;
    private String nomDepart;

    @OneToMany(mappedBy="departement")
    private List<Etudiant> etudiants;

}
