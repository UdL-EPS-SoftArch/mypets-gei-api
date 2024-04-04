package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.MedicalRecord;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.repository.MedicalRecordRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class AddMedicalRecordStepDefs {



    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private StepDefs stepDefs;

    Pet pet;

    @Given("a pet exists in the system")
    public void aPetExistsInTheSystem() {
        pet = new Pet();
    }

    @When("I add a new medical record for a pet with issue {string}, description {string}, and date {string}")
    public void iAddANewMedicalRecordForAPetWithIssueDescriptionAndDate(String issue, String description, String date) throws Throwable {
        MedicalRecord newRecord = new MedicalRecord();
        newRecord.setDescription(description);
        newRecord.setIssue(issue);
        newRecord.setDate(ZonedDateTime.parse(date));
        newRecord.setPet(pet);
        
        // Mock a POST request to /medicalRecords
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/medicalRecords")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(newRecord))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }



    @When("I add a new medical record for a pet with issue {string}, description {string} and no date")
    public void iAddANewMedicalRecordForAPetWithIssueDescriptionAndNoDate(String issue, String description) throws Exception {
        // Assuming that MedicalRecord has a constructor that does not require a date or it's nullable
        MedicalRecord recordWithoutDate = new MedicalRecord();
        //recordWithoutDate.setDescription(description);
        recordWithoutDate.setIssue(issue);
        
        recordWithoutDate.setPet(pet);

        // Simulate the action of adding a record without a date through a REST call
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/medicalRecords")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(recordWithoutDate))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I try to add a medical record for a pet")
    public void iTryToAddAMedicalRecordForAPet() throws Exception {
        // Attempt to add a medical record without specifying role or permissions
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDescription("Description");
        medicalRecord.setIssue("Issue");
        medicalRecord.setDate(ZonedDateTime.now());
        medicalRecord.setPet(pet); // Simplification for example purposes

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/medicalRecords")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(medicalRecord))
                                .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate())
                )
                .andDo(print());
    }
}