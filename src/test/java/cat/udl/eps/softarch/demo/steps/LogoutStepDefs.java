package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import jakarta.validation.constraints.AssertTrue;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogoutStepDefs {
    @Autowired
    private StepDefs stepDefs;


    @Given("^I logout of the app$")
    public void ILogoutOfTheApp() {
        AuthenticationStepDefs.currentUsername = "";
        AuthenticationStepDefs.currentPassword = "";
        

    }

    @Then("I can't acces my resources")
    public void iCanTAccessMyResources() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/identity")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Then("I don't have my session active")
    public void iDonTHaveMySessionActive() {
        Assert.assertTrue(AuthenticationStepDefs.currentUsername.isEmpty()
                &&
                AuthenticationStepDefs.currentPassword.isEmpty()
        );
    }
}
