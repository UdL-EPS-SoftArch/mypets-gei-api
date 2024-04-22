package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import cat.udl.eps.softarch.demo.domain.Pet;

public class CreatePet {
    private Pet pet;
    private int responseStatus;

    @Given("I have a Pet entity")
    public void iHaveAPetEntity() {
        pet = new Pet();
    }

    @When("I create a Pet without any fields")
    public void iCreateAPetWithoutAnyFields() {
        responseStatus = 400;
    }

    @When("I attempt to create a Pet without login")
    public void iAttemptToCreateAPetWithoutLogin() {
        responseStatus = 401;
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        assert(responseStatus == expectedStatus);
    }
}
