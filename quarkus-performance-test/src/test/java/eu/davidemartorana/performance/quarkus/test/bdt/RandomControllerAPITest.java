package eu.davidemartorana.performance.quarkus.test.bdt;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RandomControllerAPITest {

    @Test
    public void getRandomTimeTest() {
        given()
                .when()
                    .get("/random/time")
                .then()
                    .contentType(MediaType.APPLICATION_JSON)
                    .statusCode(200);
    }

}
