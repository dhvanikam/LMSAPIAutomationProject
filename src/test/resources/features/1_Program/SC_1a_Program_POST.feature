@program
Feature: Rest API testing for LMS Program module
  Description: The user (Admin) is able to create two different programs and able to retrieve specific program based on Program ID

	@program_post
  Scenario Outline: The user (Admin) can create different programs : <programName>
    Given A service with "URL" is available
    And User set the header
    When User add body with <programName>, <program description> and <programStatus>
    And User make a "POST" request with endpoint "/saveprogram"
    Then User save response
    And User get status code as 201
    And Validate the response fields
    Given A service with "URL" is available
    When User make a "GET" request with endpoint "/programs/:ProgramId"
    Then User get status code as 200

    Examples: 
      | programStatus | programName | program description |
      | "Active"      | "Selenium"  | "Learn Selenium"    |
      | "InActive"    | "Java"      | "Learn Java"        |