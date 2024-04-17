# Created by globox97 at 11/03/2024
Feature: View Pets Catalogue
  In order to choose a pet to adopt from those available
  As a User
  I want to view the pet catalogue


  Scenario: View Pets Catalogue without being logged in
    Given I'm not logged in
    When I request the Catalogue
    Then The response code is 200

    Scenario: View Pets Catalogue for a specific type of pet without being logged in
      Given I'm not logged in
      When I request the Catalogue with breed "cat"
      Then The response code is 200

  Scenario: View Pets Catalogue as a Client
    Given I login as "client" with password "password"
    When I request the Catalogue
    Then The response code is 200

  Scenario: View Pets Catalogue as a Shelter Volunteer
   Given There is a registered shelter volunteer with username "volunteer" that works at the shelter "shelter"
    Given I login with username "volunteer" and password "password"
    When I request the Catalogue for the specific shelter "shelter"
    And The response code is 200