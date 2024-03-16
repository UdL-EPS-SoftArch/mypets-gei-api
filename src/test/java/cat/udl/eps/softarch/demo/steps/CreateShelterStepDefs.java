package cat.udl.eps.softarch.demo.steps;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import jakarta.validation.constraints.AssertTrue;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

public class CreateShelterStepDefs {
    @Autowired

    private StepDefs stepDefs;
    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private UserRepository userRepository;

    @And("^I am a admin with name \"([^\"]*)\"$")
    public void iAmAdmin(String name) {
        //Admin admin = userRepository.findByName(name).get(0);
    }
    @And("^I am a shelter volunteer with name \"([^\"]*)\"$")
    public void iAmAShelterVolunteer(User user) {
        Assert.assertTrue("User should be a shelter volunteer",
                user instanceof ShelterVolunteer);
    }
    @Given("^There is a registered admin with name \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredAdminWithNameAndPasswordAndEmail(String arg0, String arg1, String arg2) {

    }

    @When("^I create a shelter with a name \"([^\"]*)\" email \"([^\"]*)\" and mobile \"([^\"]*)\"$")
    public void iCreateAShelterWithAName(String name, String email, String mobile) throws Throwable {
        Shelter newshelter = new Shelter();
        newshelter.setName(name);
        newshelter.setEmail(email);
        newshelter.setMobile(mobile);
        //Following code is doing a post request to /shelters storing the response in stepDefs.result to test REST functionalty
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/shelters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(newshelter))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^There is (\\d+) Shelter created$")
    public void thereIsShelterCreated(int sheltersCreatedNum) {
        Assert.assertEquals("Shelters created", sheltersCreatedNum, shelterRepository.count());
    }

    @Given("^There is no shelter registered with the name \"([^\"]*)\"$")
    public void thereIsNoShelterRegisteredWithTheName(String name) {
        Assert.assertTrue("Shelter with name \""
                        + name + "\" shouldn't exist", shelterRepository.findByName(name).isEmpty());
    }
}
