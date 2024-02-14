package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.TestRequestBuilderClass;

public class TestRequestBuilderClass {
    private RequestSpecification requestSpec;

    // Private constructor to prevent direct instantiation
    public TestRequestBuilderClass() {
        // Initialize request specification
        requestSpec = RestAssured.given();
    }

    /**
     * Method to get the configured request specification.
     * @return The configured request specification.
     */
    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    // Builder inner class to build the TestRequestBuilderClass instance
    public static class Builder {
        private TestRequestBuilderClass requestBuilder;

        // Constructor to initialize the TestRequestBuilderClass
        public Builder() {
            // Creating an instance of TestRequestBuilderClass
            requestBuilder = new TestRequestBuilderClass();
        }

        /**
         * Setter method to set the base URI in the request specification.
         * @param baseURI The base URI of the API.
         * @return The builder instance for method chaining.
         */
        public Builder setBaseURI(String baseURI) {
            // Set the base URI in the request specification
            requestBuilder.requestSpec.baseUri(baseURI);
            return this;
        }

        /**
         * Setter method to set a query parameter in the request specification.
         * @param paramName The name of the query parameter.
         * @param value The value of the query parameter.
         * @return The builder instance for method chaining.
         */
        public Builder setQueryParam(String paramName, Object value) {
            // Set a query parameter in the request specification
            requestBuilder.requestSpec.queryParam(paramName, value);
            return this;
        }

        /**
         * Method to build the TestRequestBuilderClass instance.
         * @return The built TestRequestBuilderClass instance.
         */
        public TestRequestBuilderClass build() {
            // Return the built TestRequestBuilderClass instance
            return requestBuilder;
        }
    }
}