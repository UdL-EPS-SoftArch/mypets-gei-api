package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Shelter")
@Data
@EqualsAndHashCode(callSuper = true)
public class Shelter extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String mobile;

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    @PastOrPresent
    @NotNull
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;

    @NotNull
    private boolean isActive;

    private Integer rating;
}