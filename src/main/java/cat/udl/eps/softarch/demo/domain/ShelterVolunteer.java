package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ShelterVolunteer extends User {

    @Override
    @JsonValue(value = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_VOLUNTEER");
    }

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Shelter userShelter;
}
