package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Controller.Exception.UserNotFoundException;
import com.umbrella.demoSpringBoot.Domain.User;
import com.umbrella.demoSpringBoot.Repository.UserRepository;
import com.umbrella.demoSpringBoot.Security.UserDetailsServiceImpl;
import com.umbrella.demoSpringBoot.Service.UserService;
import com.umbrella.demoSpringBoot.Service.dto.UserDTO;
import com.umbrella.demoSpringBoot.Service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MongoTemplate mongoTemplate;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
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
    public UserDTO update(UserDTO userDTO) {
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
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
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

    @Override
    public Optional<UserDTO> getUserWithAuthorities() {
        Optional<User> optionalUser = UserDetailsServiceImpl.getCurrentUserLogin().flatMap(userRepository::findByUsername);
        if (optionalUser.isPresent()) {
            return Optional.of(userMapper.toDto(optionalUser.get()));
        }
        return Optional.empty();
    }

    @Override
    public Page<UserDTO> findUserByPeriod(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        Query query = new Query();
        if (toDate == null) {
            toDate = LocalDate.now();
        }
        query.addCriteria(Criteria.where("creationDate").gte(fromDate).lte(toDate)).with(pageable);
        long total = mongoTemplate.count(query, User.class);
        List<User> users = mongoTemplate.find(query, User.class);
        // Page<User> users = userRepository.findByCreationDateBetween(fromDate,toDate,pageable)
        List<UserDTO> usersDTO = userMapper.toDto(users);
        return new PageImpl(usersDTO, pageable, total);
    }
}
