package it.filippor.test.pattern.recognition.rest;


import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
@QuarkusTest
public class HelloControllerIT {
	@Disabled
	@Test
	void testSayHello() {
		RestAssured.given()
        .when().body("{\"x\":12 , \"y\":23}").post()
        .then()
           .statusCode(200);
         //  .body(is("Hello World []"));
	}

}
