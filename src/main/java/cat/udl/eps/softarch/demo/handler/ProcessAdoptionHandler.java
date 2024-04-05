package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Adoption;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import cat.udl.eps.softarch.demo.exceptions.UnauthorizedAccessException;

public class ProcessAdoptionHandler {
    private static final String ROLE_USER = "ROLE_USER";

    @HandleBeforeCreate
    public void handleAdoptionBeforeCreate(Adoption adoption) throws UnauthorizedAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }

}
