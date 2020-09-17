package it.filippor.test.pattern.recognition.rest;

import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
class SpaceControllerImplementationTest {
	@AfterEach
	void reset() {
		RestAssured.given().when().delete("data/space").then().statusCode(200);
	}

	@Test
	void testAddPoint() {
		RestAssured.given().contentType(ContentType.JSON).body("{\"x\":12 , \"y\":23}")
		.when().post("data/point")
		.then().statusCode(200);

		RestAssured.given().contentType(ContentType.JSON).body("{\"x\":12 , \"y\":23}")
		.when().post("data/point")
		.then().statusCode(409);

	}

	@Test
	void testSpace() throws Exception {
		RestAssured.given()
		.when().get("data/space")
		.then().statusCode(200).body(is("[]"));

		RestAssured.given().contentType(ContentType.JSON).body("{\"x\":12 , \"y\":23}")
		.when().post("data/point")
		.then().statusCode(200);

		RestAssured.given()
		.when().get("data/space")
		.then().statusCode(200).body(is("[{\"x\":12.0,\"y\":23.0}]"));

	}

	@Test
	void testGetLine() throws Exception {
		RestAssured.given()
		.when().get("data/line/2")
		.then().statusCode(200).body(is("[]"));

		RestAssured.given().contentType(ContentType.JSON).body("{\"x\":12 , \"y\":23}")
		.when().post("data/point")
		.then().statusCode(200);
		RestAssured.given().contentType(ContentType.JSON).body("{\"x\":0 , \"y\":0}")
		.when().post("data/point")
		.then().statusCode(200);

		RestAssured.given()
		.when().get("data/line/2")
		.then().statusCode(200).body(is("[[{\"x\":0.0,\"y\":0.0},{\"x\":12.0,\"y\":23.0}]]"));
	}

	@Test
	void testValidation() throws Exception {
		RestAssured.given()
		.when().get("data/line/1")
		.then().statusCode(Status.BAD_REQUEST.getStatusCode());

		RestAssured.given().contentType(ContentType.JSON).body("{}")
		.when().post("data/point")
		.then().statusCode(Status.BAD_REQUEST.getStatusCode());

		RestAssured.given().contentType(ContentType.JSON).body("{\"x\":12}")
		.when().post("data/point")
		.then().statusCode(Status.BAD_REQUEST.getStatusCode());

	}

}
