package stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonMappingException;
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
	String programdesc;
	String programstatus;
	String createModTime;

	static String expProgramname;
	static String expProgramdesc;
	static String expProgramstatus;

	Scenario scenario;

	@Given("A service with {string} is available")
	public void a_service_with_is_available(String string) {
		RestAssured.baseURI = BASE_URL;
		Loggerload.info("A service with "+BASE_URL+" is available");
	}

	@Given("User set the header")
	public void user_set_the_header() {
		request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
		Loggerload.info("User set the header");
	}

//	@When("User add body with program name, program description and {string}")
//	public void user_add_body_with_program_name_program_description_and(String prgmst) {
//		Loggerload.info("User set the header");
//		createModTime = commnutils.getDateISOformat();
//		String pname = commnutils.getProgramName();
//		Loggerload.debug("construct the Program name - as per the pattern");
//		String programNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + pname + "-"
//				+ commnutils.getSerialNumber();
//		String prgdesc = "Learn " + pname;
//
//		expProgramname = programNameString;
//		expProgramdesc = prgdesc;
//		expProgramstatus = prgmst;
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		JSONObject requestbody = new JSONObject(map);
//
//		requestbody.put("programName", programNameString);
//		requestbody.put("programDescription", prgdesc);
//		requestbody.put("programStatus", prgmst);
//		requestbody.put("creationTime", createModTime);
//		requestbody.put("lastModTime", createModTime);
//
//		Loggerload.debug(requestbody.toJSONString());
//		request.body(requestbody.toJSONString());
//	}

	@SuppressWarnings("unchecked")
	@When("User add body with {string}, {string} and {string}")
	public void user_add_body_with_and(String prgmst, String pname, String prgdesc) {
		Loggerload.info("User add body with "+prgmst+", "+pname+" and "+prgdesc);
		createModTime = commnutils.getDateISOformat();
		//String pname = commnutils.getProgramName();
		Loggerload.debug("construct the Program name - as per the pattern");
		String programNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + pname + "-"
				+ commnutils.getSerialNumber();
		//String prgdesc = "Learn " + pname;

		expProgramname = programNameString;
		expProgramdesc = prgdesc;
		expProgramstatus = prgmst;

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);

		requestbody.put("programName", programNameString);
		requestbody.put("programDescription", prgdesc);
		requestbody.put("programStatus", prgmst);
		requestbody.put("creationTime", createModTime);
		requestbody.put("lastModTime", createModTime);

		Loggerload.debug(requestbody.toJSONString());
		request.body(requestbody.toJSONString());
	}



	@When("User make a {string} request with endpoint {string}")
	public void user_make_a_request_with_endpoint(String req, String endpoint) {
		System.out.println("endpoint : "+endpoint);

		switch (req) {
		case "POST":
			response = request.post(endpoint);
			break;

		case "GET":
			String updatedEndpoint = endpoint.replace(":ProgramId", programID.toString());
			Loggerload.info(updatedEndpoint);
			response = request.get(updatedEndpoint);
			break;
			
		case "GETALLPROGRAMS":	
				response = request.get(endpoint);
				Loggerload.info("response for request to get all Programs is : " +response.getBody().asPrettyString());
			break;

		case "PUT":
			String pname = CommonUtils.getKeyValue("prgrmName").toString();
			Loggerload.info("User do PUT request with endpoint: " +endpoint.replace(":(ProgramName)", pname));
			Loggerload.info("Program Name to be updated:" + pname);
			response = request.put(endpoint.replace(":(ProgramName)", pname));
			break;

		case "DELETE":
			String pid = CommonUtils.getKeyValue("prgrmID").toString();
			Loggerload.info("User do DELETE request with endpoint: " +endpoint.replace(":(ProgramID)", pid));
			Loggerload.info("Delete By Program ID :" + pid);
			response = request.delete(endpoint.replace(":(ProgramID)", pid));
			
			String pid1 = CommonUtils.getKeyValue("prgrmID1").toString();
			Loggerload.info("User do DELETE request with endpoint: " +endpoint.replace(":(ProgramID)", pid1));
			Loggerload.info("Delete By Program ID :" + pid1);
			response = request.delete(endpoint.replace(":(ProgramID)", pid1));
			break;

		default:
			System.out.println("Unexpected request");
		}
	}

	@Then("User save response")
	public void user_save_response() {
		ResponseBody body = response.getBody();
		jsonPathEvaluator = response.jsonPath();

		programID = jsonPathEvaluator.get("programId");
		Loggerload.info("Response : Program ID is " + programID);
		CommonUtils.setProgramID(programID);

		programName = jsonPathEvaluator.get("programName");
		CommonUtils.setProgramName(programName);
		Loggerload.info("Response : Program Name is " + programName);

		programdesc = jsonPathEvaluator.get("programDescription");
		Loggerload.info("Response : Program Description is " + programdesc);
		CommonUtils.setProgramDesc(programdesc);

		programstatus = jsonPathEvaluator.get("programStatus");
		CommonUtils.setProgramStatus(programstatus);
		Loggerload.info("Response : Program Status is " + programstatus);
	}

	@Then("User get status code as {int}")
	public void user_get_status_code_as(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), int1);
	}

	@Then("Validate the response fields")
	public void validate_the_response_fields() {
		Assert.assertEquals(programName, expProgramname);
		Assert.assertEquals(programdesc, expProgramdesc);
		Assert.assertEquals(programstatus, expProgramstatus);
	}

//_____________User update Program by ProgramName_____________________________

	
	@SuppressWarnings("unchecked")
	@When("User add body with new program name and program description")
	public void user_add_body_with_new_program_name_and_program_description() {

		createModTime = commnutils.getDateISOformat();
		String pname = commnutils.getProgramName();
		String programNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + pname + "-"
				+ commnutils.getSerialNumber();
		String prgdesc = "Study " + pname;
		String status = CommonUtils.getKeyValue("programStatus").toString();

//		String newPrgmname = programNameString;
//		String newPrgmnameexpProgramdesc = prgdesc;

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);

		requestbody.put("programName", programNameString);
		requestbody.put("programDescription", prgdesc);
		requestbody.put("programStatus", status);
		requestbody.put("creationTime", createModTime);
		requestbody.put("lastModTime", createModTime);

		request.body(requestbody.toJSONString());

		Loggerload.info("New Program Name :" + programNameString);
		Loggerload.info("New Program Description : " + prgdesc);

	}

	// To Get All Programs
	@When("{string} request is made to {string}")
	public void request_is_made_to(String string, String string2) throws JsonMappingException, JsonProcessingException {
		RequestSpecification httpRequest = RestAssured.given();
		Response allProgramsresponse = httpRequest.get("/allPrograms");

		ResponseBody body = allProgramsresponse.getBody();

		Loggerload.debug("response for request to get all Programs is : " + body.asPrettyString());

		response = allProgramsresponse;

	}

	@Then("do necessary validations")
	public void do_necessary_validations() {
		Assert.assertNotNull(response.getBody());
		Loggerload.info("response ContentType is : " + response.getContentType());
		Assert.assertEquals(response.getContentType(), "application/json");
		Loggerload.info("response statuscode : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		Loggerload.info("response statusline is : " + response.getStatusLine());
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 ");
	}

	@When("User make a {string} request with endpoint {string} with invalid param {string}")
	public void user_make_a_request_with_endpoint_with_invalid_param(String string, String endpoint, String pname) {
		Loggerload.info("Update By Program Name :" + pname);
		response = request.put(endpoint.replace(":(ProgramName)", pname));
	}

}
