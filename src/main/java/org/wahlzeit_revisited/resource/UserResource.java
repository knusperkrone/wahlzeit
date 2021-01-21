package org.wahlzeit_revisited.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.wahlzeit_revisited.service.UserService;

@Path("api/user")
public class UserResource {

    @Inject
    UserService service;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@FormParam("username") String username, @FormParam("password") String password) {
        if (username == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return service.createUser(username, password);
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
