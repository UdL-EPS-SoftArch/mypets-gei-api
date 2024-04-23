Feature: Process Adoption (User)
  In order to adopt a pet
  As a user
  I want to process adoption requests

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"


  Scenario: User is not logged in
    Given I'm not logged in
    And There is an available pet with name "pet" i want to adopt
    When I request to adopt the pet with name "pet"
    Then The response code is 401

  Scenario: Process adoption request for available pet
    Given I login as "username" with password "password"
    And There is an available pet with name "pet" i want to adopt
    When I request to adopt the pet with name "pet"
    Then The response code is 201


  Scenario: Process adoption request without pet
    Given I login as "username" with password "password"
    When I request to adopt without a pet
    Then The response code is 409



  Scenario: Process adoption request for already adopted pet
    Given I login as "username" with password "password"
    And The pet with name "pet" is already adopted
    When I request to adopt the pet with name "pet"
    Then The response code is 409
