package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.acme.dtos.ConsultationHoursDto;
import org.acme.dtos.NewConsultationHourDto;
import org.acme.service.ConsultationHoursService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "ConsultationHours", description = "Operations related to consultation hours")
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
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }
}
