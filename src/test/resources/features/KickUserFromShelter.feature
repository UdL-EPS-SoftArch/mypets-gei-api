Feature: Kick ShelterVolunteer from Shelter
  In order to kick a shelter volunteer
  As a Admin
  I want to be able to kick a volunteer from the shelter



  Scenario: Kick volunteer from shelter

    Given There a shelter with name "shelter" email "shelter@shelter.com" and mobile "12345678"
    And There is a shelter volunteer with username "user" and password "pass" in the shelter "shelter"
    And I can login with username "user" and password "pass"
    Then The response code is 200
    And There is a shelter volunteer with username "volunteer" and password "pass" in the shelter "shelter"
    When I kick user "volunteer" from shelter "shelter"
    Then The response code is 200

  Scenario: Kick volunteer from shelter as user

    Given There a shelter with name "shelter" email "shelter@shelter.com" and mobile "12345678"
    And There is a registered user with username "user" and password "pass" and email "user@user.com"
    And I can login with username "user" and password "pass"
    Then The response code is 200
    And There is a shelter volunteer with username "volunteer" and password "pass" in the shelter "shelter"
    When I kick user "volunteer" from shelter "shelter"
    Then The response code is 403


  Scenario: Kick volunteer from shelter as volunteer in another shelter

    Given There a shelter with name "shelter" email "shelter@shelter.com" and mobile "12345678"
    And There a shelter with name "shelter2" email "shelter2@shelter.com" and mobile "12345679"
    And There is a shelter volunteer with username "user" and password "pass" in the shelter "shelter2"
    And I can login with username "user" and password "pass"
    Then The response code is 200
    And There is a shelter volunteer with username "volunteer" and password "pass" in the shelter "shelter"
    When I kick user "volunteer" from shelter "shelter"
    Then The response code is 412