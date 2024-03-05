package cat.udl.eps.softarch.demo.steps;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
public class CreateShelterStepDefs {

    @Given("^There is no shelter registered with the name \"([^\"]*)\" and location \"([^\"]*)\"$")
    public void thereIsNoShelterRegisteredWithTheNameAndLocation(String name, String location) {
        Assert.assertFalse("Shelter with name \""
                + name + "\" and location \"" + location + "\" shouldn't exist",
                shelterRepository.existsByNameAndLocation(name, location));
    }

    @And("I am a shelter manager")
    public void iAmAShelterManager() {
        
    }

    @When("I attempt to create a new shelter with name {string} and location {string}")
    public void iAttemptToCreateANewShelterWithNameAndLocation(String arg0, String arg1) {
        
    }

    @And("The response contains a success message confirming the shelter was created")
    public void theResponseContainsASuccessMessageConfirmingTheShelterWasCreated() {
    }
}
