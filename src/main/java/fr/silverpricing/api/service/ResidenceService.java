package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.model.ResidenceType;

public interface ResidenceService {
    ResidenceType getResidenceType(Residence residence);
    String getResidenceCodeDepartement(Residence residence);

}
