package stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

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

	static Integer batchID;
	String batchName;
	String batchdescription;
	String batchstatus;
	Integer batchNoOfClasses;

	static String expBatchname;
	static String expBatchdesc;
	static String expBatchstatus;
	static Integer expBatchNoOfClasses;
	static Integer expProgramId;
	
	
	@Given("A service with base {string} is available")
	public void a_service_with_base_is_available(String string) {
		RestAssured.baseURI = BASE_URL;
	}


	@Given("User sets the header")
	public void user_sets_the_header() {
		request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
	}

	@When("User adding body with batch no of classes, {string}, {string}, {string}")
	public void user_adding_body_with_batch_no_of_classes(String batchst, String batchname, String batchdesc) {
		String programname = commnutils.getProgramName();

		Loggerload.debug("construct the Batch name - as per the pattern");
		String batchNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + programname + "-"
				+ batchname + commnutils.getBatchNameSeries() + "-" + commnutils.getSerialNumber();

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
		requestbody.put("programId", CommonUtils.getkeyvalueFromMap("prgrmID").toString());// CommonUtils.getKeyValue("prgrmID").toString()

		Loggerload.debug(requestbody.toJSONString());
		request.body(requestbody.toJSONString());
	}

	
	@SuppressWarnings("unchecked")
	@When("User adding body with batch name, batch description, batch no of classes {string}")
	public void user_adding_body_with_batch_name_batch_description_batch_no_of_classes(String batchst) {
		String batchname = commnutils.getBatchName();
		String programname = commnutils.getProgramName();

		Loggerload.debug("construct the Batch name - as per the pattern");
		String batchNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + programname + "-"
				+ batchname + commnutils.getBatchNameSeries() + "-" + commnutils.getSerialNumber();
		String batchdesc = "Learn " + programname;
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
		requestbody.put("programId", CommonUtils.getkeyvalueFromMap("prgrmID").toString());// CommonUtils.getKeyValue("prgrmID").toString()

		Loggerload.debug(requestbody.toJSONString());
		request.body(requestbody.toJSONString());
	}

	@When("User makes a {string} request with endpoint {string}")
	public void user_makes_a_request_with_endpoint(String req, String endpoint) {
		System.out.println("endpoint : " + endpoint + "\n " + request);

		switch (req) {
		case "POST":
			Loggerload.info("User do GET request with endpoint: "+endpoint );
			response = request.post(endpoint);
			
			break;

		case "GET":
			Loggerload.info("User do GET request with endpoint: "+endpoint.replace(":BatchId", batchID.toString()));
			response = request.get(endpoint.replace(":BatchId", batchID.toString()));
			
			break;

		case "PUT":
			String bname = CommonUtils.getkeyvalueFromMap("batchName").toString();
			Loggerload.info("User do PUT request with endpoint: " + endpoint.replace(":(BatchName)", bname));
			Loggerload.info("Batch Name to be updated:" + bname);
			response = request.put(endpoint.replace(":(BatchName)", bname));
			break;
	
		case "PUT_ByID":
			String bid3 = CommonUtils.getkeyvalueFromMap("batchID").toString();
			Loggerload.info("User do PUT request with endpoint: " + endpoint.replace(":(BatchId)", bid3));
			Loggerload.info("Batch id to be updated:" + bid3);
			response = request.put(endpoint.replace(":(BatchId)", bid3));
			break;
			
		case "DELETE":
			String bid = CommonUtils.getkeyvalueFromMap("batchID").toString();
			Loggerload.info("User do DELETE request with endpoint: " + endpoint.replace(":(BatchId)", bid));
			Loggerload.info("Delete By Batch ID :" + bid);
			response = request.delete(endpoint.replace(":(BatchId)", bid));

			String bid1 = CommonUtils.getkeyvalueFromMap("batchID1").toString();
			Loggerload.info("User do DELETE request with endpoint: " + endpoint.replace(":(BatchId)", bid1));
			Loggerload.info("Delete By Batch ID :" + bid1);
			response = request.delete(endpoint.replace(":(BatchId)", bid1));
			break;

		case "GETALLBATCHES":
			response = request.get(endpoint);
			// Loggerload.info("response for request to get all batches is : "
			// +response.getBody().asPrettyString());
			break;

		case "GETBATCHESBYBATCHID":
			Loggerload.info("User do GET request with endpoint: "+endpoint.replace(":BatchId", batchID.toString()));
			response = request.get(endpoint.replace(":BatchId", batchID.toString()));
			
			break;
			
		case "GETBATCHESBYPROGRAMID":
			String programIdEndpoint = endpoint.replace(":(ProgramId)", CommonUtils.getkeyvalueFromMap("prgrmID").toString());
			Loggerload.info("User do GET request with endpoint: "+ programIdEndpoint);
			response = request.get(programIdEndpoint);
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
		Loggerload.info("Response : Batch ID is :" + batchID);
		CommonUtils.setkeyvalueIntMap("batchID", batchID);

		batchName = jsonPathEvaluator.get("batchName");
		CommonUtils.setkeyvalueStringMap("batchName", batchName);
		Loggerload.info("Response : Batch Name is :" + batchName);

		batchdescription = jsonPathEvaluator.get("batchDescription");
		Loggerload.info("Response : Batch ID is :" + batchdescription);
		CommonUtils.setkeyvalueStringMap("batchDescription", batchdescription);

		batchstatus = jsonPathEvaluator.get("batchStatus");
		Loggerload.info("Response : Batch Status is :" + batchstatus);
		CommonUtils.setkeyvalueStringMap("batchStatus", batchstatus);

		batchNoOfClasses = jsonPathEvaluator.get("batchNoOfClasses");
		CommonUtils.setkeyvalueIntMap("BatchNoOfClasses", batchNoOfClasses);
		Loggerload.info("Response : batch No Of Classes are :" + batchNoOfClasses);

	}

	@Then("User get batch status code as {int}")
	public void user_get_batch_status_code_as(Integer statuscode) {
		Assert.assertEquals(response.getStatusCode(), statuscode);
	}

	@Then("Validate required fields")
	public void validate_required_fields() {
		Assert.assertEquals(batchName, expBatchname);
		Assert.assertEquals(batchdescription, expBatchdesc);
		Assert.assertEquals(batchstatus, expBatchstatus);
		Assert.assertEquals(batchNoOfClasses, expBatchNoOfClasses);

	}

	@Then("Validate required fields for get")
	public void validate_required_fields_for_get() {
		Assert.assertEquals(batchName, expBatchname);
		Assert.assertEquals(batchdescription, expBatchdesc);
		Assert.assertEquals(batchstatus, expBatchstatus);
		Assert.assertEquals(batchNoOfClasses, expBatchNoOfClasses);
	}

	// *********************Batch Get All***********************/

	@Then("do validations for batch")
	public void do_validations_for_batch() {

		Assert.assertNotNull(response.getBody());
		Loggerload.info("response ContentType is : " + response.getContentType());
		Assert.assertEquals(response.getContentType(), "application/json");
		Loggerload.info("response statuscode : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		Loggerload.info("response statusline is : " + response.getStatusLine());
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 ");
	}

	@Then("Validate necessary fields in response")
	public void validate_necessary_fields_in_response() {
		Assert.assertEquals(batchName, expBatchname);
		Assert.assertEquals(batchdescription, expBatchdesc);
		Assert.assertEquals(batchstatus, expBatchstatus);
		Assert.assertEquals(batchNoOfClasses, expBatchNoOfClasses);
	}

	// *********************Batch PUT By BAtchName***********************/

	@SuppressWarnings("unchecked")
	@When("User add body with new batch name and batch description")
	public void user_add_body_with_new_batch_name_and_batch_description() {

		String batchname = commnutils.getBatchName();
		String programname = commnutils.getProgramName();

		Loggerload.debug("construct the Batch name - as per the pattern");
		String batchNameString = commnutils.getMonth() + commnutils.getYear() + "-NinjaTrainees-" + programname + "-"
				+ batchname + commnutils.getBatchNameSeries() + "-" + commnutils.getSerialNumber();
		String batchdesc = "Learn " + programname;
		Integer batchNumberOfClasses = commnutils.getNoOfClasses();
		System.out.println("batchNameString : " + batchNameString);
		System.out.println("batchNumberOfClasses : " + batchNumberOfClasses);

		expBatchname = batchNameString;
		expBatchdesc = batchdesc;
		expBatchstatus = CommonUtils.getkeyvalueFromMap("batchStatus").toString();
		expBatchNoOfClasses = batchNumberOfClasses;
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject requestbody = new JSONObject(map);

		requestbody.put("batchName", batchNameString);
		requestbody.put("batchDescription", batchdesc);
		requestbody.put("batchStatus", CommonUtils.getkeyvalueFromMap("batchStatus").toString());
		requestbody.put("batchNoOfClasses", batchNumberOfClasses);
		requestbody.put("programId", CommonUtils.getkeyvalueFromMap("prgrmID").toString());

		request.body(requestbody.toJSONString());

		Loggerload.info("New Batch Name :" + batchNameString);
		Loggerload.info("New BAtch Description : " + batchdesc);
	}

	@Then("User gets status code as {int}")
	public void user_gets_status_code_as(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), int1);

	}

	// *********************Batch Invalid cases***********************/

	@When("User makes a {string} request with endpoint {string} with invalid param {string}")
	public void user_makes_a_request_with_endpoint_with_invalid_param(String string, String endpoint, String bname) {
		Loggerload.info("Update By Program Name :" + bname);
		response = request.put(endpoint.replace(":(ProgramName)", bname));
	}

}
