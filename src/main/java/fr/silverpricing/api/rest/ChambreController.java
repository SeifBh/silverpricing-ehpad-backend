package fr.silverpricing.api.rest;

import com.fasterxml.jackson.databind.JsonNode;
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
    public void createChambre(Chambre chambre,Residence residence) {
        Price price = new EhpadPrice();
        price.setChambre(chambre);
        switch (chambre.getCategoryChambre()){
            case EHPAD_CHAMBRE:
                 price = new EhpadPrice();
                ((EhpadPrice) price).setPrixHebPermCs(returnPrice(residence.getEhpadPrice(),"prixHebPermCs"));
                ((EhpadPrice) price).setPrixHebPermCd(returnPrice(residence.getEhpadPrice(),"prixHebPermCd"));
                ((EhpadPrice) price).setPrixHebPermCsa(returnPrice(residence.getEhpadPrice(),"prixHebPermCsa"));
                ((EhpadPrice) price).setPrixHebPermCda(returnPrice(residence.getEhpadPrice(),"prixHebPermCda"));
                ((EhpadPrice) price).setPrixHebTempCs(returnPrice(residence.getEhpadPrice(),"prixHebTempCs"));
                ((EhpadPrice) price).setPrixHebTempCd(returnPrice(residence.getEhpadPrice(),"prixHebTempCd"));
                ((EhpadPrice) price).setPrixHebTempCsa(returnPrice(residence.getEhpadPrice(),"prixHebTempCsa"));
                ((EhpadPrice) price).setPrixHebTempCda(returnPrice(residence.getEhpadPrice(),"prixHebTempCda"));
                ((EhpadPrice) price).setTarifHebJour(returnPrice(residence.getEhpadPrice(),"tarifHebJour"));
                ((EhpadPrice) price).setTarifGir12(returnPrice(residence.getEhpadPrice(),"tarifGir12"));
                ((EhpadPrice) price).setTarifGir34(returnPrice(residence.getEhpadPrice(),"tarifGir34"));
                ((EhpadPrice) price).setTarifGir56(returnPrice(residence.getEhpadPrice(),"tarifGir56"));
                ((EhpadPrice) price).setAutrePrestation(String.valueOf(residence.getEhpadPrice().get("autrePrestation")));
            default:
                break;
            case RA_CHAMBRE:
                price = new RaPrice();
                ((RaPrice) price).setPrixF1(returnPrice(residence.getRaPrice(),"PrixF1"));
                ((RaPrice) price).setPrixF1ASH(returnPrice(residence.getRaPrice(),"PrixF1ASH"));
                ((RaPrice) price).setPrixF1Bis(returnPrice(residence.getRaPrice(),"PrixF1Bis"));
                ((RaPrice) price).setPrixF1BisASH(returnPrice(residence.getRaPrice(),"PrixF1BisASH"));
                ((RaPrice) price).setPrixF2(returnPrice(residence.getRaPrice(),"PrixF2"));
                ((RaPrice) price).setPrixF2ASH(returnPrice(residence.getRaPrice(),"PrixF2ASH"));
                ((RaPrice) price).setAutreTarifPrest(String.valueOf(residence.getRaPrice().get("autreTarifPrest")));
                ((RaPrice) price).setPrestObligatoire(String.valueOf(residence.getRaPrice().get("prestObligatoire")));
                price.setChambre(chambre);

                break;
            case OTHERS:
                break;
        }
            priceRepository.save(price);
            chambreRepository.save(chambre);
    }

    private Float returnPrice(JsonNode jsonNode,String item){
        return !jsonNode.get(item).asText().equals("null") ? Float.valueOf(jsonNode.get(item).asText()) : 0L;
    }
}
