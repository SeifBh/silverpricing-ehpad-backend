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
    private Float price;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
