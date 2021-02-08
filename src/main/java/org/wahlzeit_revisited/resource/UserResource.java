package org.wahlzeit_revisited.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.wahlzeit.services.SysLog;
import org.wahlzeit_revisited.dto.UserCreationDto;
import org.wahlzeit_revisited.service.UserService;

@Path("api/user")
public class UserResource {

    @Inject
    UserService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserCreationDto creationDto) {
        if (creationDto.getUsername() == null || creationDto.getEmail() == null || creationDto.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return service.createUser(creationDto.getUsername(), creationDto.getEmail(), creationDto.getPassword());
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@FormParam("username") String username, @FormParam("password") String password) {
        if (username == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return service.deleteUser(username, password);
    }

}
