package fr.silverpricing.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prices", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
})
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
