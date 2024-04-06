Feature: Edit Medical Record
  In order to keep pet health history up to date
  As a shelter volunteer
  I want to edit existing medical records in pets' profiles

  Background:
    Given a pet exists in the system
    And a medical record exists for the pet
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"
    Given There is a registered volunteer with name "volunteer" and password "password" and email "volunteer@sample.app"


  Scenario: Edit an existing medical record as Volunteer
    Given I login as "volunteer" with password "password"
    When I edit the medical record for a pet with new issue "Allergy Update", new description "Updated seasonal allergy treatment", and new date "2024-03-08T14:00:00Z"
    Then The response code is 204

  Scenario: Edit a medical record with empty issue as Volunteer
    Given I login as "volunteer" with password "password"
    When I edit the medical record for a pet with new issue "", new description "Revaccination", and new date "2024-03-08T14:00:00Z"
    Then The response code is 400

  Scenario: Edit a medical record with empty description as Volunteer
    Given I login as "volunteer" with password "password"
    When I edit the medical record for a pet with new issue "Vaccination Update", new description "", and new date "2024-03-08T14:00:00Z"
    Then The response code is 400

  Scenario: Edit a medical record without changing the date as Volunteer
    Given I login as "volunteer" with password "password"
    When I edit the medical record for a pet with new issue "Injury Update", new description "Healed cut on paw"
    Then The response code is 204

  Scenario: Attempt to edit a medical record as a normal user
    Given I login as "user" with password "password"
    When I try to edit a medical record for a pet
    Then The response code is 403
    And The error message is "Unauthorized access"

  Scenario: Attempt to edit a medical record as an admin
    Given I login as "admin" with password "password"
    When I try to edit a medical record for a pet
    Then The response code is 204
