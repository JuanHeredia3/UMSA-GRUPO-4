package org.acme.resource;

import org.acme.entity.Shift;
import org.acme.service.ShiftService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
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
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid input")
                    .build();
        }
    }

    @PUT
    @Path("/Edit/{id}")
    @Operation(summary = "Update a shift", description = "Updates an existing shift by ID.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Shift updated successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Shift.class))),
        @APIResponse(responseCode = "404", description = "Shift not found")
    })
    public Response updateShiftById(@PathParam("id") Long shiftId, Shift shift) {
        boolean updated = shiftService.updateShift(shiftId, shift);
        return updated ? Response.ok(shift).build() : Response.status(Status.NOT_FOUND).build();
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
