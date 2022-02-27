package fr.silverpricing.api.runner;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.repository.ResidenceRepository;
import fr.silverpricing.api.rest.ExternalApiController;
import fr.silverpricing.api.rest.ResidenceController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@ConditionalOnProperty(
        value="app.cron.enabled",
        havingValue = "true")
public class scheduleTaskUsingCronExpression {



    @Autowired
    private ResidenceRepository residenceRepository;

    @Autowired
    private ExternalApiController externalApiController;

    @Autowired
    private ResidenceController residenceController;
    @Value("${app.cron.enabled}")
    private String cronStatus ;



    @Transactional
    //@Scheduled(cron = "${app.cron.time}", zone = "Europe/Paris")
    @Scheduled(fixedDelay = 200000)
    public void scheduleScanningUpdatedResidencesUsingCronExpression() {
        log.info("cron updating resiendce = "+cronStatus );
        log.info("schedule tasks using cron jobs");

        List<Residence> sourceResidences = externalApiController.getExternalResidences();
        List<Residence> localResidences = residenceRepository.findAll();

        try {
            sourceResidences.stream()
                    .forEach((residence) -> {
                        if(localResidences.stream()
                                .map(Residence::getNoFinesset).collect(Collectors.toList()).contains(residence.getNoFinesset())){
                            log.info("Already exist");
                            log.warn("Check if needs updates");
                        }else{
                           log.info("residence with finess "+residence.getNoFinesset()+" does not exist");

                            residenceController.createResidece(residence);
                        }
                    });

        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    private void checkUpdate(String noFinesset) {
        residenceController.updateResidence(noFinesset);
    }


}
