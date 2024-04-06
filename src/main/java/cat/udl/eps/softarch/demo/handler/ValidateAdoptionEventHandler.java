package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Adoption;
import cat.udl.eps.softarch.demo.exceptions.InvalidPostRequest;
import cat.udl.eps.softarch.demo.exceptions.UnauthorizedAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
@RepositoryEventHandler()
public class ValidateAdoptionEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(ValidateAdoptionEventHandler.class);

    // These are the roles that are allowed to create an adoption
    private static final String ROLE_SHELTER_VOLUNTEER = "ROLE_SHELTER_VOLUNTEER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    // This function is called before creating an adoption
    @HandleBeforeSave
    public void handleAdoptionBeforeCreate(Adoption adoption) throws UnauthorizedAccessException, InvalidPostRequest {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is not authorized, an exception is thrown
        if(!isAuthorized(authentication) ) {
            String userName = authentication != null ? authentication.getName() : "anonymous";
            String errorMessage = String.format("Unauthorized attempt to create an adoption by user: %s", userName);
            logger.error(errorMessage);
            throw new UnauthorizedAccessException();
        }

        logger.info("Adoption for pet {} updated successfully by Admin {}", adoption.getPet().getName(), authentication.getName());
    }


    // This function allows to check if the user is authorized to perform the action
    private boolean isAuthorized(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        List<String> requiredAuthorities = Arrays.asList(ROLE_SHELTER_VOLUNTEER, ROLE_ADMIN);

        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> requiredAuthorities.contains(grantedAuthority.getAuthority()));
    }



}
