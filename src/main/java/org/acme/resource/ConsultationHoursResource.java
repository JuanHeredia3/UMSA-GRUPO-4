package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;
import org.acme.dtos.ConsultationHoursDto;
import org.acme.dtos.NewConsultationHourDto;
import org.acme.service.ConsultationHoursService;
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

@Tag(name = "ConsultationHours", description = "Operations related to consultation hours")
@RolesAllowed("admin")
@Path("/ConsultationHours")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultationHoursResource {

    @Inject
    ConsultationHoursService consultationHoursService;
    
    @GET
    @Path("/GetAll")
    @Operation(summary = "Get all consultation hours", description = "Returns a list of all consultation hours.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "List of consultation hours", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationHoursDto.class))),
        @APIResponse(responseCode = "204", description = "No consultation hours found")
    })
    public Response getAll() {

        try {
            List<ConsultationHoursDto> consultationHoursList = consultationHoursService.getAll();
                    
        if (consultationHoursList.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.ok(consultationHoursList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }
    
    
    @POST
    @Path("/Create")
    @Operation(summary = "Create a new consultation hour", description = "Creates a new consultation hour.")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Consultation hour created", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewConsultationHourDto.class))),
        @APIResponse(responseCode = "400", description = "Invalid input"),
        @APIResponse(responseCode = "500", description = "Internal server error")
    })
    public Response create(NewConsultationHourDto newConsultationHourDto) {
        try {
            ConsultationHoursDto created = consultationHoursService.create(newConsultationHourDto);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }
    
    @DELETE
    @Path("/Delete/{id}")
    @Operation(summary = "Delete a consultation hour", description = "Deletes an existing consultation hour by ID.")
    @APIResponses({
        @APIResponse(responseCode = "204", description = "Consultation hour deleted successfully"),
        @APIResponse(responseCode = "404", description = "Consultation hour not found")
    })
    public Response deleteShiftById(@PathParam("id") Long id) {
        boolean deleted = consultationHoursService.delete(id);
        return deleted ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
    }
}
