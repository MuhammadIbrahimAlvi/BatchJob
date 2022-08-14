package com.example.demo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;

public class ControllerRestAssured {

    @BeforeAll
    public static void init() {
        baseURI = "http://localhost:8032/";
        basePath = "sha256-hash/";
    }

    @Test
    void testResponse200() {
        Response response = (Response)
                given()
                        .header("Content-Type", "application/json")
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("accountHash", 10000001)
                        .post("/{accountHash}").
                        then()
                        .statusCode(200)
                        .log().all().extract();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void testResponse500() {
        Response response = (Response) given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("accountHash", 10000001)
                .post("/{accountHash}")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).assertThat()
                .extract();
        Assertions.assertEquals(500, response.getStatusCode());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void testResponse404() {
        Response response = (Response) given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("accountHash", 10000001)
                .post("/{accountHash}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value()).log().all().extract();
        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void testResponse503() {
        Response response = (Response) given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("accountHash", 10000001)
                .post("/{accountHash}")
                .then()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value()).extract();
        Assertions.assertEquals(503, response.statusCode());
        Assertions.assertNotNull(response.body());
    }


}