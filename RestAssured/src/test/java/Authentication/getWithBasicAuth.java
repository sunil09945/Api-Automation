package Authentication;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getWithBasicAuth {

	@Test
	private void GetBasicAuth() {
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.auth().basic("17256c9b-5be8-4f84-bc5d-7da070c200c0", "1330efa7-ed47-4196-bba7-6ef4960853a7");
		
		Response response = requestSpecification.get("https://node-fake-api-server.herokuapp.com/hello-world");
		
		response.then().log().all();
	}

}
