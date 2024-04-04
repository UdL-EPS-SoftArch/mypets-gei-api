package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EditShelterStepDefs {

    @Autowired
    ShelterRepository shelterRepository;
    User user = new User();
    @Autowired
    UserRepository userRepository;
    @And("^There a shelter with name \"([^\"]*)\" email \"([^\"]*)\" and mobile \"([^\"]*)\"$")
    public void thereAShelterWithNameEmailAndMobile(String name, String email, String mobile) {
        Shelter shelter = new Shelter();
        shelter.setName(name);
        shelter.setEmail(email);
        shelter.setMobile(mobile);
        shelterRepository.save(shelter);
    }






}
