# Created by globox97 at 11/03/2024
Feature: View Pets Catalogue
  In order to view the Pets Catalogue
  As a User
  I want to view the pet catalogue

  Background:
    Given there is a registered client with username "client" and password "password" and email "user@domain.com"
    Given there is a registered volunteer with username "volunteer" and password "password" and email "volunteer@domain.com"
    Given there are pets in a shelter


  Scenario: View Pets Catalogue as a Client
    Given I login as "client" with password "password"
    When I
    Then The response code is 200

  Scenario: View Pets Catalogue without being logged in
    Given I'm not logged in

  Scenario: View Pets Catalogue as a Shelter Volunteer
    Given I'm logged in as a Volunteer

  Scenario: View Pets Catalogue from a specific