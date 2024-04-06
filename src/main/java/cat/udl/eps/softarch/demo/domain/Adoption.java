package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonIdentityReference(alwaysAsId = true) // Only serialize the pet ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user; //Adopter


    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true) // Only serialize the pet ID
    @JsonInclude(JsonInclude.Include.NON_NULL) // Include pet only if it's not null
    private Pet pet;


}
