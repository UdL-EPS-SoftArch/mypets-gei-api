Feature: Validate Adoption (Admin or Shelter Volunteer)

  In order to validate an adoption
  As an admin or shelter volunteer
  I want to review and approve adoption requests

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is a registered admin with name "admin" and password "password" and email "admin@sample.app"
    And There is a registered volunteer with name "volunteer" and password "password" and email "volunteer@sample.app"
    And There is a dog with a pending adoption request from an user

  Scenario: Admin validates adoption request
    Given I login as "admin" with password "password"
    When I validate the adoption request
    And The response code is 204

  Scenario: Shelter volunteer validates adoption request
    Given I login as "volunteer" with password "password"
    When I validate the adoption request
    And The response code is 204

  Scenario: User validates adoption request
    Given I login as "username" with password "password"
    When I validate the adoption request
    And The response code is 403

  Scenario: User is not logged in
    Given I'm not logged in
    When I validate the adoption request
    Then The response code is 401