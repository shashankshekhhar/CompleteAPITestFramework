package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

public class StepDefinitions extends Utils {

	RequestSpecification req;
	ResponseSpecification res;
	TestDataBuilder tbd = new TestDataBuilder();
	Response response;

	APIResources apiresources;
	static String place_id;

	@Given("Add place Payload")
	public void add_place_Payload() throws IOException {

		req = given().spec(requestSpecififcation()).body(tbd.addPlacePayLoad());

	}

	@When("User calls {string} with {string} Http Request")
	public void user_calls_with_Http_Request(String addplace, String HttpMethod) {
		// Write code here that turns the phrase above into concrete actions
		apiresources = APIResources.valueOf(addplace);
		if (HttpMethod.equalsIgnoreCase("POST")) {
			response = req.when().post(apiresources.getResource()).then().spec(responseSpecification()).extract()
					.response();
			place_id = extractFieldfromResponse(response, "place_id");
		} else if (HttpMethod.equalsIgnoreCase("GET")) {
			response = req.when().get(apiresources.getResource()).then().extract().response();

		} else if (HttpMethod.equalsIgnoreCase("DELETE")) {
			System.err.println(apiresources.getResource());
			response = req.when().post(apiresources.getResource()).then().extract()
					.response();
			System.err.println(response.asString());

		}

	}

	/*
	 * @When("User calls {string} with POST Http Request") public void
	 * user_calls_with_POST_Http_Request(String addplace) { // Write code here that
	 * turns the phrase above into concrete actions //res = responseSpecification();
	 * apiresources=APIResources.valueOf(addplace);
	 * 
	 * response=req.when().post(apiresources.getResource()).then().spec(
	 * responseSpecification()).extract().response(); }
	 */
	@Then("The Api call gets success with status code {int}")
	public void the_Api_call_gets_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} is response body is {string}")
	public void is_response_body_is(String keyvalue, String ExpectedValue) {
		// Write code here that turns the phrase above into concrete actions
		/*
		 * String stringresponse = response.asString(); JsonPath js = new
		 * JsonPath(stringresponse); place_id = js.getString("place_id");
		 */

		System.out.println(place_id.toString());
		System.out.println(response.asString());
		assertEquals(extractFieldfromResponse(response, keyvalue), ExpectedValue);

	}

	@Then("verify {string} created maps using {string}")
	public void verify_created_maps_using(String field, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		apiresources = APIResources.valueOf(resource);
		req = given().spec(requestSpecififcation()).queryParam("place_id", place_id);
		user_calls_with_Http_Request(resource, "GET");

		assertEquals(extractFieldfromResponse(response, "name"), "Frontline house");
	}

	@Given("Delete Payload")
	public void delete_Payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		req = given().spec(requestSpecififcation()).body(tbd.deletePlacePayload(place_id));
	
	}
}
