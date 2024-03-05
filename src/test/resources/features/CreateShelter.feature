Feature: Create Shelter
  In order to provide temporary housing for animals
  As a shelter manager
  I want to create shelters and manage their information

  Scenario: Create a new shelter
    Given There is no shelter registered with the name "shelter" and location "city"
    And I am a shelter volunteer
    When I attempt to create a new shelter with name "shelter" and location "city"
    Then The response code is 201
    And The response contains a success message confirming the shelter was created