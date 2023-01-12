package stepDefinition;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramStep {
	private static final String BASE_URL = "https://lms-backend-service.herokuapp.com/lms";
	private static Response response;
	private static String jsonString;

	@Given("A Service with {string} is available")
	public void a_service_with_is_available(String string) {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		response=request.get("/allPrograms");
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Given("Request body at {string}")
	public void request_body_at(String string) {
		
	}

	@When("user modify {string},{string},{string}")
	public void user_modify(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("do a {string} request")
	public void do_a_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("user save {string}")
	public void user_save(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Validate status code")
	public void validate_status_code() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
