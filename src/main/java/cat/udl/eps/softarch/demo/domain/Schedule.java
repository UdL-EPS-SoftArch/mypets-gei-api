package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

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

}