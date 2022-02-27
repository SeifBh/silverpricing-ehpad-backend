package fr.silverpricing.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "raPrice")
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("raPrice")
public class RaPrice extends Price{
    @Column(nullable = true)
    private Float PrixF1;
    @Column(nullable = true)
    private Float PrixF1ASH;
    @Column(nullable = true)
    private Float PrixF1Bis;
    @Column(nullable = true)
    private Float PrixF1BisASH;
    @Column(nullable = true)
    private Float PrixF2;
    @Column(nullable = true)
    private Float PrixF2ASH;
    @Column(nullable = true,name="autreTarifPrest",columnDefinition="LONGTEXT")
    private String autreTarifPrest;
    @Column(nullable = true,name="prestObligatoire",columnDefinition="LONGTEXT")
    private String prestObligatoire;
    @Column(nullable = true)
    private Float prixMin;

}
