package com.APIRestAssured.artiifact;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUserAPITestFirstPositiveScenario {
	@Test
    public void testGetUsers() {
        // Set the base URI
		 RestAssured.baseURI = "https://reqres.in/api";

	        // Send GET request to the /users endpoint with query parameter page=2
	        Response response = given()
	                .param("page", 2)
	            .when()
	                .get("/users")
	            .then()
	                .extract()
	                .response();

	        // Print the API response
	        System.out.println("API Response:\n" + response.asString());

	        // Assertions
	        assertEquals(response.getStatusCode(), 200, "Unexpected status code");
	        // You can add more assertions based on the response structure and your requirements
    
}

	
}
