package com.umbrella.demoSpringBoot.Security.payloads;

import com.umbrella.demoSpringBoot.Domain.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    @NotNull
    @NotEmpty
    private String username;
    @NotBlank
    @NotNull
    @NotEmpty
    private String email;
    @NotBlank
    @NotNull
    @NotEmpty
    private String password;
    private Set<String> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
