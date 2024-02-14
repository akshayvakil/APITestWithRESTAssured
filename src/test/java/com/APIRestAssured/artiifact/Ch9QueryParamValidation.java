package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Ch9QueryParamValidation {

	@BeforeClass
	public void setUp() {
		// Set up common configuration for all tests
		RestAssured.baseURI = "https://reqres.in/api";
	}

	@DataProvider(name = "pageNumbers")
	public Object[][] pageNumbers() {
		return new Object[][] { { 1 }, // Minimum valid page number
				{ 5 }, // Valid page number within the range
				{ 10 }, // Maximum valid page number
				{ 0 }, // Invalid page number (below minimum)
				{ 11 }, // Invalid page number (above maximum) //This is test data so it gives 400 but
						// does not fail
				// Add more data sets as needed
		};
	}

	@Test(dataProvider = "pageNumbers", description = "Validate different datasets using query param")
	public void testGetUserList(int pageNumber) {
		// Send GET request with query parameter
		Response response = given().contentType(ContentType.JSON).when().queryParam("page", pageNumber).get("/users")
				.then().extract().response();

		// Print the API response
		System.out.println("API Response for page " + pageNumber + ":\n" + response.asString());

		// Assertions for the response
		if (pageNumber >= 1 && pageNumber <= 10) {
			// Expecting a 200 OK status code for valid page numbers within the range [1,
			// 10]
			assertEquals(response.getStatusCode(), 200);
		} else {
			// Expecting a 400 Bad Request status code for invalid page numbers
			assertEquals(response.getStatusCode(), 400);
		}

		// You can add more assertions based on the response structure and your
		// requirements
	}
}
