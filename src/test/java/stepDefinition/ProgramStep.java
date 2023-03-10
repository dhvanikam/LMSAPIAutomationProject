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

	@Given("A service with {string} is available")
	public void a_service_with_is_available(String string) {
		RestAssured.baseURI = BASE_URL;
		Loggerload.info("A service with " + BASE_URL + " is available");
	}

	@Given("User set the header")
	public void user_set_the_header() {
		request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
		Loggerload.info("User set the header");
	}

	@SuppressWarnings("unchecked")
	@When("User add body with {string}, {string} and {string}")
	public void user_add_body_with_and(String prgmst, String pname, String prgdesc) {
		Loggerload.info("User add body with " + prgmst + ", " + pname + " and " + prgdesc);
		createModTime = commnutils.getDateISOformat();
		Loggerload.debug("construct the Program name - as per the pattern");
		String programNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + pname + "-"
				+ commnutils.getSerialNumber();

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
	public void user_make_a_request_with_endpoint(String req, String endpoint) throws InterruptedException {
		System.out.println("endpoint : " + endpoint);
		Thread.sleep(20);
		switch (req) {
		case "POST":
			
			response = request.post(endpoint);
			break;

		case "GET":
			Loggerload.info(endpoint.replace(":ProgramId", programID.toString()));
			response = request.get(endpoint.replace(":ProgramId", programID.toString()));
			break;

		case "GETALLPROGRAMS":
			response = request.get(endpoint);
			// Loggerload.info("response for request to get all Programs is : " +
			// response.getBody().asPrettyString());
			break;

		case "PUT":
			String pname = CommonUtils.getkeyvalueFromMap("prgrmName").toString();
			Loggerload.info("Update By Program Name :" + pname);
			Loggerload.info(endpoint.replace(":(ProgramName)", pname));
			response = request.put(endpoint.replace(":(ProgramName)", pname));
			break;

		case "PUT for PID":
			String pid2 = CommonUtils.getkeyvalueFromMap("prgrmID1").toString();
			Loggerload.info("Update By Program ID :" + pid2);
			Loggerload.info(endpoint.replace(":ProgramID", pid2));
			response = request.put(endpoint.replace(":ProgramID", pid2));
			break;

		case "DELETE":
			String pid = CommonUtils.getkeyvalueFromMap("prgrmID").toString();
			Loggerload.info("User do DELETE request with endpoint: " + endpoint.replace(":(ProgramID)", pid));
			Loggerload.info("Delete By Program ID :" + pid);
			response = request.delete(endpoint.replace(":(ProgramID)", pid));

			String pid1 = CommonUtils.getkeyvalueFromMap("prgrmID1").toString();
			Loggerload.info("User do DELETE request with endpoint: " + endpoint.replace(":(ProgramID)", pid1));
			Loggerload.info("Delete By Program ID :" + pid1);
			response = request.delete(endpoint.replace(":(ProgramID)", pid1));
			break;
			
		case "GET PROGRAM BY ID":
			String pid3 = CommonUtils.getkeyvalueFromMap("prgrmID").toString();
			Loggerload.info(pid3);
			String programIdEndpoint = endpoint.replace(":ProgramID", pid3);
			response = request.get(programIdEndpoint);
			// Loggerload.info("Response : "
			// +response.getStatusCode()+"\n"+response.getStatusLine());
			break;

		case "DELETE BY PNAME":
			String pname1 = CommonUtils.getkeyvalueFromMap("prgrmName").toString();
			Loggerload.info("Delete By Program Name :" + pname1);
			Loggerload.info(endpoint.replace(":(ProgramName)", pname1));
			response = request.put(endpoint.replace(":(ProgramName)", pname1));
			break;
		default:
			System.out.println("Unexpected request");
		}
	}

	@Then("User save response")
	public void user_save_response() {
		jsonPathEvaluator = response.jsonPath();

		programID = jsonPathEvaluator.get("programId");
		Loggerload.info("Response : Program ID is " + programID);
		CommonUtils.setkeyvalueIntMap("prgrmID", programID);

		programName = jsonPathEvaluator.get("programName");
		CommonUtils.setkeyvalueStringMap("prgrmName", programName);
		Loggerload.info("Response : Program Name is " + programName);

		programdesc = jsonPathEvaluator.get("programDescription");
		Loggerload.info("Response : Program Description is " + programdesc);
		CommonUtils.setkeyvalueStringMap("programDescription", programName);

		programstatus = jsonPathEvaluator.get("programStatus");
		CommonUtils.setkeyvalueStringMap("programStatus", programName);
		Loggerload.info("Response : Program Status is " + programstatus);
	}

	@Then("User get status code as {int}")
	public void user_get_status_code_as(Integer int1) {
		Assert.assertEquals(response.getContentType(), "application/json");
		Assert.assertNotNull(response.getBody());
		Loggerload.info("response ContentType is : " + response.getContentType());
		Assert.assertEquals(response.getContentType(), "application/json");
		Loggerload.info("response statuscode : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), int1);
	
	}


	@Then("Validate the response fields")
	public void validate_the_response_fields() {
		Loggerload.info("Validate the response fields Program name, Program description, Program status" );
		Assert.assertEquals(programName, expProgramname);
		Assert.assertEquals(programdesc, expProgramdesc);
		Assert.assertEquals(programstatus, expProgramstatus);
	}

//*********************Program PUT By ProgramName***********************/

	@SuppressWarnings("unchecked")
	@When("User add body with new program name and program description")
	public void user_add_body_with_new_program_name_and_program_description() {

		createModTime = commnutils.getDateISOformat();
		String pname = commnutils.getProgramName();
		String programNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + pname + "-"
				+ commnutils.getSerialNumber();
		String prgdesc = "Study " + pname;
		String status = CommonUtils.getkeyvalueFromMap("programStatus").toString();

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

	// *********************Program Get All***********************/

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

	// *********************Program Invalid cases***********************/

	@When("User make a {string} request with endpoint {string} with invalid param {string}")
	public void user_make_a_request_with_endpoint_with_invalid_param(String string, String endpoint, String pname) {
		Loggerload.info("Update By Program Name :" + pname);
		response = request.put(endpoint.replace(":(ProgramName)", pname));
	}

	@SuppressWarnings("unchecked")
	@When("User add body only with {string}")
	public void user_add_body_only_with(String pname) {
		Loggerload.info("User add body with " + pname);
		createModTime = commnutils.getDateISOformat();

		Loggerload.debug("construct the Program name - as per the pattern");
		String programNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + pname + "-"
				+ commnutils.getSerialNumber();

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);

		requestbody.put("programName", programNameString);
		requestbody.put("creationTime", createModTime);
		requestbody.put("lastModTime", createModTime);

		Loggerload.debug(requestbody.toJSONString());
		request.body(requestbody.toJSONString());
	}

}
