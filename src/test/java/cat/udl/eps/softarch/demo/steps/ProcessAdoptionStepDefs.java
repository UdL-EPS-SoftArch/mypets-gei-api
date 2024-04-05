package cat.udl.eps.softarch.demo.steps;


import cat.udl.eps.softarch.demo.domain.Adoption;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.exceptions.InvalidPostRequest;
import cat.udl.eps.softarch.demo.repository.AdoptionRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("ALL")
public class ProcessAdoptionStepDefs {


    @Autowired
    StepDefs stepDefs;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    AdoptionRepository adoptionRepository;

    protected ResultActions result;



    @And("I receive a confirmation message for adopting the pet")
    public void iReceiveAConfirmationMessageForAdoptingThePet() throws Throwable {
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Adoption successful")));

    }

    @Given("There is an available pet with name {string}")
    public void thereIsAnAvailablePetWithName(String arg0) {

        Pet pet = new Pet();
        pet.setName(arg0);
        pet.setAdopted(false);
        pet.setColor("color");
        pet.setSize("size");
        pet.setWeight(1.0);
        pet.setAge("age");
        pet.setDescription("description");
        pet.setBreed("breed");
        petRepository.save(pet);


    }

    @When("I request to adopt the pet with name {string}")
    public void iRequestToAdoptThePetWithName(String arg0) throws Throwable {
        Adoption adoption = new Adoption();
        adoption.setPet(petRepository.findByName(arg0).get(0));
        adoption.setUser(userRepository.findAll().iterator().next());
        adoption.setStartDate(ZonedDateTime.now());
        adoption.setConfirmed(false);
        adoption.setType("Adoption");
        adoption.setEndDate(null);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/adoptions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(adoptionRepository.findAll().iterator().next()))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }



    @Given("There is a pet with name {string} and it is already adopted")
    public void thereIsAPetWithNameAndItIsAlreadyAdopted(String arg0) {

            petRepository.findByName(arg0).get(0).setAdopted(true);

            Adoption adoption = new Adoption();
            adoption.setPet(petRepository.findAll().iterator().next());
            adoption.setUser(userRepository.findAll().iterator().next());
            adoption.setStartDate(ZonedDateTime.now());
            adoption.setConfirmed(true);
            adoption.setType("Adoption");
            adoption.setEndDate(ZonedDateTime.now());

            adoptionRepository.save(adoption);


    }




    @When("I request to adopt without a pet")
    public void iRequestToAdoptWithoutAPet() throws Throwable{
        // Proceed with adoption logic
        Adoption adoption = new Adoption();
        adoption.setUser(userRepository.findAll().iterator().next());
        adoption.setStartDate(ZonedDateTime.now());
        adoption.setConfirmed(false);
        adoption.setType("Adoption");
        adoption.setEndDate(null);
        adoption.setPet(null);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/adoptions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(adoption))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }
}
