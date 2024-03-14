package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.AdminRepository;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class DeleteShelterStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private AdminRepository adminRepository;

    @When("I try to delete Shelter with id {int}")
    public void iTryToDeleteShelterWith(int arg0) {
        Optional<Shelter> shelter = shelterRepository.findById((long) arg0);

    }

    @Given("There is a created shelter with name {string}, email {string} and phone {string}")
    public void thereIsACreatedShelterWithNameEmailAndPhone(String name, String email, String phone) {
        Shelter shelter = new Shelter();
        shelter.setName(name);
        shelter.setEmail(email);
        shelter.setMobile(phone);
        shelter.setLocatedAt(null);

        shelterRepository.save(shelter);
    }

    @When("I try to delete Shelter with name {string}")
    public void iTryToDeleteShelterWithName(String name) throws Exception {
        List<Shelter> shelterList = shelterRepository.findByName(name);

        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/shelters/{id}", shelterList.isEmpty() ? "0" : shelterList.get(0).getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print()) ;
    }


}
