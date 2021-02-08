package org.wahlzeit_revisited.service;

import org.wahlzeit_revisited.dto.UserDto;
import org.wahlzeit_revisited.model.User;

public class Transformer {

    public static UserDto transform(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

}
