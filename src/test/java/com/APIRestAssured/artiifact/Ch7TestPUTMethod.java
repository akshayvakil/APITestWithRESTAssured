package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Ch7TestPUTMethod {

    @BeforeClass
    public void setUp() {
        // Set up common configuration for all tests
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Update user details using PUT request - Positive Scenario")
    public void testUpdateUserPositive() {
        // Request body
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        // Send PUT request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .put("/users/2")
            .then()
                .statusCode(200) // Expecting a 200 OK status code
                .extract()
                .response();

        // Print the API response
        System.out.println("Positive Scenario - API Response:\n" + response.asString());

        // Assertions for the response body
        assertEquals(response.jsonPath().getString("name"), "morpheus");
        assertEquals(response.jsonPath().getString("job"), "zion resident");
        // Additional assertion for the existence of the "updatedAt" field
        //assertEquals(response.jsonPath().getString("updatedAt"), "2024-01-30T18:45:18.192Z");
        // You can add more assertions based on the response structure and your requirements
    }

    @Test(description = "Update user details using PUT request - Negative Scenario (Bad Request)")
    public void testUpdateUserNegativeBadRequest() {
        // Request body with invalid data
        String requestBody = "{\n" +
                "    \"name\" \"\",\n" +
                "    \"job\": \"\"\n" +
                "}";

        // Send PUT request with invalid data
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .put("/users/2")
            .then()
                .statusCode(400) // Expecting a 400 Bad Request status code for the negative scenario
                .extract()
                .response();

        // Print the API response
        System.out.println("Negative Scenario (Bad Request) - API Response:\n" + response.asString());

        // Assertions for the response
        // You can add assertions based on the response structure and your requirements for the negative scenario
    }

    @Test(description = "Update user details using PUT request - Negative Scenario (Not Found)")
    public void testUpdateUserNegativeNotFound() {
        // Request body
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        // Send PUT request for a non-existent user (resulting in 404 Not Found)
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .put("/users/99bv") // Assuming 999 is a non-existent user ID
            .then()
                .statusCode(404) // Expecting a 404 Not Found status code for the negative scenario
                .extract()
                .response();

        // Print the API response
        System.out.println("Negative Scenario (Not Found) - API Response:\n" + response.asString());

        // Assertions for the response
        // You can add assertions based on the response structure and your requirements for the negative scenario
    }
}
