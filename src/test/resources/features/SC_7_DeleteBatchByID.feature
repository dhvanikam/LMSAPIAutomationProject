@batch_delete
Feature: Program API testing

  Scenario: The user (Admin) can update a program by Program Name
    Given A service with "URL" is available
    When User makes a "DELETE" request with endpoint "batches/:(BatchId)"
    Then User gets status code as 200
    
	