package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Ch6TestPOSTMethodWithRequestSpecificationInterface {

    // Initialize Log4j logger
    private static final Logger logger = LogManager.getLogger(Ch6TestPOSTMethodWithRequestSpecificationInterface.class);

    @BeforeClass
    public void setUp() {
        // Set up common configuration for all tests
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Positive scenario for POST request")
    public void testPositiveScenario() {
        // Request body
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        // Log the request details
        logger.info("POST Request Details:\nURI: {}/users\nRequest Body: {}", RestAssured.baseURI, requestBody);

        // Send POST request
        Response response = given().contentType("application/json")
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/users")
            .then()
                .statusCode(201) // Expecting a 201 Created status code
                .extract()
                .response();

        // Log the API response
        logger.info("Positive Scenario - API Response:\n{}", response.asString());

        // Assertions for the response
        assertTrue(isValidJson(response.asString()), "Response is not a valid JSON");

        // Assertions for the response body
        assertEquals(response.jsonPath().getString("name"), "morpheus");
        assertEquals(response.jsonPath().getString("job"), "leader");
        // not needed as id changes assertEquals(response.jsonPath().getString("id"), "796");
        // You can add more assertions based on the response structure and your requirements
    }

    @Test   
    public void testReqSpecification()
    {
    	
    	System.out.println("============= this is new method ============================");
    	String requestBody1 = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
    	   
        RequestSpecification req= RestAssured.given();
        req.baseUri(RestAssured.baseURI);
        req.contentType(ContentType.JSON);
        req.body(requestBody1);
        
        Response resp=req.post();
        
        resp.prettyPrint();
        
        Assert.assertEquals(resp.statusCode(), 404);
        
        System.out.println("=========================================");
    	
    }
    
    @Test(description = "Negative scenario for POST request with invalid data")
    public void testNegativeScenario() {
        // Request body with invalid data
        String requestBody = "{\n" +
                "    \"name\" \"\",\n" +
                "    \"job\": \"\"\n" +
                "}";

        // Log the request details
        logger.info("POST Request Details:\nURI: {}/users\nRequest Body: {}", RestAssured.baseURI, requestBody);

        // Send POST request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/users")
            .then()
                .statusCode(400) // Expecting a 400 Bad Request status code for the negative scenario
                .extract()
                .response();

        // Log the API response
        logger.info("Negative Scenario - API Response:\n{}", response.asString());

        // Assertions for the response
        //not needed as response is not a json assertTrue(isValidJson(response.asString()), "Response is not a valid JSON");

        // Assertions for the response body
        // You can add assertions based on the response structure and your requirements for the negative scenario
    }

    // Helper method to check if a string is a valid JSON
    private boolean isValidJson(String jsonString) {
        try {
            // Parsing the string to check if it's a valid JSON
            com.google.gson.JsonParser.parseString(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
