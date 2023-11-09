package tn.esprit.kaddemproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.kaddemproject.entities.Departement;
import tn.esprit.kaddemproject.generic.IGenericServiceImp;
import tn.esprit.kaddemproject.repositories.DepartementRepository;

import java.util.List;


@Service
public class IDepartementServiceImpl extends IGenericServiceImp<Departement,Integer> implements IDepartementService{

    @Autowired
    DepartementRepository departementRepository;
    public List<Departement> retrieveAllDepartements(){
        return (List<Departement>) departementRepository.findAll();
    }

    public Departement addDepartement (Departement d){
        return departementRepository.save(d);
    }

    public   Departement updateDepartement (Departement d){
        return departementRepository.save(d);
    }

    public  Departement retrieveDepartement (Integer idDepart){
        return departementRepository.findById(idDepart).get();
    }
    public  void deleteDepartement(Integer idDepartement){
        Departement d=retrieveDepartement(idDepartement);
        departementRepository.delete(d);
    }
}
