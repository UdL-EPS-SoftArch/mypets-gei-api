Feature: Login User
  In order to use the app
  As a user
  I want to login myself to access it

  Scenario: Login already registered user
    Given I'm not logged in
    When I enter my registered username "user" with password "password"
    Then The response code is 201
    And I am successfully logged in

  Scenario: Login with a non existing user
    Given I'm not logged in
    When I enter username "sdafddsaf" and password "password"
    Then The response code is 404
    And I am prompt a error saying that the username or password are wrong

  Scenario: Login with a incorrect password
    Given I'm not logged in
    When I login with username "user" and password "afsdfsaafs"
    Then The response code is 404
    And I am prompt a error saying that the username or password are wrong

  Scenario: Login with empty username
    Given I'm not logged in
    When I log in with username "" and password "passsword"
    Then The response code is 406
    And The error message is "must not be blank"

   Scenario: Login with empty password
     Given I'm not logged in
     When I log in with username "user" and password ""
     Then The response code is 406
     And The error message is "must not be blank"