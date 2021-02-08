package org.wahlzeit_revisited.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.wahlzeit.services.SysLog;
import org.wahlzeit_revisited.db.repository.UserReadRepository;
import org.wahlzeit_revisited.db.repository.UserWriteRepository;
import org.wahlzeit_revisited.dto.UserDto;
import org.wahlzeit_revisited.model.User;

import java.sql.SQLException;

public class UserService {

    @Inject
    UserWriteRepository writeRepository;

    @Inject
    UserReadRepository readRepository;

    /*
     * business methods
     */

    public Response createUser(String username, String email, String password) {
        User newUser = new User(username, email, password);

        try {
            newUser = writeRepository.insert(newUser);
        } catch (SQLException sqlException) {
            SysLog.logThrowable(sqlException);
            return Response.serverError().build();
        }
        SysLog.logSysInfo(String.format("Created user %s (%s)  ", username, newUser.getId()));

        UserDto dto = Transformer.transform(newUser);
        return Response.ok(dto).build();
    }

    public Response deleteUser(String username, String password) {
        return Response.ok().build();
    }

}
