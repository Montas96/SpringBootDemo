package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO getUserById(String id);

    void deleteUserById(String id);

    void changeUserStatus(String id, boolean status);

    Optional<UserDTO> getUserWithAuthorities();

    Page<UserDTO> findUserByPeriod(LocalDate fromDate, LocalDate toDate, Pageable pageable);

    Page<UserDTO> getAllUsers( Pageable pageable);
}
