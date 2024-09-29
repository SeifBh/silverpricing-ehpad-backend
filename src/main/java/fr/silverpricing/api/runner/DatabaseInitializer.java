package fr.silverpricing.api.runner;

import fr.silverpricing.api.model.Departement;
import fr.silverpricing.api.model.Groupe;
import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.model.User;
import fr.silverpricing.api.rest.ExternalApiController;
import fr.silverpricing.api.security.WebSecurityConfig;
import fr.silverpricing.api.service.DepartementService;
import fr.silverpricing.api.service.GroupeServiceImpl;
import fr.silverpricing.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final DepartementService departementService;
    private final PasswordEncoder passwordEncoder;
    private final ExternalApiController externalApiController;
    private final GroupeServiceImpl groupeService;

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        if (!departementService.getAllDepartements().isEmpty()) {
            return;
        }
        if (!groupeService.getAllGroupes().isEmpty()) {
            return;
        }
        log.info("Preparing departemetns ...");
        List<Departement> departemtns = externalApiController.getListDepartements();
        departemtns.forEach(dep->{
            departementService.createDepartement(dep);
        });
        log.info("Preparing groupes ...");
        List<Residence> emptyResidences = new ArrayList<>();
        groupeService.createGroupe(new Groupe(1l,"Default","Static description",emptyResidences,new Date().toInstant(),new Date().toInstant()));
        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        });
        log.info("Database initialized");
    }

    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN),
            new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER)
    );
}
