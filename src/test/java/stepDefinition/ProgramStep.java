package stepDefinition;

import static org.hamcrest.Matchers.equalTo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Loggerload;

public class ProgramStep {
	private static final String BASE_URL = "https://lms-backend-service.herokuapp.com/lms";
	private static Response response;
	RequestSpecification request;
	private static String jsonString;

	@Given("A Service with {string} is available")
	public void a_service_with_is_available(String string) {
		RestAssured.baseURI = BASE_URL;
	}

	@Given("Request body at {string}")
	public void request_body_at(String string) {
		request = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).accept(ContentType.JSON);
	}

	@When("user modify {string},{string},{string}")
	public void user_modify(String string, String string2, String string3) {
		String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);

		String dateString = simpleDateFormat.format(new Date());
		System.out.println(dateString);
		Loggerload.info(dateString);
		/*
		 * { "programName": "Jan23-NinjaTrainees-Selenium-001", "programDescription":
		 * "Learn Selenium", "programStatus": "Active", "creationTime":
		 * "{{$isoTimestamp}}", "lastModTime": "{{$isoTimestamp}}" }
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);
		requestbody.put("programName", "Jan23-NinjaTrainees-Selenium-001");
		requestbody.put("programDescription","Learn Selenium");
		requestbody.put("programStatus", "Active");
		requestbody.put("creationTime","2023-01-12T16:02:34.753+00:00");
		requestbody.put("lastModTime","2023-01-12T16:02:34.753+00:00");
		
		Loggerload.info(requestbody.toJSONString());
		request.body(requestbody.toJSONString());
				
	}

	@When("do a {string} request")
	public void do_a_request(String string) {
		System.out.println(request);
		response = request.post("/saveprogram");
	}

	@Then("user save {string}")
	public void user_save(String string) {
		//Integer programID = response.body().asString().indexOf(0);
		
	}

	@Then("Validate status code")
	public void validate_status_code() {
		//Assert.assertEquals(response.body().asString(),"");
		//Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201");
		Assert.assertEquals(response.getStatusCode(),201);
	}

}
