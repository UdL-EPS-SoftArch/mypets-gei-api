package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Pet extends UriEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    
    String name;
    boolean isAdopted;
    String color;
    String size;
    Double weight;
    String age;
    String description;
    String breed;
    String img;

    @ManyToOne
    public Shelter isIn;
}
