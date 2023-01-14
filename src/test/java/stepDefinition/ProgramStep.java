package stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import utilities.CommonUtils;
import utilities.Loggerload;

public class ProgramStep {
	CommonUtils commnutils = new CommonUtils();


	private static final String BASE_URL = "https://lms-backend-service.herokuapp.com/lms";
	private static Response response;
	private static RequestSpecification request;
	JsonPath jsonPathEvaluator;

	Integer programID;
	String programName;
	//String programname;
	String programdesc;
	String programstatus;

	static String expProgramname;
	static String expProgramdesc;
	static String expProgramstatus;

	Scenario scenario;

	@Given("A service with {string} is available")
	public void a_service_with_is_available(String string) {
		RestAssured.baseURI = BASE_URL;

	}

	@Given("User set the header")
	public void user_set_the_header() {
		request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
	}

	@When("User add body with {string},{string},{string}")
	public void user_add_body_with(String prgname, String prgdesc, String prgmst) {
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

	@When("User make a {string} request with endpoint {string}")
	public void user_make_a_request_with_endpoint(String req, String endpoint) {
		switch (req) {
		case "POST":
			response = request.post(endpoint);
			break;

		case "GET":
			String updatedEndpoint = endpoint.replace(":ProgramId", (commnutils.getKeyValue("prgrmID").toString()));
			Loggerload.info(updatedEndpoint);
			response = request.get(updatedEndpoint);

			break;
		default:
			System.out.println("Unexpected request");
		}
	}
	@Then("User save response")
	public void user_save_response() {
		jsonPathEvaluator = response.jsonPath();
		int prgrmID = jsonPathEvaluator.get("programId");
		Loggerload.info(prgrmID);
		commnutils.setProgramID(prgrmID);

		String prgrmName = jsonPathEvaluator.get("programName");
		commnutils.setProgramName(prgrmName);
		Loggerload.info(prgrmName);

		String prgrmdesc = jsonPathEvaluator.get("programDescription");
		Loggerload.info(prgrmdesc);
		commnutils.setProgramDesc(prgrmdesc);

		String prgrmstatus = jsonPathEvaluator.get("programStatus");
		commnutils.setProgramStatus(prgrmstatus);
		Loggerload.info(prgrmstatus);
	}
	
	@Then("User get status code as {int}")
	public void user_get_status_code_as(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), int1);
	}

	@Then("Validate the response fields")
	public void validate_the_response_fields() {
		Loggerload.info(commnutils.getKeyValue("prgrmID").toString());
		Loggerload.info(commnutils.getKeyValue("prgrmName").toString());
		Loggerload.info(commnutils.getKeyValue("programDescription").toString());
		Loggerload.info(commnutils.getKeyValue("programStatus").toString());
//		Assert.assertEquals(commnutils.getProgramName("programName"), expProgramname);
//		Assert.assertEquals(commnutils.getProgramDesc("programdesc"), expProgramdesc);
//		Assert.assertEquals(commnutils.getProgramStatus("programstatus"), expProgramstatus);
	}
	
	//To Get All Programs
	@When("{string} request is made to {string}")
	public void request_is_made_to(String string, String string2) throws JsonMappingException, JsonProcessingException {
		RequestSpecification httpRequest = RestAssured.given();
		Response allProgramsresponse = httpRequest.get("/allPrograms");
		
		ResponseBody body = allProgramsresponse.getBody();
		
		Loggerload.info("response for request to get all Programs is : " +body.asPrettyString());
		
		response = allProgramsresponse;
		
	}
	
	@Then("do necessary validations")
	public void do_necessary_validations() {
		Assert.assertNotNull(response.getBody());
		Loggerload.info("response ContentType is : "+response.getContentType());
		Assert.assertEquals(response.getContentType(), "application/json");
		Loggerload.info("response statuscode : "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		Loggerload.info("response statusline is : "+response.getStatusLine());
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 ");
	}


}
