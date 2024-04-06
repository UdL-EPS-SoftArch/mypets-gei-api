Feature: Add Medical Record
  In order to maintain an accurate health history for pets
  As a shelter volunteer
  I want to add medical records to pets' profiles

  Background:
    Given a pet exists in the system
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"
    Given There is a registered volunteer with name "volunteer" and password "password" and email "volunteer@sample.app"
    

  Scenario: Add a valid medical record to an existing pet as Volunteer
    Given I login as "volunteer" with password "password"
    When I add a new medical record for a pet with issue "Allergy", description "Seasonal allergy", and date "2024-03-07T14:00:00Z"
    Then The response code is 201

  Scenario: Add medical record with empty issue as Volunteer
    Given I login as "volunteer" with password "password"
    When I add a new medical record for a pet with issue "", description "Missing vaccine", and date "2024-03-07T14:00:00Z"
    Then The response code is 400

  Scenario: Add medical record with empty description as Volunteer
    Given I login as "volunteer" with password "password"
    When I add a new medical record for a pet with issue "Vaccination", description "", and date "2024-03-07T14:00:00Z"
    Then The response code is 400

  Scenario: Add medical record without date as Volunteer
    Given I login as "volunteer" with password "password"
    When I add a new medical record for a pet with issue "Injury", description "Minor cut on paw" and no date
    Then The response code is 400

  Scenario: Attempt to add a medical record as a normal user
    Given I login as "user" with password "password"
    When I try to add a medical record for a pet
    Then The response code is 403
    And The error message is "Unauthorized access"

  Scenario: Attempt to add a medical record as an admin
    Given I login as "admin" with password "password"
    When I try to add a medical record for a pet
    Then The response code is 201
