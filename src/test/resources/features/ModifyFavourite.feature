Feature: Modify Favourite
  In order to keep a register of pets i liked
  As a user
  I want to mark or unmark a pet as favourite

  Scenario: Mark as favourite
    Given I login as "testuser"
    When I press the favouritePet button
    Then The response code is 201
    And The entry on the relation "favourites" is created

  Scenario: Unmark as favourite
    Given I login as "testuser"
    When I press the favouritePet button for the pet "testpet"
    Then The response code is 201
    And The entry on the relation "favourites" is deleted

  Scenario: Mark as favourite while i'm not logged in
    Given I'm not logged in
    When I press the button to set/unset a pet as favourite
    Then The response code is 401
    And I see a message saying "In order to use that functionality, you need to be logged in"