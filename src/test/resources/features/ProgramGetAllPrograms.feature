@program
Feature: Program API testing

  Scenario: Get All Programs do necessary validations
    Given A service with "URL" is available
    When "GET" request is made to "Get All Programs"
    Then do necessary validations  
    
    
    