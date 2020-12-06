package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Controller.Exception.UserNotFoundException;
import com.umbrella.demoSpringBoot.Domain.User;
import com.umbrella.demoSpringBoot.Repository.UserRepository;
import com.umbrella.demoSpringBoot.Service.UserService;
import com.umbrella.demoSpringBoot.Service.dto.UserDTO;
import com.umbrella.demoSpringBoot.Service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }


    @Override
    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException());
        user.setUsername(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setModificationDate(LocalDateTime.now());
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changeUserStatus(String id, boolean status) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setActivated(status);
        userRepository.save(user);
    }
}
