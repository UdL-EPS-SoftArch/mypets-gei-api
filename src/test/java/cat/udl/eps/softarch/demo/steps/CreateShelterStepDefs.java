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

    private StepDefs stepDefs;
    private ShelterRepository shelterRepository;
    @Given("^There is no shelter registered with the name \"([^\"]*)\" ")
    public void thereIsNoShelterRegisteredWithName(String name) {//Location aqui ha de ser Strind o Tipus location? tu fas post de nomes l'id de location o de tot el objecte?
        Assert.assertTrue("Shelter with name \""
                + name + "\" and location \" shouldn't exist", shelterRepository.findByLocatedAtAndName(name).isEmpty());
    }

    @And("I am a admin")
    public void iAmAdmin(User user) {
        Assert.assertTrue("User should be a shelter volunteer",
                user instanceof Admin);
    }

   
    @And("I am a shelter volunteer")
    public void iAmAShelterVolunteer(User user) {
        Assert.assertTrue("User should be a shelter volunteer",
                user instanceof ShelterVolunteer);
    }

    @Given("There is a registered admin with name \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"")
    public void thereIsARegisteredAdminWithNameAndPasswordAndEmail(String arg0, String arg1, String arg2) {

    }

    @When("I create a shelter with a name \"([^\"]*)\"")
    public void iCreateAShelterWithAName(String arg0) throws Throwable {

    }
}
