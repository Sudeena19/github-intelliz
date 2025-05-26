package com.example.user.model;

public class CreateUserRequest {
    private Integer id;
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreateUserRequest(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
