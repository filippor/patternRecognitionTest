package it.filippor.test.pattern.recognition.rest;


import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
@QuarkusTest
public class HelloControllerIT {

	@Test
	void testSayHello() {
		RestAssured.given()
        .when().get("/data/hello")
        .then()
           .statusCode(200)
           .body(is("Hello World []"));
	}

}
