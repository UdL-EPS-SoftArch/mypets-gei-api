package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

}
