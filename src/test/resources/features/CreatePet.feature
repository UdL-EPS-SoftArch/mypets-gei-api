Feature: Create a Pet without fields

  As a Developer,
  I want to test the creation of a Pet entity,
  So that I can ensure the system handles empty fields appropriately.

  Background:
    Given the system is running

  Scenario: Create a Pet without any fields
    Given I have a Pet entity
    When I attempt to create a Pet without providing any details
    Then the system should reject the request
    And the response status should be 400

  Scenario: Create a Pet without login
    Given I have a Pet entity
    When I attempt to create a Pet without login
    Then the system should handle the request without authentication
    And the response status should be 401
    And the error message should indicate unauthorized access
