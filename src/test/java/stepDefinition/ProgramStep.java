package stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Loggerload;
import utilities.CommonUtils;

public class ProgramStep {
	CommonUtils commnutils = new CommonUtils();

	private static final String BASE_URL = "https://lms-backend-service.herokuapp.com/lms";
	private static Response response;
	RequestSpecification request;
	JsonPath jsonPathEvaluator;

	Integer programID;
	String programname;
	String programdesc;
	String programstatus;

	static String expProgramname;
	static String expProgramdesc;
	static String expProgramstatus;

	@Given("A service with {string} is available")
	public void a_service_with_is_available(String string) {
		RestAssured.baseURI = BASE_URL;
	}

	@Given("Request body at {string}")
	public void request_body_at(String string) {
		request = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).accept(ContentType.JSON);
	}

	@When("User add {string},{string},{string}")
	public void user_add(String prgname, String prgdesc, String prgmst) {
		String createModTime = commnutils.getDateISOformat();
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);

		String programNameString = prgname + commnutils.getRandomint();
		expProgramname = programNameString;
		expProgramdesc = prgdesc;
		expProgramstatus = prgmst;
		requestbody.put("programName", programNameString);
		requestbody.put("programDescription", prgdesc);
		requestbody.put("programStatus", prgmst);
		requestbody.put("creationTime", createModTime);
		requestbody.put("lastModTime", createModTime);

		Loggerload.info(requestbody.toJSONString());
		request.body(requestbody.toJSONString());
	}

	@When("User make a {string} request to {string}")
	public void user_make_a_request_to(String req, String endpoint) {
		switch (req) {
		case "POST":
			response = request.post(endpoint);
			break;
		case "GET":
			String updatedEndpoint = endpoint.replace(":ProgramId", (programID.toString()));
			Loggerload.info(updatedEndpoint);
			response = request.get(updatedEndpoint);
			break;
		default:
			System.out.println("Unexpected request");
		}

	}

	@Then("User save {string}")
	public void user_save(String string) {
		jsonPathEvaluator = response.jsonPath();
		programID = jsonPathEvaluator.get("programId");
		Loggerload.info(programID);
	}

	@Then("User get status code as {int}")
	public void user_get_status_code_as(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), int1);
	}

	@Then("Validate the response fields")
	public void validate_the_response_fields() {
		jsonPathEvaluator = response.jsonPath();
		String programname = jsonPathEvaluator.get("programName");
		String programdesc = jsonPathEvaluator.get("programDescription");
		String programstatus = jsonPathEvaluator.get("programStatus");

		Assert.assertEquals(programname, expProgramname);
		Assert.assertEquals(programdesc, expProgramdesc);
		Assert.assertEquals(programstatus, expProgramstatus);
	}

}
