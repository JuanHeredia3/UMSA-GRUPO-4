package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.acme.entity.ConsultationHours;
import org.acme.service.ConsultationHoursService;

@Path("/ConsultationHours")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultationHoursResource {
    @Inject
    ConsultationHoursService consultationHoursService;
    
    @Path("/GetAll")
    @GET
    public List<ConsultationHours> getAll(){
       return consultationHoursService.getAll();
    }
}
