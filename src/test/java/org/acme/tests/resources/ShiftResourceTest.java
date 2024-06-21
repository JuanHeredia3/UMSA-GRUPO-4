package org.acme.tests.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ShiftResourceTest {

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testGetAllShifts() {
        //Scenario: El sistema devuelve todos los turnos

        given()
                .when().get("/Shift/GetAll")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
}
