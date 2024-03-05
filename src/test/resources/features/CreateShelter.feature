Feature: Create Shelter
  In order to provide temporary housing for animals
  As a shelter manager
  I want to create shelters and manage their information

  Scenario: Create a new shelter
    Given There is no shelter registered with the name "shelter" and location "city"
    And I am a shelter volunteer
    When I create a new shelter with name "shelter" and location "city"
    Then The response code is 201
    And It has been created a user with username "shelter" and location "city", the password is not returned