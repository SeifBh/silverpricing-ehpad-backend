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
@Table(name = "chambres",uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
})
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private CategoryChambre categoryChambre;
    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Price> prices = new ArrayList<>();
    @OneToOne(mappedBy = "chambre")
    private Residence residence;
    private String description;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}
