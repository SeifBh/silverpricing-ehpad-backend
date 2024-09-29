package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Departement;
import fr.silverpricing.api.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServiceImpl implements DepartementService{
    @Autowired
    DepartementRepository departementRepository;
    @Override
    public List<Departement> getAllDepartements() {
       return departementRepository.findAll();
    }

    @Override
    public void createDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public Departement findByCodeDept(String deptcode) {
        return departementRepository.findByCodeDept(deptcode);
    }
}
