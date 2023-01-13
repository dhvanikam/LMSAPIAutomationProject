@program
Feature: Rest API testing for LMS Program module
  Description: The user (Admin) is able to create two different programs and able to retrieve specific program based on Program ID

  Scenario Outline: The user (Admin) can create different programs as <programName>
    Given A service with "URL" is available
    And User set the header
    When User add body with <programName>,<programDescription>,<programStatus>
    And User make a "POST" request with endpoint "/saveprogram"
    Then User save response
    And User get status code as 201
    And Validate the response fields

    #Given A service with "URL" is available
    #When User make a "GET" request to "/programs/:ProgramId"
    #Then  User get status code as 200
    Examples: 
      | programName                     | programDescription | programStatus |
      | "Jan23-NinjaTrainees-Selenium-" | "Learn Selenium"   | "Active"      |
      | "Jan23-NinjaTrainees-Java-"     | "Learn Java"       | "Active"      | 

  Scenario: The user can retrieve specific program based on Program ID
    Given A service with "URL" is available
    When User make a "GET" request with endpoint "/programs/:ProgramId"
    Then User get status code as 200
