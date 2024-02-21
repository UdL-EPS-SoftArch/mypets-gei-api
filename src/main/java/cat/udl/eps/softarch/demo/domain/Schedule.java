package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Entity
@Table(name = "Schedule") //Avoid collision with system table User
@Data
@EqualsAndHashCode(callSuper = true)

public class Schedule extends UriEntity<Long> {

    @Id
    private long id;

    @NotNull
    private Date start;

    @NotNull
    private Date end;


    @Override
    public Long getId() {
        return id;
    }
}