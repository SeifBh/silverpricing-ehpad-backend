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
@Table(name = "chambres")
public class Chambre {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
    private Chambre chambre;
    private CategoryChambre categoryChambre;
    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Price> prices = new ArrayList<>();
    private String description;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}
