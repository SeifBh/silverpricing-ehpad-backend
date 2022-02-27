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
    @Column
    private Float PrixF1;
    @Column
    private Float PrixF1ASH;
    @Column
    private Float PrixF1Bis;
    @Column
    private Float PrixF1BisASH;
    @Column
    private Float PrixF2;
    @Column
    private Float PrixF2ASH;
    @Column
    private String autreTarifPrest;
    @Column
    private String prestObligatoire;
    @Column
    private Float prixMin;

}
