package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import cat.udl.eps.softarch.demo.repository.ShelterVolunteerRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ViewCatalogueStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ShelterVolunteerRepository shelterVolunteerRepository;

    @Autowired
    private ShelterRepository shelterRepository;


    @Given("There is a registered shelter volunteer with username {string} that works at the shelter {long}")
    public void thereIsAShelterVolunteer(String username, long shelterId) throws Exception {
        if(shelterVolunteerRepository.findById(username).isEmpty()) {
            ShelterVolunteer shelterVolunteer = new ShelterVolunteer();
            Shelter shelter = new Shelter();
            shelter.setId(shelterId);
            shelterVolunteer.setUserShelter(shelter);
            shelterRepository.save(shelter);
            shelterVolunteerRepository.save(shelterVolunteer);
        }
    }

    @When("I request the Catalogue")
    public void viewCatalogue() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(get("/pets/search/findByIsAdopted?isAdopted=false")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @When("I request the Catalogue for the specific shelter {long}")
    public void viewShelterCatalogue(Long shelter) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(get("/pets/search/findByShelter?Shelter={shelter}", shelter)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @When("I request the Catalogue with breed {string}")
    public void viewCataloguewithBreed(String breed) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(get("/pets/search/findByBreed?breed={breed}", breed)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}

