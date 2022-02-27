package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Coordinates;
import fr.silverpricing.api.model.Residence;

public interface CoordinatesService {
    void createCoordinates(Residence residence, Coordinates coordinates);
}
