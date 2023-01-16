@program
Feature: Rest API testing for LMS Program module

  @program_get_all
  Scenario: The user (Admin) can get All Programs do necessary validations
    Given A service with "URL" is available
    When User make a "GETALLPROGRAMS" request with endpoint "/allPrograms"
    Then do necessary validations

  @program_get_404
  Scenario Outline: The user (Admin) get 404 response code for invalid path for GET request All Programs
    Given A service with "URL" is available
    When User make a "GETALLPROGRAMS" request with endpoint "programinvalidpath/allPrograms"
    Then User get status code as 404
