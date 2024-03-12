Feature: Logout User
  In order to exit the app
  As a user
  I want to logout myself and can't access to my resources

  Scenario:
    Given I login as "user" with password "password"
    When I logout of the app
    Then I don't have my session active
    And The response code is 401
    And The error message is "Unauthorized"

