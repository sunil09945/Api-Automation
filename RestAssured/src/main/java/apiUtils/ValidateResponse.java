package apiUtils;

import java.util.concurrent.TimeUnit;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.response.ValidatableResponse.*;

public class ValidateResponse {
	
	public void validate(Response response) {
		ValidatableResponse validatableResponse = response.then();
		validatableResponse.assertThat().statusCode(200);
	}
	
	public void validateTime(Response response) {
		ValidatableResponse validatableResponse = response.then();
//		validatableResponse.assertThat().time(lessThan(2),TimeUnit.SECONDS);
		
	}
	
	public void validateBodyContainsString(Response response) {
		ValidatableResponse validatableResponse = response.then();
//		validatableResponse.assertThat().body(containsString("winning-numbers"));;
	}
	
}
