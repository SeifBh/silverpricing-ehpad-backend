package fr.silverpricing.api.rest;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.repository.ResidenceRepository;
import fr.silverpricing.api.service.ResidenceService;
import fr.silverpricing.api.service.ResidenceServiceImpl;
import fr.silverpricing.api.service.graphql.GraphQLService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/residences")
public class ResidenceController {

    @Autowired
    ResidenceRepository residenceRepository;
    @Autowired
    ResidenceServiceImpl residenceService;
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
    @PostMapping()
    public void createResidece(Residence residence) {
        residenceRepository.save(residence);
    }

    public void updateResidence(String noFinesset){

    }


}