package cat.udl.eps.softarch.demo.steps;



import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.Adoption;
import cat.udl.eps.softarch.demo.repository.AdoptionRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SuppressWarnings("ALL")
public class ValidateAdoptionStepDefs {

    @Autowired
    StepDefs stepDefs;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    AdoptionRepository adoptionRepository;

    protected ResultActions result;


    @And("There is a dog with a pending adoption request from an user")
    public void thereIsAPendingAdoptionRequestForPetFromUser() {
        Pet pet = new Pet();
        pet.setName("Pet");
        pet.setAdopted(false);
        pet.setColor("color");
        pet.setSize("size");
        pet.setWeight(1.0);
        pet.setAge("age");
        pet.setDescription("description");
        pet.setBreed("breed");
        petRepository.save(pet);


        Adoption adoption = new Adoption();
        adoption.setConfirmed(false);
        adoption.setStartDate(ZonedDateTime.now());
        adoption.setUser(userRepository.findAll().iterator().next());
        adoption.setPet(petRepository.findAll().iterator().next());
        adoption.setType("Adoption");
        adoption.setEndDate(null);
        adoptionRepository.save(adoption);

    }

    @When("I validate the adoption request")
    public void iValidateTheAdoptionRequestForPetFromUser() throws Throwable {
        Adoption existingAdoption = adoptionRepository.findAll().iterator().next();
        existingAdoption.setConfirmed(true);

        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/adoptions/" + existingAdoption.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(existingAdoption))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }


}
