Feature: Create Shelter
  In order to provide temporary housing for animals
  As a shelter manager
  I want to create shelters and manage their information

  Scenario: Create a new shelter as Admin
    Given There is no shelter registered with the name "shelter" and location "city"
    And I am a admin
    When I create a new shelter with name "shelter" and location "city"
    Then The response code is 201
    And It has been created a user with username "shelter" and location "city", the password is not returned

  Scenario: Create a new shelter as Shelter Volunteer
    Given There is no shelter registered with the name "shelter" and location "city"
    And I am a shelter volunteer
    When I create a new shelter with name "shelter" and location "city"
    Then The response code is 403
    And The response is "You are not authorized to create a shelter"

  Scenario: Create a new shelter with the same name and location
    Given There is a shelter registered with the name "shelter" and location "city"
    And I am a admin
    When I create a new shelter with name "shelter" and location "city"
    Then The response code is 409
    And The response is "A shelter with the same name and location already exists"

  Scenario: Create a new shelter with the same name and different location
    Given There is a shelter registered with the name "shelter" and location is different from "other city"
    And I am a admin
    When I create a new shelter with name "shelter" and location "other city"
    Then The response code is 201
    And It has been created a user with username "shelter" and location "other city", the password is not returned

  Scenario: Create a new shelter with the same location and different name
    Given