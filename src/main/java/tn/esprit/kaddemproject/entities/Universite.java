package tn.esprit.kaddemproject.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Universite implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer idUniv;
    private String nomUniv;

    @OneToMany
    private List<Departement> departements;

}
