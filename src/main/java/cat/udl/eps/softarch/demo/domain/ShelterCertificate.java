package cat.udl.eps.softarch.demo.domain;

import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class ShelterCertificate extends UriEntity<Long> {

    @Id
    @GeneratedValue()
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    private ZonedDateTime expirationDate;

    @Override
    public Long getId() {
        return id;
    }
}
