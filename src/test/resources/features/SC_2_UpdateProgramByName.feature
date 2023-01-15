@program
Feature: Program API testing

  Scenario: The user (Admin) can update a program by Program Name
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/program/:(ProgramName)"
    Then User get status code as 200

  Scenario: The user (Admin) get 404 response code for invalid path
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/programinvalidpath/:(ProgramName)"
    Then User get status code as 404

  Scenario: The user (Admin) get 400 response code for invalid param
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/program/:(ProgramName)" with invalid param "test"
    Then User get status code as 400
