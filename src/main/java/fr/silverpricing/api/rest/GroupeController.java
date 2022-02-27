package fr.silverpricing.api.rest;

import fr.silverpricing.api.model.Groupe;
import fr.silverpricing.api.repository.GroupeRepository;
import fr.silverpricing.api.service.GroupeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/groupes")
public class GroupeController  {

    @Autowired
    GroupeServiceImpl groupeService;

    @Autowired
    GroupeRepository groupeRepository;

    @PostMapping()
    public ResponseEntity<Groupe> createTutorial(@RequestBody Groupe groupe) {
        try {
            Groupe _groupe = groupeRepository.save(groupe);
            return new ResponseEntity<>(_groupe, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
