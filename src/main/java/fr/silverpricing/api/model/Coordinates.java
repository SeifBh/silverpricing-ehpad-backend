package fr.silverpricing.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Coordinates {
    @Id
    @Column(name = "id")
    private Long id;
    private String title;
    private Boolean isPublished;
    private String street;
    private String postcode;
    private String deptcode;
    private String city;
    private String phone;
    private String emailContact;
    private String gestionnaire;
    private String website;
    private String latitude;
    private String longitude;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
