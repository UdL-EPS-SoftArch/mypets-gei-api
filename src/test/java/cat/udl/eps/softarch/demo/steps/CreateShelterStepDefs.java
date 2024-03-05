package cat.udl.eps.softarch.demo.steps;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.domain.User;
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

    private StepDefs stepDefs;
    private ShelterRepository shelterRepository;
    @Given("^There is no shelter registered with the name \"([^\"]*)\" and location \"([^\"]*)\"$")
    public void thereIsNoShelterRegisteredWithTheNameAndLocation(String name, Location location) {//Location aqui ha de ser Strind o Tipus location? tu fas post de nomes l'id de location o de tot el objecte?
        Assert.assertTrue("Shelter with name \""
                + name + "\" and location \"" + location + "\" shouldn't exist", shelterRepository.findByLocatedAtAndName(location, name).isEmpty());
    }

    @And("I am a shelter volunteer")
    public void iAmAShelterVolunteer(User user) {
        Assert.assertTrue("User should be a shelter volunteer",
                user instanceof ShelterVolunteer);
    }

    @When("I create a new shelter with name {string} and location {string}")
    public void iAttemptToCreateANewShelterWithNameAndLocation(String name, Location location) {
            Shelter shelter = new Shelter();
            shelter.setName(name);
            shelter.setLocatedAt(location);

        try {
            stepDefs.result = stepDefs.mockMvc.perform(
                    post("/shelters")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new JSONObject( stepDefs.mapper.writeValueAsString(shelter)
                            ).put("name", name).toString())
                            .characterEncoding(StandardCharsets.UTF_8)
                            .accept(MediaType.APPLICATION_JSON)
                            .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @And("It has been created a user with username \"shelter\" and location \"city\", the password is not returned")
    public void itHasBeenCreatedShelterWithLocation(String name, Location location) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/shelters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject()
                                .put("name", name)
                                .put("location", location)
                                .toString()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.location", is(location)))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.password").doesNotExist());
    }
}
