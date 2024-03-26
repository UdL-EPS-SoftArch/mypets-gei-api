Feature: Kick ShelterVolunteer from Shelter
  In order to kick a shelter volunteer
  As a Admin
  I want to be able to kick a volunteer from the shelter



  Scenario: Kick volunteer from shelter
    Given  There is a registered user with username "user" and password "pass" and email "user@user.com"
    And I can login with username "user" and password "pass"
    And  The response code is 200
    And There a shelter with name "shelter" email "shelter@shelter.com" and mobile "12345678"
    And There is a shelter volunteer with username "volunteer" in the shelter "shelter"
    When I kick user "volunteer" from shelter "shelter"
    Then The response code is 200
