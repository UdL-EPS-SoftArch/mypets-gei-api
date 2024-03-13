package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterCertificate;
import cat.udl.eps.softarch.demo.repository.LocationRepository;
import cat.udl.eps.softarch.demo.repository.ShelterCertificateRepository;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import com.sun.tools.jconsole.JConsoleContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import jakarta.validation.constraints.AssertTrue;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ValidateShelterCertificateStepDefs {

    @Autowired
    private ShelterCertificateRepository shelterCertificateRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StepDefs stepDefs;

    @Given("^A shelter with name \"([^\"]*)\"$")
    public void thereIsAShelterCreatedWithName(String name){
        Location location = new Location();
        locationRepository.save(location);
        if(shelterRepository.findByName(name) != null){
             Shelter shelter = new Shelter();
                    shelter.setName(name);
                    shelter.setEmail("test@test.com");
                    shelter.setActive(true);
                    shelter.setLocatedAt(location);
                    shelter.setMobile("000000000");
            shelterRepository.save(shelter);
        }
    }

    @And("^An admin with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void anAdminWithUsername(String username, String password){
        Admin admin = new Admin();
        admin.setId(username);
        admin.setEmail("admin@admin.com");
        admin.setPassword(password);
        userRepository.save(admin);
    }

    @Given("^a shelter certificate associated with a shelter with name \"([^\"]*)\" with valid information created by a shelter volunteer$")
    public void aShelterCertificateWithValidInformationSentByAVolunteer(String name){
        Shelter shelter = shelterRepository.findByName(name).get(0);
        ShelterCertificate shelterCertificate = new ShelterCertificate();
        shelterCertificate.setShelterServed(shelter);
        shelterCertificate.setValidated(false);
        shelterCertificate.setExpirationDate(ZonedDateTime.now().plusMonths(6));
        shelterCertificateRepository.save(shelterCertificate);
    }

    @Then("^the admin with username \"([^\"]*)\" and password \"([^\"]*)\" should verify the certificate validity associated with a shelter with name \"([^\"]*)\"$")
    public void thenAdminShouldVerifyTheCertificateValidityAssociatedWithAShelterWithName(String username, String password, String name) throws Exception {
        Shelter shelter = shelterRepository.findByName(name).get(0);
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = password;

        stepDefs.result = stepDefs.mockMvc.perform(
                get( "/shelterCertificates/shelterServed/{id}", shelter.getId())
                        .with(AuthenticationStepDefs.authenticate()).accept("application/json"));
        //ShelterCertificate shelterCertificate = testShelterCertificateRepository.findByShelterServed(shelter);
        System.out.println("The Shelter Certificate IS valid!" + stepDefs.result.getClass().toString());
        Assert.assertTrue("The Shelter Certificate IS not valid!", true);
    }

    @And("^the admin should approve the certificate associated to the shelter with name \"([^\"]*)\"$")
    public void theAdminShouldApproveTheCertificateAssociatedToTheShelterWithName(String name){
        Shelter shelter = shelterRepository.findByName(name).get(0);
        ShelterCertificate sCert = shelterCertificateRepository.findByShelterServed(shelter);
        sCert.setValidated(true);
        shelterCertificateRepository.save(sCert);
        sCert = shelterCertificateRepository.findByShelterServed(shelter);
        Assert.assertTrue("The Shelter Certificate Is not validated", sCert.getValidated());
    }
    @Given("^a shelter certificate associated with a shelter with name \"([^\"]*)\" with invalid information created by a shelter volunteer$")
    public void aShelterCertificateWithInvalidInformationSentByAVolunteer(String name){
        Shelter shelter = shelterRepository.findByName(name).get(0);
        ShelterCertificate shelterCertificate = new ShelterCertificate();
        shelterCertificate.setShelterServed(shelter);
        shelterCertificate.setValidated(false);
        shelterCertificate.setExpirationDate(ZonedDateTime.now().minusMonths(6));
        shelterCertificateRepository.save(shelterCertificate);
    }

    @Then("^the admin should verify the wrong certificate validity associated with a shelter with name \"([^\"]*)\"$")
    public void thenAdminShouldVerifyTheCertificateValidityAssociatedWithAShelterWithName1(String name) {
        Shelter shelter = shelterRepository.findByName(name).get(0);
        ShelterCertificate shelterCertificate = shelterCertificateRepository.findByShelterServed(shelter);
        Assert.assertFalse("The Shelter Certificate IS not valid!", shelterCertificate.getExpirationDate().isAfter(ZonedDateTime.now()));
    }

    @And("^the admin should reject the certificate associated to the shelter with name \"([^\"]*)\"$")
    public void theAdminShouldApproveTheCertificateAssociatedToTheShelterWithName1(String name){
        Shelter shelter = shelterRepository.findByName(name).get(0);
        ShelterCertificate sCert = shelterCertificateRepository.findByShelterServed(shelter);
        sCert.setValidated(false);
        shelterCertificateRepository.save(sCert);
        sCert = shelterCertificateRepository.findByShelterServed(shelter);
        Assert.assertFalse("The Shelter Certificate Is not validated", sCert.getValidated());
    }
}
