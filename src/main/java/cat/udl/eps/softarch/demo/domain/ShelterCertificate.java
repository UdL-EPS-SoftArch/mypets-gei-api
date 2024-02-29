package cat.udl.eps.softarch.demo.domain;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ShelterCertificate extends UriEntity<Long> {

    @Id
    @GeneratedValue()
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    private ZonedDateTime expirationDate;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Shelter shelterServed;
}
