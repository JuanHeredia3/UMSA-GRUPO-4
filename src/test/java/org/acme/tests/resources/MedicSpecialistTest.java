package org.acme.tests.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.acme.dtos.NewMedicSpecialist;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MedicSpecialistTest {

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

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testCreateMedicSpecialists() {
        //Scenario: El sistema crea un especialista medico
        //BUG?

        NewMedicSpecialist newMedicSpecialistDto = new NewMedicSpecialist();
        newMedicSpecialistDto.name = "Test Create";
        newMedicSpecialistDto.consultationLocation = "Test Create";
        newMedicSpecialistDto.medicalSpeciality = "Test Create";

        given()
                .contentType(ContentType.JSON)
                .body(newMedicSpecialistDto)
                .when().post("MedicSpecialist/Create")
                .then()
                .statusCode(201)
                .body("name", equalTo("Test Create"))
                .body("medicalSpecialty", equalTo("Test Create"))
                .body("consultationLocation", equalTo("Test Create"));
    }
}
