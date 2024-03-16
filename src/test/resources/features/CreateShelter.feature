Feature: Create Shelter
  In order to provide temporary housing for animals
  As a Admin
  I want to create shelters

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"


  Scenario: Create a shelter without being logged in
    Given I'm not logged in
    When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 0 Shelter created

  Scenario: Create shelter as Admin
    Given I login as "admin" with password "password"
    When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
    Then The response code is 201

  Scenario: Create shelter as Client
    Given I login as "client" with password "password"
    When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 0 Shelter created

  Scenario: Create shelter with missing name
    Given I login as "admin" with password "password"
    When I create a shelter with a name "" email "testemail" and mobile "123456789"
    Then The response code is 400
    And There is 0 Shelter created

    Scenario: Create shelter with missing email
      Given I login as "admin" with password "password"
      When I create a shelter with a name "testShelter" email "" and mobile "123456789"
      Then The response code is 400
      And There is 0 Shelter created

    Scenario: Create shelter with missing mobile
        Given I login as "admin" with password "password"
        When I create a shelter with a name "testShelter" email "testemail" and mobile ""
        Then The response code is 400
        And There is 0 Shelter created

    Scenario: Create a shelter that already exists:
        Given I login as "admin" with password "password"
        When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
        Then The response code is 400
        When I create a shelter with a name "testShelter" email "testemail" and mobile "123456789"
        Then The response code is 400
        And There is 0 Shelter created