package fr.silverpricing.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "coordinates",uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
})
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @JsonIgnore
    @OneToOne(mappedBy = "coordinatesResidence")
    private Residence residence;
    @Column(nullable = true)
    private Boolean isPublished;
    @Column(nullable = true)
    private String street;
    @Column(nullable = true)
    private String postcode;
    @Column(nullable = true)
    private String deptcode;
    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private String phone;
    @Column(nullable = true)
    private String emailContact;
    @Column(nullable = true)
    private String gestionnaire;
    @Column(nullable = true,name="website",columnDefinition="LONGTEXT")
    private String website;
    @Column(nullable = true)
    private String latitude;
    @Column(nullable = true)
    private String longitude;
    @Column(nullable = true)
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

}
