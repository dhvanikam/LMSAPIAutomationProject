@program
Feature: Program API testing

  @program_get_all
  Scenario: The user (Admin) can get All Programs do necessary validations
    Given A service with "URL" is available
    When User make a "GETALLPROGRAMS" request with endpoint "/allPrograms"
    Then do necessary validations
