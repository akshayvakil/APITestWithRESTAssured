package com.APIRestAssured.artiifact;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Ch1APITest {

    @Test
    public void testAPI() {
        // Example: Send a GET request and print the response
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println("Response Body: " + response.getBody().asString());
        
        
    }
}
