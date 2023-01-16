@batch
Feature: Batch API testing

  @batch_get_ByID
  Scenario: The user (Admin) can get batches by batch Id and do necessary validations
    Given A service with base "URL" is available
    When User makes a "GETBATCHESBYBATCHID" request with endpoint "/batches/batchId/:BatchId"
    Then do validations for batch

  @batch_get_400
  Scenario Outline: The user (Admin) get 400 response code for invalid path
    Given A service with "URL" is available
    When User make a "GETBATCHESBYBATCHID" request with endpoint "batchesinvalidpath/batches/batchId/:BatchId"
    Then User get status code as 400