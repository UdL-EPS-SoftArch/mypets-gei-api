Feature: Edit Shelter
  In order to update shelter information
  As a shelter manager
  I want to edit a shelter

  Background:
    Given There is a registered user with username "user" and password "existing" and email "user@sample.app"
    Given There is a registered already admin with username "admin" and password "admin" and email "admin@smaple.app"
    Given There is a registered volunteer with username "volunteer" and password "password"
    And There is already a shelter with name "Shelter 1" email "shelter@sample.com" and mobile "999999999"

  Scenario: Edit shelter without being logged in
    Given I'm not logged in
    When I update the shelter with name "Shelter 1" to name "Another Shelter" email "shelter@sample.app" and mobile "123123123"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Edit shelter with user
    Given I login as "client" with password "existing"
    When I update the shelter with name "Shelter 1" to name "Another Shelter" email "shelter@sample.app" and mobile "123123123"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Edit shelter with admin
    Given I login as "admin" with password "admin"
    When I update the shelter with name "Shelter 1" to name "Another Shelter" email "shelter@sample.app" and mobile "123123123"
    And I get the shelter with name "Another Shelter"
    Then The response code is 200

  Scenario: Edit shelter with volunteer
    Given I login as "volunteer" with password "password"
    When I update the shelter with name "Shelter 1" to name "Another Shelter" email "shelter@sample.app" and mobile "123123123"
    And I get the shelter with name "Another Shelter"
    Then The response code is 200

  Scenario: Edit shelter with missing new name
    Given I login as "admin" with password "admin"
    When I update the shelter with name "Shelter 1" to name "" email "shelter@sample.app" and mobile "123123123"
    Then The response code is 400
    And The error message is "must not be blank"

  Scenario: Edit shelter with missing new email
    Given I login as "admin" with password "admin"
    When I update the shelter with name "Shelter 1" to name "Another Shelter" email "" and mobile "123123123"
    Then The response code is 400
    And The error message is "must not be blank"

  Scenario: Edit shelter with missing new mobile
    Given I login as "admin" with password "admin"
    When I update the shelter with name "Shelter 1" to name "Another Shelter" email "shelter@sample.app" and mobile ""
    Then The response code is 400
    And The error message is "must not be blank"

