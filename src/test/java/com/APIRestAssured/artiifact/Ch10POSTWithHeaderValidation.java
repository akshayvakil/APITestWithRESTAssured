package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Ch10POSTWithHeaderValidation {

    @BeforeClass
    public void setUp() {
        // Set up common configuration for all tests
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Create user using POST request with reuqest header and response header validation")
    public void testCreateUser() {
        // Request body
        String requestBody = "{\n" +
                "    \"name\": \"sidharth\",\n" +
                "    \"languages\": [\"Java\", \"Python\"],\n" +
                "    \"City\": [\n" +
                "        {\"Name\": \"bangalore\", \"Temperature\": \"30\"},\n" +
                "        {\"Name\": \"delhi\", \"Temperature\": \"40\"}\n" +
                "    ]\n" +
                "}";

        // Send POST request with additional Authentication header
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .header("Authentication", "bearer gtywsf176534fd61ut4f") // Additional Authentication header
                .body(requestBody)
            .when()
                .post("/users")
            .then()
                .statusCode(201) // Expecting a 201 Created status code
                .header("Server", "cloudflare") // Assertion for the "Server" header
                .extract()
                .response();
     

        // Print the API response
        System.out.println("API Response:\n" + response.asString());

        // Assertions for the response body
        // You can add more assertions based on the response structure and your requirements
    }
}
