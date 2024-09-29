package fr.silverpricing.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
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
    @JsonIgnore
    @OneToOne(mappedBy = "chambre")
    private Residence residence;
    private String description;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;


}
