package cat.udl.eps.softarch.demo.handler;
import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.exceptions.VolunteerFromDifferentShelter;
import cat.udl.eps.softarch.demo.repository.ShelterVolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RepositoryEventHandler(ShelterVolunteer.class)
public class ShelterVolunteerEventHandler {

    final Logger logger = LoggerFactory.getLogger(ShelterVolunteerEventHandler.class);

    final ShelterVolunteerRepository shelterVolunteerRepository;

    public ShelterVolunteerEventHandler(ShelterVolunteerRepository shelterVolunteerRepository) {
        this.shelterVolunteerRepository = shelterVolunteerRepository;
    }

    @HandleBeforeDelete
    public void handleShelterVolunteerBeforeCreate(ShelterVolunteer volunteer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ShelterVolunteer requestVolunteer = shelterVolunteerRepository.findById(username).orElse(null);
        if(requestVolunteer!=null && Objects.equals(requestVolunteer.getUserShelter().getId(), volunteer.getUserShelter().getId())) {
            logger.info("Volunteer {} is deleting volunteer {}.", username, volunteer.getUsername());
        }
        else throw new VolunteerFromDifferentShelter();

        // Do something with the username, like associating it with the created user
    }
}
