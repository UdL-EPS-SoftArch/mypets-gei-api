Feature: Create Shelter
  In order to provide temporary housing for animals
  As a Admin
  I want to create shelters

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"


  Scenario: Create a shelter without being logged in
    Given I'm not logged in
    When I create a shelter with a name "testShelter""
    Then The response code is 401
    And The error message is "Unauthorized"

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

