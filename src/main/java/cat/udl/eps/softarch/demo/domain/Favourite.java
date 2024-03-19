package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Favourite extends UriEntity<Long>{
    @GeneratedValue
    @Id
    Long id;

    @ManyToOne
    Pet pet;

    @ManyToOne
    User user;
}
