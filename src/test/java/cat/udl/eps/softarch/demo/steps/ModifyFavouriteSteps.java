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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
public class ModifyFavouriteSteps {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private StepDefs stepDefs;
    private Long petId;

    @Given("^There are two registered pets with ids \"([^\"]*)\" and \"([^\"]*)\"$")
    public void thereAreRegisteredPetsWithIds(Long petToCreate, Long petToFavourite) {
        if (!petRepository.existsById(petToCreate)) {
            Pet pet = new Pet();
            pet.setId(petToCreate);
            petRepository.save(pet);
        }
        if (!petRepository.existsById(petToFavourite)) {
            Pet pet = new Pet();
            pet.setId(petToFavourite);
            petRepository.save(pet);

        }
    }

    @Given("^User \"([^\"]*)\" has pet \"([^\"]*)\" set as favourite$")
    public void userHasPetSetAsFavourite(String userId, Long petId) throws Throwable {
        Pet newPet = new Pet();
        newPet.setId(petId);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post(String.format("/users/%s/favouritedPets",userId))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(newPet))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I press the favouritePet button for the pet with id \"([^\"]*)\"$")
    public void iPressTheFavouriteButton(Long favouritedPet) throws Throwable {
        //I'm not logged in case
        if (AuthenticationStepDefs.currentUsername == null) {
            return;
        }

        Optional<User> user = userRepository.findById(AuthenticationStepDefs.currentUsername);
        if (user.isEmpty()) {
            System.out.println("User not found or authentication failed."); // Add a debug message
            return;
        }

        //Needed later for checking correct operation
        petId = favouritedPet;

        List<Pet> petList = user.get().favouritedPets;
        boolean found = false;
        System.out.println("Size of petList (mid):");
        System.out.println(petList.size());

        if (!petList.isEmpty()) {
            for (Pet value : petList) {
                if (favouritedPet.equals(value.getId())) {
                    found = true;
                    stepDefs.result = stepDefs.mockMvc.perform(delete(String.format("/users/%s/favouritedPets",AuthenticationStepDefs.currentUsername)));
                }
            }
        }
        if (!found) {
            Pet newPet = new Pet();
            newPet.setId(favouritedPet);
            newPet.setName("Rex");
            newPet.setAge("9");
            newPet.setColor("blue");
            newPet.setAdopted(true);
            stepDefs.result = stepDefs.mockMvc.perform(
                    post(String.format("/users/%s/favouritedPets",AuthenticationStepDefs.currentUsername))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(stepDefs.mapper.writeValueAsString(newPet))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
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
        System.out.println("Size of petList (last step):");
        System.out.println(petList.size());
        for (Pet value : petList) {
            if (petId.equals(value.getId())) {
                found = true;
                break;
            }
        }
        return found;
    }
}
