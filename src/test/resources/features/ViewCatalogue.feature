# Created by globox97 at 11/03/2024
Feature: View Pets Catalogue
  In order to view the Pets Catalogue
  As a User
  I want to view the pet catalogue

  Background:
    Given there is a registered client with username "client" and password "password" and email "user@domain.com"
    Given there is a registered volunteer with username "volunteer" and password "password" and email "volunteer@domain.com"
    And There is a Pet with breed "cat"
    And There is a Pet with breed "dog"


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
    Given I'm logged in as a Volunteer
    When I request the Catalogue
    Then I'm shown the Catalogue from the shelter I work in
    And The response code is 200