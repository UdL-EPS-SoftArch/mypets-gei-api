package cat.udl.eps.softarch.demo.steps;




import cat.udl.eps.softarch.demo.domain.Adoption;
import cat.udl.eps.softarch.demo.repository.AdoptionRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("ALL")
public class ValidateAdoptionStepDefs {

    @Autowired
    StepDefs stepDefs;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    AdoptionRepository adoptionRepository;

    protected ResultActions result;




    @And("There is an available adoption with name {string}")
    public void thereIsAnAvailableAdoptionWithName(String arg0) {
        Adoption adoption = new Adoption();

    }

    @And("There is a pending adoption request for pet {string} from user {string}")
    public void thereIsAPendingAdoptionRequestForPetFromUser(String arg0, String arg1) {
    }

    @When("I validate the adoption request for pet {string} from user {string}")
    public void iValidateTheAdoptionRequestForPetFromUser(String arg0, String arg1) {
    }

    @Then("The adoption request for pet {string} is approved")
    public void theAdoptionRequestForPetIsApproved(String arg0) {
    }

}
