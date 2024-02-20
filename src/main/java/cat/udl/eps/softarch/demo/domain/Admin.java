package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Id;

public class Admin extends UriEntity<Long>{
    @Id
    Long id;

    @Override
    public Long getId() {
        return id;
    }
}
