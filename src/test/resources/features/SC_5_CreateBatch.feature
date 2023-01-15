@batch
Feature: Batch API Testing for LMS Batch module
  Description: The user (Admin) is able to create two different batches and able to retrieve specific batch based on Batch ID

  Scenario Outline: The user can create different Batchs as <batchName>
    Given A service with base "URL" is available
    And User sets the header
    When User adding body with batch name, batch description, batch no of classes <batchStatus>
    And User makes a "POST" request with endpoint "/batches"
    Then User saves response
    And User get batch status code as 201
    And Validate required fields
    Given A service with base "URL" is available
    When User makes a "GET" request with endpoint "/batches/batchId/:BatchId" 
    Then User saves response 
    Then User get batch status code as 200
    And Validate required fields for get

    Examples: 
      | batchStatus |
      | "Active"    |
      | "Active"    |
