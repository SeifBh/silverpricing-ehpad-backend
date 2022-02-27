package fr.silverpricing.api.rest;

import fr.silverpricing.api.model.*;
import fr.silverpricing.api.repository.ChambreRepository;
import fr.silverpricing.api.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/chambres")
public class ChambreController {

    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    PriceRepository priceRepository;

    @PostMapping
    public void createChambre(Chambre chambre) {
        Price price = new EhpadPrice();
        price.setChambre(chambre);
        switch (chambre.getCategoryChambre()){
            case EHPAD_CHAMBRE:
                 price = new EhpadPrice();
                ((EhpadPrice) price).setAutrePrestation("");
                ((EhpadPrice) price).setPrixMin(Float.valueOf(20));
            default:
                break;
            case RA_CHAMBRE:
                price = new RaPrice();
                price.setChambre(chambre);
                ((RaPrice) price).setPrixMin(Float.valueOf(50));

                break;
            case OTHERS:
                throw new RuntimeException(
                        "Cannot identify Type of residence");
        }
            priceRepository.save(price);
            chambreRepository.save(chambre);
    }
}
