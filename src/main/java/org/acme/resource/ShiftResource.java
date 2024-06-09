package org.acme.resource;

import org.acme.service.ShiftService;
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
import org.acme.dtos.NewShiftDto;
import org.acme.dtos.ShiftDto;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Shift", description = "Operations related to shifts")
@Path("/Shift")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShiftResource {

    @Inject
    ShiftService shiftService;

    @GET
    @Path("/GetAll")
    @Operation(summary = "Get all shifts", description = "Returns a list of all shifts.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "List of shifts",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShiftDto.class))),
        @APIResponse(responseCode = "204", description = "No shifts found")
    })
    public Response getAll() {
        try {
            List<ShiftDto> shiftList = shiftService.getAll();

            if (shiftList.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

            return Response.ok(shiftList).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing your request")
                    .build();
        }
    }
    
    @POST
    @Path("/Create")
    @Operation(summary = "Create a new shift", description = "Creates a new shift.")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Shift created successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShiftDto.class))),
        @APIResponse(responseCode = "400", description = "Invalid input")
    })
    public Response create(NewShiftDto newShift) {
        try {
            ShiftDto createdShift = shiftService.create(newShift);
            return Response.status(Response.Status.CREATED).entity(createdShift).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/Edit/{id}")
    @Operation(summary = "Update a shift", description = "Updates an existing shift by ID.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Shift updated successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShiftDto.class))),
        @APIResponse(responseCode = "404", description = "Shift not found")
    })
    public Response updateShiftById(@PathParam("id") Long shiftId, ShiftDto shiftDto) {
        boolean updated = shiftService.updateShift(shiftId, shiftDto);
        return updated ? Response.ok(shiftDto).build() : Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/Delete/{id}")
    @Operation(summary = "Delete a shift", description = "Deletes an existing shift by ID.")
    @APIResponses({
        @APIResponse(responseCode = "204", description = "Shift deleted successfully"),
        @APIResponse(responseCode = "404", description = "Shift not found")
    })
    public Response deleteShiftById(@PathParam("id") Long shiftId) {
        boolean deleted = shiftService.deleteShift(shiftId);
        return deleted ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
    }
}
