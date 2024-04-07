package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DisableUserStepdefs {

    @Autowired
    StepDefs stepDefs;

    @When("I disable the user with username {string}")
    public void iDisableTheUserWithUsername(String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/users/{username}/lock", username)
                        .accept("application/json")
                        .with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

    @Then("The user with username {string} should be disabled")
    public void theUserWithUsernameShouldBeDisabled(String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/users/{username}", username)
                        .accept("application/json")
                        .with(AuthenticationStepDefs.authenticate()));

        JSONObject jsonObject = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        boolean isUserLocked = jsonObject.getBoolean("locked");

        Assert.assertTrue(isUserLocked);
    }

    @And("I enable the user with username {string}")
    public void iEnableTheUserWithUsername(String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/users/{username}/unlock", username)
                        .accept("application/json")
                        .with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

    @Then("The user with username {string} should be enabled")
    public void theUserWithUsernameShouldBeEnabled(String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/users/{username}", username)
                        .accept("application/json")
                        .with(AuthenticationStepDefs.authenticate()));

        JSONObject jsonObject = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        boolean isUserLocked = jsonObject.getBoolean("locked");

        Assert.assertFalse(isUserLocked);
    }
}
