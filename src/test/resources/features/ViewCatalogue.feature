# Created by globox97 at 11/03/2024
Feature: View Pets Catalogue
  In order to make an adoption
  As a User
  I want to view the pet catalogue

  Background:
    Given there is a registered user with username "user" and password "password" and email "user@user.com"

  Scenario: View Pets Catalogue
    Given I can login with username "user" and password "password"
    And The response code is 200

  Scenario: View Pets Catalogue without being logged in
    Given I'm not logged in
  Scenario: View Pets Catalogue from a specific Shelter
    Given
  Scenario: View Pets Catalogue from a specific