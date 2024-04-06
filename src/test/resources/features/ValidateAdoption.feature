Feature: Validate Adoption (Admin or Shelter Volunteer)

  In order to validate an adoption
  As an admin or shelter volunteer
  I want to review and approve adoption requests

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is an available adoption with name "adoption"
    And There is a pending adoption request for pet "pet" from user "user@sample.app"

  Scenario: Admin validates adoption request
    Given I login as "admin" with password "admin123"
    When I validate the adoption request for pet "pet" from user "user@sample.app"
    Then The adoption request for pet "pet" is approved
    And The response code is 200

  Scenario: Shelter volunteer validates adoption request
    Given I login as "volunteer" with password "volunteer123"
    When I validate the adoption request for pet "pet" from user "user@sample.app"
    Then The adoption request for pet "pet" is approved
    And The response code is 200