Feature: Add New Volunteer to Shelter

  Scenario: Add a new Volunteer to an existing Shelter
    Given a Shelter with ID <shelterId> exists
    When a new Volunteer is added to Shelter with ID <shelterId>
    Then the Volunteer should be associated with Shelter with ID <shelterId>

  Scenario: Attempt to add a new Volunteer to a non-existing Shelter
    Given a Shelter with ID <shelterId> does not exist
    When a new Volunteer is added to Shelter with ID <shelterId>
    Then the system should indicate that the Shelter with ID <shelterId> does not exist

  Examples:
    | shelterId |
    | 1         |
    | 2         |
    | 100       |
