package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import cat.udl.eps.softarch.demo.repository.ShelterVolunteerRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;

public class ShelterVolunteerStepDefs {

    @Autowired
    ShelterVolunteerRepository shelterVolunteerRepository;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    ShelterRepository shelterRepository;


    @And("There is a shelter volunteer with username \"([^\"]*)\" in the shelter \"([^\"]*)\"$")
    public void thereIsAShelterVolunteerWithUsernameInTheShelter(String username, String shelterName) {
        ShelterVolunteer volunteer = new ShelterVolunteer();
        volunteer.setUserShelter(shelterRepository.findByName(shelterName).get(0));
        volunteer.setId(username);
        volunteer.setEmail("volunteeer@volunteer.com");
        volunteer.setPassword("12345678");
        shelterVolunteerRepository.save(volunteer);

    }

    @When("I kick user \"([^\"]*)\" from shelter \"([^\"]*)\"$")
    public void iKickUserFromShelter(String username, String shelterName) throws Exception {
        Optional<ShelterVolunteer> optionalVolunteer = shelterVolunteerRepository.findById(username);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/shelterVolunteer/{id}", optionalVolunteer.isPresent() ? optionalVolunteer.get().getId() : -1)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());

    }
}
