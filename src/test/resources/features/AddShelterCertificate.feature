Feature: Add Shelter Certificate
  As a ShelterVolunteer
  I want to add a Shelter Certificate
  So that my Shelter has a Certificate

  Background: Exists a shelter with name "test" and a shelter volunteer
    Given A shelter with name "test"
    And There is a registered user with username "volunteer" and password "123456789" and email "volunteer@mypets.com"

    Scenario: Adding a Shelter Certificate as a Shelter Volunteer
        Given I can login with username "volunteer" and password "123456789"
        When I add a Shelter Certificate with valid expiration date to the shelter with name "test"
        Then The response code is 201

    Scenario: Adding a Shelter Certificate as a non Shelter Volunteer
        Given I cannot login with username "test" and password "wrongPassword"
        When I add a Shelter Certificate with valid expiration date to the shelter with name "test"
        Then The response code is 403