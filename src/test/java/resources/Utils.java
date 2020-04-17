package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	RequestSpecification req;
	ResponseSpecification res;

	public RequestSpecification requestSpecififcation() throws IOException {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		req = new RequestSpecBuilder().setBaseUri(getGlobalProperties())
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		return req;

	}

	public ResponseSpecification responseSpecification() {

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res;
	}


public static String getGlobalProperties() throws IOException {
	
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("D:\\ApiTestingFramework\\APIFramework\\src\\test\\java\\resources\\global.properties");
	prop.load(fis);
	return prop.getProperty("baseURI");
	
	
}

public static String extractFieldfromResponse(Response response,String field ) {
	JsonPath js = new JsonPath(response.asString());
	String valueoffield=js.get(field);
	System.out.println(valueoffield);
	return valueoffield;
	
}

}
