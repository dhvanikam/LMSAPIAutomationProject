@program
Feature: Program API testing

  Scenario: The user (Admin) can get All Programs do necessary validations
    Given A service with "URL" is available
    When User make a "GET" request with endpoint "/allPrograms"
    Then do necessary validations  
    
    
    