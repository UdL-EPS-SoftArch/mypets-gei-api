package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyFavouriteSteps {
    @Autowired
    private UserRepository userRepository;
    private PetRepository petRepository;
    //public static String currentUsername;

    @When("^I press the favouritePet button for the pet \"([^\"]*)\"$")
    public void iPressTheFavouriteButton(String favouritedPet) throws Throwable {
        User user = new User();
        user.setId(AuthenticationStepDefs.currentUsername);
        Pet pet = new Pet();
        pet.setId(petRepository.);

        pet.
        //buscar a la relació favourites el user
        //comprovar si ja te guardat aquest pet
    }

    @Given("^I login as \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username) {
        AuthenticationStepDefs.currentUsername = username;
    }
}
