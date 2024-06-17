package org.acme.tests.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import org.acme.dtos.NewShiftDto;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ShiftTest {

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

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testCreateShift() {
        // Scenario: El sistema crea un turno

        NewShiftDto newShiftDto = new NewShiftDto();
        newShiftDto.pacientName = "John Doe";
        newShiftDto.shiftDate = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        newShiftDto.startTime = LocalTime.of(10, 0);
        newShiftDto.endTime = LocalTime.of(11, 0);
        newShiftDto.consultation = "Routine Checkup";
        newShiftDto.medicSpecialistId = 101L;

        given()
                .contentType(ContentType.JSON)
                .body(newShiftDto)
                .when().post("/Shift/Create")
                .then()
                .statusCode(201)
                    .body("pacientName", equalTo("John Doe"))
                .body("shiftDate", notNullValue())
                .body("startTime", equalTo("10:00:00"))
                .body("endTime", equalTo("11:00:00"))
                .body("consultation", equalTo("Routine Checkup"))
                .body("medicSpecialistId", equalTo(101));
    }
}
