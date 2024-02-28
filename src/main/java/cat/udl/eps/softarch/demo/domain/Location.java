package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Location extends UriEntity<Long>{
    @Id
    private Long id;

    private String address;

    private float latitude;

    private float longitude;

    private String province;

    private String municipality;

    private String postalCode;

    @OneToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

}
