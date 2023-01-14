package stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

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
import utilities.Loggerload;
import utilities.CommonUtils;

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
	}

	@Given("User set the header")
	public void user_set_the_header() {
		request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
	}
	
	@When("User add body with program name, program description and {string}")
	public void user_add_body_with_program_name_program_description_and(String prgmst) {
		createModTime = commnutils.getDateISOformat();
		String pname = commnutils.getProgramName();
		Loggerload.debug("construct the Program name - as per the pattern");		
		String programNameString = commnutils.getMonth()+commnutils.getYear()+"-NinjaTrainees-"+pname+"-"+commnutils.getSerialNumber();
		String prgdesc = "Learn "+pname;
	
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
		switch (req) {
		case "POST":
			response = request.post(endpoint);
			break;

		case "GET":
			String updatedEndpoint = endpoint.replace(":ProgramId", programID.toString());
			Loggerload.info(updatedEndpoint);
			response = request.get(updatedEndpoint);
			break;
			
		case "PUT":
		    String pname=commnutils.getKeyValue("prgrmName").toString();
		    Loggerload.info("Update By Program Name :"+pname);
            String updatedEndpoint2 = endpoint.replace(":(ProgramName)",pname);
            Loggerload.info(updatedEndpoint2);
			response = request.put(updatedEndpoint2);
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
		Loggerload.info(programID);
		commnutils.setProgramID(programID);

		programName = jsonPathEvaluator.get("programName");
		commnutils.setProgramName(programName);
		Loggerload.info(programName);

		programdesc = jsonPathEvaluator.get("programDescription");
		Loggerload.info(programdesc);
		commnutils.setProgramDesc(programdesc);

		programstatus = jsonPathEvaluator.get("programStatus");
		commnutils.setProgramStatus(programstatus);
		Loggerload.info(programstatus);
	}

	@Then("User get status code as {int}")
	public void user_get_status_code_as(Integer int1) {
	Assert.assertEquals(response.getStatusCode(),int1);
	}

	@Then("Validate the response fields")
	public void validate_the_response_fields() {
		Assert.assertEquals(programName, expProgramname);
		Assert.assertEquals(programdesc, expProgramdesc);
		Assert.assertEquals(programstatus, expProgramstatus);
	}
	
//_____________User update Program by ProgramName_____________________________
	
	@When("User add body with new program name and program description")
	public void user_add_body_with_new_program_name_and_program_description() {
		
		createModTime = commnutils.getDateISOformat();
		String pname = commnutils.getProgramName();
		String programNameString = commnutils.getMonth()+commnutils.getYear()+"-NinjaTrainees-"+pname+"-"+commnutils.getSerialNumber();
		String prgdesc = "Study "+pname;
		String status=commnutils.getKeyValue("programStatus").toString();
		
		expProgramname=programNameString;
		expProgramdesc=prgdesc;
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);

		
		requestbody.put("programName", programNameString);
		requestbody.put("programDescription", prgdesc);
		requestbody.put("programStatus", status);
		requestbody.put("creationTime", createModTime);
		requestbody.put("lastModTime", createModTime);

		
		request.body(requestbody.toJSONString());
		
		Loggerload.info("New Program Name :"+expProgramname);
		Loggerload.info("New Program Description : "+expProgramdesc);
	    
	}

	   
	
}
