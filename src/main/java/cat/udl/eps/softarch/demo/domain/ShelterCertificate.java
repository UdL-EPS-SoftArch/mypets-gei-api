package cat.udl.eps.softarch.demo.domain;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

    @NotNull
    private ZonedDateTime expirationDate;

    private Boolean validated;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Shelter shelterServed;

    @PrePersist()
    public void prePersist() {
        this.validated = false;
    }
}
