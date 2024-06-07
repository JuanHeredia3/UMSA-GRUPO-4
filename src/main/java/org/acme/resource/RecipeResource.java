package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.acme.dtos.RecipeDto;
import org.acme.service.RecipeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Recipe", description = "Operations related to medical recipes")
@Path("/Recipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeResource {

    @Inject
    RecipeService recipeService;

    @GET
    @Path("/GetAll")
    @Operation(summary = "Get all recipes", description = "Returns a list of recipes.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "List of recipes",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDto.class))),
        @APIResponse(responseCode = "204", description = "No recipes found")
    })
    public Response getAll() {

        try {
            List<RecipeDto> recipeList = recipeService.getAll();

            if (recipeList.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

            return Response.ok(recipeList).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }

    @GET
    @Path("/Get/{id}")
    @Operation(summary = "Get recipe", description = "Returns a recipe.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "A recipe",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDto.class))),
        @APIResponse(responseCode = "204", description = "recipe not found")
    })
    public Response getById(@PathParam("id") Long id) {

        try {
            RecipeDto recipe = recipeService.get(id);

            return recipe == null ? Response.status(Response.Status.NO_CONTENT).build() : Response.ok(recipe).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }
    
    @DELETE
    @Path("/Delete/{id}")
    @Operation(summary = "delete a recipe")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "true",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDto.class))),
        @APIResponse(responseCode = "204", description = "recipe not found")
    })
    public Response delete(@PathParam("id") Long id) {

        try {
            boolean isDeleted = recipeService.deleteRecipe(id);

            return isDeleted ? Response.ok(isDeleted).build() : Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }
}
