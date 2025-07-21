package api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.json.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITestMethod {
	String baseUri = "https://reqres.in";

	@Test
	public void testGetRequest() {
		RestAssured.baseURI = baseUri;
		given().when().get("/api/users/2").then().statusCode(200).body("data.first_name", equalTo("Janet"));

	}
	@Test
    public void testPostRequest() {
        JSONObject requestBody = new JSONObject();
        
        
        requestBody.put("x-api-key", "reqres-free-v1");
        requestBody.put("name", "John");
        requestBody.put("job", "developer");
        given()
        .header("x-api-key", "reqres-free-v1")
            .baseUri(baseUri)
            .header("Content-Type", "application/json")
            .body(requestBody.toString())
        .when()
            .post("/api/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("John"));
    }

}
