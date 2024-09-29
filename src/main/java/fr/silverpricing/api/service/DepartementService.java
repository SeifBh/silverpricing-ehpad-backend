package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartementService  {
    List<Departement> getAllDepartements();
    void createDepartement(Departement departement);


    Departement findByCodeDept(String code);
}
