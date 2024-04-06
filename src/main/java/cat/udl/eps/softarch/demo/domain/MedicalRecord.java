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
public class MedicalRecord extends UriEntity<Long> {

    @Id
    @GeneratedValue()
    private Long id;

    @NotBlank
    private String issue;

    @NotBlank
    private String description;

    @NotNull
    private ZonedDateTime date;
    
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true) // Only serialize the pet ID
    @JsonInclude(JsonInclude.Include.NON_NULL) // Include pet only if it's not null
    private Pet pet;
}