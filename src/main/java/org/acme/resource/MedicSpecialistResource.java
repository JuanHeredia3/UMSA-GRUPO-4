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
import org.acme.dtos.MedicSpecialistDto;
import org.acme.dtos.NewMedicSpecialist;
import org.acme.service.MedicSpecialistService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "MedicSpecialist", description = "Operations related to medical specialists")
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
}
