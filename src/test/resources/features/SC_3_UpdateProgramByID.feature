@program
Feature: Rest API testing for LMS Program module
  Description: The user (Admin) is able to update program by ID and do the validation

  Scenario: The user can update program by program ID
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT for PID" request with endpoint "/putprogram/:ProgramID"
    Then  do necessary validations