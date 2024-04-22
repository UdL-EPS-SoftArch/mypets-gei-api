Feature: Edit Pet

  As a Developer,
  I want to test the editing of a Pet entity,
  So that I can ensure the system updates the Pet information correctly.

  Background:
    Given the system is running

  Scenario: Update Pet's Name
    Given there is an existing Pet with name "OldName"
    When I update the Pet's name to "NewName"
    Then the system should confirm the Pet's name is "NewName"

  Scenario: Mark Pet as Adopted
    Given there is an existing Pet
    When I mark the Pet as adopted
    Then the system should confirm the Pet is marked as adopted

  Scenario: Change Pet's Color
    Given there is an existing Pet with color "OldColor"
    When I update the Pet's color to "NewColor"
    Then the system should confirm the Pet's color is "NewColor"

  Scenario: Update Pet's Size
    Given there is an existing Pet with size "OldSize"
    When I update the Pet's size to "NewSize"
    Then the system should confirm the Pet's size is "NewSize"

  Scenario: Change Pet's Weight
    Given there is an existing Pet with weight "OldWeight"
    When I update the Pet's weight to "NewWeight"
    Then the system should confirm the Pet's weight is "NewWeight"

  Scenario: Modify Pet's Age
    Given there is an existing Pet with age "OldAge"
    When I update the Pet's age to "NewAge"
    Then the system should confirm the Pet's age is "NewAge"

  Scenario: Update Pet's Description
    Given there is an existing Pet with description "OldDescription"
    When I update the Pet's description to "NewDescription"
    Then the system should confirm the Pet's description is "NewDescription"

  Scenario: Change Pet's Breed
    Given there is an existing Pet with breed "OldBreed"
    When I update the Pet's breed to "NewBreed"
    Then the system should confirm the Pet's breed is "NewBreed"
