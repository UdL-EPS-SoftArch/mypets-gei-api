Feature: Delete Shelter
  In order to use the app
  I must be able to delete Shelters

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered admin with username "admin" and password "password" and email "admin@sample.app"
    Given There is a created shelter with name "name", email "shelter@sample.com" and phone "123123123"


  Scenario: Delete a Shelter as user
    Given I login as "user" with password "password"
    When I try to delete Shelter with name "name"
    Then The response code is 403

  Scenario: Delete a Shelter as ShelterVolunteer
    Given I login as "ShelterVolunteer" with password "password"
    When I try to delete Shelter with name "name"
    Then The response code is 403

  Scenario: Delete a Shelter as admin
    Given I login as "admin" with password "password"
    When I try to delete Shelter with name "name"
    Then The response code is 200

  Scenario: Delete a Shelter that does not exist as user
    Given I login as "user" with password "password"
    When I try to delete Shelter with name "name"
    Then The response code is 403

  Scenario: Delete a Shelter that does not exist as ShelterVolunteer
    Given I login as "ShelterVolunteer" with password "password"
    When I try to delete Shelter with name "name"
    Then The response code is 403

  Scenario: Delete a Shelter that does not exist as admin
    Given I login as "admin" with password "password"
    When I try to delete Shelter with name "name"
    Then The response code is 404

  Scenario: Delete a Shelter not logged in
    Given I'm not logged in
    When I try to delete Shelter with name "name"
    Then The response code is 401