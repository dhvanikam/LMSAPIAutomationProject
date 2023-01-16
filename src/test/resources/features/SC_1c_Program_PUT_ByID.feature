@program
Feature: Rest API testing for LMS Program module
  Description: The user (Admin) is able to update program by ID and do the validation

  @program_put_ByID
  Scenario: The user can update program by program ID
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT for PID" request with endpoint "/putprogram/:ProgramID"
    Then do necessary validations

  @program_put_ByID_404
  Scenario: The user (Admin) get 404 response code for invalid path
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT for PID" request with endpoint "/programinvalidpath/:(ProgramID)"
    Then User get status code as 404

  @program_put_ByID_400
  Scenario: The user (Admin) get 400 response code for invalid param
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/putprogram/:(ProgramID)" with invalid param "test"
    Then User get status code as 400
