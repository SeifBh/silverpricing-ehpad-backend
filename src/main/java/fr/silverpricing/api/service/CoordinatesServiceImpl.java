package fr.silverpricing.api.service;

import fr.silverpricing.api.model.Coordinates;
import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.repository.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoordinatesServiceImpl implements CoordinatesService{
    @Autowired
    CoordinatesRepository coordinatesRepository;
    @Override
    public void createCoordinates(Residence residence, Coordinates coordinates) {
        try{
            //coordinates.setId(Long.valueOf(String.valueOf(residence.getCoordinates().get("_id"))));
            coordinates.setTitle(String.valueOf(residence.getCoordinates().get("title")));
            coordinates.setIsPublished(Boolean.valueOf(String.valueOf(residence.getCoordinates().get("isPublished"))));
            coordinates.setIsPublished(Boolean.valueOf(String.valueOf(residence.getCoordinates().get("isPublished"))));
            coordinates.setStreet(String.valueOf(residence.getCoordinates().get("street")));
            coordinates.setPostcode(String.valueOf(residence.getCoordinates().get("postcode")));
            coordinates.setDeptcode(String.valueOf(residence.getCoordinates().get("deptcode")));
            coordinates.setCity(String.valueOf(residence.getCoordinates().get("city")));
            coordinates.setWebsite(String.valueOf(residence.getCoordinates().get("website")));
            coordinates.setPhone(String.valueOf(residence.getCoordinates().get("phone")));
            coordinates.setEmailContact(String.valueOf(residence.getCoordinates().get("emailContact")));
            coordinates.setGestionnaire(String.valueOf(residence.getCoordinates().get("gestionnaire")));
            coordinates.setLongitude(String.valueOf(residence.getCoordinates().get("latitude")));
            coordinates.setLatitude(String.valueOf(residence.getCoordinates().get("longitude")));
            coordinatesRepository.save(coordinates);
        }
        catch (NullPointerException npe){
            throw new NullPointerException(npe.getMessage());
        }


    }
}
