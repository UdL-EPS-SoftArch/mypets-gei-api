package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class ModifyFavouriteSteps {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private StepDefs stepDefs;
    private Long petId;

    @Given("^There is a registered pet with id \"([^\"]*)\"")
    public void thereIsARegisteredPetWithId(Long id) {
        if (!petRepository.existsById(id)) {
            Pet pet = new Pet();
            pet.setId(id);
            petRepository.save(pet);
        }
    }

    @Given("^I login as \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username) {
        //Only need username to look for user's id later
        AuthenticationStepDefs.currentUsername = username;
    }

    @When("^I press the favouritePet button for the pet with id \"([^\"]*)\"$")
    public void iPressTheFavouriteButton(Long favouritedPet) throws Throwable {
        //I'm not logged in case
        if (AuthenticationStepDefs.currentUsername == null) {
            return;
        }

        //Needed later for checking correct operation
        petId = favouritedPet;

        Optional<User> user = userRepository.findById(AuthenticationStepDefs.currentUsername);
        List<Pet> petList = user.get().favouritedPets;
        boolean found = false;

        if (!petList.isEmpty()) {
            for (Pet value : petList) {
                if (favouritedPet.equals(value.getId())) {
                    found = true;
                    stepDefs.mockMvc.perform(delete(String.format("/favouritedPets/%s", favouritedPet)));
                }
            }
        }
        if (!found) {
            Pet newPet = new Pet();
            newPet.setId(favouritedPet);
            stepDefs.mockMvc.perform(post(String.format("/favouritedPets/%s", favouritedPet)).contentType(MediaType.APPLICATION_JSON).content(stepDefs.mapper.writeValueAsString(newPet)).characterEncoding(StandardCharsets.UTF_8).with(AuthenticationStepDefs.authenticate())).andDo(print());
        }
    }

    @And("The entry on the relation \"favourites\" is created")
    public void theEntryIsCreated() {
        Assert.assertTrue(favouritedByUser());
    }

    @And("The entry on the relation \"favourites\" is deleted")
    public void theEntryIsDeleted() {
        Assert.assertFalse(favouritedByUser());
    }

    private boolean favouritedByUser() {
        Optional<User> user = userRepository.findById(AuthenticationStepDefs.currentUsername);
        List<Pet> petList = user.get().favouritedPets;
        boolean found = false;
        for (Pet value : petList) {
            if (petId.equals(value.getId())) {
                found = true;
                break;
            }
        }
        return found;
    }
}
