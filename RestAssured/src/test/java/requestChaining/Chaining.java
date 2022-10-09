package requestChaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Chaining {
	
	String token,username,password;
	
	@Test(priority=1)
	private void test01() {
		String body="{\r\n"
				+ "	\"external_id\": \"postman\"\r\n"
				+ "}";
		RequestSpecification requestSpecification = RestAssured.given().body(body);
		requestSpecification.header("X-FakeAPI-Action","register");
		
		Response response = requestSpecification.post("https://node-fake-api-server.herokuapp.com");
		
		response.then().log().all();
		username = response.jsonPath().get("username");
		password = response.jsonPath().get("password");
		token = response.jsonPath().get("auth_token");
		
		System.out.println("Credentials are  \n"+username+"\n"+password+"\n"+token);
		
	}
	
	@Test(priority=2)
	private void test02() {
		
		RequestSpecification requestSpecification = RestAssured.given().auth().basic(username, password);
		
		Response response = requestSpecification.get("https://node-fake-api-server.herokuapp.com/hello-world");
		
		response.then().log().all();
		
	}
	
	
	
}
