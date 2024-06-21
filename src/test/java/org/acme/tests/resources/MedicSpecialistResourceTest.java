package org.acme.tests.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MedicSpecialistResourceTest {

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testGetAllMedicSpecialists() {
        //Scenario: El sistema devuelve todos los especialistas medicos

        given()
                .when().get("MedicSpecialist/GetAll")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
}
