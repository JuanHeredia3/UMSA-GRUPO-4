package org.acme.tests.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.acme.dtos.NewRecipeDto;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RecipeTest {
    
    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testGetAllRecipes() {
        //Scenario: El sistema devuelve todas las recetas

        given()
                .when().get("Recipe/GetAll")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
    
    //Hacer este test en el service
//    @Test
//    @TestSecurity(user = "admin", roles = {"admin"})
//    public void testCreateRecipe() {
//        //Scenario: El sistema crea una receta
//
//        NewRecipeDto newRecipeDto = new NewRecipeDto();
//        newRecipeDto.description = "Test Create";
//        newRecipeDto.shiftId = 101L;
//        
//        given()
//                .contentType(ContentType.JSON)
//                .body(newRecipeDto)
//                .when().post("Recipe/Create")
//                .then()
//                .statusCode(201)
//                .body("description", equalTo("Test Create"))
//                .body("shiftId", equalTo(101));
//    }
}
