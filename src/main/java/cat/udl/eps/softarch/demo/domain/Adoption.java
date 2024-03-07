package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Adoption extends UriEntity<Long> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String type;
    
    @NotNull
    private Boolean confirmed;
    
    @NotNull
    private ZonedDateTime startDate;
    
    
    private ZonedDateTime endDate;

    @ManyToOne
    private User user; //Adopter
    
    @ManyToOne
    private Pet pet;
}
