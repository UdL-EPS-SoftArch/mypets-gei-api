package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ShelterVolunteer extends User {
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Shelter userShelter;
}
