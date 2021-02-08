package org.wahlzeit_revisited.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.wahlzeit.services.SysLog;
import org.wahlzeit_revisited.dto.UserDto;
import org.wahlzeit_revisited.model.User;
import org.wahlzeit_revisited.model.repository.UserReadRepository;
import org.wahlzeit_revisited.model.repository.UserWriteRepository;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    @Inject
    UserWriteRepository writeRepository;

    @Inject
    UserReadRepository readRepository;

    /*
     * business methods
     */

    public Response createUser(String username, String email, String plainPassword) throws SQLException {
        User newUser = new User(username, email, plainPassword);

        // persist
        newUser = writeRepository.insert(newUser);
        SysLog.logSysInfo(String.format("Created user %s (%s)  ", username, newUser.getId()));

        UserDto responseDto = Transformer.transform(newUser);
        return Response.ok(responseDto).build();
    }

    public Response login(String username, String password) throws SQLException {
        Optional<User> loginUserOpt = readRepository.findByUsernamePassword(username, password);
        if (loginUserOpt.isEmpty()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        UserDto responseDto = Transformer.transform(loginUserOpt.get());
        return Response.ok(responseDto).build();
    }

    public Response deleteUser(User user) throws SQLException {
        User deletedUser = writeRepository.delete(user);

        UserDto responseDto = Transformer.transform(deletedUser);
        return Response.ok(responseDto).build();
    }

}
