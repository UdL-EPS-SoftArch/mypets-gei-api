package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class ModifyFavouriteSteps {
    @Autowired
    private PetRepository petRepository;
    private StepDefs stepDefs;
    private Long petId;
    @Given("^I login as \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username) {
        //Only need username to look for user's id later
        AuthenticationStepDefs.currentUsername = username;
    }
    @When("^I press the favouritePet button for the pet with id \"([^\"]*)\"$")
    public void iPressTheFavouriteButton(Long favouritedPet) throws Throwable{
        //I'm not logged in case
        if (AuthenticationStepDefs.currentUsername == null){
            return;
        }
        //1. Get id's from both
        //2. Look into "favouritedBy" relation
        //  2.1 if exists -> delete entry   #unmarking as favourite
        //  2.2 if not -> create entry      #marking as favourite
        User user = new User();
        user.setId(AuthenticationStepDefs.currentUsername);
        Optional<Pet> pet = petRepository.findById(petId);
        User[] userList = pet.get().getFavouritedBy();
        for (User value : userList) {
            assert user.getId() != null;
            /*if (user.getId().equals(value.getId())) {
                if (favouritedByUser()) {
                    stepDefs.mockMvc.perform(
                            delete("/favourites")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(stepDefs.mapper.writeValueAsString(user.getId()))
                                    .characterEncoding(StandardCharsets.UTF_8)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                            .andDo(print());
                } else {
                    stepDefs.mockMvc.perform(
                            post("/favourites")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(stepDefs.mapper.writeValueAsString(user.getId()))
                                    .characterEncoding(StandardCharsets.UTF_8)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                            .andDo(print());
                }
            }*/
        }
    }
    @And("The entry on the relation \"favourites\" is created")
    public void theEntryIsCreated(){Assert.assertTrue(favouritedByUser());}

    @And("The entry on the relation \"favourites\" is deleted")
    public void theEntryIsDeleted(){Assert.assertFalse(favouritedByUser());}

    private boolean favouritedByUser(){
        boolean found = false;
        Optional<Pet> pet = petRepository.findById(petId);
        User[] userList = pet.get().getFavouritedBy();
        for (User user : userList) {
            if (AuthenticationStepDefs.currentUsername.equals(user.getId())) {
                found = true;
                break;
            }
        }
        return found;
    }
}
