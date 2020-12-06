package com.umbrella.demoSpringBoot.Service.mapper;

import com.umbrella.demoSpringBoot.Domain.User;
import com.umbrella.demoSpringBoot.Service.dto.UserDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDTO> {

    default User fromId(String id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
