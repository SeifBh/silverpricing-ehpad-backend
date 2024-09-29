package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Groupe;

import java.util.List;

public interface GroupeService {
    void createGroupe(Groupe groupe);
    List<Groupe> getAllGroupes();
}
