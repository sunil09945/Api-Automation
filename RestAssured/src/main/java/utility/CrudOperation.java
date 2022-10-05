package utility;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CrudOperation {
	
	
	public void postOperation(JSONObject jobj,String endPoint) {
		Response response = given()
				.contentType(ContentType.JSON)
				.body(jobj)
				.post(endPoint);
			response.then()
				.assertThat()
				.contentType(ContentType.JSON)
				.and()
				.statusCode(201)
				.log().all()
				.time(Matchers.lessThan(3000L));
	}

	
	public void getOperation(String url,int statusCode) {

		get(url)
		.then()
			.assertThat()
			.contentType(ContentType.JSON)
			.statusCode(statusCode)
			.log().all();
	}
	
	
	public void del(String url,int statusCode) {
		when()
			.delete(url)
		.then()
			.assertThat()
			.contentType(ContentType.JSON)
			.statusCode(statusCode)
			.log().all();
		
	}
	
	
public void updatePut(String url,JSONObject jobj,int statusCode) {
		
		given()
			.contentType(ContentType.JSON)
			.body(jobj)
			.put(url)
		.then()
			.assertThat()
			.contentType(ContentType.JSON)
			.and()
			.statusCode(statusCode)
			.log().all();
	}
	


}
