package eu.davidemartorana.performance.quarkus.test.bdt;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RandomControllerAPITest {

    @Test
    public void getRandomDateTest() {
        given()
                .when()
                    .accept(MediaType.APPLICATION_JSON)
                    .get("/random/date")
                .then()
                    .contentType(MediaType.APPLICATION_JSON)
                    .statusCode(200);
    }

    @Test
    public void getRandomDescriptionTest() {
        given()
                .when()
                .accept(MediaType.APPLICATION_JSON)
                .get("/random/description")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(200);
    }

}
