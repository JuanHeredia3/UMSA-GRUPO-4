package org.acme.resource;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;

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

@Path("/Shifts")@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShiftResource {

	
	@Inject
	ShiftService shiftService;
	
	@POST
	public Response addShift(Shift shift) {
		
		if(shiftService.addShift(shift)) {
			return Response.created(URI.create("/Shifts/" + shift.getShiftId())).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@PUT
	@Path("{id}")
	public Response updateShiftById(@PathParam("id") Long shiftId, Shift shift) {
		boolean updated = shiftService.updateShift(shiftId, shift);
		return updated ? Response.ok(shift).build() : Response.status(Status.NOT_FOUND).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletedShiftById(@PathParam("id") Long shiftId) {
		boolean deleted = shiftService.deleteShift(shiftId);
		return deleted ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
	}
	
}
