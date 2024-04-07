package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.FavouritedPets;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.repository.FavouritedPetsRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class modifyFavouriteStepDefs {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private FavouritedPetsRepository favouriteRepository;
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
        FavouritedPets newPet;
        if (petRepository.existsById(petId)) {
            newPet = new FavouritedPets();
            newPet.setPetId(petId);
            newPet.setUserId(userId);
        } else {
            newPet = null;
        }
        stepDefs.mockMvc.perform(
                        post("/favouritedPetses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(newPet))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I press the favouritePet button for the pet with id \"([^\"]*)\"$")
    public void iPressTheFavouriteButton(Long favouritedPet) throws Throwable {
        //Needed later for checking correct operation
        petId = favouritedPet;

        List<FavouritedPets> petList = favouriteRepository.findByUserId(AuthenticationStepDefs.currentUsername);
        boolean found = false;

        if (!petList.isEmpty()) {
            for (FavouritedPets value : petList) {
                if (favouritedPet.equals(value.getPetId())) {
                    found = true;
                    Long entryId = favouriteRepository.findByUserIdAndPetId(AuthenticationStepDefs.currentUsername, petId).get(0).getId();
                    stepDefs.result = stepDefs.mockMvc.perform(delete(String.format("/favouritedPetses/%s", entryId))
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                            .andDo(print());
                }
            }
        }
        if (!found) {
            FavouritedPets newPet = new FavouritedPets();
            newPet.setPetId(favouritedPet);
            newPet.setUserId(AuthenticationStepDefs.currentUsername);
            stepDefs.result = stepDefs.mockMvc.perform(
                            post("/favouritedPetses")
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
        List<FavouritedPets> petList = favouriteRepository.findByUserId(AuthenticationStepDefs.currentUsername);
        boolean found = false;
        System.out.println("Size of petList (last step):");
        System.out.println(petList.size());
        for (FavouritedPets value : petList) {
            if (petId.equals(value.getPetId())) {
                found = true;
                break;
            }
        }
        return found;
    }
}
