package com.APIRestAssured.artiifact;

import io.restassured.response.Response;
import utils.TestRequestBuilderClass;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Ch13MultipleWaysRestAssured {

    // Create an instance of the RequestBuilder using the Builder pattern
    TestRequestBuilderClass requestBuilder = new TestRequestBuilderClass.Builder()
            .setBaseURI("https://jsonplaceholder.typicode.com/")  // Set base URI
            .setQueryParam("userid", 1)  // Set query parameter 'userid=1'
            .build();  // Build the TestRequestBuilderClass

    @Test(description = "Alternative 1: Using explicit extraction")
    public void testExplicitExtraction() {
        // Send the request using the configured request specification and extract the response explicitly
        Response response = requestBuilder.getRequestSpec().get("/");
        System.out.println("Response 1:\n" + response.asString());
        // Add assertions or further validations as needed
    }

    @Test(description = "Alternative 2: Using given-when-then in a single statement")
    public void testSingleStatement() {
        // Send the request and extract the response using given-when-then in a single statement
        Response response = given().spec(requestBuilder.getRequestSpec()).get("/").then().extract().response();
        System.out.println("Response 2:\n" + response.asString());
        // Add assertions or further validations as needed
    }

    @Test(description = "Alternative 3: Separate extraction step")
    public void testSeparateExtraction() {
        // Send the request using the configured request specification
        Response responseBeforeExtraction = requestBuilder.getRequestSpec().get("/");
        // Extract the response separately
        Response response = responseBeforeExtraction.then().extract().response();
        System.out.println("Response 3:\n" + response.asString());
        // Add assertions or further validations as needed
    }

    @Test(description = "Alternative 4: Using shorthand methods")
    public void testShorthandMethods() {
        // Send the request and extract the response using shorthand methods
        Response response = requestBuilder.getRequestSpec().get("/").andReturn();
        System.out.println("Response 4:\n" + response.asString());
        // Add assertions or further validations as needed
    }

    @SuppressWarnings("null")
	@Test(description = "Alternative 5: Using reusable given-when-then configuration")
    public void testReusableConfiguration() {
        // Define a reusable given-when-then configuration
        given().spec(requestBuilder.getRequestSpec())
                // Send the request and extract the response using the reusable configuration
                .when().get("/").then().extract().response();
        Response response1 = null;
        System.out.println("Response 5:\n" + response1.asString());
        // Add assertions or further validations as needed
    }
}
