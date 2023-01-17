@batch
Feature: Batch API Testing for LMS Batch module

  @batch_get_ByID
  Scenario: The user can get batches by batch Id and do necessary validations
    Given A service with base "URL" is available
    And User set the header
    When User makes a "GETBATCHESBYBATCHID" request with endpoint "/batches/batchId/:BatchId"
    Then do validations for batch

  @batch_get_ByID_404
  Scenario Outline: The user get 404 response code for invalid path for GET Request by batch Id
    Given A service with base "URL" is available
    And User set the header
    When User makes a "GETBATCHESBYBATCHID" request with endpoint "batchesinvalidpath/batches/batchId/:BatchId"
    Then User get batch status code as 404
    
  @batch_get_ByID_400
  Scenario Outline: The user get 400 response code for invalid path for GET Request by batch Id
    Given A service with base "URL" is available
    And User set the header
    When User makes a "GETBATCHESBYBATCHID" request with endpoint "/batches/batchId/:BatchId" with endpoint 9
    Then User get batch status code as 400