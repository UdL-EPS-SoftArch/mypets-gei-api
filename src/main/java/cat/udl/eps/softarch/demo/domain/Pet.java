package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Id;

public class Pet extends UriEntity<Long> {
    @Id
    Long id;
    String name;
    boolean isAdopted;
    String color;
    String size;
    Double weight;
    String age;
    String description;
    String breed;

    @Override
    public Long getId() {
        return id;
    }
}
