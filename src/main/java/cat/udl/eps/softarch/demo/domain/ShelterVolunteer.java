package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ShelterVolunteer extends User {
    @Id
    private long Id;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Shelter userShelter;
}
