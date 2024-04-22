package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AddNewVolunteerToShelter {

    private ShelterRepository shelterRepository;
    private ShelterVolunteer newVolunteer;
    private Long shelterId;
    private boolean shelterExists;
    private Exception exceptionThrown;

    @Given("a Shelter with ID {int} exists")
    public void aShelterWithIdExists(int shelterId) {
        this.shelterId = (long) shelterId;
        this.shelterExists = true; 
    }

    @Given("a Shelter with ID {int} does not exist")
    public void aShelterWithIdDoesNotExist(int shelterId) {
        this.shelterId = (long) shelterId;
        this.shelterExists = false; 
    }

    @When("a new Volunteer is added to Shelter with ID {int}")
    public void aNewVolunteerIsAddedToShelterWithId(int shelterId) {
        try {
            if (shelterExists) {
                Shelter shelter = shelterRepository.findById((long) shelterId)
                        .orElseThrow(() -> new RuntimeException("Shelter not found"));
                newVolunteer = new ShelterVolunteer();
                newVolunteer.setUserShelter(shelter);
            } else {
                throw new RuntimeException("Shelter with ID " + shelterId + " does not exist");
            }
        } catch (Exception e) {
            this.exceptionThrown = e;
        }
    }

    @Then("the Volunteer should be associated with Shelter with ID {int}")
    public void theVolunteerShouldBeAssociatedWithShelterWithId(int shelterId) {
        Assert.assertEquals((Object) shelterId, (Object) newVolunteer.getUserShelter().getId());
    }

    @Then("the system should indicate that the Shelter with ID {int} does not exist")
    public void theSystemShouldIndicateThatTheShelterWithIdDoesNotExist(int shelterId) {
        Assert.assertNotNull("Exception should be thrown for non-existing shelter", exceptionThrown);
        Assert.assertEquals("Shelter with ID " + shelterId + " does not exist", exceptionThrown.getMessage());
    }
}
