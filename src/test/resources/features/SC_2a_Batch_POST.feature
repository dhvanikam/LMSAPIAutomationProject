@batch
Feature: Batch API Testing for LMS Batch module
  Description: The user create two batches, The user can create a batch only if the program is present. As the batch is associated with program Id.

  @batch_post
  Scenario Outline: The user can create different Batchs as <batchname>
    Given A service with base "URL" is available
    And User sets the header
    When User adding body with batch no of classes, <batchname>, <batchdescription>, <batchStatus>
    And User makes a "POST" request with endpoint "/batches"
    Then User saves response
    And User get batch status code as 201
    And Validate required fields
    Given A service with base "URL" is available
    When User makes a "GET" request with endpoint "/batches/batchId/:BatchId"
    Then User saves response
    Then User get batch status code as 200
    And Validate required fields

    Examples: 
      | batchStatus | batchname  | batchdescription |
      | "Active"    | "SDET05" | "Learn Selenium" |
      | "Active"    | "DAML55"     | "Learn Java"     |

  @batch_post_404
  Scenario Outline: The user get 404 response code for invalid path for POST batch
    Given A service with "URL" is available
    And User set the header
    When User adding body with batch name, batch description, batch no of classes <batchStatus>
    And User make a "POST" request with endpoint "/batchesinvalid"
    Then User get status code as 404

    Examples: 
      | batchStatus |
      | "Active"    |

  @batch_post_400
  Scenario Outline: The user get 400 response code for invalid body for POST batch
    Given A service with "URL" is available
    And User set the header
    When User add body only with <batchName>
    And User make a "POST" request with endpoint "/batches"
    Then User get status code as 400

    Examples: 
      | batchName |
      | "SDET05"  |
