package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.MedicalRecord;
import cat.udl.eps.softarch.demo.exceptions.UnauthorizedAccessException;
import cat.udl.eps.softarch.demo.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RepositoryEventHandler()
public class MedicalRecordEventHandler {

    final MedicalRecordRepository medicalRecordRepository;
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordEventHandler.class);

    public MedicalRecordEventHandler(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @HandleBeforeCreate
    public void handleMedicalRecordBeforeCreate(MedicalRecord medicalRecord) throws UnauthorizedAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isAuthorized(authentication)) {
            logger.error("Unauthorized attempt to create a medical record by user: {}", authentication != null ? authentication.getName() : "anonymous");
            throw new UnauthorizedAccessException();
        }

        logger.info("Authorized creation of a new medical record by user: {}", authentication.getName());
    }

    @HandleAfterSave
    public void handleMedicalRecordPostSave(MedicalRecord medicalRecord) {
        // Example of repository usage after saving a MedicalRecord
        // For instance, post-save validation or updating related entities
        logger.info("Medical record for pet {} saved successfully", medicalRecord.getPet().getName());
    }

    private boolean isAuthorized(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // Define the required authorities for creating a MedicalRecord
        List<String> requiredAuthorities = Arrays.asList("ROLE_SHELTER_VOLUNTEER", "ROLE_ADMIN");

        // Check if the authenticated user has any of the required authorities
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> requiredAuthorities.contains(grantedAuthority.getAuthority()));
    }

}
