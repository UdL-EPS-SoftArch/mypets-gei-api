package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeletePetStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;


    @Given("There is a Pet registered with name {string}")
    public void thereIsAPetRegisteredWithName(String name) {
        if (petRepository.findByName(name).isEmpty()) {
            Pet pet = new Pet();
            pet.setName(name);

            petRepository.save(pet);
        }
    }

    @When("I try to delete a Pet with name {string}")
    public void iTryToDeleteAPetWithName(String name) throws Exception {
        List<Pet> petList = petRepository.findByName(name);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/pets/{id}", petList.isEmpty() ? "0" : petList.get(0).getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
