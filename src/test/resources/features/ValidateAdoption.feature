Feature: Validate Adoption
  In order to ensure responsible pet ownership
  As an admin or shelter volunteer
  I want to validate pet adoptions

  Scenario: Validate adoption request
    Given There is a pending adoption request for pet with ID "123" by user "user"
    And I'm logged in as admin or shelter volunteer
    When I validate the adoption request for pet with ID "123" by user "user"
    Then The response code is 200
    And The adoption status for pet with ID "123" is updated to "user"
    And User "user" is notified about the validation of the adoption

  Scenario: Validate adoption request for non-existent pet
    Given There is no pending adoption request for a non-existent pet with ID "999"
    And I'm logged in as admin or shelter volunteer
    When I attempt to validate the adoption request for pet with ID "999" by user "user"
    Then The response code is 404
    And The error message is "Pet with ID '999' not found"

  Scenario: Validate adoption request for already adopted pet
    Given There is a pending adoption request for pet with ID "456" by user "user"
    And the pet with ID "456" is already marked as adopted
    And I'm logged in as admin or shelter volunteer
    When I attempt to validate the adoption request for pet with ID "456" by user "user"
    Then The response code is 409
    And The error message is "Pet with ID '456' is already adopted"

  Scenario: Validate adoption request without proper authorization
    Given There is a pending adoption request for pet with ID "789" by user "user"
    And I'm not logged in or logged in as a regular user, not admin or shelter volunteer
    When I attempt to validate the adoption request for pet with ID "789" by user "user"
    Then The response code is 403
    And The error message is "Unauthorized access, admin or shelter staff privilege required"
