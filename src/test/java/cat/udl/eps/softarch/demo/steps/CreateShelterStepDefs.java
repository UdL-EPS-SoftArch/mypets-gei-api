package cat.udl.eps.softarch.demo.steps;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.AdminRepository;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import cat.udl.eps.softarch.demo.repository.ShelterVolunteerRepository;
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
import java.time.ZonedDateTime;

public class CreateShelterStepDefs {
    @Autowired

    private StepDefs stepDefs;
    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShelterVolunteerRepository ShelterVolunteerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Given("^There is a registered admin with name \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredAdminWithNameAndPasswordAndEmail(String username, String password, String email) throws Throwable {
        if (!adminRepository.existsById(username)) {
            Admin user = new Admin();
            user.setEmail(email);
            user.setId(username);
            user.setPassword(password);
            user.encodePassword();
            adminRepository.save(user);

        }
    }

    @When("^I create a shelter with a name \"([^\"]*)\", email \"([^\"]*)\" and phone \"([^\"]*)\" and location \"([^\"]*)\"$")
    public void iCreateAShelterWithAName(String name, String email, String mobile, String location) throws Throwable {;
        Shelter newshelter = new Shelter();
        newshelter.setName(name);
        newshelter.setEmail(email);
        newshelter.setMobile(mobile);
        newshelter.setCreatedAt(ZonedDateTime.now());
        newshelter.setUpdatedAt(ZonedDateTime.now());
        //Following code is doing a post request to /shelters storing the response in stepDefs.result to test REST functionalty
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/shelters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(newshelter))
                        .characterEncoding(StandardCharsets.UTF_8)
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

    @Given("^There is a registered volunteer with name \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredVolunteerWithNameAndPasswordAndEmail(String name, String password, String email) {
        if(!ShelterVolunteerRepository.existsById(name)) {
            ShelterVolunteer volunteer = new ShelterVolunteer();
            volunteer.setEmail(email);
            volunteer.setId(name);
            volunteer.setPassword(password);
            volunteer.encodePassword();
            ShelterVolunteerRepository.save(volunteer);
        }
    }
}
