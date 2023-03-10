@program
Feature: Rest API testing for LMS Program module

  @program_put_ByPName
  Scenario: The user (Admin) can update a program by Program Name
    Given A service with "URL" is available
    And User set the header
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/program/:(ProgramName)"
    Then User get status code as 200

  @program_put_ByPName_404
  Scenario: The user (Admin) get 404 response code for invalid path for PUT request by Program Name
    Given A service with "URL" is available
    And User set the header
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/programinvalidpath/:(ProgramName)"
    Then User get status code as 404

  @program_put_ByPName_400
  Scenario: The user (Admin) get 400 response code for invalid param for PUT request by Program Name
    Given A service with "URL" is available
    And User set the header
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/program/:(ProgramName)" with invalid param "test"
    Then User get status code as 400
