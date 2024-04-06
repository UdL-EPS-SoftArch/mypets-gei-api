package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.MedicalRecord;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.repository.MedicalRecordRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class EditMedicalRecordStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    PetRepository petRepository;
    
    @And("a medical record exists for the pet")
    public void aMedicalRecordExistsForThePet() {
        Pet pet = petRepository.findAll().iterator().next();

        MedicalRecord existingMedicalRecord;
        existingMedicalRecord = new MedicalRecord();
        existingMedicalRecord.setPet(pet);
        existingMedicalRecord.setIssue("Initial Issue");
        existingMedicalRecord.setDescription("Initial Description");
        existingMedicalRecord.setDate(ZonedDateTime.now());
        medicalRecordRepository.save(existingMedicalRecord);
  
    }

    @When("I edit the medical record for a pet with new issue {string}, new description {string}, and new date {string}")
    public void iEditTheMedicalRecordForAPetWithNewIssueNewDescriptionAndNewDate(String issue, String description, String date) throws Throwable {
        MedicalRecord existingMedicalRecord = medicalRecordRepository.findAll().iterator().next();
        existingMedicalRecord.setIssue(issue);
        existingMedicalRecord.setDescription(description);
        existingMedicalRecord.setDate(ZonedDateTime.parse(date));

        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/medicalRecords/" + existingMedicalRecord.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(existingMedicalRecord))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I edit the medical record for a pet with new issue {string}, new description {string}")
    public void iEditTheMedicalRecordForAPetWithNewIssueNewDescription(String issue, String description) throws Throwable {
        MedicalRecord existingMedicalRecord = medicalRecordRepository.findAll().iterator().next();

        existingMedicalRecord.setIssue(issue);
        existingMedicalRecord.setDescription(description);
        // Keeping the original date

        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/medicalRecords/" + existingMedicalRecord.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(existingMedicalRecord))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I try to edit a medical record for a pet")
    public void iTryToEditAMedicalRecordForAPet() throws Exception {
        MedicalRecord existingMedicalRecord = medicalRecordRepository.findAll().iterator().next();

        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/medicalRecords/" + existingMedicalRecord.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(existingMedicalRecord))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
