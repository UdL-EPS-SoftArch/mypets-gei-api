package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import cat.udl.eps.softarch.demo.domain.Pet;

public class EditPet {
    private Pet pet;

    @Given("there is an existing Pet with name {string}")
    public void thereIsAnExistingPetWithName(String name) {
        pet = new Pet();
        pet.setName(name);
    }

    @Given("there is an existing Pet with color {string}")
    public void thereIsAnExistingPetWithColor(String color) {
        pet = new Pet();
        pet.setColor(color);
    }

    @Given("there is an existing Pet with size {string}")
    public void thereIsAnExistingPetWithSize(String size) {
        pet = new Pet();
        pet.setSize(size);
    }

    @Given("there is an existing Pet with weight {double}")
    public void thereIsAnExistingPetWithWeight(Double weight) {
        pet = new Pet();
        pet.setWeight(weight);
    }

    @Given("there is an existing Pet with age {string}")
    public void thereIsAnExistingPetWithAge(String age) {
        pet = new Pet();
        pet.setAge(age);
    }

    @Given("there is an existing Pet with description {string}")
    public void thereIsAnExistingPetWithDescription(String description) {
        pet = new Pet();
        pet.setDescription(description);
    }

    @Given("there is an existing Pet with breed {string}")
    public void thereIsAnExistingPetWithBreed(String breed) {
        pet = new Pet();
        pet.setBreed(breed);
    }

    @When("I update the Pet's name to {string}")
    public void iUpdateThePetsNameTo(String newName) {
        pet.setName(newName);
    }

    @When("I update the Pet's color to {string}")
    public void iUpdateThePetsColorTo(String newColor) {
        pet.setColor(newColor);
    }

    @When("I update the Pet's size to {string}")
    public void iUpdateThePetsSizeTo(String newSize) {
        pet.setSize(newSize);
    }

    @When("I update the Pet's weight to {double}")
    public void iUpdateThePetsWeightTo(Double newWeight) {
        pet.setWeight(newWeight);
    }

    @When("I update the Pet's age to {string}")
    public void iUpdateThePetsAgeTo(String newAge) {
        pet.setAge(newAge);
    }

    @When("I update the Pet's description to {string}")
    public void iUpdateThePetsDescriptionTo(String newDescription) {
        pet.setDescription(newDescription);
    }

    @When("I update the Pet's breed to {string}")
    public void iUpdateThePetsBreedTo(String newBreed) {
        pet.setBreed(newBreed);
    }

    @Then("the system should confirm the Pet's name is {string}")
    public void theSystemShouldConfirmThePetsNameIs(String expectedName) {
        assert(pet.getName().equals(expectedName));
    }

    @Then("the system should confirm the Pet's color is {string}")
    public void theSystemShouldConfirmThePetsColorIs(String expectedColor) {
        assert(pet.getColor().equals(expectedColor));
    }

    @Then("the system should confirm the Pet's size is {string}")
    public void theSystemShouldConfirmThePetsSizeIs(String expectedSize) {
        assert(pet.getSize().equals(expectedSize));
    }

    @Then("the system should confirm the Pet's weight is {double}")
    public void theSystemShouldConfirmThePetsWeightIs(Double expectedWeight) {
        assert(pet.getWeight().equals(expectedWeight));
    }

    @Then("the system should confirm the Pet's age is {string}")
    public void theSystemShouldConfirmThePetsAgeIs(String expectedAge) {
        assert(pet.getAge().equals(expectedAge));
    }

    @Then("the system should confirm the Pet's description is {string}")
    public void theSystemShouldConfirmThePetsDescriptionIs(String expectedDescription) {
        assert(pet.getDescription().equals(expectedDescription));
    }

    @Then("the system should confirm the Pet's breed is {string}")
    public void theSystemShouldConfirmThePetsBreedIs(String expectedBreed) {
        assert(pet.getBreed().equals(expectedBreed));
    }
}
