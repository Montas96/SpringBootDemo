package com.umbrella.demoSpringBoot.Service.mapper.impl;

import com.umbrella.demoSpringBoot.Domain.User;
import com.umbrella.demoSpringBoot.Service.dto.UserDTO;
import com.umbrella.demoSpringBoot.Service.mapper.AddressMapper;
import com.umbrella.demoSpringBoot.Service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImp implements UserMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUserName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setActivated(dto.isActivated());
        user.setCreationDate(dto.getCreationDate());
        user.setModificationDate(dto.getModificationDate());
        user.setEmail(dto.getEmail());
        user.setRoles(dto.getRoles());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(addressMapper.toEntity(dto.getAddress()));
        return user;
    }

    @Override
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setActivated(user.isActivated());
        userDTO.setCreationDate(user.getCreationDate());
        userDTO.setModificationDate(user.getModificationDate());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(addressMapper.toDto(user.getAddress()));
        return userDTO;
    }

    @Override
    public List<User> toEntity(List<UserDTO> userDTOS) {
        List<User> users = new ArrayList<>();

        if (CollectionUtils.isEmpty(userDTOS)) {
            return users;
        }
        userDTOS.forEach(userDTO -> {
            users.add(toEntity(userDTO));
        });
        return users;
    }

    @Override
    public List<UserDTO> toDto(List<User> userList) {
        List<UserDTO> userDTOS = new ArrayList<>();

        if (CollectionUtils.isEmpty(userList)) {
            return userDTOS;
        }
        userList.forEach(user -> {
            userDTOS.add(toDto(user));
        });
        return userDTOS;
    }
}
