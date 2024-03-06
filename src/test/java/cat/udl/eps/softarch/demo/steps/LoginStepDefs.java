package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private UserRepository userRepository;

    @Given("^There isn't registered user with username \"([^\"]*)\"$")
    public void thereIsNoRegisteredUserWithUsername(String user) {
        Assert.assertFalse("User \""
                        +  user + "\"shouldn't exist",
                userRepository.existsById(user));
    }

    @Given("^There is a registered user with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void thereIsARegisteredUserWithUsernameAndPassword(String username, String password) {
        if(!userRepository.existsById(username)){
            User user = new User();
            user.setId(username);
            user.setEmail("user@user.cat");
            user.setPassword(password);
            user.encodePassword();
            userRepository.save(user);
        }
    }

    @And("^And I'm not logged in$")
    public void iMNotLoggedIn(String username) throws Throwable {

    }

    @When("^I login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iLoginWithUsernameAndPassword(String username, String password) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/users/{username}", username)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.password").doesNotExist());
    }



}
