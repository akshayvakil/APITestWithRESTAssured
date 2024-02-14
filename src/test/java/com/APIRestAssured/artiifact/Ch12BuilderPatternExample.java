package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestRequestBuilderClass;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Ch12BuilderPatternExample {
	
	/**
	 * Normal Approach withOut BuilderPattern
	 * For DEsign Pattern check another method	 * 
	 */

	@Test(description = "Get user by ID")
	public void testGetUserById() {
		// Set base URI
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

		// Send GET request with query parameter 'userid=1'
		Response response = given().queryParam("userid", 1).when().get("/").then().extract().response();

		// Validate response status code
		assertEquals(response.getStatusCode(), 200, "Unexpected status code");

		// Print API response
		System.out.println("API Response:\n" + response.asString());

		// You can add more assertions based on the response body and your requirements
	}
	
	/**
	 * Below Test is using Builder pattern Approach
	 */
	
	@Test(description = "Get user by ID using Builder Pattern")
    public void testGetUserByIdWithBuilderPattern() {
        // Creating an instance that is object  of the TestRequestBuilderClass using the Builder pattern
		/**
		 * requestBuilder object:::--> will set variables of api like baseURI , QueryParam etc.
		 * response Object :::--> will save response
		 * 
		 */
        TestRequestBuilderClass requestBuilder = new TestRequestBuilderClass.Builder()
                .setBaseURI("https://jsonplaceholder.typicode.com/")  // Set base URI
                .setQueryParam("userid", 1)  // Set query parameter 'userid=1'
                .build();  // Build the TestRequestBuilderClass

        // Send the request using the configured request specification
        Response response = requestBuilder.getRequestSpec()
                .when()
                .get("/")
                .then()
                .extract()
                .response();

        // Validate response status code
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        // Print API response
        System.out.println("API Response:\n" + response.asString());

        // You can add more assertions based on the response body and your requirements
    }
	
	
	
	
	
}
