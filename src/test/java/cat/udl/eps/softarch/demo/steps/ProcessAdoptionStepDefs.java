package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ProcessAdoptionStepDefs {
    @Given("There is an available pet with ID {string}")
    public void thereIsAnAvailablePetWithID(String arg0) {
    }

    @And("I'm logged in as user {string}")
    public void iMLoggedInAsUser(String arg0) {
    }

    @When("I request to adopt the pet with ID {string}")
    public void iRequestToAdoptThePetWithID(String arg0) {
    }

    @And("I receive a confirmation message for adopting the pet")
    public void iReceiveAConfirmationMessageForAdoptingThePet() {
    }

    @Given("There is no pet with ID {string}")
    public void thereIsNoPetWithID(String arg0) {
    }

    @And("I receive an error message that the pet does not exist")
    public void iReceiveAnErrorMessageThatThePetDoesNotExist() {
    }

    @Given("There is a pet with ID {string} and it is already adopted")
    public void thereIsAPetWithIDAndItIsAlreadyAdopted(String arg0) {
    }

    @And("I receive an error message that the pet is already adopted")
    public void iReceiveAnErrorMessageThatThePetIsAlreadyAdopted() {
    }

    @When("I attempt to request to adopt the pet with ID {string}")
    public void iAttemptToRequestToAdoptThePetWithID(String arg0) {
    }

    @And("I receive an error message that I need to be logged in to process adoption requests")
    public void iReceiveAnErrorMessageThatINeedToBeLoggedInToProcessAdoptionRequests() {
    }
}
