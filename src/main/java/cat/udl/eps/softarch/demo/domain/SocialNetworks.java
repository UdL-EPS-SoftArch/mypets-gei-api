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
    private Long id;
    private String username;
    private String type;
    private String url;

    @ManyToOne
    public Shelter belongsTo;


}
