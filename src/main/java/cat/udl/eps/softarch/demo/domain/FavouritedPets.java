package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class FavouritedPets extends UriEntity<Long> {
    @Id
    @NotNull
    Long id;

    @ManyToOne
    Pet pet;

    @ManyToOne
    User user;
}
