package stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import utilities.CommonUtils;
import utilities.Loggerload;

public class BatchStep {
	CommonUtils commnutils = new CommonUtils();

	private static final String BASE_URL = "https://lms-backend-service.herokuapp.com/lms";
	private static Response response;
	private static RequestSpecification request;
	JsonPath jsonPathEvaluator;
	
	Integer batchID;
	String batchName;
	String batchdescription;
	String batchstatus;
	Integer batchNoOfClasses;


	static String expBatchname;
	static String expBatchdesc;
	static String expBatchstatus;
	static Integer expBatchNoOfClasses;
	static Integer expProgramId;

	Scenario scenario;
	
	@Given("A services with {string} is available")
	public void a_services_with_is_available(String string) {
		RestAssured.baseURI = BASE_URL;
	}

	@Given("User sets the header")
	public void user_sets_the_header() {
		request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
	}
	
	
	@When("User adding body with batch name, batch description, batch no of classes {string}")
	public void user_adding_body_with_batch_name_batch_description_batch_no_of_classes(String batchst) {
		String batchname = commnutils.getBatchName();
		String programname = commnutils.getProgramName();
		
		Loggerload.debug("construct the Batch name - as per the pattern");		
		String batchNameString = commnutils.getMonth()+commnutils.getYear()+"-NinjaTrainees-"+programname+"-"+batchname+commnutils.getBatchNameSeries()+"-"+commnutils.getSerialNumber();
		String batchdesc = "Learn "+programname;
		Integer batchNumberOfClasses = commnutils.getNoOfClasses();
		System.out.println("batchNameString : " + batchNameString);
		System.out.println("batchNumberOfClasses : " + batchNumberOfClasses);

		expBatchname = batchNameString;
		expBatchdesc = batchdesc;
		expBatchstatus = batchst;
		expBatchNoOfClasses = batchNumberOfClasses;
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);
		
		requestbody.put("batchName", batchNameString);
		requestbody.put("batchDescription", batchdesc);
		requestbody.put("batchStatus", batchst);
		requestbody.put("batchNoOfClasses", batchNumberOfClasses);
		requestbody.put("programId",2626 );//CommonUtils.getKeyValue("prgrmID").toString()
		
		Loggerload.debug(requestbody.toJSONString());
		System.out.println("requestbody : "+requestbody);
		request.body(requestbody.toJSONString());
	}

	@When("User makes a {string} request with endpoint {string}")
	public void user_makes_a_request_with_endpoint(String req, String endpoint) {
		System.out.println("endpoint : "+endpoint +"\n " +request);
		
		switch (req) {
		case "POST":
			response = request.post(endpoint);
			Loggerload.info("Response : " +response.getStatusCode()+"\n"+response.getStatusLine());
			break;

		case "GET":
			String updatedEndpoint = endpoint.replace(":BatchId", batchID.toString());
			Loggerload.info(updatedEndpoint);
			response = request.get(updatedEndpoint);
			Loggerload.info("Response : " +response.getStatusCode()+"\n"+response.getStatusLine());
			break;
		default:
			System.out.println("Unexpected request");
		}
	}

	@Then("User saves response")
	public void user_saves_response() {
		ResponseBody body = response.getBody();	
		jsonPathEvaluator = response.jsonPath();
		
		batchID = jsonPathEvaluator.get("batchId");
		Loggerload.info(batchID);
		commnutils.setBatchID(batchID);

		batchName = jsonPathEvaluator.get("batchName");
		commnutils.setBatchName(batchName);
		Loggerload.info(batchName);

		batchdescription = jsonPathEvaluator.get("batchDescription");
		Loggerload.info(batchdescription);
		commnutils.setBatchDescription(batchdescription);

		batchstatus = jsonPathEvaluator.get("batchStatus");
		commnutils.setBatchStatus(batchstatus);
		Loggerload.info(batchstatus);
		
		batchNoOfClasses = jsonPathEvaluator.get("batchNoOfClasses");
		commnutils.setBatchNoOfClasses(batchNoOfClasses);
		Loggerload.info(batchNoOfClasses);
		
	}

	@Then("User get batch status code as {int}")
	public void user_get_batch_status_code_as(Integer int1) {
	Assert.assertEquals(response.getStatusCode(), int1);
	}

	@Then("Validate required fields")
	public void validate_required_fields() {
		Assert.assertEquals(batchName, expBatchname);
		Assert.assertEquals(batchdescription, expBatchdesc);
		Assert.assertEquals(batchstatus, expBatchstatus);
		Assert.assertEquals(batchNoOfClasses, expBatchNoOfClasses);
		//Assert.assertEquals(programId, expProgramId);

	}
	
	@Then("Validate required fields for get")
	public void validate_required_fields_for_get() {
		Assert.assertEquals(batchName, expBatchname);
		Assert.assertEquals(batchdescription, expBatchdesc);
		Assert.assertEquals(batchstatus, expBatchstatus);
		Assert.assertEquals(batchNoOfClasses, expBatchNoOfClasses);
	}



}
