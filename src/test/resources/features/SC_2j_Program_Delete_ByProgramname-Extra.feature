@program
Feature: Rest API testing for LMS Program module

  #@program_delete_ByName
  #Scenario: The user (Admin) can Delete a program by ProgramName
    #Given A service with "URL" is available
    #When User make a "DELETE BY PNAME" request with endpoint "/deletebyprogname/:(ProgramName)"
    #Then User get status code as 200

  @program_delete_ByName_404
  Scenario: The user (Admin) get 404 response code for invalid path for DELETE request by ProgramName
    Given A service with "URL" is available
    And User set the header
    When User make a "DELETE BY PNAME" request with endpoint "/programinvalidpath/:(ProgramName)"
    Then User get status code as 404	