package apiUtils;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {
	
	/**
	 * KeyWord --> PassBasicAuth
	 */
	public RequestSpecification passBasicAuth(String userName, String password) {
		RequestSpecification requestSpecification=RestAssured.given();
		requestSpecification.auth().basic( userName, password);
		return requestSpecification;
	}
	/**
	 * KeyWord --> BearerToken
	 */
	public RequestSpecification passBearerToken(String token) {
		RequestSpecification requestSpecification=RestAssured.given();
		requestSpecification.auth().oauth2(token);
		return requestSpecification;
	}
	
	
	public RequestSpecification passBodyHeader(String jsonData,String contentType,String key,String value) {
		RequestSpecification requestSpecification=RestAssured.given();
		requestSpecification.contentType(contentType).body(jsonData)
		.header(key,value);
		
		return requestSpecification;
	}
	
	
	
	
	
	
	
	@Test
	
	void a() {
		  String wordart =
		            "    **         *******     ***" + "\n" +
		            "   *  *        *     *      *" + "\n" +
		            "  ******       *******      *" + "\n" +
		            " *      *      *            *" + "\n" +
		            "*        *     *           ***" + "\n" +
		            "\n" +
		            "*******  ******  ******  *******     **    ***" + "\n" +
		            "   *     *       *          *      *  *     *" + "\n" +
		            "   *     *****   ******     *    ********   *" + "\n" +
		            "   *     *            *     *         *     *" + "\n" +
		            "   *     ******  ******     *         *   ***";

		System.out.println(wordart);
	}
	
}
