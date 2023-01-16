@program
Feature: Rest API testing for LMS Program module

  @program_get_ByID
  Scenario: The user (Admin) can get program by program Id and do necessary validations
    Given A service with "URL" is available
    When User make a "GET PROGRAM BY ID" request with endpoint "/programs/:ProgramID"
    Then do necessary validations

  @program_get_ByID_404
  Scenario: The user (Admin) get 404 response code for invalid path for get request by program Id
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "GET PROGRAM BY ID" request with endpoint "/programinvalidpath/:(ProgramID)"
    Then User get status code as 404	