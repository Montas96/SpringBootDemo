package com.umbrella.demoSpringBoot.Security.payloads;

public class JwtResponse {
    private String token;
//    private String type;
//    private String id;
//    private String username;
//    private String email;
//    private List<String> roles;

    public JwtResponse(String jwt) {
        this.token = jwt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
