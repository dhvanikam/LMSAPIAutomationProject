@program
Feature: Program API testing

  @program_delete_ByID
  Scenario: The user (Admin) can Delete a program by ProgramID
    Given A service with "URL" is available
    When User make a "DELETE" request with endpoint "/deletebyprogid/:(ProgramID)"
    Then User get status code as 200
