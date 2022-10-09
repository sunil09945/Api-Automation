package ReqRes.withOutBDD;

import org.testng.annotations.Test;

import constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseLogSpec;

public class GetRequest {
	
	@Test	
	private void getResource() {
		Response response = RestAssured.get(Constants.baseURL+"api/unknown");
//		response.getBody().prettyPrint();
//		System.out.println();
		ValidatableResponse validatableResponse = response.then();
		ValidatableResponseLogSpec<ValidatableResponse, Response> responseLogSpec = validatableResponse.log();
		responseLogSpec.status();
		responseLogSpec.body();
		
		/*========== Veerification ============*/
		
		validatableResponse.assertThat().statusCode(200);

	}

}
