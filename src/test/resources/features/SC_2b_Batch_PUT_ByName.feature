@batch
Feature: Batch API Testing for LMS Batch module

  @batch_put_ByName
  Scenario: The user can update a batch by Batch Name
    Given A service with base "URL" is available
    And User set the header
    When User add body with new batch name and batch description
    And User makes a "PUT" request with endpoint "/batches/batchName/:(BatchName)"
    Then User gets status code as 200
    And Validate necessary fields in response

  @batch_put_ByName_404
  Scenario: The user get 404 response code for invalid path for PUT request by Batch Name
    Given A service with base "URL" is available
    And User set the header
    When User add body with new batch name and batch description
    And User makes a "PUT" request with endpoint "/batchesinvalid/batchName/:(BatchName)"
    Then User get batch status code as 404

  @batch_put_ByName_400
  Scenario: The user get 400 response code for invalid param for PUT request by Batch Name
    Given A service with base "URL" is available
    And User set the header
    When User add body with new batch name and batch description
    And User makes a "PUT" request with endpoint "/batches/batchName/:(BatchName)" with invalid param "testinvalid"
    Then User get batch status code as 400
