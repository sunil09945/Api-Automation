package ReqRes.withOutBDD;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import constants.Constants;
import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseLogSpec;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

public class CreateRequest {
	
	String requestBody="{\r\n"
			+ "    \"Name\": \"Neo\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}";
	
	@Test
	private void createUserUsingStringBody() {
		
		RequestSpecification  requestSpecification= RestAssured.given();
		RequestLogSpecification requestLogSpecification = requestSpecification.log();
		requestSpecification.body(requestBody).contentType(ContentType.JSON);
		System.out.println("=============\nRequest Body is\n==============");	
		requestLogSpecification.body();
		System.out.println("=============\nRequest Header is\n==============");
		requestLogSpecification.headers();
		System.out.println("===============================");
		requestLogSpecification.cookies();
		
		Response response = requestSpecification.post(Constants.baseURL+"api/users");
		ValidatableResponse validatableResponse = response.then();
		ValidatableResponseLogSpec<ValidatableResponse, Response> log = validatableResponse.log();
		System.out.println("=============\nResponse Header is\n==============");
		log.headers();
		System.out.println("=============\nResponse Body is\n==============");
		log.body();
		System.out.println("=============\nResponse Status code is\n==============");
		log.status();
		
		System.out.println("=====Assertion========");
		validatableResponse.assertThat().statusCode(200);
	}
	
	@Test
	private void createUserUsingMap() {
		Map requestBody=new HashMap<>();
		requestBody.put("name","Neo");
		requestBody.put("job", "Engineer");
		
		RequestSpecification  requestSpecification= RestAssured.given();
		RequestLogSpecification requestLogSpecification = requestSpecification.log();
		requestSpecification.body(requestBody).contentType(ContentType.JSON);
		
		
		Response response = requestSpecification.post(Constants.baseURL+"api/users");
		ValidatableResponse validatableResponse = response.then();
		ValidatableResponseLogSpec<ValidatableResponse, Response> log = validatableResponse.log();
		System.out.println("===================================");
		log.all();
	}
	@Test
	private void createUserUsingFile() {
		File jsonFile =new File("./jsonFile.json");
		RequestSpecification  requestSpecification= RestAssured.given();
		requestSpecification.body(jsonFile).contentType(ContentType.JSON);
		
		
		Response response = requestSpecification.post(Constants.baseURL+"api/register");
		ValidatableResponse validatableResponse = response.then();
		ValidatableResponseLogSpec<ValidatableResponse, Response> log = validatableResponse.log();
		System.out.println("===================================");
		log.all();
		System.out.println("================Verification Code===================");
		validatableResponse.statusCode(400).contentType(ContentType.JSON);
		System.out.println("testing");
	}

}
