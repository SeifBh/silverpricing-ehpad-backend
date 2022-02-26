package fr.silverpricing.api.repository;

import fr.silverpricing.api.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {


}