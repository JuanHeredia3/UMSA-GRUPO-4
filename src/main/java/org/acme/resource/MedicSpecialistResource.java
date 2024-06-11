package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;
import org.acme.dtos.MedicSpecialistDto;
import org.acme.dtos.NewMedicSpecialist;
import org.acme.dtos.UpdateMedicSpecialistDto;
import org.acme.service.MedicSpecialistService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@SecurityScheme(
    securitySchemeName = "keycloak",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/realms/master/protocol/openid-connect/token"))
)

@Tag(name = "MedicSpecialist", description = "Operations related to medical specialists")
@RolesAllowed("admin")
@Path("/MedicSpecialist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicSpecialistResource {

    @Inject
    MedicSpecialistService medicSpecialistService;

    @GET
    @Path("/GetAll")
    @Operation(summary = "Get all medical specialists", description = "Returns a list of all medical specialists.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "List of medical specialists", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MedicSpecialistDto.class))),
        @APIResponse(responseCode = "204", description = "No medical specialists found")
    })
    public Response getAll() {

        try {
            List<MedicSpecialistDto> medicSpecialistList = medicSpecialistService.getAll();

            if (medicSpecialistList.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

            return Response.ok(medicSpecialistList).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }
    
    @POST
    @Path("/Create")
    @Operation(summary = "Create a new medical specialist", description = "Creates a new medical specialist.")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Medical specialist created", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MedicSpecialistDto.class))),
        @APIResponse(responseCode = "400", description = "Invalid input")
    })
    public Response create(NewMedicSpecialist newMedicSpecialist) {
        try {
            MedicSpecialistDto createdMedicSpecialist = medicSpecialistService.create(newMedicSpecialist);
            return Response.status(Response.Status.CREATED).entity(createdMedicSpecialist).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid input")
                    .build();
        }
    }
    
    @DELETE
    @Path("/Delete/{id}")
    @Operation(summary = "Delete a medic specialist", description = "Deletes an existing medic specialist by ID.")
    @APIResponses({
        @APIResponse(responseCode = "204", description = "Medic specialist deleted successfully"),
        @APIResponse(responseCode = "404", description = "Medic specialist not found")
    })
    public Response deleteShiftById(@PathParam("id") Long id) {
        boolean deleted = medicSpecialistService.delete(id);
        return deleted ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
    }
    
    @PUT
    @Path("/Update/{id}")
    @Operation(summary = "Update medical specialist", description = "Updates the name, location, and specialty of a medical specialist.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Medical specialist updated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MedicSpecialistDto.class))),
        @APIResponse(responseCode = "404", description = "Medical specialist not found")
    })
    public Response update(@PathParam("id") Long id, UpdateMedicSpecialistDto updateDto) {
        try {
            MedicSpecialistDto updatedMedicSpecialist = medicSpecialistService.update(id, updateDto);
            return Response.ok(updatedMedicSpecialist).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }

}
