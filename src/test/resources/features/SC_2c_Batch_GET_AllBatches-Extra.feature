@batch
Feature: Batch API testing

  @batch_get_all
  Scenario: The user (Admin) can get all batches do necessary validations
    Given A service with base "URL" is available
    When User makes a "GETALLBATCHES" request with endpoint "/batches"
    Then do validations for batch
