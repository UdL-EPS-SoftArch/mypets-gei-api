package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class Adoption extends UriEntity<Long> {
    
    @Id
    private Long id;
    
    @NotBlank
    private String type;
    
    @NotNull
    private Boolean confirmed;
    
    @NotBlank
    private ZonedDateTime startDate;
    
    
    private ZonedDateTime endDate;
}
