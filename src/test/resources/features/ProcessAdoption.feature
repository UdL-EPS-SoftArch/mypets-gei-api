Feature: Process Adoption (User)

  In order to adopt a pet
  As a user
  I want to process adoption requests

  Scenario: Process adoption request for available pet
    Given There is an available pet with ID "123"
    And I'm logged in as user "user"
    When I request to adopt the pet with ID "123"
    Then The response code is 200
    And I receive a confirmation message for adopting the pet

  Scenario: Process adoption request for non-existent pet
    Given There is no pet with ID "999"
    And I'm logged in as user "user"
    When I request to adopt the pet with ID "999"
    Then The response code is 404
    And I receive an error message that the pet does not exist

  Scenario: Process adoption request for already adopted pet
    Given There is a pet with ID "456" and it is already adopted
    And I'm logged in as user "user"
    When I request to adopt the pet with ID "456"
    Then The response code is 409
    And I receive an error message that the pet is already adopted

  Scenario: Process adoption request without proper authorization
    Given There is an available pet with ID "789"
    And I'm not logged in
    When I attempt to request to adopt the pet with ID "789"
    Then The response code is 403
    And I receive an error message that I need to be logged in to process adoption requests
