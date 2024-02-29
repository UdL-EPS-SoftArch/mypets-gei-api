package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Schedule extends UriEntity<Long> {

    @Id
    private Long id;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime finish;

    @ManyToOne
    public Shelter available;
}
