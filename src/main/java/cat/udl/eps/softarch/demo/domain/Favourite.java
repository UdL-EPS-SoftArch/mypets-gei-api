package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Favourite extends UriEntity<Long> {
    @Id
    @GeneratedValue
    Long id;

    String test;

    @ManyToOne
    Pet pet;

    @ManyToOne
    User user;
}
