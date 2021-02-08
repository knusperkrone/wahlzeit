package org.wahlzeit_revisited.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.wahlzeit_revisited.model.User;

public abstract class AbstractResource {

    public static final String USER_ROLE = "USER";

    @Inject
    SecurityContext securityContext;

    protected User getAuthorizedUser() {
        return (User) securityContext.getUserPrincipal();
    }

    protected Response buildBadRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    protected Response buildServerError() {
        return Response.serverError().build();
    }

}
