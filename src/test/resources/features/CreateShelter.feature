Feature: Create Shelter
  In order to provide temporary housing for animals
  As a Admin
  I want to create shelters

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"


  Scenario: Create a shelter without being logged in
    Given I'm not logged in
    When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 0 Shelter created

  Scenario: Create a new shelter as Admin
    Given There is no shelter registered with the name "shelter"
    And I am a admin with name "adminName"
    When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
    Then The response code is 201

  Scenario: Create a new shelter as Shelter Volunteer
    Given There is no shelter registered with the name "shelter"
    And I am a shelter volunteer with name "shelterVolunteerName"
    When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
    Then The response code is 403
    And The error message is "You are not authorized to create a shelter"

