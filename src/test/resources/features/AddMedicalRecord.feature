Feature: Add Medical Record
  In order to maintain an accurate health history for pets
  As a shelter volunteer
  I want to add medical records to pets' profiles

  Background:
    Given a pet "Buddy" exists in the system
    And I am logged in as a shelter volunteer

  Scenario: Add a valid medical record to an existing pet
    When I add a new medical record with issue "Allergy", description "Seasonal allergy", and date "2024-03-07T14:00:00Z" 
    Then The response code is 201
    And It has been created a medical record with issue "Allergy" and description "Seasonal allergy"

  Scenario: Add medical record with empty issue
    When I add a new medical record with issue "", description "Missing vaccine", and date "2024-03-07T14:00:00Z" 
    Then The response code is 400
    And The error message is "issue must not be blank"

  Scenario: Add medical record with empty description
    When I add a new medical record with issue "Vaccination", description "", and date "2024-03-07T14:00:00Z" 
    Then The response code is 400
    And The error message is "description must not be blank"

  Scenario: Add medical record without date
    When I add a new medical record with issue "Injury", description "Minor cut on paw" and no date 
    Then The response code is 400
    And The error message is "date must not be null"

  Scenario: Attempt to add a medical record as a normal user
    Given I am logged in as a normal user
    When I try to add a medical record 
    Then The response code is 403
    And The error message is "Unauthorized access"

  Scenario: Attempt to add a medical record as an admin
    Given I am logged in as an admin
    When I try to add a medical record 
    Then The response code is 201