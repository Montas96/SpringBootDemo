package com.umbrella.demoSpringBoot.Controller.Exception;

import com.umbrella.demoSpringBoot.Service.UserService;
import com.umbrella.demoSpringBoot.Service.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final UserService userService;


    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public UserDTO getAccount() {
        return userService.getUserWithAuthorities()
                .orElseThrow(() -> new UserNotFoundException());
    }
}
