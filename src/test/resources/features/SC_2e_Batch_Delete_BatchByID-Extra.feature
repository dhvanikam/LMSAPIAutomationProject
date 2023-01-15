@batch
Feature: Program API testing

  @batch_delete_ByName
  Scenario: The user (Admin) can update a batch by batch Name
    Given A service with "URL" is available
    When User makes a "DELETE" request with endpoint "batches/:(BatchId)"
    Then User gets status code as 200
