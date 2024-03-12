package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ValidateAdoptionStepDefs {
    @Given("There is a pending adoption request for pet with ID {string} by user {string}")
    public void thereIsAPendingAdoptionRequestForPetWithIDByUser(String arg0, String arg1) {
    }

    @And("I'm not logged in or logged in as a regular user, not admin or shelter staff")
    public void iMNotLoggedInOrLoggedInAsARegularUserNotAdminOrShelterStaff() {
        
    }

    @When("I attempt to validate the adoption request for pet with ID {string} by user {string}")
    public void iAttemptToValidateTheAdoptionRequestForPetWithIDByUser(String arg0, String arg1) {
        
    }

    @And("the pet with ID {string} is already marked as adopted")
    public void thePetWithIDIsAlreadyMarkedAsAdopted(String arg0) {
        
    }

    @And("I'm logged in as admin or shelter staff")
    public void iMLoggedInAsAdminOrShelterStaff() {
        
    }

    @Given("There is no pending adoption request for a non-existent pet with ID {string}")
    public void thereIsNoPendingAdoptionRequestForANonExistentPetWithID(String arg0) {
        
    }

    @When("I validate the adoption request for pet with ID {string} by user {string}")
    public void iValidateTheAdoptionRequestForPetWithIDByUser(String arg0, String arg1) {
        
    }

    @And("The adoption status for pet with ID {string} is updated to {string}")
    public void theAdoptionStatusForPetWithIDIsUpdatedTo(String arg0, String arg1) {
        
    }

    @And("User {string} is notified about the validation of the adoption")
    public void userIsNotifiedAboutTheValidationOfTheAdoption(String arg0) {
    }
}
