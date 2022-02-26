package fr.silverpricing.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "residences", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "noFinesset")
})
@AllArgsConstructor
@Setter
@Getter
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String noFinesset;
    private String title;
    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chambre> chambres = new ArrayList<>();
    //@OneToMany(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Price> prices = new ArrayList<>();
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}
