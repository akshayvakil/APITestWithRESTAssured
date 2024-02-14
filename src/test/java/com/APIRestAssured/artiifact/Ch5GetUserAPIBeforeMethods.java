package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Ch5GetUserAPIBeforeMethods {

    @BeforeMethod
    public void setUp() {
        // Set up common configuration for all tests
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testNegativeScenario() {
        // Send GET request to an invalid endpoint to intentionally get a 404 response
        Response response = given().when().get("/test/invalidEndpoint").then().extract().response();

        // Print the API response
        System.out.println("API Response:\n" + response.asString());

        // Assertions
        assertEquals(response.getStatusCode(), 404, "Unexpected status code"); // Expecting a 404 Not Found
        // You can add more assertions based on the response structure and your requirements
    }

    @Test
    public void testInvalidEndpoint() {
        // Send GET request to an invalid endpoint
        Response response = RestAssured.given().when().get("/test/invalid-endpoint");
        // Get response status code
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        // Add assertions as per your requirements
        // For example, to assert that the status code is in the 4xx series
        org.testng.Assert.assertTrue(statusCode >= 400 && statusCode < 500);
    }

    @Test
    public void testUnauthorizedAccess() {
        // Send GET request without authentication
        Response response = RestAssured.given().when().get("/users/1");
        // Get response status code
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        // Add assertions as per your requirements
        // For example, to assert that the status code is 401(Unauthorized)
        org.testng.Assert.assertEquals(statusCode, 401); // This fails as we receive 200 response
    }

    @Test
    public void testResourceNotFound() {
        // Send GET request without authentication
        Response response = RestAssured.given().when().get("/users/1000");
        // Get response status code
        int statusCode = response.getStatusCode();
        
        System.out.println("Status code: " + statusCode);
        // Add assertions as per your requirements
        // For example, to assert that the status code is 404
        org.testng.Assert.assertEquals(statusCode, 404);
    }
    
    
}
