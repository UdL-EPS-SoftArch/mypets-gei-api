package cat.udl.eps.softarch.demo.domain;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialNetworks extends UriEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String instagram;
    String twitter;

    @ManyToOne
    Shelter belongsTo;

}
