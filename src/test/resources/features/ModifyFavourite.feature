Feature: Modify Favourite
  In order to keep a register of pets i liked
  As a user
  I want to mark or unmark a pet as favourite

  Scenario: Mark as favourite
    Given I login as "testuser"
    When I press the favouritePet button for the pet with id "12345678"
    Then The response code is 201
    And The entry on the relation "favourites" is created

  Scenario: Unmark as favourite
    Given I login as "testuser"
    When I press the favouritePet button for the pet with id "12345678"
    Then The response code is 201
    And The entry on the relation "favourites" is deleted

  Scenario: Mark as favourite while i'm not logged in
    Given I'm not logged in
    When I press the favouritePet button for the pet with id "12345678"
    Then The response code is 401
    And The error message is "In order to use that functionality, you need to be logged in"

  Scenario: Mark as favourite a non existing pet
    Given I login as "testuser"
    When I press the favouritePet button for the pet with id "12345678"
    Then The response code is 402
    And The error message is "Something went wrong, try again later"