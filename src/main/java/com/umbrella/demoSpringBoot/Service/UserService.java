package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Service.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO getUserById(String id);

    void deleteUserById(String id);

    void changeUserStatus(String id, boolean status);
}
