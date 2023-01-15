@batch
Feature: Batch API testing

  @batch_put_ByID
  Scenario: The user (Admin) can update a batch by Batch ID
    Given A service with "URL" is available
    When User add body with new batch name and batch description
    And User makes a "PUT_ByID" request with endpoint "/batches/:(BatchId)"
    Then User gets status code as 200