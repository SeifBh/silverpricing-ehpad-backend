package fr.silverpricing.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chambres")
public class Chambre {
    @Id
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
    private Chambre chambre;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Chambre(String id, Chambre chambre, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.chambre = chambre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
