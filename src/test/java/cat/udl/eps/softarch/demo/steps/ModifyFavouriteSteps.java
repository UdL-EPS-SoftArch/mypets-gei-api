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

import java.util.List;
import java.util.Objects;

public class ModifyFavouriteSteps {
    @Autowired
    private UserRepository userRepository;
    private PetRepository petRepository;
    private Long petId;
    @Given("^I login as \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username) {
        //Only need username to look for user's id later
        AuthenticationStepDefs.currentUsername = username;
    }
    @When("^I press the favouritePet button for the pet with id \"([^\"]*)\"$")
    public void iPressTheFavouriteButton(Long favouritedPet){
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
        Pet pet = new Pet();
        pet.setId(favouritedPet);
        petId = favouritedPet;
        User[] userList = pet.getFavouritedBy();
        for (int i=0; i<userList.length; i++){
            assert user.getId() != null;
            if (user.getId().equals(userList[i].getId())){
                //remove entry
            } else {
                //create entry
            }
        }
    }
    @And("The entry on the relation \"favourites\" is created")
    public void theEntryIsCreated(){
        boolean found = false;
        Pet pet = new Pet();
        pet.setId(petId);
        User[] userList = pet.getFavouritedBy();
        for (User user : userList) {
            if (AuthenticationStepDefs.currentUsername.equals(user.getId())) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found);
    }
}
