package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Adoption extends UriEntity<Long> {
    
    @Id
    private Long id;
    
    @NotBlank
    private String type;
    
    @NotBlank
    private Boolean confirmed;
    
    @NotBlank
    private Date startDate;
    
    
    private Date endDate;
}
