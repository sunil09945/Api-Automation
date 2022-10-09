package ReqRes.withOutBDD;

import org.testng.annotations.Test;

import constants.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseLogSpec;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import pojo.PojoLibrary;

public class CreateReqWithPOJO {
	
	@Test
	private void createUserUsingStringBody() {
		
		PojoLibrary pObj=new PojoLibrary("Harry", "Minister");
		
		RequestSpecification  requestSpecification= RestAssured.given();
		RequestLogSpecification requestLogSpecification = requestSpecification.log();
		requestSpecification.body(pObj).contentType(ContentType.JSON);
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
		validatableResponse.assertThat().statusCode(201);
	}

}
