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
@Table(name = "groupes")
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
