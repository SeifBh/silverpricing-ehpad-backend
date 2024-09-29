package fr.silverpricing.api.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ehpadPrice")
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("ehpadPrice")
@Getter
@Setter
public class EhpadPrice extends Price{
    @Column
    private Float prixHebPermCs;
    @Column
    private Float prixHebPermCd;
    @Column
    private Float prixHebPermCsa;
    @Column
    private Float prixHebPermCda;
    @Column
    private Float prixHebTempCs;
    @Column
    private Float prixHebTempCd;
    @Column
    private Float prixHebTempCsa;
    @Column
    private Float prixHebTempCda;
    @Column
    private Float tarifHebJour;
    @Column
    private Float tarifGir12;
    @Column
    private Float tarifGir34;
    @Column
    private Float tarifGir56;
    @Column(nullable = true,name="autrePrestation",columnDefinition="LONGTEXT")
    private String autrePrestation;
    @Column(nullable = true,name="autreTarifPrest",columnDefinition="LONGTEXT")
    private String autreTarifPrest;

    @Column
    private Float prixMin;


}
