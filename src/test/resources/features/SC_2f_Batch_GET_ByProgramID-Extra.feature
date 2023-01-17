@batch
Feature: Batch API Testing for LMS Batch module

  @batch_get_ByProgramID
  Scenario: The user can get batches by program Id and do necessary validations
    Given A service with base "URL" is available
    When User saves response
    And User makes a "GETBATCHESBYPROGRAMID" request with endpoint "/batches/program/:(ProgramId)"
    Then do validations for batch
    
 @batch_get_ByProgramID_400
  Scenario Outline: The user get 400 response code for invalid path for GET Request by Program Id
    Given A service with base "URL" is available
    When User makes a "GETBATCHESBYPROGRAMID" request with endpoint "/batchesinvalid/program/:(ProgramId)"
    Then User get status code as 400
    
 @batch_get_ByProgramID_400
  Scenario Outline: The user get 404 response code for invalid path for GET Request by Program Id
    Given A service with base "URL" is available
    When User makes a "GETBATCHESBYPROGRAMID" request with endpoint "/batchesinvalid/program/:(ProgramId)" with endpoint 9
    Then User get status code as 404