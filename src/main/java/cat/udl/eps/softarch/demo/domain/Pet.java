package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Pet extends UriEntity<Long> {
    @Id
    @GeneratedValue
    Long id;

    String name;
    boolean isAdopted;
    String color;
    String size;
    Double weight;
    String age;
    String description;
    String breed;

    @OneToMany
    public MedicalRecord has;

    @OneToMany
    public Adoption wasAdopted;

    @ManyToOne
    public Shelter isIn;
}
