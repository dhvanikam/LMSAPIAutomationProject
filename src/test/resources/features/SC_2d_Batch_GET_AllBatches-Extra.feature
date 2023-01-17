@batch
Feature: Batch API Testing for LMS Batch module

  @batch_get_all
  Scenario: The user can get all batches do necessary validations
    Given A service with base "URL" is available
    And User set the header
    When User makes a "GETALLBATCHES" request with endpoint "/batches"
    Then do validations for batch

  @batch_get_all_404
  Scenario: The user get 404 response code for invalid path for GET Request all batches
    Given A service with base "URL" is available
    And User set the header
    When User makes a "GETALLBATCHES" request with endpoint "/batchinvalidpath/batches"
    Then User gets status code as 404
