package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Admin extends User{

    @Override
    @ElementCollection
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
    }
}
