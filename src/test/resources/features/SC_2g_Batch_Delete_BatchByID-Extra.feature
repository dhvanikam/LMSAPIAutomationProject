@batch
Feature: Batch API testing

  @batch_delete_ByID
  Scenario: The user (Admin) can update a batch by batch Name
    Given A service with "URL" is available
    When User makes a "DELETE" request with endpoint "batches/:(BatchId)"
    Then User gets status code as 200

  @batch_delete_ByID_404
  Scenario: The user (Admin) get 404 response code for invalid path
    Given A service with "URL" is available
    When User make a "DELETE" request with endpoint "/batchinvalidpath/:(BatchId)"
    Then User get status code as 404
