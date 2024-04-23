Feature: Validate ShelterCertificate
  As an Admin
  I want to validate a ShelterCertificate
  So that I can ensure Its validity and authenticity

  Background: Exists a shelter with name "test"
    Given A shelter with name "test"
    And An admin with username "admin" and password "123456789"

  Scenario: Validating a correct shelter certificate
    Given a shelter certificate associated with a shelter with name "test" with valid information created by a shelter volunteer
    When the admin with username "admin" and password "123456789" verifies the certificate validity associated with a shelter with name "test"
    Then The response code is 200

  Scenario: Validating a incorrect shelter certificate
    Given a shelter certificate associated with a shelter with name "test" with invalid information created by a shelter volunteer
    When the admin with username "admin" and password "123456789" rejects the certificate validity associated with a shelter with name "test"
    Then The shelter certificate from shelter with name "test" is not validated
    And The response code is 200

