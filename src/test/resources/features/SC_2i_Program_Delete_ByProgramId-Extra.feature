@program
Feature: Rest API testing for LMS Program module

  @program_delete_ByID
  Scenario: The user (Admin) can Delete a program by ProgramID
    Given A service with "URL" is available
    And User set the header
    When User make a "DELETE" request with endpoint "/deletebyprogid/:(ProgramID)"
    Then User get status code as 200

  @program_delete_ByID_404
  Scenario: The user (Admin) get 404 response code for invalid path for DELETE request by ProgramID
    Given A service with "URL" is available
    And User set the header
    When User make a "DELETE" request with endpoint "/programinvalidpath/:(ProgramID)"
    Then User get status code as 404
