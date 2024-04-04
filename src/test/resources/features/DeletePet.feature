Feature: Delete Pet
  in order to use the app
  As a Shelter Volunteer
  I must be able to delete a Pet

  Background:
    Given There is a Pet registered with name "pet"

  Scenario: Delete a Pet as User
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given I login with username "user" and password "password"
    When I try to delete a Pet with name "pet"
    Then The response code is 403

  Scenario: Delete a Pet as Admin
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"
    Given I login with username "admin" and password "password"
    When I try to delete a Pet with name "pet"
    Then The response code is 200

  Scenario: Delete a Pet as Shelter Volunteer
    Given There is a registered shelter volunteer with username "ShelterVolunteer" and password "password" and email "shelterv@sample.app"
    Given I login with username "ShelterVolunteer" and password "password"
    When I try to delete a Pet with name "pet"
    Then The response code is 200

  Scenario: Delete a Pet that does not exist as User
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given I login with username "user" and password "password"
    When I try to delete a Pet with name "none"
    Then The response code is 403

  Scenario: Delete a Pet that does not exist as Admin
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"
    Given I login with username "admin" and password "password"
    When I try to delete a Pet with name "none"
    Then The response code is 404

  Scenario: Delete a Pet that does not exist as Shelter Volunteer
    Given There is a registered shelter volunteer with username "ShelterVolunteer" and password "password" and email "shelterv@sample.app"
    Given I login with username "ShelterVolunteer" and password "password"
    When I try to delete a Pet with name "none"
    Then The response code is 404
