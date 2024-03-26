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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;

public class ShelterVolunteerStepDefs {

    @Autowired
    ShelterVolunteerRepository shelterVolunteerRepository;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    ShelterRepository shelterRepository;


    @And("There is a shelter volunteer with username \"([^\"]*)\" and password \"([^\"]*)\" in the shelter \"([^\"]*)\"$")
    public void thereIsAShelterVolunteerWithUsernameInTheShelter(String username, String password, String shelterName) {
        if (!shelterVolunteerRepository.existsById(username)) {
            ShelterVolunteer volunteer = new ShelterVolunteer();
            volunteer.setUserShelter(shelterRepository.findByName(shelterName).get(0));
            volunteer.setId(username);
            volunteer.setEmail(username + "@volunteer");
            volunteer.setPassword(password);
            volunteer.encodePassword();
            shelterVolunteerRepository.save(volunteer);
        }

    }

    @When("I kick user \"([^\"]*)\" from shelter \"([^\"]*)\"$")
    public void iKickUserFromShelter(String username, String shelterName) throws Exception {

        ShelterVolunteer volunteer = shelterVolunteerRepository.findById(username).orElse(null);

        assert volunteer != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/shelterVolunteers/{id}", volunteer.getId())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());


    }
}
