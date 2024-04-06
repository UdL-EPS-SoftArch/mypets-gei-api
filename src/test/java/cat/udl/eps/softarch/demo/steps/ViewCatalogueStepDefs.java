package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.repository.PetRepository;
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

    @When("I request the Catalogue")
    public void viewCatalogue() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(get("/pets/search/findByIsAdopted?isAdopted=false")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @When("I request the catalogue for the specific shelter {string}")
    public void viewShelterCatalogue(String shelter) throws Exception {
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

