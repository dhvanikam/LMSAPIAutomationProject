@program
Feature: Program API testing

  Scenario: The user (Admin) can update a program by Program Name
    Given A service with "URL" is available
    When User add body with new batch name and batch description
    And User make a "PUT" request with endpoint "/batches/batchName/:(BatchName)" for batch
