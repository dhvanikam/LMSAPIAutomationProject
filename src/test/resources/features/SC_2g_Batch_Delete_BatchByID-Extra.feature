@batch
Feature: Batch API Testing for LMS Batch module

  @batch_delete_ByID
  Scenario: The user can delete a batch by batch ID
    Given A service with base "URL" is available
    When User makes a "DELETE" request with endpoint "batches/:(BatchId)"
    Then User get batch status code as 200

  @batch_delete_ByID_404
  Scenario: The user get 404 response code for invalid path for DELETE request by batch ID
    Given A service with base "URL" is available
    When User make a "DELETE" request with endpoint "/batchinvalidpath/:(BatchId)"
    Then User get status code as 404
