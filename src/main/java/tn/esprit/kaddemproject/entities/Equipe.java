package tn.esprit.kaddemproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipe implements Serializable{
    public Equipe(Integer idEquipe)
    {
        this.idEquipe= idEquipe;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @ManyToMany(mappedBy="equipes")
    @JsonIgnore
    private List<Etudiant> etudiants ;

    @OneToOne
    private DetailEquipe detailEquipe;


}
