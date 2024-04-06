Feature: Modify Favourite
  In order to keep a register of pets i liked
  As a user
  I want to mark or unmark a pet as favourite

  Background:
    Given There is a registered user with username "testuser" and password "password" and email "user@sample.app"
    Given There are two registered pets with ids "12345678" and "12345679"


  Scenario: Mark as favourite
    Given I login as "testuser" with password "password"
    When I press the favouritePet button for the pet with id "12345678"
    Then The entry on the relation "favourites" is created
    And The response code is 201

  Scenario: Unmark as favourite
    Given I login as "testuser" with password "password"
    Given User "testuser" has pet "12345679" set as favourite
    When I press the favouritePet button for the pet with id "12345679"
    Then The response code is 201
    And The entry on the relation "favourites" is deleted

  Scenario: Mark as favourite while i'm not logged in
    Given I'm not logged in
    When I press the favouritePet button for the pet with id "12345678"
    Then The response code is 401
    And The error message is "Unauthorized"

  # This should not happen in real implementation, so we'll just let the entry be created since:
    # 1. This won't happen on a real test case
    # 2. The "wrong" entry will just never get accessed
  Scenario: Mark as favourite a non existing pet
    Given I login as "testuser" with password "password"
    When I press the favouritePet button for the pet with id "87654321"
    Then The entry on the relation "favourites" is created
    And The response code is 201