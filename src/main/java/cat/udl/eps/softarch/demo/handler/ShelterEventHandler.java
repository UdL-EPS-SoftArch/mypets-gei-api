package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RepositoryEventHandler
public class ShelterEventHandler {
    final ShelterRepository shelterRepository;

    public ShelterEventHandler(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @HandleBeforeCreate
    public void handleShelterPreCreate(Shelter shelter) {
       shelter.setCreatedAt(ZonedDateTime.now());
       shelter.setUpdatedAt(ZonedDateTime.now());

    }
    @HandleAfterSave
    public void handleShelterPostSave(Shelter shelter) {
        shelter.setUpdatedAt(ZonedDateTime.now());
    }
}
