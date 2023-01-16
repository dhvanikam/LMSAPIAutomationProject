@program
Feature: Program API testing

  #@program_delete_ByName
  #Scenario: The user (Admin) can Delete a program by ProgramName
    #Given A service with "URL" is available
    #When User make a "DELETE BY PNAME" request with endpoint "/deletebyprogname/:(ProgramName)"
    #Then User get status code as 200

  @program_delete_ByName_404
  Scenario: The user (Admin) get 404 response code for invalid path
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "DELETE BY PNAME" request with endpoint "/programinvalidpath/:(ProgramName)"
    Then User get status code as 404	