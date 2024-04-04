package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
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
import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.json.JSONObject;

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
    public void iUpdateTheShelterWithNameToNameEmailAndMobile(String prevName, String newName, String email, String mobile)
        throws Throwable {
                    Shelter shelter = shelterRepository.findByName(prevName).get(0);
                    if (shelter != null) {
                       shelter =  shelterRepository.findByName(prevName).get(0);
                    }
                stepDefs.result = stepDefs.mockMvc.perform(patch(shelter.getUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject().put("name", newName)
                                        .put("email", email)
                                        .put("mobile", mobile).toString())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
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

    @And("^I get the shelter with name \"([^\"]*)\"")
    public void iGetTheShelterWithName(String newname) throws Exception {
        List<Shelter> shelters = shelterRepository.findByName(newname);
        Shelter shelter = shelters.get(0);
        stepDefs.result = stepDefs.mockMvc.perform(get("/shelters/" + shelter.getId())
                        .accept(MediaType.APPLICATION_JSON)).andDo(print());
        JSONObject jsonObject = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        jsonObject.getString("name");
        try {
            String actualName = jsonObject.getString("name");
            Assert.assertEquals(newname, actualName);
        } catch (Exception e) {
           fail("Key not found");
        }
    }


    @Given("There is a registered already admin with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredAlreadyAdminWithUsernameAndPasswordAndEmail(String adminname, String adminPasswd, String adminEmail) {
        if (!adminRepository.existsById(adminname)) {
            Admin user = new Admin();
            user.setEmail(adminEmail);
            user.setId(adminname);
            user.setPassword(adminPasswd);
            user.encodePassword();
            adminRepository.save(user);

        }
    }
}
