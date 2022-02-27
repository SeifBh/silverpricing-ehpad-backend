package fr.silverpricing.api.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity // this was commented
@Table(name = "prices")
public class Price implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
