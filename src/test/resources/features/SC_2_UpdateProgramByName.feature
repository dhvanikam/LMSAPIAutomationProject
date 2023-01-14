@program
Feature: Program API testing

  Scenario: The user (Admin) can update a program by <programName>
    Given A service with "URL" is available
    When User add body with new program name and program description
    And User make a "PUT" request with endpoint "/program/:(ProgramName)"
    Then User get status code as 200
  
  
    
   