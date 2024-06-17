package org.acme.exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dtos.ErrorMessageDto;

@Provider
public class BusinessRuleExceptionMapper implements ExceptionMapper<BusinessRuleException>{

    @Override
    public Response toResponse(BusinessRuleException e) {
        ErrorMessageDto errorMessage = new ErrorMessageDto(e.getStatus(), e.getMessage());
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessage).type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
