package fr.silverpricing.api.rest;

import fr.silverpricing.api.model.CategoryChambre;
import fr.silverpricing.api.model.Chambre;
import fr.silverpricing.api.model.Coordinates;
import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.repository.ResidenceRepository;
import fr.silverpricing.api.service.CoordinatesServiceImpl;
import fr.silverpricing.api.service.ResidenceService;
import fr.silverpricing.api.service.ResidenceServiceImpl;
import fr.silverpricing.api.service.graphql.GraphQLService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/residences")
@Slf4j
public class ResidenceController {

    @Autowired
    ResidenceRepository residenceRepository;
    @Autowired
    ResidenceServiceImpl residenceService;
    @Autowired
    ChambreController chambreController;
    @Autowired
    CoordinatesServiceImpl coordinatesService;
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
    @Transactional
    @PostMapping()
    public void createResidece(Residence residence) {
        log.info("Adding residence with finess #"+residence.getNoFinesset());
        residence.setResidenceType(residenceService.getResidenceType(residence));
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
        chambreController.createChambre(chambre);
        residenceRepository.save(residence);
    }

    public void updateResidence(String noFinesset){

    }


}