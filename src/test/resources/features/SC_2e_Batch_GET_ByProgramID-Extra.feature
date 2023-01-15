@batch
Feature: Batch API testing

  @batch_get_ByProgramID
  Scenario: The user (Admin) can get batches by program Id and do necessary validations
    Given A service with base "URL" is available
    When User saves response
    And User makes a "GETBATCHESBYPROGRAMID" request with endpoint "/batches/program/:(ProgramId)"
    Then do validations for batch