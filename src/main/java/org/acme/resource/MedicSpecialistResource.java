package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.acme.entity.MedicSpecialist;
import org.acme.service.MedicSpecialistService;

@Path("/MedicSpecialist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicSpecialistResource {
    @Inject
    MedicSpecialistService medicSpecialistService;
    
    @Path("/GetAll")
    @GET
    public List<MedicSpecialist> getAll(){
       return medicSpecialistService.getAll();
    }
}
