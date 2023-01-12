@program
Feature: Program API testing

  Scenario: Save Program
    Given A Service with "URL" is available
    And Request body at "path"
    When user modify "Jan23-NinjaTrainees-Selenium-002","Learn Selenium","Active"
    And do a "Post" request
    Then user save "programId"
    And Validate status code