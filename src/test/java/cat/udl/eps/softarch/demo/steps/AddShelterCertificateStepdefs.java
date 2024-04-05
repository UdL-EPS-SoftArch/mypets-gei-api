package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AddShelterCertificateStepdefs {

    @Autowired()
    private ShelterRepository shelterRepository;

    @Autowired()
    private StepDefs stepDefs;

    @When("I add a Shelter Certificate with valid expiration date to the shelter with name {string}")
    public void iAddAShelterCertificateWithNameAndDescriptionToTheShelterWithName(String shelterName) throws Exception {
        Shelter shelter = this.shelterRepository.findByName(shelterName).get(0);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/shelterCertificates/", shelterName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject()
                                .put("expirationDate", ZonedDateTime.now().plusMonths(1))
                                .put("shelterServed", "/shelter/"+shelter.getId())
                                .toString()
                                .getBytes(StandardCharsets.UTF_8))
                        .with(AuthenticationStepDefs.authenticate()));
    }
}
