Feature: Edit Shelter
  In order to update shelter information
  As a shelter manager
  I want to edit a shelter

  Background:
    Given There is a registered user with username "user" and password "existing" and email "user@sample.app"
    And There a shelter with name "Shelter 1" email "shelter@sample.com" and mobile "999999999"

  Scenario: Edit shelter:
    Given I login as "username" with password "password"
    And I am shelter volunteer with name "username"
    When I update the shelter with name "Shelter 1" to name "Another Shelter" email "another email" and mobile "another mobile"
    Then The response code is 200


