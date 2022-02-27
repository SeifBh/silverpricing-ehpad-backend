package fr.silverpricing.api.repository;

import fr.silverpricing.api.model.Chambre;
import fr.silverpricing.api.model.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
}
