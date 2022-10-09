package Authentication;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostWithBearerToken {
	

	@Test
	private void GetBearerToken() {
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.auth().oauth2("MTcyNTZjOWItNWJlOC00Zjg0LWJjNWQtN2RhMDcwYzIwMGMwOjEzMzBlZmE3LWVkNDctNDE5Ni1iYmE3LTZlZjQ5NjA4NTNhNw==");
		
		Response response = requestSpecification.post("https://node-fake-api-server.herokuapp.com/hello-world");
		
		response.then().log().body();
	}
	

}
