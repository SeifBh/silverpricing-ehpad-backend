package fr.silverpricing.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.silverpricing.api.config.deserializer.LegalStatusDeserializer;
import fr.silverpricing.api.config.deserializer.ResidenceTypeDeserializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "residences", schema = "residence",uniqueConstraints = {
        @UniqueConstraint(columnNames = "_id"),
        @UniqueConstraint(columnNames = "noFinesset")
})
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    private String noFinesset;
    private String title;

    private String description;
    private String email;
    private String site;
    private String gestionnaire;
    @JsonDeserialize
    private Boolean dayCare;
    @JsonDeserialize
    private Boolean socialAssistance;
    @JsonDeserialize
    private Boolean alzheimer;
    private Integer capacity;




    @JsonDeserialize(using = LegalStatusDeserializer.class)
    private LegalStatus legal_status;
    @JsonDeserialize(using = ResidenceTypeDeserializer.class)
    private ResidenceType residenceType;
    private String taille;
    private String personnesAgeesId;
    private String cerfa;

    // Residence preferences
    private Boolean isViaTrajectoire;
    @JsonDeserialize
    private Boolean IsEHPAD;
    @JsonDeserialize
    private Boolean IsEHPA;
    @JsonDeserialize
    private Boolean IsESLD;
    @JsonDeserialize
    private Boolean IsRA;
    @JsonDeserialize
    private Boolean IsAJA;
    @JsonDeserialize
    private Boolean IsHCOMPL;
    @JsonDeserialize
    private Boolean IsHTEMPO;
    @JsonDeserialize
    private Boolean IsACC_JOUR;
    @JsonDeserialize
    private Boolean IsACC_NUIT;
    @JsonDeserialize
    private Boolean IsHAB_AIDE_SOC;
    @JsonDeserialize
    private Boolean IsCONV_APL;
    @JsonDeserialize
    private Boolean IsALZH;
    @JsonDeserialize
    private Boolean IsUHR;
    @JsonDeserialize
    private Boolean IsPASA;
    @JsonDeserialize
    private Boolean IsPUV;
    @JsonDeserialize
    private Boolean IsF1;
    @JsonDeserialize
    private Boolean IsF1Bis;
    @JsonDeserialize
    private Boolean IsF2;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinatesResidence;

    @JsonProperty("coordinates")
    private transient JsonNode coordinates;

    @JsonProperty("raPrice")
    private transient JsonNode raPrice;

    @JsonProperty("ehpadPrice")
    private transient JsonNode ehpadPrice;

    @ManyToOne
    private Groupe groupe;
    @ManyToOne
    private Departement departement;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;


}
