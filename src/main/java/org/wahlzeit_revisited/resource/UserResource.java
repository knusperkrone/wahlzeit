package org.wahlzeit_revisited.resource;


import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.wahlzeit.services.SysLog;
import org.wahlzeit_revisited.dto.LoginRequestDto;
import org.wahlzeit_revisited.dto.UserCreationDto;
import org.wahlzeit_revisited.model.User;
import org.wahlzeit_revisited.service.UserService;

@Path("api/user")
public class UserResource extends AbstractResource {

    @Inject
    UserService service;

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserCreationDto creationDto) {
        if (creationDto.getUsername() == null || creationDto.getEmail() == null || creationDto.getPassword() == null) {
            return buildBadRequest();
        }

        try {
            return service.createUser(creationDto.getUsername(), creationDto.getEmail(), creationDto.getPassword());
        } catch (Exception e) {
            SysLog.logThrowable(e);
            return buildServerError();
        }
    }

    @Path("/login")
    @POST()
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDto creationDto) {
        if (creationDto.getUsername() == null || creationDto.getPassword() == null) {
            return buildBadRequest();
        }

        try {
            return service.login(creationDto.getUsername(), creationDto.getPassword());
        } catch (Exception e) {
            SysLog.logThrowable(e);
            return buildServerError();
        }
    }

    @DELETE
    @RolesAllowed(USER_ROLE)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser() {
        User user = getAuthorizedUser();

        try {
            return service.deleteUser(user);
        } catch (Exception e) {
            SysLog.logThrowable(e);
            return buildServerError();
        }
    }

}
