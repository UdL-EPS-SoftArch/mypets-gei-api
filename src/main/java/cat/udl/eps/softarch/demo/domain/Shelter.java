package cat.udl.eps.softarch.demo.domain;

import cat.udl.eps.softarch.demo.domain.Location;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@Entity
@Table(name = "Shelter")
@Data
@EqualsAndHashCode(callSuper = true)
public class Shelter extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String mobile;

    @PastOrPresent
    @NotNull
    private ZonedDateTime createdAt;

    @PastOrPresent
    private ZonedDateTime updatedAt;

    @NotNull
    private boolean isActive;

    private Integer rating;

    @OneToOne
    public Location locatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
        isActive = false;
    }
}