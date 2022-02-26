package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.model.ResidenceType;
import org.springframework.stereotype.Service;

@Service
public class ResidenceServiceImpl implements ResidenceService{


    @Override
    public ResidenceType getResidenceType(Residence residence) {
       if(residence.getIsRA()){
           return ResidenceType.NOT_EHPAD;
       }
       else if(residence.getIsEHPAD()){
           return ResidenceType.EHPAD;
       }
       return ResidenceType.OTHER;
    }
}
