package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Log4jandCommonMethodforPost {

    // Initialize Log4j logger
    private static final Logger logger = LogManager.getLogger(Log4jandCommonMethodforPost.class);
    
  //  Logger logger= LogManager.getLogger();
    

    @BeforeClass
    public void setUp() {
        // Set up common configuration for all tests
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testNegativeScenario() {
        Response response = sendGetRequest("/test/invalidEndpoint");

        // Log the API response
        logger.info("API Response:\n" + response.asString());

        // Assertions
        assertEquals(response.getStatusCode(), 404, "Unexpected status code"); // Expecting a 404 Not Found
        // You can add more assertions based on the response structure and your requirements
    }

    @Test
    public void testInvalidEndpoint() {
        Response response = sendGetRequest("/test/invalid-endpoint");

        // Log the response status code
        logger.info("Status code: " + response.getStatusCode());
        // Add assertions as per your requirements
        // For example, to assert that the status code is in the 4xx series
        org.testng.Assert.assertTrue(response.getStatusCode() >= 400 && response.getStatusCode() < 500);
    }

    @Test
    public void testUnauthorizedAccess() {
        Response response = sendGetRequest("/users/1");

        // Log the response status code
        logger.info("Status code: " + response.getStatusCode());
        // Add assertions as per your requirements
        // For example, to assert that the status code is 401(Unauthorized)
        org.testng.Assert.assertEquals(response.getStatusCode(), 401); // This fails as we receive 200 response
    }

    @Test
    public void testResourceNotFound() {
        Response response = sendGetRequest("/users/1000");

        // Log the response status code
        logger.info("Status code: " + response.getStatusCode());
        // Add assertions as per your requirements
        // For example, to assert that the status code is 404
        org.testng.Assert.assertEquals(response.getStatusCode(), 404);
    }

    // Common method to send a GET request and extract the response
    private Response sendGetRequest(String endpoint) {
        return given().when().get(endpoint).then().extract().response();
    }
}
