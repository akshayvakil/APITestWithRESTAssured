package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.POJOUtilityCreateReuest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.util.List;

public class Ch11POSTWithPojoClass {

    @BeforeClass
    public void setUp() {
        // Set up common configuration for all tests
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Create user using POST request with POJO validation")
    public void testCreateUser() {
        // Create a CreateUserRequest instance
        POJOUtilityCreateReuest createUserRequest = new POJOUtilityCreateReuest();
        createUserRequest.setName("sidharth");
        createUserRequest.setLanguages(List.of("Java", "Python"));

        POJOUtilityCreateReuest.City city1 = new POJOUtilityCreateReuest.City();
        city1.setName("bangalore");
        city1.setTemperature("30");

        POJOUtilityCreateReuest.City city2 = new POJOUtilityCreateReuest.City();
        city2.setName("delhi");
        city2.setTemperature("40");

        createUserRequest.setCity(List.of(city1, city2));

        // Send POST request with POJO serialization
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .header("Authentication", "bearer gtywsf176534fd61ut4f") // Additional Authentication header
                .body(createUserRequest)
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
