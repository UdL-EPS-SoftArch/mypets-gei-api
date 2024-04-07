package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Adoption;
import cat.udl.eps.softarch.demo.exceptions.InvalidPostRequest;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import cat.udl.eps.softarch.demo.exceptions.UnauthorizedAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * This class defines event handlers for Adoption entity.
 */
@Component
@RepositoryEventHandler() // Ensure this handler is for MedicalRecord entity
public class AdoptionEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdoptionEventHandler.class);

    /**
     * These are the roles that are allowed to create an adoption.
     */
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_SHELTER_VOLUNTEER = "ROLE_SHELTER_VOLUNTEER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * Handles actions before creating an adoption.
     * @param adoption The adoption object to be created.
     * @throws UnauthorizedAccessException If the user is not authorized.
     * @throws InvalidPostRequest If the request is invalid.
     */
    @HandleBeforeCreate
    public void handleAdoptionBeforeCreate(Adoption adoption) throws UnauthorizedAccessException, InvalidPostRequest {

        checkAuthorization("POST");
        // If the pet is already adopted or the pet is null, an exception is thrown
        if (adoption.getPet() == null || adoption.getPet().isAdopted()  || adoption.getConfirmed()) {
            logger.error("Pet is already adopted or bad request");
            throw new InvalidPostRequest();
        }
        // If the adoption is successful, the adoption is in process

        logger.info("Adoption for pet {} created successfully ", adoption.getPet().getName());
    }

    /**
     * Handles actions before saving an adoption.
     * @param adoption The adoption object to be saved.
     * @throws UnauthorizedAccessException If the user is not authorized.
     */
    @HandleBeforeSave
    public void handleAdoptionBeforeSave(Adoption adoption) throws UnauthorizedAccessException {
        checkAuthorization("PUT");
        logger.info("Authorized save of adoption for pet {} ", adoption.getPet().getName());
    }
    // This function is called after editing an adoption
    @HandleAfterSave
    public void handleAdoptionAfterSave(Adoption adoption) throws UnauthorizedAccessException {
        adoption.getPet().setAdopted(true);
        logger.info("Pet {} adopted successfully", adoption.getPet().getName());
    }


    /**
     * Checks if the user is authorized to create or edit an adoption.
     * @param httpMethod The HTTP method used for the request.
     * @throws UnauthorizedAccessException If the user is not authorized.
     */
    private void checkAuthorization(String httpMethod) throws UnauthorizedAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isAuthorized(authentication, httpMethod)) {
            throw new UnauthorizedAccessException();
        }
    }

    /**
     * Checks if the user is authorized and checks the role of the user.
     * @param authentication The authentication object of the user.
     * @param HTTPMethod The HTTP method used for the request.
     * @return True if the user is authorized, false otherwise.
     */
    private boolean isAuthorized(Authentication authentication, String HTTPMethod) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        List<String> requiredAuthorities;

        if (HTTPMethod.equals("PUT")) {
            requiredAuthorities = Arrays.asList(ROLE_SHELTER_VOLUNTEER, ROLE_ADMIN);
        }
        else if (HTTPMethod.equals("POST")) {
            requiredAuthorities = Arrays.asList(ROLE_SHELTER_VOLUNTEER, ROLE_ADMIN, ROLE_USER);
        }
        else {
            requiredAuthorities = Arrays.asList(ROLE_ADMIN, ROLE_SHELTER_VOLUNTEER);
        }
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> requiredAuthorities.contains(grantedAuthority.getAuthority()));
    }



}
