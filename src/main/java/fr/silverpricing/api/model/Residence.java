package fr.silverpricing.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "residences", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "noFinesset")
})
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String noFinesset;
    private String title;
    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chambre> chambres = new ArrayList<>();
    private String description;
    private String email;
    private String site;
    private String gestionnaire;
    private Boolean dayCare;
    private Boolean socialAssistance;
    private Boolean alzheimer;
    private Integer capacite;
    private LegalStatus legalStatus;
    @ManyToOne
    private Groupe gr;
    @ManyToOne
    private Departement departement;
    private ResidenceType residenceType;
    private String taille;
    private String personnesAgeesId;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}
