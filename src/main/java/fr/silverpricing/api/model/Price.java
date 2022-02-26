package fr.silverpricing.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "prices", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
})
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
