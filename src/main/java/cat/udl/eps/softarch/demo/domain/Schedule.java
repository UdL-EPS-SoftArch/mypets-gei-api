package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Schedule") //Avoid collision with system table User
@Data
@EqualsAndHashCode(callSuper = true)

public class Schedule extends UriEntity<Long> {

    @Id
    private Integer id;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime end;

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}