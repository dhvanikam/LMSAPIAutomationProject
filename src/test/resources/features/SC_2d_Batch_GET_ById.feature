@batch
Feature: Batch API testing

  @batch_get_ByID
  Scenario: The user (Admin) can get batches by batch Id and do necessary validations
    Given A service with base "URL" is available
    When User makes a "GETBATCHESBYBATCHID" request with endpoint "/batches/batchId/:BatchId"
    Then do validations for batch
