package org.wahlzeit_revisited.service;

import jakarta.ws.rs.core.Response;
import org.wahlzeit_revisited.dto.UserDto;

public class UserService {

    public Response createUser(String username, String password) {
        UserDto mockDto = new UserDto();
        return Response.ok(mockDto).build();
    }

    public Response deleteUser(String username, String password) {
        return Response.ok().build();
    }

}
