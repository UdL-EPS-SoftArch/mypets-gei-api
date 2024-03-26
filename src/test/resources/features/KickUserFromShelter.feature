Feature: Kick ShelterVolunteer from Shelter
  In order to kick a shelter volunteer
  As a Admin
  I want to be able to kick a volunteer from the shelter

  Background:
    Given There a shelter with name "shelter" email "shelter@shelter.com" and mobile "12345678"
    And There is a shelter volunteer with username "user" in the shelter "shelter"
    And There is a registered user with username "user" and password "pass" and email "user@user.com"

  Scenario: Kick volunteer from shelter
    Given I can login with username "user" and password "pass"
    And  The response code is 200

    When I kick user "volunteer" from shelter "shelter"
    Then The response code is 200
