package fr.silverpricing.api.rest;

import com.fasterxml.jackson.databind.JsonNode;
import fr.silverpricing.api.model.*;
import fr.silverpricing.api.repository.GroupeRepository;
import fr.silverpricing.api.repository.ResidenceRepository;
import fr.silverpricing.api.service.CoordinatesServiceImpl;
import fr.silverpricing.api.service.DepartementServiceImpl;
import fr.silverpricing.api.service.ResidenceServiceImpl;
import fr.silverpricing.api.service.graphql.GraphQLService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/residences")
@Slf4j
public class ResidenceController {

    public static final Long GROUPE_ID = 1L;
    @Autowired
    ResidenceRepository residenceRepository;
    @Autowired
    ResidenceServiceImpl residenceService;
    @Autowired
    ChambreController chambreController;
    @Autowired
    CoordinatesServiceImpl coordinatesService;
    @Autowired
    DepartementServiceImpl departementService;
    @Autowired
    GroupeRepository groupeRepository;
    @Autowired
    GraphQLService graphQLService;

    /**
     * Get residences from a graphQL Query
     * @param query
     * @return residences
     */
    @PostMapping("graphql")
    public ResponseEntity<Object> getResidencesFromExternal(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

    /**
     * Get All Local residences
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Residence>> getResidences() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(residenceRepository.findAll());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResidence(@PathVariable Long id) {
        residenceRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Residence has been deleted Succefully");
    }

    /**
     * create Internal residence
     * @param residence
     */
    @PostMapping()
    public void createResidece(Residence residence) {
        log.info("Adding residence with finess #"+residence.getNoFinesset());
        residence.setResidenceType(residenceService.getResidenceType(residence));
        residence.setDepartement(departementService.findByCodeDept(residence.getCoordinates().get("deptcode").asText()));
        Groupe defaultGroupe= groupeRepository.findById(GROUPE_ID).orElseThrow(() -> new EntityNotFoundException(String.valueOf(GROUPE_ID)));
        residence.setGroupe(defaultGroupe);
        Coordinates coordinates = new Coordinates();
        Chambre chambre = new Chambre();
        residence.setCoordinatesResidence(coordinates);
        coordinatesService.createCoordinates(residence,coordinates);
        switch (residenceService.getResidenceType(residence)){
            case EHPAD:
                chambre.setCategoryChambre(CategoryChambre.EHPAD_CHAMBRE);
                break;
            case NOT_EHPAD:
                chambre.setCategoryChambre(CategoryChambre.RA_CHAMBRE);
                break;
            case OTHER:
                chambre.setCategoryChambre(CategoryChambre.OTHERS);
                break;
        }
        residence.setChambre(chambre);
        chambreController.createChambre(chambre,residence);
        residenceRepository.save(residence);
    }

    /**
     * Update residence by noFinesset
     * @param noFinesset
     */
    public void updateResidence(String noFinesset){

    }


}