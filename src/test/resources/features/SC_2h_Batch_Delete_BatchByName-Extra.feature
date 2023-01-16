@batch
Feature: Batch API Testing for LMS Batch module

  @batch_delete_ByBatchName
  Scenario: The user (Admin) can delete a batch by batch Name
    Given A service with "URL" is available
    When User makes a "DELETE_BatchName" request with endpoint "batches/:(BatchName)"
    Then User gets status code as 200
