Feature: Validate ShelterCertificate
  As an Admin
  I want to validate a ShelterCertificate
  So that I can ensure Its validity and authenticity

  Background: Exists a shelter with name "test"
    Given A shelter with name "test"

  Scenario: Validating a correct shelter certificate
    Given a shelter certificate associated with a shelter with name "test" with valid information created by a shelter volunteer
    Then the admin should verify the certificate validity associated with a shelter with name "test"
    And the admin should approve the certificate associated to the shelter with name "test"

  Scenario: Validating a incorrect shelter certificate
    Given a shelter certificate associated with a shelter with name "test" with invalid information created by a shelter volunteer
    Then the admin should verify the wrong certificate validity associated with a shelter with name "test"
    And the admin should reject the certificate associated to the shelter with name "test"

