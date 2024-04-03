Feature: Kick ShelterVolunteer from Shelter
  In order to kick a shelter volunteer
  As a Volunteer
  I want to be able to kick a volunteer from my shelter but not able to kick volunteers from other shelters

  Background:
    Given There is a registered user with username "user" and password "pass" and email "user@user.com"
    And There a shelter with name "shelter1" email "shelter@shelter.com" and mobile "12345678"
    And There is a shelter volunteer with username "volunteer" and password "pass" in the shelter "shelter1"
    And There is a shelter volunteer with username "volunteer1" and password "pass" in the shelter "shelter1"
    And There a shelter with name "shelter2" email "shelter2@shelter.com" and mobile "12345679"
    And There is a shelter volunteer with username "volunteer2" and password "pass" in the shelter "shelter2"

  Scenario: Kick volunteer from shelter
    Given I can login with username "volunteer" and password "pass"
    When I kick user "volunteer1" from shelter "shelter"
    Then The response code is 200
    And I cannot login with username "volunteer1" and password "pass"



  Scenario: Kick volunteer from shelter as user

    And I can login with username "user" and password "pass"
    When I kick user "volunteer1" from shelter "shelter"
    Then The response code is 403
    And I can login with username "volunteer1" and password "pass"


  Scenario: Kick volunteer from shelter as volunteer in another shelter

    Given I can login with username "volunteer" and password "pass"
    When I kick user "volunteer2" from shelter "shelter"
    Then The response code is 412
    And I can login with username "volunteer2" and password "pass"

