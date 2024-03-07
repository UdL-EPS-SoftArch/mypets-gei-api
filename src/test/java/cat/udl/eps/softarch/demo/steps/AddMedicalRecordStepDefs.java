package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class AddMedicalRecordStepDefs {
    @Given("a pet {string} exists in the system")
    public void aPetExistsInTheSystem(String arg0) {
        
    }

    @And("I am logged in as a shelter volunteer")
    public void iAmLoggedInAsAShelterVolunteer() {
    }

    @When("I add a new medical record with issue {string}, description {string}, and date {string} for {string}")
    public void iAddANewMedicalRecordWithIssueDescriptionAndDateFor(String arg0, String arg1, String arg2, String arg3) {
    }

    @And("It has been created a medical record with issue {string} and description {string} for {string}")
    public void itHasBeenCreatedAMedicalRecordWithIssueAndDescriptionFor(String arg0, String arg1, String arg2) {
    }

    @When("I add a new medical record with issue {string}, description {string} and no date for {string}")
    public void iAddANewMedicalRecordWithIssueDescriptionAndNoDateFor(String arg0, String arg1, String arg2) {
    }

    @Given("I am logged in as a normal user")
    public void iAmLoggedInAsANormalUser() {
    }

    @When("I try to add a medical record for {string}")
    public void iTryToAddAMedicalRecordFor(String arg0) {
    }

    @Given("I am logged in as an admin")
    public void iAmLoggedInAsAnAdmin() {
    }
}
