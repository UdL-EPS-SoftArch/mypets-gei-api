package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Adoption;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.exceptions.InvalidPostRequest;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import cat.udl.eps.softarch.demo.exceptions.UnauthorizedAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;



public class ProcessAdoptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProcessAdoptionHandler.class);


    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_SHELTER_VOLUNTEER = "ROLE_SHELTER_VOLUNTEER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @HandleBeforeCreate
    public void handleAdoptionBeforeCreate(Adoption adoption) throws UnauthorizedAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if(!isAuthorized(authentication) ) {
            String userName = authentication != null ? authentication.getName() : "anonymous";
            String errorMessage = String.format("Unauthorized attempt to create an adoption by user: %s", userName);
            logger.error(errorMessage);
            throw new UnauthorizedAccessException();
        }
        else if (adoption.getPet().isAdopted() || adoption.getConfirmed()) {
            logger.error("Pet {} is already  or bad request", adoption.getPet().getName());
            throw new InvalidPostRequest();
        }

        adoption.getPet().setAdopted(true);
        logger.info("Adoption for pet {} created successfully by user {}", adoption.getPet().getName(), authentication.getName());
    }


    // This function allows to check if the user is authorized to perform the action
    private boolean isAuthorized(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        List<String> requiredAuthorities = Arrays.asList(ROLE_USER, ROLE_SHELTER_VOLUNTEER, ROLE_ADMIN);

        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> requiredAuthorities.contains(grantedAuthority.getAuthority()));
    }



}
