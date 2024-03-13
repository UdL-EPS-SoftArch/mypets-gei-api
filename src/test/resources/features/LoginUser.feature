Feature: Login User
  In order to use the app
  As a user
  I want to login myself to access it

  Scenario: Login already registered user
    Given There is a registered user with username "user" and password "password"
    And I'm not logged in
    When I login with username "user" and password "password"
    Then The response code is 200
    And I can login with username "user" and password "password"

  Scenario: Login with a non existing user
    Given There isn't registered user with username "anonymous"
    And I'm not logged in
    When I login with username "anonymous" and password "password"
    Then The response code is 401
    #And The error message is "wrong user or password"

  Scenario: Login with a incorrect password
    Given There is a registered user with username "user" and password "password"
    And I'm not logged in
    When I login with username "user" and password "anonymous"
    Then The response code is 401
    #And The error message is "wrong user or password"

  Scenario: Login with empty username
    Given I'm not logged in
    When I login with username "" and password "password"
    Then The response code is 401

   Scenario: Login with empty password
     Given There is a registered user with username "user" and password "password"
     And I'm not logged in
     When I login with username "user" and password ""
     Then The response code is 401
