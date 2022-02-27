package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Groupe;
import fr.silverpricing.api.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeServiceImpl implements GroupeService{

    @Autowired
    GroupeRepository groupeRepository;
    @Override
    public void createGroupe(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }
}
