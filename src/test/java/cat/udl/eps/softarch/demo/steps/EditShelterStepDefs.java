package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.domain.Admin;

import cat.udl.eps.softarch.demo.repository.AdminRepository;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import cat.udl.eps.softarch.demo.repository.ShelterVolunteerRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class EditShelterStepDefs {

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ShelterVolunteerRepository shelterVolunteerRepository;
    @Autowired
    StepDefs stepDefs;
    @And("^There is already a shelter with name \"([^\"]*)\" email \"([^\"]*)\" and mobile \"([^\"]*)\"$")
    public void thereIsAlreadyAShelterWithNameEmailAndMobile(String name, String email, String mobile) {
        Shelter shelter = new Shelter();
        shelter.setName(name);
        shelter.setEmail(email);
        shelter.setMobile(mobile);
        shelterRepository.save(shelter);
    }

    @When("^I update the shelter with name \"([^\"]*)\" to name \"([^\"]*)\" email \"([^\"]*)\" and mobile \"([^\"]*)\"$")
    public void iUpdateTheShelterWithNameToNameEmailAndMobile(String PrevName, String NewName, String email, String mobile)
        throws Throwable {
                    Shelter shelter = shelterRepository.findByName(PrevName).get(0);
                    if (shelter != null) {
                       shelter =  shelterRepository.findByName(PrevName).get(0);
                    }
                stepDefs.result = stepDefs.mockMvc.perform(patch(shelter.getUri())//"/routes/"+route.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject().put("name",NewName).put("email",email).put("mobile",mobile).put("createdAt", ZonedDateTime.now()).toString())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        }


    @Given("^There is a registered admin with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredAdminWithUsernameAndPasswordAndEmail(String adminname, String adminPasswd, String adminEmail) throws Throwable{
            if (!adminRepository.existsById(adminname)) {
                Admin user = new Admin();
                user.setEmail(adminEmail);
                user.setId(adminname);
                user.setPassword(adminPasswd);
                user.encodePassword();
                adminRepository.save(user);
              
        }
    }

    @Given("^There is a registered volunteer with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void thereIsARegisteredVolunteerWithUsernameAndPasswordAndEmail(String name, String mobile) {
        if (!shelterVolunteerRepository.existsById(name)) {
            ShelterVolunteer volunteer = new ShelterVolunteer();
            volunteer.setEmail("shelter@sample.com");
            volunteer.setId(name);
            volunteer.setPassword(mobile);
            volunteer.encodePassword();
            shelterVolunteerRepository.save(volunteer);
        }
    }
}
