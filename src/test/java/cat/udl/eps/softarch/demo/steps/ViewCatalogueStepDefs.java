package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.When;

public class ViewCatalogueStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterRepository shelterRepository;

    @When("I request the Catalogue")
    public void viewCatalogue() throws Exception {

    }
}
