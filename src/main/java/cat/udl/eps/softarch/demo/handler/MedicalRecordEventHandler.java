package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.MedicalRecord;
import cat.udl.eps.softarch.demo.exceptions.UnauthorizedAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RepositoryEventHandler() // Ensure this handler is for MedicalRecord entity
public class MedicalRecordEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordEventHandler.class);

    private static final String ROLE_SHELTER_VOLUNTEER = "ROLE_SHELTER_VOLUNTEER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * Handles actions before creating a medical record.
     * @param medicalRecord the medical record to be created
     * @throws UnauthorizedAccessException if the user is not authorized to create a medical record
     */
    @HandleBeforeCreate
    public void handleMedicalRecordBeforeCreate(MedicalRecord medicalRecord) throws UnauthorizedAccessException {
        checkAuthorization();
        logger.info("Authorized creation of a new medical record by user: {}", getCurrentUsername());
    }

    /**
     * Handles actions before saving (updating) a medical record.
     */
    @HandleBeforeSave
    public void handleMedicalRecordBeforeSave(MedicalRecord medicalRecord) throws UnauthorizedAccessException {
        checkAuthorization();
        logger.info("Authorized save of medical record by user: {}", getCurrentUsername());
    }

    /**
     * Handles actions after saving a medical record.
     * @param medicalRecord the saved medical record
     */
    @HandleAfterSave
    public void handleMedicalRecordPostSave(MedicalRecord medicalRecord) {
        logger.info("Medical record for pet {} saved successfully", medicalRecord.getPet().getName());
    }

    private void checkAuthorization() throws UnauthorizedAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isAuthorized(authentication)) {
            throw new UnauthorizedAccessException();
        }
    }

    private boolean isAuthorized(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        List<String> requiredAuthorities = Arrays.asList(ROLE_SHELTER_VOLUNTEER, ROLE_ADMIN);
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> requiredAuthorities.contains(grantedAuthority.getAuthority()));
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : "anonymous";
    }
}
