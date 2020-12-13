package com.umbrella.demoSpringBoot.Controller;

import com.umbrella.demoSpringBoot.Domain.pojo.DatePeriod;
import com.umbrella.demoSpringBoot.Service.UserService;
import com.umbrella.demoSpringBoot.Service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if (!StringUtils.hasText(userDTO.getId())) {
            throw new RuntimeException();
        }
        UserDTO user = userService.create(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> page = userService.getAllUsers(pageable);
        return ResponseEntity.ok(page);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id) {
        if (StringUtils.hasText(id)) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (StringUtils.hasText(id)) {
            throw new RuntimeException();
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("users-by-period")
    public Page<UserDTO> findUserByPeriod(@RequestBody DatePeriod datePeriod, Pageable pageable) {
        return userService.findUserByPeriod(datePeriod.getFromDate(), datePeriod.getToDate(), pageable);
    }
}
