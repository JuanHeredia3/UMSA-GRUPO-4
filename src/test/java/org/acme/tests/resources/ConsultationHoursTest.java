package org.acme.tests.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import java.time.DayOfWeek;
import java.time.LocalTime;
import org.acme.dtos.NewConsultationHourDto;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ConsultationHoursTest {

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testGetAllConsultationHours() {
        //Scenario: El sistema devuelve todos los horarios de consulta

        given()
                .when().get("ConsultationHours/GetAll")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testCreateConsultationHours() {
        //Scenario: El sistema devuelve todos los horarios de consulta

        NewConsultationHourDto newConsultationHourDto = new NewConsultationHourDto();
        newConsultationHourDto.dayOfWeek = DayOfWeek.MONDAY;
        newConsultationHourDto.startTime = LocalTime.of(8, 0);
        newConsultationHourDto.endTime = LocalTime.of(17, 0);
        newConsultationHourDto.medicSpecialistId = 101L;

        given()
                .contentType(ContentType.JSON)
                .body(newConsultationHourDto)
                .when().post("ConsultationHours/Create")
                .then()
                .statusCode(201)
                .body("dayOfWeek", equalTo("MONDAY"))
                .body("startTime", equalTo("08:00:00"))
                .body("endTime", equalTo("17:00:00"))
                .body("medicSpecialistId", equalTo(101));
    }
}
