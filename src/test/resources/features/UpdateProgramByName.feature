@program
Feature: Program API testing

  Scenario: Update Program by Name
    Given A Service with "https://lms-backend-service.herokuapp.com/lms" 
    And Request body at "programName"
    When "programName,creationTime,last,ModTime" are modified
    And "PUT" request is made
    Then Save "programId"
    And Validate status code
    And Validate "programName,programDescription,programStatus"
    Given A Service with "URL"(GET Program by Id)
    When "Get" request is made
    Then Validate status code
    And Validate "programName,programDescription,programStatus"
