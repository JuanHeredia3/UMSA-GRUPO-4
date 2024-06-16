package org.acme.exception;



import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<CustomException>{

	@Override
	public Response toResponse(CustomException exception) {
		
		if (exception instanceof BadRequestException)
			return Response.status(Status.BAD_REQUEST).build();
		else {
			if (exception instanceof InternalServerErrorException)
				Response.status(Status.INTERNAL_SERVER_ERROR).build();
			else {
				if (exception instanceof NoContentException)
					return Response.status(Status.NO_CONTENT).build();
				else {
					if(exception instanceof NotFoundException)
						return Response.status(Status.NOT_FOUND).build();
				}
			}
		}
		return Response.status(Status.BAD_REQUEST).build();	
	}

}
