package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class ShelterEventHandler {
    final ShelterRepository shelterRepository;

    public ShelterEventHandler(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @HandleBeforeCreate
    public void handleShelterPreCreate(Shelter shelter) {
        if (shelter.getCreatedAt() == null) {
            throw new IllegalArgumentException("Shelter must have a creation date");
        }
        if (shelter.getUpdatedAt() == null) {
            throw new IllegalArgumentException("Shelter must have an update date");
        }


    }
}
