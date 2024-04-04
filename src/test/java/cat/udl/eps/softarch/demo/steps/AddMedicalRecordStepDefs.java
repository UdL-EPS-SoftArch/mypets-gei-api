package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.MedicalRecord;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.repository.MedicalRecordRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;


public class AddMedicalRecordStepDefs {

    Pet pet;
    ShelterVolunteer volunteer;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    @And("I am logged in as a shelter volunteer")
    public void iAmLoggedInAsAShelterVolunteer() {
        volunteer = new ShelterVolunteer();
    }
    @Given("a pet {string} exists in the system")
    public void aPetExistsInTheSystem(String arg0) {
        pet = new Pet();
        pet.setName(arg0);
    }

    @When("I add a new medical record with issue {string}, description {string}, and date {string}")
    public void iAddANewMedicalRecordWithIssueDescriptionAndDateFor(String issue, String description, String date) {
        ZonedDateTime dateTime = ZonedDateTime.parse(date);

        // Create and set up new MedicalRecord
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setIssue(issue);
        medicalRecord.setDescription(description);
        medicalRecord.setDate(dateTime);
        medicalRecord.setPet(pet);
        medicalRecordRepository.save(medicalRecord);
    }


    @And("It has been created a medical record with issue {string} and description {string}")
    public void itHasBeenCreatedAMedicalRecordWithIssueAndDescriptionFor(String arg0, String arg1, String arg2) {

    }

    @When("I add a new medical record with issue {string}, description {string} and no date")
    public void iAddANewMedicalRecordWithIssueDescriptionAndNoDateFor(String arg0, String arg1, String arg2) {
    }

    @Given("I am logged in as a normal user")
    public void iAmLoggedInAsANormalUser() { 
    }

    @When("I try to add a medical record")
    public void iTryToAddAMedicalRecordFor(String arg0) {
    }

    @Given("I am logged in as an admin")
    public void iAmLoggedInAsAnAdmin() {
    }
}