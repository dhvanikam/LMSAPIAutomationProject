@program
Feature: Program API testing

  Scenario: Save Program
    Given A Service with "URL" is available
    And Request body at "path"
    When user modify "programName","creationTime","lastModTime"
    And do a "Post" request
    Then user save "programId"
    And Validate status code
