Feature: Disable User
  In order to keep the user from using the system
  As an admin
  I want to disable a user

  Background: It exists an admin created
    Given There is a registered admin with name "admin" and password "123456789" and email "admin@admin.com"
    And There is a registered user with username "user1" and password "123456789" and email "user1@user1.com"
    And There is a registered user with username "user2" and password "123456789" and email "user2@user.com"

  Scenario: Admin disables a user
    Given I can login with username "admin" and password "123456789"
    When I disable the user with username "user1"
    Then The user with username "user" should be disabled
    And The response code is 200

  Scenario: Admin disables a user that does not exist
    Given I can login with username "admin" and password "123456789"
    When I disable the user with username "nonexistent"
    Then The response code is 404

  Scenario: A non-admin tries to disable a user
    Given I can login with username "user1" and password "123456789"
    When I disable the user with username "user2"
    Then The response code is 403